package dev.upcraft.sparkweave.api.client.event;

import dev.upcraft.sparkweave.api.event.Event;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;

import java.util.function.Supplier;

public interface RegisterParticleFactoriesEvent {

	Event<Callback> EVENT = Event.create(Callback.class, callbacks -> event -> {
		for (Callback callback : callbacks) {
			callback.registerParticleFactories(event);
		}
	});

	<OPT extends ParticleOptions, TYPE extends ParticleType<OPT>> void registerSpecial(Supplier<TYPE> type, ParticleProvider<OPT> provider);

	// copying from ParticleEngine because fabric does not expose this method
	default <OPT extends ParticleOptions, TYPE extends ParticleType<OPT>> void registerSprite(Supplier<TYPE> type, ParticleProvider.Sprite<OPT> sprite) {
		registerSpriteSet(type, spriteSet -> (t, level, x, y, z, velocityX, velocityY, velocityZ) -> {
			var particle = sprite.createParticle(t, level, x, y, z, velocityX, velocityY, velocityZ);
			if (particle != null) {
				particle.pickSprite(spriteSet);
			}

			return particle;
		});
	}

	<OPT extends ParticleOptions, TYPE extends ParticleType<OPT>> void registerSpriteSet(Supplier<TYPE> type, SpriteParticleRegistration<OPT> registration);

	@FunctionalInterface
	interface Callback {
		void registerParticleFactories(RegisterParticleFactoriesEvent event);
	}

	interface SpriteParticleRegistration<T extends ParticleOptions> extends ParticleEngine.SpriteParticleRegistration<T> {
	}
}
