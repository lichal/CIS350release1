package nachoGame;

import java.awt.AWTError;
import java.awt.HeadlessException;
import java.awt.Toolkit;

public class Scaler {
	
	public static int width;
	public static int height;
	
	static {
		try {
			width = (int) (Toolkit.getDefaultToolkit()
					.getScreenSize().getWidth());
			height = (int) (0.9 * Toolkit.getDefaultToolkit()
					.getScreenSize().getHeight());
		} catch (AWTError a) {
			System.out.println("AWTError");
			System.exit(1);
		} catch (HeadlessException h) {
			System.out.println("Headless Exception");
			System.exit(1);
		}
	}
	
	
	/**
	 * This class returns the new scaled dimensions necessary for
	 * rendering images on the screen for a given portion of the desired
	 * screen size
	 * 
	 * @param aspectRatio_Y_Over_X Aspect ratio of original image, with
	 *        the height divided by the width
	 * @param portion The percentage of the screen width the image
	 * should assume
	 * @throws IllegalArgumentException If something went wrong
	 * @return An array giving the pixels of the scaled image size
	 * First is for width, second is for height
	 */
	public static int[] calcDimensions(double aspectRatio_Y_Over_X,
			double portion) {
		int[] newDimens = new int[2];
		if ((aspectRatio_Y_Over_X > 0.0) && (portion > 0.0)
				&& (portion <= 1.0)) {
			newDimens[0] = (int)(portion * Scaler.width);
			newDimens[1] = (int) (aspectRatio_Y_Over_X * newDimens[0]);
			return newDimens;
		} else {
			throw new IllegalArgumentException();
		}
	}
}
