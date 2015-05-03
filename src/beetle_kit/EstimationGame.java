package beetle_kit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import user_accounts.UserInfoCreator;

/**
 * Chemistry Passport Project Runs the Estimation Game
 * 
 * @author Charlotte Dye, Sehr Sethi, and Humaira Orchee
 * @version April 6, 2015
 *
 */
public class EstimationGame extends JPanel {

	// The number of rounds the user needs to complete correctly to earn their
	// reward
	private static final int MAX_NUM_ROUNDS = 3;

	// Which grid number the user is currently working on
	private int currentGridNum;

	// The display of the view
	private GridView currentGridView;

	// The panel where the user types their answers
	private AnswerPanel currentAnswerPanel;

	// The beetle kit for the game
	private BeetleKit beetleKit;

	// The panel with the legend of the game
	private JPanel legendPanel;

	// Whether the user has pressed the "Show me how to estimate" button this
	// round
	private boolean hasViewedAnswer = false;

	/**
	 * Creates a new EstimationGame and loads the appropriate round
	 * 
	 * @param beetleKit
	 *            The BeetleKit for the game
	 */
	public EstimationGame(BeetleKit beetleKit) {

		this.beetleKit = beetleKit;

		// Start with the grid corresponding to the first round the user hasn't
		// completed
		currentGridNum = beetleKit.getKitProgress();

		// Set the layout
		setLayout(new BorderLayout());

		// Load the round and the grid
		createNextRound();

	}

	/**
	 * Creates a new grid and a new grid view.
	 */
	private void createNextRound() {

		// if the user has actually completed all MAX_NUM_ROUNDS rounds, no more
		// grids should be
		// created.
		if (currentGridNum >= MAX_NUM_ROUNDS) {

			// Create the panel prompting the user to play again or see their
			// reward
			JPanel panel = createEndGamePanel();

			// Show a dialogue congratulating the user which displays the end
			// game panel
			JOptionPane.showOptionDialog(this, panel, "Congratulations!!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, new Object[] {}, null);

			// Don't continue to the create new grid method because the button
			// they
			// click will be in charge of the next step
			return;

		}

		// Create the grid for the next round
		createNewGrid();

	}

	/**
	 * Creates the grid and starts the next round
	 */
	private void createNewGrid() {

		// Find out the user's grade so we know what size to make the grid
		String userGrade = beetleKit.getPassport().getUser().getGrade();

		// The grid with the trees
		EstimationGrid grid = new EstimationGrid(userGrade, BeetleKit
				.getStartPage().getInfestedColor(), BeetleKit.getStartPage()
				.getNonInfestedColor());

		// Generate the tree information
		grid.fillTreeArray();

		// Create the view that displays the grid
		currentGridView = new GridView(grid.getGridCells(), grid.getTrees(),
				grid.getCellWidth(), grid.getCellHeight());

		// Add the grid to the game
		add(currentGridView, BorderLayout.CENTER);

		// Create the panel with the legend that indicates which color
		// corresponds to which type of tree
		createLegendPanel();

		// Create the answer panel and add it
		currentAnswerPanel = new AnswerPanel(grid, currentGridView, this);
		add(currentAnswerPanel, BorderLayout.SOUTH);

		// Write the kit progress back to the file in case the user quits the
		// application and starts it back up
		updateKitProgressInFile();

		// Repaint
		repaint();
		revalidate();
	}

	/**
	 * Record the user's progress in the user database file on the user's
	 * computer
	 */
	private void updateKitProgressInFile() {

		try {

			// Read the whole file into a stringBuilder so we can modify it
			StringBuilder stringBuilder = readFromFile();

			// get each line from the stringBuilder
			String[] lines = stringBuilder.toString().split("\n");

			// This will store what we will write to the file (we will overwrite
			// its current contents)
			String toWrite = "";

			// Iterate through the lines of the file we read
			for (int i = 0; i < lines.length; i++) {

				// ignore the first line because it just contains the column
				// headers
				if (i == 0) {

					toWrite += lines[i] + "\n";

					// each subsequent line will contain the user information
					// for one
					// user
				} else if (lines[i].contains(beetleKit.getPassport()
						.getUserAdventureName())) {

					// if we found the current user, change their kitProgress
					// for the beetleKit (which is at index 2)

					// Split it into an array so we can modify the value at
					// index 2
					String[] userInfo = lines[i].split(",");

					userInfo[2] = String.valueOf(beetleKit.getKitProgress());

					// for the beetleKit, kitProgress can be at most
					// MAX_NUM_ROUNDS, so make sure we don't write an invalid
					// number to the file
					if (Integer.parseInt(userInfo[2]) > MAX_NUM_ROUNDS) {
						userInfo[2] = String.valueOf(MAX_NUM_ROUNDS);
					}

					// convert the array of userInfo to a String
					for (int j = 0; j < userInfo.length; j++) {

						toWrite += userInfo[j];

						// For all but the last line, add a comma at the end to
						// separate the values
						if (j < userInfo.length - 1) {
							toWrite += ",";
						}

					}

					// Add a new line character to separate users
					toWrite += "\n";
				} else {

					// otherwise, continue building the string to be written to
					// the file
					toWrite += lines[i] + "\n";
				}
			}

			// write the string back to the file
			writeToFile(toWrite);

		} catch (FileNotFoundException e) {
			System.out
					.println("ERROR: The user database file was not found, so we can't update your progress on this kit!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out
					.println("ERROR: We ran into an IOException when writing your progress to the user database file");
			e.printStackTrace();
		}

	}

	/**
	 * Write the specified text to the user database. This will overwrite
	 * anything currently in the user database.
	 * 
	 * @param toWrite
	 *            The text to write to the database
	 * @throws IOException
	 */
	public void writeToFile(String toWrite) throws IOException {

		// Locate the user database
		File fileToWrite = new File(UserInfoCreator.getFilePath());

		// the file needs to be be un-hidden before writing to file
		UserInfoCreator.setHideFile(fileToWrite, false);

		// Create a file writer and write the text to the file
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileToWrite));
		bw.write(toWrite);
		bw.flush();

		// Close the file writer
		bw.close();

		// the file needs to be hidden again after writing to file
		UserInfoCreator.setHideFile(fileToWrite, true);
	}

