package passport;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KitPage extends JPanel {

	// The width of the page for the kit
	private static final int PAGE_WIDTH = 500;

	// The height of the page for the kit
	private static final int PAGE_HEIGHT = 700;

	// The name of the page
	private String pageName;

	// The name of the child
	private String childName;

	// The name/location of the image this page shows:
	// NOTE: THIS WILL CHANGE TO GET THE APPROPRIATE STICKER
	// We should either have the image file name be some sort of parameter
	// or we could have standardized file names (e.g., sticker_[KIT NAME].png)
	private static final String IMAGE_FILE = "src//images//logo.png";
	
	//The icon that displays when the user hasn't earned this sticker
	private static final String EMPTY_IMAGE_FILE = "src//images//nosticker.png";

	// The font for the kit name
	private static final Font KIT_NAME_FONT = new Font("Times New Roman",
			Font.BOLD, 60);

	// The font for the line with the child's name
	private static final Font CHILD_FONT = new Font("Times New Roman",
			Font.PLAIN, 60);

	// The text color
	private static final Color TEXT_COLOR = Color.BLACK;

	// The background color
	private static final Color BACKGROUND_COLOR = Color.WHITE;

	// Whether the sticker should be displayed
	//ONLY SHOW THE STICKER AFTER THEY CLICK
	private boolean showSticker;

	/**
	 * Creates a new page for the kit
	 * 
	 * @param pageName
	 *            The name of the page
	 * 
	 * @param showSticker
	 *            Whether the sticker should be displayed
	 */
	public KitPage(String pageName, String childName, boolean showSticker) {

		// Note whether we should show the sticker
		this.showSticker = showSticker;

		// Set the page name
		this.pageName = pageName.toUpperCase();

		// Save the name of the child
		this.childName = childName;

		// Set the layout to BoxLayout
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// Set the size
		this.setSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));

		// Set the background color
		this.setBackground(BACKGROUND_COLOR);

		// Add everything to the page
		addContent();

	}

	/**
	 * Add the content to the kit page
	 */
	private void addContent() {
		
		//Create and add rigid area for spacing
		this.add(Box.createRigidArea(new Dimension(20,20)));

		// Add the kit name label
		// Kit name label
		JLabel kitNameLabel = new JLabel(pageName);

		// Set the font of the child's name
		kitNameLabel.setFont(KIT_NAME_FONT);

		// Set the font color
		kitNameLabel.setForeground(TEXT_COLOR);

		// Center the kit name
		//kitNameLabel.setXAlignment(Component.CENTER_ALIGNMENT);
		kitNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Add to the page
		add(kitNameLabel);
		
		//Create and add rigid area for spacing
				this.add(Box.createRigidArea(new Dimension(20,100)));

		// Check if we should add the sticker
		if (showSticker) {
			// Show the sticker
			
			

			// Create the sticker icon
			ImageIcon imageIcon = new ImageIcon(IMAGE_FILE);

			// Create a label for the sticker
			JLabel stickerLabel = new JLabel(imageIcon);

			//Center the label
			stickerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			// Add the sticker
			add(stickerLabel);
		} else {
			// Show empty sticker icon
			// Create the sticker icon
						ImageIcon imageIcon = new ImageIcon(EMPTY_IMAGE_FILE);

						// Create a label for the sticker
						JLabel emptyStickerLabel = new JLabel(imageIcon);

						//Center the label
						emptyStickerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
						
						// Add the sticker
						add(emptyStickerLabel);
		}
		
		//Create and add rigid area for spacing
		this.add(Box.createRigidArea(new Dimension(50,179)));

		// Add the child's name
		// Child's name label
		JLabel childNameLabel = new JLabel(childName);

		// Set the font of the child's name
		childNameLabel.setFont(CHILD_FONT);

		// Set the font color
		childNameLabel.setForeground(TEXT_COLOR);

		// Center the child's name
		childNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Add to the page
		add(childNameLabel);

	}

}
