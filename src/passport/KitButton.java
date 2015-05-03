package passport;

import javax.swing.JButton;

/**
 * A button in the passport for starting a kit
 * 
 * @author Charlotte Dye, Humaira Orchee, Sehr Sethi
 * @version May 3, 2015
 */
public class KitButton extends JButton {

	// The name of the kit that this button is associated with
	private String kitName;

	/**
	 * Creates a new button in the passport for starting the specified kit
	 * 
	 * @param kitName
	 *            The name of the kit that can be started by pressing on this
	 *            button
	 */
	public KitButton(String kitName) {

		this.kitName = kitName;
	}

	/**
	 * Returns the name of the kit that can be started by pressing on this
	 * button
	 * 
	 * @return The name of the kit that can be started by pressing on this
	 *         button
	 */
	public String getKitName() {

		return kitName;
	}

}
