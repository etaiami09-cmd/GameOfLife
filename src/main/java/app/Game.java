package app;

import controls.*;
import gol.Grid;
import gol.Pause;
import gol.SpeedSlider;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import render.*;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private static final int GRID_WIDTH = 40;
	private static final int GRID_HEIGHT = 30;
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 600;
	private static final int MIN_SPEED = 1000;
	private static final int MAX_SPEED = 50;
	private static final int DEFAULT_SPEED = 200;
	private final Grid grid = new Grid(GRID_WIDTH, GRID_HEIGHT);
	private final Artist gridArtist = new GridArtist(grid);
	private final SpeedSlider speedSlider = new SpeedSlider(MIN_SPEED, MAX_SPEED, DEFAULT_SPEED);
	private final Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
	private final Artist speedSliderArtist = new SpeedSliderArtist(speedSlider);
	private final Pause pause = new Pause();
	private final Artist pauseArtist = new PauseArtist(pause, grid);
	private final ArtistRegistry artistRegistry = new ArtistRegistry();
	private final GraphicsContext ctx = canvas.getGraphicsContext2D();
	private final Stage stage = new Stage();
	private long lastUpdate = 0;
	private final List<MouseObserver> mouseObservers = new ArrayList<>();
	private final List<KeyboardObserver> keyboardObservers = new ArrayList<>();

	public void run() {
		initGameSystems();
		AnimationTimer timer = getGameLoopTimer();
		timer.start();
	}

	private void draw(GraphicsContext ctx) {
		artistRegistry.draw(ctx);
	}

	// debug method to test a basic pattern
	@SuppressWarnings("unused")
	private void initMess() {
		grid.set(10, 10, true);
		grid.set(10, 11, true);
		grid.set(11, 11, true);
		grid.set(10, 12, true);
	}

	private AnimationTimer getGameLoopTimer() {
		return new AnimationTimer() {
			@Override
			public void handle(long now) {
				ctx.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
				draw(ctx);

				if (!pause.getPaused() &&  now - lastUpdate >= speedSlider.getSpeed() * 1_000_000L) {
					grid.tick();
					lastUpdate = now;
				}
			}
		};
	}

	private void initGameSystems() {
		initArtists();
		initControls();
		initStage();
	}

	private void initArtists() {
		artistRegistry.register(pauseArtist);
		artistRegistry.register(gridArtist);
		artistRegistry.register(speedSliderArtist);
		artistRegistry.setOrder(GridArtist.class, PauseArtist.class, SpeedSliderArtist.class);
	}

	private void initControls() {
		initInputObservers();
		initInputCallbacks();
		canvas.setFocusTraversable(true);
	}

	private void initStage() {
		Scene scene = new Scene(new Group(canvas), SCREEN_WIDTH, SCREEN_HEIGHT);
		stage.setOnCloseRequest(_ -> Platform.exit());
		stage.setScene(scene);
		stage.show();
	}

	private void initInputObservers() {
		mouseObservers.add(new SpeedSliderMouseObserver(speedSlider));
		keyboardObservers.add(new PauseKeyboardObserver(pause));
		ActivateCellInputObserver cellActivator =
			new ActivateCellInputObserver(pause, grid, SCREEN_WIDTH);
		mouseObservers.add(cellActivator);
		keyboardObservers.add(cellActivator);
		keyboardObservers.add(new ExitKeyboardObserver());
	}

	private void initInputCallbacks() {
		canvas.setOnMousePressed((event) -> {
			canvas.requestFocus();
			for (MouseObserver mouseObserver : mouseObservers) {
				mouseObserver.onMousePressed(event);
			}
		});
		canvas.setOnMouseDragged((event) -> {
			for (MouseObserver mouseObserver : mouseObservers) {
				mouseObserver.onMouseDragged(event);
			}
		});
		canvas.setOnMouseReleased((event) -> {
			for (MouseObserver mouseObserver : mouseObservers) {
				mouseObserver.onMouseReleased(event);
			}
		});
		canvas.setOnKeyPressed((event) -> {
			for (KeyboardObserver keyboardObserver : keyboardObservers) {
				keyboardObserver.onKeyPressed(event);
			}
		});
		canvas.setOnKeyTyped((event) -> {
			for (KeyboardObserver keyboardObserver : keyboardObservers) {
				keyboardObserver.onKeyTyped(event);
			}
		});
		canvas.setOnKeyReleased((event) -> {
			for (KeyboardObserver keyboardObserver : keyboardObservers) {
				keyboardObserver.onKeyReleased(event);
			}
		});
	}
}
