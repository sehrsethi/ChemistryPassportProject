package user_account;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextField;


public class DatabaseCreater {
	
	private static final String FILE_NAME = "database.csv" ;

	public DatabaseCreater(JTextField fullNameText, JTextField gradeText, JTextField userNameText ){
		
		writeToFile(fullNameText, gradeText, userNameText);
		
		
	}
	
	private void writeToFile(JTextField fullNameText, JTextField gradeText, JTextField userNameText ) {
		
		File databaseFile = new File(FILE_NAME) ;
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(databaseFile, true)) ;
			
			String userName = userNameText.getText() + " ," ;
			
			String fullName = fullNameText.getText() + " ," ;
			
			String grade = gradeText.getText() + " ," ;
			
			out.write("\n" + userName + fullName + grade);
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
