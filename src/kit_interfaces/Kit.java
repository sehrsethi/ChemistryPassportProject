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
