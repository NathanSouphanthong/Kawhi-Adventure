/* [Instruction.java]
 * A program that displays information about the game
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


class Instruction extends JFrame { 
  private static final long serialVersionUID = 1L;
  Image battleScreenPic=Toolkit.getDefaultToolkit().getImage("battleS.png");
  Image arrow=Toolkit.getDefaultToolkit().getImage("arrows.jpg");
  int maxX = 1366;
  int maxY = 768;
  static GameAreaPanel gamePanel;
  JFrame thisFrame, startFrame;
  
  //Constructor - this runs first
  Instruction(JFrame startingFrame) { 
    
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
    JButton start = new JButton("Starting screen");
    start.addActionListener(new StartButtonListener());
    start.setFont(font);
    mainPanel.add(start);
    JButton next = new JButton("Kawhi Leonard");
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
  
  // Inner class for the drawing
  private class GameAreaPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public void paintComponent(Graphics g) {   
      super.paintComponent(g);
      setDoubleBuffered(true); 
      g.setColor(Color.RED);
      g.setFont(new Font("Press Start 2P", Font.BOLD, 35));
      g.drawString("WELCOME TO KAWHI's ADVANTURE",maxX/2-500,50);
      
      g.setColor(Color.BLACK);
      g.setFont(new Font("Press Start 2P", Font.BOLD, 25));
      g.drawString("In this game, you are Kawhi Leonard",20,120);
      g.drawString("and your goal is to eliminate all opponent players.",20,150);
      g.setFont(new Font("Press Start 2P", Font.PLAIN, 16));
      g.drawString("Every character has health and hit chance, both could be",20,200);
      g.drawString("affected by another character's abilities. If health reaches 0",20,230);
      g.drawString("the player is eliminated. (you can store a defeated character to be used later)",20,260);
      g.drawString("In the world view, you can walk around,",20,310);
      g.drawString("you will enter a battle upon collsion with another player",20,340);
      g.setColor(Color.RED);
      g.setFont(new Font("Press Start 2P", Font.PLAIN, 12));
      g.drawString("BATTLE SCREEN",maxX/2-75,360);
      g.drawImage(battleScreenPic,maxX/2-130,370,260,130,this);
      g.drawImage(arrow,maxX/2+260,370,260,130,this);
      
      g.setColor(Color.BLACK);
      g.drawString("Battles are turn-based. ONCE YOU ENTER THE BATTLE, THERE IS NO GOING BACK.",20,530);
      g.drawString("ITS WIN OR GO HOME",20,560);
    }
  }
  
  //inner class for start screen button press
  class StartButtonListener implements ActionListener { 
    public void actionPerformed(ActionEvent event)  {  
      thisFrame.dispose();
      startFrame.setVisible(true);
    }
  }
  
  //inner class for next button press
  class NextButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event)  {  
      thisFrame.dispose();
      new KawhiScreen(startFrame);
    }
  }    
}