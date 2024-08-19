package dev.upcraft.sparkweave.quilt.impl.mod;

import dev.upcraft.sparkweave.api.platform.ModContainer;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public record QuiltModContainer(org.quiltmc.loader.api.ModContainer delegate, QuiltModMetadata metadata) implements ModContainer {

    public static QuiltModContainer of(org.quiltmc.loader.api.ModContainer delegate) {
        return new QuiltModContainer(delegate, new QuiltModMetadata(delegate));
    }

	@Override
	public List<Path> rootPaths() {
		return List.of(delegate().rootPath());
	}

	@Override
	public Optional<Path> findPath(String path) {
		return Optional.ofNullable(delegate().getPath(path));
	}
}
