package user_accounts;
import user.User;
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
import main.ChemistryPassportGUI;

public class UserAccountGUI extends JPanel {
		
	private Login login ;
	
	private ChemistryPassportGUI mainGUI ;
	
	/**
	 * 
	 * @param userInfoCreator
	 * @param mainGUI TODO
	 */
	public UserAccountGUI(UserInfoCreator userInfoCreator, ChemistryPassportGUI mainGUI){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.mainGUI = mainGUI ;
		
		login = new Login(this) ;
		
		add(login);
		
		JSeparator sep1 = new JSeparator();
		
		add(sep1);
		
		add(new SignUp(this));
	}

	
	public void createPassport(User user){
	
		
		mainGUI.createPassport(user);
		
		mainGUI.goToCard(ChemistryPassportGUI.PASSPORT_TEXT);
	}
	
	
	
//	public static void main(String[] args) {
//
//		JFrame frame = new JFrame();
//		frame.setSize(new Dimension(550,550));
//		frame.getContentPane().add(new UserAccountGUI(new UserInfoCreator(new ChemGetPropertyValues()), this));
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//	}
}
