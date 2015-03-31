package beetle_game ;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * The tiger tail parts
 * 
 * @author sethi22s
 * 
 */
//increase circle distance
//include another beetle image at the end of the tail
public class BeetleTailPart implements BeetlePart {

	// The ellipse2D objects that make the tiger tail

	private Ellipse2D tail;

	// The tiger tail

	private BeetlePart beetleTail;

	// The color of the tail

	private Color color;
	//private BeetleHead beetleHead;

	/**
	 * Initializes the instance variables
	 * 
	 * @param ellipse
	 *            the ellipse objects that make up the tiger tail
	 * @param tigerTail
	 *            the tiger tail
	 * @param color
	 *            the color of the tail
	 */
	public BeetleTailPart(Ellipse2D ellipse, BeetlePart tigerTail, Color color) {
		this.tail = ellipse ;
		this.beetleTail = tigerTail;
		this.color = color;
		//this.beetleHead=beetleHead;
		
	}

	/**
	 * Paints the tiger tails with alternating black and orange color
	 */
	@Override
	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g.setColor(color);
		g2.fill(tail);
		if (beetleTail != null) {
			beetleTail.paint(g);
		}
		if (color == Color.red) {
			color = Color.BLACK;
		} else if (color == Color.BLACK) {
			color = Color.red;
		}

	}

	/**
	 * Moves the entire tiger with the movement of the end point of the tail
	 */
	public void moveBeetle(double newX, double newY) {
		double originalx = tail.getX() ;
		double originaly = tail.getY() ;

		tail.setFrame(newX  , newY  , tail.getWidth() , tail.getHeight() );
		beetleTail.moveBeetle(originalx, originaly );


	}

	/**
	 * Moves the end point of the tail
	 */
	public void moveTail(double distanceX, double distanceY) {
		tail.setFrame((tail.getX() + distanceX), tail.getY() + distanceY,
				tail.getWidth(), tail.getHeight());
		beetleTail.moveBeetle(tail.getX() , tail.getY() );
		
		

	}

	/**
	 * Starts movement at the end point of the tail
	 */
	@Override
	public boolean startsAt(Point2D xAndy) {
		return tail.contains(xAndy);

	}


}
