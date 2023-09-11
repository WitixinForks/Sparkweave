package dev.upcraft.sparkweave.impl.service;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistryService;
import dev.upcraft.sparkweave.util.Utils;

@CalledByReflection
public class QuiltRegistryService implements RegistryService {

	@Override
	public <T> void registerAll(RegistryHandler<T> handler) {
		var registry = Utils.getBuiltinRegistry(handler.registry());
		handler.stream().forEach(supplier -> supplier.register(registry));
	}
}
