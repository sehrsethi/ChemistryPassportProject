package passport;

import java.awt.CardLayout;
import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import kit_interfaces.Kit;
import main.ChemGetPropertyValues;
import main.ChemistryPassportGUI;
import user.User;
import beetle_kit.BeetleKit;

/**
 * A Chemistry Passport that welcomes the user to the Chemistry Passport
 * Program, shows the the user the existing kits in this program, lets the user
 * choose and play with a kit, keeps track of the user's progress for each kit
 * and lets the user play the reward game upon completion of a kit.
 * 
 * @author Humaira Orchee, Charlotte Dye, Sehr Sethi
 * @version May 3, 2015
 *
 */
public class Passport extends JPanel {

	// The border around the passport
	private static final Border BORDER = BorderFactory.createLineBorder(
			Color.GRAY, 4);

	// The layout for the passport
	private static final CardLayout CARD_LAYOUT = new CardLayout();

	// The name by which the Introduction page of the passport is referred to in
	// the CARD_LAYOUT of the passport
	public static final String INTRO_PAGE_NAME = "Intro Page";

	// The name by which the KitSelectionPage of the passport is referred to in
	// the CARD_LAYOUT of the passport
	public static final String KIT_SELECTION_NAME = "Kit Selection";

	// The index of the Introduction page
	private static final int INTRO_PAGE_INDEX = 0;

	// The index of the KitSelectionPage
	private static final int KIT_SELECTION_INDEX = 1;

	// the index of the first kit page, i.e. the first kit page after the
	// Introduction Page and KitSelectionPage. It is more like the passport page
	// corresponding to the Beetle Kit
	private static final int FIRST_KIT_INDEX = 2;

	// The main GUI of the entire Chemistry Passport Program that this Passport
	// object is added to.
	private ChemistryPassportGUI mainGUI;

	// The user that the passport belongs to
	private User user;

	// The name of the user whose passport this is
	private String userAdventureName;

	// The Introduction page of the passport
	private IntroductionPage introductionPage;

	// The kit selection page
	private KitSelectionPage kitSelectionPage;

	// The list of page names in the passport. This also includes the names of
	// the IntroductionPage and the KitSelectionPage.
	private ArrayList<String> pageNames = new ArrayList<String>();

	// The list of kit pages in the passport. This does not include the
	// IntroductionPage and the KitSelectionPage.
	private ArrayList<KitPage> kitPages = new ArrayList<KitPage>();

	// The index of the currently displayed page.
	private int currentPageIndex = INTRO_PAGE_INDEX;

	// The object that provides the information from the config.properties file
	private ChemGetPropertyValues propValues;

	// The kit currently activated
	private Kit currentKit;

	/**
	 * Create a new passport.
	 * 
	 * @param user
	 *            The user for whom this passport is being created
	 * @param mainGUI
	 *            The main GUI of the entire Chemistry Passport Program that
	 *            this Passport object is added to.
	 */
	public Passport(User user, ChemistryPassportGUI mainGUI) {

		this.mainGUI = mainGUI;

		propValues = mainGUI.getPropValues();

		this.user = user;

		// Save the user;s adventure name (will be needed for various pages)
		this.userAdventureName = user.getAdventureName();

		// Set the layout to the card layout we created
		this.setLayout(CARD_LAYOUT);

		// set the border
		this.setBorder(BORDER);

		// create the Introduction page
		introductionPage = new IntroductionPage(this);

		// create the KitSelectionPage
		kitSelectionPage = new KitSelectionPage(this);

		// Add the name of the Introduction page
		pageNames.add(INTRO_PAGE_NAME);

		// Add the name of the KitSelectionPage
		pageNames.add(KIT_SELECTION_NAME);

		// Add the Introduction page to the passport panel
		this.add(introductionPage, INTRO_PAGE_NAME);

		// add the KitSelectionPage to the passport panel
		this.add(kitSelectionPage, KIT_SELECTION_NAME);

		// Display the Introduction page
		CARD_LAYOUT.show(this, INTRO_PAGE_NAME);

		// add the kit pages for all the existing kits in this application
		addExistingKitPages();

	}

