package main;

import javax.swing.JPanel;

/**
 * Represents a kit in the Chemistry Passport Program
 * @author Humaira Orchee, Charlotte Dye, Sehr Sethi
 *
 */
public abstract class Kit extends JPanel{
	
	protected ChemistryPassportGUI mainGUI ;
	
	public Kit(ChemistryPassportGUI mainGUI) {
	
		this.mainGUI = mainGUI ;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public ChemistryPassportGUI getMainGUI(){
		
		return mainGUI ;
	}

	
	/**
	 * Starts the kit
	 */
	public abstract void startKit();
	
	public abstract String getButtonName() ;
}
