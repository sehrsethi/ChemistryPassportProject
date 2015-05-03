package main;

import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class ChemistryPassport extends JApplet {

	public static final int PAGE_WIDTH = 600;

	public static final int PAGE_HEIGHT = 690;

	public void init() {

		ChemGetPropertyValues propValues = new ChemGetPropertyValues();

		ChemistryPassportGUI gui = new ChemistryPassportGUI(propValues);

		add(gui);

		setSize(PAGE_WIDTH, PAGE_HEIGHT);

		setPreferredSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));

		setMaximumSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));

		setMinimumSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));

		setVisible(true);

	}

	public static void main(String args[]) {

		JFrame frame = new JFrame();

		ChemGetPropertyValues propValues = new ChemGetPropertyValues();

		ChemistryPassportGUI gui = new ChemistryPassportGUI(propValues);

		frame.getContentPane().add(gui);

		frame.setSize(ChemistryPassportApplet.PAGE_WIDTH,
				ChemistryPassportApplet.PAGE_HEIGHT + 30);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);

		frame.setResizable(false);

	}

}
