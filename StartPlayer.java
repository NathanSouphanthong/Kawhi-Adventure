/* [StartPlayer.java]
 *Start player object that inherits the character class
 * June 12th, 2019
 * @author: Jerry Jiao/Nathan Souphanthong
 */

//imports 
import java.awt.*;

class StartPlayer extends Character{
  CollisionBox startPlayerBox;
  Image startImage = Toolkit.getDefaultToolkit().getImage("start.gif");
  StartPlayer(int health, int healthMax,int hitChance, int x, int y){
    super(health, healthMax,hitChance,x ,y);
    startPlayerBox = new CollisionBox(x,y,60,60);
  }
  
  
   /**
   * shoot 
   * This method changes the enemy players health based on a random number and hit chance 
   * @param enemyPlayer, character object
   * @return void
   */
  public void shoot(Character enemyPlayer){
    int hit = (int) (Math.random()*100);
    if (hit<getHitChance()){
      enemyPlayer.setHealth((enemyPlayer.getHealth())-8);
    }
  }
  
   /**
   * dunk 
   * This method changes the player's health based on a random number
   * @param enemyPlayer, character object
   * @return void
   */
  public void dunk(Character enemyPlayer){
    int hit = (int) (Math.random()*250);
    if (hit<getHitChance()){
      enemyPlayer.setHealth((enemyPlayer.getHealth())-15);
    }
  }
  
   /**
   * pass 
   * This method changes the player's hit chance based on a random number and hit chance
   * @param void
   * @return void
   */
  public void pass(){
    int hit = (int) (Math.random()*100);
    if (hit<getHitChance()){
      setHitChance(getHitChance()+5);
    }
  }
  
   /**
   * burnerAccount 
   * This method decreases the enemy player
   * @param enemyPlayer, character object
   * @return void
   */
  public void defense(Character enemyPlayer){
    int hit = (int) (Math.random()*100);
    if (hit<getHitChance()){
      enemyPlayer.setHitChance((enemyPlayer.getHitChance())-5);
    }
  }
}