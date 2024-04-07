package dev.upcraft.sparkweave.api.event;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class CommandEvents {

	public static final Event<Register> REGISTER = Event.create(Register.class, listeners -> (dispatcher, buildContext, environment) -> {
		for (Register listener : listeners) {
			listener.registerCommands(dispatcher, buildContext, environment);
		}
	});

	@FunctionalInterface
	public interface Register {

		void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext, Commands.CommandSelection environment);
	}
}
