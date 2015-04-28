package user_accounts;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cookies.CookieAccessor;

import java.net.* ;
import java.util.List;

public class SignUpGUI extends JPanel {

	// the cookie key for the user's name
	private static final String ADVENTURE_NAME = "AdventureName" ;
	
	// the cookie key for the user's grade
	private static final String GRADE = "Grade" ;

	private JTextField gradeTextField;
	
	private JTextField nameTextField ;
	
	private JTextField gradeTextFieldTemp;
	
	private JTextField nameTextFieldTemp ;

	private CookieAccessor cookieAccessor;

	public SignUpGUI(CookieAccessor cookieAccessor) {

		this.cookieAccessor = cookieAccessor;

		// have to do this initially
		cookieAccessor.getCookiesUsingCookieHandler();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		addAdventureNamePanel();

		addGradePanel();

		addSignUpButton();
	}

	private void addAdventureNamePanel() {

		JPanel panel = new JPanel();

		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		panel.setLayout(boxLayout);

		JLabel label = new JLabel("Adventure Name");

		panel.add(label);

		nameTextField = new JTextField();

		panel.add(nameTextField);
		
		nameTextFieldTemp = new JTextField();

		panel.add(nameTextFieldTemp);

		add(panel);
	}

	private void addGradePanel() {

		JPanel panel = new JPanel();

		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		panel.setLayout(boxLayout);

		JLabel label = new JLabel("Grade");

		panel.add(label);

		gradeTextField = new JTextField();

		panel.add(gradeTextField);
		
		gradeTextFieldTemp = new JTextField();

		panel.add(gradeTextFieldTemp);

		add(panel);
	}

	private void addSignUpButton() {

		JPanel panel = new JPanel(new BorderLayout());

		JButton button = new JButton("Sign Up");

		panel.add(button, BorderLayout.CENTER);

		add(panel);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				cookieAccessor.setCookieUsingCookieHandler(ADVENTURE_NAME, nameTextField.getText());
				
				cookieAccessor.setCookieUsingCookieHandler(GRADE, gradeTextField.getText());

			}
		});
		
		JButton button2 = new JButton("Press") ;
		panel.add(button2, BorderLayout.SOUTH) ;
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				List<HttpCookie> cookies = cookieAccessor.getCookiesUsingCookieHandler() ;
				
				for (HttpCookie cookie : cookies) {
					//textField.setText(cookie.toString());
					System.out.println("CookieHandler retrieved cookie: " + cookie);
					
					if(cookie.getName().equals(ADVENTURE_NAME)){
						
						nameTextFieldTemp.setText(nameTextFieldTemp.getText() + " " + cookie.getValue());
						
					}else if(cookie.getName().equals(GRADE)){
						
						gradeTextFieldTemp.setText(gradeTextFieldTemp.getText() + " " + cookie.getValue());
					}
				}
				
				repaint();
			}
		});
	}
}
