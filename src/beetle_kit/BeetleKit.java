package beetle_kit;

import java.awt.CardLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kit_interfaces.Kit;
import beetle_game.Beetle;
import beetle_game.BeetleGame;
import passport.KitPage;
import passport.Passport;
import main.ChemistryPassportGUI;

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

	public BeetleKit(ChemistryPassportGUI mainGUI) {

		super(mainGUI);

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

		EstimationGame app = new EstimationGame(this);

		add(app, ESTIMATION_PAGE);

	}

	public static EstimationStartPage getStartPage() {
		return startPage;
	}

	//
	// public static void main(String[] args) {
	//
	// // Create a JFrame for the application and give it a size and close
	// // operation
	// JFrame frame = new JFrame("Beetle Kit");
	// frame.setSize(605, 723);
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//
	// frame.getContentPane().add(new BeetleKit());
	//
	// // Make the frame visible
	// frame.setVisible(true);
	//
	// frame.setResizable(false);
	//
	// // close operation
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//
	// }

	@Override
	public void startKit() {

		// Create the start page
		startPage = new EstimationStartPage(this);

		// Add the start page to the CardLayout
		add(startPage, START_PAGE_NAME);

	}

	@Override
	public String getButtonName() {

		return "Beetle Kit";
	}

	/**
	 * Adds the sticker, enables the reward button, and returns to the
	 * appropriate page of the passport
	 */
	public void earnReward() {

		mainGUI.goToCard(ChemistryPassportGUI.PASSPORT_TEXT);

		// mainGUI.nextCard();

		// mainGUI.goToCard(ChemistryPasspo);

		// CARD_LAYOUT.show(this, pageNames.get(currentPage));

		// Get the passport
		Passport passport = mainGUI.getPassport();
		
		// go to the passport card
		mainGUI.goToCard(ChemistryPassportGUI.PASSPORT_TEXT);

		// Go to the corresponding page of the passport
		passport.goToPage(getButtonName());

		// Testing
		// passport.nextPage();

		// Get the kit page for this kit
		KitPage kitPage = passport.getKitPage(getButtonName());

		// System.out.println("kitPage " + kitPage);
		
		//Notify the kit page that the sticker can now be shown
		//kitPage.setShowSticker(true);

		// Add the reward sticker
		//kitPage.addSticker();
		
		kitPage.startStickerAnimation();

		// Enable the reward button
		kitPage.enableRewardButton();

		repaint();
	}

	@Override
	public String getRewardName() {
		return "Beetle Reward Game";
	}

	@Override
	public JComponent createRewardGame() {
		BeetleGame beetleGame = new BeetleGame(mainGUI);
		return beetleGame;
	}
}
