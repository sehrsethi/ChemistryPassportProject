package passport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.ChemistryPassport;

/**
 * This is the first page of the user's Passport that contains the name of the
 * program, welcome text for the user, the grades for which this program is made
 * for, the user's name, the logo and the sponsors.
 * 
 * @author Humaira Orchee, Charlotte Dye, Sehr Sethi
 * @version May 3, 2015
 */
public class IntroductionPage extends JPanel {

	// The name of the package and file of the logo
	private static final String IMAGE_FILE = "images/logo.png";

	// The font for the header (the part that says PASSPORT)
	private static final Font HEADER_FONT = new Font("Times New Roman",
			Font.BOLD, 72);

	// The text for the header
	private static final String HEADER_TEXT = "PASSPORT";

	// The font for the welcome text
	private static final Font CHEM_FONT = new Font("Times New Roman",
			Font.ITALIC | Font.BOLD, 36);

	// The text of the welcome text
	private static final String CHEM_TEXT = "to Chemistry Adventure";

	// The font for the line with grades
	private static final Font GRADE_FONT = new Font("Times New Roman",
			Font.PLAIN, 24);

	// The text for the grades
	private static final String GRADE_TEXT = "Grades K-6";

	// The font for the name of the sponsor
	private static final Font SPONSOR_FONT = new Font("Times New Roman",
			Font.PLAIN, 18);

	// The sponsor info--the space is necessary to center the text
	private static final String SPONSOR_TEXT = "Funded by the Camille and Henry Dreyfus Foundation                         and Mount Holyoke College";

	// The font for the line with the child's name
	private static final Font CHILD_FONT = new Font("Times New Roman",
			Font.PLAIN, 40);

	// The text color
	private static final Color TEXT_COLOR = Color.BLACK;

	// The passport object that this first page belongs to
	private static Passport passport;

	// The name of the user whose passport this is
	private String userAdventureName;

	// The image of the logo
	private Image logo;

	/**
	 * Constructs the introduction (i.e. first) page of the passport
	 * 
	 * @param passport
	 *            The passport object that this first page belongs to
	 */

	public IntroductionPage(Passport passport) {

		// store the passport object
		IntroductionPage.passport = passport;

		// Store the name of the user
		this.userAdventureName = passport.getUserAdventureName();

		// Set the layout to FlowLayout
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 15));

		// Set the size
		this.setPreferredSize(new Dimension(ChemistryPassport.PAGE_WIDTH,
				ChemistryPassport.PAGE_HEIGHT));
		this.setMinimumSize(new Dimension(ChemistryPassport.PAGE_WIDTH,
				ChemistryPassport.PAGE_HEIGHT));

		// Make the background white
		this.setBackground(Color.WHITE);

		// Do the following so that the images are included in the path of this
		// application and hence can run a jar file on any computer
		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream(IMAGE_FILE);

			logo = ImageIO.read(input);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Add everything to the page
		addContent();
	}

	/**
	 * Adds the content to the introduction page
	 */
	private void addContent() {

		// text - PASSPORT
		addHeader();

		// image - logo
		addLogo();

		// "to Chem Passprt"
		addChemText();

		// grade of the child
		addGradeText();

		// text - sponsor
		addSponsorText();

		// name of user
		addUserAdventureName();

		// back and fwd buttons
		addForwardButton();

	}

	/**
	 * Adds the adventure name of the user to this page
	 */
	private void addUserAdventureName() {

		// Create the label for the child's name
		JLabel childLabel = new JLabel(userAdventureName);

		// Set the font for the child's name
		childLabel.setFont(CHILD_FONT);

		// Set the text color
		childLabel.setForeground(TEXT_COLOR);

		// Center the child's name
		childLabel.setHorizontalAlignment(JLabel.CENTER);

		// Add the child's name label to the passport
		add(childLabel);
	}

	/**
	 * Adds the sponsors to this page
	 */
	private void addSponsorText() {
		JTextArea sponsor = new JTextArea(SPONSOR_TEXT, 3, 30);

		sponsor.setFont(SPONSOR_FONT);

		sponsor.setForeground(TEXT_COLOR);

		sponsor.setWrapStyleWord(true);

		sponsor.setLineWrap(true);

		sponsor.setEditable(false);

		sponsor.setAlignmentX(Component.CENTER_ALIGNMENT);

		add(sponsor);

	}

	/**
	 * Adds the text "to Chemistry Adventure" to this page
	 */
	private void addChemText() {

		// text - to Chem adventure
		JLabel chemText = new JLabel(CHEM_TEXT);

		chemText.setFont(CHEM_FONT);

		chemText.setForeground(TEXT_COLOR);

		chemText.setHorizontalAlignment(JLabel.CENTER);

		add(chemText);

	}

	/**
	 * Adds the text "Grades K-6" to this page
	 */
	private void addGradeText() {
		// text - grade
		JLabel grade = new JLabel(GRADE_TEXT);

		grade.setFont(GRADE_FONT);

		grade.setForeground(TEXT_COLOR);

		grade.setHorizontalAlignment(JLabel.CENTER);

		add(grade);
	}

	/**
	 * Adds the image of the logo to this page
	 */
	private void addLogo() {

		JLabel image = new JLabel(new ImageIcon(logo));

		add(image);
	}

	/**
	 * Adds the text "PASSPORT" to this page
	 */
	private void addHeader() {
		JLabel header = new JLabel(HEADER_TEXT);

		header.setFont(HEADER_FONT);

		header.setForeground(TEXT_COLOR);

		header.setHorizontalAlignment(JLabel.CENTER);

		add(header);
	}

	/**
	 * Creates the forward button to this page. This button lets the user go to
	 * the KitSelectionPage
	 */
	private void addForwardButton() {

		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setBackground(Color.white);

		// So that the button is on the right. Otherwise the button will be
		// added to the center
		buttonPanel.setPreferredSize(new Dimension(
				ChemistryPassport.PAGE_WIDTH - 50, 50));
		buttonPanel.setMinimumSize(new Dimension(
				ChemistryPassport.PAGE_WIDTH - 50, 50));

		Font font = new Font("Verdana", Font.PLAIN, 18);

		// forward button
		JButton forwardButton = new JButton("---->");
		forwardButton.setFont(font);

		forwardButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				passport.nextPage();

			}
		});

		buttonPanel.add(forwardButton, BorderLayout.EAST);

		add(buttonPanel);
	}

}
