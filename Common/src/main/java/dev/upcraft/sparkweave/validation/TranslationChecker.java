package dev.upcraft.sparkweave.validation;

import dev.upcraft.sparkweave.api.SparkweaveApi;
import dev.upcraft.sparkweave.logging.SparkweaveLogging;
import net.minecraft.server.Bootstrap;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TranslationChecker {

	private static final Set<String> MISSING_KEYS = new HashSet<>();
	private static final Set<String> MISSING_KEYS_VIEW = Collections.unmodifiableSet(MISSING_KEYS);

	private static void notifyMissingTranslation(String translationKey) {
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

	public static Set<String> getMissingKeys() {
		return MISSING_KEYS_VIEW;
	}
}
