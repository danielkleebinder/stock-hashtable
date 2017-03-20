package assets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Loads all assets required for launching the application.
 *
 * @author Daniel Kleebinder
 */
public class AssetsLoader {

	// Images
	public static final BufferedImage ICON = loadBufferedImage("/assets/Icon.png");
	public static final BufferedImage INSERT = loadBufferedImage("/assets/Insert.png");
	public static final BufferedImage OPEN = loadBufferedImage("/assets/Open.png");
	public static final BufferedImage SAVE = loadBufferedImage("/assets/Save.png");

	/**
	 * Loads a buffered image from a given resource path.
	 *
	 * @param path Path.
	 * @return Loaded buffered image or null if an exception occured.
	 */
	private static BufferedImage loadBufferedImage(String path) {
		try {
			return ImageIO.read(AssetsLoader.class.getResourceAsStream(path));
		} catch (IOException ex) {
			Logger.getLogger(AssetsLoader.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
