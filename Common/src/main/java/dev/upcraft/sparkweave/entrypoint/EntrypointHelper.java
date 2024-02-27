package dev.upcraft.sparkweave.entrypoint;

import dev.upcraft.sparkweave.api.platform.ModContainer;
import dev.upcraft.sparkweave.api.reflect.ContextHelper;

import java.util.ServiceLoader;
import java.util.function.BiConsumer;

public class EntrypointHelper {

	public static <T> void fireEntrypoints(Class<T> clazz, BiConsumer<T, ModContainer> consumer) {
		ServiceLoader.load(clazz).forEach(instance -> {
			ModContainer container = ContextHelper.getContext(instance.getClass());

			if(container == null) {
				throw new IllegalStateException("Entrypoint instance " + instance.getClass().getName() + " does not have a mod container");
			}

			consumer.accept(instance, container);
		});
	}
}
