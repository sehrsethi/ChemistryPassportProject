import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Chemistry Passport Project
 * Runs the estimation game
 * @author Charlotte Dye, Sehr Sethi, Humaira Orchee
 *
 */
public class EstimationGameApplication extends JPanel{
	
	public EstimationGameApplication(){
		
		setLayout(new BorderLayout());
		
		EstimationGrid grid = new EstimationGrid();
		
		GridView gridView = new GridView(grid.getCellData());
		
		add(gridView, BorderLayout.CENTER) ;
		
		AnswerPanel answerPanel = new AnswerPanel() ;
		add(answerPanel, BorderLayout.SOUTH) ;
	}

	public static void main(String[] args) {
		
		//Create a JFrame for the application and give it a size and close operation
		JFrame frame = new JFrame("Estimation game");
		frame.setSize(605, 715);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create the grid and add it to the frame
		//EstimationGrid grid = new EstimationGrid();
		
		//frame.getContentPane().add(new GridView(grid.getCellData()));
		
		frame.getContentPane().add(new EstimationGameApplication()) ;
		
		//Make the frame visible
		frame.setVisible(true);
		
		frame.setResizable(false);
		
		// close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		

	}
	


}
