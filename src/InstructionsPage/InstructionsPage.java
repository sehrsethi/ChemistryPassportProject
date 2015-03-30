package InstructionsPage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class InstructionsPage {

	public static void main(String[] args0) {
		Instructions instructions = new Instructions();
		instructions.setTitle("Bark Beetle Infestation Instruction Page");
		instructions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instructions.setSize(780, 570);
		instructions.setVisible(true);


	}
}
