package controls;

import gol.SpeedSlider;
import javafx.scene.input.MouseEvent;

public class SpeedSliderMouseObserver implements MouseObserver {

	private final SpeedSlider slider;
	private boolean dragging = false;

	public SpeedSliderMouseObserver(SpeedSlider slider) {
		this.slider = slider;
	}

	@Override
	public void onMousePressed(MouseEvent event) {
		if (slider.withinBounds((int)event.getSceneX(), (int)event.getSceneY())) {
			dragging = true;
		}
	}

	@Override
	public void onMouseDragged(MouseEvent event) {
		if (dragging) {
			slider.setHandleX((int)event.getSceneX());
		}
	}

	@Override
	public void onMouseRelease(MouseEvent event) {
		dragging = false;
	}
}
