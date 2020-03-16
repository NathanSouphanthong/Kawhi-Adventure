/* [Map.java]
 *Map class that draws and initializes characters randomly on the map while taking the 
 * information from an array using file i/o
 * June 12th, 2019
 * @author: Nathan Souphanthong
 */

//imports 
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Scanner;
import java.lang.Math;
import java.io.*; 



class Map{ 
  //variables for map and characters
  int boxWidth; 
  int boxHeight; 
  int visibleWidth, visibleHeight; 
  Player player;
  int map[][]; 
  int playerY;
  int playerX;
  //variables to determine if players should be drawn on screen
  Kawhi kawhi; BenchPlayer benchPlayer; Coach coach; KevinDurant kevinDurant; LebronJames lebronJames; 
  StephenCurry stephenCurry; StartPlayer startPlayer;
  boolean kawhiOn; boolean benchPlayerOn; boolean coachOn; boolean kevinDurantOn; boolean lebronJamesOn;
  boolean startPlayerOn; boolean stephenCurryOn;
  
  //the random coordinates for each player
  int benchPlayerX; int benchPlayerY; 
  int coachX;int coachY; 
  int startPlayerX;int startPlayerY; 
  int kevinDurantX;int kevinDurantY;
  int lebronJamesX; int lebronJamesY; 
  int stephenCurryX;int stephenCurryY; 
  
  //background images
  Image backgroundFloor = Toolkit.getDefaultToolkit().getImage("backgroundFloor.png");
  Image oracle = Toolkit.getDefaultToolkit().getImage("oracle.png");
  Image staples = Toolkit.getDefaultToolkit().getImage("staples.png");
  
  //constructor
  public Map(int xResolution, int yResoloution, Player player){ 
    visibleWidth = 40;
    visibleHeight = 40; 
    
    
    boxWidth = 60;
    boxHeight = 60;
    
    this.player = player; 
    
    map = loadMapData("map.txt"); 
    
  }
  
  //read data from a file 
  public int [][] loadMapData(String filename){ 
    int data[][] = null;
    try{ 
      File mapFile = new File(filename);
      Scanner input = new Scanner(mapFile); 
      data = new int[input.nextInt()][input.nextInt()];
      
      for(int i = 0; i < data.length;i++){ 
        for(int j = 0; j<data[0].length;j++){ 
          data[i][j] = input.nextInt(); 
//            System.out.print(data[i][j] + " ");
        }
//          System.out.println();
      }
      input.close(); 
      
      
      
    }catch (Exception E){};
    System.out.println("hi");
    System.out.println(data.length);
    System.out.println(data[0].length);
    return data; 
  }
  
  /**
   * createPlayers 
   * creates random coordinates for each of the players, as well as setting the boolean values to draw the characters
   * as true
   * @param void
   * @return void
   */
  public void createPlayers(){ 
    for (int j=0;j<visibleHeight;j++)
      for (int i=0;i<visibleWidth;i++) {
      
      if (map[j][i] == 2) {
        benchPlayerX = 0 + (int)(Math.random() * ((1860 - 0) + 1));
        benchPlayerY = 0 + (int)(Math.random() * ((980 - 0) + 1));
        
        benchPlayerOn = true;
      }else if (map[i][j] == 3){ 
        coachX = 0 + (int)(Math.random() * ((1860 - 0) + 1));
        coachY = 0 + (int)(Math.random() * ((980 - 0) + 1));
        while (coachX == benchPlayerX || coachY == benchPlayerY){  //regenerate coordinates until they are not the same
          coachX = 0 + (int)(Math.random() * ((1860 - 0) + 1));
          coachY = 0 + (int)(Math.random() * ((980 - 0) + 1));
        }
        coachOn = true;
      }else if (map[i][j] == 4){ 
        kevinDurantX = 0 + (int)(Math.random() * ((1860 - 0) + 1));
        kevinDurantY= 0 + (int)(Math.random() * ((980 - 0) + 1));
        while (kevinDurantX == benchPlayerX || kevinDurantY == benchPlayerY || kevinDurantX ==coachX || kevinDurantY
              == coachY){ 
          kevinDurantX = 0 + (int)(Math.random() * ((1860 - 0) + 1));
          kevinDurantY = 0 + (int)(Math.random() * ((980 - 0) + 1));
        }
        kevinDurantOn = true;
      }else if (map[i][j] == 5){ 
        lebronJamesX = 0 + (int)(Math.random() * ((1860 - 0) + 1));
        lebronJamesY = 0 + (int)(Math.random() * ((980 - 0) + 1));
        while (lebronJamesX == benchPlayerX || lebronJamesY == benchPlayerY || lebronJamesX ==coachX || lebronJamesY
              == coachY || lebronJamesX ==kevinDurantX || lebronJamesY == kevinDurantY ){ 
          lebronJamesX = 0 + (int)(Math.random() * ((1860 - 0) + 1));
          lebronJamesY = 0 + (int)(Math.random() * ((980 - 0) + 1));
        }
        lebronJamesOn = true;
      }else if (map[i][j] == 6){ 
        stephenCurryX = 0 + (int)(Math.random() * ((1860 - 0) + 1));
        stephenCurryY = 0 + (int)(Math.random() * ((980 - 0) + 1));
        while (lebronJamesX == benchPlayerX || lebronJamesX == benchPlayerY || lebronJamesX ==coachX || lebronJamesX
              == coachY || lebronJamesX ==kevinDurantX || lebronJamesY == kevinDurantY || stephenCurryX == lebronJamesX || 
       stephenCurryY ==  lebronJamesY){ 
          stephenCurryX = 0 + (int)(Math.random() * ((1860 - 0) + 1));
          stephenCurryY = 0 + (int)(Math.random() * ((980 - 0) + 1));
        }
        stephenCurryOn = true;
      }else if (map[i][j] == 7){ 
        startPlayerX = 0 + (int)(Math.random() * ((1860 - 0) + 1));
        startPlayerY = 0 + (int)(Math.random() * ((980 - 0) + 1));
        while (startPlayerX == benchPlayerX || startPlayerY == benchPlayerY || startPlayerX ==coachX || startPlayerY
              == coachY || startPlayerX ==kevinDurantX || startPlayerY == kevinDurantY || startPlayerX == lebronJamesX || 
       startPlayerY ==  lebronJamesY || startPlayerX ==  lebronJamesX || startPlayerY == lebronJamesY){ 
          startPlayerX = 0 + (int)(Math.random() * ((1860 - 0) + 1));
          startPlayerY = 0 + (int)(Math.random() * ((980 - 0) + 1));
        }
        startPlayerOn = true;
      }
    }
    
  }
  
