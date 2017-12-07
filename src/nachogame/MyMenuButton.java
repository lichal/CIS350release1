package nachogame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**********************************************************************
 * This class extends JButton to override the format of a button.
 * This button is used for the menu screen.
 * 
 * @author Cheng Li
 * @version Nov 30, 2017
 *********************************************************************/
public class MyMenuButton extends JButton{

	
	/** Background color use for the Buttons */
	private Color normalColor;
	
	/** This is the color of the text when button is pressed */
	private Color pressdColor;
	
	/** This is the color of the text when button is not pressed */
	private Color unPressedColor;
	
	/*******************************************************************
	 * My menu button which modify and my JButton more fancy.
	 * @param text - the text appear on the button.
	 ******************************************************************/
	public MyMenuButton(String text){
		super(text);
		
		// define a color for the button background color
		normalColor = new Color(1, 0, 0, 0.5f);
		
		// define a color when button is pressed
		pressdColor = Color.white;
		
		// define a color when button is not pressed
		unPressedColor = new Color(1, 1, 0, 1f);
		
		// set button's border and colors and font
		setBorderPainted(false);
		setBackground(normalColor);
		//setFont(new Font("Serif", Font.PLAIN, 20));
		setFont(Scaler.font);
		setForeground(unPressedColor);
		setFocusable(true);

		// set change listener to respond to click
		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (getModel().isPressed()) {
					setForeground(pressdColor);
				} else if (getModel().isRollover()) {
					setForeground(pressdColor);
				} else {
					setForeground(unPressedColor);
				}

			}
		});
	}
}
