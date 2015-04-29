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

public class UserInfoCreator {

	// The directory to put the file in
	private static final String DIRECTORY_NAME = "Chemistry Passport";

	// The name of the csv file that stores the user's information. Add a "."
	// before the file name to hide the file
	private static final String FILE_NAME = ".userInfo.csv";

	// The path of the csv file that stores the user's information
	private static String filePath;

	// True if the userInfo files exists, i.e. the user has already used the
	// program. Otherwise, false.
	private boolean exists = false;

	// This contains methods that will allow us to get the names of the kits
	private ChemGetPropertyValues propValues;

	/**
	 * 
	 */
	public UserInfoCreator(ChemGetPropertyValues propValues) {

		this.propValues = propValues;

		String directoryPath = getRootDirectoryPath();

		filePath = getFilePath(directoryPath);

		exists = !createChemPassportDirectory(directoryPath);

		if (!exists) {

			createUserInfoFile();
		}

	}

	/**
	 * Return the path of the file depending on the Operating System
	 * 
	 * @return
	 */
	private String getFilePath(String directoryPath) {

		// if in a windows
		if (System.getProperty("os.name").contains("Windows")) {

			return directoryPath + DIRECTORY_NAME + "\\" + FILE_NAME;

		} else if (System.getProperty("os.name").contains("Mac")) {

			// if in a Mac

			return directoryPath + DIRECTORY_NAME + "/" + FILE_NAME;

		} else {

			// if in any other OS, like Linux, don't put the file in a directory
			// because we are not sure what slash ("\\" or "/" or something
			// else) to use

			// add a "." before the file name to hide the file

			return directoryPath + "." + FILE_NAME;
		}
	}

	private boolean createChemPassportDirectory(String directoryPath) {

		return new File(directoryPath + DIRECTORY_NAME).mkdir();
	}

	private void createUserInfoFile() {

		File userInfoFile = new File(filePath);

		try {
			// Make the user info file
			userInfoFile.createNewFile();

			// Create the headings for the file

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
	 * 
	 * @return
	 */
	private String getRootDirectoryPath() {

		// Get the home directory where the application is located
		File currentFile = FileSystemView.getFileSystemView()
				.getHomeDirectory();
		File prevFile = currentFile;

		// use the while loop to get the root directory of the computer
		while (true) {

			try {

				prevFile = currentFile;
				currentFile = new File(currentFile.getParent());

			} catch (NullPointerException e) {

				return prevFile.getAbsolutePath();
			}
		}

	}

	public static String getFilePath() {
		return filePath;
	}

	/**
	 * 
	 * @param userInfoFile
	 * @param hide
	 *            If hide is true, then the file will be hidden. If hide is
	 *            false then the file will be not hidden
	 * @throws IOException
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
