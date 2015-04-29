package user_accounts;
import user_accounts.Login;
import user_accounts.SignUp;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import main.ChemGetPropertyValues;

public class GUI extends JPanel {
	
	//Still need to fix issue with royal and writing to file
	//as well as automatically changing file permissions
	
	/**
	 * 
	 * @param userInfoController
	 */
	public GUI(UserInfoController userInfoController){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new Login("Chemistry Passport Program Login Page" , userInfoController));
		JSeparator sep1 = new JSeparator();
		add(sep1);
		add(new SignUp(userInfoController));
	}
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setSize(new Dimension(550,550));
		frame.getContentPane().add(new GUI(new UserInfoController(new ChemGetPropertyValues())));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
