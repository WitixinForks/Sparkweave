package dev.upcraft.sparkweave.api.registry;

import dev.upcraft.sparkweave.api.Platform;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public interface RegistryService {

    static RegistryService get() {
        return Platform.getService(RegistryService.class);
    }

    <T> Registry<T> getRegistry(ResourceKey<Registry<T>> registryResourceKey);
}
