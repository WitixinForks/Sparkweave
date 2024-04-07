package dev.upcraft.sparkweave.api.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import dev.upcraft.sparkweave.api.platform.Services;
import dev.upcraft.sparkweave.api.platform.services.SparkweaveHelperService;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.network.chat.Component;

public class CommandHelper {

	private static final SparkweaveHelperService HELPER = Services.getService(SparkweaveHelperService.class);

	public static final DynamicCommandExceptionType IO_EXCEPTION = new DynamicCommandExceptionType(ex -> Component.translatable("commands.sparkweave.error.io_exception", ex instanceof Throwable t ? t.getMessage() : ex));

	public static <A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>> ArgumentTypeInfo<A, T> createArgumentInfo(Class<A> clazz, ArgumentTypeInfo<A, T> info) {
		return HELPER.create(clazz, info);
	}
}
