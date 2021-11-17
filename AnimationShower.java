import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AnimationShower extends Thread implements Shower {
	/**
	 * The animation shower can show the animation of how the people are tranced.
	 * If no solution is found, it will show a message dialog.
	 * @author Yukun Zhao
	 */
	private Frame frame = new Frame();
	private Stack<Condition> path = new Stack<Condition>();
	int savageNumber1; // savage numbers at left side
	int missionaryNumber1; // missionary numbers at left side
	int savageNumber2; // savage numbers at right side
	int missionaryNumber2; // missionary numbers at left side
	int number;
	int place; // place is a variable used to calculate where
				// the circle is drawn (possibly many) and needs
				// to be calculated precisely so that it does
				// not go off screen
	int centerS;
	int centerM;
	int gg;
	Condition c;

	@Override
	public void show(Condition termination, Estimator est) {
		Shower s = new CMDShower();
		s.show(termination, est);
		if (termination.g == Integer.MAX_VALUE) {
			JOptionPane.showMessageDialog(null, "No Solution Found!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Load the path
		while (termination != null) {
			path.add(termination);
			termination = termination.parent;
		}
		// start drawing
		frame.getContentPane().add(new Mypanel());
		frame.setVisible(true);
		place = (int) Math.sqrt(Condition.N) + 1;
		while (!path.isEmpty()) {
			c = path.pop();
			savageNumber1 = c.state[0][0];
			savageNumber2 = c.state[0][1];
			missionaryNumber1 = c.state[1][0];
			missionaryNumber2 = c.state[1][1];
			if (c.parent != null) {
				centerM = Math.abs(c.state[0][0] - c.parent.state[0][0]);
				centerS = Math.abs(c.state[1][0] - c.parent.state[1][0]);
			}
			gg = c.g;

			frame.repaint(); // Condition needs to redraw the panel each time it transforms
			try {
				// Pause the thread for two seconds so that
				// the circle disappears and appears clearly
				Thread.sleep(2000);
			} catch (Exception e) {
				// nothing to do
			}
		}
	}

	class Mypanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			int numDrawed1 = 0;
			int numDrawed2 = 0;
			super.paint(g);

			// Left draw the number of savage by blue circle
			numDrawed1 = 0;
			for (int i = 1; i <= place + 2; i++) {
				for (int j = 1; j <= place + 2; j++) {
					if (numDrawed1 < savageNumber1) {
						g.setColor(Color.blue);
						g.fillOval(100 / place * i, 300 - 100 / place * j, 150 / place / 4, 150 / place / 4);
						numDrawed1++;
					}
				}

			}
			// Left draw the number of missionary by black circle
			numDrawed1 = 0;
			for (int i = 1; i <= place + 2; i++) {
				for (int j = 1; j <= place + 2; j++) {
					if (numDrawed1 < missionaryNumber1) {
						g.setColor(Color.black);
						g.fillOval(100 / place * i, 300 + 100 / place * j, 150 / place / 4, 150 / place / 4);
						numDrawed1++;
					}
				}

			}

			// Right draw the number of savage by blue circle
			numDrawed2 = 0;
			for (int i = 1; i <= place + 2; i++) {
				for (int j = 1; j <= place + 2; j++) {
					if (numDrawed2 < savageNumber2) {
						g.setColor(Color.blue);
						g.fillOval(920 - 100 / place * i, 300 - 100 / place * j, 150 / place / 4, 150 / place / 4);
						numDrawed2++;
					}
				}

			}
			// Right draw the number of missionary by black circle
			numDrawed2 = 0;
			for (int i = 1; i <= place + 2; i++) {
				for (int j = 1; j <= place + 2; j++) {
					if (numDrawed2 < missionaryNumber2) {
						g.setColor(Color.black);
						g.fillOval(920 - 100 / place * i, 300 + 100 / place * j, 150 / place / 4, 150 / place / 4);
						numDrawed2++;
					}
				}

				// This code is used to keep track of how many barbarians and missionaries
				// are transferred each time and is displayed on the screen
				g.setColor(Color.BLACK);
				g.setFont(new Font("Arial", Font.BOLD, 20));
				g.drawString(centerM + " missionaries are transed", 450, 330);
				g.drawString(centerS + " savages are transed", 450, 370);
				g.setFont(new Font("Arial", Font.BOLD, 40));
				g.drawString(" Trips :" + gg, 450, 200);

			}

		}
	}
}

class Frame extends JFrame {
	public Frame() {
		// JFrame The method used to set the title
		setTitle("M-C");
		// Set the width and height
		setSize(1080, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		this.setBackground(Color.LIGHT_GRAY);
		// Set the default close option to exit the program when closing the form
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}