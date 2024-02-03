package dev.upcraft.sparkweave;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.ModMetadata;
import org.quiltmc.loader.api.QuiltLoader;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class SparkweaveHelper {

	public static final String MODID = "sparkweave";

	private static final Map<String, ModContainer> MOD_CONTAINERS = new Object2ObjectOpenHashMap<>();

	public static ModContainer getModContainer(String modid) {
		return MOD_CONTAINERS.computeIfAbsent(modid,
				key -> QuiltLoader.getModContainer(key)
						.orElseThrow(() -> new NoSuchElementException("No mod loaded with ID " + key))
		);
	}

	public static Optional<ModMetadata> tryGetMetadata(String modid) {
		return Optional.ofNullable(MOD_CONTAINERS.computeIfAbsent(modid, key -> QuiltLoader.getModContainer(key).orElse(null))).map(ModContainer::metadata);
	}
}
