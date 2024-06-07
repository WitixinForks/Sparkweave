package dev.upcraft.sparkweave.api.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;

public record LineRenderable(float startX, float startY, float startZ, float endX, float endY, float endZ, int red, int green, int blue, int alpha, long creationTime, long duration) implements DebugRenderable {

	@Override
	public void render(PoseStack poseStack, VertexConsumer vertexConsumer) {
		var pose = poseStack.last();
		vertexConsumer.vertex(pose, startX(), startY(), startZ()).color(red(), green(), blue(), alpha()).normal(pose, 0, 1, 0).endVertex();
		vertexConsumer.vertex(pose, endX(), endY(), endZ()).color(red(), green(), blue(), alpha()).normal(pose, 0, 1, 0).endVertex();
	}

	@Override
	public long getCreationTimeMs() {
		return creationTime;
	}

	@Override
	public long getRenderDurationMs() {
		return duration;
	}

	@Override
	public RenderType getRenderLayer() {
		return RenderType.lines();
	}
}
