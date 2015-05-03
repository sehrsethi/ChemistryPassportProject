package passport;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import main.ChemistryPassportApplet;

/**
 * The passport page relevant to each kit
 * @author Humaira Orchee, Charlotte Dye
 * @version March 25, 2015
 */
public class KitPage extends JPanel {

	// The name of the page
	private String pageName;

	// The name of the child
	private String childName;

	// The name/location of the image this page shows
	private static final String IMAGE_FILE = "images/logo.png";

	// The font for the kit name
	private static final Font KIT_NAME_FONT = new Font("Times New Roman",
			Font.BOLD, 60);

	// The font for the line with the child's name
	private static final Font CHILD_FONT = new Font("Times New Roman",
			Font.PLAIN, 40);

	// The text color
	private static final Color TEXT_COLOR = Color.BLACK;

	// The background color
	private static final Color BACKGROUND_COLOR = Color.WHITE;

	// The coordinates of the sticker are updated every DELAY milliseconds
	private static final int DELAY = 10;

	// If the sticker never reaches its final destination, then stop the
	// animation after a certain period of time has passed
	private static final long ANIMATION_MAX_RUNTIME = 7000 ;
	
	// The animation will continue for at least this long
	private static final long ANIMATION_MIN_RUNTIME = 3000;

	// Whether the sticker should be displayed
	private boolean showSticker;

	// True if they have just earned the sticker:
	// If earnSticker is true, show an animation of the sticker being put on the
	// page
	private boolean earnSticker = false;

	// the current coordinates of the sticker
	private double currentX;
	private double currentY;

	// The timer used for the animation
	private Timer timer;

	// The sticker
	private ImageIcon imageIcon;

	// The coordinates where the sticker actually stays
	private double finalX;
	private double finalY;

	// The amount by which the coordinates of the sticker change
	private double animationConstantX = -3;
	private double animationConstantY = 5;

	// allows a little flexibility so that when the image is near the center of
	// the screen (and not necessarily at the very center) the animation stops
	private Rectangle2D rectangle;

	// Keeps track of when the animation starts
	private long startTime;

	// Keep track of the time elapsed since the animation started
	//private long currentTime;

	// Whether or not this kit page is the last page of the passport. Need to
	// know this to add the appropriate buttons.
	private boolean isLastPage = true;

	// forward button
	private JButton fwdButton;

	// The passport this KitPage belongs to
	private Passport passport;
	
	// button to play reward game
	private JButton rewardButton = new JButton("Play Reward Game") ; 
	
	private Image logo ;

	/**
	 * Creates a new page for the kit
	 * 
	 * @param pageName
	 *            The name of the page
	 * 
	 * @param showSticker
	 *            Whether the sticker should be displayed
	 */
	public KitPage(String pageName, Passport passport, boolean showSticker) {
		
		
		// Note whether we should show the sticker
		this.showSticker = showSticker;

		
		// Set the page name
		this.pageName = pageName.toUpperCase();

		this.passport = passport;

		// Save the name of the child
		this.childName = passport.getUserAdventureName();

		// Set the layout to BoxLayout
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// Set the size
		this.setSize(new Dimension(ChemistryPassportApplet.PAGE_WIDTH, ChemistryPassportApplet.PAGE_HEIGHT));

		// this.setPreferredSize(new Dimension(Passport.PAGE_WIDTH,
		// Passport.PAGE_HEIGHT));
		// this.setMinimumSize(new Dimension(Passport.PAGE_WIDTH,
		// Passport.PAGE_HEIGHT));

		// Set the background color
		this.setBackground(BACKGROUND_COLOR);
		
		
		//Create the ImageIcon from the file to create the sticker
		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream(IMAGE_FILE);

			logo = ImageIO.read(input);
			
			imageIcon = new ImageIcon(logo);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		finalX = this.getWidth() / 2 - imageIcon.getIconWidth() / 2;

		finalY = this.getHeight() / 2 - imageIcon.getIconHeight() / 2 - 50;

		currentX = finalX;

		createTimer();

		// the dimensions will change depending on panel size and image size and
		// speed of animation
		rectangle = new Rectangle2D.Double(finalX - 15, finalY - 10, 20, 20);
		
		// Add everything to the page
		addContent();
		
	
				
		
		
		
		this.setFocusable(true);

	}

	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.setColor(BACKGROUND_COLOR);

