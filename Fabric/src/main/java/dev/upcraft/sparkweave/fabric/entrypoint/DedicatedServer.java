package dev.upcraft.sparkweave.fabric.entrypoint;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.entrypoint.DedicatedServerEntryPoint;
import dev.upcraft.sparkweave.entrypoint.EntrypointHelper;
import net.fabricmc.api.DedicatedServerModInitializer;

@CalledByReflection
public class DedicatedServer implements DedicatedServerModInitializer {

	@Override
	public void onInitializeServer() {
		EntrypointHelper.fireEntrypoints(DedicatedServerEntryPoint.class, DedicatedServerEntryPoint::onInitializeServer);
	}
}
