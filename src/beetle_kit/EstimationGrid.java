package beetle_kit;

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

	// 2/3 rd of the trees are blocked. Note 2/3 truncates to 0. That is why write 2.0/3.0
	private static final double RATIO_BLOCKED = 2.0/3.0 ;

	//The maximum number of trees of each color that can be placed in one cell
	private static final int MAX_TREE_NUM = 2;

	// A 2D array of GridCells, each representing
	// data in a given cell.
	private GridCell[][] cellData;
	
	// total number of infested trees in the entire grid
	private int totalInfested ;
	
	// total number of non-infested trees in the entire grid
	private int totalNonInfested ;
	
	// total number of infested trees in the unblocked cells in the entire grid
	private int totalUnblockedInfested ;
		
	// total number of non-infested trees in the unblocked cells in the entire grid
	private int totalUnblockedNonInfested ;
	

	

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
		

		// Next, we add trees to every cell
		addNewTrees();
		
		blockCells(); 

		countTrees() ;
		
	
	}
	
	/**
	 * Called by AnswerPanel when the user
	 * has successfully completed a round and is
	 * ready to move on to the next round
	 */
	public void startNextRound(){
		//Need to complete and then call this method
		//Still need to decide which class is in charge of
		//checking what round we're on (and thus
		//whether it's time to start a new round
		//or whether they're done and it's time for the reward)
		//Also not 100% sure this method is the best way to do it
		//Also, do we need to keep track of whether they got the answers
		//right on the first try?  Do we want to give them
		//an option to skip a grid and try the next one?
		//Do they need to get three in a row correct on the
		//first try to get a sticker?  If not,
		
	}

	/**
	 * Adds a non-infested or infested tree to every cell data (Note that it does not
	 * actually DRAW the tree)
	 */
	private void addNewTrees() {

		for(int r = 0 ; r < NUM_ROWS ; r++){
			
			for(int c = 0 ; c < NUM_COLS ; c++){
				
				GridCell cell = cellData[r][c] ;
						
				cell.addNonInfestedTree((int) (Math.random() * MAX_TREE_NUM));

				cell.addInfestedTree((int) (Math.random() * MAX_TREE_NUM) );
			}
		}
	}

	/**
	 * 
	 */
	private void blockCells() {
		// Calculate how many cells to unblock--might need to edit this
		//1/3rd of the cells should be blocked
		double numToBlock = (NUM_ROWS * NUM_COLS * RATIO_BLOCKED);

		// Keep track of how many unblocked cells we've created
		int numBlocked = 0;

		// While we haven't unblocked enough cells
		while (numBlocked < numToBlock) {

			// Pick randomly which cell to unblock (and add trees to)
			int rowNum = (int) ( Math.random() * NUM_ROWS);
			int colNum = (int) ( Math.random() * NUM_COLS);
			
			// If this is an unblocked cell, we block it
			if (!cellData[rowNum][colNum].isBlocked()) {

				// Note that this cell is now blocked
				cellData[rowNum][colNum].setBlocked(true);

				// Note that we've unblocked another cell
				numBlocked++;
			}
		}

				
	}
	
	private void countTrees(){
		

		for(int r = 0 ; r < EstimationGrid.NUM_ROWS ; r++){
			
			for(int c = 0 ; c < EstimationGrid.NUM_COLS ; c++){
				
				GridCell cell = cellData[r][c] ;
				
				// if cell is not blocked, then count trees in unblocked cells
				if(!cell.isBlocked()){
					
					totalUnblockedInfested += cell.getNumInfestedTrees() ;
					
					totalUnblockedNonInfested += cell.getNumNonInfestedTrees() ;
				}
				
				// count trees for all (blocked and unblocked) cells
				
				totalInfested += cell.getNumInfestedTrees() ;
				
				totalNonInfested += cell.getNumNonInfestedTrees() ;
				
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

	/**
	 * 
	 * @return
	 */
	public int getTotalUnblockedInfested() {
		return totalUnblockedInfested;
	}


	/**
	 * 
	 * @return
	 */
	public int getTotalUnblockedNonInfested() {
		return totalUnblockedNonInfested;
	}

	
	
	
	
	

}
