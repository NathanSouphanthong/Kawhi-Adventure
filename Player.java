/* [Player.java]
 * sprite class that is represented by kawhi image
 * June 12th, 2019
 * @author: Nathan Souphanthong
 */

//imports
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;

class Player{
  Image kawhiImage =  Toolkit.getDefaultToolkit().getImage("kawhiForward.png");
  int xPos, yPos;
  CollisionBox playerBox;
  String direction; 
  
  //constructor
  public Player(){ 
    this.xPos =0; 
    this.yPos = 0;
    this.direction = "stand"; 
    playerBox = new CollisionBox(xPos,yPos,60,60);
  }
  
    /**
     * update
     * updates character postion and image based on keyboard input
     * @param direction, String
     * @return void
     */
  public void update(String direction){ 
    if(direction.equals("up")){ 
      if(yPos - 20 >= 0){ 
        yPos = yPos - 20;
        playerBox.update(xPos,yPos);
        kawhiImage = Toolkit.getDefaultToolkit().getImage("kawhiForward.png");
      }
    }else { 
    }
    if(direction.equals("down")){ 
      if(yPos + 20 <= 980){ 
        yPos = yPos + 20; 
        playerBox.update(xPos,yPos);
        kawhiImage =  Toolkit.getDefaultToolkit().getImage("kawhiDown.png");
      } 
    }else { 
      
    }
    if(direction.equals("left")){ 
      if(xPos - 20 >= 0){ 
        xPos = xPos - 20; 
        playerBox.update(xPos,yPos);
        kawhiImage =  Toolkit.getDefaultToolkit().getImage("kawhiLeft.png");
      }  
    }else{ 
    }
    if(direction.equals("right")){ 
      if(xPos + 20 <= 1860){ 
        xPos = xPos + 20; 
        playerBox.update(xPos,yPos);
        kawhiImage =  Toolkit.getDefaultToolkit().getImage("kawhiRight.png");
      }   
    }else{ 
    }
    
    
  }
  
  
  //draw method
  public void draw(Graphics g){ 
   g.drawImage(kawhiImage,xPos,yPos,60,60,null);
  }
  
}