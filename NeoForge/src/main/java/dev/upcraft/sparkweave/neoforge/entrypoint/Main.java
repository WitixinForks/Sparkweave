package dev.upcraft.sparkweave.neoforge.entrypoint;

import dev.upcraft.sparkweave.SparkweaveMod;
import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.registry.block.BlockItemProvider;
import dev.upcraft.sparkweave.logging.SparkweaveLogging;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.registries.RegisterEvent;

@CalledByReflection
@Mod(SparkweaveMod.MODID)
public class Main {

	public Main() {
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
		SparkweaveLogging.getLogger().debug("System initialized!");
	}

	@SubscribeEvent
	public void processBlockItems(RegisterEvent event) {
		if(event.getRegistryKey() == Registries.ITEM) {
			BuiltInRegistries.BLOCK.entrySet().forEach(entry -> {
				if(entry.getValue() instanceof BlockItemProvider provider) {
					event.register(Registries.ITEM, entry.getKey().location(), provider::createItem);
				}
			});
		}
	}
}
