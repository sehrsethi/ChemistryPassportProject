package main;


import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JFrame;

import passport.Passport;

/**
 * 
 * @author Humaira Orchee, Charlotte Dye, Sehr Sethi
 * @version May 2, 2015
 */
public class ChemistryPassportApplet extends JApplet {
	
	
	public static final int PAGE_WIDTH = 600;
	
	public static final int PAGE_HEIGHT = 690;

	
		public void init(){
		
		ChemGetPropertyValues propValues = new ChemGetPropertyValues() ;
		
		ChemistryPassportGUI gui = new ChemistryPassportGUI(propValues);
		
		add(gui);
		
		setSize(PAGE_WIDTH, PAGE_HEIGHT);
		
		setPreferredSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		
		setMaximumSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		
		setMinimumSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));

				
		setVisible(true);
		 
		

	}

}
