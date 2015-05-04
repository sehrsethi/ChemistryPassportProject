package beetle_game;

/**
 * The first beetle of the beetle game and its tail parts
 * @author Sehr Sethi, Humaira Orchee and Charlotte Dye
 * @version April 30th, 2015
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class FirstBeetle implements BeetlePart {

	// The location of the first beetle image
	private static final String IMAGE_FILE = "images/lady_wings.gif";

	// Image icon of the beetle
	private ImageIcon firstBeetleImage;

	// The image of the beetle
	private Image beeleImage;

	// The component
	private JComponent component;

	// The current x point of the beetle head
	private double currentX;

	// The current y point of the beetle head
	private double currentY;

	/**
	 * Initializes the instance variables
	 * 
	 * @param component
	 *            The JComponent
	 * @param x
	 *            The x point of the beetle head
	 * @param y
	 *            The y point of the beetle head
	 */
	public FirstBeetle(JComponent component, double x, double y) {

		this.component = component;
		this.currentX = x + 4;
		this.currentY = y + 4;

		// get the beetle image from the build path
		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream(IMAGE_FILE);

			beeleImage = ImageIO.read(input);

			firstBeetleImage = new ImageIcon(beeleImage);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Paints the beetle head image
	 * 
	 * @param g
	 *            the Graphics component
	 */
	public void paint(Graphics g) {
		// paint the first beetle head image
		firstBeetleImage.paintIcon(component, g, (int) currentX + 2,
				(int) currentY + 2);
	}

	/**
	 * Moves the entire beetle with the movement of the end point of the tail
	 * 
	 * @param newX
	 *            the updated X point
	 * @param newY
	 *            the updated Y point
	 */
	public void moveBeetle(double newX, double newY) {

		currentX = newX - firstBeetleImage.getIconWidth() / 2;
		currentY = newY - firstBeetleImage.getIconHeight() / 2;

	}

	/**
	 * Moves the beetle tail
	 * 
	 * @param distanceX
	 *            the X distance of the beetle tail
	 * @param distanceY
	 *            the Y distance of the beetle tail
	 */
	public void moveTail(double distanceX, double distanceY) {

	}

	@Override
	public boolean startsAt(Point2D xAndy) {

		return false;

	}
}