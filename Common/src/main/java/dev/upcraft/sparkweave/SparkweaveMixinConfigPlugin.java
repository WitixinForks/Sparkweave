package dev.upcraft.sparkweave;

import dev.upcraft.sparkweave.api.platform.Services;
import dev.upcraft.sparkweave.platform.DotEnv;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class SparkweaveMixinConfigPlugin implements IMixinConfigPlugin {

	private static final boolean DEV_ENV = Services.PLATFORM.isDevelopmentEnvironment();

	static {
		DotEnv.load();
	}

	@Override
	public void onLoad(String mixinPackage) {
		// NO-OP
	}

	@Override
	public String getRefMapperConfig() {
		// NO-OP
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		if (mixinClassName.matches("^dev\\.upcraft\\.sparkweave(\\.(?<loader>[^.]))?\\.mixin\\.debug\\..+")) {
			return DEV_ENV;
		}
		return true;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
		// NO-OP
	}

	@Override
	public List<String> getMixins() {
		// NO-OP
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// NO-OP
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// NO-OP
	}
}
