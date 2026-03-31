package app;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		Game game = new Game();
		game.run();
	}

	static void main(String[] args) {
		launch(args);
	}
}
