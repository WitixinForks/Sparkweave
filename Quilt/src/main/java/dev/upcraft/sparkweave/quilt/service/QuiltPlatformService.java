package dev.upcraft.sparkweave.quilt.service;

import com.google.common.base.Suppliers;
import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.platform.ModContainer;
import dev.upcraft.sparkweave.api.platform.RuntimeEnvironmentType;
import dev.upcraft.sparkweave.api.platform.services.PlatformService;
import dev.upcraft.sparkweave.platform.BasePlatformService;
import dev.upcraft.sparkweave.quilt.impl.mod.QuiltModContainer;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.loader.api.minecraft.MinecraftQuiltLoader;
import org.spongepowered.asm.util.JavaVersion;
import oshi.SystemInfo;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class QuiltPlatformService extends BasePlatformService implements PlatformService {

	private static final String QUILT_LOADER_MODID = "quilt_loader";

	private final Map<String, Optional<ModContainer>> MOD_CONTAINERS = new Object2ObjectOpenHashMap<>();
	private final RuntimeEnvironmentType environmentType = switch (MinecraftQuiltLoader.getEnvironmentType()) {
		case CLIENT -> RuntimeEnvironmentType.CLIENT;
		case SERVER -> RuntimeEnvironmentType.SERVER;
	};
	private final Supplier<String> userAgent = Suppliers.memoize(() -> {
		var info = new SystemInfo();
		var os = info.getOperatingSystem();

		var platformName = getPlatformName();
		var platformVersion = getModContainer(QUILT_LOADER_MODID).orElseThrow(() -> new IllegalStateException("Unable to find quilt loader!")).metadata().version();

		var mcVersion = QuiltLoader.getRawGameVersion();

		var jvmVendor = System.getProperty("java.vm.vendor");
		var jvmVersion = Runtime.version().toString();

		var osName = os.getFamily();
		var osVersion = os.getVersionInfo().getVersion();

		var bitness = "x" + os.getBitness();
		if (os.getBitness() == 32) {
			bitness = "x86";
		}

		return String.format("%s/%s Minecraft/%s Java/%.1f (%s/%s) (%s %s; %s)", platformName, platformVersion, mcVersion, JavaVersion.current(), jvmVendor, jvmVersion, osName, osVersion, bitness);
	});

	@CalledByReflection
	public QuiltPlatformService() {
		// need an explicit default constructor for the service loader to work
		super();
	}

	@Override
	public boolean isModLoaded(String modid) {
		return QuiltLoader.isModLoaded(modid);
	}

	@Override
	public boolean isDevelopmentEnvironment() {
		return QuiltLoader.isDevelopmentEnvironment();
	}

	@Override
	public Path getGameDir() {
		return QuiltLoader.getGameDir();
	}

	@Override
	public Path getConfigDir() {
		return QuiltLoader.getConfigDir();
	}

	@Override
	public Optional<ModContainer> getModContainer(String modid) {
		return MOD_CONTAINERS.computeIfAbsent(modid, key -> QuiltLoader.getModContainer(key).map(QuiltModContainer::of));
	}

	@Override
	public RuntimeEnvironmentType getEnvironmentType() {
		return environmentType;
	}

	@Override
	public List<String> getLaunchArguments(boolean hideSensitive) {
		return List.of(QuiltLoader.getLaunchArguments(hideSensitive));
	}

	@Override
	public String getUserAgent() {
		return userAgent.get();
	}

	@Override
	public String getPlatformName() {
		return "Quilt";
	}

}
