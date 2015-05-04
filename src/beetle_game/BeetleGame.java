package beetle_game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.ChemistryPassportGUI;
import passport.Passport;

/**
 * This is the Reward Game for the Bark Beetle Infestation Kit. It allows the
 * user to draw two beetles on the screen with a trail of pheromones connecting
 * them and then lets the user drag the second beetle around so that the first
 * beetle follows it.
 * 
 * @author Humaira Orchee, Charlotte Dye, Sehr Sethi
 * @version April 30, 2015
 *
 */
public class BeetleGame extends JPanel {

	// width of the screen
	private static final int SCREEN_WIDTH = 700;

	// height of the screen
	private static final int SCREEN_HEIGHT = 700;

	// the main gui that the passport, kits, and other reward games are layed
	// out in
	private ChemistryPassportGUI mainGUI;

	/**
	 * Creates a new BeetleGame
	 * 
	 * @param mainGUI
	 *            the main gui that the passport, kits, and other reward games
	 *            are layed out in
	 */
	public BeetleGame(ChemistryPassportGUI mainGUI) {

		this.setLayout(new BorderLayout());

		this.mainGUI = mainGUI;

		add(addInstruction(), BorderLayout.NORTH);

		add(new Beetle(), BorderLayout.CENTER);

		add(addGoToPassportButton(), BorderLayout.SOUTH);

	}

	/**
	 * Adds a button that allows the user to go back to the KitSelectionPage of
	 * the passport
	 * 
	 * @return A button that allows the user to go back to the KitSelectionPage
	 *         of the passport
	 */
	private JButton addGoToPassportButton() {

		// add the go back to passport button at the bottom of the beetle game
		// applet
		JButton passportButton = new JButton("Go to Passport");

		passportButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// when the go back to passport button has been clicked, go back
				// to the passport
				mainGUI.goToCard(ChemistryPassportGUI.PASSPORT_TEXT);

				mainGUI.getPassport().goToPage(Passport.KIT_SELECTION_NAME);

				repaint();

			}
		});

		return passportButton;
	}

	/**
	 * Adds instruction to the game tells users how to play the game
	 * 
	 * @return A JTextField with instruction to the game tells users how to play
	 *         the game
	 */
	private JTextArea addInstruction() {

		String instruction = "Press and drag your mouse to see a beetle and a trail of pheromones appear. When you relase your mouse you will see a second beetle appear at the end of the trail. Now press on the second beetle and drag it around and see the first beetle follow it around.";

		JTextArea instructionArea = new JTextArea(instruction);
		instructionArea.setFont(new Font("Calisto MT", Font.PLAIN, 17));
		instructionArea.setFocusable(false);
		instructionArea.setLineWrap(true);
		instructionArea.setWrapStyleWord(true);
		instructionArea.setBorder(BorderFactory
				.createLineBorder(Color.BLACK, 2));

		return instructionArea;
	}

}
