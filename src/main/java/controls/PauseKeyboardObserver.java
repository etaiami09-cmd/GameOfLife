package controls;

import gol.Pause;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PauseKeyboardObserver implements KeyboardObserver {

	private boolean pressed = false;
	private Pause pause;

	public PauseKeyboardObserver(Pause pause) {
		this.pause = pause;
	}

	@Override
	public void onKeyPressed(KeyEvent event) {
		if (!pressed && event.getCode() == KeyCode.SPACE) {
			pressed = true;
			pause.flip();
		}
	}

	@Override
	public void onKeyReleased(KeyEvent event) {
		if (event.getCode() == KeyCode.SPACE) {
			pressed = false;
		}
	}

	@Override
	public void onKeyTyped(KeyEvent event) {

	}
}