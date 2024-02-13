package dev.upcraft.sparkweave.testmod.quilt.entrypoint;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.testmod.SparkweaveTestmod;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

@CalledByReflection
public class Main implements ModInitializer {

	@Override
	public void onInitialize(ModContainer mod) {
		SparkweaveTestmod.init();
	}
}
