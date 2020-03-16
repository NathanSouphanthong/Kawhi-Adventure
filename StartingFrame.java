/* [StartingFrame.java]
 * A program that starts the entire game
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




class StartingFrame extends JFrame { 
  private static final long serialVersionUID = 1L;
  
  Image klaw= Toolkit.getDefaultToolkit().getImage("klaw.png");
  int maxX = 1366;
  int maxY = 768;
  JFrame thisFrame;
  static GameAreaPanel gamePanel;
  Kawhi kawhi = new Kawhi(80,80,60,1,1);
  LebronJames  bench = new LebronJames(80,80,80,1,1);

  //Constructor - this runs first
  StartingFrame() { 
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
    JButton startButton = new JButton("START");
    startButton.addActionListener(new StartButtonListener());
    startButton.setFont(font);
    JButton instructionButton = new JButton ("Instruction");
    instructionButton.addActionListener(new InstButtonListener());
    instructionButton.setFont(font);
    
    //Add all buttons to the panel according to layout
    buttonPanel.add(startButton);
    buttonPanel.add(instructionButton);
    
    //add the panel to the frame
    this.add(new GameAreaPanel());
    this.add(buttonPanel,BorderLayout.SOUTH);
    
    //Start the app
    this.requestFocusInWindow(); //make sure the frame has focus   
    
    this.setVisible(true);
    
    //Start the game loop in a separate thread
    Thread t = new Thread(); //start the gameLoop 
    t.start();
  }
  
  //inner class used to draw
  private class GameAreaPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public void paintComponent(Graphics g) {   
      super.paintComponent(g); //required
      setDoubleBuffered(true); 
      g.setFont (BallPlanet.customFont);
      g.drawString("KAWHI'S ADVENTURE", 350,100);
      g.drawImage(klaw,maxX/2-400,80,800,550,this); 
    }
  }
  
  //This is an inner class that is used to detect start button press
  class StartButtonListener implements ActionListener {  
    public void actionPerformed(ActionEvent event)  {  
      System.out.println("Starting new Game");
      thisFrame.setVisible(false);
      new GameWindow(); 
    }
  }
  
  //inner class used to detect instruction button press
  class InstButtonListener implements ActionListener {  
    public void actionPerformed(ActionEvent event)  {  
      System.out.println("Starting instruction");
      thisFrame.setVisible(false);
      new Instruction(thisFrame); 
    }
  }

}
