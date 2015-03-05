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
	
	private int currentGridNum;
	private GridView currentGridView;
	private AnswerPanel currentAnswerPanel;
	
	public EstimationGameApplication(){
		
		currentGridNum = 0;
		
		setLayout(new BorderLayout());
		
		
		createNewGrid();
		
		/*System.out.println("green (non- infested) " + grid.getTotalUnblockedNonInfested());
		
		System.out.println("red (infested) " + grid.getTotalUnblockedInfested());
		
		System.out.println("total green (non- infested) " + grid.getTotalNonInfested());
		
		System.out.println("total red (infested) " + grid.getTotalInfested());*/
		
		
	}

	/**
	 * 
	 */
	private void createNewGrid() {
		EstimationGrid grid = new EstimationGrid();
		
		grid.fillTreeArray();
		
		//Create three different gridViews and get rid of and swap
		//when switching rounds
		
		//Need a control class--could be this one
		
		//Could take checking answer stuff and put here or in separate class
		
		currentGridView = new GridView(grid.getGridCells(), grid.getTrees(), grid.getCellWidth(), grid.getCellHeight());
				
		add(currentGridView, BorderLayout.CENTER) ;
		
		currentAnswerPanel = new AnswerPanel(grid, currentGridView, this) ;
		add(currentAnswerPanel, BorderLayout.SOUTH) ;
	}
	
	public void displayNewGrid(){
		
		removeGrid() ;
		createNewGrid() ;
		repaint() ;
		
	}
	
	/**
	 * Remove the current grid view and answer panel to prepare to replace them
	 */
	private void removeGrid(){
		remove(currentGridView);
		remove(currentAnswerPanel);
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
