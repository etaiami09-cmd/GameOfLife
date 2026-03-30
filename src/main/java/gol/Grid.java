package gol;

public class Grid {
	private final int width;
	private final int height;
	private boolean[][] data;

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		this.data = new boolean[height][width];
	}

	public void set(int x, int y, boolean value) {
		System.out.println("Called set with x=" + x + ", y=" + y);
		if (outOfBounds(x, y)) return;
		data[y][x] = value;
	}

	public boolean get(int x, int y) {
		if (outOfBounds(x, y)) return false;
		return data[y][x];
	}

	public int getWidth() {return this.width;}

	public int getHeight() {return this.height;}

	public void tick() {
		boolean[][] newData = new boolean[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				newData[y][x] = determineStatus(x, y);
			}
		}
		data = newData;
	}

	private boolean outOfBounds(int x, int y) {
		return (x < 0 || x >= width || y < 0 || y >= height);
	}

	private boolean determineStatus(int x, int y) {
		if (get(x, y)) {
			return shouldSurvive(x, y);
		}
		else {
			return shouldBeBorn(x, y);
		}
	}

	private boolean shouldBeBorn(int x, int y) {
		return (countNeighbors(x, y) == 3);
	}

	private boolean shouldSurvive(int x, int y) {
		int neighbors = countNeighbors(x, y);
		return (neighbors == 2 || neighbors == 3);
	}

	private int countNeighbors(int x, int y) {
		return (
			getIntStatus(x+1, y)
			+ getIntStatus(x-1, y)
			+ getIntStatus(x, y+1)
			+ getIntStatus(x, y-1)
			+ getIntStatus(x+1, y+1)
			+ getIntStatus(x-1, y+1)
			+ getIntStatus(x+1, y-1)
			+getIntStatus(x-1, y-1)
		);
	}

	private int getIntStatus(int x, int y) {
		return (get(x, y) ? 1 : 0);
	}
}