	/**
	 * Returns the object that provides the information from the
	 * config.properties file
	 * 
	 * @return The object that provides the information from the
	 *         config.properties file
	 */
	public ChemGetPropertyValues getPropVals() {
		return propValues;
	}

	/**
	 * Adds the kit pages for all the existing kits in this application
	 */
	private void addExistingKitPages() {

		// gets the names to be displayed on buttons and the kit pages of all
		// the existing kits
		String[] kitButtonNames = propValues.getKitButtonNames();

		// go through all the kit pages for the current user and check if they
		// have completed that kit or not
		for (int i = 0; i < kitButtonNames.length; i++) {

			int userKitProgress = user.getKitProgress().get(i);

			int kitCompletionCriteria = propValues.getKitCompletionCriteria()[i];

			// if the user has completed this kit, display the sticker.
			// Otherwise, don't show sticker.
			if (userKitProgress >= kitCompletionCriteria) {

				addKitPage(kitButtonNames[i], true);

			} else {

				addKitPage(kitButtonNames[i], false);
			}
		}
	}

	/**
	 * Returns the main GUI of the entire Chemistry Passport Program that this
	 * Passport object is added to.
	 * 
	 * @return The main GUI of the entire Chemistry Passport Program that this
	 *         Passport object is added to.
	 */
	public ChemistryPassportGUI getChemGUI() {
		return mainGUI;
	}

	/**
	 * Sets the value of current kit of this application to the kit that is
	 * currently being used
	 * 
	 * @param currentKit
	 *            The the kit that is currently being used
	 */
	public void setCurrentKit(Kit currentKit) {
		this.currentKit = currentKit;
	}

	/**
	 * Returns the current kit
	 * 
	 * @return The kit that is currently being played
	 */
	public Kit getCurrentKit() {
		return currentKit;
	}

	/**
	 * Add the page with the specified name to the list of page names
	 * 
	 * @param kitPageName
	 *            The name of the kit page to add. This cannot be the
	 *            IntroductionPage or the KitSelectionPage.
	 * 
	 * @param hasSticker
	 *            True if the sticker for the specified kit page is to be
	 *            displayed. Otherwise, false.
	 */
	public void addKitPage(String kitPageName, boolean hasSticker) {

		// Add the page name
		pageNames.add(kitPageName);

		// not applicable if there are no previous kit pages
		if (!kitPages.isEmpty()) {

			// the previous last kit page is no longer the last page
			kitPages.get(kitPages.size() - 1).setLastPage(false);
		}

		// Create the kit page
		KitPage page = new KitPage(kitPageName, this, hasSticker);

		// add the kit page to the list of Kit Pages
		kitPages.add(page);

		// this new page is now the last page
		page.setLastPage(true);

		// Add to the card layout of this passport
		this.add(page, kitPageName);

		// add the button for the new kit in the KitSelectionPage
		kitSelectionPage.addKitButton(kitPageName);

	}

	/**
	 * Goes to the next passport page
	 */
	public void nextPage() {

		// go from the Introduction Page to the KitSelectionPage
		if (currentPageIndex == INTRO_PAGE_INDEX) {

			currentPageIndex = KIT_SELECTION_INDEX;

			CARD_LAYOUT.show(this, pageNames.get(currentPageIndex));

		} else if (currentPageIndex == KIT_SELECTION_INDEX) {

			// Go from the KitSelectionPage to the first kit page. Initially it
			// is
			// probably the Beetle Kit Page

			currentPageIndex = FIRST_KIT_INDEX;

			createKit();

			CARD_LAYOUT.show(this, pageNames.get(currentPageIndex));

		} else {

			// otherwise just progress normally, i.e. going from one kit page to
			// the next

			// update current page
			currentPageIndex = (currentPageIndex + 1) % pageNames.size();

			// Create the kit to be able to add the reward game of that kit to
			// the application
			createKit();

			CARD_LAYOUT.next(this);
		}

		repaint();
	}

