package beetle_kit;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Chemistry Passport Project. The grid data for the Estimation Game
 * 
 * @author Charlotte Dye, Humaira Orchee, Sehr Sethi
 * @version April 3, 2015
 *
 */
public class EstimationGrid {

	// The number of rows in the grid
	public static final int NUM_ROWS = 7;

	// The number of columns in the grid
	public static final int NUM_COLS = 7;

	// 2/3 rd of the trees are blocked. Note 2/3 truncates to 0. That is why
	// write 2.0/3.0
	private static final double RATIO_BLOCKED = 2.0 / 3.0;

	// The maximum number of trees of each color that can be placed in one cell

	private static final double MAX_TREE_NUM = 15; 

	// Color of infested trees
	private static final Color INFESTED_COLOR = Color.RED;

	// Border Color of infested trees
	private static final Color INFESTED_BORDER_COLOR = new Color(153, 0, 0);

	// Color of non-infested trees
	private static final Color NON_INFESTED_COLOR = Color.GREEN;

	// Border color of non-infested trees
	private static final Color NON_INFESTED_BOREDR_COLOR = new Color(0, 51, 0);

	// The height of the grid
	public static final int GRID_HEIGHT = 600;

	// The width of the grid
	public static final int GRID_WIDTH = 600;

	// It is best not to have the center of the trees at the very edges of the
	// cell. This variable stores the distance of the center of each tree from
	// the edges of the cell
	private static final int PADDING = 15;

	// A 2D array of GridCells, each representing
	// data in a given cell.
	private GridCell[][] gridCells;

	// total number of infested trees in the entire grid
	private int totalInfested;

	// total number of non-infested trees in the entire grid
	private int totalNonInfested;

	// total number of infested trees in the unblocked cells in the entire grid
	private int totalUnblockedInfested;

	// total number of non-infested trees in the unblocked cells in the entire
	// grid
	private int totalUnblockedNonInfested;

	// List of all the trees in the grid
	private ArrayList<Tree> trees;

	// The width of each cell
	private int cellWidth;

	// The height of each cell
	private int cellHeight;

	// the number of tree currently in the grid
	// private int numTrees = 0 ;

