package dev.upcraft.sparkweave.api.registry;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class RegistryVisitor {

	public static <T> void visitRegistry(Registry<T> registry, BiConsumer<ResourceLocation, T> callback) {
		RegistryEntryAddedCallback.event(registry).register((rawId, id, object) -> callback.accept(id, object));
		// need to create a copy because the callback itself may add entries to the registry while we are iterating it
		var copy = registry.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().location(), Map.Entry::getValue));
		copy.forEach(callback);
	}
}
