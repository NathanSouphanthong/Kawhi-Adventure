/* [KawhiScreen.java]
 * A program that displays information about Kawhi
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


class KawhiScreen extends JFrame {  
  private static final long serialVersionUID = 1L;
  Image klaw= Toolkit.getDefaultToolkit().getImage("kawhi.gif");
  int maxX = 1366;
  int maxY = 768;
  static GameAreaPanel gamePanel;
  JFrame thisFrame, startFrame;
  
  //Constructor - this runs first
  KawhiScreen(JFrame startingFrame) { 
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
    JButton previous = new JButton("Instruction");
    previous.addActionListener(new PreviousButtonListener());
    previous.setFont(font);
    mainPanel.add(previous);
    JButton start = new JButton("Starting screen");
    start.addActionListener(new StartButtonListener());
    start.setFont(font);
    mainPanel.add(start);
    JButton next = new JButton("Bench Player");
    next.addActionListener(new NextButtonListener());
    next.setFont(font);
    mainPanel.add(next);
    GridLayout layout1 = new GridLayout(1,2);
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
      g.drawString("KAWHI LEONARD",maxX/2-220,50); 
      g.drawImage(klaw,0,60,522,500,this);
      
      g.setColor(Color.BLACK);
      g.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
      g.drawString("(Health: 200   Base Hit Chance: 80)",maxX/2-320,80); 
      g.setFont(new Font("Press Start 2P", Font.PLAIN, 14));
      g.drawString("SCORE - deal 15 damage, high hit chance.",maxX/2-220,180); 
      g.drawString("SACRIFICE - use a stored character to boost your attributes",maxX/2-220,240); 
      g.drawString(", amount depends on class of character. Guaranteed hit",maxX/2-100,260);
      g.drawString("FUN GUY - gain 15 health, high hit chance.",maxX/2-220,320);
      g.drawString("CARRY THE NORTH - put enemy player to sleep.",maxX/2-220,380); 
      g.drawString("If Kawhi has more than 50 health",maxX/2+30,400);
      g.drawString("deal 20 damage but lose 10 health",maxX/2+30,420);
      g.drawString("otherwise, deal 40 damage but lose 15 health.",maxX/2+30,440);
      g.drawString("Low hit chance.",maxX/2+30,460);
    }
  }
  
  //inner class for start screen button press
  class StartButtonListener implements ActionListener { 
    public void actionPerformed(ActionEvent event)  {  
      thisFrame.dispose();
      startFrame.setVisible(true);
    }
  }
  
  //inner class for next screen button press
  class NextButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event)  {  
      thisFrame.dispose();
      new BenchPlayerScreen(startFrame);
    }
  }
  
  //inner class for previous screen button press
  class PreviousButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
      thisFrame.dispose();
      new Instruction(startFrame);
    }
  }
}