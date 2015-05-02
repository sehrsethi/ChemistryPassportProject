package user_accounts;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import main.ChemistryPassportGUI;
import user.User;

/**
 * At the start of the application, the UserAccountGUI displays both the login
 * GUI and the sign-up GUI.
 * 
 * @author Humaira Orchee, Sehr Sethi, Charlotte Dye
 * @version May 2, 2015
 */
public class UserAccountGUI extends JPanel {

	// The login screen
	private Login login;

	// The main gui of the entire Chemistry Passport Application
	private ChemistryPassportGUI mainGUI;

	/**
	 * Creates a GUI that will display both the login GUI and the sign-up GUI
	 * 
	 * @param userInfoCreator
	 *            The class that will create the file containing the user
	 *            information
	 * @param mainGUI
	 *            The main gui of the entire Chemistry Passport Application that
	 *            this GUI is added to
	 */
	public UserAccountGUI(UserInfoCreator userInfoCreator,
			ChemistryPassportGUI mainGUI) {

		this.mainGUI = mainGUI;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// create and add the login GUI
		login = new Login(this);
		add(login);

		// create and add the separator between the login and sign-up GUIs
		JSeparator sep1 = new JSeparator();
		add(sep1);

		// create and add the sign-up GUI
		add(new SignUp(this));
	}

	/**
	 * Asks the main GUI to create the user's passport once the user has signed
	 * up or logged in
	 * 
	 * @param user
	 *            The user currently using the Chemistry Passport Program
	 */
	public void createPassport(User user) {

		mainGUI.createPassport(user);

		mainGUI.goToCard(ChemistryPassportGUI.PASSPORT_TEXT);
	}

}
