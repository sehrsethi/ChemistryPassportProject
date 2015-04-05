package beetle_kit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Chemistry Passport Project Runs the Estimation Game
 * 
 * @author Charlotte Dye, Sehr Sethi, Humaira Orchee
 * @version April 3, 2015
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

		// if the user has actually completed 3 rounds, no more grids should be
		// created.
		if (currentGridNum > MAX_NUM_ROUNDS) {

			JOptionPane
					.showMessageDialog(this,
							"Great! You have completed the Estimation Game! You now get a sticker!!!");

			return;
		}

		EstimationStartPage startPage = new EstimationStartPage();

		EstimationGrid grid = new EstimationGrid("5",
				startPage.getInfestedColor(), startPage.getNonInfestedColor());

		grid.fillTreeArray();

		// Create three different gridViews and get rid of and swap
		// when switching rounds

		// Need a control class--could be this one

		// Could take checking answer stuff and put here or in separate class

		currentGridView = new GridView(grid.getGridCells(), grid.getTrees(),
				grid.getCellWidth(), grid.getCellHeight());

		add(currentGridView, BorderLayout.CENTER);

		createKeyPanel();

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

	private void createKeyPanel() {

		JPanel panel = new JPanel();

		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

		panel.setLayout(boxLayout);

		panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5));

		panel.add(createInfestedKey());

		panel.add(new JPanel());

		panel.add(createNonInfestedKey());

		add(panel, BorderLayout.NORTH);
	}

	// private JPanel createInfestedKey(){
	//
	// //JPanel panel = new JPanel(new BorderLayout()) ;
	//
	// JPanel panel = new JPanel(new GridLayout(1,7)) ;
	//
	// panel.add(new JPanel()) ;
	// panel.add(new JPanel()) ;
	//
	// JLabel label = new JLabel("Infested Trees") ;
	//
	// //panel.add(label, BorderLayout.CENTER) ;
	//
	// panel.add(label) ;
	//
	// panel.add(new JPanel()) ;
	//
	// JPanel colorSquare = new JPanel() ;
	// colorSquare.setBorder(BorderFactory.createLineBorder(EstimationGrid.getInfestedColor(),
	// 5));
	// colorSquare.setBackground(EstimationGrid.getInfestedColor());
	//
	// //panel.add(colorSquare , BorderLayout.EAST) ;
	//
	// panel.add(colorSquare) ;
	//
	// panel.add(new JPanel()) ;
	// panel.add(new JPanel()) ;
	//
	// return panel ;
	// }

	private JPanel createInfestedKey() {

		// JPanel panel = new JPanel(new BorderLayout()) ;

		JPanel panel = new JPanel();

		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		panel.setLayout(boxLayout);

		JLabel label = new JLabel("Infested Trees");

		// panel.add(label, BorderLayout.CENTER) ;

		panel.add(new JPanel(new BorderLayout()));

		panel.add(label);

		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());

		JPanel colorSquare = new JPanel(new BorderLayout());
		colorSquare.setBorder(BorderFactory.createLineBorder(
				EstimationGrid.getInfestedColor(), 5));
		colorSquare.setBackground(EstimationGrid.getInfestedColor());

		// panel.add(colorSquare , BorderLayout.EAST) ;

		panel.add(colorSquare);

		return panel;
	}

	private JPanel createNonInfestedKey() {

		JPanel panel = new JPanel();

		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		panel.setLayout(boxLayout);

		JLabel label = new JLabel("Non Infested Trees");

		// panel.add(label, BorderLayout.CENTER) ;

		panel.add(new JPanel(new BorderLayout()));

		panel.add(label);

		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());
		panel.add(new JPanel());

		JPanel colorSquare = new JPanel(new BorderLayout());
		colorSquare.setBorder(BorderFactory.createMatteBorder(5, 30, 5, 5,
				EstimationGrid.getNonInfestedColor()));
		colorSquare.setBackground(EstimationGrid.getNonInfestedColor());

		// panel.add(colorSquare , BorderLayout.EAST) ;

		panel.add(colorSquare);

		return panel;
	}

	public static void main(String[] args) {

		// Create a JFrame for the application and give it a size and close
		// operation
		JFrame frame = new JFrame("Estimation game");
		frame.setSize(605, 723);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(new EstimationGameApplication());

		// Make the frame visible
		frame.setVisible(true);

		frame.setResizable(false);

		// close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
