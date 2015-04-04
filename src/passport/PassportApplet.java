package passport;

import javax.swing.JApplet;

public class PassportApplet extends JApplet{
	
	public void init(){
		
		
		this.setSize(Passport.PAGE_WIDTH, Passport.PAGE_HEIGHT);
		
		this.add(new Passport("Pretend child")) ;
	}

}
