package dev.upcraft.sparkweave.api.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public record LineRenderable(float startX, float startY, float startZ, float endX, float endY, float endZ, int red, int green, int blue, int alpha, long creationTime, long duration) implements DebugRenderable {

	@Override
	public void render(PoseStack poseStack, VertexConsumer vertexConsumer) {
		Matrix4f model = poseStack.last().pose();
		Matrix3f normal = poseStack.last().normal();
		vertexConsumer.vertex(model, startX(), startY(), startZ()).color(red(), green(), blue(), alpha()).normal(normal, 0, 1, 0).endVertex();
		vertexConsumer.vertex(model, endX(), endY(), endZ()).color(red(), green(), blue(), alpha()).normal(normal, 0, 1, 0).endVertex();
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
