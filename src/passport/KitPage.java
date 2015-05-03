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
 * The passport page relevant to each existing kit in the Chemistry Passport
 * Program
 * 
 * @author Humaira Orchee, Charlotte Dye, Sehr Sethi
 * @version May 3, 2015
 */
public class KitPage extends JPanel {

	// The name of the page
	private String pageName;

	// The adventure name of the user
	private String userAdventureName;

	// The name/location of the sticker image this page shows
	private static final String IMAGE_FILE = "images/logo.png";

	// The font for the kit name
	private static final Font KIT_NAME_FONT = new Font("Times New Roman",
			Font.BOLD, 60);

	// The font for the line with the user's name
	private static final Font CHILD_FONT = new Font("Times New Roman",
			Font.PLAIN, 40);

	// The text color
	private static final Color TEXT_COLOR = Color.BLACK;

	// The background color
	private static final Color BACKGROUND_COLOR = Color.WHITE;

	// The coordinates of the animating sticker are updated every DELAY
	// milliseconds
	private static final int DELAY = 10;

	// If the sticker never reaches its final destination, then stop the
	// animation after a certain period of time has passed
	private static final long ANIMATION_MAX_RUNTIME = 7000;

	// The animation will continue for at least this long
	private static final long ANIMATION_MIN_RUNTIME = 3000;

	// Whether the sticker should be displayed or not
	private boolean showSticker;

	// True if the use has just earned the sticker. Otherwise, false. If
	// earnSticker is true, show an animation of the sticker being put on the
	// page
	private boolean earnSticker = false;

	// the current coordinates of the sticker
	private double currentX;
	private double currentY;

	// The timer used for the animation
	private Timer timer;

	// The sticker image icon
	private ImageIcon imageIcon;

	// The final coordinates where the stationary sticker is positioned
	private double finalX;
	private double finalY;

	// The amount by which the coordinates of the sticker change
	private double animationConstantX = -3;
	private double animationConstantY = 5;

	// allows a little flexibility so that when the image is near the center of
	// the screen (and not necessarily at the very center) the animation stops
	private Rectangle2D rectangle;

	// The time when the animation starts
	private long startTime;

	// Whether or not this kit page is the last page of the passport. Need to
	// know this information to add the appropriate forward and back buttons.
	private boolean isLastPage = true;

	// Forward button
	private JButton fwdButton;

	// The passport this KitPage belongs to
	private Passport passport;

	// Button to play reward game
	private JButton rewardButton = new JButton("Play Reward Game");

	// The image of the sticker. It is the same as the logo image of the
	// Chemistry Passport Program
	private Image stickerImage;

	/**
	 * Creates a new page for the kit
	 * 
	 * @param pageName
	 *            The name of the page
	 * 
	 * @param showSticker
	 *            Whether or not the sticker should be displayed
	 */
	public KitPage(String pageName, Passport passport, boolean showSticker) {

		// Note whether we should show the sticker
		this.showSticker = showSticker;

		// Set the page name
		this.pageName = pageName.toUpperCase();

		// set the passport object
		this.passport = passport;

		// Save the name of the user
		this.userAdventureName = passport.getUserAdventureName();

		// Set the layout to BoxLayout
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// Set the size
		this.setSize(new Dimension(ChemistryPassportApplet.PAGE_WIDTH,
				ChemistryPassportApplet.PAGE_HEIGHT));

		// Set the background color
		this.setBackground(BACKGROUND_COLOR);

		// Create the ImageIcon from the file to create the sticker
		// Do the following so that the images are included in the path of this
		// application and hence can run a jar file on any computer
		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream(IMAGE_FILE);

			stickerImage = ImageIO.read(input);

			imageIcon = new ImageIcon(stickerImage);

		} catch (IOException e) {

			e.printStackTrace();
		}

		// the sticker should be in the middle of the page
		finalX = this.getWidth() / 2 - imageIcon.getIconWidth() / 2;

		finalY = this.getHeight() / 2 - imageIcon.getIconHeight() / 2 - 50;

		currentX = finalX;

		// create the timer that updates the coordinates of the animating
		// sticker
		createTimer();

		rectangle = new Rectangle2D.Double(finalX + 35, finalY + 10, 40, 40);

		// Add everything to the page
		addContent();

		this.setFocusable(true);

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		// background
		g.setColor(BACKGROUND_COLOR);

		g.fillRect(0, 0, getWidth(), getHeight());

