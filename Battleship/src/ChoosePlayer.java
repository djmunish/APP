import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JLabel;


public class ChoosePlayer {

	private JFrame frame;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoosePlayer window = new ChoosePlayer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChoosePlayer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 583, 468);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnPlayWithPc = new JButton("play with pc");
		btnPlayWithPc.setPreferredSize(new Dimension(40, 40));

		btnPlayWithPc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame2 f22 = new Frame2();
				f22.setVisible(true);
			}
		});

		btnPlayWithPc.setAction(action);
		frame.getContentPane().add(btnPlayWithPc, BorderLayout.NORTH);
		
		JLabel lblWelcomeToBattleship = new JLabel("Welcome to Battleship Game");
		frame.getContentPane().add(lblWelcomeToBattleship, BorderLayout.CENTER);
		
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "PLAY WITH PC");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
