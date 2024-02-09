package dev.upcraft.sparkweave.api.item;

import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;

public class ItemGroupHelper {

    public static <T extends ItemLike> void addRegistryEntries(CreativeModeTab.Output collector, RegistryHandler<T> itemProvider) {
        itemProvider.stream().forEach(supplier -> collector.accept(supplier.get()));
    }
}
