package dev.upcraft.sparkweave.fabric.impl.registry;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;

import java.util.function.Supplier;

public interface FabricRegistryHack<R> {

	Holder.Reference<R> sparkweave$createHolder(ResourceKey<R> key, Supplier<R> factory);
}
