package dev.upcraft.sparkweave.quilt.service;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.platform.ModContainer;
import dev.upcraft.sparkweave.api.platform.RuntimeEnvironmentType;
import dev.upcraft.sparkweave.api.platform.services.PlatformService;
import dev.upcraft.sparkweave.quilt.impl.mod.QuiltModContainer;
import dev.upcraft.sparkweave.platform.BasePlatformService;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.loader.api.minecraft.MinecraftQuiltLoader;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CalledByReflection
public class QuiltPlatformService extends BasePlatformService implements PlatformService {

	private final Map<String, Optional<ModContainer>> MOD_CONTAINERS = new Object2ObjectOpenHashMap<>();
	private final RuntimeEnvironmentType environmentType = switch (MinecraftQuiltLoader.getEnvironmentType()) {
		case CLIENT -> RuntimeEnvironmentType.CLIENT;
		case SERVER -> RuntimeEnvironmentType.SERVER;
	};

	// need an explicit default constructor for the service loader to work
	public QuiltPlatformService() {
		super();
	}

	@Override
	public boolean isModLoaded(String modid) {
		return QuiltLoader.isModLoaded(modid);
	}

	@Override
	public boolean isDevelopmentEnvironment() {
		return QuiltLoader.isDevelopmentEnvironment();
	}

	@Override
	public Path getGameDir() {
		return QuiltLoader.getGameDir();
	}

	@Override
	public Path getConfigDir() {
		return QuiltLoader.getConfigDir();
	}

	@Override
	public Optional<ModContainer> getModContainer(String modid) {
		return MOD_CONTAINERS.computeIfAbsent(modid, key -> QuiltLoader.getModContainer(key).map(QuiltModContainer::of));
	}

	@Override
	public RuntimeEnvironmentType getEnvironmentType() {
		return environmentType;
	}

	@Override
	public List<String> getLaunchArguments(boolean hideSensitive) {
		return List.of(QuiltLoader.getLaunchArguments(hideSensitive));
	}

}
