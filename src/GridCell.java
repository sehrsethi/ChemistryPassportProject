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
	
	/**
	 * Create a new GridCell
	 * @param sCol The column of this cell
	 * @param sRow The row of this cell
	 */
	public GridCell(int sCol, int sRow){
		
		//By default, it is unblocked and has no trees
		numRedTrees = 0;
		numGreenTrees = 0;
		isBlocked = false;
		
		//Set row and column
		col = sCol;
		row = sRow;
	}
	
	/**
	 * Returns true if the cell is blocked, or false otherwise
	 * @return
	 */
	public boolean isBlocked(){
		return isBlocked;
	}
	
	/**
	 * Sets whether or not this cell is blocked
	 * @param nIsBlocked True if blocked, or false otherwise
	 */
	public void setBlocked(boolean nIsBlocked){
		isBlocked = nIsBlocked;
	}
	
}
