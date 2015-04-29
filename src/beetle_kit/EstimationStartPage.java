package beetle_kit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

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

	// The main instructions for the Beetle game
	//private String instructionsText;

	// The colorblindness mode (by default, non-colorblind mode)
	private String mode = DEFAULT_TEXT;

	private BeetleKit beetleKit;

	// colors in default mode
	private Color infestedColor = Color.RED;

	private Color nonInfestedColor = Color.GREEN;

	/**
	 * Creates the Start Page
	 * 
	 * @param beetleKit
	 *            TODO
	 */
	public EstimationStartPage(BeetleKit beetleKit) {

		this.beetleKit = beetleKit;

		//this.setLayout(new BorderLayout());
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);

		this.setLayout(boxLayout);

		// this.setLayout(new BorderLayout());

		addText();

		addComboBox();

		addStartButton();

	}

	/**
	 * Creates instructions and asks the user to choose a mode
	 */
	private void addText() {

		// The instructions for the game

		JPanel textPanel = new JPanel();

		//BoxLayout boxLayout = new BoxLayout(textPanel, BoxLayout.Y_AXIS);
		//textPanel.setLayout(boxLayout);

		String instructionsText = "INTRODUCTION TO THE GAME:"
				+ "\n"
				+ "\n"
				+ "Bark beetles are a natural predator of trees and form an important part of the cycle of life."

				+ " Sometimes, when there are too many bark beetles, they are a threat to wildlife, trees, and homes."

				+ "When too many trees are infected with bark beetles, forest animals lose their homes and food."

				+ "Furthermore, the risk of forest fires increases."
				+ " Knowing how many trees are infected is important to understanding"

				+ "whether the number of bark beetle larvae is growing or shrinking."

				+ "\n"

				+ "In this kit, you'll learn how to estimate how many trees are infected and how many are healthy."

				+ "You'll also learn about how beetles use pheromones to attract each other. "

				+ "\n"
				+ "\n"
				+ "Estimating the size of the problem"
				+"\n"
				+ "\n"
				+ "Scientists can't always measure everything exactly in the time given."

				+ "When they can't, they will often use estimation to get a good answer."

				+ "In this game, we need to count the \"infested crowns\" before the bark beetle infected trees lose their needles."

				+ "We could take a picture of an infected forest and count the number of trees with \"infested\" "
				+ "crowns and \"non-infested\" crowns"

				+ "to get an exact number of dying and healthy trees."

				+ "However, for a large forest, this would take a while."

				+ "Instead, we will estimate using this grid. "

				+ "To make the grid, we started with a picture of a section of infected and healthy trees,"

				+ " put a grid on top of it and covered 2 out of every 3 squares of the picture to help speed the counting."

				+ "\n"
				+ "\n"
				+ "Instructions on how to play the game:"
				+ "\n"

				+ "To play the bark beetle infestation game, we need to set up rules that tell us what to count and how to count."

				+ "\n"
				+ "Rule 1: What to count: "
				+ "Infested crowns and non-infested crowns in each picture."

				+ "Rule 2: How to count:"
				+ " If a block contains the center of a tree crown, add one to the count. "

				+ "If it has less than the center of a tree crown, don't add one to the count."

				+ "Sometimes the picture in the block isn't clear. Just make a your best guess!"

				+ "Write the number of infested and non-infested crowns you counted in the appropriate answer area. "

				+ "Since only two out of every threesquares is visible, you are only seeing one third of the area. "

				+ "To estimate how many red crowns are in the whole grid, add the number you got for a third of the picture three times. "

				+ "Do the same to get an estimate of the number of green crowns in the grid."

				+ "Try getting the estimate by clicking on the check estimate button."
				+ "Your estimates will be different but similar. "
				+ "The differences highlight that estimation is approximate.";

		//JEditorPane instructionsArea = new JEditorPane("text/html", "");
		//instructionsArea.setText(instructionsText);
		JTextArea instructionsArea = new JTextArea(instructionsText, 50, 40);

		instructionsArea.setLineWrap(true);
		instructionsArea.setWrapStyleWord(true);

		// Users cannot modify the instructions
		instructionsArea.setEditable(false);
		instructionsArea.setFocusable(false);
		
		instructionsArea.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// Add the instructions text area
		textPanel.add(instructionsArea);
		
		textPanel.setBackground(Color.WHITE);


		// add the scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(textPanel);

		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPane);

	}

	/**
	 * Lets the user choose a mode from a JComboBox
	 */
	private void addComboBox() {

		JPanel panel = new JPanel(new BorderLayout());

		// The color-blindness settings
		JTextArea label = new JTextArea(
				"Are you colorblind? If so, select the type of colorblindness you have.");

		label.setFocusable(false);

		panel.add(label, BorderLayout.NORTH);

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

				mode = (String) comboBox.getSelectedItem();

				infestedColor = selectInfestedColor(mode);

				nonInfestedColor = selectNonInfestedColor(mode);

			}
		});

		// add(comboBox, BorderLayout.CENTER) ;

		panel.add(comboBox, BorderLayout.CENTER);

		add(panel);
	}

	/**
	 * Adds a "Start" button. Clicking on this button lets the user start the
	 * game.
	 */
	private void addStartButton() {

		JButton startButton = new JButton("Start");

		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// create the game only when the user wants to start playing.
				// otherwise the colors are not chosen properly
				beetleKit.createEstimationGame();

				beetleKit.nextPage();

			}
		});

		// add(startButton, BorderLayout.SOUTH);

		add(startButton);
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

}
