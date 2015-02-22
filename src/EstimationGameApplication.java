import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;


public class EstimationGameApplication {
	
	public static final int FRAME_WIDTH = 600 ;
	
	public static final int FRAME_HEIGHT = 800 ;
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame() ;
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT); 
		
		frame.setTitle("Estimation Game");
		
		EstimationGrid estimationGame = new EstimationGrid(FRAME_WIDTH, FRAME_HEIGHT ) ;		
		estimationGame.calculateInfested();
		estimationGame.calculateNonInfested();
		
		frame.add(estimationGame) ;
		
		
		
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
