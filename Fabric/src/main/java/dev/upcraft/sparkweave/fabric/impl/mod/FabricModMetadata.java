package dev.upcraft.sparkweave.fabric.impl.mod;

import dev.upcraft.sparkweave.api.platform.ModMetadata;
import net.fabricmc.loader.api.ModContainer;
import org.jetbrains.annotations.Nullable;

public class FabricModMetadata implements ModMetadata {

	private final net.fabricmc.loader.api.metadata.ModMetadata delegate;

	public FabricModMetadata(ModContainer modContainer) {
        delegate = modContainer.getMetadata();
    }

	@Override
	public String id() {
		return delegate.getId();
	}

	@Override
	public @Nullable String issuesUrl() {
		return delegate.getContact().get("issues").orElse(null);
	}

	@Override
	public @Nullable String sourcesUrl() {
		return delegate.getContact().get("sources").orElse(null);
	}

	@Override
	public @Nullable String getHomepageUrl() {
		return delegate.getContact().get("homepage").orElse(null);
	}

	@Override
	public String displayName() {
		return delegate.getName();
	}

	@Override
	public String version() {
		return delegate.getVersion().getFriendlyString();
	}
}
