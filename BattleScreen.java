/* [BattleScreen.java]
 * A program that runs the battle
 * June 12th, 2019
 * @author: Jerry Jiao
 */

//Graphics & GUI imports
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

class BattleScreen extends JFrame{ 
  private static final long serialVersionUID = 1L;
  //images
  Image court= Toolkit.getDefaultToolkit().getImage("ballNet.png");
  Image floor =Toolkit.getDefaultToolkit().getImage("ballFloor.png"); 
  Image klaw= Toolkit.getDefaultToolkit().getImage("kawhi.gif");
  Image curry= Toolkit.getDefaultToolkit().getImage("curry.gif");
  Image lebron= Toolkit.getDefaultToolkit().getImage("lebron.gif");
  Image kevin= Toolkit.getDefaultToolkit().getImage("kd.gif");
  Image benchP= Toolkit.getDefaultToolkit().getImage("bench.gif");
  Image startP=Toolkit.getDefaultToolkit().getImage("start.gif");
  Image face= Toolkit.getDefaultToolkit().getImage("face.png");
  Image coach=Toolkit.getDefaultToolkit().getImage("coach.gif");
  
  //boolean variables
  boolean notAdded = true;
  boolean playerSleep = false;
  boolean npcSleep = false;
  boolean npcGraphic = false;
  boolean scoreGraphic=false;
  boolean carryGraphic=false;
  boolean funGuyGraphic = false;
  boolean sacrificeGraphic = false;
  boolean missGraphic = false;
  boolean emptyGraphic = false;
  boolean win = false;
  boolean lose = false;
  
  //class variables
  double time;
  int playerHP, npcHP;
  static GameAreaPanel gamePanel;
  JFrame thisFrame, startFrame;
  Kawhi playable;
  Character npc;
  int maxX = 1366;
  int maxY = 768;
  
  //Constructor - this runs first
  BattleScreen(JFrame startingFrame, Kawhi playable, Character npc) { 
    super("My Game"); 
    this.playable=playable;
    this.npc=npc;
    playerHP = playable.getHealth();
    npcHP = npc.getHealth();
    // Set the frame to full screen 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1366,768);
    this.setResizable(false);
    
    this.startFrame=startingFrame;
    this.thisFrame = this; //lol 
    
    //Set up the game panel (where we put our graphics)
    gamePanel = new GameAreaPanel();
    this.add(new GameAreaPanel());
    
    this.requestFocusInWindow(); //make sure the frame has focus   
    this.setVisible(true);
    
    //making buttons and panels
    Font font = new Font("Press Start 2P", Font.PLAIN, 58);
    JPanel mainPanel = new JPanel();
    mainPanel.setPreferredSize(new Dimension(maxX, maxY/4));
    JButton scoreB = new JButton("Score");
    scoreB.addActionListener(new ScoreButtonListener());
    scoreB.setFont(font);
    JButton funGuy = new JButton("Fun Guy");
    funGuy.addActionListener(new FunGuyButtonListener());
    funGuy.setFont(font);
    JButton sacrificeB = new JButton ("Sacrifice");
    sacrificeB.addActionListener(new SacrificeButtonListener());
    sacrificeB.setFont(font);
    JButton carryB = new JButton ("Carry The North");
    carryB.addActionListener(new CarryButtonListener());
    font = new Font("Press Start 2P", Font.PLAIN, 28);
    carryB.setFont(font);
    mainPanel.add(scoreB);
    GridLayout layout1 = new GridLayout(2,2);
    mainPanel.setLayout(layout1);
    thisFrame.add(mainPanel,BorderLayout.SOUTH);
    mainPanel.add(sacrificeB);
    mainPanel.add(carryB);
    mainPanel.add(funGuy);
    //Start the game loop in a separate thread
    Thread t = new Thread(new Runnable() { public void run() { animate(); }}); //start the gameLoop 
    t.start(); 
  } //End of Constructor
  
  //the main gameloop - this is where the game state is updated
  public void animate() {
    while(win==false && lose==false){
      this.repaint();
      if( npc.getHealth()<=0){
        win=true;
      }else if (playable.getHealth()<=0){
        lose = true;
      }
    }    
  }
  
  /** --------- INNER CLASSES ------------- **/
  
  // Inner class for the the game area - This is where all the drawing of the screen occurs
  private class GameAreaPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public void paintComponent(Graphics g) {   
      super.paintComponent(g); //required
      setDoubleBuffered(true); 
      g.setColor(Color.WHITE); //There are many graphics commands that Java can use
      g.setFont (new Font("Press Start 2P", Font.PLAIN, 30));
      g.fillRect(0, 0, maxX, maxY);
      
