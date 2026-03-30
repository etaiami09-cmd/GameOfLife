package render;

import gol.Grid;
import gol.Pause;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

@SuppressWarnings("FieldCanBeLocal")
public class PauseArtist implements Artist {
	private static final Color pauseColor = Color.RED;
	private static final Color gridLineColor = Color.DARKGRAY;
	private static final int X_POSITION = 50;
	private static final int Y_POSITION = 50;
	private static final int SCALE = 60;
	private final Pause pause;
	private final Grid grid;
	private final int posX = X_POSITION;
	private final int posY = Y_POSITION;
	private final int scale = SCALE;
	private final double[][] polygon;

	public PauseArtist(Pause pause, Grid grid) {
		this.pause = pause;
		this.grid = grid;
		polygon = getTriangle();
	}

	@Override
	public void draw(GraphicsContext ctx) {
		if (pause.getPaused()) {
			drawGridLines(ctx);
			drawPauseTriangle(ctx);
		}
	}

	private void drawGridLines(GraphicsContext ctx) {
		int screenWidth = (int)ctx.getCanvas().getWidth();
		int screenHeight = (int)ctx.getCanvas().getHeight();
		int cellSize = getCellSize(screenWidth);
		ctx.setStroke(gridLineColor);
		for (int x = 0; x < grid.getWidth(); x++) {
			int screenX = cellSize * x;
			ctx.strokeLine(screenX, 0, screenX, screenHeight);
		}
		for (int y = 0; y < grid.getHeight(); y++) {
			int screenY = cellSize * y;
			ctx.strokeLine(0, screenY, screenWidth, screenY);
		}
	}

	private int getCellSize(int screenWidth) {
		return (int)((double)screenWidth / (double)grid.getWidth());
	}

	private void drawPauseTriangle(GraphicsContext ctx) {
		ctx.setFill(pauseColor);
		ctx.fillPolygon(polygon[0], polygon[1], 3);
	}

	private double[][] getTriangle() {
		return new double[][]{
			{posX, posX, posX + Math.sqrt((5.0/4.0) * Math.pow(scale, 2)) * 0.8},
			{posY, posY+scale, posY + (scale / 2.0)}
		};
	}
}
