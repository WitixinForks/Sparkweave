package dev.upcraft.sparkweave.api.platform.services;

import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

public interface SparkweaveHelperService {

	/**
	 * @deprecated use {@link SparkweaveHelperService#newCreativeTabBuilder(Component)}
	 */
	@Deprecated(forRemoval = true, since = "0.502.0-alpha.1")
	default CreativeModeTab.Builder newCreativeTabBuilder() {
		return newCreativeTabBuilder(Component.literal("SPARKWEAVE: please use the creative tab builder that takes a title component!"));
	}

	/**
	 * @see dev.upcraft.sparkweave.api.item.CreativeTabHelper
	 */
	CreativeModeTab.Builder newCreativeTabBuilder(Component title);

	/**
	 * @see dev.upcraft.sparkweave.api.command.CommandHelper#createArgumentInfo(Class, ArgumentTypeInfo)
	 */
	<A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>> ArgumentTypeInfo<A, T> create(Class<A> clazz, ArgumentTypeInfo<A, T> info);
}
