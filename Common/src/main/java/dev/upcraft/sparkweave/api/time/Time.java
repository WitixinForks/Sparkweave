package dev.upcraft.sparkweave.api.time;

import java.time.Duration;

public class Time {

	public static long toTicks(long durationMs) {
		return durationMs / 50L;
	}

	public static long toMillis(long durationTicks) {
		return durationTicks * 50L;
	}

	public static long toTicks(Duration duration) {
		return toTicks(duration.toMillis());
	}
}
