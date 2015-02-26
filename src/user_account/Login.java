package user_account;
/**
 * authors: Sehr, Humaira and Charlotte 
 * Simple Login and Sign up Page V.1
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import com.sun.org.apache.bcel.internal.generic.DADD;


//convet to app
//init method where main method
//create JFrames, do not extend, so u can extend JApplet
//create database
//connect to database - read and write from file
public class Login extends JFrame implements ActionListener, KeyListener{
       
       // Member variables ('m' -> member)
       private String m_fullName = null;
       private String m_serverName = null;
       private String m_context = null;
       private String m_user = null;
       private String m_grade = null;
       private String m_entryFullName = null;

       // Gui Components ('g' -> GUI component)
       private JPanel           g_root = null;
       private JLabel           g_userLabel = null;
       private JTextField       g_userText = null;
       private JLabel           g_gradeLabel = null;
       private JPasswordField   g_gradeText = null;
       private JTabbedPane      g_tabbedPane = null;
       private JLabel           g_serverNameLabel = null;
       private JLabel			g_contextLabel2 = null;
       private JTextField		g_contextText2 = null;
       private JLabel			g_contextLabel3 = null;
       private JTextField		g_contextText3 = null;
       //private JTextField       g_serverNameText = null;
       private JLabel           g_contextLabel = null;
       private JTextField       g_contextText = null;
       private JButton          g_login = null;
       private JButton          g_cancel = null;
       private JButton			g_signup = null;
       private JButton			g_submit = null;
       
       Login(String title)
       {
            super(title);


            g_root = new JPanel();
            g_root.setLayout( new BoxLayout(g_root, BoxLayout.Y_AXIS) );

           g_root.setBorder( new javax.swing.border.LineBorder( 
                                 (new Color(150,150,150)), 2 ) );
     
            JPanel pan4 = new JPanel();
            JPanel pan2 = new JPanel();
            g_userLabel = new JLabel("User Name:  ");
            g_userLabel.setFont( new Font("Monospaced", Font.PLAIN, 12) );
            g_userText = new JTextField(40);
            g_userText.setFont( new Font("Monospaced", Font.BOLD, 12) );
            pan2.add( g_userLabel );
            pan2.add( g_userText );
            g_userText.addKeyListener( this );
            pan4.add(pan2);
            
            JPanel pan3 = new JPanel();
            g_gradeLabel = new JLabel("   Grade:  ");
            g_gradeLabel.setFont( new Font("Monospaced", Font.PLAIN, 12) );
            g_gradeText = new JPasswordField(40);
            g_gradeText.setFont( new Font("Monospaced", Font.BOLD, 12) );
            pan3.add( g_gradeLabel );
            pan3.add( g_gradeText );
            g_gradeText.addKeyListener( this );
            pan4.add(pan3);
            g_root.add(pan4);
   
            g_tabbedPane = new JTabbedPane();
            JPanel root_panel_inside_tabbedPane = new JPanel();
            root_panel_inside_tabbedPane.setLayout(new BoxLayout(root_panel_inside_tabbedPane, 
                                                   BoxLayout.Y_AXIS) );
            root_panel_inside_tabbedPane.setLayout(new GridLayout(7,1));
            root_panel_inside_tabbedPane.setBorder( new javax.swing.border.LineBorder( 
                                  (new Color(150,150,150)), 3 ) );
        
            
            JPanel pan5 = new JPanel();
            pan5.setLayout(null);
            g_login = new JButton("  Login  ");
            g_cancel = new JButton("  Cancel  ");
            pan5.add(g_login);
            pan5.add(g_cancel);
            Insets insets = pan5.getInsets();
            g_login.setBounds(330 + insets.left, insets.top, 90, 30);
            g_cancel.setBounds(430 + insets.left, insets.top, 90, 30);
            g_root.add(pan5);
            
            g_login.addActionListener(this);
            g_cancel.addActionListener(this);

            this.getContentPane().add( g_root );
            this.setBounds(100,100,530,325);
                   
            JPanel pan11 = new JPanel();
            g_serverNameLabel = new JLabel("         Not A User Yet? Sign Up Below!  ");
            g_serverNameLabel.setFont( new Font("Monospaced", Font.PLAIN, 12) );
           // g_serverNameText = new JTextField(45);
            //g_serverNameText.setFont( new Font("Monospaced", Font.BOLD, 12) );
           pan11.add( g_serverNameLabel );
           //p/an11.add( g_serverNameText );
            //g_serverNameText.addKeyListener( this );
            root_panel_inside_tabbedPane.add(new JPanel());
            root_panel_inside_tabbedPane.add(pan11);
            
            createSignUpWindow(root_panel_inside_tabbedPane, insets);


            JSeparator sep1 = new JSeparator();
            g_root.add(sep1);
    		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		Dimension d = new Dimension(650, 650);
    		this.setSize(d);
    		
         
            this.setVisible(true);
       }

	/**
	 * @param root_panel_inside_tabbedPane
	 * @param insets
	 */
	private void createSignUpWindow(JPanel root_panel_inside_tabbedPane,
			Insets insets) {
		JPanel pan12 = new JPanel();
		g_contextLabel = new JLabel("Full Name:  ");
		g_contextLabel.setFont( new Font("Monospaced", Font.PLAIN, 12) );
		g_contextText = new JTextField(45);
		g_contextText.setFont( new Font("Monospaced", Font.BOLD, 12) );

		pan12.add( g_contextLabel );
		pan12.add( g_contextText );
		g_contextText.setFont( new Font("Monospaced", Font.BOLD, 12) );

		g_contextText.addKeyListener( this );
		root_panel_inside_tabbedPane.add(pan12);
		//pan11.add(pan12);
		
		
		
		JPanel pan13 = new JPanel();
		g_contextLabel2 = new JLabel("    Grade:  ");
		g_contextLabel2.setFont( new Font("Monospaced", Font.PLAIN, 12) );
		g_contextText2 = new JTextField(45);
		g_contextText2.setFont( new Font("Monospaced", Font.BOLD, 12) );

		pan13.add( g_contextLabel2 );
		pan13.add( g_contextText2 );
		g_contextText2.setFont( new Font("Monospaced", Font.BOLD, 12) );

		g_contextText2.addKeyListener( this );
		root_panel_inside_tabbedPane.add(pan13);
         // pan11.add(pan12);
		
		
		JPanel pan14 = new JPanel();
		g_contextLabel3 = new JLabel("User Name:  ");
		g_contextLabel3.setFont( new Font("Monospaced", Font.PLAIN, 12) );
		g_contextText3 = new JTextField(45);
		g_contextText3.setFont( new Font("Monospaced", Font.BOLD, 12) );

		pan14.add( g_contextLabel3 );
		pan14.add( g_contextText3 );
		g_contextText3.setFont( new Font("Monospaced", Font.BOLD, 12) );

		g_contextText3.addKeyListener( this );
		root_panel_inside_tabbedPane.add(pan14);
         // pan14.add(pan13);
		
		
		JPanel pan8 = new JPanel();
		pan8.setLayout(null);
		g_submit = new JButton("  Submit  ");
		pan8.add(g_submit);
		Insets insets2 = pan8.getInsets();
		g_submit.setBounds(430 + insets.left, insets.top, 90, 30);
		root_panel_inside_tabbedPane.add(pan8);
		g_submit.addActionListener(this);

		g_tabbedPane.addTab("Sign Up", null, root_panel_inside_tabbedPane, "");
		g_root.add( g_tabbedPane );
		
		
	}
       
