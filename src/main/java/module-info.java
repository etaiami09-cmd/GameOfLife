module GameOfLife {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.desktop;
	opens app to javafx.graphics;
	exports gol;
}