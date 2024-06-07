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
			ResourceLocation.fromNamespaceAndPath("sparkweave", "test1"),
			ResourceLocation.fromNamespaceAndPath("sparkweave", "test2"),
			ResourceLocation.fromNamespaceAndPath("sparkweave", "test3"),
			ResourceLocation.fromNamespaceAndPath("sparkweave", "test4"),
			ResourceLocation.fromNamespaceAndPath("sparkweave", "test5")
		);
		System.out.println("opening screen");
		ctx.getSource().getClient().setScreen(new ConsentScreen(permissions, true));

		return Command.SINGLE_SUCCESS;
	}

}
