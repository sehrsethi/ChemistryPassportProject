package passport;

import java.util.ArrayList;

import javax.swing.JApplet;

import user.User;

/**
 * 
 * @version April 4, 2015
 *
 */
public class PassportApplet extends JApplet{
	
	public void init(){
		
		
		this.setSize(Passport.PAGE_WIDTH, Passport.PAGE_HEIGHT);
		 
		 ArrayList<Integer> kitProgress = new ArrayList<Integer>() ;
		 kitProgress.add(5) ;
		
		User user = new User("user name", "long Fake Name Fake", "K", kitProgress) ;
		
		this.add(new Passport(user)) ;
	}

}
