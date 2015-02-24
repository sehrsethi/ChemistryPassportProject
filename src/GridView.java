import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 
 * Represents the view for the grid
 * @author Charlotte Dye, Humaira Orchee, Sehr Sethi
 *
 */
public class GridView extends JPanel{
	
	//The height of the grid
	private static final int GRID_HEIGHT = 600;
	
	//The width of the grid
	private static final int GRID_WIDTH = 600;
	
	//The width of each cell
	private int cellWidth;
	
	//The height of each cell
	private int cellHeight;
	
	//The array of cell data
	private GridCell[][] gridCells;
	
	/**
	 * Create a new GridView object
	 * @param gridCells The cells that will be in the grid
	 */
	public GridView(GridCell[][] gridCells){
		this.gridCells = gridCells;
		cellWidth = GRID_WIDTH / EstimationGrid.NUM_COLS;
		cellHeight = GRID_HEIGHT / EstimationGrid.NUM_ROWS;
		
	}
	
	public void paintComponent(Graphics g){
		
		//First we draw the grid lines
		drawGridLines(g);
	}

	/**
	 * Draws the vertical and horizontal lines for the grid
	 * @param g The graphics to use to draw
	 */
	private void drawGridLines(Graphics g) {
		//This will draw the vertical lines
		for (int i = 0; i <= EstimationGrid.NUM_COLS; i++){
			g.drawLine(i*cellWidth,0,i*cellWidth, GRID_HEIGHT);
		}
		
		//This will draw the horizontal lines
		for (int i = 0; i <= EstimationGrid.NUM_ROWS; i++){
			g.drawLine(0,i*cellHeight,GRID_WIDTH, i*cellHeight);
		}
	}

}
