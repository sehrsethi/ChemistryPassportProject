package passport;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import user.User;

public class Passport extends JPanel implements MouseListener {

	// Passport should take in fake name, (maybe grade?)
	// Every page should take title of kit
	// Page could need add sticker method
	// Use logo.png as sticker for now, or get beetle thing
	
	// The user (child) that the passport belongs to  
	private User user ;
	
	// The name of the child whose passport this is
	private String childName;

	// The width of the page of the passport
	public static final int PAGE_WIDTH = 500;

	// The height of the page of the passport
	public static final int PAGE_HEIGHT = 700;

	
	// set a border around it
	public static final Border BORDER = BorderFactory.createLineBorder(Color.GRAY, 4) ;


	// The layout for the passport
	private static final CardLayout CARD_LAYOUT = new CardLayout();

	// The name of the first page
	private static final String FIRST_PAGE_NAME = "First Page";

	// The first page of the passport
	private FirstPage firstPage;
	// private static final FirstPage firstPage = new FirstPage();

	// The list of page names
	private ArrayList<String> pageNames = new ArrayList<String>();

	// The list of pages
	private ArrayList<KitPage> kitPages = new ArrayList<KitPage>();

	// The currently displayed page
	private int currentPage = 0;

	/**
	 * Create a new passport
	 * @param user TODO
	 */
	public Passport(User user) {
		
		this.user = user ;

		// Save the child's name (will be needed for various pages)
		this.childName = user.getFakeName();

		// Set the layout to the card layout we created
		this.setLayout(CARD_LAYOUT);
		
		// set the border
		this.setBorder(BORDER);

		// Add a mouse listener so that when we click on the passport,
		// it goes to the next page
		this.addMouseListener(this);

		firstPage = new FirstPage(this);

		// Add the name of the first page
		pageNames.add(FIRST_PAGE_NAME);

		// Add the first page to the panel
		this.add(firstPage, FIRST_PAGE_NAME);

		// Display the first page
		CARD_LAYOUT.show(this, FIRST_PAGE_NAME);

		// Add the bark beetle page to the passport
		// NOTE: At some point we need to find whether we should show the
		// sticker
		addPage("Bark Beetle", false);
		
		addPage("Example", true);

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
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		//CARD_LAYOUT.next(this);

		/*// If we have more pages to show, prepare to show the next page
		if (currentPage + 1 < pageNames.size()) {
			// Note that we're going to the next page
			currentPage++;
		}

		// Otherwise, prepare to show the first page again
		else {
			currentPage = 0;
		}

		// Show which page we are going to show
		// System.out.println(currentPage);

		// Show the next page
		CARD_LAYOUT.show(this, pageNames.get(currentPage));*/
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
	 * Goes to the nest passport page
	 */
	public void nextPage(){
		
		CARD_LAYOUT.next(this);
		
		repaint();
	}
	
	/**
	 * Goes to the previous passport page
	 */
	public void previouPage(){
		
		CARD_LAYOUT.previous(this);
		
		repaint();
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getChildName() {
		return childName;
	}

	public static void main(String[] args) {

		// Create the frame
		JFrame frame = new JFrame();

		 ArrayList<Integer> kitProgress = new ArrayList<Integer>() ;
		 kitProgress.add(5) ;
		
		User user = new User("user name", "long Fake Name Fake", "K", kitProgress) ;
		
		// Add the passport to the frame--will need to figure out
		// how to do the name getting part
		frame.getContentPane().add(new Passport(user));

		// Set the size to the specified page size
		frame.setSize(Passport.PAGE_WIDTH, Passport.PAGE_HEIGHT);

		// Make visible
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
