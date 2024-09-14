package dev.upcraft.sparkweave.testmod.init;

import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import dev.upcraft.sparkweave.testmod.SparkweaveTestmod;
import dev.upcraft.sparkweave.testmod.effect.ExperiencedStatusEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class TestStatusEffects {

	public static final RegistryHandler<MobEffect> STATUS_EFFECTS = RegistryHandler.create(Registries.MOB_EFFECT, SparkweaveTestmod.MODID);

	public static final RegistrySupplier<MobEffect> EXPERIENCED = STATUS_EFFECTS.register("experienced", () -> new ExperiencedStatusEffect(MobEffectCategory.BENEFICIAL, 0xFF005A));
}
