package main;


import javax.swing.JFrame;

import passport.Passport;

public class ChemistryPassport {
	
	public static void main(String args[]) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		ChemGetPropertyValues propValues = new ChemGetPropertyValues() ;
		
		ChemistryPassportGUI gui = new ChemistryPassportGUI(propValues);
		
		 //Create the frame
		 JFrame frame = new JFrame();

		 frame.getContentPane().add(gui);
		
		 // Set the size to the specified page size
		 frame.setSize(Passport.PAGE_WIDTH, Passport.PAGE_HEIGHT);
		
		 // Make visible
		 frame.setVisible(true);
		
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	 

		//ChemGetPropertyValues c = (ChemGetPropertyValues) Class.forName("ChemGetPropertyValues").newInstance() ;

		 
		

	}

}
