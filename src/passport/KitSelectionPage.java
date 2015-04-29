package passport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import kit_interfaces.Kit;
import main.ChemGetPropertyValues;
import main.ChemistryPassportGUI;
import beetle_kit.BeetleKit;
import user.User;

/**
 * This passport page allows the user to select the kit they want to work on
 * 
 * @author Humaira Orchee
 * @version April 4, 2015
 *
 */
public class KitSelectionPage extends JPanel {

	// TODO There is a weird grey border around the kitButtonPanel. Look into
	// that

	private ChemistryPassportGUI mainGUI;

	// The font for the name of the kit on each button
	private static final Font BUTTON_FONT = new Font("Times New Roman",
			Font.BOLD, 24);

	// The passport that this page is associated with
	private Passport passport;

	// The panel that contains the buttons corresponding to the kits in this
	// project
	private JPanel kitButtonPanel;

	// The list of buttons corresponding to the kits in this project
	private ArrayList<JButton> kitButtonsList;

	// name of all the kits in the Chemistry Passport Project
	// private String[] kitNames;

	// Names of the classes of all the kits in the Chemistry Passport Project
	private String[] kitClassNames;

	// Names of the buttons for all of the kits in the Chemistry Passport
	// Project
	private String[] kitButtonNames;

	/**
	 * Creates a passport page allows the user to select the kit they want to
	 * work on
	 * 
	 * @param passort
	 *            The passport that this page is associated with
	 */
	public KitSelectionPage(Passport passort) {

		this.passport = passort;

		this.setBackground(Color.WHITE);

		this.setLayout(new BorderLayout());

		this.setPreferredSize(new Dimension(Passport.PAGE_WIDTH,
				Passport.PAGE_HEIGHT));
		this.setMinimumSize(new Dimension(Passport.PAGE_WIDTH,
				Passport.PAGE_HEIGHT));

		mainGUI = passort.getChemGUI();

		kitButtonsList = new ArrayList<JButton>();

		// kitNames = passort.getPropVals().getKitNames();

		// Get the names of the kit classes
		kitClassNames = passort.getPropVals().getKitNames();

		// Get the titles of the kits (the text displayed on the buttons)
		kitButtonNames = passort.getPropVals().getKitButtonNames();

		// adds the text "Choose Your Kit!"
		addHeader();

		// Initializes the kitButtonPanel
		initKitButtonPanel();

		// adds the fwd and back buttons
		addFwdAndBackButtons();

		// addExistingKitButtons();

	}

	/**
	 * Adds buttons for pages that already exist in the passport.
	 * 
	 * TODO Will most probably not need this method since the Passport class
	 * should take care of this
	 */
	private void addExistingKitButtons() {

		ArrayList<String> kitNames = passport.getPageNames();

		// start at index 2 because index 0 contains name of first page and
		// index 1 contains the name of the kit selection page
		// for (int i = 2; i < kitNames.size(); i++) {
		for (int i = 0; i < kitButtonNames.length; i++) {

			// addKitButton(kitNames.get(i));

			addKitButton(kitButtonNames[i]);
		}
	}

	/**
	 * Creates the panel where the button corresponding to kits are and enables
	 * scrolling
	 */
	private void initKitButtonPanel() {

		// the panel to add the kitButtonPanel and a scrollPane
		JPanel panel = new JPanel(new BorderLayout());
		add(panel, BorderLayout.CENTER);

		// initialize the kitButtonPanel
		kitButtonPanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(kitButtonPanel, BoxLayout.Y_AXIS);
		kitButtonPanel.setLayout(boxLayout);
		kitButtonPanel.setBackground(Color.WHITE);
		kitButtonPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		// add the scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(kitButtonPanel);

		// add the panel
		panel.add(scrollPane, BorderLayout.CENTER);

	}

	/**
	 * Adds the header "Chose Your Kit!"
	 */
	private void addHeader() {

		JLabel label = new JLabel("Choose Your Kit!");

		label.setHorizontalAlignment(JLabel.CENTER);

		Font font = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 50);

		label.setFont(font);

