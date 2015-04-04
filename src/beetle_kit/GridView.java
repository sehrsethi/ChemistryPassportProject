package beetle_kit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * 
 * Represents the view for the grid
 * 
 * @author Charlotte Dye, Humaira Orchee, Sehr Sethi
 * @version April 3, 2015
 */
public class GridView extends JPanel {

	private static final Color BACKGROUND_COLOR = Color.WHITE;

	// The array of cell data
	private GridCell[][] gridCells;

	// List of all the trees in the grid
	private ArrayList<Tree> trees;

	// Whether or not this GridView should have blocked cells
	private boolean hasBlockedCells = true;

	// The width of each cell
	private int cellWidth;

	// The height of each cell
	private int cellHeight;

	/**
	 * Create a new GridView object
	 * 
	 * @param gridCells
	 *            The cells that will be in the grid
	 * @param trees
	 *            The array list of trees present in the grid
	 * @param cellWidth
	 *            The width of each grid cell
	 * @param cellHeight
	 *            The height of each grid cell
	 */
	public GridView(GridCell[][] gridCells, ArrayList<Tree> trees,
			int cellWidth, int cellHeight) {

		this.gridCells = gridCells;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;

		// trees = new ArrayList<Tree>();

		this.trees = trees;

		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5);

		this.setBorder(border);

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
	 * Sets the array list of trees to the specified one
	 * 
	 * @param trees
	 *            The new array list of trees
	 */
	public void setTrees(ArrayList<Tree> trees) {
		this.trees = trees;
	}

	@Override
	public void paintComponent(Graphics g) {

		// background of grid
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());

		// It is important to draw all the unblocked cells first and then all
		// the blocked cells otherwise the parts of trees end up being drawn on
		// top of blocked cells.

		// now draw each cell with the trees in them
		drawTrees(g);

		// paint blocked cells opaque gray if it is the blocked view. otherwise,
		// paint the blocked cells transparent gray.
		if (hasBlockedCells) {
			// Now we draw each blocked cell
			for (int r = 0; r < gridCells.length; r++) {
				for (int c = 0; c < gridCells[r].length; c++) {

					drawBlockedCell(gridCells[r][c], r, c, g, Color.GRAY);

				}
			}

		} else {

			Color color = new Color(Color.GRAY.getRed(), Color.GRAY.getGreen(),
					Color.GRAY.getBlue(), 175);

			// Now we draw each (formerly) blocked cell
			for (int r = 0; r < gridCells.length; r++) {
				for (int c = 0; c < gridCells[r].length; c++) {

					drawBlockedCell(gridCells[r][c], r, c, g, color);

				}
			}

		}

		// draw the grid lines last
		drawGridLines(g);

		// We should make sure that the blocking is on top of the trees
		// and the grid lines are on top of everything else

	}

	/**
	 * If a cell is blocked, fills with gray color. Otherwise does nothing.
	 * 
	 * @param toDraw
	 *            The blocked cell to draw on
	 * @param row
	 *            The row of the grid that cell is in
	 * @param col
	 *            The column of the grid that cell is in
	 * @param g
	 *            The graphics object to paint on
	 * @param cellColor
	 *            The color of the blocked cell. It could be solid gray or
	 *            transparent gray.
	 */
	private void drawBlockedCell(GridCell toDraw, int row, int col, Graphics g,
			Color cellColor) {

		// We need to calculate the boundaries of the cell
		int startX = col * cellWidth;
		int startY = row * cellHeight;

		// First we see if it is blocked
		if (toDraw.isBlocked()) {

			// In this case, we just block out the cell by filling it with gray
			g.setColor(cellColor);
			g.fillRect(startX, startY, cellWidth, cellHeight);

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
		for (int i = 0; i <= gridCells[0].length; i++) {
			g.drawLine(i * cellWidth, 0, i * cellWidth,
					EstimationGrid.GRID_HEIGHT);
		}

		// This will draw the horizontal lines
		for (int i = 0; i <= gridCells.length; i++) {
			g.drawLine(0, i * cellHeight, EstimationGrid.GRID_WIDTH, i
					* cellHeight);
		}
	}

	/**
	 * Draws all the trees on the graphics object
	 * 
	 * @param g
	 *            The graphics object to draw on
	 */
	private void drawTrees(Graphics g) {

		// fill/draw the tree
		Graphics2D g2d = (Graphics2D) g;

		for (int i = 0; i < trees.size(); i++) {

			Tree tree = trees.get(i);
			g2d.setColor(tree.getFillColor());
			g2d.fill(tree);
			g2d.setColor(tree.getBorderColor());
			g2d.draw(tree);
		}
	}

	/**
	 * Lets this GridView know if it has blocked cells or not
	 * 
	 * @param hasBlockedCells
	 *            True if this GridView should have blocked cells. Otherwise,
	 *            false.
	 */
	public void setHasBlockedCells(boolean hasBlockedCells) {
		this.hasBlockedCells = hasBlockedCells;
	}

}
