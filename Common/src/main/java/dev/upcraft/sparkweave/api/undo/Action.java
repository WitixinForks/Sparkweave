package dev.upcraft.sparkweave.api.undo;

public interface Action {

	void perform();

	void undo();
}
