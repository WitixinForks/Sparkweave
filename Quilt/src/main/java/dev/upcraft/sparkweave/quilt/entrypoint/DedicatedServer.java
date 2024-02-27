package dev.upcraft.sparkweave.quilt.entrypoint;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.entrypoint.DedicatedServerEntryPoint;
import dev.upcraft.sparkweave.entrypoint.EntrypointHelper;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.server.DedicatedServerModInitializer;

@CalledByReflection
public class DedicatedServer implements DedicatedServerModInitializer {

	@Override
	public void onInitializeServer(ModContainer mod) {
		EntrypointHelper.fireEntrypoints(DedicatedServerEntryPoint.class, DedicatedServerEntryPoint::onInitializeServer);
	}
}
