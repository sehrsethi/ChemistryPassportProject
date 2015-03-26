package beetle_kit;
import java.awt.Dimension;

import javax.swing.JApplet;

/**
 * Creates an applet fro EstimationGridApplication
 * @author Charlotte, Humaira, Sehr
 * @version March 4, 2015
 *
 */
public class EstimationApplet extends JApplet {
	
	public void init(){		
	
		this.setSize(598, 680);
		
		this.getContentPane().add(new EstimationGameApplication() ) ;
		
		this.setVisible(true);
		
		this.setMaximumSize(new Dimension(598, 680));
		
		this.setMinimumSize(new Dimension(598, 680));

		
	}

}
