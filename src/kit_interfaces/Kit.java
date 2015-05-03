package kit_interfaces;

import javax.swing.JComponent;
import javax.swing.JPanel;

import passport.Passport;
import main.ChemistryPassportGUI;

/**
 * A kit in the Chemistry Passport Program is designed to teach children in
 * grade k-6 a skill.
 * 
 * @author Humaira Orchee, Charlotte Dye, Sehr Sethi
 * @version May 2, 2015
 *
 */
public abstract class Kit extends JPanel {

	// Each kit has an integer associated with them, determined by the order
	// they were added to the program. This number can be found in the
	// config.properties file.
	protected Integer kitIndex;

	// The main GUI of the entire Chemistry Passport Program.
	protected ChemistryPassportGUI mainGUI;

	// The Integer representing user's progress on current kit
	protected int kitProgress;
	
	// The current passport
	protected Passport passport;

	/**
	 * Creates a new kit. This constructor is used when only the reward game
	 * needs to be added to the passport without the need for the entire kit to
	 * be created.
	 * 
	 * @param mainGUI
	 *            The main GUI of the entire Chemistry Passport Program.
	 */
	public Kit(ChemistryPassportGUI mainGUI) {

		this.mainGUI = mainGUI;		

		// Get the passport
		passport = mainGUI.getPassport();

	}

	/**
	 * Creates a new kit. This constructor is used when only the reward game
	 * needs to be added to the passport without the need for the entire kit to
	 * be created.
	 * 
	 * @param mainGUI
	 *            The main GUI of the entire Chemistry Passport Program.
	 * @param kitProgress
	 *            The Integer representing user's progress on current kit.
	 * @param kitIndex
	 *            The integer associated with the current kit.
	 */
	public Kit(ChemistryPassportGUI mainGUI, Integer kitProgress,
			Integer kitIndex) {

		this.mainGUI = mainGUI;

		this.kitProgress = kitProgress;

		this.kitIndex = kitIndex;
		

		// Get the passport
		passport = mainGUI.getPassport();
	}

	/**
	 * Returns the main ChemistryPassort GUI.
	 * 
	 * @return The main GUI of the program.
	 */
	public ChemistryPassportGUI getMainGUI() {

		return mainGUI;
	}

	/**
	 * Returns the kit progress of the user for this kit.
	 * 
	 * @return The kit progress of the user for this kit.
	 */
	public int getKitProgress() {

		return kitProgress;
	}

//	/**
//	 * Sets the new progress of the current user for this kit.
//	 * 
//	 * @param kitProgress
//	 *            The new progress of the current user for this kit.
//	 */
//	protected abstract void setUserKitProgress(int kitProgress);

	/**
	 * Sets the new progress of the user for this kit.
	 * 
	 * @param kitProgress
	 *            The new progress of the user for this kit.
	 */
	public void setKitProgress(int kitProgress) {

		this.kitProgress = kitProgress;
		
		passport.getUser().setKitProgress(kitIndex, kitProgress);
	}

	/**
	 * Starts the kit
	 */
	public abstract void startKit();

	/**
	 * Returns the name of the button for this kit in the KitSelectionPage
	 * 
	 * NOTE : This should exactly match the value of kit_[kit_num]_Name in the
	 * config.properties file
	 * 
	 * @return The name of the button for this kit in the KitSelectionPage
	 */
	public abstract String getButtonName();

	/**
	 * Starts the reward game and returns the reward component
	 * 
	 * @return Reward component to display
	 */
	public abstract JComponent createRewardGame();

	/**
	 * Returns the name associated with the reward component
	 * 
	 * @return The name associated with the reward component
	 */
	public abstract String getRewardName();
	

	/**
	 * Get the passport that this BeetleKit belongs to
	 * 
	 * @return The user's passport
	 */
	public Passport getPassport() {
		return passport;
	}
}
