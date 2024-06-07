package dev.upcraft.sparkweave.neoforge.impl.mod;

import dev.upcraft.sparkweave.api.platform.ModContainer;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public record NeoforgeModContainer(NeoForgeModMetadata metadata) implements ModContainer {

    public static NeoforgeModContainer of(net.neoforged.fml.ModContainer delegate) {
        return new NeoforgeModContainer(new NeoForgeModMetadata(delegate));
    }

	@Override
	public List<Path> rootPaths() {
		return List.of(metadata().modInfo().getOwningFile().getFile().getSecureJar().getRootPath());
	}

	@Override
	public Optional<Path> findPath(String path) {
		try {
			return Optional.ofNullable(metadata().modInfo().getOwningFile().getFile().getSecureJar().getPath(path));
		}
		catch (InvalidPathException e) {
			return Optional.empty();
		}
	}
}
