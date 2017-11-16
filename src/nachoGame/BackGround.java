package nachoGame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/***********************************************************************
 * This class set the background image according to the game panel size
 * 
 * @author Cheng Li
 * @version 10-14-2017
 **********************************************************************/
public class BackGround {

	/** The background image */
	private Image background;

	/*******************************************************************
	 * The main constructor of the background, it will perform the read
	 * background, resize background.
	 ******************************************************************/
	public BackGround() {
		try {
			background = ImageIO.read(new File("Art/back.jpg"));
			background = background.getScaledInstance(Scaler.width,
					Scaler.height + 40, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException m) {
			System.out.println("Size render problem!!!");
		}
	}

	/*******************************************************************
	 * @return current background image
	 ******************************************************************/
	public Image getBack() {
		return background;
	}

}
