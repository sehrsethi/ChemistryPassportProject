import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class Tree extends JComponent{
	

	public void paintComponent(Graphics g){
		
		Graphics2D g2d = (Graphics2D)g ; 
	
		/*Ellipse2D.Double e1 = new Ellipse2D.Double(0,0,50,50) ;
		
		Ellipse2D.Double e2 = new Ellipse2D.Double(0,0,50,50) ;
		
		
		
		g2d.fill(e1);
		g2d.fill(e2);
		
		System.out.println(e1.equals(e2));*/
		
		Tree2 t1 = new Tree2(0, 0, 50) ;
		
		
		
		Tree2 t2 = new Tree2(0, 0, 100) ;
		
		g2d.fill(t1);
		
		g2d.setColor(Color.red);
		
		g2d.fill(t2);
		
		System.out.println(t1.equals(t2));
		
		
	
	}
	
	
public static void main(String[] args) {
		
		//Create a JFrame for the application and give it a size and close operation
		JFrame frame = new JFrame("Estimation game");
		frame.setSize(596, 618);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create the grid and add it to the frame
		
		
		frame.getContentPane().add(new Tree());
		
		//Make the frame visible
		frame.setVisible(true);
		
		frame.setResizable(false);
		
		// close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

private class Tree2 extends Ellipse2D.Double{
	
	private int centerX ;
	
	private int centerY ;
	
	private int diameter ;
	
	private Tree2(int centerX, int centerY, int diameter){
		
		this.centerX = centerX ;
		
		this.centerY = centerY ;
		
		this.diameter = diameter ;
				
		this.height = diameter ;
		
		this.width = diameter ;
		
	}
	
}


}
