import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GridCell extends JPanel {

	public enum TreeType {
		INFESTED, NON_INFESTED
	};
	
	// minimum height and width of a tree
	private static final int MIN = 50 ;

	private Polygon tree;

	private TreeType treeType;
	
	private Color treeColor ;
	
	private int treeHeight ;
	
	private int treeWidth ;

	private final int height ;
	private final int width ;
	
	private boolean blocked;
	
	private Border border ;
	

	public GridCell(int width, int height) {

		super() ;
		this.width = width ;
		this.height = height ;	

		blocked = true ;
		setLayout(new BorderLayout());
		
		
		border = BorderFactory.createLineBorder(Color.BLACK, 2) ;
		
		setBorder(border);
		
		treeHeight = (int) (MIN + Math.random()*height) ;
		treeWidth =  (int) (MIN + Math.random()*width) ;
		
		
	}

	public void paint(Graphics g) {
		
		

		//System.out.println("width " + width);
		//System.out.println("height " + height);

		if(blocked){
			
			//System.out.println("blocked");
			
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, width, height);
			
		}else{
			

			//System.out.println("not blocked");
			
			g.setColor(Color.white);
			g.fillRect(0, 0, width, height);
			
			
			//System.out.println(treeColor);
			g.setColor(treeColor);
			g.fillPolygon(tree);
			
		}

		border.paintBorder(this, g, 0, 0, width, height);
	}

	public TreeType getTreeType() {
		return treeType;
	}
	
	public void createTree(){
		
		// tree dimensions
		int topX = (int) (Math.random()*(width/2)) ;
		int topY = (int) (Math.random()*(height/2)) ;
		
		int leftX = 0 ;
		int leftY = topY + treeHeight ;
		
		int rightX = treeWidth ;
		int rightY = topY + treeHeight ;
		
		tree = new Polygon() ;
		tree.addPoint(topX, topY);
		tree.addPoint(leftX, leftY);
		tree.addPoint(rightX, rightY);
		
		
		// tree color
		if (!blocked) {

			int random = (int) (Math.random() * 2);

			if (random == 0) {

				treeColor = Color.RED ;

				treeType = TreeType.INFESTED;

			} else {

				treeColor = Color.GREEN ;

				treeType = TreeType.NON_INFESTED;
			}
		}
		
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	
	
/*public static void main(String[] args){
		
		JFrame frame = new JFrame() ;
		
		frame.setSize(300, 300);
		
		frame.setTitle("Estimation Game");
		
		frame.add(new GridCell(300,300)) ;
		
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/

}
