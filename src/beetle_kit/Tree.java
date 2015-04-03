package beetle_kit;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

/**
 * A Tree object that is represented by a circle
 * 
 * @author Humaira Orchee, Charlotte Dye
 * @version April 3, 2015
 *
 */
public class Tree extends Ellipse2D.Double {
	
	private static final int MAX_ATTEMPT = 10 ;

	// The diameter of the circular tree
	private double diameter;

	// The fill color of the tree
	private Color fillColor;

	// The border color of the tree
	private Color borderColor;

	// The x coordinate of the center of the tree
	public int centerX;

	// The y coordinate of the center of the tree
	public int centerY;

	private int attempt = 0 ;

	/**
	 * Constructs a circular tree
	 * 
	 * @param centerX
	 *            The x coordinate of the center of the tree
	 * @param centerY
	 *            The y coordinate of the center of the tree
	 * @param diameter
	 *            The diameter of the tree
	 * @param fillColor
	 *            The fill color of the tree
	 * @param borderColor
	 *            The border color of the tree
	 */
	public Tree(int centerX, int centerY, double diameter, Color fillColor,
			Color borderColor) {

		super();

		this.centerX = centerX;

		this.centerY = centerY;

		this.diameter = diameter;

		// Since the tree is circular, its height = width = diameter
		this.height = diameter;

		this.width = diameter;

		// Since tree extends Ellipse2D.Double, calculate top x and y
		// coordinates with the provided center coordinates and diameter
		this.x = centerX - diameter / 2;

		this.y = centerY - diameter / 2;

		this.fillColor = fillColor;

		this.borderColor = borderColor;

	}

	/**
	 * Returns the fill color of the tree
	 * 
	 * @return The fill color of the tree
	 */
	public Color getFillColor() {
		return fillColor;
	}

	/**
	 * Returns the border color of the tree
	 * 
	 * @return the border color of the tree
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * 
	 * I DONT THINK WE NEED THIS METHOD ANYMORE BECAUSE THE CHECKOVERLAPP METHOD
	 * SHOULD TAKE CARE OF THIS ALREAY
	 * 
	 * Returns true if this tree is contained within another tree. Otherwise
	 * returns false.
	 * 
	 * @param otherTree
	 *            The other Tree object that might contain this tree
	 * @return True if this tree is contained within another tree. Otherwise
	 *         returns false.
	 */
	public boolean containedWithin(Tree otherTree) {

		/**
		 * Let outer parentheses be other tree. Let inner parentheses be this
		 * tree . (()) - in this situation, returns true
		 */
		return ((this.x >= otherTree.x)
				&& (this.x + this.diameter <= otherTree.x + otherTree.diameter)
				&& (this.y >= otherTree.y) && (this.y + this.diameter <= otherTree.y
				+ otherTree.diameter));

	}

	/**
	 * Returns true if this tree has unacceptable amount of overlap. Otherwise
	 * returns false.
	 * 
	 * @param otherTree
	 *            The other Tree object that could be overlapping too much
	 * @return
	 */
	public boolean checkOverlap(Tree otherTree) {
		
		//System.out.println("attempt " + attempt);
		
		// Compare sum of radii to distance between centers

		double radiiSum = (this.diameter / 2) + (otherTree.diameter / 2);
		
		//System.out.println("radiiSum " + radiiSum);

		double diffX = Math.pow(otherTree.centerX - this.centerX, 2);

		//System.out.println("diffX " + diffX);
		
		double diffY = Math.pow(otherTree.centerY - this.centerY, 2);
		
		//System.out.println("diffY " + diffY);

		double distance = Math.sqrt(diffX + diffY);

		//System.out.println("distance " + distance);
		
		//System.out.println("overlap " + (distance < (radiiSum / 1.2)));
		
		// Compare sum of radii to distance between centers
		
		//return (distance < (radiiSum / 1.2));
		
		//true if this tree has unacceptable amount of overlap. Otherwise false.
		boolean overlap = (distance) < ((2.0/3.0)*radiiSum ) ;
		
		
		if( (overlap) && (attempt < MAX_ATTEMPT)){
			
			diameter = diameter*0.95 ;
			attempt++ ;
			//checkOverlap(this) ;
			
			if(checkOverlap(this)){
				
				return true ;
				
			}else{
				
				return false ;
			}
			
		}
		
		
		
		//Check overlap again
		overlap = (distance) < ((2.0/3.0)*radiiSum);
		
		//If it's still overlapping, then don't place this tree
		if (overlap){
			return true;
		}
		
		//attempt = 0 ;
		
		//If it is no longer overlapping, return false
		return false ;
		
		//boolean hidden = containedWithin(otherTree) || otherTree.containedWithin(this) ;
		
		//return hidden ;
		//return overlap || hidden;
		
		//return false ;

	}

	
}
