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
 * @version April 30th, 2015
 *
 */
public class SignUp extends JPanel implements ActionListener, KeyListener {

	//Adds the text "Sign Up" to the Sign Up GUI
	private JLabel signUpLabel = null;
	
	//Adds the text "Grade" to the Sign Up GUI
	private JLabel gradeLabel = null;
	
	//Adds the text "Adventure Name" to the Sign Up GUI
	private JLabel userAdventureNameLabel = null;
	
	//Allows the user to enter their "Adventure Name"
	private JTextField userAdventureNameTextField = null;
	
	//The button for submitting the sign up button
	private JButton submitButton = null;
	
	//Allows the user to select their grade level from a drop down of available grade levels
	private JComboBox<String> gradeComboBox = null;
	
	//Holds the grade levels for the users
	private String[] grades = { "" , "K", "1", "2", "3", "4", "5", "6" };
	
	//Holds the current user's grade level
	private String userGrade ;

	// The main sign up panel that contains all the sign up information and the sign up title
	private JTabbedPane g_tabbedPane = new JTabbedPane();
	
	// Panel for JButtons and Jlabel with sign up tab
	private JPanel root_panel_inside_tabbedPane = new JPanel();
	
	private UserAccountGUI userAccountGUI ;
	
	private User user ;
	
	/**
	 * 
	 * @param userAccountGUI TODO
	 */
	public SignUp(UserAccountGUI userAccountGUI) {

		//this.userInfoCreator = userInfoCreator;
		
		this.userAccountGUI = userAccountGUI ;

		BoxLayout boxLayout = new BoxLayout(root_panel_inside_tabbedPane,
				BoxLayout.Y_AXIS);
		root_panel_inside_tabbedPane.setLayout(boxLayout);
		signUp();

	}

