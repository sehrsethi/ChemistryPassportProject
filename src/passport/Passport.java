package passport;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import kit_interfaces.Kit;
import main.ChemGetPropertyValues;
import main.ChemistryPassportGUI;
import user.User;

/**
 * A Chemistry Passport that keeps track of the user's progress for each kit
 * 
 * @author Humaira Orchee, Charlotte Dye
 * @version April 4, 2015
 *
 */
public class Passport extends JPanel implements MouseListener {

	// Passport should take in fake name, (maybe grade?)
	// Every page should take title of kit
	// Page could need add sticker method
	// Use logo.png as sticker for now, or get beetle thing

	// The width of the page of the passport
	public static final int PAGE_WIDTH = 622;

	// The height of the page of the passport
	public static final int PAGE_HEIGHT = 738;

	// set a border around it
	public static final Border BORDER = BorderFactory.createLineBorder(
			Color.GRAY, 4);

	// The layout for the passport
	private static final CardLayout CARD_LAYOUT = new CardLayout();

	// The name of the intro page
	public static final String INTRO_PAGE_NAME = "Intro Page";

	// The name of the KitSelectionPage
	public static final String KIT_SELECTION_NAME = "Kit Selection";

	// The index of the intro page
	private static final int INTRO_PAGE_INDEX = 0;

	// The index of the KitSelectionPage
	private static final int KIT_SELECTION_INDEX = 1;

	// the index of the first kit page, i.e. the first page after the Intro Page
	// and KitSelectionPage
	private static final int FIRST_KIT_INDEX = 2;
	
	
	private ChemistryPassportGUI chemGUI;

	// The user (child) that the passport belongs to
	private User user;

	// The name of the child whose passport this is
	private String userName;


	// The intro page of the passport
	private IntroductionPage introductionPage;

	// The kit selection page
	private KitSelectionPage kitSelectionPage;
	// private static final FirstPage Page = new FirstPage();

	// The list of page names
	private ArrayList<String> pageNames = new ArrayList<String>();

	// The list of pages
	private ArrayList<KitPage> kitPages = new ArrayList<KitPage>();

	// The index of the currently displayed page
	private int currentPageIndex = INTRO_PAGE_INDEX;
	
	//The ChemGetPropertyValues instance
	private ChemGetPropertyValues propValues;
	
	//The kit currently running
	private Kit currentKit;
	

	/**
	 * Create a new passport
	 * 
	 * @param user
	 *            TODO The user for whom this passport is being created
	 * @param passportGUI TODO
	 */
	public Passport(User user, ChemistryPassportGUI chemGUI) {
		
		this.chemGUI = chemGUI;
		
		propValues = chemGUI.getPropValues() ;
		
		this.user = user;

		// Save the child's name (will be needed for various pages)
		this.userName = user.getAdventureName();

		// Set the layout to the card layout we created
		this.setLayout(CARD_LAYOUT);

		// set the border
		this.setBorder(BORDER);

		// Add a mouse listener so that when we click on the passport,
		// it goes to the next page
		this.addMouseListener(this);

		introductionPage = new IntroductionPage(this);

		kitSelectionPage = new KitSelectionPage(this);

		// Add the name of the intro page
		pageNames.add(INTRO_PAGE_NAME);

		pageNames.add(KIT_SELECTION_NAME);

		// Add the intro page to the panel
		this.add(introductionPage, INTRO_PAGE_NAME);

		// add the kit selection age to the panel
		this.add(kitSelectionPage, KIT_SELECTION_NAME);

		// Display the first page
		CARD_LAYOUT.show(this, INTRO_PAGE_NAME);

		// TODO : sth needs to happen here so that when the passport is created,
		// it automatically adds the pages for all the existing kits. For now,
		// add them manually as examples

		//addPage("Bark Beetle", false);

		//addPage("Example", true);

		//addPage("Example 2", false);
		
		addExistingKitPages();

	}
	
	/**
	 * Returns an instance of ChemGetPropertyValues
	 * @return
	 */
	public ChemGetPropertyValues getPropVals(){
		return propValues;
	}
	
	private void addExistingKitPages(){
		//String[] kitClassNames = propVals.getKitNames();
		String[] kitButtonNames = propValues.getKitButtonNames();
		
		for (int i = 0; i < kitButtonNames.length; i++){
			
			//TODO : Will have to change the boolean to true if the kit was completed
			addPage(kitButtonNames[i],false);
		}
	}

