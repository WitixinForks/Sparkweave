package dev.upcraft.sparkweave.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DotEnv {

	public static void load(Path path) {
		try (var reader = Files.newBufferedReader(path)) {
			System.getProperties().load(reader);
		} catch (IOException e) {
			SparkweaveLogging.getLogger().error("Unable to load env file: {}", path.toAbsolutePath(), e);
		}
	}
}
