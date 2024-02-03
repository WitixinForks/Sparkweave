package dev.upcraft.sparkweave.api.util.modcompat;

import dev.upcraft.sparkweave.api.util.ContextHelper;
import org.quiltmc.loader.api.QuiltLoader;

import java.util.function.Supplier;

public final class CompatHelper {

	private final String modid;
	private final boolean enabled;

	public CompatHelper(String modid) {
		this.modid = modid;
		this.enabled = QuiltLoader.isModLoaded(modid);
	}

	public String modid() {
		return modid;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void ifEnabled(Supplier<Runnable> runnable) {
		if (isEnabled()) {
			runnable.get().run();
		}
	}

	public <T> T orElse(Supplier<Supplier<T>> supplier, T defaultValue) {
		return isEnabled() ? supplier.get().get() : defaultValue;
	}

	public <T> T orElseGet(Supplier<Supplier<T>> supplier, Supplier<T> defaultValue) {
		return isEnabled() ? supplier.get().get() : defaultValue.get();
	}

	public void orThrow() {
		if (!isEnabled()) {
			var callingMod = ContextHelper.getCallerContext().metadata().name();
			throw new IllegalStateException(String.format("[%s CompatHelper] Error: mod %s is not loaded!", callingMod, modid));
		}
	}
}
