package beetle_kit;
import java.awt.Color;
import java.awt.geom.Ellipse2D;


/**
 * A Tree object that is represented by a circle
 * @author Humaira
 *
 */
public class Tree extends Ellipse2D.Double {

	//private int centerX;

	//private int centerY;

	// the diamater of the circular tree
	private int diameter;
	
	private Color fillColor ;
	
	private Color borderColor ;

	/**
	 * Constructs a circular tree
	 * @param centerX The x coordinate of the center of the tree
	 * @param centerY The y coordinate of the center of the tree
	 * @param diameter The diameter of the tree
	 */
	public Tree(int centerX, int centerY, int diameter, Color fillColor, Color borderColor) {

		super();

		//this.centerX = centerX;

		//this.centerY = centerY;

		this.diameter = diameter;

		// Since the tree is circular, its height = width = diameter
		this.height = diameter;

		this.width = diameter;

		// Since tree extends Ellipse2D.Double, calculate top x and y coordinates with the provided center coordinates and diameter
		this.x = centerX - diameter / 2;

		this.y = centerY - diameter / 2;
		
		this.fillColor = fillColor ;
		
		this.borderColor = borderColor ;

	}
	
	

	/**
	 * 
	 * @return
	 */
	public Color getFillColor() {
		return fillColor;
	}


	/**
	 * 
	 * @return
	 */
	public Color getBorderColor() {
		return borderColor;
	}



	/**
	 * Returns true if this tree is contained within another tree. Otherwise returns false.
	 * @param otherTree The other Tree object that might contain this tree
	 * @return True if this tree is contained within another tree. Otherwise returns false.
	 */
	public boolean containedWithin(Tree otherTree) {

		/** Let outer parentheses be other tree. 
		 * 	Let inner parentheses be this tree .
		 *  (()) - in this situation, returns true
		 */
		return ((this.x >= otherTree.x)
				&& (this.x + this.diameter <= otherTree.x + otherTree.diameter)
				&& (this.y >= otherTree.y) && (this.y + this.diameter <= otherTree.y
				+ otherTree.diameter));

	}

}


