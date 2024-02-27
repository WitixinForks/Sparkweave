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

	<T extends ParticleOptions> void registerSpecial(Supplier<ParticleType<T>> type, ParticleProvider<T> provider);

	// copying from ParticleEngine because fabric does not expose this method
	default <T extends ParticleOptions> void registerSprite(Supplier<ParticleType<T>> type, ParticleProvider.Sprite<T> sprite) {
		registerSpriteSet(type, spriteSet -> (t, level, x, y, z, velocityX, velocityY, velocityZ) -> {
			var particle = sprite.createParticle(t, level, x, y, z, velocityX, velocityY, velocityZ);
			if (particle != null) {
				particle.pickSprite(spriteSet);
			}

			return particle;
		});
	}

	<T extends ParticleOptions> void registerSpriteSet(Supplier<ParticleType<T>> type, SpriteParticleRegistration<T> registration);

	@FunctionalInterface
	interface Callback {
		void registerParticleFactories(RegisterParticleFactoriesEvent event);
	}

	interface SpriteParticleRegistration<T extends ParticleOptions> extends ParticleEngine.SpriteParticleRegistration<T> {
	}
}
