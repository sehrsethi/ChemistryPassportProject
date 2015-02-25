import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * 
 * Represents the view for the grid
 * 
 * @author Charlotte Dye, Humaira Orchee, Sehr Sethi
 *
 */
public class GridView extends JPanel {

	private static final Color BACKGROUND_COLOR = new Color(235, 255, 214);

	// The height of the grid
	private static final int GRID_HEIGHT = 600;

	// The width of the grid
	private static final int GRID_WIDTH = 600;

	// Color of infested trees
	private static final Color INFESTED_COLOR = Color.RED;

	// Border Color of infested trees
	private static final Color INFESTED_BORDER_COLOR = new Color(153, 0, 0);

	// Color of non-infested trees
	private static final Color NON_INFESTED_COLOR = Color.GREEN;

	// Border color of non-infested trees
	private static final Color NON_INFESTED_BOREDR_COLOR = new Color(0, 51, 0);

	// The width of each cell
	private int cellWidth;

	// The height of each cell
	private int cellHeight;

	// The array of cell data
	private GridCell[][] gridCells;

	// List of all the trees in the grid
	private ArrayList<Tree> trees;

	// for numbering trees. remove in final product
	private int num = 0;

	/**
	 * Create a new GridView object
	 * 
	 * @param gridCells
	 *            The cells that will be in the grid
	 */
	public GridView(GridCell[][] gridCells) {

		this.gridCells = gridCells;
		cellWidth = GRID_WIDTH / EstimationGrid.NUM_COLS;
		cellHeight = GRID_HEIGHT / EstimationGrid.NUM_ROWS;

		trees = new ArrayList<Tree>();

	}

	public void paintComponent(Graphics g) {

		// background of grid
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());

		// It is important to draw all the unblocked cells first and then all
		// the blocked cells otherwise the parts of trees end up being drawn on
		// top of blocked cells.

		// now draw each unblocked cell with the trees in them
		
		for (int r = 0; r < EstimationGrid.NUM_ROWS; r++) {
			for (int c = 0; c < EstimationGrid.NUM_COLS; c++) {

				drawUnblockedCell(gridCells[r][c], g, r, c);

			}
		}

		// Now we draw each blocked cell
		for (int r = 0; r < EstimationGrid.NUM_ROWS; r++) {
			for (int c = 0; c < EstimationGrid.NUM_COLS; c++) {

				drawBlockedCell(gridCells[r][c], r, c, g);

			}
		}

		// draw the grid lines last
		drawGridLines(g);

		// We should make sure that the blocking is on top of the trees
		// and the grid lines are on top of everything else

	}

