package dev.upcraft.sparkweave.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Mod {

	@Target(value = {ElementType.TYPE, ElementType.PACKAGE})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Context {
		/**
		 * @return the mod ID of the current context
		 */
		String value();
	}

	// TODO see if this is needed at all, and find a way to make it work for multiple loader setups
//	/**
//	 * Marks an interface as being an entrypoint, which will cause the annotation processor
//	 * to include it in the quilt.mod.json {@code entrypoints} section.
//	 */
//	@Target(value = ElementType.TYPE)
//	@Retention(RetentionPolicy.RUNTIME)
//	public @interface Entrypoint {
//
//		/**
//		 * @return the id of the entrypoint
//		 */
//		String value();
//	}
}
