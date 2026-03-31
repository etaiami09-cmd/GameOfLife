package render;

import gol.SpeedSlider;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

@SuppressWarnings("ClassCanBeRecord")
public class SpeedSliderArtist implements Artist {
	private static final Color handleColor = Color.RED;
	private static final Color barColor = Color.BLUE;
	private static final Color ticksPerSecondColor = Color.DARKBLUE;
	private static final int textPadding = 25;
	private final SpeedSlider slider;

	public SpeedSliderArtist(SpeedSlider slider) {
		this.slider = slider;
	}

	@Override
	public void draw(GraphicsContext ctx) {
		drawSlider(ctx);
		drawTicksPerSecond(ctx);
	}

	private void drawSlider(GraphicsContext ctx) {
		drawBar(ctx);
		drawHandle(ctx);
	}

	private void drawHandle(GraphicsContext ctx) {
		int handleSize = slider.getHandleSize();
		int handlePosX = slider.getHandleScreenX();
		int handlePosY = slider.getHandleScreenY();
		ctx.setFill(handleColor);
		ctx.fillOval(handlePosX, handlePosY, handleSize, handleSize);
	}

	private void drawBar(GraphicsContext ctx) {
		int barPosX = slider.getPosX();
		int barPosY = slider.getPosY();
		int barWidth = slider.getWidth();
		int barHeight = slider.getHeight();
		ctx.setFill(barColor);
		ctx.fillRect(barPosX, barPosY, barWidth, barHeight);
	}

	private void drawTicksPerSecond(GraphicsContext ctx) {
		ctx.setFill(ticksPerSecondColor);
		ctx.setFont(Font.font("Arial", slider.getHandleSize()));
		ctx.setTextBaseline(VPos.CENTER);
		ctx.fillText(getTicksPerSecondText(), getTextXPosition(), getTextYPosition());
	}

	private double getTicksPerSecond() {
		return (1000.0 / slider.getSpeed());
	}

	private String getTicksPerSecondText() {
		double ticksPerSecond = getTicksPerSecond();
		String text = String.format("%.2f", ticksPerSecond);
		return text + " G/S";
	}

	private int getTextXPosition() {
		return slider.getPosX() + slider.getWidth() + textPadding;
	}

	private int getTextYPosition() {
		return slider.getPosY() + (slider.getHeight() / 2);
	}
}
