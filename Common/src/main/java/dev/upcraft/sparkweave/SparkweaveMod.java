package dev.upcraft.sparkweave;

import dev.upcraft.sparkweave.api.entrypoint.MainEntryPoint;
import dev.upcraft.sparkweave.api.event.CommandEvents;
import dev.upcraft.sparkweave.api.platform.ModContainer;
import dev.upcraft.sparkweave.command.SparkweaveCommandRoot;

public class SparkweaveMod implements MainEntryPoint {

	public static final String MODID = "sparkweave";

	@Override
	public void onInitialize(ModContainer mod) {
		CommandEvents.REGISTER.register(SparkweaveCommandRoot::register);
	}
}
