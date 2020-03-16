/* [BallPlanet.java]
 * main method that starts the game
 * June 12th, 2019
 * @author: Nathan Souphanthong
 */

//imports 
import java.io.*;
import java.awt.*;

//main class that also imports text 
class BallPlanet { 
  static Font customFont;
  public static void main(String [] args){ 

    try{
       customFont = Font.createFont(Font.TRUETYPE_FONT, new File("PressStart2P.ttf")).deriveFont(40f);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(customFont);
      
    }catch  (IOException e) {
      e.printStackTrace();
    }catch (FontFormatException e) {
      e.printStackTrace();
    }
    
    //intialize game 
    new StartingFrame(); 
  }
}




  
  
  
    
    
    
    
    
 
    
    
  
  
  
  
  
  
  
  
  
  
  
  



