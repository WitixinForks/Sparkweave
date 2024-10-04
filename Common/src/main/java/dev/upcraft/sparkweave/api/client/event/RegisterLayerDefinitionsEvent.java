package dev.upcraft.sparkweave.api.client.event;

import dev.upcraft.sparkweave.api.event.Event;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.function.Supplier;

public interface RegisterLayerDefinitionsEvent {

	void registerModelLayers(ModelLayerLocation layerLocation, Supplier<LayerDefinition> supplier);

	Event<RegisterLayerDefinitionsEvent.Callback> EVENT = Event.create(RegisterLayerDefinitionsEvent.Callback.class, callbacks -> event -> {
		for (RegisterLayerDefinitionsEvent.Callback callback : callbacks) {
			callback.registerModelLayers(event);
		}
	});

	@FunctionalInterface
	interface Callback {
		void registerModelLayers(RegisterLayerDefinitionsEvent event);
	}
}
