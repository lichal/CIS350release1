package nachogame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GamePause extends JDialog implements ActionListener{
	
	/** Message when game is paused */
	private JLabel resumeMessage;
	
	/** Resume the game */
	private JButton resumeButton;
	
	/** Quit the game */
	private JButton quitButton;
	
	private boolean resumed;
	
	public GamePause(JFrame parent) {
		super(parent, true);
		
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
		
		setUndecorated(true);
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
	}
	
	public boolean getResumed(){
		return resumed;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == resumeButton) {
			resumed = true;
			dispose();
		}
	}
	
}
