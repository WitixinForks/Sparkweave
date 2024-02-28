package dev.upcraft.sparkweave.neoforge.impl.registry;

import dev.upcraft.sparkweave.api.client.event.RegisterParticleFactoriesEvent;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import java.util.function.Supplier;

public record RegisterParticleFactoriesEventImpl(
	RegisterParticleProvidersEvent event) implements RegisterParticleFactoriesEvent {

	@Override
	public <OPT extends ParticleOptions, TYPE extends ParticleType<OPT>> void registerSpecial(Supplier<TYPE> type, ParticleProvider<OPT> provider) {
		event.registerSpecial(type.get(), provider);
	}

	@Override
	public <OPT extends ParticleOptions, TYPE extends ParticleType<OPT>> void registerSpriteSet(Supplier<TYPE> type, SpriteParticleRegistration<OPT> registration) {
		event.registerSpriteSet(type.get(), registration);
	}

	@Override
	public <OPT extends ParticleOptions, TYPE extends ParticleType<OPT>> void registerSprite(Supplier<TYPE> type, ParticleProvider.Sprite<OPT> sprite) {
		event.registerSprite(type.get(), sprite);
	}
}
