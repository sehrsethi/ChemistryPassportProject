package main;

import javax.swing.JPanel;

/**
 * Represents a kit in the Chemistry Passport Program
 * @author Humaira Orchee, Charlotte Dye, Sehr Sethi
 *
 */
public abstract class Kit extends JPanel{
	
	/**
	 * Returns the name of this kit
	 * @return Name of this kit
	 */
	public abstract String getKitName();
	
	/**
	 * Starts the kit
	 */
	public abstract void startKit();
}
