package dev.upcraft.sparkweave.quilt.client.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import dev.upcraft.sparkweave.SparkweaveMod;
import dev.upcraft.sparkweave.quilt.client.consent.ConsentScreen;
import net.minecraft.resources.ResourceLocation;
import org.quiltmc.qsl.command.api.client.ClientCommandManager;
import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;

import java.util.List;
import java.util.stream.Stream;

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
		List<ResourceLocation> permissions =
			SparkweaveMod.ids(
				"test1",
				"test2",
				"test3",
				"test4",
				"test5"
			);
		System.out.println("opening screen");
		ctx.getSource().getClient().setScreen(new ConsentScreen(permissions, true));

		return Command.SINGLE_SUCCESS;
	}

}
