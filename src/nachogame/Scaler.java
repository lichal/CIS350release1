package nachogame;

import java.awt.AWTError;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;

/**********************************************************************
 * This class is used for scaling space objects.
 * 
 * @author Jon DeWent
 * @author Cheng Li
 * @author Ryan Bassor
 *********************************************************************/

public class Scaler {

	/** The width of object in the game. */
	public static final int WIDTH = (int) (Toolkit.getDefaultToolkit().
			getScreenSize().getWidth());
	/** The height of the object. */
	public static final int HEIGHT = (int) (0.9 
		* Toolkit.getDefaultToolkit().
			getScreenSize().getHeight());
	/** Keep font big enough to read on screen. */
	protected static Font font;

	static {
		try {
			int fontSize = (int) (0.012 * Scaler.WIDTH);
		font = new Font("Calibri", Font.TRUETYPE_FONT, fontSize);
		} catch (AWTError a) {
			System.out.println("AWTError");
			System.exit(1);
		} catch (HeadlessException h) {
			System.out.println("Headless Exception");
			System.exit(1);
		}
	}

	/******************************************************************
	 * This class returns the new scaled dimensions necessary 
	 * for rendering images on the screen for a given portion of the 
	 * desired screen size.
	 * 
	 * @param aspectRatioYOverX - Aspect ratio of original image, 
	 * with the height divided by the width.
	 * @param portion - The percentage of the screen width the 
	 * image should assume.
	 * @throws IllegalArgumentException  - If something went wrong.
	 * @return An array giving the pixels of the scaled image size 
	 * First is for width, second is for height.
	 *****************************************************************/
	public static int[] calcDimensions(final double aspectRatioYOverX,
			final double portion) {
		int[] newDimens = new int[2];
		if ((aspectRatioYOverX > 0.0) && (portion > 0.0)
			&& (portion <= 1.0)) {
			newDimens[0] = (int) (portion * Scaler.WIDTH);
			newDimens[1] = (int) (aspectRatioYOverX * newDimens[0]);
			return newDimens;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
}
