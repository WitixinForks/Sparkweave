package dev.upcraft.sparkweave.neoforge.event.client;

import dev.upcraft.sparkweave.SparkweaveMod;
import dev.upcraft.sparkweave.validation.TranslationChecker;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;

@EventBusSubscriber(modid = SparkweaveMod.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ResourcePackEvents {

	@SubscribeEvent
	public static void onRegisterReloadListeners(RegisterClientReloadListenersEvent event) {
		event.registerReloadListener(new ResourceManagerReloadListener() {

			@Override
			public String getName() {
				return SparkweaveMod.MODID + "_translation_check";
			}

			@Override
			public void onResourceManagerReload(ResourceManager resourceManager) {
				TranslationChecker.validate();
			}
		});
	}
}
