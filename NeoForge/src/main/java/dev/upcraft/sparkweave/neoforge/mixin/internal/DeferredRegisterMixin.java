package dev.upcraft.sparkweave.neoforge.mixin.internal;

import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("AddedMixinMembersNamePattern")
@Mixin(DeferredRegister.class)
public abstract class DeferredRegisterMixin<T> implements RegistryHandler<T> {

	@Shadow
	public abstract Collection<DeferredHolder<T, ? extends T>> getEntries();

	@SuppressWarnings("unchecked")
	@Override
	public Map<ResourceLocation, RegistrySupplier<? extends T>> values() {
		return (Map<ResourceLocation, RegistrySupplier<? extends T>>) (Object) this.getEntries().stream().collect(Collectors.toMap(DeferredHolder::getId, UnaryOperator.identity()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistrySupplier<? extends T>> getEntriesOrdered() {
		return (List<RegistrySupplier<? extends T>>) (Object) this.getEntries().stream().sorted(Comparator.comparing(DeferredHolder::getKey, Comparator.naturalOrder())).toList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Stream<RegistrySupplier<? extends T>> stream() {
		return (Stream<RegistrySupplier<? extends T>>) (Object) this.getEntries().stream();
	}

	@Invoker("getRegistryKey")
	@Override
	public abstract ResourceKey<Registry<T>> registry();
}
