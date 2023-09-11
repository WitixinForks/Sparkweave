package dev.upcraft.sparkweave.entrypoint;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.impl.registry.BlockItemProviderProcessor;
import dev.upcraft.sparkweave.util.SparkweaveLogging;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

@CalledByReflection
public class Main implements ModInitializer {

	@Override
	public void onInitialize(ModContainer mod) {
		BlockItemProviderProcessor.register();
		SparkweaveLogging.getLogger().debug("System initialized!");
	}
}
