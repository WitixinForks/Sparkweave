package dev.upcraft.sparkweave.util;

import dev.upcraft.sparkweave.api.SparkweaveApi;
import net.minecraft.server.Bootstrap;

import java.util.HashSet;
import java.util.Set;

public class TranslationChecker {

	private static final Set<String> MISSING_KEYS = new HashSet<>();

	public static void notifyMissingTranslation(String translationKey) {
		if(!MISSING_KEYS.contains(translationKey)) {
			if(SparkweaveApi.Client.LOG_MISSING_TRANSLATIONS) {
				SparkweaveLogging.getLogger().warn("Missing translation for key '{}'", translationKey);
			}
			MISSING_KEYS.add(translationKey);
		}
	}

	public static void validate() {
		Bootstrap.getMissingTranslations().forEach(TranslationChecker::notifyMissingTranslation);
	}
}
