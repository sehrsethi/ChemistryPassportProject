package beetle_kit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * The first page of the estimation game, which displays the instructions and
 * lets the user choose the default mode or one of the three color-blind modes
 * and allows them to start playing the game
 * 
 * @author Humaira Orchee, Charlotte Dye, and Sehr Sethi
 * @version April 30, 2015
 */
public class EstimationStartPage extends JPanel {

	// The text for the default mode and each colorblindness mode
	private static final String DEFAULT_TEXT = "Default (Non-colorblind)";
	private static final String RED_BLUE_GREEN_TEXT = "Red-Blue-Green Colorblindness";
	private static final String RED_GREEN_TEXT = "Red-Green Colorblindness";
	private static final String BLUE_YELLOW_TEXT = "Blue-Yellow Colorblindness";

	// The colorblindness mode selected (by default, non-colorblind mode)
	private String mode = DEFAULT_TEXT;

	// The kit this start page belongs to
	private BeetleKit beetleKit;

	// colors in default mode
	private Color infestedColor = Color.RED;
	private Color nonInfestedColor = Color.GREEN;

	/**
	 * Creates the Start Page with instructions for the Beetle Kit
	 * 
	 * @param beetleKit
	 *            The kit this start page belongs to
	 */
	public EstimationStartPage(BeetleKit beetleKit) {

		this.beetleKit = beetleKit;

		// Set the layout
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxLayout);

		// Add the instruction text
		addText();

		// Add a panel for spacing
		add(new JPanel());

		// Add the drop-down menu for colorblindness mode selection
		addComboBox();

		// Add panels for spacing
		add(new JPanel());
		add(new JPanel());

