package beetle_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * The beetle tail parts that construct the beetle tail
 * 
 * @author Sehr Sethi, Charlotte Dye and Humaira Orchee
 * @version April 30, 2015
 */

public class BeetlePheromones implements BeetlePart {

	// The first color of the beetle tail part
	public static final Color COLOR1 = new Color(255, 0, 0, 150);

	// The second color of the beetle tail part
	public static final Color COLOR2 = new Color(0, 0, 0, 175);

	// The ellipse2D objects that make the beetle tail
	private Ellipse2D tail;

	// The rest of the beetle, including the beetle tail and the second beetle
	private BeetlePart restBeetlePart;

	// The color of the tail
	private Color color;

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
	public BeetlePheromones(Ellipse2D ellipse, BeetlePart restBeetleTail,
			Color color) {

		this.tail = ellipse;
		this.restBeetlePart = restBeetleTail;
		this.color = color;

	}

	/**
	 * Paints the beetle tails with alternating black and orange color
	 */
	@Override
	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		g.setColor(color);

		g2.fill(tail);

		// if the rest of the beetle has not been constructed, paint the rest of
		// the beetle
		if (restBeetlePart != null) {
			restBeetlePart.paint(g);
		}

		// alternate between two colors for the beetle tail parts
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
		// detect the starting points of the beetle tail
		// get their cordinates
		double originalx = tail.getX();
		double originaly = tail.getY();

		// draw the beetle at those starting points
		tail.setFrame(newX, newY, tail.getWidth(), tail.getHeight());
		restBeetlePart.moveBeetle(originalx, originaly);

	}

	/**
	 * Moves the end point of the tail
	 */
	public void moveTail(double distanceX, double distanceY) {

		tail.setFrame((tail.getX() + distanceX), tail.getY() + distanceY,
				tail.getWidth(), tail.getHeight());

		restBeetlePart.moveBeetle(tail.getX(), tail.getY());

	}

	/**
	 * Starts movement at the end point of the tail
	 */
	@Override
	public boolean startsAt(Point2D xAndy) {
		return tail.contains(xAndy);

	}

}
