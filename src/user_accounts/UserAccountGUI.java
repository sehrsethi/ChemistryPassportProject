package user_accounts;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import main.ChemistryPassportGUI;
import user.User;

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
