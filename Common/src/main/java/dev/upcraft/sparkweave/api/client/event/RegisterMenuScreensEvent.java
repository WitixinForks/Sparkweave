package dev.upcraft.sparkweave.api.client.event;

import dev.upcraft.sparkweave.api.event.Event;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

public interface RegisterMenuScreensEvent {

	<MENU extends AbstractContainerMenu, SCREEN extends Screen & MenuAccess<MENU>> void register(Supplier<MenuType<? extends MENU>> menuType, MenuScreens.ScreenConstructor<MENU, SCREEN> screenFactory);

	Event<Callback> EVENT = Event.create(Callback.class, callbacks -> event -> {
		for (Callback callback : callbacks) {
			callback.registerMenuScreens(event);
		}
	});

	@FunctionalInterface
    interface Callback {
		void registerMenuScreens(RegisterMenuScreensEvent event);
	}
}
