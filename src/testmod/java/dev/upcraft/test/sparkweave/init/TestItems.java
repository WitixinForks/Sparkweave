package dev.upcraft.test.sparkweave.init;

import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import dev.upcraft.test.sparkweave.SparkweaveTestMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public class TestItems {

    public static final RegistryHandler<Item> ITEMS = RegistryHandler.create(Registries.ITEM, SparkweaveTestMod.MODID);

    public static final RegistrySupplier<Item> TEST_ITEM = ITEMS.register("test_item", () -> new Item(new Item.Properties()));
}
