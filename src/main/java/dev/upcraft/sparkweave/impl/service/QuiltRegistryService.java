package dev.upcraft.sparkweave.impl.service;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.registry.RegistryService;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;

@CalledByReflection
public class QuiltRegistryService implements RegistryService {

    @SuppressWarnings("unchecked")
    @Override
    public <T> Registry<T> getRegistry(ResourceKey<Registry<T>> registryResourceKey) {
        return ((Registry<Registry<T>>) BuiltInRegistries.REGISTRY).get(registryResourceKey);
    }
}
