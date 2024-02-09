package dev.upcraft.sparkweave.mixin.debug;

import net.minecraft.client.User;
import net.minecraft.client.main.GameConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GameConfig.UserData.class)
public interface UserDataAccessor {

	@Mutable
	@Accessor("user")
	void sparkweave$updateSession(User value);
}
