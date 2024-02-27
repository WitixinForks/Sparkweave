package dev.upcraft.sparkweave.api.client.event;

import dev.upcraft.sparkweave.api.event.Event;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

@SuppressWarnings("deprecation")
public interface RegisterItemPropertiesEvent {

	default ItemPropertyFunction registerGeneric(ResourceLocation id, ClampedItemPropertyFunction property) {
		return ItemProperties.registerGeneric(id, property);
	}

	default void registerCustomModelData(ItemPropertyFunction property) {
		ItemProperties.registerCustomModelData(property);
	}

	default void register(Item item, ResourceLocation id, ClampedItemPropertyFunction property) {
		ItemProperties.register(item, id, property);
	}

	Event<Callback> EVENT = Event.create(Callback.class, callbacks -> event -> {
		for (Callback callback : callbacks) {
			callback.registerItemProperties(event);
		}
	});

	@FunctionalInterface
	interface Callback {
		void registerItemProperties(RegisterItemPropertiesEvent event);
	}
}
