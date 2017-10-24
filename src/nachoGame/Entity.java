package nachoGame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/***********************************************************************
 * Each entity represent an element in the game, the entity is responsible for
 * element's position and resolve image.
 * 
 * @author Cheng Li
 * @version 10-14-2017
 **********************************************************************/
public abstract class Entity {

	/** x position of the current instance of entity. */
	protected int x;

	/** y position of the current instance of entity. */
	protected int y;

	/** Health of the current instance of entity. */
	protected int health;

	/** Current entity graphic */
	protected Image image;

	/*******************************************************************
	 * This is the constructor for entity
	 * 
	 * @param img
	 *            string reference of the entity graphic
	 * @param x
	 *            the x position on the screen of entity
	 * @param y
	 *            the y position on the screen of entity
	 * @param health
	 *            current entity health
	 ******************************************************************/
	public Entity(String img, int x, int y, int health) {
		this.x = x;
		this.y = y;
		this.health = health;
		try {
			image = ImageIO.read(new File(img));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*******************************************************************
	 * @return current x position of entity
	 ******************************************************************/
	public int getX() {
		return x;
	}

	/*******************************************************************
	 * Set the x position of the entity
	 * 
	 * @param x
	 *            x position of the entity
	 ******************************************************************/
	public void setX(int x) {
		this.x = x;
	}

	/*******************************************************************
	 * @return current y position of entity
	 ******************************************************************/
	public int getY() {
		return y;
	}

	/*******************************************************************
	 * Set the y position of the entity
	 * 
	 * @param y
	 *            y position of the entity
	 ******************************************************************/
	public void setY(int y) {
		this.y = y;
	}

	/*******************************************************************
	 * @return entity health
	 ******************************************************************/
	public int getHealth() {
		return health;
	}

	/*******************************************************************
	 * Set the entity health
	 * 
	 * @param health
	 *            entity health
	 ******************************************************************/
	public void setHealth(int health) {
		this.health = health;
	}

	/******************************`*************************************
	 * Set the graphic of entity
	 * 
	 * @param img
	 *            string reference of entity graphic
	 ******************************************************************/
	public void setImage(String img) {
		if (img == null) {
			image = null;
			return;
		}
		try {
			image = ImageIO.read(new File(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*******************************************************************
	 * @return entity image
	 ******************************************************************/
	public Image getImage() {
		return image;
	}

	/*******************************************************************
	 * This abstract class contain partial logic of the entity
	 ******************************************************************/
	public abstract void doLogic();

}
