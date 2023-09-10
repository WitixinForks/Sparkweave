package dev.upcraft.sparkweave.util.vanity;

import com.google.gson.*;
import com.mojang.serialization.JsonOps;
import dev.upcraft.sparkweave.SparkweaveHelper;
import dev.upcraft.sparkweave.api.util.ContextHelper;
import dev.upcraft.sparkweave.api.util.data.DataStore;
import dev.upcraft.sparkweave.api.util.web.HttpStatus;
import dev.upcraft.sparkweave.util.SparkweaveLogging;
import net.minecraft.Util;
import net.minecraft.util.GsonHelper;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.ModMetadata;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class SparkweaveVanityHandler {

	private static final String DATA_URL = "http://localhost:8000/graphql";
	private static final String GET_VANITY_DATA_QUERY = """
			query {
				users {
					username,
					uuid,
					tier
				}
			}
			""".stripIndent();
	private static final Gson GSON = new GsonBuilder().serializeNulls().create();
	private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();

	private static final DataStore<Map<UUID, SparkweaveVanityData>> DATA_STORE = Util.make(() -> {

		Supplier<Map<UUID, SparkweaveVanityData>> dataFetcher = () -> {
			ModContainer container = ContextHelper.getCurrentContext();
			ModMetadata meta = container.metadata();
			try {
				JsonObject bodyJson = new JsonObject();
				bodyJson.addProperty("query", GET_VANITY_DATA_QUERY);
				String body = GSON.toJson(bodyJson);
				//@formatter:off
				HttpRequest request = HttpRequest.newBuilder(URI.create(DATA_URL))
					.header("Accept", "application/json;charset=UTF-8")
					.header("Content-Type", "application/json;charset=UTF-8")
					.header("User-Agent", String.format("%s/%s (%s)", meta.id(), meta.version(), null)) //TODO OS and java info
					.POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
					.build();
				//@formatter:on

				HttpResponse<InputStream> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofInputStream());
				if (HttpStatus.ok(response.statusCode())) {
					try (var reader = new InputStreamReader(response.body(), StandardCharsets.UTF_8)) {
						JsonObject json = GSON.fromJson(reader, JsonObject.class);
						JsonObject data = GsonHelper.getAsJsonObject(json, "data");
						JsonArray users = GsonHelper.getAsJsonArray(data, "users");

						//@formatter:off
						return SparkweaveVanityData.LIST_CODEC
							.map(list -> list.stream().collect(Collectors.toMap(SparkweaveVanityData::uuid, UnaryOperator.identity())))
							.parse(JsonOps.INSTANCE, users)
							.resultOrPartial(msg -> SparkweaveLogging.getLogger().error("Failed to parse vanity data: {}", msg))
							.orElseThrow(() -> new JsonParseException("Failed to parse vanity data"));
						//@formatter:on
					}
				} else {
					HttpStatus status = HttpStatus.of(response.statusCode());
					SparkweaveLogging.getLogger().error("Got Status {} ({}) while trying to fetch vanity data! see {}", status.code(), status.displayName(), status.getDescriptionUrl());
				}
			} catch (JsonParseException | IOException | InterruptedException e) {
				SparkweaveLogging.getLogger().error("Failed to retrieve vanity data", e);
			}
			return Map.of();
		};
		return new DataStore<>(SparkweaveHelper.MODID + "-vanity-data", dataFetcher, Duration.ofMinutes(10), true);
	});

	@Nullable
	public static SparkweaveVanityData getData(UUID uuid) {
		return DATA_STORE.get().get(uuid);
	}

	public static boolean has(UUID uuid) {
		return DATA_STORE.get().containsKey(uuid);
	}

	public static Map<UUID, SparkweaveVanityData> getAll() {
		return DATA_STORE.get();
	}

	public static CompletableFuture<Void> refresh(Executor executor, boolean force) {
		return DATA_STORE.refresh(executor, force);
	}
}
