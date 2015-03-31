package beetle_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import com.sun.org.apache.xml.internal.resolver.readers.XCatalogReader;

public class SecondBeetle implements BeetlePart {

	// The beetle head image

	private ImageIcon beetleImage = new ImageIcon("src//images//lady_wings.gif");

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