		// sticker
		paintSticker(g);

	}

	/**
	 * Draws either a placeholder for the sticker or the sticker itself
	 * 
	 * @param g
	 *            The graphics object to draw on
	 */
	private void paintSticker(Graphics g) {

		// If the user has completed this kit, show the sticker and allow them
		// to play the reward game. Otherwise, draw a placeholder for the
		// sticker.
		if (showSticker) {

			// this condition is for when the user had already completed the kit
			// during a previous session

			imageIcon.paintIcon(this, g, (int) finalX, (int) finalY);

			enableRewardButton();

		} else if (!showSticker) {

			if (!earnSticker) {

				((Graphics2D) g).setRenderingHint(
						RenderingHints.KEY_ANTIALIASING,
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

				// this condition of when the user has just finished the kit

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
		addUserAdventureName();

		this.add(Box.createRigidArea(new Dimension(1, 50)));

		addRewardButton();

		this.add(Box.createRigidArea(new Dimension(10, 50)));

		addFwdAndBackButtons();

	}

	/**
	 * Adds the adventure name of the user to the page
	 */
	private void addUserAdventureName() {
		// User's name label
		JLabel userName = new JLabel(userAdventureName);

		// Set the font of the User's name
		userName.setFont(CHILD_FONT);

		// Set the font color
		userName.setForeground(TEXT_COLOR);

		// Center the User's name
		userName.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Add to the page
		add(userName);
	}

	/**
	 * Starts the animation of the sticker when the child has just completed the
	 * kit
	 */
	public void startStickerAnimation() {

		earnSticker = true;

		// start animation
		startTime = System.currentTimeMillis();

		timer.start();
	}

	/**
	 * Set whether the sticker should be shown
	 * 
	 * @param showSticker
	 *            True if the sticker should be shown, or false otherwise
	 */
	public void setShowSticker(boolean showSticker) {
		this.showSticker = showSticker;
	}

	/**
	 * Adds the name of the kit ot the page
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

			animationConstantY = animationConstantY * -1;
		}

		// bounce off right
		if (currentX + imageIcon.getIconWidth() >= maxWidth) {

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
	 * parameter. If it is not the last page, then the forward button will be
	 * enabled. If this is the last page, then the forward button will be
	 * diaabled.
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

		// back button
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

		// the forward button does not work for the last kit page
		if (isLastPage) {

			forwardButton.setEnabled(false);
		}

		this.fwdButton = forwardButton;

		buttonPanel.add(backButton);
		buttonPanel.add(forwardButton);

		add(buttonPanel);
	}

	/**
	 * Creates the timer that updates the coordinates of the animating sticker
	 */
	private void createTimer() {
		timer = new Timer(DELAY, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// update the coordinates of the sticker
				animate(KitPage.this.getWidth(), KitPage.this.getHeight() - 110);

				// If the sticker has roughly reached the middle of the screen
				// or if the animation has happened for a certain period of
				// time, then stop the animation
				long timeElapsed = System.currentTimeMillis() - startTime;

				if (rectangle.intersects(currentX, currentY,
						imageIcon.getIconWidth() / 5,
						imageIcon.getIconHeight() / 5)
						&& (timeElapsed > ANIMATION_MIN_RUNTIME)) {

					timer.stop();

				} else if (timeElapsed > ANIMATION_MAX_RUNTIME) {

					currentX = finalX;

					currentY = finalY;

					timer.stop();

				}

				repaint();

			}
		});
	}

	/**
	 * Add the button that allows the user to lay their reward game once they
	 * have completed this kit
	 */
	private void addRewardButton() {

		rewardButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		rewardButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// The reward game to add
				JComponent reward = passport.getCurrentKit().createRewardGame();

				// The name of the reward game to add (necessary for CardLayout)
				String rewardName = passport.getCurrentKit().getRewardName();

				// Add to CardLayout
				// passport.getChemGUI().add(beetle, "Beetle Reward Game");
				passport.getChemGUI().add(reward, rewardName);

				// Load in CardLayout
				// passport.getChemGUI().goToCard("Beetle Reward Game");
				passport.getChemGUI().goToCard(rewardName);

				repaint();

			}
		});

		rewardButton.setEnabled(false);

		add(rewardButton);

	}

	/**
	 * Enables the button that allows user to play the rward game upon
	 * completion of the relevant kit
	 */
	public void enableRewardButton() {

		rewardButton.setEnabled(true);
	}

	/**
	 * Returns the name of this page
	 * 
	 * @return The name of this page
	 */
	public String getPageName() {

		return pageName;
	}

}
