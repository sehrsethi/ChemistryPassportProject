package beetle_kit;

/**
 * Chemistry Passport Project This represents the information contained in a
 * cell in the grid for the Estimation Game.
 * 
 * @author Charlotte Dye, Humaira Orchee, and Sehr Sethi
 * @version April 30, 2015
 */
public class GridCell {

	// The number of non-infested trees that are in this cell (i.e. more than
	// 50% in
	// cell)
	private int numInfestedTrees;

	// The number of infested trees that are in this cell (i.e. more than 50% in
	// cell)
	private int numNonInfestedTrees;

	// True if this cell is "blocked" (covered in gray) or false otherwise
	private boolean isBlocked;

	/**
	 * Create a new GridCell
	 * 
	 * @param sCol
	 *            The column of this cell
	 * @param sRow
	 *            The row of this cell
	 */
	public GridCell(int sCol, int sRow) {

		// By default, it is unblocked and has no trees
		numInfestedTrees = 0;
		numNonInfestedTrees = 0;

		// initially all cells are unblocked
		isBlocked = false;

	}

	/**
	 * Returns true if the cell is blocked, otherwise false
	 * 
	 * @return True if the cell is blocked, otherwise false
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
	 * @return Number of infested trees in this cell
	 */
	public int getNumInfestedTrees() {
		return numInfestedTrees;
	}

	/**
	 * Returns how many non-infested trees are in this cell
	 * 
	 * @return Number of non-infested trees in this cell
	 */
	public int getNumNonInfestedTrees() {
		return numNonInfestedTrees;
	}

	/**
	 * Decrement the number of infested trees in the cell unless the number is 0
	 * 
	 * @return False when numInfestedTrees is already 0; true if it succeeds
	 */
	public boolean decrementNumInfested() {

		// If there is at least one infested tree, decrement number of infested
		// trees and return true
		if (numInfestedTrees > 0) {
			numInfestedTrees--;
			return true;
		} else {
			// Otherwise, just return false
			return false;
		}
	}

	/**
	 * Decrement the number of non-infested trees in the cell unless the number
	 * is 0
	 * 
	 * @return False when numNonInfestedTrees is already 0; true if it succeeds
	 */
	public boolean decrementNumNonInfested() {

		// If there is at least one non-infested tree, decrement the number of
		// non-infested trees and return true
		if (numNonInfestedTrees > 0) {
			numNonInfestedTrees--;
			return true;
		} else {
			// Otherwise, just return false
			return false;
		}
	}
}
