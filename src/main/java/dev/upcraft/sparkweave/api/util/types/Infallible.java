package dev.upcraft.sparkweave.api.util.types;

/**
 * A throwable that is guaranteed to never be thrown.
 */
public final class Infallible extends Throwable {

	private Infallible() {
		throw new UnsupportedOperationException("Cannot instantiate Infallible");
	}
}
