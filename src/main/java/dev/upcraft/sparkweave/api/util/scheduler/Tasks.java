package dev.upcraft.sparkweave.api.util.scheduler;

import com.mojang.datafixers.util.Either;
import dev.upcraft.sparkweave.api.util.types.Success;
import dev.upcraft.sparkweave.impl.scheduler.ScheduledTaskQueue;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class Tasks {

	/**
	 * Schedule a task to run on the server's main thread after a delay.
	 * The task will not be saved to disk, and will not be run if the server restarts.
	 */
	public static <T> CompletableFuture<Either<T, ? extends Exception>> scheduleEphemeral(Callable<T> task, long delayTicks) {
		return ScheduledTaskQueue.scheduleEphemeral(task, delayTicks);
	}

	/**
	 * Schedule a task to run on the server's main thread after an initial delay, and then repeatedly after a fixed delay.
	 * The task will not be saved to disk, and will not be run if the server restarts.
	 */
	public static <T> Task<T> scheduleEphemeralAtFixedRate(Callable<T> task, long delayTicks, long periodTicks) {
		return ScheduledTaskQueue.scheduleEphemeralAtFixedRate(task, delayTicks, periodTicks);
	}

	/**
	 * Schedule a task to run on the server's main thread after a delay.
	 * The task will not be saved to disk, and will not be run if the server restarts.
	 */
	public static CompletableFuture<Either<Success, ? extends Exception>> scheduleEphemeral(Runnable task, long delayTicks) {
		return scheduleEphemeral(() -> {
			task.run();
			return Success.INSTANCE;
		}, delayTicks);
	}

	/**
	 * Schedule a task to run on the server's main thread after an initial delay, and then repeatedly after a fixed delay.
	 * The task will not be saved to disk, and will not be run if the server restarts.
	 */
	public static Task<Success> scheduleEphemeralAtFixedRate(Runnable task, long delayTicks, long periodTicks) {
		return scheduleEphemeralAtFixedRate(() -> {
			task.run();
			return Success.INSTANCE;
		}, delayTicks, periodTicks);
	}
}
