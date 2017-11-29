package nachogame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GamePause extends JDialog implements ActionListener {

	/** Message when game is paused */
	private JLabel resumeMessage;

	/** Resume the game */
	private JButton resumeButton;

	/** Quit the game */
	private JButton quitButton;

	/** Boolean determine if the game is resumed */
	private boolean resumed;

	public GamePause(JFrame parent) {
		super(parent, true);
		super.getContentPane().setBackground(Color.blue);

		// initialize resume with false
		resumed = false;

		// instantiates the JLabel, and JButtons
		resumeMessage = new JLabel("Game Paused");
		resumeButton = new JButton("Resume");
		quitButton = new JButton("Quit");

		// set Layout
		setLayout(new GridLayout(2, 2));

		// Add all components
		add(resumeMessage);
		add(quitButton);
		add(resumeButton);

		// add listener
		resumeButton.addActionListener(this);
		quitButton.addActionListener(this);

		// set dialog information
		setUndecorated(true);
		pack();
		setLocationRelativeTo(parent);
		setVisible(false);
	}

	public boolean getResumed() {
		return resumed;
	}

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