	/**
	 * Reads in the information in the user database
	 * 
	 * @return A StringBuilder containing the contents of the user database file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public StringBuilder readFromFile() throws FileNotFoundException,
			IOException {

		// Create the reader for the file
		BufferedReader br = new BufferedReader(new FileReader(new File(
				UserInfoCreator.getFilePath())));

		// This will store the contents of the database file as we read it in
		StringBuilder stringBuilder = new StringBuilder();

		// Read the first line
		String line = br.readLine();

		// While we're still getting valid text, add it to the string builder
		// and get the next line
		while (line != null) {
			stringBuilder.append(line + "\n");
			line = br.readLine();
		}

		// Close the reader
		br.close();

		// Return the text we read from the file
		return stringBuilder;
	}

	/**
	 * Gets rid of the current grid and displays a new Grid
	 */
	public void displayNewGrid() {

		// Remove the current grid
		removeGrid();

		// if the user has viewed the answer, then do not give them credit for
		// that round. Otherwise, give them credit and note that they've
		// advanced
		if (!hasViewedAnswer) {
			beetleKit.setKitProgress(beetleKit.getKitProgress() + 1);
			beetleKit.setUserKitProgress(beetleKit.getKitProgress());
			currentGridNum++;
		}

		// They are starting a new round, so make sure that we reset
		// hasViewedAnswer
		hasViewedAnswer = false;

		// Create the next round
		createNextRound();

	}

	/**
	 * Remove the current grid view and answer panel to prepare to replace them
	 */
	private void removeGrid() {

		// Remove the grid, answer panel, and legend
		remove(currentGridView);
		remove(currentAnswerPanel);
		remove(legendPanel);

		// Update the display
		repaint();
		revalidate();

	}

