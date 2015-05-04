package user_accounts;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import user.User;

/**
 * GUI for constructing the Sign Up Page for the Chemistry Passport Program
 * 
 * @author Sehr Sethi, Humaira Orchee and Charlotte Dye
 * @version April 30, 2015
 *
 */
public class SignUp extends JPanel implements ActionListener{

	// Adds the text "Sign Up" to the Sign Up GUI
	private JLabel signUpLabel = null;

	// Adds the text "Grade" to the Sign Up GUI
	private JLabel gradeLabel = null;

	// Adds the text "Adventure Name" to the Sign Up GUI
	private JLabel userAdventureNameLabel = null;

	// Allows the user to enter their "Adventure Name"
	private JTextField userAdventureNameTextField = null;

	// The button for submitting the sign up button
	private JButton submitButton = null;

	// Allows the user to select their grade level from a drop down of available
	// grade levels
	private JComboBox<String> gradeComboBox = null;

	// Holds the grade levels for the users
	private String[] grades = { "", "K", "1", "2", "3", "4", "5", "6" };

	// Holds the current user's grade level
	private String userGrade;

	// The main sign up panel that contains all the sign up information and the
	// sign up title
	private JTabbedPane g_tabbedPane = new JTabbedPane();

	// Panel that contains all sign up information; labels, text fields and
	// buttons
	private JPanel root_panel_inside_tabbedPane = new JPanel();

	// Displays the log in and the sign up GUI at the start up of the
	// application
	private UserAccountGUI userAccountGUI;

	// The current user that is currently playing the Chemistry Passport
	// Application
	private User user;

	/**
	 * Constructs a Sign Up GUI
	 * 
	 * @param userAccountGUI
	 *            The GUI that the SignUp GUI is added to
	 */
	public SignUp(UserAccountGUI userAccountGUI) {

		this.userAccountGUI = userAccountGUI;

		BoxLayout boxLayout = new BoxLayout(root_panel_inside_tabbedPane,
				BoxLayout.Y_AXIS);
		root_panel_inside_tabbedPane.setLayout(boxLayout);
		signUp();

	}

