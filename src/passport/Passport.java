package passport;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Passport extends JPanel implements MouseListener{
	
	private static final int PAGE_WIDTH = 500;

	private static final int PAGE_HEIGHT = 700;
	
	private static final CardLayout CARD_LAYOUT = new CardLayout();

	private static final String FIRST_PAGE_NAME = "First Page" ;
	
	private static final FirstPage firstPage = new FirstPage() ; 
	
	
	private ArrayList<String> pageNames  = new ArrayList<String>() ;
	
	private int currentPage = 0 ; 
	
	public Passport(){
		
		this.setLayout(CARD_LAYOUT) ;
		
		this.addMouseListener(this);
		
		addPage("Beetle Page") ;
		
		this.add(firstPage, FIRST_PAGE_NAME) ;
		
		CARD_LAYOUT.show(this,FIRST_PAGE_NAME) ;
				
		
		
	}
	
	/**
	 * 
	 * @param pageName
	 */
	public void addPage(String pageName){
		
		pageNames.add(pageName) ;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		currentPage++ ;
		
		System.out.println(currentPage);
		CARD_LAYOUT.show(this, pageNames.get(currentPage));
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {

		JFrame frame = new JFrame();

		frame.getContentPane().add(new Passport());

		frame.setSize(Passport.PAGE_WIDTH, Passport.PAGE_HEIGHT);

		frame.setVisible(true);
	}

}
