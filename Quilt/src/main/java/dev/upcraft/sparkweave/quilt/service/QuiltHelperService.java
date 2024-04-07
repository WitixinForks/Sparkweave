package dev.upcraft.sparkweave.quilt.service;

import com.mojang.brigadier.arguments.ArgumentType;
import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.platform.services.SparkweaveHelperService;
import dev.upcraft.sparkweave.quilt.mixin.impl.ArgumentTypeInfosAccessor;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.world.item.CreativeModeTab;

public class QuiltHelperService implements SparkweaveHelperService {

	@CalledByReflection
	public QuiltHelperService() {
		// need an explicit default constructor for the service loader to work
	}

	@Override
	public CreativeModeTab.Builder newCreativeTabBuilder() {
		return FabricItemGroup.builder();
	}

	@Override
	public synchronized <A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>> ArgumentTypeInfo<A, T> create(Class<A> clazz, ArgumentTypeInfo<A, T> info) {
		ArgumentTypeInfosAccessor.sparkweave$getByClass().put(clazz, info);
		return info;
	}
}
