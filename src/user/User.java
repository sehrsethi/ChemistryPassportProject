package user;

import java.util.ArrayList;

public class User {

	private final String adventureName ;
	
	private final String grade ;
	
	private ArrayList<Integer> kitProgress ;
	
	
	public User(String adventureName , String grade, ArrayList<Integer> kitProgress){
		
		this.adventureName = adventureName ;
		
		this.grade = grade ;
		
		this.kitProgress = kitProgress ;
		
	}

	
	public User(String adventureName , String grade, String kitProgressString){
		
		this.adventureName = adventureName ;
		
		this.grade = grade ;
		
		kitProgress = new ArrayList<Integer>() ;
		
		String[] kitProgressArray = kitProgressString.split(",") ;
		
		for(int i = 0 ; i < kitProgressArray.length ; i++){
			
			kitProgress.add(Integer.parseInt(kitProgressArray[i])) ;
		}
		
	}
	

	public String getAdventureName() {
		return adventureName;
	}

	public ArrayList<Integer> getKitProgress() {
		return kitProgress;
	}
	
	public void setKitProgress(int kitNumber, int progress){
		
		kitProgress.set(kitNumber, progress);
	}

	public String getGrade() {
		return grade;
	}

}
