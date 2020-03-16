/* [WinFrame.java]
 * A program that displays at the end of winning the game. It asks user to replay or to quit the game
 * June 12th, 2019
 * @author: Jerry Jiao
 */

//Imports
import java.awt.Toolkit;
import java.awt.Graphics;


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;

import javax.swing.JPanel;
import java.awt.BorderLayout;


class WinFrame extends JFrame { 
  private static final long serialVersionUID = 1L;
  Image klaw= Toolkit.getDefaultToolkit().getImage("klaw.png");
  int maxX = 1366;
  int maxY = 768;
  JFrame thisFrame;
  static GameAreaPanel gamePanel;
  
  //Constructor - this runs first
  WinFrame() {
    super("Start Screen");
    this.thisFrame = this; //lol  
    
    gamePanel = new GameAreaPanel();
    //configure the window
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1366,768);  
    this.setResizable (false);
    
    //Create a Panel for stuff
    JPanel buttonPanel = new JPanel();
    GridLayout layout1 = new GridLayout(2,1);
    Font font = new Font("Press Start 2P", Font.PLAIN, 25);
    buttonPanel.setLayout(layout1);
    buttonPanel.setPreferredSize(new Dimension(100, 150));
    JButton restartButton = new JButton("RESTART");
    restartButton.addActionListener(new RestartButtonListener());
    restartButton.setFont(font);
    JButton quitButton = new JButton ("QUIT");
    quitButton.addActionListener(new EndButtonListener());
    quitButton.setFont(font);
    //Add all buttons to the Panel according to layout
    buttonPanel.add(restartButton);
    buttonPanel.add(quitButton);

    this.add(new GameAreaPanel());
    this.add(buttonPanel,BorderLayout.SOUTH);
    
    //Start the app
    this.requestFocusInWindow(); //make sure the frame has focus   
    
    this.setVisible(true);
    
    //Start the game loop in a separate thread
    Thread t = new Thread(); //start the gameLoop 
    t.start();
  }
  
  //inner class for drawing
  private class GameAreaPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public void paintComponent(Graphics g) {   
      super.paintComponent(g); //required
      setDoubleBuffered(true); 
      g.setFont (new Font("Press Start 2P", Font.BOLD, 60));
      g.drawString("YOU WONNNNNNNNNNNNN!!", 125,100);
      g.drawImage(klaw,maxX/2-200,330,400,200,this); 
    }
  }
  
  //This is an inner class that is used to detect restart button press
  class RestartButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
      System.out.println("Starting new Game");
      thisFrame.dispose();
      new StartingFrame(); //create a new FunkyFrame (another file that extends JFrame)
    }
  }
  
  //inner class used to detect quit button press
  class EndButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
      System.out.println("Starting instruction");
      thisFrame.dispose();
    }
  }

  
}