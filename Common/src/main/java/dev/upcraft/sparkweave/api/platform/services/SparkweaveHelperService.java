package dev.upcraft.sparkweave.api.platform.services;

import net.minecraft.world.item.CreativeModeTab;

public interface SparkweaveHelperService {

	/**
	 * @see dev.upcraft.sparkweave.api.item.CreativeTabHelper
	 */
	CreativeModeTab.Builder newCreativeTabBuilder();
}
