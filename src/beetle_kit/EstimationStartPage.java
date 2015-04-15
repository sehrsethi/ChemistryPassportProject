package beetle_kit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * The page that lets the user choose the default mode or one of the three
 * color-blind modes and allows them to start playing the game
 * 
 * @author Humaira Orchee, Charlotte Dye, Sehr Sethi
 * @version April 6, 2015
 */
public class EstimationStartPage extends JPanel {

	private static final String DEFAULT_TEXT = "Default (Non- colorblind)";

	private static final String RED_BLUE_GREEN_TEXT = "Red-Blue-Green Colorblindness";

	private static final String RED_GREEN_TEXT = "Red-Green Colorblindness";

	private static final String BLUE_YELLOW_TEXT = "Blue-Yellow Colorblindness";

	private String mode = DEFAULT_TEXT;
	
	private BeetleKit beetleKit ;

	// colors in default mode
	private Color infestedColor = Color.RED;

	private Color nonInfestedColor = Color.GREEN;

	/**
	 * Creates the Start Page
	 * @param beetleKit TODO
	 */
	public EstimationStartPage(BeetleKit beetleKit) {
		
		this.beetleKit = beetleKit ;

		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);

		this.setLayout(boxLayout);

		// this.setLayout(new BorderLayout());

		addText();

		addComboBox();

		addStartButton();
		
		addResumeButton();

	}

	/**
	 * Asks the user to choose a mode
	 */
	private void addText() {

		JTextArea label = new JTextArea(
				"Are you colorblind? If so, select the type of colorblindness you have.");

		label.setEditable(false);
		label.setFocusable(false);

		// add(label, BorderLayout.NORTH) ;

		add(label);
	}

	/**
	 * Lets the user choose a mode from a JComboBox
	 */
	private void addComboBox() {

		JPanel panel = new JPanel(new BorderLayout());

		String[] comboChoices = { DEFAULT_TEXT, RED_BLUE_GREEN_TEXT,
				RED_GREEN_TEXT, BLUE_YELLOW_TEXT };

		final JComboBox<String> comboBox = new JComboBox<String>(comboChoices);

		comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		comboBox.setAlignmentY(Component.CENTER_ALIGNMENT);

		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.setAlignmentY(Component.CENTER_ALIGNMENT);

		comboBox.setSelectedIndex(0);

		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				System.out.println("item changed");

				mode = (String) comboBox.getSelectedItem();

				System.out.println("mode " + mode);

				infestedColor = selectInfestedColor(mode);

				nonInfestedColor = selectNonInfestedColor(mode);

			}
		});

		// add(comboBox, BorderLayout.CENTER) ;

		panel.add(comboBox);

		add(panel);
	}

	/**
	 * Adds a "Start" button. Clicking on this button lets the user resume the
	 * game.
	 */
	private void addStartButton() {

		JButton startButton = new JButton("Start");

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Should be starting now");
				
				// create the game only when the user wants to start playing. otherwise the colors are not chosen properly
				beetleKit.createEstimationGame();
				
				beetleKit.nextPage();

			}
		});

		// add(startButton, BorderLayout.SOUTH);

		add(startButton);
	}
	
	/**
	 * Adds a "Resume" button. Clicking on this button lets the user resume the
	 * game. 
	 * 
	 * TODO : Might need to read from database whether or not the user can resume. Changes in other classes will have to be made if we decide to add this feature
	 */
	private void addResumeButton() {

		JButton resumeButton = new JButton("Resume");

		resumeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Resume");
				
			
			}
		});



		add(resumeButton);
	}

	/**
	 * Sets the color of the infested trees according to the mode the user chose
	 * 
	 * @param mode
	 *            The default mode or one of the colorblind modes
	 * @return The color of the infested trees according to the mode
	 */
	private Color selectInfestedColor(String mode) {

		if (mode.equals(DEFAULT_TEXT)) {

			return Color.RED;

		} else if (mode.equals(RED_BLUE_GREEN_TEXT)) {

			// hex value : 6094ff
			return new Color(96, 148, 255);

		} else if (mode.equals(RED_GREEN_TEXT)) {

			// hex value : 008ce2
			return new Color(0, 140, 226);

		} else if (mode.equals(BLUE_YELLOW_TEXT)) {

			// hex value : fe1c00
			return new Color(254, 28, 0);
		}

		else
			throw new AssertionError("There should only be 4 modes. Mode is "
					+ mode);

	}

	/**
	 * Sets the color of the non-infested trees according to the mode the user
	 * chose
	 * 
	 * @param mode
	 *            The default mode or one of the colorblind modes
	 * @return The color of the non-infested trees according to the mode
	 */
	private Color selectNonInfestedColor(String mode) {

		if (mode.equals(DEFAULT_TEXT)) {

			return Color.GREEN;

		} else if (mode.equals(RED_BLUE_GREEN_TEXT)) {

			// hex value : ffe41c
			return new Color(255, 228, 28);

		} else if (mode.equals(RED_GREEN_TEXT)) {

			// hex value : f6c600
			return new Color(246, 198, 0);

		} else if (mode.equals(BLUE_YELLOW_TEXT)) {

			// hex value : 7aedff
			return new Color(122, 237, 255);
		}

		else
			throw new AssertionError("There should only be 4 modes. Mode is "
					+ mode);
	}

	public Color getInfestedColor() {
		return infestedColor;
	}

	public Color getNonInfestedColor() {
		return nonInfestedColor;
	}

	public static void main(String[] args) {

		// Create a JFrame for the application and give it a size and close
		// operation
		JFrame frame = new JFrame("Estimation Start Page");
		frame.setSize(605, 671);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		frame.getContentPane().add(new EstimationStartPage(this));

		// Make the frame visible
		frame.setVisible(true);

		frame.setResizable(false);

		// close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
