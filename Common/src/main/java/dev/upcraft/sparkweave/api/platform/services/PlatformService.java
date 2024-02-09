package dev.upcraft.sparkweave.api.platform.services;

import dev.upcraft.sparkweave.api.platform.RuntimeEnvironmentType;
import dev.upcraft.sparkweave.api.platform.ModContainer;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface PlatformService {

	boolean isModLoaded(String modid);

	boolean isDevelopmentEnvironment();

	Path getGameDir();

	Path getConfigDir();

	Path getUserDataDir(@Nullable String subDirectory);

	Optional<ModContainer> getModContainer(String modid);

	RuntimeEnvironmentType getEnvironmentType();

	List<String> getLaunchArguments(boolean hideSensitive);

	default String getEnvString() {
		return !isDevelopmentEnvironment() ? "production" : "development";
	}
}
