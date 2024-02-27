package dev.upcraft.sparkweave.quilt.impl.registry;

import dev.upcraft.sparkweave.api.client.event.RegisterParticleFactoriesEvent;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;

import java.util.function.Supplier;

public class RegisterParticleFactoriesEventImpl implements RegisterParticleFactoriesEvent {

	@Override
	public <T extends ParticleOptions> void registerSpecial(Supplier<ParticleType<T>> type, ParticleProvider<T> provider) {
		ParticleFactoryRegistry.getInstance().register(type.get(), provider);
	}

	@Override
	public <T extends ParticleOptions> void registerSpriteSet(Supplier<ParticleType<T>> type, SpriteParticleRegistration<T> registration) {
		ParticleFactoryRegistry.getInstance().register(type.get(), registration::create);
	}
}
