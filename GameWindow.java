/* [Coach.java]
 * Game window graphics based on the map
 * June 12th, 2019
 * @author: Nathan Souphanthong
 */


//imports 
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Rectangle;



class GameWindow extends JFrame{ 
  private static final long serialVersionUID = 1L;
  //allow the frame to be accessed statically 
  static JFrame startFrame;

  //constructor setting dimensions and frame settings
  public GameWindow(){ 
    setTitle("Ball Planet"); 
    startFrame=this;
    setSize(1920,1080); 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().add(new GamePanel()); 
    setVisible(true); 
    setPreferredSize(new Dimension(1920,1080));
    setResizable(true);
  }
  
  
    /** --------- INNER CLASSES ------------- **/
  //innter class to draw
  static class GamePanel extends JPanel implements KeyListener{ 
    private static final long serialVersionUID = 1L;
    
    //intialize map, sprite, and kawhi character to represent sprite
    Map map; 
    Player player;
    Kawhi kawhi = new Kawhi(100,200,800,1,1);

    
    //constructor
    GamePanel(){ 
      setSize(1920,1080); 
      setPreferredSize(new Dimension(1920,1080));
      addKeyListener(this); 
      setFocusable(true); 
      requestFocusInWindow();
      player = new Player(); 
      map = new Map(1920,1080,player); 
      map.createPlayers();
    }
    
    //paint component
    public void paintComponent(Graphics g){ 
      super.paintComponent(g); 
      map.draw(g); 
      player.draw(g);
      //checking for collisions based on what is visible on the map and loading battle screen
      if(map.lebronJamesOn == true && map.startPlayerOn == false && map.benchPlayerOn == false && 
         map.coachOn == false && map.kevinDurantOn == false && player.playerBox.boundingBox.intersects(map.lebronJames.lebronBox.boundingBox)){ 
        System.out.println("Collide");
        new BattleScreen(startFrame, kawhi, map.lebronJames);
        startFrame.setVisible(false);
        map.lebronJamesOn = false;
        map.lebronJames.lebronBox.boundingBox = new Rectangle(0,0,0,0); 
      }else if (map.startPlayerOn == true && player.playerBox.boundingBox.intersects(map.startPlayer.startPlayerBox.boundingBox)){ 
        System.out.println("Collide");
        new BattleScreen(startFrame, kawhi, map.startPlayer); 
        startFrame.setVisible(false);
        map.startPlayerOn = false;
        map.startPlayer.startPlayerBox.boundingBox = new Rectangle(0,0,0,0); 
      }else if (map.benchPlayerOn == true && player.playerBox.boundingBox.intersects(map.benchPlayer.benchPlayerBox.boundingBox)){ 
        System.out.println("Collide");
        new BattleScreen(startFrame, kawhi, map.benchPlayer); 
        startFrame.setVisible(false);
        map.benchPlayerOn = false;
        map.benchPlayer.benchPlayerBox.boundingBox = new Rectangle(0,0,0,0); 
      }else if (map.coachOn == true && player.playerBox.boundingBox.intersects(map.coach.coachBox.boundingBox)){ 
        System.out.println("Collide");
        new BattleScreen(startFrame, kawhi, map.coach); 
        startFrame.setVisible(false);
        map.coachOn = false;
        map.coach.coachBox.boundingBox = new Rectangle(0,0,0,0); 
      }else if (map.startPlayerOn == false && map.benchPlayerOn == false && map.coachOn == false && 
                map.kevinDurantOn == true && player.playerBox.boundingBox.intersects(map.kevinDurant.durantBox.boundingBox)){ 
        System.out.println("Collide");
        new BattleScreen(startFrame, kawhi, map.kevinDurant); 
        startFrame.setVisible(false);
        map.kevinDurantOn = false;
        map.kevinDurant.durantBox.boundingBox = new Rectangle(0,0,0,0); 
      }else if (map.stephenCurryOn == true && map.startPlayerOn == false
                 && map.benchPlayerOn == false && map.coachOn == false && map.kevinDurantOn == false && 
                map.lebronJamesOn == false && player.playerBox.boundingBox.intersects(map.stephenCurry.stephenCurryBox.boundingBox)){ 
        System.out.println("Collide");
        new BattleScreen(startFrame, kawhi, map.stephenCurry); 
        startFrame.setVisible(false);
        map.stephenCurryOn = false;
        map.stephenCurry.stephenCurryBox.boundingBox = new Rectangle(0,0,0,0); 
      }
      //redraw frame everytime
      repaint(); 
      
    }
    
    //key listener for sprite movement
    public void keyTyped(KeyEvent e){ 
      if(e.getKeyChar() == 'a'){ 
        System.out.println("left"); 
        player.update("left"); 
      }else if(e.getKeyChar() == 's'){ 
        System.out.println("down"); 
        player.update("down"); 
      }else if (e.getKeyChar() == 'd'){ 
        System.out.println("right"); 
        player.update("right"); 
      }else if (e.getKeyChar() == 'w'){ 
        System.out.println("up");
        player.update("up"); 
      }
      
    }
    
    public void keyPressed(KeyEvent e){ 
      
    }
    
    public void keyReleased(KeyEvent e){ 
      
    }
    
  } //end of gamepanel
  
}//end of gamewindow