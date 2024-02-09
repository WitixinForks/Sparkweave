package dev.upcraft.sparkweave.api.scheduler;

import com.mojang.datafixers.util.Either;

public interface Task<T> {

	/**
	 * @return the last result of this task, or an exception if the task failed.
	 */
    Either<T, ? extends Exception> getLastResult();

	/**
	 * Cancel any future execution of this task.
	 * If the task is currently running, it will be allowed to finish.
	 * <p>Repeatedly calling this method has no effect.</p>
	 */
	void cancel();

	boolean isCancelled();
}
