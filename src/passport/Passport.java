package passport;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Passport extends JPanel implements MouseListener {

	// Passport should take in fake name, (maybe grade?)
	// Every page should take title of kit
	// Page could need add sticker method
	// Use logo.png as sticker for now, or get beetle thing

	// The name of the child whose passport this is
	private String childName;

	// The width of the page of the passport
	private static final int PAGE_WIDTH = 500;

	// The height of the page of the passport
	private static final int PAGE_HEIGHT = 700;

	// The layout for the passport
	private static final CardLayout CARD_LAYOUT = new CardLayout();

	// The name of the first page
	private static final String FIRST_PAGE_NAME = "First Page";

	// The first page of the passport
	private FirstPage firstPage;
	//private static final FirstPage firstPage = new FirstPage();

	// The list of page names
	private ArrayList<String> pageNames = new ArrayList<String>();

	// The currently displayed page
	private int currentPage = 0;

	/**
	 * Create a new passport
	 * 
	 * @param childName
	 *            The name of the child
	 */
	public Passport(String childName) {
		firstPage = new FirstPage(childName);
		
		// Save the child's name (will be needed for various pages)
		this.childName = childName;
		
		// Set the layout to the card layout we created
		this.setLayout(CARD_LAYOUT);

		// Add a mouse listener so that when we click on the passport,
		// it goes to the next page
		this.addMouseListener(this);

		//Add the name of the first page
		pageNames.add(FIRST_PAGE_NAME);
		
		// Add the first page to the panel
		this.add(firstPage, FIRST_PAGE_NAME);

		// Display the first page
		CARD_LAYOUT.show(this, FIRST_PAGE_NAME);

		// Add the bark beetle page to the passport 
		//NOTE: At some point we need to find whether we should show the sticker
		addPage("Bark Beetle", false);

	}

	/**
	 * Add the page with the specified name to the list of page names
	 * 
	 * @param pageName
	 *            The name of the page to add
	 *            
	 * @param hasSticker Whether the sticker should be displayed for this page
	 */
	public void addPage(String pageName, boolean hasSticker) {

		//Add the page name
		pageNames.add(pageName);
		
		//Create the page
		KitPage page = new KitPage(pageName, childName, hasSticker);
		
		//Add to the card layout
		this.add(page, pageName);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		//If we have more pages to show, prepare to show the next page
		if (currentPage + 1 < pageNames.size()){
			// Note that we're going to the next page
			currentPage++;
		}
		
		//Otherwise, prepare to show the first page again
		else {
			currentPage = 0;
		}

		// Show which page we are going to show
		//System.out.println(currentPage);

		// Show the next page
		CARD_LAYOUT.show(this, pageNames.get(currentPage));
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

	public static void main(String[] args) {

		// Create the frame
		JFrame frame = new JFrame();

		// Add the passport to the frame--will need to figure out
		// how to do the name getting part
		frame.getContentPane().add(new Passport("Pretend child"));

		// Set the size to the specified page size
		frame.setSize(Passport.PAGE_WIDTH, Passport.PAGE_HEIGHT);

		// Make visible
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}