	/**
	 * Creates labels and buttons for sign up to ask for user information
	 */
	public void signUp() {

		BoxLayout boxLayout = new BoxLayout(root_panel_inside_tabbedPane,
				BoxLayout.Y_AXIS);
		root_panel_inside_tabbedPane.setLayout(boxLayout);
		root_panel_inside_tabbedPane.setLayout(new GridLayout(7, 1));
		root_panel_inside_tabbedPane.setBorder(BorderFactory.createLineBorder(
				(new Color(150, 150, 150)), 3));

		g_tabbedPane.add(root_panel_inside_tabbedPane);

		// The panel that holds the label to ask users to sign up
		JPanel pan11 = new JPanel();
		signUpLabel = new JLabel("         Not A User Yet? Sign Up Below!  ");
		signUpLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		pan11.add(signUpLabel);
		root_panel_inside_tabbedPane.add(new JPanel());
		root_panel_inside_tabbedPane.add(pan11);

		// The panel that requests the user to input a Adventure Name
		JPanel pan12 = new JPanel();
		userAdventureNameLabel = new JLabel("Adventure Name:  ");
		userAdventureNameLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		userAdventureNameTextField = new JTextField(45);
		userAdventureNameTextField.setFont(new Font("Monospaced", Font.BOLD, 12));

		userAdventureNameTextField.setFont(new Font("Monospaced", Font.BOLD, 12));

		userAdventureNameTextField.addKeyListener(this);
		root_panel_inside_tabbedPane.add(pan12);

		// The panel that asks the user to input a user name

		// JPanel pan13 = new JPanel();
		// signUp_UserNameLabel = new JLabel("User Name:  ");
		// signUp_UserNameLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		// signUp_UserNameText = new JTextField(45);
		// signUp_UserNameText.setFont(new Font("Monospaced", Font.BOLD, 12));

		// signUp_UserNameText.setFont(new Font("Monospaced", Font.BOLD, 12));

		// The panel that asks the user to select a grade level
		JPanel pan14 = new JPanel();
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

				userGrade = (String) gradeComboBox
						.getSelectedItem();

			}
		});

		pan12.add(userAdventureNameLabel);
		pan12.add(userAdventureNameTextField);

		// pan13.add(signUp_UserNameLabel);
		// pan13.add(signUp_UserNameText);

		pan14.add(gradeLabel);
		pan14.add(gradeComboBox);
		gradeComboBox.setFont(new Font("Monospaced", Font.BOLD, 14));

		gradeComboBox.addKeyListener(this);
		// root_panel_inside_tabbedPane.add(pan13);

		// signUp_UserNameText.addKeyListener(this);

		root_panel_inside_tabbedPane.add(pan14);

		// The panel that holds the submit information button
		JPanel pan8 = new JPanel();
		submitButton = new JButton("  Submit  ");
		pan8.add(submitButton);
		Insets insets2 = pan8.getInsets();
		submitButton.setBounds(430 + insets2.left, insets2.top, 90, 30);
		root_panel_inside_tabbedPane.add(pan8);
		submitButton.addActionListener(this);

		// Add to the main sign up panel
		g_tabbedPane.addTab("Sign Up", null, root_panel_inside_tabbedPane, "");
		add(g_tabbedPane);

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Once the submit button has been pressed, get the user information and
	 * write to the database
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == submitButton)

			if (checkSignUp()) {

				if (writeToFile(userAdventureNameTextField, userGrade)) {
					
					JOptionPane.showMessageDialog(this, "User account for "
							+ userAdventureNameTextField.getText()
							+ " has been created. Cheers!");
					
					
					userAccountGUI.createPassport(user);
					


				}

			}

	}

	/**
	 * Checks if the user name and Adventure Name already exist in the database
	 * 
	 * @return
	 */

	private boolean checkSignUp() {
		// getUserName = signUp_UserNameText.getText();
		// m_grade = signUp_GradeCombo.getSelectedItem();

		if ((userAdventureNameTextField.getText() == null) || userAdventureNameTextField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Enter the Adventure Name", "Error",
					JOptionPane.ERROR_MESSAGE);

			return false;

		}else if(userAdventureNameTextField.getText().contains(",")){
			
			JOptionPane.showMessageDialog(this, "Adventure Name cannot have a ','. Try again!", "Error",
					JOptionPane.ERROR_MESSAGE);

			return false;

		}
		
		
		else if ((userGrade == null)
				|| (userGrade.equals(""))) {
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

		File databaseFile = new File(UserInfoCreator.getFilePath());

		String kitProgress = createKitProgressString();

		try {
			
			// the file should be un-hidden before writing to file
			UserInfoCreator.setHideFile(databaseFile, false);

			BufferedWriter out = new BufferedWriter(new FileWriter(
					databaseFile, true));

			String adventureName = adventureNameText.getText();

			if (adventureNameExists(adventureName)) {

				JOptionPane.showMessageDialog(this,
						"Someone already has this username: " + adventureName
								+ ". Let's pick another one!");
				
				out.flush();

				out.close();
				
				// the file should be hidden after writing to file
				UserInfoCreator.setHideFile(databaseFile, true);

				return false;
			}


			
			//adventureName += ",";

			// grade = (JComboBox) grade.getSelectedItem();
			out.write("\n" + adventureName + "," + grade + "," + kitProgress);

			out.flush();

			out.close();
	
			// the file should be hidden after writing to file
			UserInfoCreator.setHideFile(databaseFile, true);
			
			user = new User(adventureName, grade, kitProgress) ;
			
			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}


	/**
	 * For each kit in the program, it sets the user's progress to 0.
	 * @return
	 */
	private String createKitProgressString() {

		File databaseFile = new File(UserInfoCreator.getFilePath());

		try {
			
			BufferedReader in = new BufferedReader(new FileReader(databaseFile));


			String[] firstLine = in.readLine().split(",");

			// the first line in the file has the Adventure Name, Grade and then all the names of the existing Kits
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
	 * 
	 * @return
	 */
	private String autogenerateUserName() {
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

			// don't care about the first line because it just contains the column headers
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