/*       public void actionPerformed(ActionEvent evt)
       {
           if(evt.getSource() == g_login) 
             this.checkFields();
           if(evt.getSource() == g_cancel)
             System.exit(0);
       }*/

       public void keyTyped(KeyEvent e) {}
       public void keyReleased(KeyEvent e) {}
       public void keyPressed(KeyEvent evt)
       {
            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
              checkFields();
       }

       private void checkFields()
       {
             m_user = g_userText.getText();
             m_grade = new String(g_gradeText.getPassword());
            // m_serverName = g_serverNameText.getText();
             m_context = g_contextText.getText();

             if((m_user == null) || (m_user.equals(""))) {
               JOptionPane.showMessageDialog(this, "Enter the User Name", "Error", 
                                             JOptionPane.ERROR_MESSAGE);
               return;
             }
             else if(m_grade == null) {
                    JOptionPane.showMessageDialog(this, "Enter the Grade", "Error", 
                                                  JOptionPane.ERROR_MESSAGE);
                   return;
             }
             else if((m_serverName == null) || (m_serverName.equals(""))) {
                    JOptionPane.showMessageDialog(this, "Enter the User Name", "Error", 
                                                  JOptionPane.ERROR_MESSAGE);
                    return;
             }
             
      }


     
    
       public static void main(String[] args) {
      //
      //ry {
      //UIManager.setLookAndFeel("com.sun.java.swing.plaf.ibm.IBMLookAndFeel");
      //catch(Exception e) {
      //     e.printStackTrace();
      //
    	   
            Login l = new Login("Chemistry Passport Program Login Page");
            l.addWindowListener( new WindowAdapter() {
                                       public void windowClosing(WindowEvent e) {
                                          System.exit(0);
                                       }
                                     }
                                );
      
       }
       
       public void actionPerformed(ActionEvent evt)
       {
           /*String fullName = null;
           fullName = (String) g_combo.getSelectedItem();
           this.hide();
           return;*/
     	  
     	  if(evt.getSource().equals(g_submit)){
     		  
     		  System.out.println("Clicked on submit");
     		  
     		  new DatabaseCreater(g_contextText, g_contextText2, g_contextText3) ;
     	  }
       }
}


         
/*class ChoiceDialog extends JDialog implements ActionListener{
    
      private Login owner = null;
      private JComboBox g_combo = null;
      private JButton g_ok = null;

      ChoiceDialog(String[] users, Frame owner)
      {
         super(owner, "Choose UserName", true);
         this.owner = (Login) owner;

         JPanel pan = new JPanel();
         pan.setLayout( new BoxLayout(pan,BoxLayout.Y_AXIS) ); 
         
         JLabel lab = new JLabel("Multiple Entries Found : Choose UserName");
         lab.setFont( new Font("Halvetica", Font.BOLD, 16) );
         //lab.setForeground( new Color(250, 100, 100) );
         lab.setAlignmentX(Component.CENTER_ALIGNMENT);

         g_combo = new JComboBox( users );
         g_combo.setFont( new Font("Monospaced", Font.BOLD, 12) );
         g_combo.setEnabled( true );
         g_combo.setAlignmentX(Component.CENTER_ALIGNMENT);
         g_combo.setBorder( new javax.swing.border.LineBorder( 
                                  (new Color(120,120,120)), 3 ) );

         JSeparator sep = new JSeparator();

         pan.add( lab );
         pan.add( g_combo );
         pan.add( sep );

         g_ok = new JButton("     Login     ");
         g_ok.setAlignmentX(Component.CENTER_ALIGNMENT);
         g_ok.setFont( new Font("Halvetica", Font.BOLD, 16) );
         pan.add( g_ok );
         g_ok.addActionListener(this);
   
         this.getContentPane().add(pan);
         this.setBounds(150,150,450,150);
         this.setVisible(true);
      }
      
     
}*/
         