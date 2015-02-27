package beetle_kit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * Allows user to type in answers
 * 
 * @author Humaira
 *
 */
public class AnswerPanel extends JPanel {

	private JTextField infestedText;

	private JTextField nonInfestedText;

	private final EstimationGrid estimationGrid;

	private final GridView gridView;

	private static final int ERROR_BARS = 5;

	// the number by which number if infested / non- infested trees will be
	// multiplied
	private static final int MULTIPLY = 3;

	/**
	 * @param grid
	 * @param gridView
	 * 
	 */
	public AnswerPanel(EstimationGrid grid, GridView gridView) {

		this.estimationGrid = grid;

		this.gridView = gridView;

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		Border border = BorderFactory.createRaisedBevelBorder();

		setBorder(border);

		this.add(new JPanel());

		/*
		 * addInfestedText();
		 * 
		 * this.add(new JPanel());
		 * 
		 * addNonInfestedtest();
		 */

		estimatePanel();

		//this.add(new JPanel());

		addShowButton();

		this.add(new JPanel());

		// empty panels are added to add more space
	}

	private void estimatePanel() {
		
		JPanel panel = new JPanel() ;

		JButton estimateButton = new JButton("Ready to Estimate!");

		estimateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				createPopUp();

			}

		});

		panel.add(estimateButton);
		
		add(panel) ;

	}

	private void createPopUp() {

		JPanel panel = new JPanel();

		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxLayout);

		createSubAnswerPanel(panel, "Infested");

		// create space between the infested and non-infested sub answer panels
		panel.add(new JPanel());
		panel.add(new JPanel());

		createSubAnswerPanel(panel, "Non-Infested");

		// should not have the ok button
		JOptionPane.showOptionDialog(gridView, panel, "Input Answer",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				new Object[] {}, null);

	}

	private void createSubAnswerPanel(final JPanel panel, final String treeType) {

		JPanel treePanel = new JPanel(new BorderLayout());

		TitledBorder border = BorderFactory.createTitledBorder(treeType
				+ " Tree");

		border.setTitleFont((new Font("Dialog", Font.BOLD, 15)));

		treePanel.setBorder(border);

		// How many trees
		JPanel textPanel = new JPanel(new GridLayout(3, 2, 20, 20));

		JLabel treeLabel = new JLabel("How many " + treeType.toLowerCase()
				+ " trees do you see?");

		textPanel.add(treeLabel);

		final JTextField treeAnswerField = new JTextField(10);

		textPanel.add(treeAnswerField);

		// multiply by what?
		JTextArea multiplyArea = new JTextArea(
				"What number should you multiply the number of "
						+ treeType.toLowerCase() + " trees by?" + "\n"
						+ "(Remember that only 1/3 of the grid is visible)");

		multiplyArea.setEditable(false);

		multiplyArea.setBackground(new Color(0, 0, 0, 0));

		multiplyArea.setFont(new Font("Dialog", Font.BOLD, 12));

		textPanel.add(multiplyArea);

		final JTextField multiplyField = new JTextField(10);

		textPanel.add(multiplyField);

		// Final Answer

		JLabel finalAnswerLabel = new JLabel("How many "
				+ treeType.toLowerCase()
				+ " trees do you think are in the grid?");

		textPanel.add(finalAnswerLabel);

		final JTextField finalAnswerField = new JTextField(10);

		textPanel.add(finalAnswerField);

		treePanel.add(textPanel, BorderLayout.CENTER);

		createAnswerButtonPanel(panel, treeType, treePanel, treeAnswerField,
				multiplyField, finalAnswerField);

		panel.add(treePanel);
	}

	/**
	 * @param panel
	 * @param treeType
	 * @param treePanel
	 * @param treeAnswerField
	 * @param multiplyField
	 * @param finalAnswerField
	 */
	private void createAnswerButtonPanel(final JPanel panel,
			final String treeType, JPanel treePanel,
			final JTextField treeAnswerField, final JTextField multiplyField,
			final JTextField finalAnswerField) {
		// the panel is needed so that the button is of a reasonable size .
		JPanel buttonPanel = new JPanel();
		JButton checkButton = new JButton("Check my Answer!");

		buttonPanel.add(checkButton);

		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					int userTreeAnswer = Integer.parseInt(treeAnswerField
							.getText());

					int userMultiplyAnswer = Integer.parseInt(multiplyField
							.getText());

					if (userMultiplyAnswer != MULTIPLY) {

						JOptionPane
								.showMessageDialog(
										panel,
										"Are you sure you should multiply by "
												+ userMultiplyAnswer
												+ " ? Try again! Hint : Remeber that only one-third of the grid is visible. T");
					}

					int userFinalAnswer = Integer.parseInt(finalAnswerField
							.getText());

					int actualTreeAnswer = 0;

					if (treeType.equals("Infested")) {

						actualTreeAnswer = estimationGrid
								.getTotalUnblockedInfested();

					} else if (treeType.equals("Non-Infested")) {

						actualTreeAnswer = estimationGrid
								.getTotalUnblockedNonInfested();

					} else {

						throw new AssertionError(
								"the string should only have been Infested or Non-Infested");
					}

					int actualFinalAnswer = actualTreeAnswer * MULTIPLY;

					if (calculateRightAnswer(userFinalAnswer, actualFinalAnswer)) {

						JOptionPane.showMessageDialog(panel,
								"You got the correct answer! Good job!");

					} else {

						
						JOptionPane.showMessageDialog(panel,
								"Sorry but your answer is wrong. The correct answer is "
										+ actualFinalAnswer + ". Try again!");

					}

				} catch (NumberFormatException exception) {

					JOptionPane.showMessageDialog(panel,
							"Please enter a number");

				}

			}
		});
		
		JButton showAnswerButton = new JButton("Show me how to estimate the trees!") ;
		
		buttonPanel.add(showAnswerButton) ;
		
		showAnswerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int actualTreeAnswer = 0;

				if (treeType.equals("Infested")) {

					actualTreeAnswer = estimationGrid
							.getTotalUnblockedInfested();

				} else if (treeType.equals("Non-Infested")) {

					actualTreeAnswer = estimationGrid
							.getTotalUnblockedNonInfested();

				} else {

					throw new AssertionError(
							"the string should only have been Infested or Non-Infested");
				}
				
				int actualFinalAnswer = actualTreeAnswer * MULTIPLY;
				
				 JTextArea answerExplanationArea = new JTextArea(
				 "There are actually "
				 + actualTreeAnswer + " " + treeType.toLowerCase() +
				 " trees visible in the grid. To get the total number of trees, you should do the following: "
				 + "\n" + actualTreeAnswer + " * " + MULTIPLY + " = "
				 + actualFinalAnswer);
				 
				 
				 answerExplanationArea.setEditable(false);
				 answerExplanationArea.setBackground(new Color(0,0,0,0));
				 answerExplanationArea.setWrapStyleWord(true);
				 answerExplanationArea.setLineWrap(true);
				// answerExplanationArea.setFont(new Font("Times New Roman" , Font.PLAIN , 18));
				 
				 String answer = "There are actually "
						 + actualTreeAnswer + " " + treeType.toLowerCase() +
						 " trees visible in the grid. To get the total number of trees, you should do the following: " + actualTreeAnswer + " * " + MULTIPLY + " = "
						 + actualFinalAnswer ;
				JOptionPane.showMessageDialog(panel, answer);

				
			}
		});

		// treePanel.add(checkButton, BorderLayout.SOUTH);

		treePanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Adds JLabel, JTextField and JBUtton pertaining to user's aswer about
	 * infested trees
	 */
	private void addNonInfestedtest() {

		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		panel.add(new JPanel());

		panel.add(new JPanel());

		panel.add(new JLabel("Enter number of non-infested trees : "));

		panel.add(new JPanel());

		nonInfestedText = new JTextField();
		panel.add(nonInfestedText);

		JButton checkButton = new JButton("Check My Answer!");

		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					int userAnswer = Integer.parseInt(nonInfestedText.getText());

					int actualAnswer = estimationGrid
							.getTotalUnblockedNonInfested() * 3;

					boolean rightAnswer = calculateRightAnswer(userAnswer,
							actualAnswer);

					if (rightAnswer) {

						// OptionPane.showMessageDialog(panel,
						// "Good job! You got the correct answer! "
					}

					JOptionPane.showMessageDialog(panel, "You said there are "
							+ userAnswer + " non infested trees." + "\n"
							+ "There are actually " + actualAnswer
							+ " non-infested trees in total." + "");

					gridView.repaint();
					/*
					 * System.out.println("You said there are " +
					 * nonInfestedText.getText() + " non infested trees");
					 */

				} catch (NumberFormatException e) {

					JOptionPane.showMessageDialog(gridView,
							"Please enter a number");
				}

			}

		});

		panel.add(new JPanel());

		panel.add(checkButton);

		panel.add(new JPanel());

		add(panel);
	}

	/**
	 * Adds JLabel, JTextField and JBUtton pertaining to user's answer about
	 * infested trees
	 */
	private void addInfestedText() {

		final JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		panel.add(new JPanel());

		panel.add(new JPanel());

		panel.add(new JLabel("Enter number of infested trees :   "));

		panel.add(new JPanel());

		panel.add(new JPanel());

		panel.add(new JPanel());

		infestedText = new JTextField();
		panel.add(infestedText);

		JButton checkButton = new JButton("Check My Answer!");

		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					int numInfested = Integer.parseInt(infestedText.getText());

					JOptionPane.showMessageDialog(panel, "You said there are "
							+ numInfested
							+ "  infested trees. There are actually "
							+ estimationGrid.getTotalUnblockedInfested()
							+ " infested trees that are visible");

					gridView.repaint();

					/*
					 * System.out.println("You said there are " +
					 * infestedText.getText() + " non infested trees");
					 */

				} catch (NumberFormatException e) {

					JOptionPane.showMessageDialog(gridView,
							"Please enter a number");
				}

			}
		});

		panel.add(new JPanel());

		panel.add(checkButton);

		panel.add(new JPanel());

		add(panel);

	}

	private void addShowButton() {

		JPanel panel = new JPanel();
		
		panel.setAlignmentX(CENTER_ALIGNMENT);
		
		panel.setAlignmentY(CENTER_ALIGNMENT);

		JButton showButton = new JButton("Show full grid");

		panel.add(showButton);

	

		showButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// System.out.println("Clicked");

				GridView fullGridView = new GridView(estimationGrid
						.getCellData());

				fullGridView.setHasBlockedCells(false);

				fullGridView.setTrees(gridView.getTrees());

				JPanel fullGridPanel = new JPanel(new BorderLayout());

				fullGridPanel.add(fullGridView, BorderLayout.CENTER);

				JTextArea treeInfo = new JTextArea("There are "
						+ estimationGrid.getTotalInfested()
						+ " infested trees." + "\n" + "There are "
						+ estimationGrid.getTotalNonInfested()
						+ " non-infested trees.");
				Font font = new Font("Times New Roman", Font.PLAIN, 18);
				treeInfo.setFont(font);
				treeInfo.setEditable(false);

				fullGridPanel.add(treeInfo, BorderLayout.SOUTH);

				JFrame frame = new JFrame();
				frame.setSize(GridView.GRID_WIDTH, GridView.GRID_HEIGHT + 72);
				frame.setTitle("Showing Full Grid");
				frame.add(fullGridPanel);
				frame.setResizable(false);
				frame.setVisible(true);

				gridView.repaint();
			}
		});
		
		add(panel);
	}

	/**
	 * Returns true if the user's answers are within the error bars
	 * 
	 * @param userAnswer
	 * @param actualAnswer
	 * @return
	 */
	private boolean calculateRightAnswer(int userAnswer, int actualAnswer) {

		return ((actualAnswer >= userAnswer - ERROR_BARS) && (actualAnswer <= userAnswer
				+ ERROR_BARS));
	}

}