	/**
	 * Creates a legend that indicates the color associated with infested trees
	 * and non-infested trees
	 */
	private void createLegendPanel() {

		// Create the panel for the legend and set the layout
		legendPanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(legendPanel, BoxLayout.Y_AXIS);
		legendPanel.setLayout(boxLayout);
		legendPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,
				5));

		// Create the legend portion for infested trees
		// the spacing of the text is important
		legendPanel.add(createKey("          Infested Trees         ",
				EstimationGrid.getInfestedColor()));

		// Add a panel for spacing reasons
		legendPanel.add(new JPanel());

		// Create the legend portion for non-infested trees
		legendPanel.add(createKey("          Non-infested Trees ",
				EstimationGrid.getNonInfestedColor()));

		// Add the legend panel to the game
		add(legendPanel, BorderLayout.NORTH);
	}

	/**
	 * Creates a JLabel indicating the kind of tree and a little panel of the
	 * color corresponding to the kid of tree
	 * 
	 * @param text
	 *            The kind of tree
	 * @param color
	 *            The color of the given kind of tree
	 * @return The panel containing the text and the color
	 */
	private JPanel createKey(String text, Color color) {

		// The panel containing this key item
		JPanel panel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		panel.setLayout(boxLayout);

		// The label with the key text identifying whether this color is for
		// infested or non-infested trees
		JLabel label = new JLabel(text);

		// Add a panel to the key panel for spacing reasons
		panel.add(new JPanel(new BorderLayout()));

		// Add the label to the key panel
		panel.add(label);

		// Add panels to the key panel for spacing reason
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());

		// Create the panel that contains the color
		JPanel colorSquare = new JPanel(new BorderLayout());
		colorSquare.setBorder(BorderFactory.createLineBorder(color, 5));
		colorSquare.setBackground(color);

		// Add the color square to the key panel
		panel.add(colorSquare);

		return panel;
	}

	/**
	 * Create the panel that notifies the user that they have successfully
	 * completed the game and allows them to view their reward or keep playing.
	 * 
	 * @return The panel notifying the user that they have completed the game
	 *         and prompts them to view their reward or continue.
	 */
	private JPanel createEndGamePanel() {

		// The end game panel
		JPanel panel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxLayout);

		// The label that congratulates the user
		JLabel congratulationsLabel = new JLabel(
				"Great! You have completed the Estimation Game! You now get a sticker!!!");

		// The label asking the user what they want to do next
		JLabel questionLabel = new JLabel(
				"Do you want to continue to estimate trees?");

		// Center the label text
		congratulationsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Add the labels with spacing
		panel.add(congratulationsLabel);
		panel.add(Box.createRigidArea(new Dimension(40, 10)));
		panel.add(questionLabel);
		panel.add(Box.createRigidArea(new Dimension(40, 10)));

		// The panel containing the buttons for viewing reward or playing more
		JPanel buttonPanel = new JPanel();
		BoxLayout buttonBoxLayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
		buttonPanel.setLayout(buttonBoxLayout);
		panel.add(buttonPanel);

		// The button the user presses to continue playing the estimation game.
		JButton yesButton = new JButton("Yes, continue playing");
		yesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Close all the popups
				removeJOptionPanes();

				// Load the next grid
				createNewGrid();
			}

		});

		// The button the user presses to go back to their passport
		JButton noButton = new JButton("No, go back to my passport");
		noButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Remove all the popups
				removeJOptionPanes();

				// before going back to passport, write the user's progress to
				// the file
				updateKitProgressInFile();

				// Show the sticker animation and enable the reward button
				beetleKit.earnReward();

			}
		});

		// Add the buttons to the panel with spacing
		buttonPanel.add(yesButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(50, 10)));
		buttonPanel.add(noButton);

		return panel;

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

	/**
	 * Note that the user has viewed the answer for this round and it shouldn't
	 * be counted
	 */
	public void setViewedAnswerToTrue() {
		hasViewedAnswer = true;
	}

}
