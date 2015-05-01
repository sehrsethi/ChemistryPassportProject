package beetle_kit;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Chemistry Passport Project The grid data for the Estimation Game
 * 
 * @author Charlotte Dye, Humaira Orchee, and Sehr Sethi
 * @version April 30, 2015
 *
 */
public class EstimationGrid {

	// The number of rows in the grid
	private static int numRows;

	// The number of columns in the grid
	private static int numCols;

	// 2/3 rd of the trees are blocked. Note 2/3 truncates to 0. That is why
	// write 2.0/3.0
	private static final double RATIO_BLOCKED = 2.0 / 3.0;

	// The maximum number of trees of each color that can be placed in one cell
	private static final double MAX_TREE_NUM = 15;

	// Color of infested trees
	private static Color infestedColor;

	// Border Color of trees
	private static final Color BORDER_COLOR = Color.BLACK;

	// Color of non-infested trees
	private static Color nonInfestedColor;

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

	/**
	 * Creates the grid
	 * 
	 * @param grade
	 *            The grade of the user
	 * @param infestedColor
	 *            The color to make the infested trees
	 * @param nonInfestedColor
	 *            The color to make the non-infested trees
	 */
	public EstimationGrid(String grade, Color infestedColor,
			Color nonInfestedColor) {

		EstimationGrid.infestedColor = infestedColor;
		EstimationGrid.nonInfestedColor = nonInfestedColor;

		// Set the size to be appropriate for the grade of the user
		setGridSize(grade);

		trees = new ArrayList<Tree>();

		// The width and height of each cell are calculated based on the total
		// width and height and the number of columns and rows
		cellWidth = GRID_WIDTH / numCols;
		cellHeight = GRID_HEIGHT / numRows;

		// First, we create the GridCell
		// objects and the data for this grid
		gridCells = new GridCell[numRows][numCols];

		// Create the cells
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {

				gridCells[i][j] = new GridCell(i, j);
			}
		}

		// Next, we add trees to every cell
		addNewTrees();

		// Block out 2/3 of the cells
		blockCells();

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