  /**
   * draw
   * draws the characters based on the amount of players on the screen, in addition changes the background image
   * as the levels change
   * @param g
   * @return void
   */
  public void draw(Graphics g){ 
    //draw background based on the amount of players alive on the game screen
    if(benchPlayerOn == true || coachOn == true || startPlayerOn == true){ 
      g.drawImage(backgroundFloor,0,0,1920,1080,null);
    }else if (kevinDurantOn == true && startPlayerOn == false
                && benchPlayerOn == false && coachOn == false ){ 
      g.drawImage(oracle,0,0,1920,1080,null);
    }else if (lebronJamesOn == true && startPlayerOn == false
                && benchPlayerOn == false && coachOn == false && kevinDurantOn == false ){ 
      g.drawImage(staples,0,0,1920,1080,null);
    }else if (stephenCurryOn == true  && startPlayerOn == false
                && benchPlayerOn == false && coachOn == false && kevinDurantOn == false && lebronJamesOn == false ){
      g.drawImage(oracle,0,0,1920,1080,null);
    }
    //draws players based on their respective coordinates
    for (int j=0;j<visibleHeight;j++)
      for (int i=0;i<visibleWidth;i++) {
      if (map[j][i]==2 && benchPlayerOn == true ){ 
        benchPlayer = new BenchPlayer(60,30,40,benchPlayerX,benchPlayerY);
        g.drawImage(benchPlayer.benchImage,benchPlayerX,benchPlayerY,boxWidth,boxHeight,null);
      }else if (map[j][i] == 3 && coachOn == true ) {
        coach = new Coach(60,120,60,coachX,coachY);
        g.drawImage(coach.coachImage,coachX,coachY,boxWidth,boxHeight,null);
      }else if (map[j][i] == 4 && kevinDurantOn == true && startPlayerOn == false
                  && benchPlayerOn == false && coachOn == false ) {
        kevinDurant = new KevinDurant(90,180,90,kevinDurantX,kevinDurantY);
        g.drawImage(kevinDurant.kdImage,kevinDurantX,kevinDurantY,boxWidth,boxHeight,null);
      }else if (map[j][i] ==  5 && lebronJamesOn == true && startPlayerOn == false
                  && benchPlayerOn == false && coachOn == false && kevinDurantOn == false ) {
        lebronJames = new LebronJames(115,230,70,lebronJamesX,lebronJamesY);
        g.drawImage(lebronJames.lebronImage,lebronJamesX,lebronJamesY,boxWidth,boxHeight,null);
      }else if (map[j][i] == 6 && stephenCurryOn == true  && startPlayerOn == false
                  && benchPlayerOn == false && coachOn == false && kevinDurantOn == false && lebronJamesOn == false ) {
        stephenCurry = new StephenCurry(80,160,120,stephenCurryX,stephenCurryY);
        g.drawImage(stephenCurry.curryImage,stephenCurryX,stephenCurryY,boxWidth,boxHeight,null);
      }else if (map[j][i] == 7 && startPlayerOn == true ) {
        startPlayer = new StartPlayer(100,30,55,startPlayerX,startPlayerY);
        g.drawImage(startPlayer.startImage,startPlayerX,startPlayerY,boxWidth,boxHeight,null);
      }
      
//        g.fillRect(i*boxWidth, j*boxHeight, boxWidth-1, boxHeight-1); 
    }
    
  }
  
}