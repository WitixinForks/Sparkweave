package dev.upcraft.sparkweave.quilt.entrypoint;

import dev.upcraft.sparkweave.api.annotation.CalledByReflection;
import dev.upcraft.sparkweave.api.entrypoint.MainEntryPoint;
import dev.upcraft.sparkweave.api.event.CommandEvents;
import dev.upcraft.sparkweave.api.platform.services.RegistryService;
import dev.upcraft.sparkweave.api.registry.block.BlockItemProvider;
import dev.upcraft.sparkweave.entrypoint.EntrypointHelper;
import dev.upcraft.sparkweave.logging.SparkweaveLogging;
import dev.upcraft.sparkweave.scheduler.ScheduledTaskQueue;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.command.api.CommandRegistrationCallback;
import org.quiltmc.qsl.lifecycle.api.event.ServerLifecycleEvents;
import org.quiltmc.qsl.lifecycle.api.event.ServerTickEvents;

@CalledByReflection
public class Main implements ModInitializer {

	@Override
	public void onInitialize(ModContainer mod) {
		RegistryService.get().visitRegistry(BuiltInRegistries.BLOCK, (id, block) -> {
			if (block instanceof BlockItemProvider provider) {
				Registry.register(BuiltInRegistries.ITEM, id, provider.createItem());
			}
		});

		ServerLifecycleEvents.STARTING.register(ScheduledTaskQueue::onServerStarting);
		ServerLifecycleEvents.STOPPED.register(server -> ScheduledTaskQueue.onServerStopped());
		ServerTickEvents.START.register(server -> ScheduledTaskQueue.onServerTick());
		CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, environment) -> CommandEvents.REGISTER.invoker().registerCommands(dispatcher, buildContext, environment));

		EntrypointHelper.fireEntrypoints(MainEntryPoint.class, MainEntryPoint::onInitialize);

		SparkweaveLogging.getLogger().debug("System initialized!");
	}
}
