package user;

import java.util.ArrayList;

public class User {
	
	private final String userName ;
	
	private final String fakeName ;
	
	private final String grade ;
	
	private ArrayList<Integer> kitProgress ;
	
	
	public User(String userName , String fakeName, String grade, ArrayList<Integer> kitProgress){
		
		this.userName = userName ;
		
		this.fakeName = fakeName ;
		
		this.grade = grade ;
		
		this.kitProgress = kitProgress ;
		
		
	}

	public String getUserName() {
		return userName;
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
