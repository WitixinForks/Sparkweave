package dev.upcraft.sparkweave.neoforge.service;

import com.mojang.brigadier.arguments.ArgumentType;
import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.platform.services.SparkweaveHelperService;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.world.item.CreativeModeTab;

public class NeoHelperService implements SparkweaveHelperService {

	@CalledByReflection
	public NeoHelperService() {

	}

	@Override
	public CreativeModeTab.Builder newCreativeTabBuilder() {
		return CreativeModeTab.builder();
	}

	@Override
	public <A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>> ArgumentTypeInfo<A, T> create(Class<A> clazz, ArgumentTypeInfo<A, T> info) {
		return ArgumentTypeInfos.registerByClass(clazz, info);
	}
}
