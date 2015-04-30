package beetle_kit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * This class is a panel that lets users type their answers for the estimation
 * game and allows them to view the full unblocked grid, as well as the correct
 * calculation and estimation for the given grid.
 * 
 * @author Humaira Orchee, Charlotte Dye, and Sehr Sethi
 * @version April 30, 2015
 *
 */
public class AnswerPanel extends JPanel {

	// User answers count as correct if they fall between [correct answer] -
	// ERROR_BARS and [correct answer] + ERROR_BARS. This way, users can be off
	// slightly in their estimations without penalty.
	private static final int ERROR_BARS = 10;

	// the number by which number if infested / non-infested trees will be
	// multiplied. E.g., if only 1/3 of the cells are unblocked, they
	// should multiply their count by 3 (MULTIPLY) to get the correct
	// estimation.
	private static final int MULTIPLY = 3;

	// Information about the estimation grid
	private final EstimationGrid estimationGrid;

	// Visual representation of the estimation grid
	private final GridView gridView;

	// Whether they have correctly guessed the number of non-infested trees
	private boolean nonInfCorrect;

	// Whether they have correctly guessed the number of infested trees
	private boolean infCorrect;

	// The EstimationGame object that controls the estimation game
	private final EstimationGame controller;

	/**
	 * Constructs a panel that lets users input their answers, checks their
	 * answers, lets them view the full, unblocked grid, and lets them see the
	 * correct estimation.
	 * 
	 * @param grid
	 *            The information about the Estimation Grid
	 * @param gridView
	 *            The visual representation of the Estimation Grid
	 * @param controller
	 *            The EstimationGame object that controls the game
	 */
	public AnswerPanel(EstimationGrid grid, GridView gridView,
			EstimationGame controller) {

		this.estimationGrid = grid;
		this.controller = controller;
		this.gridView = gridView;

		// Initially, the user hasn't guessed anything correctly
		nonInfCorrect = false;
		infCorrect = false;

		// Set the layout and border of the answer panel
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 1));
		Border border = BorderFactory.createEmptyBorder(7, 7, 7, 7);
		setBorder(border);

		// Create the panel for users' answer
		estimatePanel();

		// button that shows full unblocked grid
		addShowButton();

	}

	/**
	 * Creates and adds abutton that allows the users to input answers in a
	 * pop-up. The pop-up also tells them how to calculate answers.
	 */
	private void estimatePanel() {

		// Create the button
		JButton estimateButton = new JButton("Ready to Estimate!");

		estimateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// pop-up that allows users to input answers.
				createPopUp();

			}
		});

		// Add the button to the answer panel
		add(estimateButton);
	}

	/**
	 * Creates a pop-up that allows the users to input their estimates for
	 * infested and non-infested trees
	 */
	private void createPopUp() {

		// layout of the panel that will pop up
		JPanel panel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxLayout);

		// Create the sub answer panel for infested trees
		createSubAnswerPanel(panel, "Infested");

		// create space between the infested and non-infested sub answer panels
		panel.add(new JPanel());
		panel.add(new JPanel());

		// Create the sub answer panel for non-infested trees
		createSubAnswerPanel(panel, "Non-Infested");

		// Make a popup that displays the panel, with no buttons other than
		// those in the panel
		JOptionPane.showOptionDialog(gridView, panel, "Input Answer",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				new Object[] {}, null);

	}

	/**
	 * Allows user to input estimates for either infested trees or non-infested
	 * trees
	 * 
	 * @param panel
	 *            The panel to add all the components (like JTextFields) to
	 * @param treeType
	 *            Indicates whether the sub-answer panel is for infested or
	 *            non-infested trees
	 */
	private void createSubAnswerPanel(final JPanel panel, final String treeType) {

		// Create the panel to add all the components to. This panel will later
		// be added to the parameter "panel".
		JPanel treePanel = new JPanel(new GridBagLayout());

		// Create a titled border for treePanel
		TitledBorder border = BorderFactory.createTitledBorder(treeType
				+ " Tree");
		border.setTitleFont((new Font("Dialog", Font.BOLD, 15)));
		treePanel.setBorder(border);

		// The border that will be added to each of the components
		Border componentBorder = BorderFactory.createLineBorder(new Color(0, 0,
				0, 0), 10);

		// label for the number of trees that are visible in the unblocked cells
		JLabel countLabel = new JLabel("How many " + treeType.toLowerCase()
				+ " trees do you see?");
		countLabel.setBorder(componentBorder);

		// Create the GridBag constraints for countLabel
		GridBagConstraints countLabelConstraints = new GridBagConstraints();
		countLabelConstraints.gridx = 0;
		countLabelConstraints.gridy = 0;
		countLabelConstraints.anchor = GridBagConstraints.LINE_START;

		// Add the count label to treePanel
		treePanel.add(countLabel, countLabelConstraints);

		// text field where users can input answers about the number of visible
		// trees of treeTye they see in the unblocked cells
		final JTextField countAnswerField = new JTextField(5);

		// Create the GridBag constraints for countAnswerField
		GridBagConstraints countFieldConstraints = new GridBagConstraints();
		countFieldConstraints.gridx = 1;
		countFieldConstraints.gridy = 0;

		// Add countAnswerField to treePanel
		treePanel.add(countAnswerField, countFieldConstraints);

		// text area for entering the number that users should multiply their
		// answers by
		JTextArea multiplyArea = new JTextArea(
				"What number should you multiply the \nnumber of "
						+ treeType.toLowerCase()
						+ " trees by? \n(Remember that only 1/3 of the grid is \nvisible)");

		// Set up the layout for multiplyArea
		multiplyArea.setEditable(false);
		multiplyArea.setBackground(new Color(0, 0, 0, 0));
		multiplyArea.setFont(new Font("Dialog", Font.BOLD, 12));
		multiplyArea.setBorder(componentBorder);
		multiplyArea.setFocusable(false);

		// Create the GridBag constraints for multiplyArea
		GridBagConstraints multiplyAreaConstraints = new GridBagConstraints();
		multiplyAreaConstraints.gridx = 0;
		multiplyAreaConstraints.gridy = 1;
		multiplyAreaConstraints.anchor = GridBagConstraints.LINE_START;

		// Add multiplyArea to treePanel
		treePanel.add(multiplyArea, multiplyAreaConstraints);

		// Field for users to input the number they should multiply the visible
		// number of
		// trees by in order to get the final estimate
		final JTextField multiplyField = new JTextField(5);

		// Create the GridBag constraints for multiplyField
		GridBagConstraints multiplyFieldConstraints = new GridBagConstraints();
		multiplyFieldConstraints.gridx = 1;
		multiplyFieldConstraints.gridy = 1;
		multiplyAreaConstraints.anchor = GridBagConstraints.LINE_START;

		// Add multiplyField to treePanel
		treePanel.add(multiplyField, multiplyFieldConstraints);

		// text area for the final estimate
		JTextArea estimateArea = new JTextArea("How many "
				+ treeType.toLowerCase()
				+ " trees do you think\nare in the grid?");

		// Set up the layout for estimateArea
		estimateArea.setEditable(false);
		estimateArea.setBackground(new Color(0, 0, 0, 0));
		estimateArea.setFont(new Font("Dialog", Font.BOLD, 12));
		estimateArea.setBorder(componentBorder);
		estimateArea.setFocusable(false);

		// Create the GridBag constraints for multiplyField
		GridBagConstraints finalAnswerAreaConstraints = new GridBagConstraints();
		finalAnswerAreaConstraints.gridx = 0;
		finalAnswerAreaConstraints.gridy = 2;
		finalAnswerAreaConstraints.anchor = GridBagConstraints.LINE_START;

		// Add estimateArea to treePanel
		treePanel.add(estimateArea, finalAnswerAreaConstraints);

		// Field where users can input their estimate
		final JTextField estimateField = new JTextField(5);

		// Set up the GridBag constraints for estimateField
		GridBagConstraints finalAnswerFieldConstraints = new GridBagConstraints();
		finalAnswerFieldConstraints.gridx = 1;
		finalAnswerFieldConstraints.gridy = 2;

		// Add estimateField to treePanel
		treePanel.add(estimateField, finalAnswerFieldConstraints);

		// creates a panel with buttons for checking user answers and showing
		// them how to do the calculation
		createAnswerButtonPanel(panel, treeType, treePanel, countAnswerField,
				multiplyField, estimateField);

		// Add this tree panel to the panel passed in as a parameter
		panel.add(treePanel);
	}

	/**
	 * Creates buttons for checking user answers and showing them how to do the
	 * calculation
	 * 
	 * @param popupPanel
	 *            The panel to add all the pop-up dialogue boxes to give
	 *            feedback to users about their answers to
	 * @param treeType
	 *            Indicates if the buttons are for the infested trees or
	 *            non-infested trees
	 * @param treePanel
	 *            The panel to add the buttons to
	 * @param countAnswerField
	 *            JTextField that stores the user's count of the number of trees
	 *            that are visible in the unblocked cells of treeType
	 * @param multiplyField
	 *            JTextField that stores the user's answer about the number by
	 *            which they should multiply their count
	 * @param estimateAnswerField
	 *            JTextField that stores the user's estimate of the total number
	 *            of trees in the grid of treeType
	 */
	private void createAnswerButtonPanel(final JPanel popupPanel,
			final String treeType, JPanel treePanel,
			final JTextField countAnswerField, final JTextField multiplyField,
			final JTextField estimateAnswerField) {

		// show how to estimate button
		createHowToEstimateButton(popupPanel, treeType, treePanel);

		// check answer button
		createCheckAnswerButton(popupPanel, treeType, treePanel,
				countAnswerField, multiplyField, estimateAnswerField);

	}

	/**
	 * Creates button showing users how to do the calculation
	 * 
	 * @param popupPanel
	 *            The panel to add all the pop-up dialogues giving feedback to
	 *            users about their answers to
	 * @param treeType
	 *            Indicates if the buttons are for the infested trees or
	 *            non-infested trees
	 * @param treePanel
	 *            The panel to add the buttons to
	 **/
	private void createHowToEstimateButton(final JPanel popupPanel,
			final String treeType, JPanel treePanel) {

		// button that shows user how to make the estimation
		JButton showAnswerButton = new JButton(
				"Show me how to estimate the trees!");

		// Create the GridBag constraints for showAnswerButton
		GridBagConstraints showAnswerButtonConstraints = new GridBagConstraints();
		showAnswerButtonConstraints.gridx = 0;
		showAnswerButtonConstraints.gridy = 3;
		showAnswerButtonConstraints.insets = new Insets(5, 5, 5, 5);

		// Add the action listener for the show answer button
		showAnswerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Note that the user has viewed the answer (this is important
				// so that this round doesn't count as a completed round for the
				// purposes of the reward)
				controller.setViewedAnswerToTrue();

				// Determine that actual number of visible trees of the given
				// type
				int actualTreeCount = getActualTreeCount(treeType);

				// the actual (i.e., correct given the method of estimating and
				// the number of visible trees) estimate of the number of trees
				// of treeType in the grid
				int actualEstimate = actualTreeCount * MULTIPLY;

				// The text of the popup that shows the users how to estimate
				// the trees
				String answer = "There are actually "
						+ actualTreeCount
						+ " "
						+ treeType.toLowerCase()
						+ " trees visible in the grid. To get the total number of trees, you should do the following: "
						+ actualTreeCount + " * " + MULTIPLY + " = "
						+ actualEstimate;

				// Create the popup
				JOptionPane.showMessageDialog(popupPanel, answer);

			}

		});

		// Add the showAnswerButton to the treePanel
		treePanel.add(showAnswerButton, showAnswerButtonConstraints);
	}

	/**
	 * Get the actual number of visible (i.e., non-blocked) trees of type
	 * treeType
	 * 
	 * @param treeType
	 *            Type of tree (infested or non-infested)
	 * @return Number of visible trees of given type
	 * @throws AssertionError
	 */
	private int getActualTreeCount(final String treeType) throws AssertionError {
		// The number of trees actually visible in the grid given the
		// treeType
		int actualTreeCount = 0;

		// If the trees are infested, use the appropriate method
		if (treeType.equals("Infested")) {

			actualTreeCount = estimationGrid.getTotalUnblockedInfested();

		} else if (treeType.equals("Non-Infested")) {

			// If the trees are non-infested, use the appropriate method
			actualTreeCount = estimationGrid.getTotalUnblockedNonInfested();

		} else {

			// Otherwise, something has gone wrong (those are the only
			// two types of trees)
			throw new AssertionError(
					"the string should only have been Infested or Non-Infested");
		}
		return actualTreeCount;
	}

	/**
	 * Creates button for checking user answers
	 * 
	 * @param popupPanel
	 *            The panel to add all the pop-ups with feedback to the user to
	 * @param treeType
	 *            Indicates if the buttons are for the infested trees or
	 *            non-infested trees
	 * @param treePanel
	 *            The panel to add the buttons to
	 * @param countAnswerField
	 *            JTextField that stores the user's count of the number of trees
	 *            that are visible in the unblocked cells of treeType
	 * @param multiplyField
	 *            JTextField that stores the user's answer about the number by
	 *            which they should multiply their count
	 * @param estimateAnswerField
	 *            JTextField that stores the user's estimate of the total number
	 *            of trees in the grid of treeType
	 */
	private void createCheckAnswerButton(final JPanel popupPanel,
			final String treeType, JPanel treePanel,
			final JTextField countAnswerField, final JTextField multiplyField,
			final JTextField estimateAnswerField) {

		// button that checks the user's answer
		JButton checkButton = new JButton("Check my Answer!");

		// Create the GridBag constraints for checkButton
		GridBagConstraints checkButtonConstraints = new GridBagConstraints();
		checkButtonConstraints.gridx = 1;
		checkButtonConstraints.gridy = 3;
		checkButtonConstraints.insets = new Insets(5, 5, 5, 5);

		// Add checkButton to the tree panel
		treePanel.add(checkButton, checkButtonConstraints);

		// Add the action listener for checkButton
		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					// how many visible trees did the user count in the
					// unblocked cells?
					int userCountAnswer = Integer.parseInt(countAnswerField
							.getText());

					// what number is the user trying to multiply the number of
					// visible trees by?
					int userMultiplyAnswer = Integer.parseInt(multiplyField
							.getText());

					// if the user is not trying to multiply by the correct
					// answer, their answer is wrong: pop up a message informing
					// them
					if (userMultiplyAnswer != MULTIPLY) {

						JOptionPane
								.showMessageDialog(
										popupPanel,
										"Are you sure you should multiply by "
												+ userMultiplyAnswer
												+ "? Try again! Hint: Remember that only one-third of the grid is visible.");

						// If they multiply by the wrong number, it won't
						// check if the result is correct or not, so don't
						// continue
						return;
					}

					// how many trees does the user estimate is in the the grid?
					int userEstimateAnswer = Integer
							.parseInt(estimateAnswerField.getText());

					// if the user's multiplication is wrong, then their answer
					// is wrong, so inform them of this
					if ((userCountAnswer * userMultiplyAnswer) != userEstimateAnswer) {

						JOptionPane.showMessageDialog(popupPanel,
								userCountAnswer + " * " + userMultiplyAnswer
										+ " does not equal "
										+ userEstimateAnswer + ". Try again!");

						// If they multiply wrong, it won't
						// check if the result is correct or not, so don't
						// continue
						return;
					}

					// depending on the treeType, how many trees are actually
					// visible in the grid?
					int actualCountAnswer = getActualTreeCount(treeType);

					// what should the actual estimate be?
					int actualEstimateAnswer = actualCountAnswer * MULTIPLY;

					// check if the user's answer is within the error bars. Let
					// the user know if they got the correct answer or not and
					// remember it.
					if (calculateRightAnswer(userEstimateAnswer,
							actualEstimateAnswer)) {

						// Tell the user they got the right answer
						JOptionPane.showMessageDialog(popupPanel,
								"You got the correct answer! Good job!");

						// If they guessed infested trees, note that they got
						// that correct
						if (treeType.equals("Infested")) {
							infCorrect = true;
						}
						// If they guessed non-infested trees, note that they
						// got that correct
						else {
							nonInfCorrect = true;
						}

						// If they have gotten both parts correct, let them know
						// and advance to the next round
						if (infCorrect && nonInfCorrect) {

							// Get rid of all the pop-ups
							removeJOptionPanes();

							// Inform them that they got both correct
							JOptionPane
									.showMessageDialog(
											popupPanel,
											"You got the number of infested and non-infested"
													+ " trees correct!  Good job!  Time to try the next round.");

							// Continue to the next round
							controller.displayNewGrid();
						}

					} else {
						// In this case, they got the answer wrong

						// Let them know they got the wrong answer
						JOptionPane.showMessageDialog(popupPanel,
								"Sorry but your answer is wrong. Try again!");

					}

				} catch (NumberFormatException exception) {

					// if users try to input anything but a number, let them
					// know that they should type a number

					JOptionPane.showMessageDialog(popupPanel,
							"Please enter a number");

				}

			}
		});
	}

	/**
	 * Creates a button that creates a pop-up that lets users see the full grid.
	 */
	private void addShowButton() {

		// clicking on this button allows user to see the full grid
		JButton showButton = new JButton("Show full grid");

		// Add showButton to the AnswerPanel
		add(showButton);

		// Add the action listener for showButton
		showButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// a new visual representation of the grid is created to see the
				// grid without any cells being blocked
				GridView fullGridView = new GridView(estimationGrid
						.getGridCells(), gridView.getTrees(), estimationGrid
						.getCellWidth(), estimationGrid.getCellHeight());

				// this view has no blocked cells
				fullGridView.setHasBlockedCells(false);

				// gets the array list of trees with their coordinates and
				// dimensions
				fullGridView.setTrees(gridView.getTrees());

				// the new panel for the full grid view and text showing actual
				// number of infested and non-infested trees
				JPanel fullGridPanel = new JPanel(new BorderLayout());
				fullGridPanel.add(fullGridView, BorderLayout.CENTER);

				// text showing the number of infested and non-infested trees
				JTextArea treeInfo = new JTextArea("There are "
						+ estimationGrid.getTotalInfested()
						+ " infested trees." + "\n" + "There are "
						+ estimationGrid.getTotalNonInfested()
						+ " non-infested trees.");

				// font for this text
				Font font = new Font("Times New Roman", Font.PLAIN, 18);
				treeInfo.setFont(font);
				treeInfo.setEditable(false);

				// add text to panel
				fullGridPanel.add(treeInfo, BorderLayout.SOUTH);

				// Set the preferred and minimum sizes of the full panel to be
				// slightly bigger than the grid itself
				fullGridPanel.setPreferredSize(new Dimension(
						EstimationGrid.GRID_WIDTH,
						EstimationGrid.GRID_HEIGHT + 50));
				fullGridPanel.setMinimumSize(new Dimension(
						EstimationGrid.GRID_WIDTH,
						EstimationGrid.GRID_HEIGHT + 50));

				// Show the popup with the full view
				JOptionPane.showOptionDialog(gridView, fullGridPanel,
						"Full Grid", JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, new Object[] {}, null);

				// Repaint to make sure everything shows correctly
				gridView.repaint();

			}
		});

	}

	/**
	 * Returns true if the user's answers are within the error bars
	 * 
	 * @param userAnswer
	 *            The answer (estimate) that the user has typed in
	 * @param actualAnswer
	 *            The actual answer (estimate) that is calculated from the grid
	 * @return Whether or not the user got the correct answer
	 */
	private boolean calculateRightAnswer(int userAnswer, int actualAnswer) {

		// Calculate the minimum acceptable answer
		int userAnswerMin = userAnswer - ERROR_BARS;

		// the allowed answer cannot be negative
		if (userAnswer < 0) {
			userAnswerMin = 0;
		}

		// The answer counts as correct if it is between userAnswerMin and
		// actualAnswer + ERROR_BARS
		return ((actualAnswer >= userAnswerMin) && (actualAnswer <= userAnswer
				+ ERROR_BARS));
	}

	/**
	 * Removes a JOptionPane and its children
	 * 
	 * http://stackoverflow.com/questions/18105598/closing-a-joptionpane-
	 * programatically
	 */
	private void removeJOptionPanes() {

		Window[] windows = Window.getWindows();

		for (Window window : windows) {

			if (window instanceof JDialog) {

				JDialog dialog = (JDialog) window;

				if (dialog.getContentPane().getComponentCount() == 1
						&& dialog.getContentPane().getComponent(0) instanceof JOptionPane) {

					dialog.dispose();
				}
			}
		}

	}

}
