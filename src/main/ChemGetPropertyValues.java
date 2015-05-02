package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Chemistry Passport Program
 * 
 * This class is in charge of reading information from the properties file in
 * the resources folder about the kits.
 * 
 * @author Charlotte Dye, Humaira Orchee, and Sehr Sethi
 *
 */
public class ChemGetPropertyValues {

	// The package and class names (e.g., beetle_kit.BeetleKit)
	private String[] kitClassNames;

	// The names for kits that are displayed in the passport and on buttons
	// (e.g. Beetle Kit)
	private String[] kitButtonNames;

	// The maximum allowed progress/the progress necessary to get the sticker
	// and reward (e.g., for the estimation game this is 3, because they need to
	// play 3 rounds before they can get their reward)
	private int[] kitCompletionCriteria;

	/**
	 * Creates an instance of the ChemGetPropertyValues class and reads
	 * everything in from the property file
	 */
	public ChemGetPropertyValues() {

		// Set up the property file
		Properties property = new Properties();
		String propertyFileName = "config/config.properties";
		InputStream inputStream = ChemGetPropertyValues.class.getClassLoader()
				.getResourceAsStream(propertyFileName);

		try {

			// Load the properties
			property.load(inputStream);

			// Get the property values

			// The number of total kits in the program
			int numKits = Integer.parseInt(property.getProperty("numKits"));

			kitClassNames = new String[numKits];
			kitButtonNames = new String[numKits];
			kitCompletionCriteria = new int[numKits];

			// Iterate through information for each kit.
			for (int i = 0; i < numKits; i++) {

				// kit class names is the class of the class where the main
				// method of that kit is and the name of the package in which
				// that class is located
				kitClassNames[i] = property.getProperty("kit_" + (i + 1));

				// This is the name of the kit as displayed in passport pages
				// and buttons
				kitButtonNames[i] = property.getProperty("kit_" + (i + 1)
						+ "_Name");

				try {

					// The kit completion criteria for each kit
					kitCompletionCriteria[i] = Integer.parseInt(property
							.getProperty("kit_" + (i + 1)
									+ "_Completion_Criteria"));

				} catch (NumberFormatException e) {

					// If the kit completion criteria isn't a valid number,
					// default to 10
					kitCompletionCriteria[i] = 10;

				}
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	/**
	 * Returns the an array of kit class and package names
	 * 
	 * @return String array of full paths to main classes of kits
	 */
	public String[] getKitNames() {
		return kitClassNames;
	}

	/**
	 * Returns an array of the kit button names
	 * 
	 * @return String array of kit names displayed on passport and buttons
	 */
	public String[] getKitButtonNames() {
		return kitButtonNames;
	}

	/**
	 * Returns an array of kit completion criteria
	 * 
	 * @return An int array of the maximum allowable progress for each kit
	 */
	public int[] getKitCompletionCriteria() {
		return kitCompletionCriteria;
	}

}
