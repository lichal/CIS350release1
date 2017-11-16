package nachoGame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ship extends SpaceObject{

	public static int V0;	// not moving
	public static int VL;	// left
	public static int VR;	// right
	
	public Ship() {
		super(1.0, 0.085, "Art/Disgusting_Mustard_Yellow.png");
		setX(Scaler.width / 2);
		setY(getMaxY() + 15);
		V0 = 0;
		VL = -5;
		VR = 5;
	}
	
}
