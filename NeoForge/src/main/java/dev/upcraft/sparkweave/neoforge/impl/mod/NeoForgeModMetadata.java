package dev.upcraft.sparkweave.neoforge.impl.mod;

import dev.upcraft.sparkweave.api.platform.ModMetadata;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.loading.moddiscovery.ModFileInfo;
import net.neoforged.neoforgespi.language.IModInfo;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.util.Optional;

public class NeoForgeModMetadata implements ModMetadata {

	private final IModInfo delegate;

	public NeoForgeModMetadata(ModContainer modContainer) {
		delegate = modContainer.getModInfo();
    }

	@Override
	public String id() {
		return delegate.getModId();
	}

	@Override
	public @Nullable String issuesUrl() {
        if (delegate.getOwningFile() instanceof ModFileInfo modFileInfo) {
			return Optional.ofNullable(modFileInfo.getIssueURL()).map(URL::toString).orElse(null);
		}

        return null;
    }

	@Override
	public @Nullable String sourcesUrl() {
		return delegate.getOwningFile().getConfig().<String>getConfigElement("sourcesUrl").orElse(null);
	}

	@Override
	public @Nullable String getHomepageUrl() {
		return delegate.getConfig().<String>getConfigElement("displayURL").orElse(null);
	}

	@Override
	public String displayName() {
		return delegate.getDisplayName();
	}

	@Override
	public String version() {
		return delegate.getVersion().toString();
	}
}
