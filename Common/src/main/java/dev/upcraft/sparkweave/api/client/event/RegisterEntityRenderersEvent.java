package dev.upcraft.sparkweave.api.client.event;

import dev.upcraft.sparkweave.api.event.Event;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public interface RegisterEntityRenderersEvent {

	<T extends Entity> void registerRenderer(Supplier<EntityType<T>> entityType, EntityRendererProvider<T> entityRendererProvider);

	Event<Callback> EVENT = Event.create(Callback.class, callbacks -> event -> {
		for (Callback callback : callbacks) {
			callback.registerEntityRenderers(event);
		}
	});

	@FunctionalInterface
	interface Callback {
		void registerEntityRenderers(RegisterEntityRenderersEvent event);
	}
}
