package dev.upcraft.test.sparkweave.client;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.annotation.Mod;
import dev.upcraft.sparkweave.api.client.Debug;
import dev.upcraft.sparkweave.api.util.Time;
import net.minecraft.client.Minecraft;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;

@CalledByReflection
public class SparkweaveTestModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		ClientTickEvents.START.register(new ClientTickEvents.Start() {
			int ticks = 0;
			@Override
			public void startClientTick(Minecraft client) {
				if (client.level != null && ticks++ % 100 == 0) {
					for (int x = 0; x < 16; x++) {
						for (int z = 0; z < 16; z++) {
							Debug.setColor(50 + x * 10, 0, 135 + z * 8);
							Debug.drawLine(x, 0, z, x, 1, z, Time.toMillis(100));
						}
					}

				}
			}
		});
	}
}
