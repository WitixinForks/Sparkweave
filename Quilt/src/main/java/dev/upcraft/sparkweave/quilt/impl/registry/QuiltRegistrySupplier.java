package dev.upcraft.sparkweave.quilt.impl.registry;

import dev.upcraft.sparkweave.api.registry.RegistryHelper;
import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

public class QuiltRegistrySupplier<T> implements RegistrySupplier<T> {

	private final ResourceKey<? super T> id;
	private final Supplier<T> factory;
	@Nullable
	private T value;
	private Registry<? super T> registry;

	public QuiltRegistrySupplier(ResourceKey<? super T> id, Supplier<T> factory) {
		this.id = id;
		this.factory = factory;
	}

	public void register(Registry<? super T> registry) {
		this.registry = registry;
		this.value = Registry.register(registry, this.getId(), Objects.requireNonNull(this.factory.get(), "factory returned null for " + this.id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get() {
		if (value == null) {
			Registry<Object> reg = (Registry<Object>) getRegistry();
			value = (T) Objects.requireNonNull(reg.get(this.id.location()), "Registry supplier called too early: " + this.getId());
		}
		return value;
	}

	@Override
	public boolean is(T other) {
		return this.get() == other;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean matches(TagKey<? super T> tag) {
        Registry<Object> reg = (Registry<Object>) getRegistry();
		return reg.getHolderOrThrow((ResourceKey<Object>) getRegistryKey()).is((TagKey<Object>) tag);
	}

	@Override
	public ResourceLocation getId() {
		return this.id.location();
	}

	@Override
	public ResourceKey<? super T> getRegistryKey() {
		return this.id;
	}

	@Override
	public Registry<? super T> getRegistry() {
		if(registry == null) {
			registry = RegistryHelper.getBuiltinRegistry(ResourceKey.createRegistryKey(id.registry()));
		}
		return registry;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof QuiltRegistrySupplier<?> other) {
			return this.getRegistryKey() == other.getRegistryKey();
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id.registry(), this.id.location());
	}
}
