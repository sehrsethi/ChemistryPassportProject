import java.awt.Color;
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
		
		//Now we draw each cell
		for (int i = 0; i < EstimationGrid.NUM_ROWS; i++){
			for (int j = 0; j < EstimationGrid.NUM_COLS; j++){
				drawCell(gridCells[i][j],i,j, g);
			}
		}
		
		
		//We should make sure that the blocking is on top of the trees
		//and the grid lines are on top of everything else
	}

	/**
	 * Draw the specified cell
	 * @param toDraw Cell data for drawing
	 * @param row The row this cell is in
	 * @param col The column this cell is in
	 */
	private void drawCell(GridCell toDraw, int row, int col, Graphics g){
		
		//We need to calculate the boundaries of the cell
		int startX = row * cellWidth;
		int startY = col * cellHeight;
		
		//First we see if it is blocked
		if (toDraw.isBlocked()){

			//In this case, we just block out the cell by filling it with gray
			g.setColor(Color.GRAY);
			g.fillRect(startX, startY, cellWidth, cellHeight);
			
		}
		else {
			//Otherwise, we need to draw the trees.
			//We need it to be at least 50% in this cell.  I -think- this
			//will be satisfied as long as the center is in the cell, but
			//I'm not sure.  Also, we want it to be clear enough
			//that the tree is mostly in the cell--a tree that is 51% in
			//this cell and 49% in another cell will be hard to figure out
			//Also, should trees be able to overlap?
			//What size will the trees be?  All the same size or different sizes?
			//Basically, we need to figure out what the constraints are
			//on how the trees look/are placed in the grid and then figure
			//out how to satisfy those constraints.
			//Additionally, does it matter if we put all green trees down
			//before red trees?  If so, what do we do to make sure this
			//doesn't cause issues
			
			if (toDraw.getNumGreenTrees() > 0){
				//Draw green trees
				
			}
			if (toDraw.getNumRedTrees() > 0){
				//Draw red trees
			}
			
			
		}
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
