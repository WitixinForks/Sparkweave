package dev.upcraft.sparkweave.quilt.service;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.platform.services.SparkweaveHelperService;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.world.item.CreativeModeTab;

public class QuiltHelperService implements SparkweaveHelperService {

	@CalledByReflection
	public QuiltHelperService() {
		// need an explicit default constructor for the service loader to work
	}

	@Override
	public CreativeModeTab.Builder newCreativeTabBuilder() {
		return FabricItemGroup.builder();
	}
}
