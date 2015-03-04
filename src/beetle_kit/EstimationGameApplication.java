package beetle_kit;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Chemistry Passport Project
 * Runs the Estimation Game
 * 
 * @author Charlotte Dye, Sehr Sethi, Humaira Orchee
 * @version March 4, 2015
 *
 */
public class EstimationGameApplication extends JPanel{
	
	public EstimationGameApplication(){
		
		setLayout(new BorderLayout());
		
		EstimationGrid grid = new EstimationGrid();
		
		grid.fillTreeArray();
		
		//Create three different gridViews and get rid of and swap
		//when switching rounds
		
		//Need a control class--could be this one
		
		//Could take checking answer stuff and put here or in separate class
		
		GridView gridView = new GridView(grid.getGridCells(), grid.getTrees(), grid.getCellWidth(), grid.getCellHeight());
				
		add(gridView, BorderLayout.CENTER) ;
		
		AnswerPanel answerPanel = new AnswerPanel(grid, gridView) ;
		add(answerPanel, BorderLayout.SOUTH) ;
		
		/*System.out.println("green (non- infested) " + grid.getTotalUnblockedNonInfested());
		
		System.out.println("red (infested) " + grid.getTotalUnblockedInfested());
		
		System.out.println("total green (non- infested) " + grid.getTotalNonInfested());
		
		System.out.println("total red (infested) " + grid.getTotalInfested());*/
		
		
	}

	public static void main(String[] args) {
		
		//Create a JFrame for the application and give it a size and close operation
		JFrame frame = new JFrame("Estimation game");
		frame.setSize(605, 671);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(new EstimationGameApplication()) ;
		
		//Make the frame visible
		frame.setVisible(true);
		
		frame.setResizable(false);
		
		// close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		

	}
	


}
