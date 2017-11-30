package nachogame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**********************************************************************
 * This class extends JButton to override the format of a button.
 * 
 * @author Cheng Li
 * @version Nov 30, 2017
 *********************************************************************/
public class MyButton extends JButton{
	
	/** Background color use for the Buttons */
	private Color normalColor;
	
	/** This is the color of the text when button is pressed */
	private Color pressdColor;
	
	/** This is the color of the text when button is not pressed */
	private Color unPressedColor;
	
	/*******************************************************************
	 * My button which modify and my JButton more fancy
	 * @param text - the text appear on the button
	 ******************************************************************/
	public MyButton(String text){
		super(text);
		
		// define a color for the button background color
		normalColor = new Color(1, 0, 0, 0.3f);
		
		// define a color when button is pressed
		pressdColor = Color.white;
		
		// define a color when button is not pressed
		unPressedColor = Color.pink;
		
		// set button's border and colors and font
		setBorder(new LineBorder(Color.BLACK));
		setBackground(normalColor);
		setFont(new Font("Serif", Font.PLAIN, 20));
		setForeground(unPressedColor);

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
