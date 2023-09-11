package dev.upcraft.sparkweave.api.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.quiltmc.qsl.registry.api.event.RegistryEvents;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class RegistryVisitor {

	public static <T> void visitRegistry(Registry<T> registry, Visitor<T> callback) {
		RegistryEvents.getEntryAddEvent(registry).register(context -> callback.accept(context.id(), context.value()));
		// need to create a copy because the callback itself may add entries to the registry while we are iterating it
		var copy = registry.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().location(), Map.Entry::getValue));
		copy.forEach(callback);
	}

	@FunctionalInterface
	public interface Visitor<T> extends BiConsumer<ResourceLocation, T> {

		@Override
		void accept(ResourceLocation id, T t);
	}
}
