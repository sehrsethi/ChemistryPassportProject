import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * Allows user to type in answers 
 * @author Humaira
 *
 */
public class AnswerPanel extends JPanel {

	private JTextField infestedText;

	private JTextField nonInfestedText;

	/**
	 * 
	 */
	public AnswerPanel() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		Border border = BorderFactory.createRaisedBevelBorder();

		setBorder(border);

		this.add(new JPanel());

		addInfestedText();

		this.add(new JPanel());

		addNonInfestedtest();

		this.add(new JPanel());
		
		// empty panels are added to add more space
	}

	/**
	 * Adds JLabel, JTextField and JBUtton pertaining to user's aswer about infested trees
	 */
	private void addNonInfestedtest() {

		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		panel.add(new JPanel());
		
		panel.add(new JPanel());
		
		panel.add(new JLabel("Enter number of non-infested trees : "));
						
		panel.add(new JPanel());
		
		nonInfestedText = new JTextField();
		panel.add(nonInfestedText);

		JButton checkButton = new JButton("Check My Answer!");

		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try{
				int numNonInfested = Integer.parseInt(nonInfestedText.getText()) ;
				
				JOptionPane.showMessageDialog(panel, "You said there are "
						+ numNonInfested + " non infested trees");

				/*System.out.println("You said there are "
						+ nonInfestedText.getText() + " non infested trees");*/
				
				}catch(NumberFormatException e){
					
					JOptionPane.showMessageDialog(panel, "Please enter a number");
				}

			}
		});

		panel.add(new JPanel());
		
		panel.add(checkButton);
		
		panel.add(new JPanel());

		add(panel);
	}

	/**
	 * Adds JLabel, JTextField and JBUtton pertaining to user's aswer about infested trees
	 */
	private void addInfestedText() {

		final JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		panel.add(new JPanel());
		
		panel.add(new JPanel());
		
		
		
		panel.add(new JLabel("Enter number of infested trees :   "));
			
		panel.add(new JPanel());
		
		panel.add(new JPanel());
		
		panel.add(new JPanel());
		
		infestedText = new JTextField();
		panel.add(infestedText);

		JButton checkButton = new JButton("Check My Answer!");

		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try{
					int numInfested = Integer.parseInt(infestedText.getText()) ;
					
						JOptionPane.showMessageDialog(panel, "You said there are "
							+ numInfested + "  infested trees");

					/*System.out.println("You said there are "
							+ infestedText.getText() + " non infested trees");*/
					
					}catch(NumberFormatException e){
						
						JOptionPane.showMessageDialog(panel, "Please enter a number");
					}

			}
		});

		panel.add(new JPanel());
		
		panel.add(checkButton);
		
		panel.add(new JPanel());

		
		
		add(panel);

	}

}
