package dev.upcraft.sparkweave.fabric.impl.registry;

import dev.upcraft.sparkweave.api.client.event.RegisterParticleFactoriesEvent;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;

import java.util.function.Supplier;

public class RegisterParticleFactoriesEventImpl implements RegisterParticleFactoriesEvent {

	@Override
	public <OPT extends ParticleOptions, TYPE extends ParticleType<OPT>> void registerSpecial(Supplier<TYPE> type, ParticleProvider<OPT> provider) {
		ParticleFactoryRegistry.getInstance().register(type.get(), provider);
	}

	@Override
	public <OPT extends ParticleOptions, TYPE extends ParticleType<OPT>> void registerSpriteSet(Supplier<TYPE> type, SpriteParticleRegistration<OPT> registration) {
		ParticleFactoryRegistry.getInstance().register(type.get(), registration::create);
	}
}
