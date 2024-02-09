package dev.upcraft.sparkweave.quilt.client.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import org.quiltmc.qsl.command.api.client.ClientCommandManager;
import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;

public class DebugMonitorCommand {

	public static void register(LiteralArgumentBuilder<QuiltClientCommandSource> root) {
		root.then(ClientCommandManager.literal("debug").then(ClientCommandManager.literal("monitor").executes(context -> {
			return 0;
		})));
	}
}
