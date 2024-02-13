package dev.upcraft.sparkweave.api.platform.services;

import dev.upcraft.sparkweave.api.platform.Services;
import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistryVisitor;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.ApiStatus;

public interface RegistryService {

	@ApiStatus.Internal
	RegistryService INSTANCE = Services.getService(RegistryService.class);

    static RegistryService get() {
        return INSTANCE;
    }

	<T> RegistryHandler<T> createRegistryHandler(ResourceKey<Registry<T>> registryKey, String namespace);

	<T> void visitRegistry(Registry<T> registry, RegistryVisitor<T> callback);

	@ApiStatus.Internal
	<T> void handleRegister(RegistryHandler<T> handler);
}
