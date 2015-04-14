package main;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ChemGetPropertyValues {
	
	private String[] kitClassNames;
	
	private String[] kitButtonNames;
	
	public ChemGetPropertyValues(){
		Properties property = new Properties();
		String propertyFileName = "config/config.properties";

		InputStream inputStream = ChemGetPropertyValues.class.getClassLoader()
				.getResourceAsStream(propertyFileName);
		

		try {
			property.load(inputStream);

			// Get the property values
			int numKits = Integer.parseInt(property.getProperty("numKits"));
			
			kitClassNames = new String[numKits];
			kitButtonNames = new String[numKits];

			for (int i = 0; i < numKits; i++) {
				kitClassNames[i] = property.getProperty("kit" + (i + 1));
			}
			for (int j = 0; j < numKits; j++){
				kitButtonNames[j] = property.getProperty("kit" + (j+1) + "Name");
			}

			//return kitClassNames;
		} catch (IOException e) {
			// TODO Generate proper error message
			e.printStackTrace();
			//return null;
		}
	}
	
	public String[] getKitNames(){
		return kitClassNames;
	}
	
	public String[] getKitButtonNames(){
		return kitButtonNames;
	}


}
