package passport;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FirstPage extends JPanel {

	//The width of this page
	private static final int PAGE_WIDTH = 500;

	//The height of this page
	private static final int PAGE_HEIGHT = 700;

	//The name/location of the image this page shows
	private static final String IMAGE_FILE = "src//images//logo.png";

	//The font for the header (the part that says PASSPORT)
	private static final Font HEADER_FONT = new Font("Times New Roman",
			Font.BOLD, 72);

	//The text for the header
	private static final String HEADER_TEXT = "PASSPORT";

	//The font for the second line
	private static final Font LINE2_FONT = new Font("Times New Roman",
			Font.ITALIC | Font.BOLD , 36);

	//The text of the second line
	private static final String LINE2_TEXT = "to Chemistry Adventure";

	//The font for the line with grades
	private static final Font GRADE_FONT = new Font("Times New Roman",
			Font.PLAIN, 24);

	//The text for the grade range
	private static final String GRADE_TEXT = "Grades K-6";

	//The font for the name of the sponsor
	private static final Font SPONSOR_FONT = new Font("Times New Roman",
			Font.PLAIN, 18); 

	// The sponsor info--the space is necessary to center the text
	private static final String SPONSOR_TEXT = "Funded by the Camille and Henry Dreyfus Foundation                         and Mount Holyoke College";

	//The font for the line with the child's name
	private static final Font CHILD_FONT = new Font("Times New Roman",
			Font.PLAIN, 60);
	
	//The text color
	private static final Color TEXT_COLOR = Color.BLACK;
	
	//The name of the child whose passport this is
	private String childName;
	
	/**
	 * Constructs the first page
	 * @param childName The name of the child this passport belongs to
	 */
	public FirstPage(String childName) {

		//Store the name of the child
		this.childName = childName;
		
		//Set the layout to FlowLayout
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 25));

		//Set the size
		this.setSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		
		//Make the background white
		this.setBackground(Color.WHITE);

		//Add everything to the page
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
		JLabel header = new JLabel(HEADER_TEXT);

		header.setFont(HEADER_FONT);

		header.setForeground(TEXT_COLOR);

		header.setHorizontalAlignment(JLabel.CENTER);

		add(header);

		// image - logo
		ImageIcon imageIcon = new ImageIcon(IMAGE_FILE);

		JLabel image = new JLabel(imageIcon);

		add(image);

		// text - to Chem adventure
		JLabel line2 = new JLabel(LINE2_TEXT);

		line2.setFont(LINE2_FONT);

		line2.setForeground(TEXT_COLOR);

		line2.setHorizontalAlignment(JLabel.CENTER);

		add(line2);

		// text - grade
		JLabel grade = new JLabel(GRADE_TEXT);

		grade.setFont(GRADE_FONT);

		grade.setForeground(TEXT_COLOR);

		grade.setHorizontalAlignment(JLabel.CENTER);

		add(grade);
		

		// text - sponsor
		JTextArea sponsor = new JTextArea(SPONSOR_TEXT, 3, 30);
		
		sponsor.setFont(SPONSOR_FONT);

		sponsor.setForeground(TEXT_COLOR);
		
		sponsor.setWrapStyleWord(true);
		
		sponsor.setLineWrap(true);	
		
		sponsor.setEditable(false);  

		add(sponsor);
		
		//Add the child's name to the bottom of the passport page
		
		//Create the label for the child's name
		JLabel childLabel = new JLabel(childName);
		
		//Set the font for the child's name
		childLabel.setFont(CHILD_FONT);
		
		//Set the text color
		childLabel.setForeground(TEXT_COLOR);
		
		//Center the child's name
		childLabel.setHorizontalAlignment(JLabel.CENTER);

		//Add the child's name label to the passport
		add(childLabel);

	}

	public static void main(String[] args) {

		//Are we going to have a main method here??
		
		
		JFrame frame = new JFrame();

		frame.getContentPane().add(new FirstPage("TempFakeName"));

		frame.setSize(FirstPage.PAGE_WIDTH, FirstPage.PAGE_HEIGHT);

		frame.setVisible(true);
	}
}
