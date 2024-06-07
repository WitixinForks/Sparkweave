package dev.upcraft.sparkweave.fabric.client.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class DebugMonitorCommand {

	public static void register(LiteralArgumentBuilder<FabricClientCommandSource> root) {
		root.then(ClientCommandManager.literal("debug").then(ClientCommandManager.literal("monitor").executes(context -> {
			return 0;
		})));
	}
}
