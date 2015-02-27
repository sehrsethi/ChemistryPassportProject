
/**
 * Chemistry Passport Project The grid for the estimation game
 * 
 * @author Charlotte Dye, Humaira Orchee, Sehr Sethi
 *
 */
public class EstimationGrid {
	// The number of rows in the grid
	public static final int NUM_ROWS = 4;

	// The number of columns in the grid
	public static final int NUM_COLS = 4;

	// (1/PERCENT_BLOCKED) of cells will be blocked
	private static final int RATIO_BLOCKED = 3;

	//The maximum number of trees of each color that can be placed in one cell
	private static final int MAX_TREE_NUM = 2;

	// A 2D array of GridCells, each representing
	// data in a given cell.
	private GridCell[][] cellData;
	
	// total number of infested trees in the entire grid
	private int totalInfested ;
	
	// total number of non-infested trees in the entire grid
	private int totalNonInfested ;
	

	/**
	 * Returns the cell data array
	 * 
	 * @return
	 */
	public GridCell[][] getCellData() {
		return cellData;
	}

	/**
	 * Creates the grid
	 */
	public EstimationGrid() {
		// First, we create the GridCell
		// objects and the data for this grid
		cellData = new GridCell[NUM_ROWS][NUM_COLS];

		// Create the cells
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLS; j++) {
				
				cellData[i][j] = new GridCell(i, j);
			}
		}

		// Next, we add trees to the unblocked cells
		addNewTrees();

	}

	/**
	 * Randomly adds a non-infested or infested tree to the cell data (Note that it does not
	 * actually DRAW the tree)
	 */
	private void addNewTrees() {

		// Calculate how many cells to unblock--might need to edit this
		//1/3rd of the cells should be blocked
		int numToUnblock = (NUM_ROWS * NUM_COLS / RATIO_BLOCKED);

		// Keep track of how many unblocked cells we've created
		int numUnblocked = 0;

		// While we haven't unblocked enough cells
		while (numUnblocked < numToUnblock) {

			// Pick randomly which cell to unblock (and add trees to)
			int rowNum = (int) ( Math.random() * NUM_ROWS);
			int colNum = (int) ( Math.random() * NUM_COLS);
			
			// If this is a blocked cell, we unblock it and add random trees.
			if (cellData[rowNum][colNum].isBlocked()) {

				// Note that this cell is no longer blocked
				cellData[rowNum][colNum].setBlocked(false);

				// Note that we've unblocked another cell
				numUnblocked++;

				// Add a random number of non-infested and infested trees
				
				int numNonInfested = (int) (Math.random() * MAX_TREE_NUM);
				
				// update the total number of non-infested trees in the grid
				totalNonInfested += numNonInfested ;
				
				cellData[rowNum][colNum]
						.addNonInfestedTree(numNonInfested);

				int numInfested = (int) (Math.random() * MAX_TREE_NUM);
				
				// update the total number of infested trees in the grid
				totalInfested += numInfested ;
				
				cellData[rowNum][colNum]
						.addInfestedTree(numInfested );
			}

		}

	}

	/**
	 * Returns the total number of infested trees in the grid
	 * @return Total number of infested trees in the grid
	 */
	public int getTotalInfested() {
		return totalInfested;
	}

	/**
	 * Return the total number of non-infested trees in the grid
	 * @return Total number of non-infested trees in the grid
	 */
	public int getTotalNonInfested() {
		return totalNonInfested;
	}
	
	

}
