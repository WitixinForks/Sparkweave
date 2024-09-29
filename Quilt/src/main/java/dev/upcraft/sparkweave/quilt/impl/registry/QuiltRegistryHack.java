package dev.upcraft.sparkweave.quilt.impl.registry;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;

import java.util.function.Supplier;

public interface QuiltRegistryHack<R> {

	Holder.Reference<R> sparkweave$createHolder(ResourceKey<R> key, Supplier<R> factory);
}
