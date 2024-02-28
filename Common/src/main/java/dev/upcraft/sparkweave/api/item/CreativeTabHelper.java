package dev.upcraft.sparkweave.api.item;

import dev.upcraft.sparkweave.api.platform.Services;
import dev.upcraft.sparkweave.api.platform.services.SparkweaveHelperService;
import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.item.CreativeTabFiller;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;

public class CreativeTabHelper {

	private static final SparkweaveHelperService HELPER = Services.getService(SparkweaveHelperService.class);
	private static final RandomSource RANDOM_SOURCE = RandomSource.create();

	public static <T extends ItemLike> void addRegistryEntries(CreativeModeTab.ItemDisplayParameters displayParameters, CreativeModeTab.Output collector, RegistryHandler<T> itemProvider) {
		itemProvider.stream().forEach(supplier -> {

			var registryObject = supplier.get();

			if (registryObject instanceof CreativeTabFiller filler) {
				filler.addItemsToTab(displayParameters, collector, RANDOM_SOURCE);
			} else {
				collector.accept(registryObject);
			}
		});
	}

	public static RandomSource getRandom() {
		return RANDOM_SOURCE;
	}

	/**
	 * @deprecated Use {@link #addRegistryEntries(CreativeModeTab.ItemDisplayParameters, CreativeModeTab.Output, RegistryHandler)} instead
	 */
	@Deprecated(since = "0.105.0", forRemoval = true)
	public static <T extends ItemLike> void addRegistryEntries(CreativeModeTab.Output collector, RegistryHandler<T> itemProvider) {
		itemProvider.stream().forEach(supplier -> collector.accept(supplier.get()));
	}

	public static CreativeModeTab.Builder newBuilder() {
		return HELPER.newCreativeTabBuilder();
	}
}
