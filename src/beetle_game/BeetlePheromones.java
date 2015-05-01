package beetle_game ;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * The beetle tail parts
 * 
 * @author Sehr Sethi, Charlotte Dye and Humaira Orchee
 * 
 */

public class BeetlePheromones implements BeetlePart {
	
	//public static final Color COLOR1 = new Color(153,255,255) ;
	
	//public static final Color COLOR2 = new Color(204,204,255) ;
	
	public static final Color COLOR1 = new Color(255,0,0, 150) ;
	
	public static final Color COLOR2 =  new Color(0,0,0, 175) ;
	
	// The ellipse2D objects that make the beetle tail

	private Ellipse2D tail;

	// The beetle tail

	private BeetlePart restBeetlePart;

	// The color of the tail

	private Color color;
	//private BeetleHead beetleHead;

	/**
	 * Initializes the instance variables
	 * 
	 * @param ellipse
	 *            the ellipse objects that make up the beetle tail
	 * @param restBeetleTail
	 *            The rest of the beetle tail
	 * @param color
	 *            the color of the tail
	 */
	public BeetlePheromones(Ellipse2D ellipse, BeetlePart restBeetleTail, Color color) {
		
		
		this.tail = ellipse ;
		this.restBeetlePart = restBeetleTail;
		this.color = color;
		//this.beetleHead=beetleHead;
		
	}

	/**
	 * Paints the beetle tails with alternating black and orange color
	 */
	@Override
	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(color);
		
		g2.fill(tail);
		
		if (restBeetlePart != null) {
			restBeetlePart.paint(g);
		}
	
		
		if (color == COLOR1) {
			
			color = COLOR2;
			
		} else if (color == COLOR2) {
			
			color = COLOR1;
		}

	}

	/**
	 * Moves the entire beetle with the movement of the end point of the tail
	 */
	public void moveBeetle(double newX, double newY) {
				
		double originalx = tail.getX() ;
		double originaly = tail.getY() ;

		tail.setFrame(newX  , newY  , tail.getWidth() , tail.getHeight() );
		
		restBeetlePart.moveBeetle(originalx, originaly );


	}

	/**
	 * Moves the end point of the tail
	 */
	public void moveTail(double distanceX, double distanceY) {
		
		
		tail.setFrame((tail.getX() + distanceX), tail.getY() + distanceY,
				tail.getWidth(), tail.getHeight());
		
		restBeetlePart.moveBeetle(tail.getX() , tail.getY() );
		
		

	}

	/**
	 * Starts movement at the end point of the tail
	 */
	@Override
	public boolean startsAt(Point2D xAndy) {
		return tail.contains(xAndy);

	}


}
