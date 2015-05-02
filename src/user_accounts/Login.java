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
 * GUI for constructing the Login and Sign Up Page
 * 
 * @author Sehr Sethi, Humaira Orchee and Charlotte Dye
 * @version April 30th, 2015
 *
 */
public class Login extends JPanel implements ActionListener, KeyListener {


	private JLabel adventureNameLabel = null;
	
	private JTextField adventureNameTextField = null;
	
	private JButton loginButton = null;
	
	private JButton loginCancelButton = null;
	
	private Insets insets;
	
	private JTabbedPane g_tabbedPane = new JTabbedPane();
	
	private JPanel root_panel = null;
	
	private String m_user = null;	
	
	private User user ;
	
	private UserAccountGUI userAccountGUI ;

	/**
	 * 
	 * @param userAccountGUI TODO
	 */
	public Login(UserAccountGUI userAccountGUI) {

		//this.userInfoCreator = userInfoCreator ;
		
		this.userAccountGUI = userAccountGUI ;
		
		loginPage();
		
		

	}

	/**
	 * Constructs the Login Page
	 */
	public void loginPage() {

		root_panel = new JPanel();
		root_panel.setLayout(new BoxLayout(root_panel, BoxLayout.Y_AXIS));
		root_panel.setBorder(BorderFactory.createLineBorder((new Color(150,
				150, 150)), 2));

		JPanel pan2 = new JPanel();
		adventureNameLabel = new JLabel("Adventure Name:  ");
		adventureNameLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		adventureNameTextField = new JTextField(40);
		adventureNameTextField.setFont(new Font("Monospaced", Font.BOLD, 12));
		pan2.add(adventureNameLabel);
		pan2.add(adventureNameTextField);
		adventureNameTextField.addKeyListener(this);
		root_panel.add(pan2);

		JPanel pan5 = new JPanel();
		loginButton = new JButton("  Login  ");
		loginCancelButton = new JButton("  Cancel  ");
		pan5.add(loginButton);
		pan5.add(loginCancelButton);
		insets = pan5.getInsets();
		loginButton.setBounds(330 + insets.left, insets.top, 90, 30);
		loginCancelButton.setBounds(430 + insets.left, insets.top, 90, 30);
		root_panel.add(pan5);

		loginButton.addActionListener(this);
		loginCancelButton.addActionListener(this);

		g_tabbedPane.addTab("Login", null, root_panel, "");

		add(g_tabbedPane);
		this.setBounds(100, 100, 530, 325);
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == loginButton) {

			if (userNameEntered()) {
				this.readFromFile(adventureNameTextField);
			}
		}

		if (evt.getSource() == loginCancelButton){
			System.exit(0);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

			if (userNameEntered()) {

				this.readFromFile(adventureNameTextField);

			}
		}
	}

	private boolean userNameEntered() {
		m_user = adventureNameTextField.getText();

		if ((m_user == null) || (m_user.equals(""))) {
			JOptionPane.showMessageDialog(this, "Enter the User Name", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;

		}

		return true;
	}

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

			if (!userFound) {

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
	 */
	private User createUser(String line) {

		String[] userInfo = line.split(",");

		String adventureName = userInfo[0];

		String grade = userInfo[1];

		ArrayList<Integer> kitProgress = new ArrayList<Integer>();
		
		int index = 0 ;
		
		// i = 0 : adventure name
		// i = 1 : grade
		// so to look for kit progress we start looking at i = 2
		for (int i = 2; i < userInfo.length; i++) {

			kitProgress.add(Integer.parseInt(userInfo[i]));

			index++ ;
		}

		User tempUser = new User(adventureName, grade, kitProgress);

		return tempUser ;

	}

	public User getUser() {
		return user;
	}
	
	
	
	

}
