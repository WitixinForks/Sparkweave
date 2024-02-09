package dev.upcraft.sparkweave.quilt.impl.mod;

import dev.upcraft.sparkweave.api.platform.ModMetadata;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.ModContainer;

public class QuiltModMetadata implements ModMetadata {

	private final org.quiltmc.loader.api.ModMetadata delegate;

	public QuiltModMetadata(ModContainer modContainer) {
        delegate = modContainer.metadata();
    }

	@Override
	public String id() {
		return delegate.id();
	}

	@Override
	public @Nullable String issuesUrl() {
		return delegate.getContactInfo("issues");
	}

	@Override
	public @Nullable String sourcesUrl() {
		return delegate.getContactInfo("sources");
	}

	@Override
	public @Nullable String getHomepageUrl() {
		return delegate.getContactInfo("homepage");
	}

	@Override
	public String displayName() {
		return delegate.name();
	}

	@Override
	public String version() {
		return delegate.version().raw();
	}
}
