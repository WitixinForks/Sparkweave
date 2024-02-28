package dev.upcraft.sparkweave.neoforge.event.client;

import dev.upcraft.sparkweave.SparkweaveMod;
import dev.upcraft.sparkweave.api.client.event.*;
import dev.upcraft.sparkweave.client.event.RegisterItemPropertiesEventImpl;
import dev.upcraft.sparkweave.neoforge.impl.registry.RegisterParticleFactoriesEventImpl;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SparkweaveMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModBusRegistryEvents {

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			RegisterMenuScreensEvent.EVENT.invoker().registerMenuScreens(ClientModBusRegistryEvents::registerMenuScreen);
			RegisterItemPropertiesEvent.EVENT.invoker().registerItemProperties(new RegisterItemPropertiesEventImpl());
		});
	}

	private static <MENU extends AbstractContainerMenu, SCREEN extends Screen & MenuAccess<MENU>> void registerMenuScreen(Supplier<MenuType<MENU>> type, RegisterMenuScreensEvent.ScreenConstructor<MENU, SCREEN> factory) {
		MenuScreens.register(type.get(), factory);
	}

	@SubscribeEvent
	public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
		RegisterBlockEntityRenderersEvent.EVENT.invoker().registerBlockEntityRenderers((RegisterBlockEntityRenderersEvent) event);
		RegisterEntityRenderersEvent.EVENT.invoker().registerEntityRenderers((RegisterEntityRenderersEvent) event);
	}

	@SubscribeEvent
	public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
		RegisterParticleFactoriesEvent.EVENT.invoker().registerParticleFactories(new RegisterParticleFactoriesEventImpl(event));
	}

}
