package beetle_kit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import user_accounts.UserInfoCreator;
import main.ChemistryPassportGUI;

/**
 * Chemistry Passport Project Runs the Estimation Game
 * 
 * @author Charlotte Dye, Sehr Sethi, Humaira Orchee
 * @version April 6, 2015
 *
 */
public class EstimationGame extends JPanel {

	private static final int MAX_NUM_ROUNDS = 3;
	private int currentGridNum;
	private GridView currentGridView;
	private AnswerPanel currentAnswerPanel;
	private BeetleKit beetleKit;
	private JPanel legendPanel;

	public EstimationGame(BeetleKit beetleKit) {

		this.beetleKit = beetleKit;

		currentGridNum = 1 + beetleKit.getKitProgress();

		setLayout(new BorderLayout());

		createNextRound();

	}

	/**
	 * Creates a new grid and a new grid view.
	 */
	private void createNextRound() {

		// if the user has actually completed 3 rounds, no more grids should be
		// created.
		if (currentGridNum > MAX_NUM_ROUNDS) {

			JPanel panel = createEndGamePanel();

			JOptionPane.showOptionDialog(this, panel, "Congratulations!!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, new Object[] {}, null);

			return;

			// Go to passport Card
			// Then go to page we want
		}

		// EstimationStartPage startPage = new EstimationStartPage();

		createNewGrid();

	}

	/**
	 * 
	 */
	private void createNewGrid() {
		String userGrade = beetleKit.getPassport().getUser().getGrade();
		EstimationGrid grid = new EstimationGrid(userGrade, BeetleKit
				.getStartPage().getInfestedColor(), BeetleKit.getStartPage()
				.getNonInfestedColor());

		grid.fillTreeArray();

		// Create three different gridViews and get rid of and swap
		// when switching rounds

		// Need a control class--could be this one

		// Could take checking answer stuff and put here or in separate class

		currentGridView = new GridView(grid.getGridCells(), grid.getTrees(),
				grid.getCellWidth(), grid.getCellHeight());

		add(currentGridView, BorderLayout.CENTER);

		createLegendPanel();

		currentAnswerPanel = new AnswerPanel(grid, currentGridView, this);
		add(currentAnswerPanel, BorderLayout.SOUTH);
		currentGridNum++;

		// TODO : update the file with the new kit progress
		updateKitProgressInFile();

		repaint();
		revalidate();
	}

	/**	
 * 
 */
	private void updateKitProgressInFile() {

		try {
			StringBuilder stringBuilder = readFromFile();

			// at this point, all the text in the file is stored in
			// stringBuilder

			// get each line from the stringBuilder

			String[] lines = stringBuilder.toString().split("\n");

			String toWrite = "";

			// ignore the first line because its the column headers

			// each subsequent line will contain the user information for one
			// user

			for (int i = 0; i < lines.length; i++) {
				
				System.out.println("line at i" + lines[i]);
				
				System.out.println("user name is :" + (beetleKit.getPassport()
						.getUserName()));


				System.out.println("user name 2 is :" + (beetleKit.getPassport().getUser().getAdventureName()));

				
				if (i == 0) {

					toWrite += lines[i] + "\n";

				}else if (lines[i].contains(beetleKit.getPassport()
						.getUserName())) {
					
					System.out.println("I am here:");


					// if we found the current user, change their kitProgress for
					// the beetleKit (which is at index 2)

					
					String[] userInfo = lines[i].split(",");

					userInfo[2] = String.valueOf(beetleKit.getKitProgress());
					
					System.out.println("Kit progress: " + beetleKit.getKitProgress());
					
					System.out.println("User Info at 2: " + userInfo[2]);

					// for the beetleKit, kitProgress can be at most
					// MAX_NUM_ROUNDS

					if (Integer.parseInt(userInfo[2]) > MAX_NUM_ROUNDS) {

						userInfo[2] = String.valueOf(MAX_NUM_ROUNDS);

					}

					// convert the array of userInfo to a String

					for (int j = 0; j < userInfo.length; j++) {
						
						System.out.println("user info at j: " + userInfo[j]);

						toWrite += userInfo[j];
						
						if(j < userInfo.length - 1){
							toWrite += ",";
						}
						
						System.out.println("to write is : " + toWrite);

					}

					toWrite += "\n";
				} else {

					// otherwise, continue building the string to be written to
					// the file

					// TODO : come back if this breaks!

					toWrite += lines[i] + "\n";
				}
			}

			// write the string back to the file
			

			writeToFile(toWrite);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param toWrite
	 * @throws IOException
	 */
	public void writeToFile(String toWrite) throws IOException {
		File fileToWrite = new File(UserInfoCreator.getFilePath());
		
		// the file should be un-hidden before writing to file
		UserInfoCreator.setHideFile(fileToWrite, false);

		BufferedWriter bw = new BufferedWriter(new FileWriter(fileToWrite));

		bw.write(toWrite);

		bw.flush();

		bw.close();
		

		// the file should be hidden after writing to file
		UserInfoCreator.setHideFile(fileToWrite, true);
	}

	/**
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public StringBuilder readFromFile() throws FileNotFoundException,
			IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(
				UserInfoCreator.getFilePath())));

		StringBuilder stringBuilder = new StringBuilder();

		String line = br.readLine();

		while (line != null) {

			stringBuilder.append(line + "\n");

			line = br.readLine();

		}

		br.close();
		return stringBuilder;
	}

	/**
	 * Displays a new Grid Note : When a new grid is displayed, user should
	 * close all pop-up manually.
	 */
	public void displayNewGrid() {

		removeGrid();
		beetleKit.setKitProgress(beetleKit.getKitProgress() + 1);
		createNextRound();

	}

	/**
	 * Remove the current grid view and answer panel to prepare to replace them
	 */
	private void removeGrid() {
		remove(currentGridView);
		remove(currentAnswerPanel);
		remove(legendPanel);
		repaint();
		revalidate();

	}

	/**
	 * Creates a legend that indicates the color associated with infested trees
	 * and non-infested trees
	 */
	private void createLegendPanel() {

		legendPanel = new JPanel();

		BoxLayout boxLayout = new BoxLayout(legendPanel, BoxLayout.Y_AXIS);

		legendPanel.setLayout(boxLayout);

		legendPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,
				5));

		// the spacing of the text is important
		legendPanel.add(createKey("          Infested Trees         ",
				EstimationGrid.getInfestedColor()));

		legendPanel.add(new JPanel());

		legendPanel.add(createKey("          Non-infested Trees ",
				EstimationGrid.getNonInfestedColor()));

		// panel.add(createNonInfestedKey());

		add(legendPanel, BorderLayout.NORTH);
	}

	/**
	 * Creates a JLabel indicating the kind of tree and and a little panel of
	 * the color corresponding to the kid of tree
	 * 
	 * @param text
	 *            The kind of tree
	 * @param color
	 *            The color of the given kind of tree
	 * @return The panel containing the text and the color
	 */
	private JPanel createKey(String text, Color color) {

		// JPanel panel = new JPanel(new BorderLayout()) ;

		JPanel panel = new JPanel();

		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		panel.setLayout(boxLayout);

		JLabel label = new JLabel(text);

		// panel.add(label, BorderLayout.CENTER) ;

		panel.add(new JPanel(new BorderLayout()));

		panel.add(label);

		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());

		JPanel colorSquare = new JPanel(new BorderLayout());

		colorSquare.setBorder(BorderFactory.createLineBorder(color, 5));

		colorSquare.setBackground(color);

		// panel.add(colorSquare , BorderLayout.EAST) ;

		panel.add(colorSquare);

		return panel;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel createEndGamePanel() {

		JPanel panel = new JPanel();

		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

		panel.setLayout(boxLayout);

		JLabel congratulationsLabel = new JLabel(
				"Great! You have completed the Estimation Game! You now get a sticker!!!");

		JLabel questionLabel = new JLabel(
				"Do you want to continue to estimate trees?");

		congratulationsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(congratulationsLabel);
		panel.add(Box.createRigidArea(new Dimension(40, 10)));
		panel.add(questionLabel);
		panel.add(Box.createRigidArea(new Dimension(40, 10)));

		JPanel buttonPanel = new JPanel();

		BoxLayout buttonBoxLayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);

		buttonPanel.setLayout(buttonBoxLayout);

		panel.add(buttonPanel);

		JButton yesButton = new JButton("Yes, continue playing");
		yesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				removeJOptionPanes();

				createNewGrid();

			}

		});
		JButton noButton = new JButton("No, go back to my passport");
		noButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				removeJOptionPanes();
				
				// before going back to passport, write the user's progress to the file
				
				updateKitProgressInFile();

				beetleKit.earnReward();

			}
		});

		buttonPanel.add(yesButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(50, 10)));
		buttonPanel.add(noButton);

		return panel;

	}

	/**
	 * Removes a JOptionPAne and its children
	 * 
	 * http://stackoverflow.com/questions/18105598/closing-a-joptionpane-programatically
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
