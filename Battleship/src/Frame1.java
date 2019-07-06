import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;


public class Frame1 {

	private JFrame frame;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
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
	public Frame1() {
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
		btnPlayWithPc.setAction(action);
		frame.getContentPane().add(btnPlayWithPc, BorderLayout.NORTH);
		
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			
			putValue(NAME, "");
			putValue(SHORT_DESCRIPTION, "Some short description");
			
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
