package dev.upcraft.sparkweave.api.platform;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface ModContainer {

	ModMetadata metadata();

	List<Path> rootPaths();

	Optional<Path> findPath(String path);
}
