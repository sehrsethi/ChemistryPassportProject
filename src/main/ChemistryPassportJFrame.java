package main;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class ChemistryPassportJFrame{// extends JApplet {
	

	/**
	 * 
	 * @param args
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static void main(String args[]) {

		
		JFrame frame = new JFrame();
		
		ChemGetPropertyValues propValues = new ChemGetPropertyValues() ;
		
		ChemistryPassportGUI gui = new ChemistryPassportGUI(propValues);
		
		
		frame.getContentPane().add(gui);
		
		frame.setSize(ChemistryPassportApplet.PAGE_WIDTH, ChemistryPassportApplet.PAGE_HEIGHT + 30);
		
		
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		 
		 frame.setVisible(true);
		 
		 frame.setResizable(false);
		
	}

	
}
