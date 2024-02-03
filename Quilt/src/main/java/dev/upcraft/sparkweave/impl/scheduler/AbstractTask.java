package dev.upcraft.sparkweave.impl.scheduler;

import com.mojang.datafixers.util.Either;
import dev.upcraft.sparkweave.api.util.scheduler.Task;

import java.util.concurrent.Callable;
import java.util.function.LongSupplier;

public abstract class AbstractTask<T> implements Task<T> {

	protected final Callable<T> task;
	protected long nextExecutionTime;

	private volatile boolean cancelled = false;

    public AbstractTask(long nextExecutionTime, Callable<T> task) {
        this.nextExecutionTime = nextExecutionTime;
        this.task = task;
    }

    public long nextExecutionTime() {
        return nextExecutionTime;
    }

    public abstract boolean run(LongSupplier timeSupplier);

	@Override
	public Either<T, ? extends Exception> getLastResult() {
		return Either.right(new UnsupportedOperationException("Not implemented for single-run tasks."));
	}

	@Override
	public void cancel() {
		this.cancelled = true;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}
}
