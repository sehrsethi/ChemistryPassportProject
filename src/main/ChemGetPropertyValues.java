package main;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ChemGetPropertyValues {
	
	private String[] kitClassNames;
	
	private String[] kitButtonNames;
	
	private int[] kitCompletionCriteria ;
	
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
			kitCompletionCriteria = new int[numKits] ;

			
			for (int i = 0; i < numKits; i++) {
				
				// kit class names is the class of the class where the main method of that kit is and the name of the package in which that class is located
				kitClassNames[i] = property.getProperty("kit_" + (i + 1));
				
				// This is the name of the kit as displayed in passport pages and buttons
				kitButtonNames[i] = property.getProperty("kit_" + (i + 1) + "_Name");
				
				try{
					
					kitCompletionCriteria[i] = Integer.parseInt(property.getProperty("kit_" + (i + 1) + "_Completion_Criteria")) ;
				
				}catch(NumberFormatException e){
					
					kitCompletionCriteria[i] = 10 ;
					
				}
			}
			

			//return kitClassNames;
		} catch (IOException e) {
			// TODO Generate proper error message
			e.printStackTrace();
			//return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String[] getKitNames(){
		return kitClassNames;
	}
	
	/**
	 * 
	 * @return
	 */
	public String[] getKitButtonNames(){
		return kitButtonNames;
	}

	/**
	 * 
	 * @return
	 */
	public int[] getKitCompletionCriteria() {
		return kitCompletionCriteria;
	}
	
	


}
