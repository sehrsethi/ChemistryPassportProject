package main;

import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 * The main class that starts the JApplet or the JFrame for the Chemistry
 * Passport Program
 * 
 * @author Humaira Orchee, Sehr Sethi, and Charlotte Dye
 * @version April 30, 2015
 */
public class ChemistryPassport extends JApplet {

	// The width of the JApplet/JFrame
	public static final int PAGE_WIDTH = 600;

	// The height of the JApplet/JFrame
	public static final int PAGE_HEIGHT = 690;

	/**
	 * Starts the JApplet for the Chemistry Passport Program
	 */
	public void init() {

		// get the property values
		ChemGetPropertyValues propValues = new ChemGetPropertyValues();

		// construct a GUI, given the property values
		ChemistryPassportGUI gui = new ChemistryPassportGUI(propValues);

		// add the gui to the applet
		add(gui);

		// set the dimensions
		setSize(PAGE_WIDTH, PAGE_HEIGHT);

		setPreferredSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));

		setMaximumSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));

		setMinimumSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));

		// view the applet
		setVisible(true);

	}

	/**
	 * Constructs the main JFrame of the Chemistry Passport Application
	 * 
	 * @param args
	 *            The arguments to pass. Do not need to pass any for this
	 *            application.
	 */
	public static void main(String args[]) {

		// constructs a new JFrame for the application
		JFrame frame = new JFrame();

		// get the property values
		ChemGetPropertyValues propValues = new ChemGetPropertyValues();

		// construct a GUI. given these property values
		ChemistryPassportGUI gui = new ChemistryPassportGUI(propValues);

		// add the GUI to the JFrame
		frame.getContentPane().add(gui);

		frame.setSize(ChemistryPassport.PAGE_WIDTH,
				ChemistryPassport.PAGE_HEIGHT + 30);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// make the JFrame visible
		frame.setVisible(true);

		// Does not allow the user to resize the JFrame
		frame.setResizable(false);

	}

}
