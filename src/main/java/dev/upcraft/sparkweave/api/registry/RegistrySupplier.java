package dev.upcraft.sparkweave.api.registry;

import dev.upcraft.sparkweave.util.Utils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

public class RegistrySupplier<T> implements Supplier<T> {

	private final ResourceKey<T> id;
	private final Supplier<T> factory;
	@Nullable
	private T value;

	public RegistrySupplier(ResourceKey<T> id, Supplier<T> factory) {
		this.id = id;
		this.factory = factory;
	}

	public void register(Registry<T> registry) {
		this.value = Registry.register(registry, this.getId(), Objects.requireNonNull(this.factory.get(), "factory returned null for " + this.id));
	}

	@Override
	public T get() {
		if (value == null) {
			ResourceKey<Registry<T>> registryKey = ResourceKey.createRegistryKey(this.id.registry());
			value = Objects.requireNonNull(Utils.getBuiltinRegistry(registryKey).get(this.id.location()), "Registry supplier called too early: " + this.getId());
		}
		return value;
	}

	public boolean is(T other) {
		return this.get() == other;
	}

	public ResourceLocation getId() {
		return this.id.location();
	}

	public ResourceKey<T> getRegistryKey() {
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RegistrySupplier<?>) {
			var otherId = ((RegistrySupplier<?>) obj).getRegistryKey();
			return this.getRegistryKey().registry().equals(otherId.registry()) && this.getRegistryKey().location().equals(otherId.location());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id.registry(), this.id.location());
	}
}
