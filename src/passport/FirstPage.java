package passport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import user.User;

public class FirstPage extends JPanel {

	// The width of this page
	private static final int PAGE_WIDTH = 500;

	// The height of this page
	private static final int PAGE_HEIGHT = 700;

	// The name/location of the image this page shows
	// private static final String IMAGE_FILE = "images//logo.png";

	// The name/location of the image this page shows
	private static final String IMAGE_FILE = "C://Users//Humaira//Documents//Course Works//Spring 2015 - 8//CS 316 - Software Practicum//ChemistryPassportWorkspace//ChemistryPassport//bin//images//logo.png";

	// The font for the header (the part that says PASSPORT)
	private static final Font HEADER_FONT = new Font("Times New Roman",
			Font.BOLD, 72);

	// The text for the header
	private static final String HEADER_TEXT = "PASSPORT";

	// The font for the second line
	private static final Font CHEM_FONT = new Font("Times New Roman",
			Font.ITALIC | Font.BOLD, 36);

	// The text of the second line
	private static final String CHEM_TEXT = "to Chemistry Adventure";

	// The font for the line with grades
	private static final Font GRADE_FONT = new Font("Times New Roman",
			Font.PLAIN, 24);

	// The text for the grade range
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

	private static Passport passport;

	// The name of the child whose passport this is

	private String childName;

	/**
	 * Constructs the first page
	 * 
	 * @param passport
	 *            TODO
	 */

	public FirstPage(Passport passport) {

		this.passport = passport;

		// Store the name of the child
		this.childName = passport.getChildName();

		// Set the layout to FlowLayout
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 15));

		// Set the size
		// this.setSize(new Dimension(Passport.PAGE_WIDTH,
		// Passport.PAGE_HEIGHT));
		this.setPreferredSize(new Dimension(Passport.PAGE_WIDTH,
				Passport.PAGE_HEIGHT));
		this.setMinimumSize(new Dimension(Passport.PAGE_WIDTH,
				Passport.PAGE_HEIGHT));

		// Make the background white

		this.setBackground(Color.WHITE);

		// Add everything to the page
		addContent();
	}

	// public void paintComponent(Graphics g){
	//
	// Graphics2D g2d = (Graphics2D) g ;
	//
	// g2d.setColor(Color.WHITE);
	//
	// g2d.fillRect(0, 0, PAGE_WIDTH, PAGE_HEIGHT);
	//
	// g2d.setColor(TEXT_COLOR);
	//
	// g2d.setFont(HEADER_FONT);
	//
	// g2d.drawString(HEADER_TEXT, 100, 100);
	//
	// ImageIcon imageIcon = new ImageIcon(IMAGE_FILE) ;
	//
	// imageIcon.paintIcon(this, g2d, 100, 150);
	//
	// g2d.setFont(LINE2_FONT);
	//
	// g2d.drawString(LINE2_TEXT, 100, 400);
	//
	// g2d.setFont(GRADE_FONT);
	//
	// g2d.drawString(GRADE_TEXT, 100, 450);
	//
	// g2d.setFont(SPONSOR_FONT);
	//
	// g2d.drawString(SPONSOR_TEXT, 100, 500);
	//
	//
	//
	//
	// }

	/**
	 * Adds the content to the first page
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

		// name of child/user
		addChildName();

		// back and fwd buttons
		addFwdButton();

	}

	/**
	 * 
	 */
	private void addChildName() {

		System.out.println("add child name " + childName);

		// child name can have max 20 characters or the entire name will not be
		// displayed

		// Create the label for the child's name
		JLabel childLabel = new JLabel(childName);

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
	 * 
	 */
	private void addSponsorText() {
		JTextArea sponsor = new JTextArea(SPONSOR_TEXT, 3, 30);

		sponsor.setFont(SPONSOR_FONT);

		sponsor.setForeground(TEXT_COLOR);

		sponsor.setWrapStyleWord(true);

		sponsor.setLineWrap(true);

		sponsor.setEditable(false);

		add(sponsor);
		// <<<<<<< HEAD
		//
		// // Add the child's name to the bottom of the passport page
		//
		// // Create the label for the child's name
		// JLabel childLabel = new JLabel(childName);
		//
		// // Set the font for the child's name
		// childLabel.setFont(CHILD_FONT);
		//
		// // Set the text color
		// childLabel.setForeground(TEXT_COLOR);
		//
		// // Center the child's name
		// childLabel.setHorizontalAlignment(JLabel.CENTER);
		//
		// // Add the child's name label to the passport
		// add(childLabel);
		//
		// =======
		// >>>>>>> refs/heads/humaira_animateSticker
	}

	/**
	 * 
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
	 * 
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
	 * 
	 */
	private void addLogo() {
		ImageIcon imageIcon = new ImageIcon(IMAGE_FILE);

		JLabel image = new JLabel(imageIcon);

		add(image);
	}

	/**
	 * 
	 */
	private void addHeader() {
		JLabel header = new JLabel(HEADER_TEXT);

		header.setFont(HEADER_FONT);

		header.setForeground(TEXT_COLOR);

		header.setHorizontalAlignment(JLabel.CENTER);

		add(header);
	}

	/**
	 * Creates the forward button
	 */
	private void addFwdButton() {

		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setBackground(Color.white);

		// So that the button is on the right. Otherwise the button is added to
		// the center
		buttonPanel
				.setPreferredSize(new Dimension(Passport.PAGE_WIDTH - 50, 50));
		buttonPanel.setMinimumSize(new Dimension(Passport.PAGE_WIDTH - 50, 50));

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

	/**
	 * For testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame();

		ArrayList<Integer> kitProgress = new ArrayList<Integer>();
		kitProgress.add(5);

		User user = new User("user name", "long Fake Name Fake name", "K",
				kitProgress);

		frame.getContentPane().add(new FirstPage(new Passport(user)));

		frame.setSize(Passport.PAGE_WIDTH, Passport.PAGE_HEIGHT);

		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
