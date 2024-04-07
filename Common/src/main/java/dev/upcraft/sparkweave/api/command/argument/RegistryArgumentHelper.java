package dev.upcraft.sparkweave.api.command.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class RegistryArgumentHelper {

	private static final DynamicCommandExceptionType REGISTRY_NOT_FOUND = new DynamicCommandExceptionType(x -> Component.translatable("argument.sparkweave.registries.not_found", x));

	public static CompletableFuture<Suggestions> suggestRegistries(CommandContext<CommandSourceStack> ctx, SuggestionsBuilder builder) {
		return SharedSuggestionProvider.suggestResource(ctx.getSource().registryAccess().listRegistries().map(ResourceKey::location), builder);
	}

	public static ArgumentType<ResourceLocation> registryArgument() {
		return ResourceLocationArgument.id();
	}

	public static <T> Registry<T> getRegistry(CommandContext<CommandSourceStack> ctx, String name) throws CommandSyntaxException {
		var location = ctx.getArgument(name, ResourceLocation.class);
		Optional<Registry<T>> optional = ctx.getSource().registryAccess().registry(ResourceKey.createRegistryKey(location));
		return optional.orElseThrow(() -> REGISTRY_NOT_FOUND.create(location));
	}

}