	/**
	 * Goes to the specified page of the passport
	 * 
	 * @param pageName
	 *            The name of the passport page to go to
	 */
	public void goToPage(String pageName) {

		// Iterate through the list of page names
		for (int i = 0; i < pageNames.size(); i++) {

			// Once we find this page, set its index as our current index
			if (pageNames.get(i).equals(pageName)) {
				currentPageIndex = i;
				break;
			}
		}

		// Go to the specified page
		CARD_LAYOUT.show(this, pageNames.get(currentPageIndex));

		// NOTE : There is no need to create a kit here to add the reward game
		// because of the following reasons.
		// 1. The BeetleGame class only calls this method to go to the
		// KitSlectionPage
		// 2. When a specific kit calls this method to go to its associated kit
		// page on the passport, it should do the work needed to create and add
		// the reward game.

		repaint();
	}

	/**
	 * Goes to the previous passport page
	 */
	public void previouPage() {

		// go from KitSelectionPage to Introduction Page
		if (currentPageIndex == KIT_SELECTION_INDEX) {

			currentPageIndex = INTRO_PAGE_INDEX;

			CARD_LAYOUT.show(this, pageNames.get(currentPageIndex));

		} else if (currentPageIndex == FIRST_KIT_INDEX) {

			// go from first kit page (probably Beetle kit page) to the Kit
			// Selection Page

			currentPageIndex = KIT_SELECTION_INDEX;

			CARD_LAYOUT.show(this, pageNames.get(currentPageIndex));

		} else {

			// otherwise just progress normally, i.e. going from one kit page to
			// the previous one

			// update current page
			if (currentPageIndex > 0) {

				currentPageIndex--;

			} else {

				currentPageIndex = 0;
			}

			// Create the kit to be able to add the reward game of that kit to
			// the application
			createKit();

			CARD_LAYOUT.previous(this);
		}

		repaint();
	}

	/**
	 * Creates the kit corresponding to the current kit page displayed on the
	 * passport
	 */
	private void createKit() {

		try {

			// The 'Reflection' technique is used to create the appropriate kit.
			// This is because we did not want to load every existing kit if it
			// is not used and because we did not want the developers who want
			// to add new kits to this application have to edit/add to our
			// existing code.

			// A kit is created just so that the reward game for that kit can be
			// added to the running application. The kit created is essentially
			// a blank kit because unless the user wants to actually use that
			// kit, there is no need to create all the other pieces of the kit.
			// The constructor used is : public Kit(ChemistryPassportGUI
			// mainGUI)
			Kit kit = (Kit) Class
					.forName(propValues.getKitNames()[currentPageIndex - 2])
					.getDeclaredConstructor(ChemistryPassportGUI.class)
					.newInstance(mainGUI);

			currentKit = kit;

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {

			Kit kit = new BeetleKit(mainGUI);

			currentKit = kit;
		}
	}

	/**
	 * Returns adventure name of the user
	 * 
	 * @return The adventure name of the user
	 */
	public String getUserAdventureName() {
		return userAdventureName;
	}

	/**
	 * Returns the list of page names. This also includes the names of the
	 * IntroductionPage and the KitSelectionPage.
	 * 
	 * @return The list of page names
	 */
	public ArrayList<String> getPageNames() {
		return pageNames;
	}

	/**
	 * Removes the kit page associated with the given page name from the
	 * passport
	 * 
	 * TODO Test if this method works
	 * 
	 * @param pageName
	 *            The name of the kit page to be removed from the passport
	 */
	public void removeKitPage(String pageName) {

		pageNames.remove(pageName);

		for (int i = 0; i < kitPages.size(); i++) {

			String kitName = kitPages.get(i).getName();

			if (kitName.equals(pageName)) {

				kitPages.remove(i);
			}
		}

		kitSelectionPage.removeKitButton(pageName);

		revalidate();
		repaint();
	}

	/**
	 * Given the name displayed on the kit page, it returns the kit page.
	 * 
	 * @param pageName
	 *            The name displayed on the kit page that is wanted
	 * @return The kit page, given the name displayed on that kit page
	 */
	public KitPage getKitPage(String pageName) {

		for (int i = 0; i < kitPages.size(); i++) {

			if (kitPages.get(i).getPageName().equalsIgnoreCase(pageName)) {

				return kitPages.get(i);
			}
		}

		return null;
	}

	/**
	 * Returns the user.
	 * 
	 * @return The user
	 */
	public User getUser() {
		return user;
	}
}
