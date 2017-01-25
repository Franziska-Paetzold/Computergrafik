package lenz.opengl;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public abstract class AbstractSimpleBase {

	public void start() {

		try {
			Display.setDisplayMode(new DisplayMode(1200, 1200));
			Display.create();
		} catch (LWJGLException e) {
			throw new RuntimeException("Unable to intialize display", e);
		}

		// hier Initialisieren
		initOpenGL();

		while (!Display.isCloseRequested()) {

			// hier Zeichnen
			render();

			Display.update();
			//sync spart Rechenleistung, weil Bildschirm eh nicht schneller als 60 Bilder pro Sekunde kann 
			Display.sync(60);
		}

		Display.destroy();

	}

	protected abstract void initOpenGL();
	// ToDo

	protected abstract void render();
	//ToDo

}
