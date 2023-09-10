package dev.upcraft.test.sparkweave;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.registry.RegistryService;
import dev.upcraft.test.sparkweave.init.TestCreativeTabs;
import dev.upcraft.test.sparkweave.init.TestItems;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

@CalledByReflection
public class SparkweaveTestMod implements ModInitializer {
    public static final String MODID = "sparkweave-testmod";

    @Override
	public void onInitialize(ModContainer mod) {
        var service = RegistryService.get();
        TestItems.ITEMS.accept(service);
        TestCreativeTabs.TABS.accept(service);
	}
}
