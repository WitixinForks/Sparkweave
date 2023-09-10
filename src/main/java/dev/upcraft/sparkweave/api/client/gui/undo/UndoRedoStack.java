package dev.upcraft.sparkweave.api.client.gui.undo;

import java.util.LinkedList;

public class UndoRedoStack {

	private final LinkedList<Action> actions = new LinkedList<>();
	private int currentIndex = -1;

	public Action addAction(Action action) {
		if (currentIndex < actions.size() - 1) {
			actions.subList(currentIndex + 1, actions.size()).clear();
		}
		actions.add(action);
		currentIndex++;
		return action;
	}

	public void undo() {
		if (currentIndex >= 0) {
			actions.get(currentIndex).undo();
			currentIndex--;
		}
	}

	public void redo() {
		if (currentIndex < actions.size() - 1) {
			currentIndex++;
			actions.get(currentIndex).perform();
		}
	}

	public boolean canUndo() {
		return currentIndex >= 0;
	}

	public boolean canRedo() {
		return currentIndex < actions.size() - 1;
	}

	public void clear() {
		actions.clear();
		currentIndex = -1;
	}

	public int size() {
		return actions.size();
	}

	public int currentIndex() {
		return currentIndex;
	}

	public Action currentAction() {
		return actions.get(currentIndex);
	}

	public Action getAction(int index) {
		return actions.get(index);
	}
}
