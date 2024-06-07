package dev.upcraft.sparkweave.neoforge.event.client;

import dev.upcraft.sparkweave.SparkweaveMod;
import dev.upcraft.sparkweave.api.client.render.DebugRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@EventBusSubscriber(modid = SparkweaveMod.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class DebugRenderEvents {

	@SubscribeEvent
	public static void onRenderWorld(RenderLevelStageEvent event) {
		if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_ENTITIES) {
			DebugRenderer.render(event.getPoseStack(), event.getLevelRenderer().renderBuffers.bufferSource(), event.getCamera());
		}
	}
}
