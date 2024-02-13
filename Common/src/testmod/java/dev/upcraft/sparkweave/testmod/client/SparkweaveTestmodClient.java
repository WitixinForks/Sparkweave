package dev.upcraft.sparkweave.testmod.client;

import dev.upcraft.sparkweave.api.client.Debug;
import dev.upcraft.sparkweave.api.time.Time;
import net.minecraft.client.Minecraft;

public class SparkweaveTestmodClient {

	private static int ticks = 0;

	public static void onClientTickStart(Minecraft client) {
		if (client.level != null && ticks++ % 100 == 0) {
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					Debug.setColor(50 + x * 10, 0, 135 + z * 8);
					Debug.drawLine(x, 0, z, x, 1, z, Time.toMillis(100));
				}
			}
		}
	}
}
