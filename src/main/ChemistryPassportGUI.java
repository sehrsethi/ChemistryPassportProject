package main;

import java.awt.CardLayout;

import javax.swing.JPanel;

import passport.Passport;
import user.User;
import user_accounts.UserAccountGUI;
import user_accounts.UserInfoCreator;

/**
 * 
 * @author Humaira Orchee, Sehr Sethi, and Charlotte Dye
 * @version April 30, 2015
 */

public class ChemistryPassportGUI extends JPanel {

	// The layout of the GUI
	private static final CardLayout CARD_LAYOUT = new CardLayout();

	// The name by which the Passport panel is referred to in the overall layout
	public static final String PASSPORT_TEXT = "Passport";

	// The name by which the UserAccountGUI panel (which contains the Login and
	// SignUp GUIs) is referred to in the overall layout
	public static final String USER_ACCOUNT_TEXT = "User Account";

	// The passport object
	private Passport passport;

	// The GUI containing the Login and SignUp GUIs
	private UserAccountGUI userAccountGUI;

	// The object that provides the information from the config.properties file
	private ChemGetPropertyValues propValues;

	/**
	 * Creates the overall GUI for the Chemistry Passport Program
	 * 
	 * @param propValues
	 *            The object that provides the information from the
	 *            config.properties file
	 */
	public ChemistryPassportGUI(ChemGetPropertyValues propValues) {

		this.propValues = propValues;

		this.setLayout(CARD_LAYOUT);

		this.setSize(ChemistryPassport.PAGE_WIDTH,
				ChemistryPassport.PAGE_HEIGHT);

		// add the GUI containing the Login and SignUp GUIs
		userAccountGUI = new UserAccountGUI(new UserInfoCreator(propValues),
				this);
		this.add(userAccountGUI, USER_ACCOUNT_TEXT);

	}

	/**
	 * Creates the passport for the current user
	 * 
	 * @param user
	 *            The current user for whom the passport has to be created
	 */
	public void createPassport(User user) {

		passport = new Passport(user, this);

		this.add(passport, PASSPORT_TEXT);
	}

	/**
	 * Goes to the next card of the CARD_LAYOUT
	 */
	public void nextCard() {
		CARD_LAYOUT.next(this);
		repaint();
	}

	/**
	 * Goes to the previous card of the CARD_LAYOUT
	 */
	public void previousCard() {
		CARD_LAYOUT.last(this);
		repaint();
	}

	/**
	 * Goes to the specified card of the CARD_LAYOUT
	 * 
	 * @param cardName
	 *            The name of the card to go to
	 */
	public void goToCard(String cardName) {

		CARD_LAYOUT.show(this, cardName);
		repaint();
	}

	/**
	 * Returns the passport of the current user
	 * 
	 * @return The passport of the current user
	 */
	public Passport getPassport() {
		return passport;
	}

	/**
	 * Returns the object that provides the information from the
	 * config.properties file
	 * 
	 * @return The object that provides the information from the
	 *         config.properties file
	 */
	public ChemGetPropertyValues getPropValues() {
		return propValues;
	}

}
