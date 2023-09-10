package dev.upcraft.test.sparkweave.init;

import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import dev.upcraft.sparkweave.api.util.item.ItemGroupHelper;
import dev.upcraft.test.sparkweave.SparkweaveTestMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

public class TestCreativeTabs {

    public static final RegistryHandler<CreativeModeTab> TABS = RegistryHandler.create(Registries.CREATIVE_MODE_TAB, SparkweaveTestMod.MODID);

    public static final RegistrySupplier<CreativeModeTab> ITEMS = TABS.register("items", () -> FabricItemGroup.builder()
            .title(Component.translatable("itemGroup.sparkweave-testmod.items"))
            .icon(() -> TestItems.TEST_ITEM.get().getDefaultInstance())
            .displayItems((itemDisplayParameters, output) -> ItemGroupHelper.addRegistryEntries(output, TestItems.ITEMS))
            .build()
    );
}
