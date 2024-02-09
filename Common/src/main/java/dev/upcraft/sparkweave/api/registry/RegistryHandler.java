package dev.upcraft.sparkweave.api.registry;

import dev.upcraft.sparkweave.api.platform.services.RegistryService;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface RegistryHandler<T> {

	static <T> RegistryHandler<T> create(ResourceKey<Registry<T>> registryKey, String namespace) {
		return RegistryService.get().createRegistryHandler(registryKey, namespace);
	}

	<S extends T> RegistrySupplier<S> register(String name, Supplier<S> factory);

	Map<ResourceLocation, RegistrySupplier<? extends T>> values();

	List<RegistrySupplier<? extends T>> getEntriesOrdered();

	Stream<RegistrySupplier<? extends T>> stream();

	ResourceKey<Registry<T>> registry();
}
