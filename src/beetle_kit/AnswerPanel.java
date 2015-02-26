package beetle_kit;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * Allows user to type in answers
 * 
 * @author Humaira
 *
 */
public class AnswerPanel extends JPanel {

	private JTextField infestedText;

	private JTextField nonInfestedText;

	private final EstimationGrid estimationGrid;

	/**
	 * @param grid
	 * @param gridView
	 * 
	 */
	public AnswerPanel(EstimationGrid grid) {

		this.estimationGrid = grid;

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		Border border = BorderFactory.createRaisedBevelBorder();

		setBorder(border);

		this.add(new JPanel());

		addInfestedText();

		this.add(new JPanel());

		addNonInfestedtest();

		this.add(new JPanel());

		addShowButton();

		this.add(new JPanel());

		// empty panels are added to add more space
	}

	/**
	 * Adds JLabel, JTextField and JBUtton pertaining to user's aswer about
	 * infested trees
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

				try {
					int numNonInfested = Integer.parseInt(nonInfestedText
							.getText());

					JOptionPane.showMessageDialog(panel, "You said there are "
							+ numNonInfested
							+ " non infested trees. There are actually "
							+ estimationGrid.getTotalUnblockedNonInfested()
							+ " non-infested trees that are visible.");

					/*
					 * System.out.println("You said there are " +
					 * nonInfestedText.getText() + " non infested trees");
					 */

				} catch (NumberFormatException e) {

					JOptionPane.showMessageDialog(panel,
							"Please enter a number");
				}

			}
		});

		panel.add(new JPanel());

		panel.add(checkButton);

		panel.add(new JPanel());

		add(panel);
	}

	/**
	 * Adds JLabel, JTextField and JBUtton pertaining to user's aswer about
	 * infested trees
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

				try {
					int numInfested = Integer.parseInt(infestedText.getText());

					JOptionPane.showMessageDialog(panel, "You said there are "
							+ numInfested
							+ "  infested trees. There are actually "
							+ estimationGrid.getTotalUnblockedInfested()
							+ " infested trees that are visible");

					/*
					 * System.out.println("You said there are " +
					 * infestedText.getText() + " non infested trees");
					 */

				} catch (NumberFormatException e) {

					JOptionPane.showMessageDialog(panel,
							"Please enter a number");
				}

			}
		});

		panel.add(new JPanel());

		panel.add(checkButton);

		panel.add(new JPanel());

		add(panel);

	}

	private void addShowButton() {

		JPanel panel = new JPanel(new BorderLayout());

		JButton showButton = new JButton("Show full grid");

		panel.add(showButton);

		add(panel);

		showButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Clicked");

				GridView fullGridView = new GridView(estimationGrid
						.getCellData());
				fullGridView.setHasBlockedCells(false);

				JPanel fullGridPanel = new JPanel(new BorderLayout());

				fullGridPanel.add(fullGridView, BorderLayout.CENTER);

				JTextArea treeInfo = new JTextArea("There are "
						+ estimationGrid.getTotalInfested()
						+ " infested trees." + "\n" + "There are "
						+ estimationGrid.getTotalNonInfested()
						+ " non-infested trees.");
				Font font = new Font("Times New Roman", Font.PLAIN, 18);
				treeInfo.setFont(font);
				treeInfo.setEditable(false);

				fullGridPanel.add(treeInfo, BorderLayout.SOUTH);

				JFrame frame = new JFrame();
				frame.setSize(GridView.GRID_WIDTH, GridView.GRID_HEIGHT);
				frame.setTitle("Showing Full Grid");
				frame.add(fullGridPanel);
				frame.setResizable(false);
				frame.setVisible(true);

			}
		});
	}

}
