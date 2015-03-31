package beetle_game ;

import java.awt.Graphics;
import java.awt.geom.Point2D;

/**
 * The interface
 * 
 * @author sethi22s
 * 
 */

public interface BeetlePart {

	public void paint(Graphics g);

	public void moveTail(double distanceX, double distanceY);

	public void moveBeetle(double newX , double newY);

	public boolean startsAt(Point2D xAndy);
}
