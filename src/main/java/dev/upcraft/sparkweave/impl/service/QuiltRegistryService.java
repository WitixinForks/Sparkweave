package dev.upcraft.sparkweave.impl.service;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistryService;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;

import java.util.Objects;

@CalledByReflection
public class QuiltRegistryService implements RegistryService {

    @SuppressWarnings("unchecked")
    private static <T> Registry<T> getRegistry(ResourceKey<Registry<T>> registryKey) {
        return Objects.requireNonNull(((Registry<Registry<T>>) BuiltInRegistries.REGISTRY).get(registryKey), "unable to resolve registry" + registryKey);
    }

	@Override
	public <T> void registerAll(RegistryHandler<T> handler) {
		var registry = getRegistry(handler.registry());
		handler.stream().forEach(supplier -> supplier.register(registry));
	}
}
