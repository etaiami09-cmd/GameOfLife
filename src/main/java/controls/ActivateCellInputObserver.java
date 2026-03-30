package controls;

import gol.Grid;
import gol.Pause;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ActivateCellInputObserver implements MouseObserver, KeyboardObserver {

	private final Pause pause;
	private final Grid grid;
	private final int width;
	private boolean shiftPressed = false;

	public ActivateCellInputObserver(Pause pause, Grid grid, int width) {
		this.pause = pause;
		this.grid = grid;
		this.width = width;
	}

	@Override
	public void onMousePressed(MouseEvent event) {
		if (pause.getPaused()) {
			activateHoveredCell(event);
		}
	}

	@Override
	public void onMouseDragged(MouseEvent event) {
		if (pause.getPaused() && shiftPressed) {
			activateHoveredCell(event);
		}
	}

	@Override
	public void onMouseReleased(MouseEvent event) {}

	@Override
	public void onKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.SHIFT) {
			shiftPressed = true;
		}
	}

	@Override
	public void onKeyReleased(KeyEvent event) {
		if (event.getCode() == KeyCode.SHIFT) {
			shiftPressed = false;
		}
	}

	@Override
	public void onKeyTyped(KeyEvent event) {
	}

	private int screenToGrid(double vec) {
		double gridToScreenRatio = grid.getWidth() / (double)width;
		return (int)(vec * gridToScreenRatio);
	}

	private void activateHoveredCell(MouseEvent event) {
		double mouseX = event.getSceneX();
		double mouseY = event.getSceneY();
		grid.set(screenToGrid(mouseX), screenToGrid(mouseY), true);
	}
}