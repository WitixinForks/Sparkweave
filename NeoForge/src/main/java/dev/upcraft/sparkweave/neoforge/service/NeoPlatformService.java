package dev.upcraft.sparkweave.neoforge.service;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.platform.ModContainer;
import dev.upcraft.sparkweave.api.platform.RuntimeEnvironmentType;
import dev.upcraft.sparkweave.api.platform.services.PlatformService;
import dev.upcraft.sparkweave.neoforge.impl.mod.NeoforgeModContainer;
import dev.upcraft.sparkweave.platform.BasePlatformService;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import oshi.SystemInfo;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

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
}