      //draw characters
      g.drawImage(floor,0,maxY/2-20,maxX,maxY/4+20,this);
      g.drawImage(court,maxX/2-120,0,240,220,this);
      g.drawImage(klaw,820,210,375,255,this);
      if (npc instanceof StephenCurry){
        g.drawImage(curry,100,210,375,255,this);
      }else if (npc instanceof LebronJames){
        g.drawImage(lebron,100,210,375,255,this);
      }else if (npc instanceof BenchPlayer){
        g.drawImage(benchP,100,210,375,255,this);
      }else if (npc instanceof StartPlayer){
        g.drawImage(startP,100,210,375,255,this);
      }else if (npc instanceof KevinDurant){
        g.drawImage(kevin,100,210,375,255,this);
      }else if (npc instanceof Coach){
        g.drawImage(coach,100,210,375,255,this);
      }
      //draw health
      g.setColor(Color.BLUE);
      g.drawString("Health: "+npcHP+"/"+npc.getMaxHealth(),100,180);
      g.drawString("Health: "+playerHP+"/"+playable.getMaxHealth(),850,180);
      
      //if you eliminate the other character, go to win frame
      if (win){
        g.setColor(Color.BLACK);
        g.fillRect(maxX/2-400,maxY/2-200,800,400);
        g.setColor(Color.RED);
        g.drawString("YOU WON THE BATTLE!",420,350);
        this.repaint();
        //
        for (int i=0; i<3 && playable.getArr()==null && notAdded==true;i++){
          playable.addArr(i,npc);
          notAdded=false;
        }
        notAdded = true;
        if (System.nanoTime() / 1000000000.0 - time >2){
          thisFrame.dispose();
          if (npc instanceof StephenCurry){
            startFrame.dispose();
            new WinFrame();
          }else{
            startFrame.setVisible(true);
          }
        }
      }
      
      //if you die, go to lose frame
      if (lose){
        thisFrame.dispose();
        startFrame.dispose();
        new LoseFrame();
      }
      
      //display npc attack graphics
      if (npcGraphic){
        g.setColor(Color.BLACK);
        g.drawString("i attack",150,150);
        playerHP = playable.getHealth();
        npcHP = npc.getHealth();
        this.repaint();
        if (playerSleep){
          g.setColor(Color.WHITE);
          g.fillRect(850,100,600,50);
          g.setColor(Color.BLACK);
          g.drawString("I sleep",850,150);
          this.repaint();
          //display user sleep graphics
          if (playable.getSleep()){
            playable.setSleepFalse();
            if (npc.getSleep()==false &&npc.getHealth()>0){
              npcAction(npc,playable);
              playerSleep=true;
            }else{
              npcSleep=true;
              npc.setSleepFalse();
            }
          }
          if (System.nanoTime() / 1000000000.0 - time> 2){
            playerSleep = false;
          }
        }
        if (System.nanoTime() / 1000000000.0 - time>2){
          npcGraphic = false;
        }
      }
      
      //display score graphics
      if (scoreGraphic){
        g.setColor(Color.BLACK);
        g.drawString("...",850,150);
        this.repaint();
        if (System.nanoTime() / 1000000000.0 - time> 1){
          scoreGraphic = false;
          if (!npc.getSleep() &&npc.getHealth()>0){
            npcGraphic = true;
          }
        }
      }
      
      //display funGuy graphics
      if (funGuyGraphic){
        g.setColor(Color.BLACK);
        g.drawString("HEheEHhEhhEEhhEHhEHhhEEHhhEEhEhEH!",850,150);
        this.repaint();
        if (System.nanoTime() / 1000000000.0 - time> 1){
          funGuyGraphic = false;
          if (!npc.getSleep() && npc.getHealth()>0){
            npcGraphic = true;
          }
        }
      }
      
      //display sacrifice graphics
      if (sacrificeGraphic){
        g.setColor(Color.BLACK);
        g.drawString("King In The North!",850,150);
        this.repaint();
        if (System.nanoTime() / 1000000000.0 - time> 1){
          sacrificeGraphic = false;
          if (!npc.getSleep() && npc.getHealth()>0){
            npcGraphic = true;
          }
        }
      }
      
      //display this graphic if character array is empty
      if (emptyGraphic){
        g.setColor(Color.BLACK);
        g.setFont (new Font("Press Start 2P", Font.PLAIN, 16));
        g.drawString("Your character slot is empty!",850,150);
        this.repaint();
        if (System.nanoTime() / 1000000000.0 - time>1){
          emptyGraphic = false;
        }
      }
      
      //display this graphic if an ability misses
      if (missGraphic){
        g.setColor(Color.BLACK);
        g.drawString("YOU MISSED!!",850,150);
        this.repaint();
        if (System.nanoTime() / 1000000000.0 - time> 1){
          missGraphic = false;
          if (!npc.getSleep() &&npc.getHealth()>0){
            npcGraphic = true;
          }
        }
      }
      
      //display carry graphics
      if (carryGraphic){
        g.setColor(Color.BLACK);
        g.drawString("yes!",850,150);
        this.repaint();
        if (System.nanoTime() / 1000000000.0 - time> 1){
          carryGraphic = false;
        }
      }
      
