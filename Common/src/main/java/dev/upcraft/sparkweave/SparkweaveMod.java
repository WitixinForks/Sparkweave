package dev.upcraft.sparkweave;

import com.google.common.base.Preconditions;
import dev.upcraft.sparkweave.api.entrypoint.MainEntryPoint;
import dev.upcraft.sparkweave.api.event.CommandEvents;
import dev.upcraft.sparkweave.api.platform.ModContainer;
import dev.upcraft.sparkweave.command.SparkweaveCommandRoot;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.stream.Stream;

public class SparkweaveMod implements MainEntryPoint {

	public static final String MODID = "sparkweave";

	@Override
	public void onInitialize(ModContainer mod) {
		CommandEvents.REGISTER.register(SparkweaveCommandRoot::register);
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MODID, path);
	}

	public static List<ResourceLocation> ids(String... paths) {
		Validate.notEmpty(paths, "Must provide at least 1 ID!");
		return Stream.of(paths).map(SparkweaveMod::id).toList();
	}
}
