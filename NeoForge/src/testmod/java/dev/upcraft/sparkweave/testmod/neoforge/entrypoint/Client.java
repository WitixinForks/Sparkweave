package dev.upcraft.sparkweave.testmod.neoforge.entrypoint;

import dev.upcraft.sparkweave.testmod.SparkweaveTestmod;
import dev.upcraft.sparkweave.testmod.client.SparkweaveTestmodClient;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = SparkweaveTestmod.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class Client {

	@SubscribeEvent
	public static void onClientTick(ClientTickEvent.Pre event) {
		SparkweaveTestmodClient.onClientTickStart(Minecraft.getInstance());
	}
}
