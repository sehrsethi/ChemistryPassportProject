package user_accounts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.filechooser.FileSystemView;

import main.ChemGetPropertyValues;

/**
 * This class creates a directory for the Chemistry Passport Project in the root
 * directory of the user's computer and creates a hidden file containing
 * information about the user's progress for each kit. If the directory and the
 * file already exists, then it does not recreate them. This class also provides
 * the path of the file every time this application is started.
 * 
 * @author Sehr Sethi, Humaira Orchee and Charlotte Dye
 * @version August 23, 2015
 */
public class UserInfoCreator {

	// The directory to put the file in.
	private static final String DIRECTORY_NAME = "ChemistryPassport";

	// The name of the csv file that stores the user's information. Add a "."
	// before the file name to hide the file.
	private static final String FILE_NAME = ".userInfo.csv";

	// The path of the csv file that stores the user's information.
	private static String filePath;

	// True if the userInfo files exists, i.e. the user has already used the
	// program. Otherwise, false.
	private boolean exists = false;

	// This contains methods that will allow us to get the names of the kits.
	private ChemGetPropertyValues propValues;

	/**
	 * Constructs a new UserInfoCreator that will provide the path to the hidden
	 * file that stores user information.
	 * 
	 * @param propValues
	 *            The class that will provide the information about the existing
	 *            kits from the resources/config/config.properties file.
	 */
	public UserInfoCreator(ChemGetPropertyValues propValues) {
		
		this.propValues = propValues;
		

		// The Chemistry Passport directory has to be in the root directory of
		// the user's computer. Find that path.

		String directoryPath = getDirectoryPath() ;
				
		// Get the path of the hidden file containing user's information
		filePath = getFilePath(directoryPath);
		
		// If the directory already exist's then the .csv file will already
		// exist in most cases. If not, then create the file.
		exists = !createChemPassportDirectory(directoryPath);

		if (!exists) {
			createUserInfoFile();
		}

	}

	private String getDirectoryPath() {
		
		String dirPath = System.getProperty("user.home") ;
						
		//The way we format file paths depends on the operating system
		if (System.getProperty("os.name").contains("Windows")) {
			
			return dirPath + "\\" + DIRECTORY_NAME ;

		} else if (System.getProperty("os.name").contains("Mac")) {

			return dirPath + "/" + DIRECTORY_NAME ;

		} else {
			
			// this should work for a linux or unix OS but we are not entirely sure because we could not test it on another OS.
			return dirPath + "/" + DIRECTORY_NAME ;
		}

	}

	/**
	 * Return the path of the file depending on the Operating System.
	 * 
	 * @param directoryPath
	 *            The path of the Chemistry Passport directory that the file
	 *            will be in.
	 * @return The path of the file that will contain user information.
	 */
	private String getFilePath(String directoryPath) {
		
		// if in a windows
		if (System.getProperty("os.name").contains("Windows")) {

			return directoryPath + "\\" + FILE_NAME;

		} else if (System.getProperty("os.name").contains("Mac")) {

			// if in a Mac
			return directoryPath + "/" + FILE_NAME;

		} else {
			
			// this should work for a linux or unix OS but we are not entirely sure because we could not test it on another OS.
			return directoryPath + "/." + FILE_NAME;
		}
	}

	/**
	 * Creates a directory with the name "Chemistry Passport" (which should be
	 * the value specified by DIRECTORY_NAME) in the location specified.
	 * 
	 * @param directoryPath
	 *            The location or path that the directory should be created in.
	 *            This should be the root directory of the computer.
	 * @return True of the directory was created. False if the directory already
	 *         exists and was not created.
	 */
	private boolean createChemPassportDirectory(String directoryPath) {

		return new File(directoryPath).mkdir();
	}

	/**
	 * Creates the file that will contain information about the user, including
	 * their progress for each kit, in the Chemistry Passport directory.
	 */
	private void createUserInfoFile() {

		// create the file object
		File userInfoFile = new File(filePath);
		


		try {
			// Create the file in the directory
			boolean created=userInfoFile.createNewFile();


			// Create the column headings for the file

			// Get a file writer
			BufferedWriter out = new BufferedWriter(
					new FileWriter(userInfoFile));

			// Start making the header lines
			String header = "Adventure Name,Grade";

			// Get the names of the kits
			String[] kitNames = propValues.getKitButtonNames();

			// Iterate through the kits and add kit names to the header string
			for (int i = 0; i < kitNames.length; i++) {
				header += "," + kitNames[i];
			}

			// Write the header to the file
			out.write(header);

			// Close the file writer
			out.close();
			// out.write("");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method is not being used anymore.
	 * Returns the location of the root directory of the user's computer. For
	 * instance, in Windows, it is usually "C:/".
	 * 
	 * @return The location of the root directory of the user's computer.
	 */
	private String getRootDirectoryPath() {

		// Get the home directory where the application is located
		File currentFile = FileSystemView.getFileSystemView()
				.getHomeDirectory();

		File prevFile = currentFile;

		// use the while loop to get the root directory of the computer from the
		// home directory. If currentFile is null, then prevFile is the root
		// directory.
		while (true) {

			try {

				prevFile = currentFile;
				currentFile = new File(currentFile.getParent());

			} catch (NullPointerException e) {

				return prevFile.getAbsolutePath();
			}
		}

	}

	/**
	 * Returns the path of the file containing user's information.
	 * 
	 * @return The path of the file containing user's information.
	 */
	public static String getFilePath() {
		return filePath;
	}

	/**
	 * Depending on the parameter, it hides or shows the specified file. This
	 * method is needed for on a Windows, putting a "." in front of file names
	 * don't hide the file and windows cannot write to a hidden file.
	 * 
	 * @param userInfoFile
	 *            The file to hide or to show.
	 * @param hide
	 *            If hide is true, then the file will be hidden. If hide is
	 *            false then the file will be shown.
	 */
	public static void setHideFile(File userInfoFile, boolean hide) {

		Path path = FileSystems.getDefault().getPath(
				userInfoFile.getAbsolutePath());

		if (System.getProperty("os.name").contains("Windows")) {
			try {

				Files.setAttribute(path, "dos:hidden", hide);

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

}
