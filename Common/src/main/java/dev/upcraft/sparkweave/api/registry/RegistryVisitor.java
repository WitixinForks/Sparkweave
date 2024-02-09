package dev.upcraft.sparkweave.api.registry;

import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface RegistryVisitor<T> extends BiConsumer<ResourceLocation, T> {

	@Override
	void accept(ResourceLocation id, T t);
}
