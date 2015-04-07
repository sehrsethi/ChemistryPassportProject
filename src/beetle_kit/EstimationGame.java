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
 * @version April 6, 2015
 *
 */
public class EstimationGame extends JPanel {

	private static final int MAX_NUM_ROUNDS = 3;
	private int currentGridNum;
	private GridView currentGridView;
	private AnswerPanel currentAnswerPanel;

	public EstimationGame() {

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
			
			
			//Go to passport Card
			//Then go to page we want
		}

		//EstimationStartPage startPage = new EstimationStartPage();

		EstimationGrid grid = new EstimationGrid("2",
				BeetleKitApplication.getStartPage().getInfestedColor(), BeetleKitApplication.getStartPage().getNonInfestedColor());

		grid.fillTreeArray();

		// Create three different gridViews and get rid of and swap
		// when switching rounds

		// Need a control class--could be this one

		// Could take checking answer stuff and put here or in separate class

		currentGridView = new GridView(grid.getGridCells(), grid.getTrees(),
				grid.getCellWidth(), grid.getCellHeight());

		add(currentGridView, BorderLayout.CENTER);

		createLegendPanel();

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

	/**
	 * Creates a legend that indicates the color associated with infested trees and non-infested trees
	 */
	private void createLegendPanel() {

		JPanel panel = new JPanel();

		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

		panel.setLayout(boxLayout);

		panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5));

		// the spacing of the text is important
		panel.add(createKey("          Infested Trees         " , EstimationGrid.getInfestedColor()));

		panel.add(new JPanel());
		
		panel.add(createKey("          Non-infested Trees " , EstimationGrid.getNonInfestedColor()));

		//panel.add(createNonInfestedKey());

		add(panel, BorderLayout.NORTH);
	}

	

	/**
	 * Creates a JLabel indicating the kind of tree and and a little panel of the color corresponding to the kid of tree
	 * @param text The kind of tree
	 * @param color The color of the given kind of tree
	 * @return The panel containing the text and the color
	 */
	private JPanel createKey(String text, Color color) {

		// JPanel panel = new JPanel(new BorderLayout()) ;

		JPanel panel = new JPanel();

		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		panel.setLayout(boxLayout);

		JLabel label = new JLabel(text);

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
				color, 5));
		
		colorSquare.setBackground(color);

		// panel.add(colorSquare , BorderLayout.EAST) ;

		panel.add(colorSquare);

		return panel;
	}

//
//	public static void main(String[] args) {
//
//		// Create a JFrame for the application and give it a size and close
//		// operation
//		JFrame frame = new JFrame("Estimation game");
//		frame.setSize(605, 723);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		frame.getContentPane().add(new EstimationGameApplication());
//
//		// Make the frame visible
//		frame.setVisible(true);
//
//		frame.setResizable(false);
//
//		// close operation
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//	}

}
