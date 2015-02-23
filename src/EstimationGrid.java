import java.awt.GridLayout;

import javax.swing.JComponent;

/**
 * Chemistry Passport Project
 * The grid for the estimation game
 * @author Charlotte Dye, Humaira Orchee, Sehr Sethi
 *
 */
public class EstimationGrid extends JComponent{
	//The number of rows in the grid
	private static final int NUM_ROWS = 7;
	
	//The number of columns in the grid
	private static final int NUM_COLS = 7;
	
	//The percent of cells that will be blocked
	private static final int PERCENT_BLOCKED = 3;
	
	//A 2D array of GridCells, each representing
	//data in a given cell.
	private GridCell[][] cellData;
	
	/**
	 * Creates the grid
	 */
	public EstimationGrid(){
		//First, we create the GridCell
		//objects and the data for this grid
		cellData = new GridCell[NUM_ROWS][NUM_COLS];
		
		//Next, we add trees/blocking for the cells
		
		//Now, we add the border lines
	}
	
	
}