		g.fillRect(0, 0, getWidth(), getHeight());

		paintSticker(g);

		
	}

	/**
	 * Draws either a placeholder for the sticker or the sticker itself
	 * 
	 * @param g
	 *            The graphics object to draw on
	 */
	private void paintSticker(Graphics g) {
		
		
		if (showSticker) {

			
			imageIcon.paintIcon(this, g, (int) finalX, (int) finalY);
			
			enableRewardButton();
			
			//repaint() ;

		} else if (!showSticker) {

			if (!earnSticker) {
				
				((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);

				g.setColor(Color.LIGHT_GRAY);

				int circleDiameter = 150;

				g.fillOval(getWidth() / 2 - circleDiameter / 2, getHeight() / 2
						- circleDiameter / 2 - 50, circleDiameter,
						circleDiameter);

				g.setColor(Color.BLACK);

				g.setFont(new Font("Times New Roman", Font.PLAIN, 16));

				g.drawString("You haven't earned", getWidth() / 2
						- circleDiameter / 2 + 15, getHeight() / 2
						- circleDiameter / 2 + 20);

				g.drawString("this sticker yet!", getWidth() / 2
						- circleDiameter / 2 + 25, getHeight() / 2
						- circleDiameter / 2 + 40);

			} else if (earnSticker) {

				imageIcon.paintIcon(this, g, (int) currentX, (int) currentY);

			}
		}

	}

	/**
	 * Add the content to the kit page
	 */
	private void addContent() {

		// Create and add rigid area for spacing
		this.add(Box.createRigidArea(new Dimension(20, 10)));

		addKitName();

		// Create and add rigid area for spacing
		this.add(Box.createRigidArea(new Dimension(20, 75)));

		// Create and add rigid area for spacing
		this.add(Box.createRigidArea(new Dimension(50, 300)));

		// Add the child's name
		addChildName();

		this.add(Box.createRigidArea(new Dimension(1, 50)));

		addRewardButton();
		
		this.add(Box.createRigidArea(new Dimension(10, 50)));
		
		addFwdAndBackButtons();
		
	}

	/**
	 * 
	 */
	private void addChildName() {
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


	
	public void startStickerAnimation() {
		earnSticker = true;
		
		// start animation
		startTime = System.currentTimeMillis();

		timer.start();
	}

	/**
	 * Set whether the sticker should be shown
	 * @param showSticker True if the sticker should be shown, or false otherwise
	 */
	public void setShowSticker(boolean showSticker) {
		this.showSticker = showSticker;
	}

	/**
	 * 
	 */
	private void addKitName() {
		
		// Add the kit name label
		JLabel kitNameLabel = new JLabel(pageName);
		
		// Set the font of the child's name
		kitNameLabel.setFont(KIT_NAME_FONT);

		// Set the font color
		kitNameLabel.setForeground(TEXT_COLOR);

		// Center the kit name
		// kitNameLabel.setXAlignment(Component.CENTER_ALIGNMENT);
		kitNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Add to the page
		add(kitNameLabel);
	}

	/**
	 * Creates and returns and empty panel to create space between components
	 * 
	 * @return An empty panel to create space between components
	 */
	private JPanel createSpacing() {

		JPanel panel = new JPanel();

		// make this background transparent
		panel.setBackground(new Color(0, 0, 0, 0));

		return panel;

	}

	/**
	 * Animates the sticker
	 * 
	 * @param maxWidth
	 *            The x coordinate beyond which the sticker cannot go
	 * @param maxHeight
	 *            The y coordinate beyond which the sticker cannot go
	 */
	private void animate(int maxWidth, int maxHeight) {

		// updates sticker coordinates
		currentX += animationConstantX;
		currentY += animationConstantY;

		// bounce off left
		if (currentX <= 0) {

			animationConstantX = animationConstantX * -1;

		}

		// bounce of bottom
		if (currentY + imageIcon.getIconHeight() >= maxHeight) {

			animationConstantY = animationConstantY*-1;
		}

		// bounce off right
		if (currentX + imageIcon.getIconWidth() >= maxWidth) {

			// timer.setDelay(DELAY - 1);

			animationConstantX = animationConstantX * -1;
			animationConstantY = -1.5;
		}

		// bounce off top
		if (currentY <= 0) {

			animationConstantY = 1.5;
		}

	}

	/**
	 * Returns true if this page is the last page of the kit. Returns false
	 * otherwise.
	 * 
	 * @return True if this page is the last page of the kit and false
	 *         otherwise.
	 */
	public boolean isLastPage() {
		return isLastPage;
	}

	/**
	 * Sets this page to be last page or not depending on the value of the
	 * parameter
	 * 
	 * @param isLastPage
	 *            True if this is the last page of the passport. False if this
	 *            is not the last page of the passport.
	 */
	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;

		// If this kit page is the last page then the forward button should not
		// be working. If it is not the last page then the forward button should
		// be working.
		if (isLastPage) {

			fwdButton.setEnabled(false);

		} else {

			fwdButton.setEnabled(true);
		}
	}

	/**
	 * Creates the back and forward buttons
	 */
	private void addFwdAndBackButtons() {

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200,
				15));
		buttonPanel.setBackground(Color.white);

		Font font = new Font("Verdana", Font.PLAIN, 18);

		// backButton
		JButton backButton = new JButton("<----");
		backButton.setFont(font);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				passport.previouPage();

			}
		});

		// forward button
		JButton forwardButton = new JButton("---->");
		forwardButton.setFont(font);

		forwardButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				passport.nextPage();

			}
		});

		// the forward button does not work for the last kitpage
		if (isLastPage) {

			forwardButton.setEnabled(false);
		}

		this.fwdButton = forwardButton;

		buttonPanel.add(backButton);
		buttonPanel.add(forwardButton);

		add(buttonPanel);
	}

	/**
	 * 
	 */
	private void createTimer() {
		timer = new Timer(DELAY, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// update the coordinates of the sticker
				animate(KitPage.this.getWidth(), KitPage.this.getHeight() - 110);

				// If the sticker has roughly reached the middle of the screen
				// or if the
				// animation has happened for a certain period of time, then
				// stop the
				// animation

				
				long timeElapsed = System.currentTimeMillis() - startTime;
				
				
				if (rectangle.intersects(currentX, currentY, imageIcon.getIconWidth()/5, imageIcon.getIconHeight()/5) && (timeElapsed > ANIMATION_MIN_RUNTIME) ) {

					timer.stop();

				} else if(timeElapsed > ANIMATION_MAX_RUNTIME){
					
					
					currentX = finalX ;
					
					currentY = finalY ;
					
					timer.stop();
					
				}
				
				repaint();

			}
		});
	}
	
	private void addRewardButton(){
		
		rewardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		rewardButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//The reward game to add
				JComponent reward = passport.getCurrentKit().createRewardGame();
				 
				//The name of the reward game to add (necessary for CardLayout)
				String rewardName = passport.getCurrentKit().getRewardName();
				
				// Add to CardLayout
				//passport.getChemGUI().add(beetle, "Beetle Reward Game");
				passport.getChemGUI().add(reward, rewardName);
				
				// Load in CardLayout
				//passport.getChemGUI().goToCard("Beetle Reward Game");
				passport.getChemGUI().goToCard(rewardName);
				
				repaint();
				
			}
		});
		
		rewardButton.setEnabled(false);
		
		add(rewardButton) ;
		
	}

	public void enableRewardButton(){
		
		rewardButton.setEnabled(true);
	}
	
	public String getPageName(){
		
		return pageName ;
	}

}
