package dev.upcraft.sparkweave.api.annotation;

import java.lang.annotation.*;

/**
 * Indicates that a class, field, method, or constructor is called by reflection.
 * <p>
 * This annotation is used purely to suppress warnings about reflection usage.
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface CalledByReflection {
}