		add(label, BorderLayout.NORTH);

		// TODO : for testing. remove later
		label.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

				KitSelectionPage.this.addKitButton("Temp Kit");
				KitSelectionPage.this.repaint();

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * Adds a button corresponding to the kit just added
	 * 
	 * @param kitName
	 *            The name of the kit added to the passport
	 */
	public void addKitButton(String kitButtonName) {

		// create vertical spacing between buttons
		kitButtonPanel.add(Box.createRigidArea(new Dimension(3, 10)));

		// the kitName also includes the package name and we do not want the
		// name of the package on the button. So we get rid of the first part of
		// the text (before the ".") to get just the kit name to put on the
		// button
		// String buttonName = kitName.substring(kitName.indexOf(".") + 1);

		// create and format button
		// final JButton kitButton = new JButton(buttonName);
		final JButton kitButton = new JButton(kitButtonName);

		kitButton.setFont(BUTTON_FONT);

		// center button in the BoxLayout
		kitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		kitButtonPanel.add(kitButton);

		kitButtonsList.add(kitButton);

		// add listener to button
		kitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// go through the kit names available in the program currently
				// and match the name of the kit with the name of the button to
				// start the appropriate kit

				for (int i = 0; i < kitButtonNames.length; i++) {
					// if (kitNames[i].contains(kitButton.getText())) {

					if (kitButtonNames[i].contains(kitButton.getText())) {
						
						activateKit(kitClassNames[i], i);
					}
				}

			}
		});

	}

	/**
	 * Called by the action listener of the kit buttons to start the kit
	 * @param kitName the name of the kit to activate
	 * @param kitNumber the kit number 
	 */
	private void activateKit(String kitName, int kitNumber) {

		System.out.println("Kit Selection Page : activateKit");
		
		try {
			
			System.out.println("kitNumber " + kitNumber);
			
			//The progress of the user for the kit to be activated
			Integer kitProgress = passport.getUser().getKitProgress().get(kitNumber);

			System.out.println("kitProgress " + kitProgress);
			
			// Get an instance of the kit corresponding to kitName
			// Kit kit = (Kit) Class.forName(kitName).newInstance();

//			Kit kit = (Kit) Class.forName(kitName)
//					.getDeclaredConstructor(ChemistryPassportGUI.class, Integer.class)
//					.newInstance(mainGUI, kitNumber);
			
			Kit kit = (Kit) Class.forName(kitName)
					.getDeclaredConstructor(ChemistryPassportGUI.class, Integer.class)
					.newInstance(mainGUI, kitProgress);

			
			//Inform the passport that this is the current kit
			passport.setCurrentKit(kit);
			
			// Start the kit!
			kit.startKit();
			
			

			// Add to CardLayout
			mainGUI.add(kit, kitName);

			// Load in CardLayout
			mainGUI.goToCard(kitName);

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
		}catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Removes the button corresponding to the kit that is to be removed
	 * 
	 * @param kitName
	 *            The name of the kit to be removed
	 */
	public void removeKitButton(String kitName) {

		for (int i = 0; i < kitButtonsList.size(); i++) {

			JButton button = kitButtonsList.get(i);
			if (kitName.equals(button.getText())) {

				remove(button);
				revalidate();
			}
		}
	}

	/**
	 * Creates the back and forward buttons
	 */
	private void addFwdAndBackButtons() {

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 160,
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

		// now add the buttons to the screen
		buttonPanel.add(backButton);
		buttonPanel.add(forwardButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Main method for testing TODO : remove later
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Create the frame
		JFrame frame = new JFrame();

		ArrayList<Integer> kitProgress = new ArrayList<Integer>();
		kitProgress.add(5);

		User user = new User("long Fake Name Fake", "K", kitProgress);

		// Add the passport to the frame--will need to figure out
		// how to do the name getting part
		// frame.getContentPane().add(new KitSelectionPage(new Passport(user,
		// this)));

		// Set the size to the specified page size
		frame.setSize(Passport.PAGE_WIDTH, Passport.PAGE_HEIGHT);

		// Make visible
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
