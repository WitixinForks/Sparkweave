package dev.upcraft.sparkweave.neoforge.service;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.platform.services.SparkweaveHelperService;
import net.minecraft.world.item.CreativeModeTab;

@CalledByReflection
public class NeoHelperService implements SparkweaveHelperService {

	@Override
	public CreativeModeTab.Builder newCreativeTabBuilder() {
		return CreativeModeTab.builder();
	}
}
