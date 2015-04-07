package main;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import beetle_kit.BeetleKitApplication;
import passport.Passport;
import user.User;

public class ChemistryPassportGUI extends JPanel{
	
	//The layout
	private static final CardLayout CARD_LAYOUT = new CardLayout();
	
	private static final String PASSPORT_TEXT = "Passport";
	
	public static final String BEETLE_KIT_TEXT = "Beetle Kit";

	public ChemistryPassportGUI(){
		this.setLayout(CARD_LAYOUT);
		
		//Add the passport
		this.setSize(Passport.PAGE_WIDTH, Passport.PAGE_HEIGHT);
		 
		 ArrayList<Integer> kitProgress = new ArrayList<Integer>() ;
		 kitProgress.add(5) ;
		
		User user = new User("user name", "long Fake Name Fake", "K", kitProgress) ;
		
		this.add(new Passport(user, this), PASSPORT_TEXT) ;
		
		//Add the beetle kit
		this.add(new BeetleKitApplication(), BEETLE_KIT_TEXT);
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
	}

}