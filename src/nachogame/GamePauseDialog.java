package nachogame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/*******************************************************************
 * This class modifies the JDialog, make the appearance of the 
 * dialog more attracting, and format buttons and labels.
 * 
 * @author Cheng Li
 *@version 11-30-2017
 ******************************************************************/
public class GamePauseDialog extends JDialog implements ActionListener {

	/** Message when game is paused */
	private JLabel resumeMessage;

	/** Resume the game */
	private JButton resumeButton;

	/** Quit the game */
	private JButton quitButton;

	/** Boolean determine if the game is resumed */
	private boolean resumed;
	
	/** The JPanel holds resume and quit button */
	private JPanel buttonPanel;
	
	/** The JPanel holds message */
	private JPanel messagePanel;
	
	/** Background color use for the dialog */
	private Color backColor;

	/*******************************************************************
	 * This is the JDialog for game pause
	 * @param parent - the parent JFrame
	 ******************************************************************/
	public GamePauseDialog(JFrame parent) {
		super(parent, true);

		// instantiate a color for the dialog
		backColor = new Color(1, 0, 0, 0.3f);
	
		// initialize resume with false
		resumed = false;
		
		// initialize a new JPanel to hold buttons and a message panel
		buttonPanel = new JPanel();
		messagePanel = new JPanel();
		
		// set their layout to gridlayout
		messagePanel.setLayout(new GridLayout(1, 1));
		buttonPanel.setLayout(new GridLayout(1, 2));

		// instantiates the JLabel, and JButtons
		resumeMessage = new JLabel("Game Paused");
		resumeButton = new JButton("Resume");
		quitButton = new JButton("Quit");
		
		// set message alignment and font
		resumeMessage.setHorizontalAlignment(JLabel.CENTER);
		resumeMessage.setFont(new Font("Serif", Font.BOLD, 25));
		
		// set button format
		quitButton.setBorder(new LineBorder(Color.BLACK));
		quitButton.setBackground(backColor);
		quitButton.setFont(new Font("Serif", Font.PLAIN, 20));
		quitButton.setForeground(Color.pink);
		resumeButton.setBorder(new LineBorder(Color.BLACK));
		resumeButton.setBackground(backColor);
		resumeButton.setFont(new Font("Serif", Font.PLAIN, 20));
		resumeButton.setForeground(Color.pink);

		// set Layout
		setLayout(new GridLayout(2, 1));
		
		// add message to message panel
		messagePanel.add(resumeMessage);
		
		// Add buttons to the JPanel
		buttonPanel.add(quitButton);
		buttonPanel.add(resumeButton);
		
		// Add all components
		add(messagePanel);
		add(buttonPanel);

		// add listener
		resumeButton.addActionListener(this);
		quitButton.addActionListener(this);

		// set the background color for the button panel
		buttonPanel.getRootPane().setBackground(backColor);
		buttonPanel.setBackground(backColor);
		
		// set the background color for the message panel
		messagePanel.getRootPane().setBackground(backColor);
		messagePanel.setBackground(backColor);

		// set dialog information
		setUndecorated(true);
		
		setPreferredSize(new Dimension(Scaler.width/6, Scaler.height/6));
		
		// set the dialog to be transparent
		getRootPane ().setOpaque (false);
		
		// set the background color for the dialog
		getContentPane ().setBackground (new Color (0, 0, 0, 0));
		setBackground (new Color (0, 0, 0, 0));
		
		// pack
		pack();
		
		//set location appear
		setLocationRelativeTo(parent);
		setVisible(false);
	}

	/*******************************************************************
	 * This is a getter that determines if resume is pressed
	 ******************************************************************/
	public boolean getResumed() {
		return resumed;
	}

	/*******************************************************************
	 * The action performed class, checks for the button pressed
	 * @param e - action event of player
	 ******************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == resumeButton) {
			resumed = true;
			dispose();
		}
		if (e.getSource() == quitButton) {
			// what is displayed on the dialog
			Object[] message = { "Quit Game?" };

			int option = JOptionPane.showConfirmDialog(null, message, "Game Quit", JOptionPane.OK_CANCEL_OPTION);

			// close the dialog either if cancel and x is clicked
			if (option == JOptionPane.CANCEL_OPTION)
				System.out.println("Quit Canceled");
			else if (option == JOptionPane.CLOSED_OPTION)
				System.out.println("Quit Canceled");
			// Quit the game
			else if (option == JOptionPane.OK_OPTION) {
				System.exit(1);
			}
		}
	}

}
