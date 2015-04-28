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
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class SignUp extends JPanel implements ActionListener, KeyListener {

	// Instance variables for Sign Up Page

	// The database file that contains user information
	private static final String FILE_NAME = "database.csv";
	private JLabel signUpLabel = null;
	private JLabel signUp_Grade = null;
	private JLabel signUp_UserNameLabel = null;
	private JTextField signUp_UserNameText = null;
	private JLabel signup_FakeNameLabel = null;
	private JTextField signup_FakeNameText = null;
	private JButton submitButton = null;
	private JComboBox<String> signUp_GradeCombo = null;
	private String[] grades = { "", "K", "1", "2", "3", "4", "5", "6" };

	// The main sign up panel
	private JTabbedPane g_tabbedPane = new JTabbedPane();
	// Panel for JButtons and Jlabels with sign up tab
	private JPanel root_panel_inside_tabbedPane = new JPanel();

	private String getUserName = null;
	private String getFakeName = null;
	private String gradeFromComboBox = "";

	// The location of the database
	private URL url_database;

	/**
	 * Constructs a sign up panel
	 */
	public SignUp() {

		BoxLayout boxLayout = new BoxLayout(root_panel_inside_tabbedPane,
				BoxLayout.Y_AXIS);
		root_panel_inside_tabbedPane.setLayout(boxLayout);

		// Set the database location
		try {
			url_database = new URL(
					"http://royal.cs.mtholyoke.edu/orche22h/database.csv");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

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

		// The panel that requests the user to input a fake name
		JPanel pan12 = new JPanel();
		signup_FakeNameLabel = new JLabel("Fake Name:  ");
		signup_FakeNameLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		signup_FakeNameText = new JTextField(45);
		signup_FakeNameText.setFont(new Font("Monospaced", Font.BOLD, 12));

		signup_FakeNameText.setFont(new Font("Monospaced", Font.BOLD, 12));

		signup_FakeNameText.addKeyListener(this);
		root_panel_inside_tabbedPane.add(pan12);

		// The panel that asks the user to input a user name
		JPanel pan13 = new JPanel();
		signUp_UserNameLabel = new JLabel("User Name:  ");
		signUp_UserNameLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		signUp_UserNameText = new JTextField(45);
		signUp_UserNameText.setFont(new Font("Monospaced", Font.BOLD, 12));

		signUp_UserNameText.setFont(new Font("Monospaced", Font.BOLD, 12));

		// The panel that asks the user to select a grade level
		JPanel pan14 = new JPanel();
		signUp_Grade = new JLabel("Grade:  ");
		signUp_Grade.setFont(new Font("Monospaced", Font.PLAIN, 12));

		signUp_GradeCombo = new JComboBox<String>(grades);
		signUp_GradeCombo.setFont(new Font("Monospaced", Font.BOLD, 12));
		signUp_GradeCombo.setEnabled(true);
		signUp_GradeCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
		signUp_GradeCombo.setPreferredSize(new Dimension(300, 30));
		signUp_GradeCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				gradeFromComboBox = (String) signUp_GradeCombo
						.getSelectedItem();

			}
		});

		pan12.add(signup_FakeNameLabel);
		pan12.add(signup_FakeNameText);

		pan13.add(signUp_UserNameLabel);
		pan13.add(signUp_UserNameText);

		pan14.add(signUp_Grade);
		pan14.add(signUp_GradeCombo);
		signUp_GradeCombo.setFont(new Font("Monospaced", Font.BOLD, 14));

		signUp_GradeCombo.addKeyListener(this);
		root_panel_inside_tabbedPane.add(pan13);

		signUp_UserNameText.addKeyListener(this);
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

				if (writeToFile(signup_FakeNameText, signUp_UserNameText,
						gradeFromComboBox)) {
					JOptionPane.showMessageDialog(this, "User account for "
							+ signup_FakeNameText.getText()
							+ " has been created. Cheers!");

					System.exit(0);

				}

			}

	}

	/**
	 * Checks if the user name and fake name already exist in the database
	 * 
	 * @return
	 */

	private boolean checkSignUp() {
		getFakeName = signup_FakeNameText.getText();
		getUserName = signUp_UserNameText.getText();
		// m_grade = signUp_GradeCombo.getSelectedItem();

		if ((getFakeName == null) || getFakeName.equals("")) {
			JOptionPane.showMessageDialog(this, "Enter the Fake Name", "Error",
					JOptionPane.ERROR_MESSAGE);

			return false;

		} else if ((getUserName == null) || (getUserName.equals(""))) {
			JOptionPane.showMessageDialog(this, "Enter the User Name", "Error",
					JOptionPane.ERROR_MESSAGE);

			return false;

		} else if ((gradeFromComboBox == null)
				|| (gradeFromComboBox.equals(""))) {
			JOptionPane.showMessageDialog(this, "Enter the Grade", "Error",
					JOptionPane.ERROR_MESSAGE);

			return false;

		}

		return true;

	}

	/**
	 * Writes user information to the database and URL
	 * 
	 * @param fakeNameText
	 *            the fake name of the user entered
	 * @param userNameText
	 *            the user name of the user
	 * @param grade
	 *            the selected grade level
	 * @return
	 */
	private boolean writeToFile(JTextField fakeNameText,
			JTextField userNameText, String grade) {

		File databaseFile = new File(FILE_NAME);

		String kitProgress = testKitProgress();

		try {

			// BufferedWriter out = new BufferedWriter(new FileWriter(
			// databaseFile, true));

			// BufferedWriter out = new BufferedWriter()

			String userName = userNameText.getText();

			if (userNameExists(userName)) {

				JOptionPane.showMessageDialog(this,
						"Someone already has this username: " + userName
								+ ". Let's pick another one!");

				return false;
			}

			userName += ",";

			String fakeName = fakeNameText.getText() + ",";

			// Connect to the database on the server
			URLConnection connection = url_database.openConnection();

			connection.setDoOutput(true);
			connection.setUseCaches(false);

			// connection.
			
			//DataOutputStream dos = new DataOutputStream(connection.getOutputStream()) ;

			
			
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());
			
			

			// grade = (JComboBox) grade.getSelectedItem();
			out.write("\n" + userName + fakeName + grade + "," + kitProgress);
			
			String message = "\n" + userName + fakeName + grade + "," + kitProgress ;
			
			//dos.writeBytes(message);
			//dos.flush() ;
			//dos.close() ;
			
			out.flush();
			
			// System.out.println("I am here");
			

			
			out.close();
			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	



	/**
	 * Checks the kit progress of the user and writes to database
	 * 
	 * @param databaseFile
	 * @return
	 */
	private String testKitProgress() {

		File databaseFile = new File(FILE_NAME);

		try {
			// BufferedReader in = new BufferedReader(new
			// FileReader(databaseFile));

			BufferedReader in = new BufferedReader(new InputStreamReader(
					url_database.openStream()));

			String[] firstLine = in.readLine().split(",");

			int numKits = firstLine.length - 3;

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
	 * @param userName
	 *            the user name entered
	 * @return
	 */
	private boolean userNameExists(String userName) {

		File databaseFile = new File(FILE_NAME);

		try {

			// BufferedReader in = new BufferedReader(new
			// FileReader(databaseFile));

			BufferedReader in = new BufferedReader(new InputStreamReader(
					url_database.openStream()));

			in.readLine();
			String line = in.readLine();
			while (line != null) {
				String[] userInfo = line.split(",");
				String temp_userName = userInfo[0];
				if (temp_userName.trim().equals(userName.trim())) {
					return true;
				} else {
					line = in.readLine();
				}
			}
			return false;

			// //List<String> lines = Files.readAllLines(databaseFile.toPath(),
			// // StandardCharsets.UTF_8);
			//
			// for (String line : lines) {
			//
			// String[] lineArray = line.split(",") ;
			//
			// String tempUserName = lineArray[0];
			//
			//
			// if (tempUserName.trim().equals(userName.trim())) {
			//
			// System.out.println("return true for " + tempUserName + "and" +
			// userName);
			// return true;
			// }
			//
			// }
			//
			// System.out.println("return false");
			// return false;

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