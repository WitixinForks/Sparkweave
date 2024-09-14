package dev.upcraft.sparkweave.testmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

// TODO make this randomly give experience to all entities nearby
public class ExperiencedStatusEffect extends MobEffect {

	public ExperiencedStatusEffect(MobEffectCategory category, int particleColor) {
		super(category, particleColor);
	}
}
