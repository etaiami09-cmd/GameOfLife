import gol.SpeedSlider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpeedSliderTest {

	@Test
	void sliderValueShouldInitializeCorrectly() {
		SpeedSlider slider = new SpeedSlider(100, 1000, 400);
		int value = slider.getSpeed();
		assertEquals(400, value, "SpeedSlider should have initialized to given default value");
	}

	@Test
	void sliderStartPositionShouldBeWithinBounds() {
		SpeedSlider slider = new SpeedSlider(200, 1000, 500);
		boolean withinBounds = slider.withinBounds(slider.getPosX(), slider.getPosY());
		assertTrue(withinBounds, "The start position of the slider should be within bounds");
	}

	@Test
	void sliderEndPositionShouldBeWithinBounds() {
		SpeedSlider slider = new SpeedSlider(200, 1000, 500);
		boolean withinBounds = slider.withinBounds(
			slider.getPosX() + slider.getWidth()-1,
			slider.getPosY() + slider.getHeight()-1
		);
		assertTrue(withinBounds, "The end position of the slider should be within bounds");
	}

	@Test
	void settingHandleXShouldAdjustHandleCorrectly() {
		SpeedSlider slider = new SpeedSlider(200, 1000, 500);
		slider.setHandleX(slider.getPosX() + (slider.getWidth() / 2));
		assertEquals(600, slider.getSpeed(),
			"Setting handle X to middle of bar should have set value to middle");
	}

	@Test
	void handleXShouldInitializeToCorrectPositionOnScreen() {
		SpeedSlider slider = new SpeedSlider(200, 1000, 600);
		int handleX = slider.getHandleScreenX();
		assertEquals(
			slider.getPosX() + (slider.getWidth() / 2) - (slider.getHandleSize() / 2),
			handleX,
			"Handle X should be in the middle of the bar when initialized to middle value, "
			+ "adjusted for handle starting at its top left"
		);
	}
}
