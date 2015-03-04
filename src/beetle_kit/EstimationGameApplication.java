package beetle_kit;
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
		
		//Create three different gridViews and get rid of and swap
		//when switching rounds
		
		//Need a control class--could be this one
		
		//Could take checking answer stuff and put here or in separate class
		
		GridView gridView = new GridView(grid.getCellData());
		gridView.fillTreeArray();
				
		add(gridView, BorderLayout.CENTER) ;
		
		AnswerPanel answerPanel = new AnswerPanel(grid, gridView) ;
		add(answerPanel, BorderLayout.SOUTH) ;
		
		System.out.println("green (non- infested) " + grid.getTotalUnblockedNonInfested());
		
		System.out.println("red (infested) " + grid.getTotalUnblockedInfested());
		
		System.out.println("total green (non- infested) " + grid.getTotalNonInfested());
		
		System.out.println("total red (infested) " + grid.getTotalInfested());
		
		
	}

	public static void main(String[] args) {
		
		//Create a JFrame for the application and give it a size and close operation
		JFrame frame = new JFrame("Estimation game");
		frame.setSize(605, 661);
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
