package passport;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class KitSelectionPage extends JPanel {
	
	private Passport passport ;
	
	public KitSelectionPage(Passport passort){
		
		this.passport = passort ;
		
	}

	
	/**
	 * Creates the back and forward buttons
	 */
	private void addButtons() {

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 160,
				15));
		buttonPanel.setBackground(Color.white);

		Font font = new Font("Verdana", Font.PLAIN, 18);

		// backButton
		JButton backButton = new JButton("<----");
		backButton.setFont(font);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				passport.previouPage();

			}
		});

		// forward button
		JButton forwardButton = new JButton("---->");
		forwardButton.setFont(font);

		forwardButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				passport.nextPage();

			}
		});

		// the forward button does not work for the last kitpage
		buttonPanel.add(backButton);
		buttonPanel.add(forwardButton);

		add(buttonPanel);
	}
}
