package dev.upcraft.sparkweave.api.platform;

import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface ModContainer {

	ModMetadata metadata();

	/**
	 * @deprecated Use {@link #rootPaths()} instead
	 */
	@Deprecated(forRemoval = true)
	Path rootPath();

	List<Path> rootPaths();

	/**
	 * @deprecated Use {@link #findPath(String)} instead
	 */
	@Deprecated(forRemoval = true)
	@Nullable
	default Path getPath(String file) {
		Path root = rootPath();
		return root.resolve(file.replace("/", root.getFileSystem().getSeparator()));
	}

	default Optional<Path> findPath(String path) {
		return Optional.ofNullable(getPath(path));
	}
}
