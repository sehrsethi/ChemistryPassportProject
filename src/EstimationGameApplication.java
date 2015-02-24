import javax.swing.JFrame;

/**
 * Chemistry Passport Project
 * Runs the estimation game
 * @author Charlotte Dye, Sehr Sethi, Humaira Orchee
 *
 */
public class EstimationGameApplication {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Estimation game");
		frame.setSize(400, 600);
		
		EstimationGrid grid = new EstimationGrid();
		frame.getContentPane().add(new GridView(grid.getCellData()));
		
		frame.setVisible(true);
		//frame.pack();

	}

}
