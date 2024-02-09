package dev.upcraft.sparkweave.neoforge.entrypoint;

import dev.upcraft.sparkweave.SparkweaveMod;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(SparkweaveMod.MODID)
public class Main {

	public Main() {
		//TODO init

		if(FMLEnvironment.dist.isClient()) {
			Client.init();
		}
	}
}
