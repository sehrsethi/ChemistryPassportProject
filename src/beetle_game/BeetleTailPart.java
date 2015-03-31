package beetle_game ;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * The beetle tail parts
 * 
 * @author sethi22s
 * 
 */
//increase circle distance
//include another beetle image at the end of the tail
public class BeetleTailPart implements BeetlePart {
	
	// The ellipse2D objects that make the beetle tail

	private Ellipse2D tail;

	// The beetle tail

	private BeetlePart restBeetleTail;

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
	public BeetleTailPart(Ellipse2D ellipse, BeetlePart restBeetleTail, Color color) {
		
		
		this.tail = ellipse ;
		this.restBeetleTail = restBeetleTail;
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
		
		if (restBeetleTail != null) {
			restBeetleTail.paint(g);
		}
		
		if (color == Color.red) {
			
			color = Color.BLACK;
			
		} else if (color == Color.BLACK) {
			
			color = Color.red;
		}

	}

	/**
	 * Moves the entire beetle with the movement of the end point of the tail
	 */
	public void moveBeetle(double newX, double newY) {
		
		System.out.println("beetle tail part move beetle");
		
		double originalx = tail.getX() ;
		double originaly = tail.getY() ;

		tail.setFrame(newX  , newY  , tail.getWidth() , tail.getHeight() );
		
		restBeetleTail.moveBeetle(originalx, originaly );


	}

	/**
	 * Moves the end point of the tail
	 */
	public void moveTail(double distanceX, double distanceY) {
		
		System.out.println("beetle tail part move tail");
		
		tail.setFrame((tail.getX() + distanceX), tail.getY() + distanceY,
				tail.getWidth(), tail.getHeight());
		
		restBeetleTail.moveBeetle(tail.getX() , tail.getY() );
		
		

	}

	/**
	 * Starts movement at the end point of the tail
	 */
	@Override
	public boolean startsAt(Point2D xAndy) {
		return tail.contains(xAndy);

	}


}
