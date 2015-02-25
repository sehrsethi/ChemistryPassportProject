import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;

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

	private static final Color INFESTED_COLOR = Color.RED;

	private static final Color INFESTED_BORDER_COLOR = new Color(153, 0, 0);

	private static final Color NON_INFESTED_COLOR = Color.GREEN;

	private static final Color NON_INFESTED_BOREDR_COLOR = new Color(0, 51, 0);

	// The width of each cell
	private int cellWidth;

	// The height of each cell
	private int cellHeight;

	// The array of cell data
	private GridCell[][] gridCells;

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

	}

	public void paintComponent(Graphics g) {

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

				// drawCell(gridCells[r][c], r, c, g);

				drawBlockedCell(gridCells[r][c], r, c, g);

			}
		}

		// First we draw the grid lines
		drawGridLines(g);

		// We should make sure that the blocking is on top of the trees
		// and the grid lines are on top of everything else

	}

	//
	// /**
	// * Draw the specified cell
	// *
	// * @param toDraw
	// * Cell data for drawing
	// * @param row
	// * The row this cell is in
	// * @param col
	// * The column this cell is in
	// */
	// private void drawCell(GridCell toDraw, int row, int col, Graphics g) {
	//
	// // We need to calculate the boundaries of the cell
	// int startX = row * cellWidth;
	// int startY = col * cellHeight;
	//
	// // First we see if it is blocked
	// if (toDraw.isBlocked()) {
	//
	// // In this case, we just block out the cell by filling it with gray
	// g.setColor(Color.GRAY);
	// g.fillRect(startX, startY, cellWidth, cellHeight);
	//
	// } else {
	//
	// drawUnblockedCell(toDraw, g, startX, startY);
	//
	// }
	// }

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
	 * @param toDraw
	 * @param g
	 * @param row
	 * @param col
	 */
	private void drawUnblockedCell(GridCell toDraw, Graphics g, int row, int col) {
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

		int centerX = (int) (row * cellWidth + Math.random() * cellWidth);

		int centerY = (int) (col * cellHeight + Math.random() * cellHeight);

		// randomly choose if infested or non-infested trees are drawn first.
		// This matters because the tree drawn first gets drawn over. This
		// creates a bias for greater number of trees that are drawn later.
		if (Math.random()*2 <= 0) {
			int numInfestedTrees = 0;

			while (toDraw.getNumInfestedTrees() > numInfestedTrees) {

				// drawTree(g, NON_INFESTED_COLOR, centerX, centerY);

				drawTree(g, INFESTED_COLOR, INFESTED_BORDER_COLOR, centerX,
						centerY);
				numInfestedTrees++;
			}

			int numNonInfestedTrees = 0;

			while (toDraw.getNumNonInfestedTrees() > numNonInfestedTrees) {

				// drawTree(g, NON_INFESTED_COLOR, centerX, centerY);

				drawTree(g, NON_INFESTED_COLOR, NON_INFESTED_BOREDR_COLOR,
						centerX, centerY);
				numNonInfestedTrees++;
			}

		} else {

			int numNonInfestedTrees = 0;

			while (toDraw.getNumNonInfestedTrees() > numNonInfestedTrees) {

				// drawTree(g, NON_INFESTED_COLOR, centerX, centerY);

				drawTree(g, NON_INFESTED_COLOR, NON_INFESTED_BOREDR_COLOR,
						centerX, centerY);
				numNonInfestedTrees++;
			}

			int numInfestedTrees = 0;

			while (toDraw.getNumInfestedTrees() > numInfestedTrees) {

				// drawTree(g, NON_INFESTED_COLOR, centerX, centerY);

				drawTree(g, INFESTED_COLOR, INFESTED_BORDER_COLOR, centerX,
						centerY);
				numInfestedTrees++;
			}

		}

		/*
		 * if (toDraw.getNumInfestedTrees() > 0){ //Draw green trees
		 * 
		 * } if (toDraw.getNumNonInfestedTrees() > 0){ //Draw non-infested trees }
		 */
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

	private void drawTree(Graphics g, Color fillColor, Color borderColor,
			int centerX, int centerY) {

		int minDimension = Math.min(cellHeight, cellWidth);
		/*
		 * int diameter = (int) ((minDimension / 2) + Math.random()
		 * (minDimension));
		 */

		int diameter = minDimension;

		int topX = centerX - diameter / 2;

		int topY = centerY - diameter / 2;

		g.setColor(fillColor);

		g.fillOval(topX, topY, diameter, diameter);

		g.setColor(borderColor);

		g.drawOval(topX, topY, diameter, diameter);
	}

}
