package dev.upcraft.sparkweave.neoforge.service;

import com.google.common.base.Suppliers;
import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.platform.ModContainer;
import dev.upcraft.sparkweave.api.platform.RuntimeEnvironmentType;
import dev.upcraft.sparkweave.api.platform.services.PlatformService;
import dev.upcraft.sparkweave.neoforge.impl.mod.NeoforgeModContainer;
import dev.upcraft.sparkweave.platform.BasePlatformService;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.internal.versions.neoforge.NeoForgeVersion;
import net.neoforged.neoforge.internal.versions.neoform.NeoFormVersion;
import org.spongepowered.asm.util.JavaVersion;
import oshi.SystemInfo;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@CalledByReflection
public class NeoPlatformService extends BasePlatformService implements PlatformService {

//	private static final Set<String> SENSITIVE_ARGS = Set.of(
//		"accesstoken",
//		"clientid",
//		"profileproperties",
//		"proxypass",
//		"proxyuser",
//		"username",
//		"userproperties",
//		"uuid",
//		"xuid"
//	);

	private final Supplier<String> userAgent = Suppliers.memoize(() -> {
		var info = new SystemInfo();
		var os = info.getOperatingSystem();

		var platformName = getPlatformName();
		var platformVersion = NeoForgeVersion.getVersion();

		var mcVersion = NeoFormVersion.getMCVersion();

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

	// need an explicit default constructor for the service loader to work
	public NeoPlatformService() {
		super();
	}

	@Override
	public boolean isModLoaded(String modid) {
		return ModList.get().isLoaded(modid);
	}

	@Override
	public boolean isDevelopmentEnvironment() {
		return FMLEnvironment.production;
	}

	@Override
	public Path getGameDir() {
		return FMLPaths.GAMEDIR.get();
	}

	@Override
	public Path getConfigDir() {
		return FMLPaths.CONFIGDIR.get();
	}

	@Override
	public Optional<ModContainer> getModContainer(String modid) {
		return ModList.get().getModContainerById(modid).map(NeoforgeModContainer::of);
	}

	@Override
	public RuntimeEnvironmentType getEnvironmentType() {
		return switch (FMLEnvironment.dist) {
			case CLIENT -> RuntimeEnvironmentType.CLIENT;
			case DEDICATED_SERVER -> RuntimeEnvironmentType.SERVER;
		};
	}

	@Override
	public List<String> getLaunchArguments(boolean hideSensitive) {
		// FIXME filter sensitive args, perhaps find a better way to get launch args as well
		// see above for sensitive argument list
		return new SystemInfo().getOperatingSystem().getCurrentProcess().getArguments();
	}

	@Override
	public String getUserAgent() {
		return userAgent.get();
	}

	@Override
	public String getPlatformName() {
		return "NeoForge";
	}
}
