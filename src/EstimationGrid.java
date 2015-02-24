

/**
 * Chemistry Passport Project The grid for the estimation game
 * 
 * @author Charlotte Dye, Humaira Orchee, Sehr Sethi
 *
 */
public class EstimationGrid {
	// The number of rows in the grid
	public static final int NUM_ROWS = 7;

	// The number of columns in the grid
	public static final int NUM_COLS = 7;

	// (1/PERCENT_BLOCKED) of cells will be blocked
	private static final int PERCENT_BLOCKED = 3;
	
	private static final int MAX_TREE_NUM = 3 ;

	// The number of trees to put in the grid
	//private static final int NUM_TREES = 10;

	// A 2D array of GridCells, each representing
	// data in a given cell.
	private GridCell[][] cellData;

	/**
	 * Returns the cell data array
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

		

		// Block random cells until we've blocked correct #
//		while (numBlocked < numToBlock) {
//			int row = (int) (Math.random() * NUM_ROWS);
//			int col = (int) (Math.random() * NUM_COLS);
//
//			// Only block if it hasn't been blocked yet
//			if (!cellData[row][col].isBlocked()) {
//				// Block cell
//				cellData[row][col].setBlocked(true);
//
//				// Note that we've blocked another cell
//				numBlocked++;
//			}
//		}

		// Next, we add trees to the unblocked cells
		addNewTrees();

		// Next, we draw the trees

		// Now, we add the border lines and draw the blocked cells
	}

	/**
	 * Randomly adds a red or green tree to the cell data (Note that it does not
	 * actually DRAW the tree)
	 */
	private void addNewTrees() {
		
		// Calculate how many cells to unblock
		int numToUnblock = NUM_ROWS * NUM_COLS / PERCENT_BLOCKED;

		// Keep track of how many blocked cells we've created
		int numUnblocked = 0;

		//int numCreatedTrees = 0;

		//while (numCreatedTrees < NUM_TREES) {
		while (numUnblocked < numToUnblock){
			// Pick randomly red or green--0 is red and 1 is green
			//int colorNum = (int) (Math.random() * 2);

			// Pick randomly which cell
			int rowNum = (int) (Math.random() * NUM_ROWS);
			int colNum = (int) (Math.random() * NUM_COLS);
			
			//Decide how many red trees to generate

			//If this is an unblocked cell, we can add a tree to it.
			if (cellData[rowNum][colNum].isBlocked()) {
				
				//Note that this cell is no longer blocked
				cellData[rowNum][colNum].setBlocked(false);
				
				//Note that we've unblocked another cell
				numUnblocked++;
				
				// Add specified tree color to cell
				//if (colorNum == 0) {
					// Add red tree
					cellData[rowNum][colNum].addRedTree((int)(Math.random()*MAX_TREE_NUM));
				//} else {
					// Add green tree
					cellData[rowNum][colNum].addGreenTree((int)(Math.random()*MAX_TREE_NUM));

				//}
				//numCreatedTrees++;

			}
			
			
		}

	}

}
