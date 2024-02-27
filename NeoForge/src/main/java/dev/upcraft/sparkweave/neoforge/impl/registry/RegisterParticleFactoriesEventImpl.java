package dev.upcraft.sparkweave.neoforge.impl.registry;

import dev.upcraft.sparkweave.api.client.event.RegisterParticleFactoriesEvent;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import java.util.function.Supplier;

public record RegisterParticleFactoriesEventImpl(
	RegisterParticleProvidersEvent event) implements RegisterParticleFactoriesEvent {

	@Override
	public <T extends ParticleOptions> void registerSpecial(Supplier<ParticleType<T>> type, ParticleProvider<T> provider) {
		event.registerSpecial(type.get(), provider);
	}

	@Override
	public <T extends ParticleOptions> void registerSpriteSet(Supplier<ParticleType<T>> type, ParticleEngine.SpriteParticleRegistration<T> registration) {
		event.registerSpriteSet(type.get(), registration);
	}

	@Override
	public <T extends ParticleOptions> void registerSprite(Supplier<ParticleType<T>> type, ParticleProvider.Sprite<T> sprite) {
		event.registerSprite(type.get(), sprite);
	}
}
