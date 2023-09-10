package dev.upcraft.sparkweave.api;

import java.nio.file.Path;
import java.util.ServiceLoader;

public class Platform {

	private static final PlatformProvider INSTANCE = getService(PlatformProvider.class);

	public static <T> T getService(Class<T> serviceClass) {
		return ServiceLoader.load(serviceClass).findFirst().orElseThrow(() -> new IllegalStateException("No platform implementation found for " + serviceClass.getCanonicalName()));
	}

	//--------------------------------------

	public static boolean DEVELOPMENT_ENVIRONMENT = INSTANCE.isDevelopmentEnvironment();

	public static Path getGameDir() {
		return INSTANCE.getGameDir();
	}

	public static Path getConfigDir() {
		return INSTANCE.getConfigDir();
	}

	public static Path getUserDataDir(String subDirectory) {
		return INSTANCE.getUserDataDir(subDirectory);
	}

	public static boolean isModLoaded(String modid) {
		return INSTANCE.isModLoaded(modid);
	}
}
