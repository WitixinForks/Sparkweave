package dev.upcraft.sparkweave.neoforge.service;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.platform.services.RegistryService;
import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistryVisitor;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.commons.lang3.NotImplementedException;

public class NeoRegistryService implements RegistryService {

	@CalledByReflection
	public NeoRegistryService() {
		// need an explicit default constructor for the service loader to work
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> RegistryHandler<T> createRegistryHandler(ResourceKey<Registry<T>> registryKey, String namespace) {
		return (RegistryHandler<T>) DeferredRegister.create(registryKey, namespace);
	}

	@Override
	public <T> void visitRegistry(Registry<T> registry, RegistryVisitor<T> callback) {
		throw new NotImplementedException();
	}

	@Override
	public <T> void handleRegister(RegistryHandler<T> handler) {
		if (handler instanceof DeferredRegister<?> deferredRegister) {
			deferredRegister.register(FMLJavaModLoadingContext.get().getModEventBus());
		}
	}
}
