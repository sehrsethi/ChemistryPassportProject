package beetle_game;

import javax.swing.JApplet;

public class BeetleGameApplet extends JApplet{
	
	public void init(){
		
		Beetle beetle = new Beetle();
		
		this.add(beetle) ;
	}

}
