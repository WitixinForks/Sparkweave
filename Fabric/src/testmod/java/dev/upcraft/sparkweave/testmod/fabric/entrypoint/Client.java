package dev.upcraft.sparkweave.testmod.fabric.entrypoint;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.testmod.client.SparkweaveTestmodClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@CalledByReflection
public class Client implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ClientTickEvents.START_CLIENT_TICK.register(SparkweaveTestmodClient::onClientTickStart);
	}
}
