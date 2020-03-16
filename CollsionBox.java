/* [CollisionBox.java]
 * creates a rectangle box around the sprites to be able to check for collision  
 * June 12th, 2019
 * @author: Nathan Souphanthong
 */

//imports
import java.awt.*;

class CollisionBox{ 
  double xPosition,yPosition;
  int height,width;
  Rectangle boundingBox;
  
  //constructor
  public CollisionBox(int x, int y, int w, int h){ 
  xPosition = x;
  yPosition = y;
  width=w;
  height=h;
  boundingBox = new Rectangle((int)xPosition, (int)yPosition, width, height);
  }
  /**
   * update
   * updates character postion and image based on movement
   * @param int playerX, int playerY, coordinates of the object
   * @return void
   */
  public void update(int playerX, int playerY){ 
    this.xPosition = playerX;
    this.yPosition = playerY;
    boundingBox = new Rectangle((int)xPosition, (int)yPosition, width, height);
    
  }
}