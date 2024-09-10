package dev.upcraft.sparkweave.api;

import dev.upcraft.sparkweave.api.platform.Env;
import dev.upcraft.sparkweave.api.platform.RuntimeEnvironmentType;
import dev.upcraft.sparkweave.api.platform.Services;
import org.apache.logging.log4j.Level;

public class SparkweaveApi {

	public static final boolean DEVELOPMENT_ENVIRONMENT = Services.PLATFORM.isDevelopmentEnvironment();

	public static final boolean CLIENTSIDE_ENVIRONMENT = Services.PLATFORM.getEnvironmentType().isClientSide();

	public static final boolean DEBUG_MODE = Env.getBool("debug");

	public static final boolean DEBUG_LOGGING = DEBUG_MODE || Env.getBool("debug.logging");

	public static final Level DEBUG_LOG_LEVEL = Level.toLevel(Env.get("debug.logging.level"), DEVELOPMENT_ENVIRONMENT ? Level.ALL : Level.DEBUG);

	public static class Client {

		static {
			RuntimeEnvironmentType.CLIENT.orElseThrow();
		}

		public static final boolean LOAD_RENDERDOC = Env.getBool("debug.render.load_renderdoc");

		public static final boolean LOG_MISSING_TRANSLATIONS = DEVELOPMENT_ENVIRONMENT || Env.getBool("debug.log.missing_translations");

		public static final boolean RENDER_SLOT_NUMBERS = DEBUG_MODE || Env.getBool("debug.render.slotnumber");
	}
}
