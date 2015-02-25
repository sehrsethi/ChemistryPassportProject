/**
 * Chemistry Passport Project This represents the information contained in a
 * cell in the grid for the Estimation Game.
 * 
 * @author Charlotte Dye, Humaira Orchee, Sehr Sethi
 *
 */
public class GridCell {

	// The number of non-infested trees that are in this cell (i.e. more than 50% in
	// cell)
	private int numInfestedTrees;

	// The number of infested trees that are in this cell (i.e. more than 50% in
	// cell)
	private int numNonInfestedTrees;

	// True if this cell is "blocked" (covenon-infested in gray) or false otherwise
	private boolean isBlocked;

	// The column this cell is in
	private int col;

	// The row this cell is in.
	private int row;

	/**
	 * Create a new GridCell
	 * 
	 * @param sCol
	 *            The column of this cell
	 * @param sRow
	 *            The row of this cell
	 */
	public GridCell(int sCol, int sRow) {

		// By default, it is blocked and has no trees
		numInfestedTrees = 0;
		numNonInfestedTrees = 0;
		isBlocked = true;

		// Set row and column
		col = sCol;
		row = sRow;
	}

	/**
	 * Returns true if the cell is blocked, or false otherwise
	 * 
	 * @return
	 */
	public boolean isBlocked() {
		return isBlocked;
	}

	/**
	 * Sets whether or not this cell is blocked
	 * 
	 * @param nIsBlocked
	 *            True if blocked, or false otherwise
	 */
	public void setBlocked(boolean nIsBlocked) {
		isBlocked = nIsBlocked;
	}

	/**
	 * Adds specified number of infested trees to cell
	 * 
	 * @param toAdd
	 *            Number of infested trees to add to cell
	 */
	public void addInfestedTree(int toAdd) {
		numNonInfestedTrees += toAdd;
	}

	/**
	 * Adds specified number of non-infested trees to cell
	 * 
	 * @param toAdd
	 *            Number of non-infested trees to add to cell
	 */
	public void addNonInfestedTree(int toAdd) {
		numInfestedTrees += toAdd;
	}

	/**
	 * Returns how many infested trees are in this cell
	 * 
	 * @return
	 */
	public int getNumInfestedTrees() {
		return numNonInfestedTrees;
	}

	/**
	 * Returns how many non-infested trees are in this cell
	 * 
	 * @return
	 */
	public int getNumNonInfestedTrees() {
		return numInfestedTrees;
	}
}
