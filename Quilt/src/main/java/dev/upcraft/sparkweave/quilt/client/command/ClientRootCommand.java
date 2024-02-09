package dev.upcraft.sparkweave.quilt.client.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import dev.upcraft.sparkweave.quilt.client.consent.ConsentScreen;
import net.minecraft.resources.ResourceLocation;
import org.quiltmc.qsl.command.api.client.ClientCommandManager;
import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;

import java.util.List;

public class ClientRootCommand {

	public static void register(CommandDispatcher<QuiltClientCommandSource> dispatcher) {
		var root = ClientCommandManager.literal("sparkweave");
		DebugMonitorCommand.register(root);

		//TODO remove debug
		root.then(ClientCommandManager.literal("test")
			.executes(ClientRootCommand::openConsentScreen));

		dispatcher.register(root);
	}

	private static int openConsentScreen(CommandContext<QuiltClientCommandSource> ctx) {
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