/**
 * If a cell is blocked, fills with gray color. Otherwise does nothing.
 * @param toDraw The blocked cell to draw on
 * @param row The row of the grid that cell is in
 * @param col The column  of the grid  that cell is in
 * @param g The graphics object to paint on
 */
	private void drawBlockedCell(GridCell toDraw, int row, int col, Graphics g) {

		// We need to calculate the boundaries of the cell
		int startX = row * cellWidth;
		int startY = col * cellHeight;

		// First we see if it is blocked
		if (toDraw.isBlocked()) {

			// In this case, we just block out the cell by filling it with gray
			g.setColor(Color.GRAY);
			g.fillRect(startX, startY, cellWidth, cellHeight);

		}

	}

	/**
	 * Draws infested and non-infested trees in an unblocked cell. Does nothing if the cell is blocked.
	 * @param toDraw The unblocked cell to draw on
	 * @param g The graphics object to draw on
	 * @param row The row of the grid the cell is in
	 * @param col The column  of the grid  that cell is in
	 */
	private void drawUnblockedCell(GridCell toDraw, Graphics g, int row, int col) {
		
		// if cell is blocked, to do nothing
		if(toDraw.isBlocked()){
			
			return ;
		}
		// Otherwise, we need to draw the trees.
		// We need it to be at least 50% in this cell. I -think- this
		// will be satisfied as long as the center is in the cell, but
		// I'm not sure. Also, we want it to be clear enough
		// that the tree is mostly in the cell--a tree that is 51% in
		// this cell and 49% in another cell will be hard to figure out
		// Also, should trees be able to overlap?
		// What size will the trees be? All the same size or different
		// sizes?
		// Basically, we need to figure out what the constraints are
		// on how the trees look/are placed in the grid and then figure
		// out how to satisfy those constraints.
		// Additionally, does it matter if we put all infested trees down
		// before non-infested trees? If so, what do we do to make sure this
		// doesn't cause issues

	

		// randomly choose if infested or non-infested trees are drawn first.
		// This matters because the tree drawn first gets drawn over. This
		// increases the probability for greater number of trees that are drawn later.
					
		if (Math.random() <= 0.5) {
			
			// draw infested trees first
			
			int numInfestedTrees = 0;

			while (toDraw.getNumInfestedTrees() > numInfestedTrees) {

				// check if the tree was actually drawn
				boolean drawn = drawTree(g, INFESTED_COLOR,
						INFESTED_BORDER_COLOR, row, col);

				if (drawn) {
					numInfestedTrees++;
				}
			}

			// then draw non-infested trees
			int numNonInfestedTrees = 0;

			while (toDraw.getNumNonInfestedTrees() > numNonInfestedTrees) {

				// check if the tree was actually drawn
				boolean drawn = drawTree(g, NON_INFESTED_COLOR,
						NON_INFESTED_BOREDR_COLOR, row, col);

				if (drawn) {
					numNonInfestedTrees++;
				}
			}

		} else {
			
			// draw non-infested trees first 

			int numNonInfestedTrees = 0;

			while (toDraw.getNumNonInfestedTrees() > numNonInfestedTrees) {

				// check if the tree was actually drawn
				boolean drawn = drawTree(g, NON_INFESTED_COLOR,
						NON_INFESTED_BOREDR_COLOR, row, col);
				if (drawn) {
					numNonInfestedTrees++;
				}
			}

			// then draw infested trees
			int numInfestedTrees = 0;

			while (toDraw.getNumInfestedTrees() > numInfestedTrees) {

				// check if the tree was actually drawn
				boolean drawn = drawTree(g, INFESTED_COLOR,
						INFESTED_BORDER_COLOR, row, col);

				if (drawn) {
					numInfestedTrees++;
				}
			}

		}

		
	}

	/**
	 * Draws the vertical and horizontal lines for the grid
	 * 
	 * @param g
	 *            The graphics to use to draw
	 */
	private void drawGridLines(Graphics g) {

		g.setColor(Color.BLACK);
		
		// This will draw the vertical lines
		for (int i = 0; i <= EstimationGrid.NUM_COLS; i++) {
			g.drawLine(i * cellWidth, 0, i * cellWidth, GRID_HEIGHT);
		}

		// This will draw the horizontal lines
		for (int i = 0; i <= EstimationGrid.NUM_ROWS; i++) {
			g.drawLine(0, i * cellHeight, GRID_WIDTH, i * cellHeight);
		}
	}

	/**
	 * Draw trees on the grid. More than 50% of the tree should be in the cell specified by the row and column. If it tries to draw a tree such that one tree will be completely hidden behind another tree, then does nothing and returns false.
	 * @param g The graphics object to draw on
	 * @param fillColor The body color of the tree according to whether or not the tree is infested or not infested.
	 * @param borderColor The border color of the tree according to whether or not the tree is infested or not infested.
	 * @param row The row of the cell containing the bulk of the tree
	 * @param col The column of the cell containing the bulk of the tree
	 * @return True if a tree was successfully drawn. Otherwise returns false.
	 */
	private boolean drawTree(Graphics g, Color fillColor, Color borderColor,
			int row, int col) {

		// dimensions of the tre
		int minDimension = Math.min(cellHeight, cellWidth);

		int diameter = (int) ((minDimension / 2) + Math.random()
				* (minDimension));
		
		// center ccoordinates of the tree
		int centerX = (int) (row * cellWidth + Math.random() * cellWidth);

		int centerY = (int) (col * cellHeight + Math.random() * cellHeight);

		// the tree to draw
		Tree tree = new Tree(centerX, centerY, diameter);

		// if the tree we are trying to draw will completely hide another existing tree or the tree itself will be completely hidden by an existing tree, then do nothing.
		if (hideTree(tree)) {

			return false;
		}

		trees.add(tree);

		num++;

		// fill/draw the tree
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(fillColor);

		g2d.fill(tree);

		g2d.setColor(borderColor);

		g2d.draw(tree);

		// draw bounding rectangle
		g2d.setColor(Color.BLUE);
		g2d.draw(tree.getBounds2D());

		// draw the index of the tree in the array
		g2d.setColor(Color.BLACK);
		g2d.drawString("" + num, centerX, centerY);

		//a tree was drawn so return true
		return true;
	}

	/**
	 * Return whether or not one tree will completely hide another tree
	 * @param The tree object that might hide another tree or be hidden by another tree
	 * @return True of no tree is hidden completely by another. Otherwise returns false.
	 */
	private boolean hideTree(Tree tree) {

		// look though the entire array of trees
		for (int i = 0; i < trees.size(); i++) {

			Tree otherTree = trees.get(i);

			// if the specified tree is hidden by another existing tree or hides an existing tree, return true.
			if (otherTree.containedWithin(tree)
					|| tree.containedWithin(otherTree)) {

				return true;
			}
		}

		// no tree is hidden by another tree
		return false;
	}

}
