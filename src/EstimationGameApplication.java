import javax.swing.JFrame;

/**
 * Chemistry Passport Project
 * Runs the estimation game
 * @author Charlotte Dye, Sehr Sethi, Humaira Orchee
 *
 */
public class EstimationGameApplication {

	public static void main(String[] args) {
		
		//Create a JFrame for the application and give it a size and close operation
		JFrame frame = new JFrame("Estimation game");
		frame.setSize(596, 618);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create the grid and add it to the frame
		EstimationGrid grid = new EstimationGrid();
		
		frame.getContentPane().add(new GridView(grid.getCellData()));
		
		//Make the frame visible
		frame.setVisible(true);
		
		frame.setResizable(false);
		
		// close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

}
