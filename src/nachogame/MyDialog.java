package nachogame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*******************************************************************
 * This class modifies the JDialog, make the appearance of the 
 * dialog more attracting, and format buttons and labels.
 * 
 * @author Cheng Li
 *@version 11-30-2017
 ******************************************************************/
public class MyDialog extends JDialog implements ActionListener {

	/** Message of the dialog. */
	private JLabel messageLabel;

	/** Left button of the dialog. */
	private MyButton leftButton;

	/** Right button of the dialog. */
	private MyButton rightButton;

	/** Boolean determine if the game is resumed. */
	private boolean resumed;
	
	/** Boolean determine if the game is restart. */
	private boolean restart;
	
	/** The boolean that states whether the game should be at main menu. */
	private boolean backToMain;
	
	/** The JPanel holds left and right button. */
	private JPanel buttonPanel;
	
	/** The JPanel holds message. */
	private JPanel messagePanel;
	
	/** Background color use for the dialog. */
	private Color backColor;
	
	/** Determines which dialog I'm using. */
	private String dialogStat;
	
	/*******************************************************************
	 * This is the JDialog for game pause.
	 * @param parent - the parent JFrame
	 * @param message - the message to be displayed
	 * @param leftButtonText - the message displayed in the rightbutton
	 * @param rightButtonText - the message displayed in the leftbutton
	 ******************************************************************/
	public MyDialog(final JFrame parent, final String message, 
		final String leftButtonText, final String rightButtonText) {
		super(parent, true);
		
		// set the message so we know which dialog it is
		dialogStat = message;
		
		// instantiate a color for the dialog
//		backColor = new Color(1, 0, 0, 0.3f);
		backColor = new Color(0.05f, 0.05f, 0.05f, 0.3f);
		
		// initialize all boolean to false
		resumed = false;
		restart = false;
		backToMain = false;
		
		// initialize a new JPanel to hold buttons and a message panel
		buttonPanel = new JPanel();
		messagePanel = new JPanel();
		
		// set their layout to gridlayout
		messagePanel.setLayout(new GridLayout(1, 1));
		buttonPanel.setLayout(new GridLayout(1, 2));

		// instantiates the JLabel, and JButtons
		messageLabel = new JLabel(message);
		leftButton = new MyButton(leftButtonText);
		rightButton = new MyButton(rightButtonText);
		
		// set message alignment and font
		messageLabel.setHorizontalAlignment(JLabel.CENTER);
		//messageLabel.setFont(new Font("Serif", Font.BOLD, 25));
		messageLabel.setFont(Scaler.font);
		// set message color to white
		messageLabel.setForeground(new Color(255, 255, 255));

		// set Layout
		setLayout(new GridLayout(2, 1));
		
		// add message to message panel
		messagePanel.add(messageLabel);
		
		// Add buttons to the JPanel
		buttonPanel.add(leftButton);
		buttonPanel.add(rightButton);
		
		// Add all components
		add(messagePanel);
		add(buttonPanel);

		// add listener
		leftButton.addActionListener(this);
		rightButton.addActionListener(this);

		// set the background color for the button panel
		buttonPanel.getRootPane().setBackground(backColor);
		buttonPanel.setBackground(backColor);
		
		// set the background color for the message panel
		messagePanel.getRootPane().setBackground(backColor);
		messagePanel.setBackground(backColor);
//		messagePanel.setForeground(new Color(255, 255, 255));

		// set dialog information
		setUndecorated(true);
		
		setPreferredSize(new Dimension(Scaler.WIDTH / 6,
			Scaler.HEIGHT / 6));
		
		// set the dialog to be transparent
		getRootPane().setOpaque(false);
//		setOpacity(0.3f);
		
		// set the background color for the dialog
		getContentPane().setBackground(backColor);
		setBackground(backColor);
		
		// pack
		pack();
		
		//set location appear
		setFont(Scaler.font);
		setModal(true);
		setLocationRelativeTo(parent);
		setVisible(false);
	}

	/*******************************************************************
	 * This is a getter that determines if resume is pressed.
	 * @return resumed - the boolean that determines if the game 
	 * is continuing
	 ******************************************************************/
	public boolean getResumed() {
		return resumed;
	}


	/*******************************************************************
	 * This sets the boolean for resumed.
	 * @param resumed - a boolean that determines whether the game continues
	 ******************************************************************/
	public void setResumed(final boolean resumed) {
		this.resumed = resumed;
	}
	

	/*******************************************************************
	 * This is a getter that determines if resume is pressed.
	 * @return restart - the boolean that states whether game restarts.
	 ******************************************************************/
	public boolean getRestart() {
		return restart;
	}
	
	/*******************************************************************
	 * This is a getter that determines if resume is pressed.
	 * @param restart - the boolean that states whether game restarts.
	 ******************************************************************/
	public void setRestart(final boolean restart) {
		this.restart = restart;
	}
	
	
	/*******************************************************************
	 * This is a getter that determines if the game should go to the 
	 * main menu.
	 * @return backToMain - the boolean that states whether game 
	 * should go to the main menu.
	 ******************************************************************/
	public boolean getBackToMain() {
		return backToMain;
	}
	
	/*******************************************************************
	 * This method sets the boolean that determines if the game should
	 * go back to the main menu.
	 * @param backToMain - the boolean that sets the game back to main 
	 * menu.
	 ******************************************************************/
	public void setBackToMain(final boolean backToMain) {
		this.backToMain = backToMain;
	}

	/*******************************************************************
	 * The action performed class, checks for the button pressed.
	 * @param e - action event of player
	 ******************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (dialogStat == "Game Paused") {
			if (e.getSource() == rightButton) {
				resumed = true;
				dispose();
			}
			if (e.getSource() == leftButton) {
				backToMain = true;
				dispose();
			}
		}
		
		if (dialogStat == "Game Over") {
			if (e.getSource() == rightButton) {
				restart = true;
				dispose();
			}
			if (e.getSource() == leftButton) {
				backToMain = true;
				dispose();
			}
		}
		
	}

}
