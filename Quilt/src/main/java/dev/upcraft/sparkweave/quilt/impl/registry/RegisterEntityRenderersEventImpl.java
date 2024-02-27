package dev.upcraft.sparkweave.quilt.impl.registry;

import dev.upcraft.sparkweave.api.client.event.RegisterEntityRenderersEvent;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public class RegisterEntityRenderersEventImpl implements RegisterEntityRenderersEvent {

	@Override
	public <T extends Entity> void registerRenderer(Supplier<EntityType<? extends T>> entityType, EntityRendererProvider<T> entityRendererProvider) {
		EntityRendererRegistry.register(entityType.get(), entityRendererProvider);
	}
}
