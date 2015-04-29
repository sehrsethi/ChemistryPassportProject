package beetle_game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class SecondBeetle implements BeetlePart {


	// The location of the second beetle image 
	private static final String IMAGE_FILE = "images/lady_wings.gif" ;

	// The image icon of the second beetle  
	private ImageIcon beetleImage ;
	
	// The image of the second beetle  
	private Image image ;

	// The component
	private JComponent component;

	// The x point of the beetle head
	private double xPoint;

	// The y point of the beetle head
	private double yPoint;

	// The beetle tail
	private BeetlePart restBeetleTail;


	public SecondBeetle(JComponent component, double x, double y, BeetlePart restBeetleTail) {

		this.component = component;
		this.xPoint = x - 20;
		this.yPoint = y - 20;
		this.restBeetleTail = restBeetleTail ;
		
		// get the beetle image from the build path
		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream(IMAGE_FILE);

			image = ImageIO.read(input);
			
			beetleImage = new ImageIcon(image) ;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void paint(Graphics g) {
		
		restBeetleTail.paint(g);

		beetleImage.paintIcon(component, g, (int) xPoint, (int) yPoint);

	}

	@Override
	public void moveTail(double distanceX, double distanceY) {

		
		xPoint += distanceX ;
		
		yPoint += distanceY ;
		
		component.repaint();
		
		restBeetleTail.moveTail(distanceX, distanceY);


	}

	@Override
	public void moveBeetle(double newX, double newY) {

	}

	@Override
	public boolean startsAt(Point2D xAndy) {
		
		double diameter = 75 ;

		Ellipse2D circle = new Ellipse2D.Double() ;
		
		double centerX = xPoint + beetleImage.getIconWidth()/2 ;
		
		double centerY = yPoint + beetleImage.getIconHeight()/2 ;
		
		double x = centerX - diameter/2 ;
		
		double y = centerY - diameter/2 ;
		
		circle.setFrame(x,y, diameter, diameter);

		
		return (circle.intersects(xAndy.getX(), xAndy.getY(), 10, 10)) ;
	}

}
