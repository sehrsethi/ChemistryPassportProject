package beetle_kit;

import java.awt.CardLayout;

import javax.swing.JComponent;

import kit_interfaces.Kit;
import beetle_game.BeetleGame;
import passport.KitPage;
import passport.Passport;
import main.ChemistryPassportGUI;

/**
 * This class starts the Beetle Kit
 * 
 * @author Humaira Orchee, Charlotte Dye, Sehr Sethi
 * @version April 30, 2015
 */
public class BeetleKit extends Kit {

	// The layout for the kit
	public static final CardLayout CARD_LAYOUT = new CardLayout();

	// The name of the first page to load for the Beetle Kit (i.e., the
	// instructions page)
	private static final String START_PAGE_NAME = "Start Page";

	// The name of the page with the actual estimation game
	private static final String ESTIMATION_PAGE = "Estimation Page";

	// The page with the instructions for the Beetle Kit
	private static EstimationStartPage startPage;


	/**
	 * Creates a new BeetleKit object with a reference to the
	 * ChemistryPassportGUI. NOTE: This constructor is used to start the reward
	 * game when users play the reward game before playing the Estimation Game.
	 * When setting up the Estimation Game itself, use the other constructor.
	 * 
	 * @param mainGUI
	 *            The main GUI for the Chemistry Passport application.
	 */
	public BeetleKit(ChemistryPassportGUI mainGUI) {

		super(mainGUI);
	}

	/**
	 * Creates a new BeetleKit object, sets the information necessary to load
	 * the game, and sets the layout.
	 * 
	 * @param mainGUI
	 *            The main GUI for the Chemistry Passport application.
	 * @param kitProgress
	 *            An integer representing the user's progress in the Beetle Kit.
	 * @param kitIndex
	 *            The index of this kit.
	 */
	public BeetleKit(ChemistryPassportGUI mainGUI, Integer kitProgress,
			Integer kitIndex) {

		super(mainGUI, kitProgress, kitIndex);

		// Set the layout to a card layout
		this.setLayout(CARD_LAYOUT);


	}

	/**
	 * Flips to the next page of the beetle kit.
	 */
	public void nextPage() {

		CARD_LAYOUT.next(this);

		repaint();
	}

	/**
	 * Flips to the previous page of the beetle kit
	 */
	public void prevPage() {

		CARD_LAYOUT.previous(this);

		repaint();
	}

	/**
	 * Creates the EstimationGame for this kit and adds it to the layout
	 */
	public void createEstimationGame() {

		// Create the EstimationGame
		EstimationGame app = new EstimationGame(this);

		// Add it to the BeetleKit's card layout with the name specified by
		// ESTIMATION_PAGE
		add(app, ESTIMATION_PAGE);
	}

	/**
	 * Returns the starting page of the BeetleKit (i.e., the instructions page)
	 * 
	 * @return The EstimationStartPage for this kit.
	 */
	public static EstimationStartPage getStartPage() {
		return startPage;
	}

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

		// go to the passport card in the main GUI
		mainGUI.goToCard(ChemistryPassportGUI.PASSPORT_TEXT);

		// Go to the corresponding page of the passport
		passport.goToPage(getButtonName());

		// Get the kit page for this kit
		KitPage kitPage = passport.getKitPage(getButtonName());

		// Start the animation that shows the sticker
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

	/**
	 * Get the passport that this BeetleKit belongs to
	 * 
	 * @return The user's passport
	 */
	public Passport getPassport() {
		return passport;
	}

//	@Override
//	protected void setUserKitProgress(int progress) {
//
//		passport.getUser().setKitProgress(kitIndex, progress);
//
//	}
}
