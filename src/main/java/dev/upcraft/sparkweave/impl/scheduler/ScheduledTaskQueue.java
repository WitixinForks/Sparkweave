package dev.upcraft.sparkweave.impl.scheduler;

import com.mojang.datafixers.util.Either;
import dev.upcraft.sparkweave.api.util.scheduler.Task;
import org.quiltmc.qsl.lifecycle.api.event.ServerLifecycleEvents;
import org.quiltmc.qsl.lifecycle.api.event.ServerTickEvents;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.function.LongSupplier;

public class ScheduledTaskQueue {

	private static final Queue<AbstractTask<?>> TASK_QUEUE = new ArrayDeque<>();
	private static volatile LongSupplier timeSupplier = () -> 0;

	public static void init() {
		ServerLifecycleEvents.STARTING.register(server -> {
			TASK_QUEUE.clear();
			timeSupplier = server.getWorldData().overworldData()::getGameTime;
		});
		ServerLifecycleEvents.STOPPED.register(server -> TASK_QUEUE.clear());
		ServerTickEvents.START.register(server -> {
			long time = timeSupplier.getAsLong();
            for (Iterator<AbstractTask<?>> iterator = TASK_QUEUE.iterator(); iterator.hasNext(); ) {
                AbstractTask<?> task = iterator.next();

				if(task.isCancelled()) {
					iterator.remove();
					continue;
				}

				if(task.nextExecutionTime() <= time) {
					if(!task.run(timeSupplier)) {
						iterator.remove();
					}
				}
            }
		});
	}

	public static <T> CompletableFuture<Either<T, ? extends Exception>> scheduleEphemeral(Callable<T> task, long delayTicks) {
		var scheduledTask = new SingleRunTask<>(timeSupplier.getAsLong() + delayTicks, task);
		TASK_QUEUE.add(scheduledTask);
		return scheduledTask.result();
	}

	public static <T> Task<T> scheduleEphemeralAtFixedRate(Callable<T> task, long delayTicks, long periodTicks) {
		var scheduledTask = new RepeatingTask<>(timeSupplier.getAsLong() + delayTicks, periodTicks, task);
		TASK_QUEUE.add(scheduledTask);
		return scheduledTask;
	}
}
