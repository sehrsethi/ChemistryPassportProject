package user;

import java.util.ArrayList;

public class User {

	private final String fakeName ;
	
	private final String grade ;
	
	private ArrayList<Integer> kitProgress ;
	
	
	public User(String adventureName , String grade, ArrayList<Integer> kitProgress){

		
		this.fakeName = adventureName ;
		
		this.grade = grade ;
		
		this.kitProgress = kitProgress ;
		
		
	}



	public String getFakeName() {
		return fakeName;
	}

	public ArrayList<Integer> getKitProgress() {
		return kitProgress;
	}

	public String getGrade() {
		return grade;
	}
	
	

}
