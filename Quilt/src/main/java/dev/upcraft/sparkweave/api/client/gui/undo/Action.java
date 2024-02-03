package dev.upcraft.sparkweave.api.client.gui.undo;

public interface Action {

	void perform();

	void undo();
}
