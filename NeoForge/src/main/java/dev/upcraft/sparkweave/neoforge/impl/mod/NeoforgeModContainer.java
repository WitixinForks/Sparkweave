package dev.upcraft.sparkweave.neoforge.impl.mod;

import dev.upcraft.sparkweave.api.platform.ModContainer;

import java.nio.file.Path;

public record NeoforgeModContainer(NeoForgeModMetadata metadata) implements ModContainer {

    public static NeoforgeModContainer of(net.neoforged.fml.ModContainer delegate) {
        return new NeoforgeModContainer(new NeoForgeModMetadata(delegate));
    }

	@Override
	public Path rootPath() {
		return metadata().modInfo().getOwningFile().getFile().getSecureJar().getRootPath();
	}

	@Override
	public Path getPath(String file) {
		return metadata().modInfo().getOwningFile().getFile().getSecureJar().getPath(file);
	}
}
