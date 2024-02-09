package dev.upcraft.sparkweave.api.logging;

import dev.upcraft.sparkweave.api.annotation.CallerSensitive;
import dev.upcraft.sparkweave.api.reflect.ContextHelper;
import dev.upcraft.sparkweave.api.platform.ModContainer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

public class SparkweaveLoggerFactory {

	private static final LoggerContext ctx = Configurator.initialize(SparkweaveLoggerFactory.class.getClassLoader(), ConfigurationSource.fromResource("/assets/sparkweave/log4j2.xml", SparkweaveLoggerFactory.class.getClassLoader()));

	private SparkweaveLoggerFactory() {
	}

	public static Logger getLogger(String name) {
		return ctx.getLogger(name);
	}

	public static Logger getLogger(ModContainer mod) {
		var name = mod.metadata().displayName();
		return getLogger(!name.isBlank() ? name : mod.metadata().id());
	}

	@CallerSensitive
	public static Logger getLogger() {
		return getLogger(ContextHelper.getCallerContext());
	}



}