		// Add the button to start the kit
		addStartButton();

	}

	/**
	 * Creates instructions and asks the user to choose a mode
	 */
	private void addText() {

		// The panel with the instruction text
		JPanel textPanel = new JPanel();

		// The instruction text
		String instructionsText = "INTRODUCTION TO THE GAME:"
				+ "\n"
				+ "\n"
				+ "Bark beetles are a natural predator of trees and form an important part of the cycle of life."
				+ " Sometimes, when there are too many bark beetles, they are a threat to wildlife, trees, and homes."
				+ " When too many trees are infected with bark beetles, forest animals lose their homes and food."
				+ " Furthermore, the risk of forest fires increases."
				+ " Knowing how many trees are infected is important to understanding"
				+ " whether the number of bark beetle larvae is growing or shrinking."
				+ "\n"
				+ "\n"
				+ "In this kit, you'll learn how to estimate how many trees are infected and how many are healthy."
				+ " You'll also learn about how beetles use pheromones to attract each other. "
				+ "\n"
				+ "\n"
				+ "\n"
				+ "ESTIMATING THE SIZE OF THE PROBLEM"
				+ "\n"
				+ "\n"
				+ "Scientists can't always measure everything exactly in the time given."
				+ " When they can't, they will often use estimation to get a good answer."
				+ " In this game, we need to count the \"infested crowns\" before the bark beetle infected trees lose their needles."
				+ " We could take a picture of an infected forest and count the number of trees with \"infested\" "
				+ " crowns and \"non-infested\" crowns"
				+ " to get an exact number of dying and healthy trees."
				+ " However, for a large forest, this would take a while."
				+ " Instead, we will estimate using this grid. "
				+ " To make the grid, we started with a picture of a section of infected and healthy trees,"
				+ " put a grid on top of it and covered 2 out of every 3 squares of the picture to help speed the counting."
				+ "\n"
				+ "\n"
				+ "\n"
				+ "INSTRUCTIONS ON HOW TO PLAY THE GAME:"
				+ "\n"
				+ "\n"
				+ "To play the bark beetle infestation game, we need to set up rules that tell us what to count and how to count."
				+ "\n"
				+ "\n"
				+ "Rule 1: What to count "
				+ "\n"
				+ "Infested crowns and non-infested crowns in each picture."
				+ "\n"
				+ "\n"
				+ "Rule 2: How to count"
				+ "\n"
				+ "If a block contains the center of a tree crown, add one to the count. "
				+ "If it has less than the center of a tree crown, don't add one to the count."
				+ " Sometimes the picture in the block isn't clear. Just make a your best guess!"
				+ " Write the number of infested and non-infested crowns you counted in the appropriate answer area. "
				+ "\n"
				+ "\n"
				+ "Rule 3: How to Estimate"
				+ "\n"
				+ "Since only two out of every threesquares is visible, you are only seeing one third of the area. "
				+ "To estimate how many infested crowns are in the whole grid, add the number you got for a third of the picture three times. "
				+ "Do the same to get an estimate of the number of non-infested crowns in the grid."
				+ " Try getting the estimate by clicking on the check estimate button."
				+ " Your estimates will be different but similar. "
				+ " The differences highlight that estimation is approximate."
				+ "\n" + "\n";

		// Set up and format the text area with the instructions text
		JTextArea instructionsArea = new JTextArea(instructionsText, 50, 40);
		instructionsArea.setLineWrap(true);
		instructionsArea.setWrapStyleWord(true);
		instructionsArea.setFont(new Font("Calisto MT", Font.PLAIN, 17));

		// Users cannot modify the instructions
		instructionsArea.setEditable(false);
		instructionsArea.setFocusable(false);

		// Add the instructions text area to the text panel and set the
		// background
		textPanel.add(instructionsArea);
		textPanel.setBackground(Color.WHITE);

		// add the scroll pane (the instructions text will scroll when
		// necessary)
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

		// The panel with the combo box
		JPanel panel = new JPanel(new BorderLayout());

		// The label prompting the user to pick a mode
		JTextArea label = new JTextArea(
				"Are you colorblind? If so, select the type of colorblindness you have.");
		label.setFont(new Font("Calisto MT", Font.BOLD | Font.ITALIC, 17));
		label.setBackground(new Color(0, 0, 0, 0));
		label.setFocusable(false);

		// Add the label to the panel
		panel.add(label, BorderLayout.NORTH);

		// The text of the different options in the drop-down menu
		String[] comboChoices = { DEFAULT_TEXT, RED_BLUE_GREEN_TEXT,
				RED_GREEN_TEXT, BLUE_YELLOW_TEXT };

		// The drop down menu
		final JComboBox<String> comboBox = new JComboBox<String>(comboChoices);

		// Set up the combo box display
		comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		comboBox.setAlignmentY(Component.CENTER_ALIGNMENT);
		comboBox.setFont(new Font("Calisto MT", Font.PLAIN, 14));

		// Align the panel
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.setAlignmentY(Component.CENTER_ALIGNMENT);

		// By default, the default option is shown in the drop-down menu
		comboBox.setSelectedIndex(0);

		// Add the drop-down listener
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				// Update the mode to correspond to their selection
				mode = (String) comboBox.getSelectedItem();

				// Update the colors to be consistent with the mode
				infestedColor = selectInfestedColor(mode);
				nonInfestedColor = selectNonInfestedColor(mode);

			}
		});

		// Add the drop-down menu to the panel
		panel.add(comboBox, BorderLayout.CENTER);

		// Add the panel
		add(panel);
	}

	/**
	 * Adds a "Start" button. Clicking on this button lets the user start the
	 * game.
	 */
	private void addStartButton() {

		// The start button
		JButton startButton = new JButton("Start");

		// Set up how button will look
		startButton.setFont(new Font("Calisto MT", Font.BOLD, 18));
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Add the action listener for the start button
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// create the game when the user is ready to start playing
				beetleKit.createEstimationGame();
				beetleKit.nextPage();

			}
		});

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

		// Set the infested tree color according to the mode

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

		// These are the only acceptable modes, so throw an error if we get here
		else {
			throw new AssertionError("There should only be 4 modes. Mode is "
					+ mode);
		}

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

		// Set the non-infested tree color according to the mode

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

		// If we've gotten here, they have a mode that shouldn't exist, so throw
		// an error
		else {
			throw new AssertionError("There should only be 4 modes. Mode is "
					+ mode);
		}
	}

	/**
	 * Returns the color of infested trees
	 * 
	 * @return Color of infested trees
	 */
	public Color getInfestedColor() {
		return infestedColor;
	}

	/**
	 * Returns the color of non-infested trees
	 * 
	 * @return Color of non-infested trees
	 */
	public Color getNonInfestedColor() {
		return nonInfestedColor;
	}

}
