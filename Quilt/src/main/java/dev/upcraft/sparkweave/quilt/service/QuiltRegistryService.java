package dev.upcraft.sparkweave.quilt.service;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.platform.services.RegistryService;
import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistryVisitor;
import dev.upcraft.sparkweave.quilt.impl.registry.QuiltRegistryHandler;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.quiltmc.qsl.registry.api.event.RegistryEvents;

import java.util.Map;
import java.util.stream.Collectors;

public class QuiltRegistryService implements RegistryService {

	@CalledByReflection
	public QuiltRegistryService() {
		// need an explicit default constructor for the service loader to work
	}

	@Override
	public <T> RegistryHandler<T> createRegistryHandler(ResourceKey<Registry<T>> registryKey, String namespace) {
		return QuiltRegistryHandler.create(registryKey, namespace);
	}

	@Override
	public <T> void visitRegistry(Registry<T> registry, RegistryVisitor<T> callback) {
		RegistryEvents.getEntryAddEvent(registry).register(context -> callback.accept(context.id(), context.value()));
		// need to create a copy because the callback itself may add entries to the registry while we are iterating it
		var copy = registry.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().location(), Map.Entry::getValue));
		copy.forEach(callback);
	}

	@Override
	public <T> void handleRegister(RegistryHandler<T> handler) {
		// NO-OP because registration happens anyway, we just need to load the class
	}
}
