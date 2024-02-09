package dev.upcraft.sparkweave.platform;

import dev.upcraft.sparkweave.SparkweaveMod;
import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.impl.MacOSXAppDirs;
import net.harawata.appdirs.impl.ShellFolderResolver;
import net.harawata.appdirs.impl.UnixAppDirs;
import net.harawata.appdirs.impl.WindowsAppDirs;
import net.minecraft.Util;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;

public abstract class BasePlatformService {

	protected final AppDirs appDirs = switch (Util.getPlatform()) {
		case WINDOWS -> new WindowsAppDirs(new ShellFolderResolver());
		case LINUX -> new UnixAppDirs();
		case OSX -> new MacOSXAppDirs();
		default -> throw new IllegalStateException("Unsupported OS: " + System.getProperty("os.name"));
	};

	public Path getUserDataDir(@Nullable String subDirectory) {
		Path homeDir = Path.of(appDirs.getUserDataDir(SparkweaveMod.MODID, null, null, false));
		Path basePath = homeDir.resolve("." + SparkweaveMod.MODID);

		try {
			Files.createDirectories(basePath);
			if (Util.getPlatform() == Util.OS.WINDOWS) {
				DosFileAttributeView attributes = Files.getFileAttributeView(basePath, DosFileAttributeView.class);
				if (!attributes.readAttributes().isHidden()) {
					attributes.setHidden(true);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("unable to create or modify directory at " + basePath.toAbsolutePath(), e);
		}
		return subDirectory != null ? basePath.resolve(subDirectory) : basePath;
	}
}
