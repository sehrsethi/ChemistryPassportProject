import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class EstimationGrid extends JComponent {

	private static final int NUM_ROWS = 7;

	private static final int NUM_COLS = 7;

	private static final int PERCENT_BLOCKED = 3;

	private int numInfested;

	private int numNonInfested;

	private final int colWidth;

	private final int rowHeight;

	private GridCell[][] grid;

	public EstimationGrid(int width, int height) {

		super();

		setLayout(new GridLayout(NUM_ROWS, NUM_COLS));

		grid = new GridCell[NUM_ROWS][NUM_COLS];

		colWidth = width / NUM_COLS;
		rowHeight = height / NUM_ROWS;

		fillGrid();
		
		unblockCells();
	}

	private void fillGrid() {

		for (int r = 0; r < NUM_ROWS; r++) {

			for (int c = 0; c < NUM_COLS; c++) {


				grid[r][c] = new GridCell(colWidth, rowHeight);
				add(grid[r][c]);

			}
		}

	}

	public void paint(Graphics g) {

		for (int r = 0; r < NUM_ROWS; r++) {

			for (int c = 0; c < NUM_COLS; c++) {

				grid[r][c].repaint();
			}
		}
	}

	public void calculateInfested() {

		System.out.println("Infested (red) " + numInfested);
	}

	public void calculateNonInfested() {

		System.out.println("Infested (green) " + numNonInfested);
	}

	/**
	 * It is actually unblocking because by default the cells are blocked
	 */
	private void unblockCells() {

		int notBlocked = NUM_COLS * NUM_ROWS / PERCENT_BLOCKED;

		int currentNotBlocked = 0;

		// keep unblocking till 1/3 rd of the grid is unblocked
		while (currentNotBlocked < notBlocked) {

			int randomRow = (int) (Math.random() * NUM_ROWS);
			int randomCol = (int) (Math.random() * NUM_COLS);

			GridCell cell = grid[randomRow][randomCol];

			// if a grid is already unblocked, dont bother
			// otherwise unblock it
			if (cell.isBlocked()) {

				cell.setBlocked(false);
				cell.createTree();
				

				countTreeTypes(cell);

				currentNotBlocked++;

			}
		}

	}
	
	/**
	 * As each grid cell is created, determine whether or not it has an infested or non-infested tree
	 * @param cell
	 */
	private void countTreeTypes(GridCell cell){
		
		if (cell.getTreeType() == GridCell.TreeType.INFESTED
				&& !cell.isBlocked()) {

			numInfested++;

		} else if (cell.getTreeType() == GridCell.TreeType.NON_INFESTED
				&& !cell.isBlocked()) {

			numNonInfested++;
		}
		
	}

}
