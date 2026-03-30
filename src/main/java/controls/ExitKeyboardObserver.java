package controls;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ExitKeyboardObserver implements KeyboardObserver {

	@Override
	public void onKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ESCAPE) {
			Platform.exit();
		}
	}

	@Override
	public void onKeyReleased(KeyEvent event) {}

	@Override
	public void onKeyTyped(KeyEvent event) {}
}
