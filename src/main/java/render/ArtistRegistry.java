package render;

import javafx.scene.canvas.GraphicsContext;

import java.util.*;

public class ArtistRegistry {
	private final Map<Class<? extends Artist>, Artist> artists = new HashMap<>();
	private List<Class<? extends Artist>> order;

	public void register(Artist artist) {
		artists.put(artist.getClass(), artist);
	}

	@SafeVarargs
	public final void setOrder(Class<? extends Artist>... classes) {
		order = Arrays.asList(classes);
	}

	public void draw(GraphicsContext ctx) {
		if (order == null) {
			drawArbitrarily(ctx);
		}
		else {
			drawInOrder(ctx);
		}
	}

	private void drawArbitrarily(GraphicsContext ctx) {
		for (Artist artist : artists.values()) {
			artist.draw(ctx);
		}
	}

	private void drawInOrder(GraphicsContext ctx) {
		for (Class<? extends Artist> clazz : order) {
			artists.get(clazz).draw(ctx);
		}
	}
}
