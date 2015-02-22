import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JApplet;
import javax.swing.JTextField;

public class AppletTest extends JApplet {

	public void init() {
		JTextField tField = new JTextField();

		File file = new File("C://Users//Humaira//Desktop//HelloWorld.txt");
		
		// File("royal.cs.mtholyoke.edu//userhomes//orche22h//HelloWorld.txt") ;

		try {
			
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write("Hello World!");
			out.close() ;

			BufferedReader in = new BufferedReader(new FileReader(file));

			String text = in.readLine();
			tField.setText(text);
			
			in.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.add(tField);
	}

}
