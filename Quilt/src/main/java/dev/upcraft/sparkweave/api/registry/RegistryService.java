package dev.upcraft.sparkweave.api.registry;

import dev.upcraft.sparkweave.api.Platform;

public interface RegistryService {

    static RegistryService get() {
        return Platform.getService(RegistryService.class);
    }

	<T> void registerAll(RegistryHandler<T> handler);
}
