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
 * This class creates a Panel that lets users type their answers and allows them
 * to see the full gird (with no blocked cells).
 * 
 * @author Humaira Orchee and Charlotte Dye
 * @version April 3, 2015
 *
 */
public class AnswerPanel extends JPanel {

	// Allows for some errors in the users' answers
	private static final int ERROR_BARS = 10;

	// the number by which number if infested / non- infested trees will be
	// multiplied
	private static final int MULTIPLY = 3;

	// private JTextField infestedText;

	// private JTextField nonInfestedText;

	// Information about the estimation grid
	private final EstimationGrid estimationGrid;

	// Visual representation of the estimation grid
	private final GridView gridView;

	// Might need to change this
	// Whether they have correctly guessed the number of non-infested trees
	private boolean nonInfCorrect;

	// Whether they have correctly guessed the number of infested trees
	private boolean infCorrect;
	
	private final EstimationGame controller;

	/**
	 * Constructs a panel that lets user's input their answers, checks their
	 * answers and lets them see the full unblocked grid.
	 * 
	 * @param grid
	 *            The information about the Estimation Grid
	 * @param gridView
	 *            The Visual representation of the Estimation Grid
	 * @param controller TODO
	 */
	public AnswerPanel(EstimationGrid grid, GridView gridView, EstimationGame controller) {

		this.controller = controller;
		// Initially, the user hasn't guessed anything correctly
		nonInfCorrect = false;
		infCorrect = false;

		this.estimationGrid = grid;

		this.gridView = gridView;

		// layout and border of the answer panel
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 1));
		Border border = BorderFactory.createEmptyBorder(7, 7, 7, 7);
		setBorder(border);

		// this.add(new JPanel());

		/*
		 * addInfestedText();
		 * 
		 * this.add(new JPanel());
		 * 
		 * addNonInfestedtest();
		 */

		// panel for users' answer
		estimatePanel();

		// button that shows full unblocked grid
		addShowButton();

	}

	/**
	 * Creates a button that allows the users to input answers in a pop-up. The
	 * pop-up also tells them how to calculate answers.
	 */
	private void estimatePanel() {

		JButton estimateButton = new JButton("Ready to Estimate!");

		estimateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// pop-up that allows users to input answers.
				createPopUp();

			}

		});

		add(estimateButton);
	}

	/**
	 * Creates a pop-up that allows the users to input their estimates for
	 * infested and non-infested trees
	 */
	private void createPopUp() {

		// layout of this panel
		JPanel panel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxLayout);

		// sub answer panel for infested trees
		createSubAnswerPanel(panel, "Infested");

		// create space between the infested and non-infested sub answer panels
		panel.add(new JPanel());
		panel.add(new JPanel());

		// sub answer panel for non-infested trees
		createSubAnswerPanel(panel, "Non-Infested");

		// should not have the ok button in the pop-up
		JOptionPane.showOptionDialog(gridView, panel, "Input Answer",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				new Object[] {}, null);

	}

	/**
	 * Allows user to input estimates for either infested trees or non-infested
	 * trees
	 * 
	 * @param panel
	 *            The panel to add all the components (like JTextFields) to add
	 *            to
	 * @param treeType
	 *            Indicates if the sub-answer panel is for infested or
	 *            non-infested trees
	 */
	private void createSubAnswerPanel(final JPanel panel, final String treeType) {

		// The panel to add all the components to. This panel will later be
		// added to the parameter panel.
		JPanel treePanel = new JPanel(new GridBagLayout());
		TitledBorder border = BorderFactory.createTitledBorder(treeType
				+ " Tree");
		border.setTitleFont((new Font("Dialog", Font.BOLD, 15)));
		treePanel.setBorder(border);

		// The border around all the components
		Border componentBorder = BorderFactory.createLineBorder(new Color(0, 0,
				0, 0), 10);

		// label for the number of trees that are visible in the unblocked cells
		JLabel countLabel = new JLabel("How many " + treeType.toLowerCase()
				+ " trees do you see?");
		countLabel.setBorder(componentBorder);

		GridBagConstraints countLabelConstraints = new GridBagConstraints();
		countLabelConstraints.gridx = 0;
		countLabelConstraints.gridy = 0;
		countLabelConstraints.anchor = GridBagConstraints.LINE_START;

		treePanel.add(countLabel, countLabelConstraints);

		// text field where users can input answers about the number of visible
		// trees of treeTye they see in the unblocked cells
		final JTextField countAnswerField = new JTextField(5);

		GridBagConstraints countFieldConstraints = new GridBagConstraints();
		countFieldConstraints.gridx = 1;
		countFieldConstraints.gridy = 0;

		treePanel.add(countAnswerField, countFieldConstraints);

		// text area for the number that users should multiply their answers by
		JTextArea multiplyArea = new JTextArea(
				"What number should you multiply the \nnumber of "
						+ treeType.toLowerCase()
						+ " trees by? \n(Remember that only 1/3 of the grid is \nvisible)");

		multiplyArea.setEditable(false);
		multiplyArea.setBackground(new Color(0, 0, 0, 0));
		multiplyArea.setFont(new Font("Dialog", Font.BOLD, 12));
		multiplyArea.setBorder(componentBorder);
		multiplyArea.setFocusable(false);

		GridBagConstraints multiplyAreaConstraints = new GridBagConstraints();
		multiplyAreaConstraints.gridx = 0;
		multiplyAreaConstraints.gridy = 1;
		multiplyAreaConstraints.anchor = GridBagConstraints.LINE_START;

		treePanel.add(multiplyArea, multiplyAreaConstraints);

		// users can input the number they should multiply the visible number of
		// trees by to get the estimate
		final JTextField multiplyField = new JTextField(5);

		GridBagConstraints multiplyFieldConstraints = new GridBagConstraints();
		multiplyFieldConstraints.gridx = 1;
		multiplyFieldConstraints.gridy = 1;
		multiplyAreaConstraints.anchor = GridBagConstraints.LINE_START;

		treePanel.add(multiplyField, multiplyFieldConstraints);

		// text area for the final estimate
		JTextArea estimateArea = new JTextArea("How many "
				+ treeType.toLowerCase()
				+ " trees do you think\nare in the grid?");

		estimateArea.setEditable(false);
		estimateArea.setBackground(new Color(0, 0, 0, 0));
		estimateArea.setFont(new Font("Dialog", Font.BOLD, 12));
		estimateArea.setBorder(componentBorder);
		estimateArea.setFocusable(false);

		GridBagConstraints finalAnswerAreaConstraints = new GridBagConstraints();
		finalAnswerAreaConstraints.gridx = 0;
		finalAnswerAreaConstraints.gridy = 2;
		finalAnswerAreaConstraints.anchor = GridBagConstraints.LINE_START;

		treePanel.add(estimateArea, finalAnswerAreaConstraints);

		// users can input their estimate
		final JTextField estimateField = new JTextField(5);
		
		GridBagConstraints finalAnswerFieldConstraints = new GridBagConstraints();
		finalAnswerFieldConstraints.gridx = 1;
		finalAnswerFieldConstraints.gridy = 2;

		treePanel.add(estimateField, finalAnswerFieldConstraints);

		// creates buttons for checking user answers and showing them how to do
		// the calculation
		createAnswerButtonPanel(panel, treeType, treePanel, countAnswerField,
				multiplyField, estimateField);

		panel.add(treePanel);
	}

	/**
	 * Creates buttons for checking user answers and showing them how to do the
	 * calculation
	 * 
	 * @param panel
	 *            The panel to add all the pop-ups to
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
	private void createAnswerButtonPanel(final JPanel panel,
			final String treeType, JPanel treePanel,
			final JTextField countAnswerField, final JTextField multiplyField,
			final JTextField estimateAnswerField) {

		// show how to estimate button
		createHowToEstimateButton(panel, treeType, treePanel);

		// check answer button
		createCheckAnswerButton(panel, treeType, treePanel, countAnswerField,
				multiplyField, estimateAnswerField);

	}

	/**
	 * Creates button showing users how to do the calculation
	 * 
	 * @param panel
	 *            The panel to add all the pop-ups to
	 * @param treeType
	 *            Indicates if the buttons are for the infested trees or
	 *            non-infested trees
	 * @param treePanel
	 *            The panel to add the buttons to
	 **/
	private void createHowToEstimateButton(final JPanel panel,
			final String treeType, JPanel treePanel) {
		// button that shows user how to make the estimation
		JButton showAnswerButton = new JButton(
				"Show me how to estimate the trees!");

		GridBagConstraints showAnswerButtonConstraints = new GridBagConstraints();
		showAnswerButtonConstraints.gridx = 0;
		showAnswerButtonConstraints.gridy = 3;
		showAnswerButtonConstraints.insets = new Insets(5, 5, 5, 5);

		treePanel.add(showAnswerButton, showAnswerButtonConstraints);

		showAnswerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// depending on the treeType, how many trees are actually
				// visible in the grid?

				int actualTreeCount = 0;

				if (treeType.equals("Infested")) {

					actualTreeCount = estimationGrid
							.getTotalUnblockedInfested();

				} else if (treeType.equals("Non-Infested")) {

					actualTreeCount = estimationGrid
							.getTotalUnblockedNonInfested();

				} else {

					throw new AssertionError(
							"the string should only have been Infested or Non-Infested");
				}

				// the actual estimate of the number of trees of treeType in the
				// grid
				int actualEstimate = actualTreeCount * MULTIPLY;

				String answer = "There are actually "
						+ actualTreeCount
						+ " "
						+ treeType.toLowerCase()
						+ " trees visible in the grid. To get the total number of trees, you should do the following: "
						+ actualTreeCount + " * " + MULTIPLY + " = "
						+ actualEstimate;
				JOptionPane.showMessageDialog(panel, answer);

			}
		});
	}

	/**
	 * Creates button for checking user answers
	 * 
	 * @param panel
	 *            The panel to add all the pop-ups to
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
	private void createCheckAnswerButton(final JPanel panel,
			final String treeType, JPanel treePanel,
			final JTextField countAnswerField, final JTextField multiplyField,
			final JTextField estimateAnswerField) {
		// button that checks the user's answer
		JButton checkButton = new JButton("Check my Answer!");

		GridBagConstraints checkButtonConstraints = new GridBagConstraints();
		checkButtonConstraints.gridx = 1;
		checkButtonConstraints.gridy = 3;
		checkButtonConstraints.insets = new Insets(5, 5, 5, 5);

		treePanel.add(checkButton, checkButtonConstraints);

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
					// answer, their answer is wrong
					if (userMultiplyAnswer != MULTIPLY) {

						JOptionPane
								.showMessageDialog(
										panel,
										"Are you sure you should multiply by "
												+ userMultiplyAnswer
												+ "? Try again! Hint: Remember that only one-third of the grid is visible.");
					
						//If they multiply by the wrong number, it won't
						//check if the result is correct or not
						return ;
					}

					// how many trees does the user estimate is in the the grid?
					int userEstimateAnswer = Integer
							.parseInt(estimateAnswerField.getText());

					// if the user's multiplication is wrong, then their answer
					// is wrong
					if ((userCountAnswer * userMultiplyAnswer) != userEstimateAnswer) {

						JOptionPane.showMessageDialog(panel, userCountAnswer
								+ " * " + userMultiplyAnswer
								+ " does not equal " + userEstimateAnswer
								+ ". Try again!");
						
						//If they multiply wrong, it won't
						//check if the result is correct or not
						return;
					}

					// depending on the treeType, how many trees are actually
					// visible in the grid?
					int actualCountAnswer = 0;

					if (treeType.equals("Infested")) {

						actualCountAnswer = estimationGrid
								.getTotalUnblockedInfested();

					} else if (treeType.equals("Non-Infested")) {

						actualCountAnswer = estimationGrid
								.getTotalUnblockedNonInfested();

					} else {

						throw new AssertionError(
								"the string should only have been Infested or Non-Infested");
					}

					// what should the actual estimate be?
					int actualEstimateAnswer = actualCountAnswer * MULTIPLY;

					// check if the user's answer is within the error bars. Let
					// the user know if they got the correct answer or not and
					// remember it.
					if (calculateRightAnswer(userEstimateAnswer,
							actualEstimateAnswer)) {

						JOptionPane.showMessageDialog(panel,
								"You got the correct answer! Good job!");

						// Note that they solved one of the problems correctly

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

						// If they now have gotten both parts correct, we're
						// going
						// to advance to the next level, but for now we're just
						// going
						// to note that fact
						if (infCorrect && nonInfCorrect) {
							
							removeJOptionPanes();
							
							JOptionPane
									.showMessageDialog(
											panel,
											"You got the number of infested and non-infested"
													+ " trees correct!  Good job!  Time to try the next round.");
							
							
							
							controller.displayNewGrid();
						}

					} else {

						// not sure if we want to let them know the correct
						// answer of they get it wrong...

						// JOptionPane.showMessageDialog(panel,
						// "Sorry but your answer is wrong. The correct answer is "
						// + actualEstimateAnswer + ". Try again!");

						JOptionPane.showMessageDialog(panel,
								"Sorry but your answer is wrong. Try again!");

					}

				} catch (NumberFormatException exception) {

					// if users try to input anything but a number, let them
					// know that they should type a number

					JOptionPane.showMessageDialog(panel,
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

		add(showButton);

		showButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// a new visual representation of the grid is created
				GridView fullGridView = new GridView(estimationGrid
						.getGridCells(), gridView.getTrees(), estimationGrid.getCellWidth()	, estimationGrid.getCellHeight());

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

				// TODO : fix the value 50
				fullGridPanel.setPreferredSize(new Dimension(EstimationGrid.GRID_WIDTH, EstimationGrid.GRID_HEIGHT + 50 ));
				fullGridPanel.setMinimumSize(new Dimension(EstimationGrid.GRID_WIDTH, EstimationGrid.GRID_HEIGHT + 50 ));

				
				
//				// add the panel to a frame. will need to change this part.
//				JFrame frame = new JFrame();
//				frame.setSize(EstimationGrid.GRID_WIDTH, EstimationGrid.GRID_HEIGHT + 72);
//				frame.setTitle("Showing Full Grid");
//				frame.add(fullGridPanel);
//				frame.setResizable(false);
//				frame.setVisible(true);

				JOptionPane.showOptionDialog(gridView, fullGridPanel, "Full Grid",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
						new Object[] {}, null);
				
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

		// the allowed answer cannot be negative
		int userAnswerMin = userAnswer - ERROR_BARS;

		if (userAnswer < 0) {

			userAnswerMin = 0;
		}

		return ((actualAnswer >= userAnswerMin) && (actualAnswer <= userAnswer
				+ ERROR_BARS));
	}
	
	/**
	 * Removes a JOptionPAne and its children
	 * 
	 * http://stackoverflow.com/questions/18105598/closing-a-joptionpane-programatically
	 */
	private void removeJOptionPanes(){
		
		Window[] windows = Window.getWindows();
		
        for (Window window : windows) {
        	
            if (window instanceof JDialog) {
            	
                JDialog dialog = (JDialog) window;
                
                if (dialog.getContentPane().getComponentCount() == 1
                    && dialog.getContentPane().getComponent(0) instanceof JOptionPane){
                	
                    dialog.dispose();
                }
            }
        }
		
	}

}
