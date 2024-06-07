package dev.upcraft.sparkweave.fabric.impl.registry;

import dev.upcraft.sparkweave.api.client.event.RegisterMenuScreensEvent;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

public class RegisterMenuScreensEventImpl implements RegisterMenuScreensEvent {

	@Override
	public <MENU extends AbstractContainerMenu, SCREEN extends Screen & MenuAccess<MENU>> void register(Supplier<MenuType<MENU>> menuType, ScreenConstructor<MENU, SCREEN> screenFactory) {
		MenuScreens.register(menuType.get(), screenFactory);
	}
}
