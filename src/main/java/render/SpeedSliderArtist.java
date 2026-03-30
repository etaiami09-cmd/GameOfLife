package render;

import gol.SpeedSlider;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

@SuppressWarnings("ClassCanBeRecord")
public class SpeedSliderArtist implements Artist {
	private static final Color handleColor = Color.RED;
	private static final Color barColor = Color.BLUE;
	private final SpeedSlider slider;

	public SpeedSliderArtist(SpeedSlider slider) {
		this.slider = slider;
	}

	@Override
	public void draw(GraphicsContext ctx) {
		int barPosX = slider.getPosX();
		int barPosY = slider.getPosY();
		int barWidth = slider.getWidth();
		int barHeight = slider.getHeight();
		int handleSize = slider.getHandleSize();
		int handlePosX = slider.getHandleScreenX();
		int handlePosY = slider.getHandleScreenY();
		ctx.setFill(barColor);
		ctx.fillRect(barPosX, barPosY, barWidth, barHeight);
		ctx.setFill(handleColor);
		ctx.fillOval(handlePosX, handlePosY, handleSize, handleSize);
	}
}
