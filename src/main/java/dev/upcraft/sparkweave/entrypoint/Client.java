package dev.upcraft.sparkweave.entrypoint;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.annotation.Mod;
import dev.upcraft.sparkweave.impl.client.command.ClientRootCommand;
import dev.upcraft.sparkweave.impl.client.render.DebugRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.command.api.client.ClientCommandRegistrationCallback;

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
