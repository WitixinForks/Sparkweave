package dev.upcraft.sparkweave.neoforge.entrypoint;

import dev.upcraft.sparkweave.SparkweaveMod;
import dev.upcraft.sparkweave.api.client.render.DebugRenderer;
import dev.upcraft.sparkweave.validation.TranslationChecker;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@Mod.EventBusSubscriber(modid = SparkweaveMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Client {

	public static void init() {
		//TODO init
	}

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

	@SubscribeEvent
	public static void onRenderWorld(RenderLevelStageEvent event) {
		if(event.getStage() == RenderLevelStageEvent.Stage.AFTER_ENTITIES) {
			DebugRenderer.render(event.getPoseStack(), event.getLevelRenderer().renderBuffers.bufferSource(), event.getCamera());
		}
	}
}
