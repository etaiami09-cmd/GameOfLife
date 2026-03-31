package gol;

public class SpeedSlider {
	private static final int HANDLE_SIZE = 30;
	private static final int POSITION_Y = 575;
	private static final int POSITION_X = 125;
	private static final int WIDTH = 500;
	private static final int HEIGHT = 10;
	private final int minSpeed;
	private final int maxSpeed;
	private int value;
	private final int handleSize = HANDLE_SIZE;
	private final int posX = POSITION_X;
	private final int posY = POSITION_Y;
	private final int width = WIDTH;
	private final int height = HEIGHT;

	public SpeedSlider(int minSpeed, int maxSpeed, int initial) {
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
		this.value = initial;
	}

	public int getSpeed() {return this.value;}

	public int getHandleSize() {return this.handleSize;}

	public void setHandleX(int x) {
		int realX = Math.clamp(x, posX, posX+width);
		double percentage = (realX - posX) / (double)width;
		setPercentage(percentage);
	}

	public boolean withinBounds(int x, int y) {
		return (withinHandle(x, y) || withinBar(x, y));
	}

	public int getPosX() {return this.posX;}

	public int getPosY() {return this.posY;}

	public int getWidth() {return this.width;}

	public int getHeight() {return this.height;}

	public int getHandleScreenY() {
		return posY + (height / 2) - (handleSize / 2);
	}

	public int getHandleScreenX() {
		return getHandleX() + posX - (handleSize / 2);
	}

	private int getHandleY() {
		return this.height;
	}

	private int getHandleX() {
		double percentage = ((double)value - minSpeed) / ((double)maxSpeed - minSpeed);
		return (int)(width * percentage);
	}

	private boolean withinHandle(int x, int y) {
		return (distanceToHandle(x, y) < handleSize);
	}

	private boolean withinBar(int x, int y) {
		return (
			y >= posY
			&& (y-posY) < height
			&& x >= posX
			&& (x-posX) < width
		);
	}

	private double distanceToHandle(int x, int y) {
		return Math.pow((Math.pow(x-getHandleX(), 2) + Math.pow(y-getHandleY(), 2)), 0.5);
	}

	private void setPercentage(double percentage) {
		int rangeSize = maxSpeed - minSpeed;
		int withinRangeValue = (int)(percentage * rangeSize);
		this.value = minSpeed + withinRangeValue;
	}
}