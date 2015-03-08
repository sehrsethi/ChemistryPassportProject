package user_accounts;
/**
 * authors: Sehr, Humaira and Charlotte 
 * Simple Login and Sign up Page V.1
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * GUI for constructing the Login and Sign Up Page
 * 
 * @author sethi22s, dye22c, orch22h
 *
 */
public class Login extends JPanel implements ActionListener, KeyListener {

	// Instance variables for Login Page
	
	private static final String FILE_NAME = "database.csv" ; 
	private JLabel login_userNameLabel = null;
	private JTextField login_userNameText = null;
	private JButton loginButton = null;
	private JButton loginCancelButton = null;
	private Insets insets;
	private JTabbedPane g_tabbedPane = new JTabbedPane();
	private JPanel root_panel = null;	
	private String m_user = null;

	/**
	 * Constructor Constructs the GUI
	 * 
	 * @param title
	 *            The title of the page
	 */
	Login(String title) {
		
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
		login_userNameLabel = new JLabel("User Name:  ");
		login_userNameLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		login_userNameText = new JTextField(40);
		login_userNameText.setFont(new Font("Monospaced", Font.BOLD, 12));
		pan2.add(login_userNameLabel);
		pan2.add(login_userNameText);
		login_userNameText.addKeyListener(this);
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
		if (evt.getSource() == loginButton)
			this.checkFields();
			this.readToFile(login_userNameText);
		if (evt.getSource() == loginCancelButton)
			System.exit(0);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER)
			checkFields();
	}

	private void checkFields() {
		m_user = login_userNameText.getText();

		if ((m_user == null) || (m_user.equals(""))) {
			JOptionPane.showMessageDialog(this, "Enter the User Name", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
	

	}
	
	
		
	}
	
private void readToFile(JTextField userNameText ) {
		
		File databaseFile = new File(FILE_NAME) ;
		
		try {
			BufferedReader out = new BufferedReader(new FileReader(databaseFile)) ;
			
			String userName = userNameText.getText() + " ," ;
			
			out.read();
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	



}
