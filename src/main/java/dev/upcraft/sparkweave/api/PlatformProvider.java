package dev.upcraft.sparkweave.api;

import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

public interface PlatformProvider {

	boolean isModLoaded(String modid);

	boolean isDevelopmentEnvironment();

	Path getGameDir();

	Path getConfigDir();

	Path getUserDataDir(@Nullable String subDirectory);
}
