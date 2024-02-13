package dev.upcraft.sparkweave.neoforge.service;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.platform.services.SparkweaveHelperService;
import net.minecraft.world.item.CreativeModeTab;

public class NeoHelperService implements SparkweaveHelperService {

	@CalledByReflection
	public NeoHelperService() {

	}

	@Override
	public CreativeModeTab.Builder newCreativeTabBuilder() {
		return CreativeModeTab.builder();
	}
}
