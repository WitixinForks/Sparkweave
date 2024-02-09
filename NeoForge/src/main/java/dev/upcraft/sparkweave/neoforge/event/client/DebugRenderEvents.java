package dev.upcraft.sparkweave.neoforge.event.client;

import dev.upcraft.sparkweave.SparkweaveMod;
import dev.upcraft.sparkweave.api.client.render.DebugRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@Mod.EventBusSubscriber(modid = SparkweaveMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DebugRenderEvents {

	@SubscribeEvent
	public static void onRenderWorld(RenderLevelStageEvent event) {
		if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_ENTITIES) {
			DebugRenderer.render(event.getPoseStack(), event.getLevelRenderer().renderBuffers.bufferSource(), event.getCamera());
		}
	}
}
