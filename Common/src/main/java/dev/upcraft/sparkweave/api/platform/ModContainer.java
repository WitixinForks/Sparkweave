package dev.upcraft.sparkweave.api.platform;

import java.nio.file.Path;

public interface ModContainer {

	ModMetadata metadata();

	Path rootPath();

	default Path getPath(String file) {
		Path root = rootPath();
		return root.resolve(file.replace("/", root.getFileSystem().getSeparator()));
	}
}
