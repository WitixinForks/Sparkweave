package dev.upcraft.sparkweave.api;

import dev.upcraft.sparkweave.api.util.Env;
import net.fabricmc.api.EnvType;
import org.apache.logging.log4j.Level;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.loader.api.minecraft.MinecraftQuiltLoader;

public class SparkweaveApi {

	public static final boolean DEVELOPMENT_ENVIRONMENT = QuiltLoader.isDevelopmentEnvironment();

	public static final boolean CLIENTSIDE_ENVIRONMENT = MinecraftQuiltLoader.getEnvironmentType() == EnvType.CLIENT;

	public static final boolean DEBUG_MODE = Env.getBool("debug");

	public static final boolean DEBUG_LOGGING = DEBUG_MODE || Env.getBool("debug.logging");

	public static final Level DEBUG_LOG_LEVEL = Level.toLevel(Env.get("debug.logging.level"), DEVELOPMENT_ENVIRONMENT ? Level.ALL : Level.DEBUG);

	@ClientOnly
	public static class Client {

		public static final boolean RENDER_SLOT_NUMBERS = DEBUG_MODE || Env.getBool("debug.render.slotnumber");

		public static final boolean LOG_MISSING_TRANSLATIONS = DEVELOPMENT_ENVIRONMENT || Env.getBool("debug.log.missing_translations");
	}
}