	public ChemistryPassportGUI getChemGUI(){
		return chemGUI;
	}
	
	/**
	 * Sets the kit that is currently being played
	 * @param currentKit The current kit
	 */
	public void setCurrentKit (Kit currentKit){
		this.currentKit = currentKit;
	}
	
	/**
	 * Returns the current kit
	 * @return The kit that is currently being played
	 */
	public Kit getCurrentKit(){
		return currentKit;
	}
	
	/**
	 * Add the page with the specified name to the list of page names
	 * 
	 * @param pageName
	 *            The name of the page to add
	 * 
	 * @param hasSticker
	 *            Whether the sticker should be displayed for this page
	 */
	public void addPage(String pageName, boolean hasSticker) {

		// Add the page name
		pageNames.add(pageName);

		// not applicable if there are no previous kit pages
		if (!kitPages.isEmpty()) {

			// the previous last kit page is no longer the last page
			kitPages.get(kitPages.size() - 1).setLastPage(false);
		}

		// Create the page
		KitPage page = new KitPage(pageName, this, hasSticker);

		// add the kit page to the list of Kit Pages
		kitPages.add(page);

		// this new page is now the last page
		page.setLastPage(true);

		// Add to the card layout
		this.add(page, pageName);

		// add the button for the new kit in the Kit Selection Page
		kitSelectionPage.addKitButton(pageName);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	

	/**
	 * Goes to the next passport page
	 */
	public void nextPage() {

		// go from the first page to the kit Selection page
		if (currentPageIndex == INTRO_PAGE_INDEX) {

			currentPageIndex = KIT_SELECTION_INDEX;

			CARD_LAYOUT.show(this, pageNames.get(currentPageIndex));

		}
		// go from the Kit Selection Page to the first kit page. Initially it is
		// probably the Beetle Kit Page
		else if (currentPageIndex == KIT_SELECTION_INDEX) {

			currentPageIndex = FIRST_KIT_INDEX;

			CARD_LAYOUT.show(this, pageNames.get(currentPageIndex));

		}
		// otherwise just progress normally
		else {

			// update current page
			currentPageIndex = (currentPageIndex + 1) % pageNames.size();

			CARD_LAYOUT.next(this);
		}

		repaint();
	}

	/**
	 * Goes to the specified page of the passport
	 * @param pageName The name of the passport page
	 */
	public void goToPage(String pageName){

		//Iterate through the list of page names
		for (int i = 0; i < pageNames.size(); i++){
			
			//Once we find this page, set its index as our current index
			if (pageNames.get(i).equals(pageName)){
				currentPageIndex = i;
				break;
			}
		}
		
		//Go to the specified page
		CARD_LAYOUT.show(this,pageNames.get(currentPageIndex));
		
		repaint();
	}
	
	/**
	 * Goes to the previous passport page
	 */
	public void previouPage() {

		// go from Kit Selection Page to Intro Page
		if (currentPageIndex == KIT_SELECTION_INDEX) {

			currentPageIndex = INTRO_PAGE_INDEX;

			CARD_LAYOUT.show(this, pageNames.get(currentPageIndex));

		}
		// go from first kit page (probably Beetle kit page) to the Kit
		// Selection Page
		else if (currentPageIndex == FIRST_KIT_INDEX) {

			currentPageIndex = KIT_SELECTION_INDEX;

			CARD_LAYOUT.show(this, pageNames.get(currentPageIndex));

		}
		// otherwise progress normally
		else {

			// update current page
			if (currentPageIndex > 0) {

				currentPageIndex--;

			} else {

				currentPageIndex = 0;
			}

			CARD_LAYOUT.previous(this);
		}

		repaint();
	}

	/**
	 * Returns name of the user
	 * 
	 * @return The name of the user
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Returns the list of page names
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
	 * Returns the kit page
	 * @param pageName The name of the kit page
	 * @return
	 */
	public KitPage getKitPage(String pageName){
		
		for(int i = 0 ; i < kitPages.size() ; i++){
			
			if(kitPages.get(i).getPageName().equalsIgnoreCase(pageName)){
				
				return kitPages.get(i) ;
			}
		}
		
		// TODO : catch for null pointer exception later
		return null ;
	}
	
	/**
	 * Returns the user
	 * @return the user
	 */
	public User getUser(){
		return user;
	}
}
