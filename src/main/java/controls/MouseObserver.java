package controls;

import javafx.scene.input.MouseEvent;

/**
 * Interface for all systems that depend on mouse events
 */
public interface MouseObserver {
	void onMousePressed(MouseEvent event);
	void onMouseDragged(MouseEvent event);
	void onMouseRelease(MouseEvent event);
}
