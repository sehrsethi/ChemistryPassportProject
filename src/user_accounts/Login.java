package user_accounts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import user.User;

/**
 * GUI for constructing the Login Page for the Chemistry Passport Program
 * 
 * @author Sehr Sethi, Humaira Orchee and Charlotte Dye
 * @version April 30, 2015
 *
 */
public class Login extends JPanel implements ActionListener, KeyListener {

	// Adds the text "Adventure Name" to the Log in GUI
	private JLabel userAdventureNameLabel = null;

	// Adds the text Field for the user to type their "Adventure Name"
	private JTextField userAdventureNameTextField = null;

	// The button that allows the user to log in
	private JButton loginButton = null;

	// The button that allows the user to canel out of the log in GUI
	private JButton loginCancelButton = null;

	// Represents the borders for the container. Used to specify the location
	// for the log in button
	private Insets insets;

	// The panel that the log in information is added to along with the log in
	// title
	private JTabbedPane g_tabbedPane = new JTabbedPane();

	// The panel that holds the log in labels, text fields and buttons
	private JPanel root_panel = null;

	// Used to get the adventure name text
	private String m_user = null;

	// The current user that is currently playing the Chemistry Passport
	// Application
	private User user;

	// Displays the log in and the sign up GUI at the start up of the
	// application
	private UserAccountGUI userAccountGUI;

	/**
	 * Constructs the log in page
	 * 
	 * @param userAccountGUI
	 *            The GUI that the SignUp GUI is added to
	 */
	public Login(UserAccountGUI userAccountGUI) {

		// this.userInfoCreator = userInfoCreator ;

		this.userAccountGUI = userAccountGUI;

		loginPage();

	}

	/**
	 * Constructs the Login Page Contains log in labels, buttons and textFields
	 * to hold log in information
	 */
	public void loginPage() {

		// Sets the layout of the root_panel that is responsible for holding all
		// log in information
		root_panel = new JPanel();
		root_panel.setLayout(new BoxLayout(root_panel, BoxLayout.Y_AXIS));
		root_panel.setBorder(BorderFactory.createLineBorder((new Color(150,
				150, 150)), 2));

		// The panel that contains the text "Adventure Name" and the associated
		// text field
		JPanel adventurePanel = new JPanel();
		userAdventureNameLabel = new JLabel("Adventure Name:  ");
		userAdventureNameLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		userAdventureNameTextField = new JTextField(40);
		userAdventureNameTextField
				.setFont(new Font("Monospaced", Font.BOLD, 12));
		adventurePanel.add(userAdventureNameLabel);
		adventurePanel.add(userAdventureNameTextField);
		userAdventureNameTextField.addKeyListener(this);
		// Add adventure panel to the root panel
		root_panel.add(adventurePanel);

		// The panel that holds the log in button and the cancel log in button
		JPanel buttonPanel = new JPanel();
		loginButton = new JButton("  Login  ");
		loginCancelButton = new JButton("  Cancel  ");
		buttonPanel.add(loginButton);
		buttonPanel.add(loginCancelButton);
		insets = buttonPanel.getInsets();
		loginButton.setBounds(330 + insets.left, insets.top, 90, 30);
		loginCancelButton.setBounds(430 + insets.left, insets.top, 90, 30);
		// adds the button panel to the root panel
		root_panel.add(buttonPanel);

		// Add action listener to the cancel and log in button
		loginButton.addActionListener(this);
		loginCancelButton.addActionListener(this);

		// Constructs a panel that contains the log in tab and adds the root
		// panel to this panel
		g_tabbedPane.addTab("Login", null, root_panel, "");
		add(g_tabbedPane);
		this.setBounds(100, 100, 530, 325);
	}

	/**
	 * Performs the appropriate actions when the user clicks either the log in
	 * or cancel button
	 */
	public void actionPerformed(ActionEvent evt) {
		// if the user cicked the log in button
		if (evt.getSource() == loginButton) {

			// if they have entered an "Adventure Name"
			if (userNameEntered()) {
				// read their "Adventure Name" from the file
				this.readFromFile(userAdventureNameTextField);
			}
		}
		// if the user clicks the cancel button
		if (evt.getSource() == loginCancelButton) {
			// close the application
			System.exit(0);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Allows the user to log in when they press the enter key
	 */
	public void keyPressed(KeyEvent evt) {
		// when the user presses the enter key
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			// if they already have an account
			if (userNameEntered()) {
				// get their "Adventure Name" from the file
				this.readFromFile(userAdventureNameTextField);

			}
		}
	}

	/**
	 * Checks if the user name has entered text in the log in text field that
	 * holds user name
	 * 
	 * @return true if the user name entered exists in file, else return false
	 */
	private boolean userNameEntered() {
		// gets the user's "Adventure Name"
		m_user = userAdventureNameTextField.getText();

		// If the user has not entered an "Adventure Name" or have left the
		// field empty
		if ((m_user == null) || (m_user.equals(""))) {
			// inform them to enter a user name to log in to the application
			JOptionPane.showMessageDialog(this, "Enter the User Name", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;

		}

		return true;
	}

	/**
	 * Reads the user information from a file to check if the user name entered
	 * in the log in page exists in the file
	 * 
	 * @param adventureNameText
	 *            the user "Adventure Name"
	 */
	private void readFromFile(JTextField adventureNameText) {

		File databaseFile = new File(UserInfoCreator.getFilePath());

		boolean userFound = false;

		try {

			BufferedReader in = new BufferedReader(new FileReader(databaseFile));

			// read first line
			in.readLine();

			// start reading the file to look for the adventure name
			String line = in.readLine();

			while (line != null) {

				String[] userInfo = line.split(",");

				String adventureName = userInfo[0];

				if (adventureName.equals(adventureNameText.getText())) {

					userFound = true;

					user = createUser(line);

					// at this point, some controller should get the user so
					// that the passport for the user is created and the chosen
					// kit can be started.

					userAccountGUI.createPassport(user);

					in.close();

					break;
				}

				line = in.readLine();

			}
			// if the user is not found in the file

			if (!userFound) {
				// inform the user that this user does not exist
				JOptionPane.showMessageDialog(this, "Sorry but the username "
						+ adventureNameText.getText() + " was not found.");

			}

			in.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Creates a user
	 * 
	 * @param line
	 *            the row in the database file that hold user name, grade and
	 *            kit progress
	 */
	private User createUser(String line) {

		String[] userInfo = line.split(",");

		// The "Adventure Name of the user
		String adventureName = userInfo[0];

		// The user grade
		String grade = userInfo[1];

		// Holds the user's kit progress
		ArrayList<Integer> kitProgress = new ArrayList<Integer>();

		int index = 0;

		// i = 0 : adventure name
		// i = 1 : grade
		// so to look for kit progress we start looking at i = 2
		for (int i = 2; i < userInfo.length; i++) {

			kitProgress.add(Integer.parseInt(userInfo[i]));

			index++;
		}

		User tempUser = new User(adventureName, grade, kitProgress);

		return tempUser;

	}

	/**
	 * Returns the current user
	 * 
	 * @return the current user
	 */
	public User getUser() {
		return user;
	}

}
