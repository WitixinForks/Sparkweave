package dev.upcraft.sparkweave.api.registry;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class RegistryHandler<T> implements Consumer<RegistryService> {

	private final ResourceKey<Registry<T>> registryKey;
	private final String modid;

	private final Map<ResourceLocation, RegistrySupplier<T>> values = new Object2ObjectOpenHashMap<>();
	private final List<RegistrySupplier<T>> orderedEntries = new LinkedList<>();

	public RegistryHandler(ResourceKey<Registry<T>> registryKey, String modid) {
		this.registryKey = registryKey;
		this.modid = modid;
	}

	public static <T> RegistryHandler<T> create(ResourceKey<Registry<T>> registryKey, String modid) {
		return new RegistryHandler<>(registryKey, modid);
	}

	@SuppressWarnings("unchecked")
	public <S extends T> RegistrySupplier<S> register(String name, Supplier<S> factory) {
		var id = new ResourceLocation(modid, name);
		var supplier = new RegistrySupplier<>(ResourceKey.create(registryKey, id), (Supplier<T>) factory);
		values.put(id, supplier);
		orderedEntries.add(supplier);
		return (RegistrySupplier<S>) supplier;
	}

	@Override
	public void accept(RegistryService registryService) {
		registryService.registerAll(this);
	}

	public Map<ResourceLocation, RegistrySupplier<T>> values() {
		return Collections.unmodifiableMap(values);
	}

	public List<RegistrySupplier<T>> getEntriesOrdered() {
		return Collections.unmodifiableList(orderedEntries);
	}

	public Stream<RegistrySupplier<T>> stream() {
		return orderedEntries.stream();
	}

	public ResourceKey<Registry<T>> registry() {
		return registryKey;
	}
}
