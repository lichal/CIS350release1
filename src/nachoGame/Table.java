package nachoGame;

import java.awt.Image;

/**********************************************************************
 * This is the table class that sets the image of the table and
 * determines if the nacho reaches the table without correct sauce apply
 * to the nacho.
 * 
 * @author Jon DeWent
 * @version 10-24-2017
 *********************************************************************/
public class Table extends Entity{

	final int width = 175;
	final int height = 125;
	
	public Table(String img, int x, int health) {
		super(img, x, 650, health);
		
		image = image.getScaledInstance(width, height,
				Image.SCALE_DEFAULT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doLogic() {
		
	}

}
