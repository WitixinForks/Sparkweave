package dev.upcraft.sparkweave.api.entrypoint;

import dev.upcraft.sparkweave.api.platform.ModContainer;

public interface ClientEntryPoint {

	void onInitializeClient(ModContainer mod);
}
