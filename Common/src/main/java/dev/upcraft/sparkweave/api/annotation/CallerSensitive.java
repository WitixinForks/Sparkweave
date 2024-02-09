package dev.upcraft.sparkweave.api.annotation;

import dev.upcraft.sparkweave.api.reflect.ContextHelper;

import java.lang.annotation.*;

/**
 * Indicates that a method or constructor is sensitive to the context in which it is used
 *
 * @see ContextHelper
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface CallerSensitive {
}
