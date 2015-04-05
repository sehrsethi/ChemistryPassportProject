package beetle_kit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class EstimationStartPage extends JPanel {

	// private static final int DEFAULT = 0 ;
	//
	// private static final int PROTANOPIA = 1 ;
	//
	// // red-green color blindness
	// private static final int DEUTERANOPIA = 2 ;
	//
	// private static final int TRITANOPTIA = 3 ;

	public static final String DEFAULT_TEXT = "Default (Non- colorblind)";

	public static final String PROTANOPIA_TEXT = "Protanopia (Red-Blue-Green Colorblindness)";

	public static final String DEUTERANOPIA_TEXT = "Deuteranopia (Red-Green Colorblindness)";

	public static final String TRITANOPTIA_TEXT = "Tritanopia (Blue-Yellow Colorblindness)";

	private String mode = DEFAULT_TEXT;
	
	// colors in default mode
	private Color infestedColor = Color.RED;
	
	private Color nonInfestedColor = Color.GREEN;

	public EstimationStartPage() {

		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);

		this.setLayout(boxLayout);

		// this.setLayout(new BorderLayout());

		addText();

		addComboBox();

		addStartButton();

	}

	/**
	 * 
	 */
	private void addText() {

		JTextArea label = new JTextArea(
				"Are you colorblind? If so, select the type of colorblindness you have.");

		label.setEditable(false);
		label.setFocusable(false);

		// add(label, BorderLayout.NORTH) ;

		add(label);
	}

	/**
	 * 
	 */
	private void addComboBox() {

		JPanel panel = new JPanel(new BorderLayout());

		String[] comboChoices = { DEFAULT_TEXT, PROTANOPIA_TEXT,
				DEUTERANOPIA_TEXT, TRITANOPTIA_TEXT };

		final JComboBox<String> comboBox = new JComboBox<String>(comboChoices);

		comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		comboBox.setAlignmentY(Component.CENTER_ALIGNMENT);

		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.setAlignmentY(Component.CENTER_ALIGNMENT);

		comboBox.setSelectedIndex(0);

		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				System.out.println("item changed");

				mode = (String) comboBox.getSelectedItem();

				System.out.println("mode " + mode);
				
				infestedColor = selectInfestedColor(mode) ;
				
				nonInfestedColor = selectNonInfestedColor(mode) ;

			}
		});

		// add(comboBox, BorderLayout.CENTER) ;

		panel.add(comboBox);

		add(panel);
	}

	private void addStartButton() {

		JButton startButton = new JButton("Start");

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Should be starting now");

			}
		});

		// add(startButton, BorderLayout.SOUTH);

		add(startButton);
	}



	private Color selectInfestedColor(String mode) {

		if (mode.equals(DEFAULT_TEXT)) {

			return Color.RED;

		} else if (mode.equals(PROTANOPIA_TEXT)) {

			// hex value : 6094ff
			return new Color(96, 148, 255);

		} else if (mode.equals(DEUTERANOPIA_TEXT)) {

			// hex value : 008ce2
			return new Color(0, 140, 226);

		} else if (mode.equals(TRITANOPTIA_TEXT)) {

			// hex value : fe1c00
			return new Color(254, 28, 0);
		}

		else
			throw new AssertionError("There should only be 4 modes. Mode is "
					+ mode);

	}

	private Color selectNonInfestedColor(String mode) {

		if (mode.equals(DEFAULT_TEXT)) {

			return Color.GREEN;

		} else if (mode.equals(PROTANOPIA_TEXT)) {

			// hex value : ffe41c
			return new Color(255, 228, 28);

		} else if (mode.equals(DEUTERANOPIA_TEXT)) {

			// hex value : f6c600
			return new Color(246, 198, 0);

		} else if (mode.equals(TRITANOPTIA_TEXT)) {

			// hex value : 7aedff
			return new Color(122, 237, 255);
		}

		else
			throw new AssertionError("There should only be 4 modes. Mode is "
					+ mode);
	}
	
	
	
	public Color getInfestedColor() {
		return infestedColor;
	}

	public Color getNonInfestedColor() {
		return nonInfestedColor;
	}
	


	public static void main(String[] args) {

		// Create a JFrame for the application and give it a size and close
		// operation
		JFrame frame = new JFrame("Estimation Start Page");
		frame.setSize(605, 671);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(new EstimationStartPage());

		// Make the frame visible
		frame.setVisible(true);

		frame.setResizable(false);

		// close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
