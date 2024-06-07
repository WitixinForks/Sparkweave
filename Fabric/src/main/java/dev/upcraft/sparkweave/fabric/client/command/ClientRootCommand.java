package dev.upcraft.sparkweave.fabric.client.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import dev.upcraft.sparkweave.fabric.client.consent.ConsentScreen;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class ClientRootCommand {

	public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
		var root = ClientCommandManager.literal("sparkweave");
		DebugMonitorCommand.register(root);

		//TODO remove debug
		root.then(ClientCommandManager.literal("test")
			.executes(ClientRootCommand::openConsentScreen));

		dispatcher.register(root);
	}

	private static int openConsentScreen(CommandContext<FabricClientCommandSource> ctx) {
		List<ResourceLocation> permissions = List.of(
			new ResourceLocation("sparkweave", "test1"),
			new ResourceLocation("sparkweave", "test2"),
			new ResourceLocation("sparkweave", "test3"),
			new ResourceLocation("sparkweave", "test4"),
			new ResourceLocation("sparkweave", "test5")
		);
		System.out.println("opening screen");
		ctx.getSource().getClient().setScreen(new ConsentScreen(permissions, true));

		return Command.SINGLE_SUCCESS;
	}

}
