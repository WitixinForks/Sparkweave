package dev.upcraft.sparkweave.api.platform;

import org.jetbrains.annotations.Nullable;

public interface ModMetadata {

	String id();

	@Nullable
	String issuesUrl();

	@Nullable
	String sourcesUrl();

	@Nullable
	String getHomepageUrl();

	String displayName();

    String version();
}
