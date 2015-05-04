package beetle_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

/**
 * The user interface for the Beetle reward game
 * 
 * @author Sehr Sethi, Charlotte Dye and Humaira Orchee
 * 
 * @version April 30, 2015
 */

public class Beetle extends JComponent implements MouseListener,
		MouseMotionListener {

	private static final int DIAMETER = 25;

	// The interface beetlePart
	private BeetlePart beetlePart;

	// The beetle tail parts
	private BeetlePheromones beetlePheromones;

	// Checks if the tail is in drawing mode
	private boolean drawingTail = false;

	// Checks if in moving mode
	private boolean isMoving = false;

	// The last x and y point of the tail
	private Point2D lastXandY;

	/**
	 * Adds mouse and mouseMotion listeners
	 */
	public Beetle() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		// if the beetle tail has not been drawn
		if (beetlePart == null) {
			// start the beetle tail chain
			startChain(e.getPoint());
			// draw the beetle tail chain
			drawingTail = true;

		}

		else if (beetlePart.startsAt(e.getPoint())) {

			isMoving = true;
		}

		lastXandY = e.getPoint();

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		// if the user releases the mouse, stop drawing the tail
		if (drawingTail) {

			endTail(e.getPoint());

			drawingTail = false;

		}

		isMoving = false;

	}

	@Override
	public void mouseDragged(MouseEvent e) {

		// if the user drags the mouse
		if (drawingTail) {
			// keep drawing the chain of the beetle tail
			Ellipse2D.Double ellipse = new Ellipse2D.Double(e.getX(), e.getY(),
					DIAMETER, DIAMETER);
			addLink(ellipse);

			// if the user drags the mouse, detect movement
		} else if (isMoving) {

			// move the beetle chain tail with the movement of the mouse
			beetlePart.moveTail(e.getX() - (int) lastXandY.getX(), e.getY()
					- (int) lastXandY.getY());

			repaint();

		}
		lastXandY = e.getPoint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	/**
	 * Constructs a new beetle head
	 * 
	 * @param xandy
	 *            stats the chain of the beetle tail at these starting points
	 */
	public void startChain(Point2D xandy) {

		beetlePart = new FirstBeetle(this, xandy.getX(), xandy.getY());
	}

	/**
	 * Adds the beetle tail parts
	 * 
	 * @param ellipse
	 *            the ellipse 2D points that construct the beetle tail
	 */
	public void addLink(Ellipse2D ellipse) {

		beetlePart = new BeetlePheromones(ellipse, beetlePart,
				BeetlePheromones.COLOR1);
		repaint();

	}

	/**
	 * Detect the point where the tail ends
	 * 
	 * @param endPoint
	 *            the end point of the tail
	 */
	public void endTail(Point2D endPoint) {

		beetlePart = new SecondBeetle(this, endPoint.getX(), endPoint.getY(),
				beetlePart);
		repaint();
	}

	/**
	 * Draws the current frame. This consists of a rectangle for the background
	 * 
	 * @param g
	 *            the graphics
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Dimension rect = getSize();

		g.setColor(Color.white);

		g.fillRect(0, 0, (int) (rect.getWidth()), (int) rect.getHeight());

		if (beetlePart != null) {
			beetlePart.paint(g);
		}

	}

}