package passport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

	// The label for empty sticker
	private StickerPanel emptyStickerPanel = new StickerPanel();

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

	// Whether the sticker should be displayed
	// ONLY SHOW THE STICKER AFTER THEY CLICK
	private boolean showSticker;

	// True if they have just earned the sticker:
	// If earnSticker is true, show an animation of the sticker being put on the
	// page
	private boolean earnSticker = false;

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

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(BACKGROUND_COLOR);

		g.fillRect(0, 0, getWidth(), getHeight());

		emptyStickerPanel.repaint();

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

		// Check if we should add the sticker
		if (showSticker) {
			// Show the sticker

			// Create the sticker icon
			ImageIcon imageIcon = new ImageIcon(IMAGE_FILE);

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

			emptyStickerPanel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					// Add the sticker

					earnSticker = true;

					emptyStickerPanel.showNoSticker = false;

					repaint();

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

			// Add the sticker
			add(emptyStickerPanel);
		}

		// Create and add rigid area for spacing
		this.add(Box.createRigidArea(new Dimension(50, 179)));

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

	private class StickerPanel extends JPanel implements Runnable{

		private boolean showNoSticker = true;

		private int currentX;

		private int currentY;
		
		private Thread animationThread ;

		public StickerPanel() {
			setLayout(new BorderLayout());
			setSize(new Dimension(200, 200));
			
			animationThread = new Thread(this) ;
			
			start();
		}

		public void paintComponent(Graphics g) {

			super.paintComponent(g);

			g.setColor(BACKGROUND_COLOR);

			g.fillRect(0, 0, getWidth(), getHeight());

			if (showNoSticker) {

				g.setColor(Color.LIGHT_GRAY);

				g.fillOval(175, getHeight() / 2 - 50, 150, 150);

				g.setColor(Color.BLACK);

				g.drawString("You haven't earned", 190, 140);

				g.drawString("this sticker yet!", 200, 160);
			} else if (earnSticker) {

				//
				// // Show the sticker
				//
				// // Create the sticker icon
				// ImageIcon imageIcon = new ImageIcon(IMAGE_FILE);
				//
				// // Create a label for the sticker
				// JLabel stickerLabel = new JLabel(imageIcon);
				//
				// // Center the label
				// stickerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				//
				// // Add the sticker
				// add(stickerLabel);
				//
				// Create the sticker icon

				// emptyStickerLabel.setVisible(false);

				// this.remove(emptyStickerLabel);
				// revalidate();
				
				
				
			 ImageIcon imageIcon = new ImageIcon(IMAGE_FILE);
			 

				final int finalX = StickerPanel.this.getWidth() / 2
						- imageIcon.getIconWidth() / 2;
				final int finalY = StickerPanel.this.getHeight() / 2
						- imageIcon.getIconHeight() / 2;
				

				if ((currentX == finalX) || (currentY == finalY)) {
					
					animationThread = null;
					
					//imageIcon.paintIcon(StickerPanel.this, g, currentX,	currentY);
					imageIcon.paintIcon(StickerPanel.this, g, finalX, finalY);
					
					return ;
				}
				//drawMovingImage(g, imageIcon);
				
				imageIcon.paintIcon(StickerPanel.this, g, currentX,	currentY);

				
			}

		}

		public void start(){
			if (animationThread != null){
				animationThread.start();
			}
		}

		@Override
		public void run() {
			
			while (Thread.currentThread() == animationThread){
				currentX++;
				currentY++;
				
				System.out.println("Current x is " + currentX);
				System.out.println("Current y is " + currentY);

				repaint() ;
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
		

		// /**
		// * @param g
		// * @param imageIcon
		// */
		// private void drawMovingImage(final Graphics g, final ImageIcon
		// imageIcon) {
		//
		//
		// Timer timer = new Timer(500, new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// System.out.println("Current x is "+currentX);
		// System.out.println("Current y is "+currentY);
		// currentX++;
		// currentY++;
		//
		// int finalX = StickerPanel.this.getWidth() / 2 -
		// imageIcon.getIconWidth()
		// / 2;
		// int finalY = StickerPanel.this.getHeight() / 2
		// - imageIcon.getIconHeight() / 2;
		//
		// System.out.println("Final x is "+finalX);
		// System.out.println("Final y is "+finalY);
		//
		// if ((currentX <= finalX)
		// && (currentY <= finalY)){
		// System.out.println("This is here");
		//
		//
		// //return ;
		//
		//
		// imageIcon.paintIcon(StickerPanel.this, g, currentX,
		// currentY);
		// }
		//
		// }
		// });
		//
		// timer.start();
		//
		// // getWidth() / 2 - imageIcon.getIconWidth() / 2,
		// // getHeight() / 2 - imageIcon.getIconHeight() / 2);
		// }

	}

}