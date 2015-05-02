package user;

import java.util.ArrayList;

/**
 * This class creates the user that is currently using the Chemistry Passport
 * Program
 * 
 * @author Humaira Orchee, Charlotte Dye, Sehr Sethi
 * @version May 2, 2015
 */
public class User {

	// The name that the user chooses to use for the program instead of their
	// actual name.
	private final String adventureName;

	// The user's grade.
	private final String grade;

	// The array list of the progress, represented by integers, made by the user
	// in each kit currently existing in the program.
	private ArrayList<Integer> kitProgress;

	/**
	 * Creates an user.
	 * 
	 * @param adventureName
	 *            The name that the user chooses to use for the program instead
	 *            of their actual name.
	 * @param grade
	 *            The user's grade.
	 * @param kitProgress
	 *            The array list of the progress, represented by integers, made
	 *            by the user in each kit currently existing in the program.
	 */
	public User(String adventureName, String grade,
			ArrayList<Integer> kitProgress) {

		this.adventureName = adventureName;

		this.grade = grade;

		this.kitProgress = kitProgress;

	}

	/**
	 * Creates an user.
	 * 
	 * @param adventureName
	 *            The name that the user chooses to use for the program instead
	 *            of their actual name.
	 * @param grade
	 *            The user's grade.
	 * @param kitProgressString
	 *            The string of the progress, represented by integers, made by
	 *            the user in each kit currently existing in the program.
	 */
	public User(String adventureName, String grade, String kitProgressString) {

		this.adventureName = adventureName;

		this.grade = grade;

		// It is more convenient to have the user's progress in each kit stored
		// in an array list. So convert the string of progress to an array list
		// of progress.
		kitProgress = new ArrayList<Integer>();

		// Each integer indicating kit progress is separated by "," in the
		// string.
		String[] kitProgressArray = kitProgressString.split(",");

		for (int i = 0; i < kitProgressArray.length; i++) {

			kitProgress.add(Integer.parseInt(kitProgressArray[i]));
		}

	}

	/**
	 * Returns the adventure name of the user.
	 * 
	 * @return The adventure name of the user.
	 */
	public String getAdventureName() {
		return adventureName;
	}

	/**
	 * Returns the array list of the progress, represented by integers, made by
	 * the user in each kit currently existing in the program.
	 * 
	 * @return The array list of the progress, represented by integers, made by
	 *         the user in each kit currently existing in the program.
	 */
	public ArrayList<Integer> getKitProgress() {
		return kitProgress;
	}

	/**
	 * Lets each kit update the progress the user made for that kit.
	 * 
	 * @param kitNumber
	 *            The number of the kit for which the user's progress has
	 *            changed. Each kit has an integer associated with them,
	 *            determined by the order they were added to the program. This
	 *            number can be found in the config.properties file.
	 * @param progress
	 *            The new progress of the user in the specified kit.
	 */
	public void setKitProgress(int kitNumber, int progress) {

		kitProgress.set(kitNumber, progress);
	}

	/**
	 * Return the grade of the user.
	 * 
	 * @return The grade of the user.
	 */
	public String getGrade() {
		return grade;
	}

}
