package dev.upcraft.sparkweave.testmod.init;

import dev.upcraft.sparkweave.api.item.CreativeTabHelper;
import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import dev.upcraft.sparkweave.testmod.SparkweaveTestmod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

public class TestCreativeTabs {

	public static final RegistryHandler<CreativeModeTab> TABS = RegistryHandler.create(Registries.CREATIVE_MODE_TAB, SparkweaveTestmod.MODID);

	public static final RegistrySupplier<CreativeModeTab> ITEMS = TABS.register("items", () -> CreativeTabHelper.newBuilder()
		.title(Component.translatable("itemGroup.sparkweave-testmod.items"))
		.icon(() -> TestItems.TEST_ITEM.get().getDefaultInstance())
		.displayItems((itemDisplayParameters, output) -> CreativeTabHelper.addRegistryEntries(output, TestItems.ITEMS))
		.build()
	);
}
