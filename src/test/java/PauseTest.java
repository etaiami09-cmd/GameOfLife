import gol.Pause;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PauseTest {

	@Test
	void shouldInitializeAsPaused() {
		Pause pause = new Pause();
		assertTrue(pause.getPaused(), "Pause instance should have initialized as paused");
	}

	@Test
	void flipShouldFlipTheValue() {
		Pause pause = new Pause();
		for (int i = 1; i < 101; i++) {
			pause.flip();
			assertEquals(
				i % 2 == 0, pause.getPaused(),
				"Flipping pause instance should flip its value"
			);
		}
	}

	@Test
	void settingValueShouldWork() {
		Pause pause = new Pause();
		pause.setPaused(true);
		assertTrue(pause.getPaused(), "Setting pause to true should work");
		pause.setPaused(false);
		assertFalse(pause.getPaused(), "Setting pause to false should work");
	}
}
