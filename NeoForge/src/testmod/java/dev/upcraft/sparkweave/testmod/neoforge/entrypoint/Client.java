package dev.upcraft.sparkweave.testmod.neoforge.entrypoint;

import dev.upcraft.sparkweave.testmod.SparkweaveTestmod;
import dev.upcraft.sparkweave.testmod.client.SparkweaveTestmodClient;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;

@Mod.EventBusSubscriber(modid = SparkweaveTestmod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class Client {

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		if(event.phase == TickEvent.Phase.START) {
			SparkweaveTestmodClient.onClientTickStart(Minecraft.getInstance());
		}
	}
}
