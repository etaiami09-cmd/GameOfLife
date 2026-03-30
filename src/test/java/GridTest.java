import gol.Grid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

	@Test
	void deadCellWithThreeNeighborsBecomesAlive() {
		Grid grid = new Grid(5, 5);
		grid.set(1, 2, true);
		grid.set(2, 2, true);
		grid.set(3, 2, true);
		grid.tick();
		assertTrue(grid.get(2, 1), "Cell (2, 1) should have survived");
	}

	@Test
	void liveCellWithTwoNeighborsSurvives() {
		Grid grid = new Grid(5, 5);
		grid.set(1, 2, true);
		grid.set(1, 1, true);
		grid.set(2, 2, true);
		grid.tick();
		assertTrue(grid.get(1, 2), "Cell (1, 2) should have survived");
	}

	@Test
	void liveCellWithThreeNeighborsSurvives() {
		Grid grid = new Grid(5, 5);
		grid.set(1, 2, true);
		grid.set(1, 1, true);
		grid.set(2, 2, true);
		grid.set(2, 1, true);
		grid.tick();
		assertTrue(grid.get(1, 2), "Cell (1, 2) should have survived");
	}

	@Test
	void liveCellWithZeroNeighborsDies() {
		Grid grid = new Grid(5, 5);
		grid.set(2, 2, true);
		grid.tick();
		assertFalse(grid.get(2, 2), "Cell (2, 2) should have died");
	}

	@Test
	void liveCellWithOneNeighborDies() {
		Grid grid = new Grid(5, 5);
		grid.set(1, 2, true);
		grid.set(2, 2, true);
		grid.tick();
		assertFalse(grid.get(1, 2), "Cell (1, 2) should have died");
	}

	@Test
	void liveCellWithFourNeighborsDies() {
		Grid grid = new Grid(5, 5);
		grid.set(1, 2, true);
		grid.set(1, 3, true);
		grid.set(2, 2, true);
		grid.set(2, 1, true);
		grid.set(1, 1, true);
		grid.tick();
		assertFalse(grid.get(1, 2), "Cell (1, 2) should have died");
	}
}