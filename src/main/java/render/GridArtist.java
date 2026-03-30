package render;

import gol.Grid;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

@SuppressWarnings("ClassCanBeRecord")
public class GridArtist implements Artist {
	private static final Color deadColor = Color.WHITE;
	private static final Color liveColor = Color.BLACK;
	private final Grid grid;

	public GridArtist(Grid grid) {
		this.grid = grid;
	}

	@Override
	public void draw(GraphicsContext ctx) {
		int screenWidth = (int)ctx.getCanvas().getWidth();
		int pixelSize = getPixelSize(screenWidth);
		for (int y = 0; y < grid.getHeight(); y++) {
			for (int x = 0; x < grid.getWidth(); x++) {
				ctx.setFill(getColor(x, y));
				ctx.fillRect(
					gridToScreen(x, screenWidth), gridToScreen(y, screenWidth), pixelSize, pixelSize
				);
			}
		}
	}

	private Color getColor(int x, int y) {
		return (grid.get(x, y)) ? liveColor : deadColor;
	}

	private int getPixelSize(int screenWidth) {
		return (int)((double)screenWidth / (double)grid.getWidth());
	}

	private int gridToScreen(int vec, int screenWidth) {
		double ratio = ((double)screenWidth) / ((double)grid.getWidth());
		return (int)(ratio * vec);
	}

	@SuppressWarnings("unused")
	private int screenToGrid(int vec, int screenWidth) {
		double ratio = grid.getWidth() / (double)screenWidth;
		return (int)(ratio * vec);
	}
}
