package kit_interfaces;

import javax.swing.JComponent;
import javax.swing.JPanel;

import main.ChemistryPassportGUI;

/**
 * Represents a kit in the Chemistry Passport Program
 * @author Humaira Orchee, Charlotte Dye, Sehr Sethi
 *
 */
public abstract class Kit extends JPanel{
	
	protected ChemistryPassportGUI mainGUI ;
	
	//The user's progress on current kit
	protected int kitProgress;
	
	public Kit(ChemistryPassportGUI mainGUI, Integer kitProgress) {
	
		this.mainGUI = mainGUI ;
		
		this.kitProgress = kitProgress;
	}
	
	
	/**
	 * Gets the main ChemistryPassort GUI 
	 * @return the main GUI of the program
	 */
	public ChemistryPassportGUI getMainGUI(){
		
		return mainGUI ;
	}
	
	/**
	 * Gets the kit progress of the user for this kit
	 * @return The kit progress 
	 */	
	public int getKitProgress(){
		
		System.out.println("Kit : getKitProgress");
		
		System.out.println("kit progress " + kitProgress);
		
		return kitProgress;
	}
	
	/**
	 * 
	 * @param kitProgress
	 */
	public void setKitProgress(int kitProgress) {
		
		System.out.println("set kit progress to " + kitProgress);
		
		this.kitProgress = kitProgress;
	}


	/**
	 * Starts the kit
	 */
	public abstract void startKit();
	
	/**
	 * Returns the name of the button for this kit
	 * @return Button name
	 */
	public abstract String getButtonName() ;


	/**
	 * Starts the reward game and returns the reward component
	 * @return Reward component to display
	 */
	public abstract JComponent createRewardGame();
	
	/**
	 * Returns the name associated with the reward component
	 * @return The name associated with the reward component
	 */
	public abstract String getRewardName();
}
