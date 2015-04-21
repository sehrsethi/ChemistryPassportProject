package beetle_game ;


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
	private static final String IMAGE_FILE = "images/lady_wings.gif" ;

	// Image icon of the beetle
	private ImageIcon firstBeetleImage ;
	
	// The image of the beetle
	private Image beeleImage ;

	// The component
	private JComponent component;

	// The x point of the beetle head
	private double x;

	// The y point of the beetle head
	private double y;


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
		this.x = x + 4;
		this.y = y + 4;
		

		// get the beetle image from the build path
		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream(IMAGE_FILE);

			beeleImage = ImageIO.read(input);
			
			firstBeetleImage = new ImageIcon(beeleImage) ;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Paints the beetle head image
	 */
	public void paint(Graphics g) {
		
		firstBeetleImage.paintIcon(component, g, (int) x + 2, (int) y + 2);
	}

	/**
	 * Moves the entire beetle with the movement of the end point of the tail
	 */
	public void moveBeetle(double newX, double newY) {
		
		x = newX - firstBeetleImage.getIconWidth() / 2;
		y = newY - firstBeetleImage.getIconHeight() / 2;

	}

	/**
	 * Moves the beetle tail
	 */
	public void moveTail(double distanceX, double distanceY) {

	}

	@Override
	/**
	 * The point where the movement starts
	 */
	public boolean startsAt(Point2D xAndy) {

		return false;

	}
}