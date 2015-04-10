package main;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ChemGetPropertyValues {

	public static String[] getKitNames() {

		Properties property = new Properties();
		String propertyFileName = "config/config.properties";

		InputStream inputStream = ChemGetPropertyValues.class.getClassLoader()
				.getResourceAsStream(propertyFileName);
		

		try {
			property.load(inputStream);

			// Get the property values
			int numKits = Integer.parseInt(property.getProperty("numKits"));

			String kitNames[] = new String[numKits];

			for (int i = 0; i < numKits; i++) {
				kitNames[i] = property.getProperty("kit" + (i + 1));
			}

			return kitNames;
		} catch (IOException e) {
			// TODO Generate proper error message
			e.printStackTrace();
			return null;
		}
	}

}
