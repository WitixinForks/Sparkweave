package dev.upcraft.sparkweave.api.platform;

import com.mojang.serialization.Codec;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;

public enum RuntimeEnvironmentType implements StringRepresentable {
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
	public String getSerializedName() {
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

	public static final Codec<RuntimeEnvironmentType> CODEC = StringRepresentable.fromEnum(RuntimeEnvironmentType::values);
}
