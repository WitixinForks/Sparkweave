package dev.upcraft.sparkweave.api.platform;

import net.minecraft.util.Mth;

import java.util.Arrays;

public enum RuntimeEnvironmentType {
	CLIENT("client"),
	SERVER("server");

	private final String name;

	RuntimeEnvironmentType(String name) {
        this.name = name;
    }

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

	public boolean isClientSide() {
		return this == CLIENT;
	}

	public boolean isServerSide() {
		return this == SERVER;
	}

	public void orElseThrow() {
		if(Services.PLATFORM.getEnvironmentType() != this) {
			var ex = new IllegalStateException("Attempted to access " + this.getName() + "from wrong environment!");
			var stacktrace = ex.getStackTrace();
			ex.setStackTrace(Arrays.copyOfRange(stacktrace, Mth.clamp(2, 0, stacktrace.length - 1), stacktrace.length));
			throw ex;
		}
	}
}
