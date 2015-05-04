package passport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import kit_interfaces.Kit;
import main.ChemistryPassport;
import main.ChemistryPassportGUI;

/**
 * This passport page allows the user to select the kit they want to work on
 * 
 * @author Humaira Orchee, Charlotte Dye, Sehr Sethi
 * @version May 3, 2015
 *
 */
public class KitSelectionPage extends JPanel {

	// The main GUI of the entire Chemistry Passport Program.
	private ChemistryPassportGUI mainGUI;

	// The font for the name of the kit on each button
	private static final Font BUTTON_FONT = new Font("Times New Roman",
			Font.BOLD, 24);

	// The passport object that this page is a part of
	private Passport passport;

	// The panel that contains the buttons corresponding to the kits in this
	// project
	private JPanel kitButtonPanel;

	// The list of buttons corresponding to the kits in this project
	private ArrayList<JButton> kitButtonsList;

	// Names of the classes of all the kits in the Chemistry Passport Project
	private String[] kitClassNames;

	// Names of the buttons for all of the kits in the Chemistry Passport
	// Program
	private String[] kitButtonNames;

	/**
	 * Creates a passport page that allows the user to select the kit they want
	 * to work on
	 * 
	 * @param passort
	 *            The passport object that this page is a part of
	 */
	public KitSelectionPage(Passport passort) {

		this.passport = passort;

		// background color
		this.setBackground(Color.WHITE);

		// layout
		this.setLayout(new BorderLayout());

		// dimension
		this.setPreferredSize(new Dimension(ChemistryPassport.PAGE_WIDTH,
				ChemistryPassport.PAGE_HEIGHT));
		this.setMinimumSize(new Dimension(ChemistryPassport.PAGE_WIDTH,
				ChemistryPassport.PAGE_HEIGHT));

		mainGUI = passort.getChemGUI();

		kitButtonsList = new ArrayList<JButton>();

		// Get the names of the kit classes
		kitClassNames = passort.getPropVals().getKitNames();

		// Get the titles of the kits (the text displayed on the buttons)
		kitButtonNames = passort.getPropVals().getKitButtonNames();

		// adds the text "Choose Your Kit!"
		addHeader();

		// Initializes the kitButtonPanel
		initKitButtonPanel();

		// adds the forward and back buttons
		addFwdAndBackButtons();

	}

	/**
	 * Creates the panel containing the button corresponding to kits
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

		// Add the scroll pane. It appears if needed
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(kitButtonPanel);

		// add the panel to this page
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

		// create and format button
		final JButton kitButton = new JButton(kitButtonName);
		kitButton.setFont(BUTTON_FONT);

		// center button in the BoxLayout
		kitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		// add the button to the kitButtonPanel
		kitButtonPanel.add(kitButton);

		// add the button to the list of buttons
		kitButtonsList.add(kitButton);

		// add an action listener to button
		kitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// go through the kit names available in the program currently
				// and match the name of the kit with the name of the button to
				// start the appropriate kit

				for (int i = 0; i < kitButtonNames.length; i++) {

					// find the correct kit and let the user play with the kit
					if (kitButtonNames[i].contains(kitButton.getText())) {

						activateKit(kitClassNames[i], i);
					}
				}

			}
		});

	}

	/**
	 * Creates the specifies kit and and lets the user play with the kit. Needs
	 * to be called by the ActionListener of the associated kit button
	 * 
	 * @param kitName
	 *            The name of the kit to activate. This is the package name and
	 *            the class name ( [name_of_package.name_of_kit] (e.g.
	 *            kit_2=apple_kit.AppleKit) ), not the name displayed on the
	 *            button or the name displayed in the passport page.
	 * @param kitNumber
	 *            The number of the kit. Each kit has an integer associated with
	 *            them, determined by the order they were added to the program.
	 *            This number can be found in the config.properties file.
	 */
	private void activateKit(String kitName, int kitNumber) {

		try {

			// The progress of the user for the kit to be activated.
			// passport.getUser().getKitProgress() returns an array list of
			// Integers, where the the integer at index i represents the
			// progress the user has made in kit i so far.
			Integer kitProgress = passport.getUser().getKitProgress()
					.get(kitNumber);

			// The 'Reflection' technique is used to create the appropriate kit.
			// This is because we did not want to load every existing kit if it
			// is not used and because we did not want the developers who want
			// to add new kits to this application have to edit/add to our
			// existing code.
			
			// The constructor used here is :
			//public Kit(ChemistryPassportGUI mainGUI, Integer kitProgress, Integer kitIndex)
			Kit kit = (Kit) Class
					.forName(kitName)
					.getDeclaredConstructor(ChemistryPassportGUI.class,
							Integer.class, Integer.class)
					.newInstance(mainGUI, kitProgress, (Integer) kitNumber);

			// Inform the passport that this is the current kit
			passport.setCurrentKit(kit);

			// Start the kit!
			kit.startKit();

			// Add to CardLayout
			mainGUI.add(kit, kitName);

			// Go to the card containing the kit just created
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
		} catch (InstantiationException e) {
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
	 * Creates the back and forward buttons to go back and forth between the
	 * passport pages
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

		// now add the buttons to the GUI
		buttonPanel.add(backButton);
		buttonPanel.add(forwardButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}

}
