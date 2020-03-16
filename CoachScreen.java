/* [CoachScreen.java]
 * A program that displays information about coach
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



class CoachScreen extends JFrame { 
  private static final long serialVersionUID = 1L;
  Image coach=Toolkit.getDefaultToolkit().getImage("coach.gif");
  int maxX = 1366;
  int maxY = 768;
  
  static GameAreaPanel gamePanel;
  JFrame thisFrame, startFrame;
  
  //Constructor - this runs first
  CoachScreen (JFrame startingFrame) { 
    
    super("My Game"); 
    thisFrame = this;
    startFrame = startingFrame;
    

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1366,768);
    this.setResizable(false);
    
    //Set up the game panel (where we put our graphics)
    gamePanel = new GameAreaPanel();
    this.add(new GameAreaPanel());
    Font font = new Font("Press Start 2P", Font.PLAIN, 25);
    JPanel mainPanel = new JPanel();
    mainPanel.setPreferredSize(new Dimension(100, 150));
    JButton previous = new JButton("Starting Player");
    previous.addActionListener(new PreviousButtonListener());
    previous.setFont(font);
    mainPanel.add(previous);
    JButton start = new JButton("Starting screen");
    start.addActionListener(new StartButtonListener());
    start.setFont(font);
    mainPanel.add(start);
    JButton next = new JButton("Lebron James");
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
  
  /** --------- INNER CLASSES ------------- **/
  
  // Inner class for drawings
  private class GameAreaPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public void paintComponent(Graphics g) {   
      super.paintComponent(g); 
      setDoubleBuffered(true); 
      g.setColor(Color.RED);
      g.setFont(new Font("Press Start 2P", Font.BOLD, 35));
      g.drawString("COACH",maxX/2-100,50); 
      g.drawImage(coach,0,60,522,500,this);
      
      g.setColor(Color.BLACK);
      g.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
      g.drawString("(Health: 120   Base Hit Chance: 60)",maxX/2-320,80); 
      g.setFont(new Font("Press Start 2P", Font.PLAIN, 14));
      g.drawString("SCREAM - decrease enemy's hit chance by 10, high hit chance.",maxX/2-220,180); 
      g.drawString("WHINE - increase hit chance by 10, high hit chance",maxX/2-220,240); 
      g.drawString("BENCH - gain 10 health, high hit chance.",maxX/2-220,300);
      g.drawString("SEND TO D-LEAGUE - one-shot enemy player, very low hit chance.",maxX/2-220,360); 
      
    }
  }
  
  //inner class for start screen button press
  class StartButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
      thisFrame.dispose();
      startFrame.setVisible(true);
    }
  }
  
  //inner class for next screen button press
  class NextButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
      thisFrame.dispose();
      new LBJScreen(startFrame);
    }
  }
  
  //inner class for previous screen button press
  class PreviousButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
      thisFrame.dispose();
      new StartPlayerScreen(startFrame);
    }
  }    
}