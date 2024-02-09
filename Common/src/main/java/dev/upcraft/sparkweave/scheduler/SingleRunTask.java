package dev.upcraft.sparkweave.scheduler;

import com.mojang.datafixers.util.Either;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.function.LongSupplier;

public class SingleRunTask<T> extends AbstractTask<T> {

	private final CompletableFuture<Either<T, ? extends Exception>> result = new CompletableFuture<>();

	public SingleRunTask(long nextExecutionTime, Callable<T> task) {
		super(nextExecutionTime, task);
	}

	@Override
	public synchronized boolean run(LongSupplier timeSupplier) {
		Either<T, ? extends Exception> taskResult;
		try {
			taskResult = Either.left(this.task.call());
		} catch (Exception e) {
			taskResult = Either.right(e);
		}
		this.result.complete(taskResult);
		return false;
	}

	CompletableFuture<Either<T, ? extends Exception>> result() {
		return result;
	}
}
