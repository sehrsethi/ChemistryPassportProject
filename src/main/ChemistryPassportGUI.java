package main;

import java.awt.CardLayout;
import javax.swing.JPanel;

import passport.Passport;
import user.User;
import user_accounts.UserAccountGUI;
import user_accounts.UserInfoCreator;

public class ChemistryPassportGUI extends JPanel{
	
	//The layout
	private static final CardLayout CARD_LAYOUT = new CardLayout();
	
	public static final String PASSPORT_TEXT = "Passport";
	
	public static final String USER_ACCOUNT_TEXT = "User Account" ;
	
	private Passport passport ;
	
	private UserAccountGUI userAccountGUI ;
	
	private ChemGetPropertyValues propValues ;
	
	//public static final String BEETLE_KIT_TEXT = "Beetle Kit";
	
	
	
	public ChemistryPassportGUI(ChemGetPropertyValues propValues){
		
		this.propValues = propValues ;
		
		this.setLayout(CARD_LAYOUT);
		
		//Add the passport
		this.setSize(Passport.PAGE_WIDTH, Passport.PAGE_HEIGHT);
		
		userAccountGUI = new UserAccountGUI(new UserInfoCreator(propValues), this) ;
		
		this.add(userAccountGUI, USER_ACCOUNT_TEXT);
		
		//User user = userAccountGUI.getUser() ;
		 
//		 ArrayList<Integer> kitProgress = new ArrayList<Integer>() ;
//		 kitProgress.add(5) ;
//		
//		User user = new User("long Fake Name Fake", "K", kitProgress) ;
		
		
		//Add the beetle kit
		//this.add(new BeetleKitApplication(), BEETLE_KIT_TEXT);
	}
	
	/**
	 * 
	 * @param user
	 */
	public void createPassport(User user){
		
		passport = new Passport(user, this) ;
		
		this.add(passport, PASSPORT_TEXT) ;
	}
	
	public void nextCard(){
		CARD_LAYOUT.next(this);
		repaint();
	}
	
	public void previousCard(){
		CARD_LAYOUT.last(this);
		repaint();
	}
	
	public void goToCard(String pageName){

		CARD_LAYOUT.show(this, pageName);
		repaint() ;
	}

	public Passport getPassport() {
		return passport;
	}

	public ChemGetPropertyValues getPropValues() {
		return propValues;
	}
	
	

	
	
}
