package InstructionsPage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

public class Instructions extends JFrame {

	private JTextArea textArea;
	
	//add label to top of textarea
	 JLabel	instructionsLabel = new JLabel();
	


	public Instructions() {
		setLayout(new SpringLayout());
		
		instructionsLabel.setLocation(100, 100);
	    instructionsLabel.setText("Instructions:");
	    instructionsLabel.setFont(new Font("Verdana",1,20));	
	    add(instructionsLabel);
	    
	    String text = 
	    		 "Introduction to the game:"
	    				+ "\n" 
	    				+ "\n" +"Bark beetles are a natural predator of trees and form an important part of the cycle of life."
	    		+ "\n" + "Sometimes, when the number of bark beetles is too large, they are a threat to wildlife, trees, and homes."
	    		+ "\n"+ "When too many trees are infected with bark beetles, forest animals lose their homes and food."
	    		+ "\n"+ "Further, the risk of forest fires increases."
	    		+ " Knowing how many trees are infected is important to understanding"
	    		+ "\n" + "whether the number of bark beetle larvae is growing or shrinking."
	    		+ "\n"+ "In this kit, you’ll learn how to estimate how many trees are infected and how many are healthy."
	    		+ "\n"+ "You’ll also learn about how beetles use pheromones to attract each other and how these could be used to trap them. "
	    
	    		+ "\n" +
	    		
	     		"1.Estimating the size of the problem: "
	     		+ "Scientists can’t always measure everything exactly in the time given."
	     		+ "\n" +
	     		"When they can’t, they will often use estimation to get a good answer."
	    		+ "\n" +
	    		"In this game, we need to count the “red crowns” before the bark beetle infected trees lose their needles."
	    		+ "\n" +
	    		"We could take a picture of an infected forest and count the number of trees with “red” "
	    		+ "crowns and “green” crowns"
	    		+ "\n" + "to get an exact number of dying and healthy trees."
	    		+ "\n" +
	    		"However, for a large forest, this would take a while." 
	    		+ "\n" +
	    		"Instead, we will estimate using this grid. "
	    		+ "\n" +
	    		"To make the grid, we started with a picture of a section of infected and healthy trees,"
	    		+ "\n" +" put a grid on top of it and covered 2 out of every 3 squares of the picture to help speed the counting."
	    		+ "\n" +
	    		"\n" + "Instructions on how to play the game:"
	    		+ "\n" 
	    		+ "\n"+ "To play the bark beetle infestation game, we need to set up rules that tell us what to count and how to count."
				+ "\n"
				+ "Rule 1: What to count: "
				+ "Red crowns and green crowns in each picture."
				+ "\n"
				+ "Rule 2: How to count:"
				+ " If a block contains the center of a tree crown, add one to the count. "
				+ "\n"
				+ "If it has less than the center of a tree crown, don’t add one to the count."
				+ "\n"
				+ "Sometimes the picture in the block isn’t clear. Just make a your best guess!"
				+ "\n"
				+ "Write the number of red and green crowns you counted in your notebook. "
				+ "\n"
				+ "Since only two out of every threesquares is visible, you are only seeing one third of the area. "
				+ "\n"
				+ "To estimate how many red crowns are in the whole grid, add the number you got for a third of the picture three times. "
				+ "\n"
				+ "Do the same to get an estimate of the number of green crowns in the grid."
				+ "\n"
				+ "Try getting the estimate by clicking on the check estimate button."
				+ "\n"
				+ "Your estimates will be different but similar. "
				+ "\n"
				+ "The differences highlight that estimation is approximate.";

		textArea = new JTextArea( text, 775, 700);

		textArea.setPreferredSize(new Dimension(775, 700));

		textArea.setFont(new Font("Serif", Font.BOLD, 15));
		add(instructionsLabel);
		textArea.add(instructionsLabel);
		add(textArea);

		JScrollPane scrollPane = new JScrollPane(textArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		textArea.setLineWrap(true);

		add(scrollPane);

		textArea.setEditable(false);
		pack();

	}

}