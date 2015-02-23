/**
 * Chemistry Passport Project
 * This represents the information contained in a cell
 * in the grid for the Estimation Game.
 * @author Charlotte Dye, Humaira Orchee, Sehr Sethi
 *
 */
public class GridCell {
	
	//The number of red trees that are in this cell (i.e. more than 50% in cell)
	private int numRedTrees;
	
	//The number of green trees that are in this cell (i.e. more than 50% in cell)
	private int numGreenTrees;
	
	//True if this cell is "blocked" (covered in gray) or false otherwise
	private boolean isBlocked;
	
	//The column this cell is in
	private int col;
	
	//The row this cell is in.
	private int row;
	
}