	/**
	 * Creates the grid
	 */
	public EstimationGrid() {

		trees = new ArrayList<Tree>();

		cellWidth = GRID_WIDTH / NUM_COLS;
		cellHeight = GRID_HEIGHT / NUM_ROWS;

		// First, we create the GridCell
		// objects and the data for this grid
		gridCells = new GridCell[NUM_ROWS][NUM_COLS];

		// Create the cells
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLS; j++) {

				gridCells[i][j] = new GridCell(i, j);
			}
		}

		// Next, we add trees to every cell
		addNewTrees();

		blockCells();

		//countTrees();

	}

	/**
	 * Returns the width of each cell
	 * 
	 * @return The width of each cell
	 */
	public int getCellWidth() {
		return cellWidth;
	}

	/**
	 * Returns the height of each cell
	 * 
	 * @return The height of each cell
	 */
	public int getCellHeight() {
		return cellHeight;
	}

	/**
	 * Returns the array list of trees
	 * 
	 * @return The array list of trees
	 */
	public ArrayList<Tree> getTrees() {
		return trees;
	}

	/**
	 * Returns the cell data array
	 * 
	 * @return The cell data array
	 */
	public GridCell[][] getGridCells() {
		return gridCells;
	}

	/**
	 * Creates trees in all the cells (blocked and unblocked) and puts them in
	 * an array list of trees
	 */
	public void fillTreeArray() {

		// create trees initially
		for (int r = 0; r < gridCells.length; r++) {
			for (int c = 0; c < gridCells[r].length; c++) {

				// creates trees in that cell
				createTreeInCell(gridCells[r][c], r, c);

			}
		}

		// //system.out.println("total number of trees " + numTrees);
		
		countTrees();
	}

	/**
	 * 
	 * Draws infested and non-infested trees in a cell. Randomly selects whether
	 * to draw infested trees first on non-infested trees first.
	 * 
	 * @param toDraw
	 *            The cell to draw on
	 * @param row
	 *            The row of the grid the cell is in
	 * @param col
	 *            The column of the grid that cell is in
	 */
	private void createTreeInCell(GridCell toDraw, int row, int col) {
		
		//system.out.println("(" + row + " , " + col + " )");

		if (Math.random() <= 0.5) {

			// draw infested trees first

			int numInfestedTrees = 0;

			while (toDraw.getNumInfestedTrees() > numInfestedTrees) {

				// check if the tree was actually drawn
				boolean drawn = createTree(INFESTED_COLOR,
						INFESTED_BORDER_COLOR, row, col);

				////system.out.println("drawn " + drawn);

				if (drawn) {

				//	//system.out.println("draw tree");
					numInfestedTrees++;

					// numTrees++ ;

					// //system.out.println("numInfestedTrees "+
					// numInfestedTrees);
				}

				// At this point, we've tried to draw a tree and failed
				else {
					// We need to do something here so it accepts that it can't
					// draw
					// a tree

					////system.out.println("do not draw tree");

					//numInfestedTrees++;
					
					//toDraw.decrementNumInfested() ;
					
					if(!toDraw.decrementNumInfested()){
						
						break ;
					}
					
					
				}
				
				//system.out.println("tree has " + toDraw.getNumInfestedTrees() + " infested trees");
			}

			// then draw non-infested trees
			int numNonInfestedTrees = 0;

			while (toDraw.getNumNonInfestedTrees() > numNonInfestedTrees) {

				// check if the tree was actually drawn
				boolean drawn = createTree(NON_INFESTED_COLOR,
						NON_INFESTED_BOREDR_COLOR, row, col);

				if (drawn) {
					numNonInfestedTrees++;

					// numTrees++ ;

					// //system.out.println("numNonInfestedTrees " +
					// numNonInfestedTrees);
				}else{
					
					//numNonInfestedTrees++;
					
					if(!toDraw.decrementNumNonInfested()){
						
						break ;
					}
				}
				
				//system.out.println("tree has " + toDraw.getNumNonInfestedTrees() + " non infested trees");
			}
			
			//system.out.println("this tree has " + toDraw.getNumInfestedTrees() + " infested trees");
			//system.out.println("this tree has " + toDraw.getNumNonInfestedTrees() + " non infested trees");

		} else {

			// draw non-infested trees first

			int numNonInfestedTrees = 0;

			while (toDraw.getNumNonInfestedTrees() > numNonInfestedTrees) {

				// check if the tree was actually drawn
				boolean drawn = createTree(NON_INFESTED_COLOR,
						NON_INFESTED_BOREDR_COLOR, row, col);
				if (drawn) {

					numNonInfestedTrees++;

					// numTrees++ ;

					// //system.out.println("numNonInfestedTrees " +
					// numNonInfestedTrees);

				}else{
					
					if(!toDraw.decrementNumNonInfested()){
						
						break ;
					}
					
					//numNonInfestedTrees++;
				}
				
				//system.out.println("tree has " + toDraw.getNumNonInfestedTrees() + " non infested trees");
			}

			// then draw infested trees
			int numInfestedTrees = 0;

			while (toDraw.getNumInfestedTrees() > numInfestedTrees) {

				// check if the tree was actually drawn
				boolean drawn = createTree(INFESTED_COLOR,
						INFESTED_BORDER_COLOR, row, col);

				if (drawn) {

					numInfestedTrees++;

					// numTrees++ ;

					// //system.out.println("numInfestedTrees "+
					// numInfestedTrees);

				}else{
					
					if(!toDraw.decrementNumInfested()){
						
						break ;
					}
					
					//numInfestedTrees++;
				}
				
				//system.out.println("tree has " + toDraw.getNumInfestedTrees() + " infested trees");
			}
			
			//system.out.println("this tree has " + toDraw.getNumInfestedTrees() + " infested trees");
			//system.out.println("this tree has " + toDraw.getNumNonInfestedTrees() + " non infested trees");

		}

	}

	/**
	 * Draw trees on the grid. More than 50% of the tree should be in the cell
	 * specified by the row and column. If it tries to draw a tree such that one
	 * tree will be completely hidden behind another tree, then does nothing and
	 * returns false.
	 * 
	 * @param fillColor
	 *            The body color of the tree according to whether or not the
	 *            tree is infested or not infested.
	 * @param borderColor
	 *            The border color of the tree according to whether or not the
	 *            tree is infested or not infested.
	 * @param row
	 *            The row of the cell containing the bulk of the tree
	 * @param col
	 *            The column of the cell containing the bulk of the tree
	 * @return True if a tree was successfully drawn. Otherwise returns false.
	 */
	private boolean createTree(Color fillColor, Color borderColor, int row,
			int col) {

		// dimensions of the tree
		int minDimension = Math.min(cellHeight, cellWidth);

		double diameter = (int) ((minDimension / 2) + Math.random()
				* (minDimension * 1.25));

		// center coordinates of the tree. its best if the center is not on the
		// edges
		int centerX = (int) ((PADDING + (col * cellWidth)) + (Math.random() * (cellWidth - 2 * PADDING)));

		int centerY = (int) ((PADDING + (row * cellHeight)) + (Math.random() * (cellHeight - 2 * PADDING)));
		
//		int centerX = (int) ((5+ (col * cellWidth)) + ((Math.random() * cellWidth )-5));
//
//		int centerY = (int) ((5+ (row * cellHeight)) + ((Math.random() * cellHeight )-5));

		// the tree to draw
		Tree tree = new Tree(centerX, centerY, diameter, fillColor, borderColor);

		// if the tree we are trying to draw will completely hide another
		// existing tree or the tree itself will be completely hidden by an
		// existing tree, then do nothing.
		// if (checkTreeOverlap(tree) || isTreeHidden(tree)) {

		if (!canDrawTree(tree)) {
			// ////system.out.println("tree overlap so no tree drawn");

			return false;

		} else {

			trees.add(tree);

			// //system.out.println("added trees. size is " + trees.size());

			////system.out.println(" tree drawn");

			////system.out.println("array size " + trees.size());

			// a tree was drawn so return true
			return true;
		}
	}

	/**
	 * Return whether or not one tree will completely hide another tree
	 * 
	 * @param The
	 *            tree object that might hide another tree or be hidden by
	 *            another tree
	 * @return True if a tree is hidden completely by another. Otherwise returns
	 *         false.
	 */
	private boolean isTreeHidden(Tree tree) {

		// //system.out.println("size " + trees.size() );

		// look though the entire array of trees
		for (int i = 0; i < trees.size(); i++) {

			// ////system.out.println("i " + i);

			Tree otherTree = trees.get(i);

			// boolean overlaps = tree.checkOverlap(otherTree);

			boolean hidden = otherTree.containedWithin(tree)
					|| tree.containedWithin(otherTree);


			// if the specified tree is hidden by another existing tree or hides
			// an existing tree, return true.

			// if ( hidden || overlaps ) {

			// a tree is hidden by another tree
			if (hidden) {

				////system.out.println("tree is hidden");
				return true;
			}
		}

		////system.out.println("tree is not hidden");
		// no tree is hidden by another tree
		return false;
	}

	/**
	 * 
	 * @param tree
	 * @return
	 */
	private boolean checkTreeOverlap(Tree tree) {

		// //system.out.println("size " + trees.size() );

		// look though the entire array of trees
		for (int i = 0; i < trees.size(); i++) {

			// ////system.out.println("i " + i);

			Tree otherTree = trees.get(i);

			/*
			 * boolean overlaps = otherTree.checkOverlap(tree) ||
			 * tree.checkOverlap(otherTree);
			 */

			boolean overlaps = tree.checkOverlap(otherTree);

			// boolean hidden = otherTree.containedWithin(tree)
			// || tree.containedWithin(otherTree);

			// if the specified tree is hidden by another existing tree or hides
			// an existing tree, return true.

			// if ( hidden || overlaps ) {

			if (overlaps) {

				return true;
			}
		}

		// no tree is hidden by another tree
		return false;
	}

	/**
	 * Called by AnswerPanel when the user has successfully completed a round
	 * and is ready to move on to the next round
	 */
	public void startNextRound() {
		// Need to complete and then call this method
		// Still need to decide which class is in charge of
		// checking what round we're on (and thus
		// whether it's time to start a new round
		// or whether they're done and it's time for the reward)
		// Also not 100% sure this method is the best way to do it
		// Also, do we need to keep track of whether they got the answers
		// right on the first try? Do we want to give them
		// an option to skip a grid and try the next one?
		// Do they need to get three in a row correct on the
		// first try to get a sticker? If not,

	}

	/**
	 * Adds 0-MAX_TREE_NUM non-infested or infested tree to every cell data
	 * (Note that it does NOT actually DRAW the tree)
	 */
	private void addNewTrees() {

		for (int r = 0; r < NUM_ROWS; r++) {

			for (int c = 0; c < NUM_COLS; c++) {

				GridCell cell = gridCells[r][c];

				if (Math.random() < 0.5) {

					cell.addNonInfestedTree((int) (Math.random() * MAX_TREE_NUM) + 1);

					cell.addInfestedTree((int) (Math.random() * MAX_TREE_NUM));

				} else {

					cell.addNonInfestedTree((int) (Math.random() * MAX_TREE_NUM));

					cell.addInfestedTree((int) (Math.random() * MAX_TREE_NUM) + 1);
				}
			}
		}
	}

	/**
	 * Randomly chooses two-third of the cells to block
	 */
	private void blockCells() {

		// Calculate how many cells to unblock--might need to edit this
		// 1/3rd of the cells should be blocked
		double numToBlock = (NUM_ROWS * NUM_COLS * RATIO_BLOCKED);

		// Keep track of how many unblocked cells we've created
		int numBlocked = 0;

		// While we haven't unblocked enough cells
		while (numBlocked < numToBlock) {

			// Pick randomly which cell to unblock (and add trees to)
			int rowNum = (int) (Math.random() * NUM_ROWS);
			int colNum = (int) (Math.random() * NUM_COLS);

			// If this is an unblocked cell, we block it
			if (!gridCells[rowNum][colNum].isBlocked()) {

				// Note that this cell is now blocked
				gridCells[rowNum][colNum].setBlocked(true);

				// Note that we've unblocked another cell
				numBlocked++;
			}
		}

	}

	/**
	 * Counts the total number of infested and non-infested trees that are
	 * present in the unblocked cells and in all the cells of the full grid
	 */
	private void countTrees() {

		for (int r = 0; r < EstimationGrid.NUM_ROWS; r++) {

			for (int c = 0; c < EstimationGrid.NUM_COLS; c++) {

				GridCell cell = gridCells[r][c];

				// if cell is not blocked, then count trees in unblocked cells
				if (!cell.isBlocked()) {

					totalUnblockedInfested += cell.getNumInfestedTrees();

					totalUnblockedNonInfested += cell.getNumNonInfestedTrees();
				}

				// count trees for all (blocked and unblocked) cells

				totalInfested += cell.getNumInfestedTrees();

				totalNonInfested += cell.getNumNonInfestedTrees();

			}
		}

	}

	/**
	 * Returns the total number of infested trees in the grid
	 * 
	 * @return Total number of infested trees in the grid
	 */
	public int getTotalInfested() {

		return totalInfested;
	}

	/**
	 * Returns the total number of non-infested trees in the grid
	 * 
	 * @return Total number of non-infested trees in the grid
	 */
	public int getTotalNonInfested() {
		return totalNonInfested;
	}

	/**
	 * Returns the total number of infested trees in the unblocked cells of the
	 * grid
	 * 
	 * @return The total number of infested trees in the unblocked cells of the
	 *         grid
	 */
	public int getTotalUnblockedInfested() {
		return totalUnblockedInfested;
	}

	/**
	 * Returns the total number of non-infested trees in the unblocked cells of
	 * the grid
	 * 
	 * @return The total number of non-infested trees in the unblocked cells of
	 *         the grid
	 */
	public int getTotalUnblockedNonInfested() {
		return totalUnblockedNonInfested;
	}

	public boolean canDrawTree(Tree tree) {

		if (checkTreeOverlap(tree)) {

			// //system.out.println("tree overlap occurs");

			// return !isTreeHidden(tree) ;
			return false;

		} else {

			////system.out.println("no tree overlap occurs");

			return !isTreeHidden(tree);

		}
	}
	


}
