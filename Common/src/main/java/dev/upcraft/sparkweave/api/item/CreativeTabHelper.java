package dev.upcraft.sparkweave.api.item;

import dev.upcraft.sparkweave.api.platform.Services;
import dev.upcraft.sparkweave.api.platform.services.SparkweaveHelperService;
import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;

public class CreativeTabHelper {

	private static final SparkweaveHelperService HELPER = Services.getService(SparkweaveHelperService.class);

    public static <T extends ItemLike> void addRegistryEntries(CreativeModeTab.Output collector, RegistryHandler<T> itemProvider) {
        itemProvider.stream().forEach(supplier -> collector.accept(supplier.get()));
    }

	public static CreativeModeTab.Builder newBuilder() {
		return HELPER.newCreativeTabBuilder();
	}
}
