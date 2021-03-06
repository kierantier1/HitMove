package gdx.hitmove.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import gdx.hitmove.GdxHitMove;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GdxHitMove(), config);
                config.width = 1200;
                config.height = 600;
		new LwjglApplication(new GdxHitMove(), config);
	}
}