		// Stores a count of how many infested and non-infested trees the user
		// should estimate and stores this information
		countTrees();
	}

	/**
	 * 
	 * Draws infested and non-infested trees in a cell. Randomly selects whether
	 * to draw infested trees first or non-infested trees first.
	 * 
	 * @param toDraw
	 *            The cell to draw on
	 * @param row
	 *            The row of the grid the cell is in
	 * @param col
	 *            The column of the grid that cell is in
	 */
	private void createTreeInCell(GridCell toDraw, int row, int col) {

		// Randomly decides which type of tree to draw first
		if (Math.random() <= 0.5) {

			// draw infested trees first
			int numInfestedTrees = 0;

			// Draw the specified number of infested trees
			while (toDraw.getNumInfestedTrees() > numInfestedTrees) {

				// check if the tree was actually drawn
				boolean drawn = createTree(infestedColor, BORDER_COLOR, row,
						col);

				// If we successfully drew a tree, increment our count
				if (drawn) {
					numInfestedTrees++;
				}

				// At this point, we've tried to draw a tree and failed
				else {
					// Don't increment the number of trees we've drawn

					// Decrement the number of trees left to draw, unless that
					// number is already 0
					if (!toDraw.decrementNumInfested()) {
						break;
					}

				}

			}

			// then draw non-infested trees
			int numNonInfestedTrees = 0;

			// Draw the specified number of non-infested trees
			while (toDraw.getNumNonInfestedTrees() > numNonInfestedTrees) {

				// check if the tree was actually drawn
				boolean drawn = createTree(nonInfestedColor, BORDER_COLOR, row,
						col);

				// If it was drawn successfully, increment our count
				if (drawn) {
					numNonInfestedTrees++;

				} else {
					// Otherwise, decrement number of trees left to draw, unless
					// it was already 0
					if (!toDraw.decrementNumNonInfested()) {
						break;
					}
				}

			}

		} else {

			// draw non-infested trees first
			int numNonInfestedTrees = 0;

			// Draw specified number of non-infested trees
			while (toDraw.getNumNonInfestedTrees() > numNonInfestedTrees) {

				// check if the tree was actually drawn
				boolean drawn = createTree(nonInfestedColor, BORDER_COLOR, row,
						col);

				// If it was drawn successfully, increment our count
				if (drawn) {
					numNonInfestedTrees++;
				} else {

					// Otherwise decrement number of trees left to draw, unless
					// it was already 0
					if (!toDraw.decrementNumNonInfested()) {
						break;
					}

				}
			}

			// then draw infested trees
			int numInfestedTrees = 0;

			// Draw specified number of infested trees
			while (toDraw.getNumInfestedTrees() > numInfestedTrees) {

				// check if the tree was actually drawn
				boolean drawn = createTree(infestedColor, BORDER_COLOR, row,
						col);

				// If we drew it successfully, update our count
				if (drawn) {

					numInfestedTrees++;

				} else {
					// Otherwise, decrement number of trees left to draw, unless
					// it was already 0
					if (!toDraw.decrementNumInfested()) {
						break;
					}

				}

			}

		}

	}

	/**
	 * Draw trees in the grid. More than 50% of each tree should be in the cell
	 * specified by the row and column. If it tries to draw a tree such that one
	 * tree will be completely hidden behind another tree, then it does nothing
	 * and returns false.
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

		// the tree to draw
		Tree tree = new Tree(centerX, centerY, diameter, fillColor, borderColor);

		// if the tree we are trying to draw will completely hide another
		// existing tree or the tree itself will be completely hidden by an
		// existing tree, then don't draw the tree.

		if (!canDrawTree(tree)) {
			return false;

		} else {
			// If no trees will be hidden as a result, add this to the list of
			// trees
			trees.add(tree);

			// a tree was drawn so return true
			return true;
		}
	}

	/**
	 * Return whether or not one tree will completely hide another tree
	 * 
	 * @param tree
	 *            The tree object that might hide another tree or be hidden by
	 *            another tree
	 * @return True if a tree is hidden completely by another. Otherwise returns
	 *         false.
	 */
	private boolean isTreeHidden(Tree tree) {

		// look though the entire array of trees
		for (int i = 0; i < trees.size(); i++) {
			Tree otherTree = trees.get(i);

			// Hidden is true if one tree is contained within another tree
			boolean hidden = otherTree.containedWithin(tree)
					|| tree.containedWithin(otherTree);

			// if the specified tree is hidden by another existing tree or hides
			// an existing tree, return true.
			if (hidden) {

				return true;
			}
		}

		// no tree is hidden by another tree
		return false;
	}

	/**
	 * Checks whether this tree overlaps with any existing trees
	 * 
	 * @param tree
	 *            The tree whose overlap we will check
	 * @return True if this tree overlaps too much with another tree, or false
	 *         otherwise
	 */
	private boolean checkTreeOverlap(Tree tree) {

		// look though the entire array of trees
		for (int i = 0; i < trees.size(); i++) {

			Tree otherTree = trees.get(i);

			// Check the overlap
			boolean overlaps = tree.checkOverlap(otherTree);

			// if the specified tree is hidden by another existing tree or hides
			// an existing tree, return true.
			if (overlaps) {
				return true;
			}
		}

		// no tree is hidden by another tree
		return false;
	}

	/**
	 * Adds 0-MAX_TREE_NUM non-infested or infested tree to the data for every
	 * cell (Note that it does NOT actually DRAW the tree)
	 */
	private void addNewTrees() {

		// Iterate through each row
		for (int r = 0; r < numRows; r++) {

			// Iterate through each column
			for (int c = 0; c < numCols; c++) {

				GridCell cell = gridCells[r][c];

				// 50% chance of adding more non-infested trees than infested
				// trees
				if (Math.random() < 0.5) {

					cell.addNonInfestedTree((int) (Math.random() * MAX_TREE_NUM) + 1);

					cell.addInfestedTree((int) (Math.random() * MAX_TREE_NUM));

				} else {
					// 50% chance of adding more infested teres than
					// non-infested trees
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

		// Calculate how many cells to block
		double numToBlock = (numRows * numCols * RATIO_BLOCKED);

		// Keep track of how many cells we've blocked
		int numBlocked = 0;

		// While we haven't blocked enough cells
		while (numBlocked < numToBlock) {

			// Pick randomly which cell to block
			int rowNum = (int) (Math.random() * numRows);
			int colNum = (int) (Math.random() * numCols);

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
	 * present in the unblocked cells, as well as the total number of infested
	 * and non-infested trees present in total
	 */
	private void countTrees() {

		// Iterate through the cells
		for (int r = 0; r < EstimationGrid.numRows; r++) {

			for (int c = 0; c < EstimationGrid.numCols; c++) {

				GridCell cell = gridCells[r][c];

				// if cell is not blocked, then increment trees in unblocked
				// cells
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

	/**
	 * Returns whether we can draw this tree (i.e., it wouldn't create too much
	 * overlap or result in hidden trees)
	 * 
	 * @param tree
	 *            The tree to check for overlapping or hiding
	 * @return True if we can draw the tree, or false otherwise
	 */
	public boolean canDrawTree(Tree tree) {

		// If it overlaps too much, we know we can't draw it
		if (checkTreeOverlap(tree)) {

			return false;

		} else {

			// Otherwise, proceed to check whether it creates a hidden tree
			return !isTreeHidden(tree);

		}
	}

	/**
	 * Sets the size of the grid to be appropriate for users in the given grade
	 * 
	 * @param grade
	 *            Grade of the user (Between K and 6)
	 */
	private void setGridSize(String grade) {

		// Kindergarteners get 3*3 grids
		if (grade.equals("K")) {
			numCols = 3;
			numRows = 3;

		} else if (grade.equals("1") || grade.equals("2")) {

			// First and second graders get 4*4 grids
			numCols = 4;
			numRows = 4;

		} else if (grade.equals("3") || grade.equals("4")) {

			// 3rd and 4th graders get 5*5 grids
			numCols = 5;
			numRows = 5;

		} else if (grade.equals("5")) {

			// 5th graders get 6*6 grids
			numCols = 6;
			numRows = 6;

		} else if (grade.equals("6")) {

			// 6th graders get 7*7 grids
			numCols = 7;
			numRows = 7;

		} else {

			// Those are the only grades that we allow, so throw an exception
			throw new AssertionError("Grade can only be k - 6. But grade is "
					+ grade + ".");
		}
	}

	/**
	 * Get the color of infested trees
	 * 
	 * @return Color of infested trees
	 */
	public static Color getInfestedColor() {

		return infestedColor;
	}

	/**
	 * Get the color of non-infested trees
	 * 
	 * @return Color of non-infested trees
	 */
	public static Color getNonInfestedColor() {

		return nonInfestedColor;
	}

}
