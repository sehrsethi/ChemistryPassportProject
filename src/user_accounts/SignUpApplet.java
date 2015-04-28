package user_accounts;

import javax.swing.JApplet;

import cookies.CookieAccessor;

public class SignUpApplet extends JApplet{
	
	public void init(){
		
		setSize(600, 600);
		
		CookieAccessor cookieAccessor = new CookieAccessor() ;
		
		//cookieAccessor.accessCookies();
		
		add(new SignUpGUI(cookieAccessor)) ;
		
	}

}
