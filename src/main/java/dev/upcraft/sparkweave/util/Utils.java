package dev.upcraft.sparkweave.util;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;

import java.util.Objects;

public class Utils {

	@SuppressWarnings("unchecked")
	public static <T> Registry<T> getBuiltinRegistry(ResourceKey<Registry<T>> registryKey) {
		return Objects.requireNonNull(((Registry<Registry<T>>) BuiltInRegistries.REGISTRY).get(registryKey), "unable to resolve registry" + registryKey);
	}
}
