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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class SignUp extends JPanel implements ActionListener, KeyListener {

	// Instance variables for Sign Up Page
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
	Insets insets;

	private JTabbedPane g_tabbedPane = new JTabbedPane();
	// private JPanel root_panel = new JPanel();
	private JPanel root_panel_inside_tabbedPane = new JPanel();

	private String getUserName = null;
	private String getFakeName = null;
	private String[] m_grade = null;

	private String gradeFromComboBox = "";

	public SignUp() {

		BoxLayout boxLayout = new BoxLayout(root_panel_inside_tabbedPane,
				BoxLayout.Y_AXIS);
		root_panel_inside_tabbedPane.setLayout(boxLayout);

		signUp();
	}

	public void signUp() {

		BoxLayout boxLayout = new BoxLayout(root_panel_inside_tabbedPane,
				BoxLayout.Y_AXIS);
		root_panel_inside_tabbedPane.setLayout(boxLayout);

		// root_panel_inside_tabbedPane.setLayout(new BoxLayout(
		// root_panel_inside_tabbedPane, BoxLayout.Y_AXIS));
		root_panel_inside_tabbedPane.setLayout(new GridLayout(7, 1));
		root_panel_inside_tabbedPane.setBorder(BorderFactory.createLineBorder(
				(new Color(150, 150, 150)), 3));

		g_tabbedPane.add(root_panel_inside_tabbedPane);

		JPanel pan11 = new JPanel();
		signUpLabel = new JLabel("         Not A User Yet? Sign Up Below!  ");
		signUpLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		pan11.add(signUpLabel);
		root_panel_inside_tabbedPane.add(new JPanel());
		root_panel_inside_tabbedPane.add(pan11);

		JPanel pan12 = new JPanel();
		signup_FakeNameLabel = new JLabel("Fake Name:  ");
		signup_FakeNameLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		signup_FakeNameText = new JTextField(45);
		signup_FakeNameText.setFont(new Font("Monospaced", Font.BOLD, 12));

		signup_FakeNameText.setFont(new Font("Monospaced", Font.BOLD, 12));

		signup_FakeNameText.addKeyListener(this);
		root_panel_inside_tabbedPane.add(pan12);

		JPanel pan13 = new JPanel();
		signUp_UserNameLabel = new JLabel("User Name:  ");
		signUp_UserNameLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		signUp_UserNameText = new JTextField(45);
		signUp_UserNameText.setFont(new Font("Monospaced", Font.BOLD, 12));

		signUp_UserNameText.setFont(new Font("Monospaced", Font.BOLD, 12));

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

		JPanel pan8 = new JPanel();
		// pan8.setLayout(null);
		submitButton = new JButton("  Submit  ");
		pan8.add(submitButton);
		Insets insets2 = pan8.getInsets();
		submitButton.setBounds(430 + insets2.left, insets2.top, 90, 30);
		root_panel_inside_tabbedPane.add(pan8);
		submitButton.addActionListener(this);

		g_tabbedPane.addTab("Sign Up", null, root_panel_inside_tabbedPane, "");

		// root_panel_inside_tabbedPane.add(g_tabbedPane);
		// g_tabbedPane.add(root_panel_inside_tabbedPane) ;

		// JSeparator sep1 = new JSeparator();
		// root_panel.add(sep1);

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

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == submitButton)
			
			if (checkSignUp()) {

				if(writeToFile(signup_FakeNameText, signUp_UserNameText,
						gradeFromComboBox)){
					JOptionPane.showMessageDialog(this, "User account for "
							+ signup_FakeNameText.getText() + " has been created. Cheers!");
					
					System.exit(0);

					
				}
				
				
			}

		

	}

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

	private boolean writeToFile(JTextField fullNameText, JTextField userNameText,
			String grade) {

		File databaseFile = new File(FILE_NAME);

		String kitProgress = textKitProgress();

		try {

			BufferedWriter out = new BufferedWriter(new FileWriter(
					databaseFile, true));

			String userName = userNameText.getText();

			if (userNameExists(userName)) {

				JOptionPane.showMessageDialog(this,
						"Someone already has this username: " + userName
								+  ". Let's pick another one!");
				
				return false ;
			}
			
			userName += userName + "," ;

			String fakeName = fullNameText.getText() + ",";

			// grade = (JComboBox) grade.getSelectedItem();
			out.write("\n" + userName + fakeName + grade + "," + kitProgress);

			out.close();
			
			return true ;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false ;
	}

	/**
	 * @param databaseFile
	 * @return
	 */
	private String textKitProgress() {

		File databaseFile = new File(FILE_NAME);

		try {
			BufferedReader in = new BufferedReader(new FileReader(databaseFile));

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

	private boolean userNameExists(String userName) {

		File databaseFile = new File(FILE_NAME);

		try {

			BufferedReader in = new BufferedReader(new FileReader(databaseFile));

			List<String> lines = Files.readAllLines(databaseFile.toPath(),
					StandardCharsets.UTF_8);

			for (String line : lines) {
				
				String[] lineArray = line.split(",") ;
				
				System.out.println(line);

				System.out.println(lineArray[0]);
				
				String tempUserName = lineArray[0];
				
				System.out.println(tempUserName.trim() + "|and|" + userName.trim());
				
				if (tempUserName.trim().equals(userName.trim())) {

					System.out.println("return true for " + tempUserName + "and" + userName);
					return true;
				}

			}

			System.out.println("return false");
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

	// ChoiceDialog extends JDialog implements ActionListener {
	//
	// private Login owner = null;
	// private JComboBox g_combo = null;
	// private JButton g_ok = null;
	//
	// hoiceDialog(String[] users, Frame owner) {
	// super(owner, "Choose UserName", true);
	// this.owner = (Login) owner;
	//
	// JPanel pan = new JPanel();
	// pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
	//
	// JLabel lab = new JLabel("Multiple Entries Found : Choose UserName");
	// lab.setFont(new Font("Halvetica", Font.BOLD, 16));
	// // lab.setForeground( new Color(250, 100, 100) );
	// lab.setAlignmentX(Component.CENTER_ALIGNMENT);
	//
	// g_combo = new JComboBox(users);
	// g_combo.setFont(new Font("Monospaced", Font.BOLD, 12));
	// g_combo.setEnabled(true);
	// g_combo.setAlignmentX(Component.CENTER_ALIGNMENT);
	// g_combo.setBorder(new javax.swing.border.LineBorder((new Color(120,
	// 120, 120)), 3));
	//
	// JSeparator sep = new JSeparator();
	//
	// pan.add(lab);
	// pan.add(g_combo);
	// pan.add(sep);
	//
	// g_ok = new JButton("     Login     ");
	// g_ok.setAlignmentX(Component.CENTER_ALIGNMENT);
	// g_ok.setFont(new Font("Halvetica", Font.BOLD, 16));
	// pan.add(g_ok);
	// g_ok.addActionListener(this);
	//
	// this.getContentPane().add(pan);
	// this.setBounds(150, 150, 450, 150);
	// this.setVisible(true);
	//
	// }

	//
	// public void actionPerformed(ActionEvent evt) {
	// String UserName = null;
	// USerName = (String) g_combo.getSelectedItem();
	// this.hide();
	// return;
	//
}