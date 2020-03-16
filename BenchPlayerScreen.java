/* [BenchPlayerScreen.java]
 * A program that displays information about the bench player
 * June 12th, 2019
 * @author: Jerry Jiao
 */

//imports 
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
  

class BenchPlayerScreen extends JFrame { 
  private static final long serialVersionUID = 1L;
  Image benchP= Toolkit.getDefaultToolkit().getImage("bench.gif");
int maxX = 1366;
    int maxY = 768;
  //class variable (non-static)
   static double x, y;
   static GameAreaPanel gamePanel;
  JFrame thisFrame, startFrame;
  
  //Constructor - this runs first
  BenchPlayerScreen(JFrame startingFrame) { 
    
    super("My Game"); 
    thisFrame = this;
    startFrame = startingFrame;
    
    // Set the frame to full screen 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1366,768);
   // this.setUndecorated(true);  //Set to true to remove title bar
   //frame.setResizable(false);


    
    //Set up the game panel (where we put our graphics)
    gamePanel = new GameAreaPanel();
    this.add(new GameAreaPanel());
    Font font = new Font("Press Start 2P", Font.PLAIN, 25);
    JPanel mainPanel = new JPanel();
    mainPanel.setPreferredSize(new Dimension(100, 150));
    JButton previous = new JButton("Kawhi");
    previous.addActionListener(new PreviousButtonListener());
    previous.setFont(font);
    mainPanel.add(previous);
    JButton start = new JButton("Starting screen");
    start.addActionListener(new StartButtonListener());
    start.setFont(font);
    mainPanel.add(start);
    JButton next = new JButton("Starting Player");
    next.addActionListener(new NextButtonListener());
    next.setFont(font);
    mainPanel.add(next);
    GridLayout layout1 = new GridLayout(1,3);
    mainPanel.setLayout(layout1);
    thisFrame.add(mainPanel,BorderLayout.SOUTH);
    
      
    this.requestFocusInWindow(); //make sure the frame has focus   
    
    this.setVisible(true);
  
    //Start the game loop in a separate thread
    Thread t = new Thread(); //start the gameLoop 
    t.start();
   
  } //End of Constructor

  //the main gameloop - this is where the game state is updated
  
  /** --------- INNER CLASSES ------------- **/
  
  // Inner class for the the game area - This is where all the drawing of the screen occurs
  private class GameAreaPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public void paintComponent(Graphics g) {   
       super.paintComponent(g); //required
       setDoubleBuffered(true); 
       g.setColor(Color.RED);
       g.setFont(new Font("Press Start 2P", Font.BOLD, 35));//There are many graphics commands that Java can use
       g.drawString("BENCH PLAYER",maxX/2-220,50); 
       g.drawImage(benchP,0,60,522,500,this);
       
       g.setColor(Color.BLACK);
       g.setFont(new Font("Press Start 2P", Font.PLAIN, 20));//There are many graphics commands that Java can use
       g.drawString("(Health: 60   Base Hit Chance: 40)",maxX/2-320,80); 
       g.setFont(new Font("Press Start 2P", Font.PLAIN, 14));
       g.drawString("POUR WATER - deal 5 damage, medium hit chance.",maxX/2-220,180); 
      
       g.drawString("SIT - gain 2 health, medium hit chance.",maxX/2-220,320);
      
    }
  }
  
  class StartButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
    thisFrame.dispose();
    startFrame.setVisible(true);
    }
  }
  class NextButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
    thisFrame.dispose();
    new StartPlayerScreen(startFrame);
    }
  }
  class PreviousButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
    thisFrame.dispose();
    new KawhiScreen(startFrame);
    }
  }
  // -----------  Inner class for the keyboard listener - this detects key presses and runs the corresponding code
  //end of mouselistener
    
}