      //display npc sleep graphics if npc is asleep
      if (npcSleep){
        g.setColor(Color.BLACK);
        g.drawString("I sleep",150,150);
        this.repaint();
        if (System.nanoTime() / 1000000000.0 - time> 1){
          npcSleep=false;
        }
      }
    }
  }
  
  //inner class for score button press
  class ScoreButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
      if (System.nanoTime() / 1000000000.0 - time>2){
        time = System.nanoTime() / 1000000000.0;
        if ( playable.score(npc)){
          playerHP = playable.getHealth();
          npcHP = npc.getHealth();
          scoreGraphic = true;
        }else{
          missGraphic = true;
        }
        //npc action
        if (npc.getHealth()>0){
        if (npc.getSleep()==false){
          npcAction(npc,playable);
        }else{
          npcSleep=true;
          npc.setSleepFalse();
        }
        }
      }
    }
  }
  
  //inner class for funGuy button press
  class FunGuyButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {
      if (System.nanoTime() / 1000000000.0 - time>2 ){
        time = System.nanoTime() / 1000000000.0;
        if ( playable.funGuy()){
          playerHP = playable.getHealth();
          npcHP = npc.getHealth();
          funGuyGraphic = true;
        }else{
          missGraphic = true;
        }
        //npc action
        if (npc.getHealth()>0){
        if (npc.getSleep()==false ){
          npcAction(npc,playable);
        }else{
          npcSleep=true;
          npc.setSleepFalse();
        }
        }
      }
    }
  }
  
  //inner class for sacrifice button press
  class SacrificeButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {
      if (System.nanoTime() / 1000000000.0 - time>2){
        time = System.nanoTime() / 1000000000.0;
        if(playable.sacrifice(playable.getArr())){
          playerHP = playable.getHealth();
          npcHP = npc.getHealth();
          sacrificeGraphic = true;
          if(npc.getHealth()>0){
          if (npc.getSleep()==false){
            npcAction(npc,playable);
          }else{
            npcSleep=true;
            npc.setSleepFalse();
          }
          }
        }else{
          emptyGraphic = true;
        }   
      }
    }
  }
  
  //inner class for carry button press
  class CarryButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
      if (System.nanoTime()/1000000000.0 - time>2){
        time = System.nanoTime() / 1000000000.0;
        if (playable.carryTheNorth(npc)){
          playerHP = playable.getHealth();
          npcHP = npc.getHealth();
          carryGraphic = true;
          npcSleep=true;
          npc.setSleepFalse();        
        }else{
          missGraphic = true;
          //npc action
          if (npc.getHealth()>0){
          if (npc.getSleep()==false){
            npcAction(npc,playable);
          }else{
            npcSleep=true;
            npc.setSleepFalse();
          }
        }
        }
      }
    }
  }
  
  /**
   * npc randomly uses an ability
   * @param npc character
   * @param user-controlled kawhi
   */
  public void npcAction(Character npc, Kawhi kawhi){
    int action = (int) (Math.random()*4);
    //  npcGraphic = true;
    
    if(npc instanceof BenchPlayer){
      if (action < 2){
        ((BenchPlayer)npc).pourWater(kawhi);
      }else{
        ((BenchPlayer)npc).sit();
      }
    }else if (npc instanceof StartPlayer){
      if (action==0){
        ((StartPlayer)npc).shoot(kawhi);
      }else if (action==1){
        ((StartPlayer)npc).dunk(kawhi);
      }else if (action==2){
        ((StartPlayer)npc).defense(kawhi);
      }else{
        ((StartPlayer)npc).pass();
      }
    }else if (npc instanceof Coach){
      if (action==0){
        ((Coach)npc).sendToDLeague(kawhi);
      }else if (action==1){
        ((Coach)npc).scream(kawhi);
      }else if (action==2){
        ((Coach)npc).bench();
      }else{
        ((Coach)npc).whine();
      }
    }else if (npc instanceof LebronJames){
      if (action==0){
        ((LebronJames)npc).theDecision(kawhi);
      }else if (action==1){
        ((LebronJames)npc).buzzerBeater(kawhi);
      }else if (action==2){
        ((LebronJames)npc).posterize(kawhi);
      }else{
        ((LebronJames)npc).jamToTrashMusic();
      }
    }else if (npc instanceof KevinDurant){
      if (action==0){
        ((KevinDurant)npc).snake(kawhi);
      }else if (action==1){
        ((KevinDurant)npc).burnerAccount(kawhi);
      }else if (action==2){
        ((KevinDurant)npc).buckets(kawhi);
      }else{
        ((KevinDurant)npc).mvpSpeech();
      }
    }else if (npc instanceof StephenCurry){
      if (action==0){
        ((StephenCurry)npc).floater(kawhi);
      }else if (action==1){
        ((StephenCurry)npc).clutch(kawhi);
      }else if (action==2){
        ((StephenCurry)npc).deepThree(kawhi);
      }else{
        ((StephenCurry)npc).playoffMode();
      }
    }
    playerSleep = playable.getSleep();
  }
}