package beetle_kit;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Chemistry Passport Project Runs the Estimation Game
 * 
 * @author Charlotte Dye, Sehr Sethi, Humaira Orchee
 * @version March 4, 2015
 *
 */
public class EstimationGameApplication extends JPanel {

	private static final int MAX_NUM_ROUNDS = 3;
	private int currentGridNum;
	private GridView currentGridView;
	private AnswerPanel currentAnswerPanel;

	public EstimationGameApplication() {

		currentGridNum = 1;

		setLayout(new BorderLayout());

		createNewGrid();

	}

	/**
	 * Creates a new grid and a new grid view.
	 */
	private void createNewGrid() {

		// if the user has actually completed 3  rounds, no more grids should be created.
		if (currentGridNum > MAX_NUM_ROUNDS) {

			JOptionPane
					.showMessageDialog(this,
							"Great! You have completed the Estimation Game! You now get a sticker!!!");
	

			return;
		}

		EstimationGrid grid = new EstimationGrid();

		grid.fillTreeArray();

		// Create three different gridViews and get rid of and swap
		// when switching rounds

		// Need a control class--could be this one

		// Could take checking answer stuff and put here or in separate class

		currentGridView = new GridView(grid.getGridCells(), grid.getTrees(),
				grid.getCellWidth(), grid.getCellHeight());

		add(currentGridView, BorderLayout.CENTER);

		currentAnswerPanel = new AnswerPanel(grid, currentGridView, this);
		add(currentAnswerPanel, BorderLayout.SOUTH);
		currentGridNum++;

		repaint();
		revalidate();

	}

	/**
	 * Displays a new Grid Note : When a new grid is displayed, user should
	 * close all pop-up manually.
	 */
	public void displayNewGrid() {

		removeGrid();
		createNewGrid();

	}

	/**
	 * Remove the current grid view and answer panel to prepare to replace them
	 */
	private void removeGrid() {
		remove(currentGridView);
		remove(currentAnswerPanel);
		repaint();
		revalidate();

	}

	public static void main(String[] args) {

		// Create a JFrame for the application and give it a size and close
		// operation
		JFrame frame = new JFrame("Estimation game");
		frame.setSize(605, 671);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(new EstimationGameApplication());

		// Make the frame visible
		frame.setVisible(true);

		frame.setResizable(false);

		// close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
