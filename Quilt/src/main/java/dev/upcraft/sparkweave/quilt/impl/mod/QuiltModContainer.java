package dev.upcraft.sparkweave.quilt.impl.mod;

import dev.upcraft.sparkweave.api.platform.ModContainer;

import java.nio.file.Path;

public record QuiltModContainer(org.quiltmc.loader.api.ModContainer delegate, QuiltModMetadata metadata) implements ModContainer {

    public static QuiltModContainer of(org.quiltmc.loader.api.ModContainer delegate) {
        return new QuiltModContainer(delegate, new QuiltModMetadata(delegate));
    }

	@Override
	public Path rootPath() {
		return delegate().rootPath();
	}

	@Override
	public Path getPath(String file) {
		return delegate().getPath(file);
	}
}
