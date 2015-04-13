package beetle_kit;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Kit;

/**
 * This class start the Beetle Kit
 * 
 * @author Humaira Orchee, Charlotte Dye, Sehr Sethi
 * @version April 7, 2015
 */
public class BeetleKit extends Kit {

	// The layout for the kit
	public static final CardLayout CARD_LAYOUT = new CardLayout();

	private static final String START_PAGE_NAME = "Start Page";

	private static final String ESTIMATION_PAGE = "Estimation Page";

	private static EstimationStartPage startPage;

	public BeetleKit() {
		this.setLayout(CARD_LAYOUT);

		// add(app, ESTIMATION_PAGE);
	}

	public void nextPage() {

		CARD_LAYOUT.next(this);

		repaint();
	}

	public void prevPage() {

		CARD_LAYOUT.previous(this);

		repaint();
	}

	public void createEstimationGame() {

		EstimationGame app = new EstimationGame();

		add(app, ESTIMATION_PAGE);

	}

	public static EstimationStartPage getStartPage() {
		return startPage;
	}

	public static void main(String[] args) {

		// Create a JFrame for the application and give it a size and close
		// operation
		JFrame frame = new JFrame("Beetle Kit");
		frame.setSize(605, 723);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(new BeetleKit());

		// Make the frame visible
		frame.setVisible(true);

		frame.setResizable(false);

		// close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void startKit() {
		
		//Create the start page
		startPage = new EstimationStartPage(this);

		//Add the start page to the CardLayout
		add(startPage, START_PAGE_NAME);

	}

}
