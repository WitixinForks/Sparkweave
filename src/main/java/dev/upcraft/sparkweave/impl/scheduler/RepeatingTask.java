package dev.upcraft.sparkweave.impl.scheduler;

import com.mojang.datafixers.util.Either;

import java.util.concurrent.Callable;
import java.util.function.LongSupplier;

public class RepeatingTask<T> extends AbstractTask<T> {

	private final long period;
	private Either<T, ? extends Exception> lastResult = Either.right(new IllegalStateException("Task has not been run yet."));

	public RepeatingTask(long nextExecutionTime, long period, Callable<T> task) {
		super(nextExecutionTime, task);
		this.period = period;
	}

	public long period() {
		return period;
	}

	@Override
	public synchronized boolean run(LongSupplier timeSupplier) {
		var startTime = timeSupplier.getAsLong();
		this.nextExecutionTime = startTime + period();
		try {
			this.lastResult = Either.left(this.task.call());
			return true;
		} catch (Exception e) {
			this.lastResult = Either.right(e);
		}
		return false;
	}

	@Override
	public Either<T, ? extends Exception> getLastResult() {
		return this.lastResult;
	}
}
