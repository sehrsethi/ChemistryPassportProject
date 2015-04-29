package main;


import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JFrame;

import passport.Passport;

public class ChemistryPassport extends JApplet {
	
	
	public static final int PAGE_WIDTH = 600;
	
	public static final int PAGE_HEIGHT = 690;
	
	//public static void main(String args[]) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
	
		public void init(){
		
		ChemGetPropertyValues propValues = new ChemGetPropertyValues() ;
		
		ChemistryPassportGUI gui = new ChemistryPassportGUI(propValues);
		
		 //Create the frame
		 //JFrame frame = new JFrame();

		 //frame.getContentPane().add(gui);
		
		 // Set the size to the specified page size
		// frame.setSize(Passport.PAGE_WIDTH, Passport.PAGE_HEIGHT);
		
		 // Make visible
		// frame.setVisible(true);
		
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	 

		//ChemGetPropertyValues c = (ChemGetPropertyValues) Class.forName("ChemGetPropertyValues").newInstance() ;
		add(gui);
		
		setSize(PAGE_WIDTH, PAGE_HEIGHT);
		
		setPreferredSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		
		setMaximumSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		
		setMinimumSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));

				
		setVisible(true);
		 
		

	}

}
