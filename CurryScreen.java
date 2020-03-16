/* [CurryScreen.java]
 * A program that displays information about Stephen Curry
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

//Keyboard imports

//Mouse imports


class CurryScreen extends JFrame { 
  private static final long serialVersionUID = 1L;
  Image curry= Toolkit.getDefaultToolkit().getImage("curry.gif");
  int maxX = 1366;
  int maxY = 768;
  static double x, y;
  static GameAreaPanel gamePanel;
  JFrame thisFrame, startFrame;
  
  //Constructor - this runs first
  CurryScreen(JFrame startingFrame) { 
    
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
    JButton previous = new JButton("Kevin Durant");
    previous.addActionListener(new PreviousButtonListener());
    previous.setFont(font);
    mainPanel.add(previous);
    JButton start = new JButton("Starting screen");
    start.addActionListener(new StartButtonListener());
    start.setFont(font);
    mainPanel.add(start);
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
  
  // Inner class for drawing
  private class GameAreaPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public void paintComponent(Graphics g) {   
      super.paintComponent(g); //required
      setDoubleBuffered(true); 
      g.setColor(Color.RED);
      g.setFont(new Font("Press Start 2P", Font.BOLD, 35));
      g.drawImage(curry,0,60,522,500,this);
      
      g.setColor(Color.BLACK);
      g.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
      g.drawString("(Health: 160   Base Hit Chance: 120)",maxX/2-320,80); 
      g.setFont(new Font("Press Start 2P", Font.PLAIN, 14));
      g.drawString("FLOATER - deal 14 damage, guaranteed hit.",maxX/2-220,180); 
      g.drawString("DEEP THREE - deal 20 damage, medium hit chance",maxX/2-220,240); 
      
      g.drawString("CLUTCH - if enemy has less than 50 health, gain 50 health and hit chance.",maxX/2-220,300);
      g.drawString("else, gain 5 health",maxX/2+30,320);
      g.drawString("PLAYOFF MODE- if Curry has more than 50 health,",maxX/2-220,380); 
      g.drawString("lose 20 health but gain 40 hit chance.",maxX/2+30,400);
      
    }
  }
  
  //inner class for start screen button press
  class StartButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
      thisFrame.dispose();
      startFrame.setVisible(true);
    }
  }
  
  //inner class for previous button press
  class PreviousButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
      thisFrame.dispose();
      new KDScreen(startFrame);
    }
  }
}