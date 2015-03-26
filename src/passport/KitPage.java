package passport;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * @author Humaira Orchee, Charlotte Dye
 * @version March 25, 2015
 */
public class KitPage extends JPanel implements ActionListener {

	// The width of the page for the kit
	private static final int PAGE_WIDTH = 500;

	// The height of the page for the kit
	private static final int PAGE_HEIGHT = 700;

	// The name of the page
	private String pageName;

	// The name of the child
	private String childName;

	// The label for empty sticker
	// private StickerPanel emptyStickerPanel = new StickerPanel();

	// The name/location of the image this page shows:
	// NOTE: THIS WILL CHANGE TO GET THE APPROPRIATE STICKER
	// We should either have the image file name be some sort of parameter
	// or we could have standardized file names (e.g., sticker_[KIT NAME].png)
	private static final String IMAGE_FILE = "src//images//logo.png";

	// The icon that displays when the user hasn't earned this sticker
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

	// The coordinates of the sticker are updated every DELAY milliseconds
	private static final int DELAY = 10;

	// If the sticker never reaches its final destination, then stop the
	// animation after a certain period of time has passed
	private static final long ANIMATION_TIME = 5300;

	// Whether the sticker should be displayed
	// ONLY SHOW THE STICKER AFTER THEY CLICK
	private boolean showSticker;

	// True if they have just earned the sticker:
	// If earnSticker is true, show an animation of the sticker being put on the
	// page
	private boolean earnSticker = false;

	// False if they have not previously completed this kit and earned a sticker
	// for it. True otherwise.
	private boolean showNoSticker = true;

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
	private double animationConstantX = -1;
	private double animationConstantY = 1.5;

	// allows a little flexibility so that when the image is near the center of
	// the screen (and not necessarily at the very center) the animation stops
	private Rectangle2D rectangle;

	// Keeps track of when the animation starts
	private long startTime;

	// Keep track of the time elapsed since the animation started
	private long currentTime;

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

		imageIcon = new ImageIcon(IMAGE_FILE);

		finalX = this.getWidth() / 2 - imageIcon.getIconWidth() / 2;

		finalY = this.getHeight() / 2 - imageIcon.getIconHeight() / 2;

		timer = new Timer(DELAY, this);

		currentX = finalX;

		// the dimensions will change depending on panel size and image size and
		// speed of animation
		rectangle = new Rectangle2D.Double(finalX - 10, finalY - 10, 5, 5);

		// Add everything to the page
		addContent();

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(BACKGROUND_COLOR);

		g.fillRect(0, 0, getWidth(), getHeight());

		paintSticker(g);

		// for debugging
		// g.setColor(Color.RED);
		//
		// g.fillRect((int) rectangle.getX(), (int) rectangle.getY(),
		// (int) rectangle.getWidth(), (int) rectangle.getHeight());
		//
		// g.setColor(Color.BLUE);
		//
		// g.drawRect((int) currentX, (int) currentY, imageIcon.getIconWidth(),
		// imageIcon.getIconHeight());

	}

	/**
	 * Draws either a placeholder for the sticker or the sticker itself
	 * 
	 * @param g
	 *            The graphics object to draw on
	 */
	private void paintSticker(Graphics g) {

		if (showNoSticker) {

			g.setColor(Color.LIGHT_GRAY);

			int circleDiameter = 150;

			g.fillOval(getWidth() / 2 - circleDiameter / 2, getHeight() / 2
					- circleDiameter / 2, circleDiameter, circleDiameter);

			g.setColor(Color.BLACK);

			g.setFont(new Font("Times New Roman", Font.PLAIN, 16));

			g.drawString("You haven't earned", getWidth() / 2 - circleDiameter
					/ 2 + 15, getHeight() / 2 - circleDiameter / 2 + 70);

			g.drawString("this sticker yet!", getWidth() / 2 - circleDiameter
					/ 2 + 25, getHeight() / 2 - circleDiameter / 2 + 90);

		} else if (earnSticker) {

			imageIcon.paintIcon(this, g, (int) currentX, (int) currentY);

		}

	}

	/**
	 * Add the content to the kit page
	 */
	private void addContent() {

		// Create and add rigid area for spacing
		this.add(Box.createRigidArea(new Dimension(20, 20)));

		// Add the kit name label
		// Kit name label
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

		// Create and add rigid area for spacing
		this.add(Box.createRigidArea(new Dimension(20, 100)));

		// IS THIS ACTUALLY ADDING THE STICKER?
		// Check if we should add the sticker
		if (showSticker) {
			// Show the sticker

			// Create the sticker icon
			// ImageIcon imageIcon = new ImageIcon(IMAGE_FILE);

			// Create a label for the sticker
			JLabel stickerLabel = new JLabel(imageIcon);

			// Center the label
			stickerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

			// Add the sticker
			add(stickerLabel);

		} else {
			// Show empty sticker icon
			// Create the sticker icon
			// ImageIcon imageIcon = new ImageIcon(EMPTY_IMAGE_FILE);

			// imageIcon.
			// Create a label for the sticker
			// emptyStickerLabel = new JLabel(imageIcon);

			// Center the label
			// emptyStickerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					// Add the sticker

					earnSticker = true;

					// something needs to happen here so that the database knows
					// that a sticker has been added

					showNoSticker = false;

					// start animation
					startTime = System.currentTimeMillis();

					timer.start();

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

			});

		}

		// Create and add rigid area for spacing
		this.add(Box.createRigidArea(new Dimension(50, 350)));

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

	@Override
	public void actionPerformed(ActionEvent e) {

		// update the coordinates of the sticker
		animate(this.getWidth(), this.getHeight());

		currentTime = System.currentTimeMillis();

		// If the sticker has roughly reached the middle of the screen or if the
		// animation has happened for a certain period of time, then stop the
		// animation
		
		// if(currentX == finalX && currentY == finalY){

		if (rectangle.contains(currentX, currentY)) {

			timer.stop();

		} else if (currentTime - startTime >= ANIMATION_TIME) {

			System.out.println(currentTime - startTime);

			timer.stop();
		}

		repaint();

	}

	/**
	 * Animates the sticker
	 * 
	 * @param maxWidth
	 *            The x coordinate beyond which the sticker cannot go
	 * @param maxHeight
	 *            The y coordinate beyond which the sticker cannot go
	 */
	public void animate(int maxWidth, int maxHeight) {

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

			timer.setDelay(DELAY - 5);

			animationConstantX = animationConstantX * -1;
			animationConstantY = -0.5;
		}

		// bounce off top
		if (currentY <= 0) {

			animationConstantY = 1.5;
		}

	}

	public static void main(String[] args) {

		// Create the frame
		JFrame frame = new JFrame();

		// Add the passport to the frame--will need to figure out
		// how to do the name getting part
		frame.getContentPane().add(
				new KitPage("Bark Beetle", "Pretend Child", false));

		// Set the size to the specified page size
		frame.setSize(PAGE_WIDTH, PAGE_HEIGHT);

		// Make visible
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
