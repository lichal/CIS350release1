package nachogame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/***********************************************************************
 * This class set the background image according to the game panel size.
 * 
 * @author Cheng Li
 * @version 10-14-2017
 **********************************************************************/
public class BackGround {

	/** The background for our game. */
	private Image background;

	/***********************************************************************
	 * The main constructor of the background.
	 **********************************************************************/
	public BackGround(int x) {
		if (x == 1) {
			try {
				background = ImageIO.read(new File("Art/back.jpg"));
				background = background.getScaledInstance(Scaler.width, Scaler.height, Image.SCALE_DEFAULT);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException m) {
				System.out.println("Size render problem!!!");
			}
		} else {
			try {
				background = ImageIO.read(new File("Art/galaxy.jpg"));
				background = background.getScaledInstance(Scaler.width, Scaler.height, Image.SCALE_DEFAULT);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException m) {
				System.out.println("Size render problem!!!");
			}
		}
	}

	/***********************************************************************
	 * This method returns the background image.
	 * 
	 * @return background - the background image.
	 **********************************************************************/
	public Image getBack() {
		return background;
	}

}
