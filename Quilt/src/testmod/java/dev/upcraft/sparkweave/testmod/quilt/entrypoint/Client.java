package dev.upcraft.sparkweave.testmod.quilt.entrypoint;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.testmod.client.SparkweaveTestmodClient;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;

@CalledByReflection
public class Client implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {
		ClientTickEvents.START.register(SparkweaveTestmodClient::onClientTickStart);
	}
}
