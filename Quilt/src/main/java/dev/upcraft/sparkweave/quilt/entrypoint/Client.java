package dev.upcraft.sparkweave.quilt.entrypoint;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.client.event.*;
import dev.upcraft.sparkweave.api.client.render.DebugRenderer;
import dev.upcraft.sparkweave.api.entrypoint.ClientEntryPoint;
import dev.upcraft.sparkweave.client.event.RegisterItemPropertiesEventImpl;
import dev.upcraft.sparkweave.entrypoint.EntrypointHelper;
import dev.upcraft.sparkweave.quilt.client.command.ClientRootCommand;
import dev.upcraft.sparkweave.quilt.impl.registry.RegisterBlockEntityRenderersEventImpl;
import dev.upcraft.sparkweave.quilt.impl.registry.RegisterEntityRenderersEventImpl;
import dev.upcraft.sparkweave.quilt.impl.registry.RegisterMenuScreensEventImpl;
import dev.upcraft.sparkweave.quilt.impl.registry.RegisterParticleFactoriesEventImpl;
import dev.upcraft.sparkweave.validation.TranslationChecker;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.command.api.client.ClientCommandRegistrationCallback;
import org.quiltmc.qsl.resource.loader.api.client.ClientResourceLoaderEvents;

@ClientOnly
@CalledByReflection
public class Client implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		WorldRenderEvents.AFTER_ENTITIES.register((context) -> DebugRenderer.render(context.matrixStack(), context.consumers(), context.camera()));
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, buildContext, environment) -> {
			if(environment.isIntegrated()) {
				ClientRootCommand.register(dispatcher);
			}
		});
		ClientResourceLoaderEvents.END_PACK_RELOAD.register(context -> TranslationChecker.validate());

		EntrypointHelper.fireEntrypoints(ClientEntryPoint.class, ClientEntryPoint::onInitializeClient);

		RegisterBlockEntityRenderersEvent.EVENT.invoker().registerBlockEntityRenderers(new RegisterBlockEntityRenderersEventImpl());
		RegisterEntityRenderersEvent.EVENT.invoker().registerEntityRenderers(new RegisterEntityRenderersEventImpl());
		RegisterItemPropertiesEvent.EVENT.invoker().registerItemProperties(new RegisterItemPropertiesEventImpl());
		RegisterMenuScreensEvent.EVENT.invoker().registerMenuScreens(new RegisterMenuScreensEventImpl());
		RegisterParticleFactoriesEvent.EVENT.invoker().registerParticleFactories(new RegisterParticleFactoriesEventImpl());

//		//TODO debug
//		var kb = new KeyMapping("key.sparkweave.debug", GLFW.GLFW_KEY_J, "key.categories.misc");
//		ClientTickEvents.START.register(client -> {
//			if(client.level != null) {
//				if(kb.consumeClick()) {
//					List<ResourceLocation> permissions = List.of(
//						new ResourceLocation("sparkweave", "test1"),
//						new ResourceLocation("sparkweave", "test2"),
//						new ResourceLocation("sparkweave", "test3"),
//						new ResourceLocation("sparkweave", "test4"),
//						new ResourceLocation("sparkweave", "test5")
//					);
//					client.setScreen(new ConsentScreen(permissions, true));
//				}
//			}
//		});
	}
}