	/**
	 * Constructs the Sign Up Page Contains Sin Up labels, buttons and
	 * textFields to hold Sign Up user information
	 */
	public void signUp() {

		// Adds a box layout to the main panel containing the sign up user
		// information
		BoxLayout boxLayout = new BoxLayout(root_panel_inside_tabbedPane,
				BoxLayout.Y_AXIS);
		root_panel_inside_tabbedPane.setLayout(boxLayout);
		root_panel_inside_tabbedPane.setLayout(new GridLayout(7, 1));
		root_panel_inside_tabbedPane.setBorder(BorderFactory.createLineBorder(
				(new Color(150, 150, 150)), 3));
		// add the main panel to the tabbed pane that contains the sign up title
		// and now holds all the sign up user information
		g_tabbedPane.add(root_panel_inside_tabbedPane);

		// The panel that holds the label to ask users to sign up
		JPanel startUpPanel = new JPanel();
		signUpLabel = new JLabel("         Not A User Yet? Sign Up Below!  ");
		signUpLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		startUpPanel.add(signUpLabel);
		root_panel_inside_tabbedPane.add(new JPanel());
		// add this panel to the main sign up panel
		root_panel_inside_tabbedPane.add(startUpPanel);

		// The panel that requests the user to input a Adventure Name
		JPanel userAdventureNamePanel = new JPanel();
		userAdventureNameLabel = new JLabel("Adventure Name:  ");
		userAdventureNameLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		userAdventureNameTextField = new JTextField(45);
		userAdventureNameTextField
				.setFont(new Font("Monospaced", Font.BOLD, 12));

		userAdventureNameTextField
				.setFont(new Font("Monospaced", Font.BOLD, 12));

		// add this adventure name panel to the main sign up panel
		root_panel_inside_tabbedPane.add(userAdventureNamePanel);

		// The panel that contains user grade information
		// Asks the user to select an appropriate grade level given a drop down
		// of grades
		JPanel gradePanel = new JPanel();
		gradeLabel = new JLabel("Grade:        ");
		gradeLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		gradeComboBox = new JComboBox<String>(grades);
		gradeComboBox.setFont(new Font("Monospaced", Font.BOLD, 12));
		gradeComboBox.setEnabled(true);
		gradeComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		gradeComboBox.setPreferredSize(new Dimension(300, 30));
		gradeComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				userGrade = (String) gradeComboBox.getSelectedItem();

			}
		});

		// Add the text "Adventure Name" and allow the user to input an
		// "Adventure Name"
		// Add this information to the panel that contains information for
		// "Adventure Name"
		userAdventureNamePanel.add(userAdventureNameLabel);
		userAdventureNamePanel.add(userAdventureNameTextField);

		// Add the text "Grade" to the panel that contains user grade
		// information
		gradePanel.add(gradeLabel);
		// Add the drop down of grades to the grade panel
		gradePanel.add(gradeComboBox);
		gradeComboBox.setFont(new Font("Monospaced", Font.BOLD, 14));
		// Add the grade panel to the main sign up panel
		root_panel_inside_tabbedPane.add(gradePanel);

		// The panel that holds the submit information button
		JPanel signUpSubmitButtonPanel = new JPanel();
		submitButton = new JButton("  Submit  ");
		signUpSubmitButtonPanel.add(submitButton);
		Insets insets2 = signUpSubmitButtonPanel.getInsets();
		submitButton.setBounds(430 + insets2.left, insets2.top, 90, 30);
		root_panel_inside_tabbedPane.add(signUpSubmitButtonPanel);
		submitButton.addActionListener(this);

		// Add to the main sign up panel
		g_tabbedPane.addTab("Sign Up", null, root_panel_inside_tabbedPane, "");
		add(g_tabbedPane);

	}


	/**
	 * Once the submit button has been pressed, get the user information and
	 * write to the database
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		// If the user clicks the submit button on the sign up page
		if (evt.getSource() == submitButton)

			if (checkSignUp()) {

				// Write their entered "Adventure Name" and selected grade to
				// database file
				if (writeToFile(userAdventureNameTextField, userGrade)) {

					// Prompt them that their account has been created
					JOptionPane.showMessageDialog(this, "User account for "
							+ userAdventureNameTextField.getText()
							+ " has been created. Cheers!");

					// Create a passport for this current user
					userAccountGUI.createPassport(user);

				}

			}

	}

	/**
	 * Checks if the user name and Adventure Name already exist in the database
	 * 
	 * @return true if exists, otherwise prompt user to select another Adventure
	 *         Name
	 */

	private boolean checkSignUp() {

		// If the user has not entered an "Adventure Name" or have left the
		// field empty
		if ((userAdventureNameTextField.getText() == null)
				|| userAdventureNameTextField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Enter the Adventure Name",
					"Error", JOptionPane.ERROR_MESSAGE);

			return false;

			// if they adventure name contains a ","
		} else if (userAdventureNameTextField.getText().contains(",")) {

			// tell them that the adventure name cannot contain commas
			JOptionPane.showMessageDialog(this,
					"Adventure Name cannot have a ','. Try again!", "Error",
					JOptionPane.ERROR_MESSAGE);

			return false;

		}

		// if the user has not selected a grade level
		else if ((userGrade == null) || (userGrade.equals(""))) {
			// promt them to enter a grade level
			JOptionPane.showMessageDialog(this, "Enter the Grade", "Error",
					JOptionPane.ERROR_MESSAGE);

			return false;

		}

		return true;

	}

	/**
	 * Writes user information to the database and URL
	 * 
	 * @param adventureNameText
	 *            the Adventure Name of the user entered
	 * @param grade
	 *            the selected grade level
	 * @return
	 */
	private boolean writeToFile(JTextField adventureNameText, String grade) {

		// the file that contains user information
		File databaseFile = new File(UserInfoCreator.getFilePath());

		// holds the users kit progress information
		String kitProgress = createKitProgressString();

		try {

			// the file should be un-hidden before writing to file
			UserInfoCreator.setHideFile(databaseFile, false);

			BufferedWriter out = new BufferedWriter(new FileWriter(
					databaseFile, true));

			// get the adventure name of the user
			String adventureName = adventureNameText.getText();

			// if this adventure name already exists
			if (adventureNameExists(adventureName)) {

				// ask them to enter another adventure name
				JOptionPane.showMessageDialog(this,
						"Someone already has this username: " + adventureName
								+ ". Let's pick another one!");

				out.flush();

				out.close();

				// the file should be hidden after writing to file
				UserInfoCreator.setHideFile(databaseFile, true);

				return false;
			}

			// write the users adventure name, grade and kit progress to file
			out.write("\n" + adventureName + "," + grade + "," + kitProgress);

			out.flush();

			out.close();

			// the file should be hidden after writing to file
			UserInfoCreator.setHideFile(databaseFile, true);

			// construct a new user with the entered information
			user = new User(adventureName, grade, kitProgress);

			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * For each kit in the program, it sets the user's progress to 0.
	 * 
	 * @return a string of the user's kit progress
	 */
	private String createKitProgressString() {

		File databaseFile = new File(UserInfoCreator.getFilePath());

		try {

			BufferedReader in = new BufferedReader(new FileReader(databaseFile));

			String[] firstLine = in.readLine().split(",");

			// the first line in the file has the Adventure Name, Grade and then
			// all the names of the existing Kits
			int numKits = firstLine.length - 2;

			String kitProgress = "";

			for (int i = 1; i <= numKits; i++) {

				if (i < numKits) {

					kitProgress += "0,";

				} else {

					kitProgress += "0";
				}
			}
			in.close();
			return kitProgress;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;

	}

	/**
	 * Checks if the user name entered already exists
	 * 
	 * @param adventureName
	 *            the user name entered
	 * @return
	 */
	private boolean adventureNameExists(String adventureName) {

		File databaseFile = new File(UserInfoCreator.getFilePath());

		try {

			BufferedReader in = new BufferedReader(new FileReader(databaseFile));

			// don't care about the first line because it just contains the
			// column headers
			in.readLine();

			// start reading to find user name in the file
			String line = in.readLine();

			while (line != null) {

				String[] userInfo = line.split(",");

				String tempAdventureName = userInfo[0];

				if (tempAdventureName.trim().equals(adventureName.trim())) {

					return true;

				} else {

					line = in.readLine();
				}
			}
			return false;

		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return false;
	}

}