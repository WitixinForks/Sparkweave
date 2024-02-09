package dev.upcraft.sparkweave.neoforge.mixin.internal;

import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DeferredHolder.class)
public abstract class DeferredHolderMixin<R, T extends R> implements RegistrySupplier<T> {

	@Invoker("is")
	@Override
	public abstract boolean matches(TagKey<? super T> tag);

	@Invoker("getKey")
	@Override
	public abstract ResourceKey<? super T> getRegistryKey();
}
