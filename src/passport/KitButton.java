package passport;

import javax.swing.JButton;

/**
 * 
 * @author Charlotte Dye, Humaira Orchee, Sehr Sethi A button in the passport
 *         for starting a kit
 *
 */
public class KitButton extends JButton {

	private String kitName;

	public KitButton(String kitName) {

		this.kitName = kitName;
	}

	public String getKitName() {

		return kitName;
	}

}
