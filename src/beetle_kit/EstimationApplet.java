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
/*		 
		//Create a JFrame for the application and give it a size and close operation
				JFrame frame = new JFrame("Estimation game");
				frame.setSize(605, 715);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				//Create the grid and add it to the frame
				//EstimationGrid grid = new EstimationGrid();
				
				//frame.getContentPane().add(new GridView(grid.getCellData()));
				
				frame.getContentPane().add(new EstimationGameApplication()) ;
				
				//Make the frame visible
				frame.setVisible(true);
				
				frame.setResizable(false);
				
				// close operation
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				this.add(frame) ;
				*/
				
	
		this.setSize(598, 680);
		
		this.getContentPane().add(new EstimationGameApplication() ) ;
		
		this.setVisible(true);
		
		this.setMaximumSize(new Dimension(598, 680));
		
		this.setMinimumSize(new Dimension(598, 680));
		
	
		
		
		
	}

}
