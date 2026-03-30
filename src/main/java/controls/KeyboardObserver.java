package controls;

import javafx.scene.input.KeyEvent;

public interface KeyboardObserver {
	void onKeyPressed(KeyEvent event);
	void onKeyReleased(KeyEvent event);
	void onKeyTyped(KeyEvent event);
}
