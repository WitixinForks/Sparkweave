package dev.upcraft.sparkweave.api.event;

import net.minecraft.server.MinecraftServer;

public class LifeCycleEvents {

	public static final Event<ServerStarting> SERVER_STARTING = dev.upcraft.sparkweave.api.event.Event.create(ServerStarting.class, listeners -> server -> {
		for (ServerStarting listener : listeners) {
			listener.onServerStarting(server);
		}
	});

	public static final Event<ServerStarted> SERVER_STARTED = Event.create(ServerStarted.class, listeners -> server -> {
		for (ServerStarted listener : listeners) {
			listener.onServerStarted(server);
		}
	});

	@FunctionalInterface
	public interface ServerStarting {
		void onServerStarting(MinecraftServer server);
	}

	@FunctionalInterface
	public interface ServerStarted {
		void onServerStarted(MinecraftServer server);
	}
}
