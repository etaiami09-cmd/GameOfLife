package gol;

public class Pause {
	private boolean isPaused = true;

	public Pause() {}

	public boolean getPaused() {return this.isPaused;}

	public void flip() {this.isPaused = !isPaused;}

	@SuppressWarnings("unused")
	public void setPaused(boolean newPaused) {this.isPaused = newPaused;}
}
