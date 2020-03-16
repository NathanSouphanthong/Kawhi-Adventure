/* [KevinDurant.java]
 * that inherits the character class
 * June 12th, 2019
 * @author: Jerry Jiao/Nathan Souphanthong
 */

//imports
import java.awt.*;

class KevinDurant extends Character{
  CollisionBox durantBox;
  Image kdImage = Toolkit.getDefaultToolkit().getImage("kd.png");
  KevinDurant(int health, int healthMax,int hitChance, int x, int y){
    super(health, healthMax,hitChance,x ,y);
    durantBox = new CollisionBox(x,y,60,60);
  }
  
  
    /**
   * snake 
   * This method accepts a character object and increases its own hit chance by the same number
   * it decreases the enemy player's hit chance
   * @param enemyPlayer, character object
   * @return void
   */
  public void snake(Character enemyPlayer){
    int hit = (int) (Math.random()*100);
    if (hit<getHitChance()){
      setHitChance(getHitChance()+10);
      enemyPlayer.setHitChance( enemyPlayer.getHitChance()-10);
    }
  }
  
    /**
   * mvpSpeech 
   * This method increases the health of the character and the hit chance 
   * @param void
   * @return void
   */
  public void mvpSpeech(){
    int hit = (int) (Math.random()*150);
    if (hit<getHitChance()){
      setHealth(getHealth()+10);
      setHitChance(getHitChance()+5);
    }
  }
  
    /**
   * burnerAccount 
   * This method accepts a character object puts the character to sleep based on a random number
   * @param void
   * @return void
   */
  public void burnerAccount(Character enemyPlayer){
    int hit = (int) (Math.random()*200);
    if (hit<getHitChance()){
      enemyPlayer.setSleepTrue();
    }
  }
  
  
    /**
   * buckets 
   * This method accepts a character object and decreases the enemy character object's health by a random number
   * @param enemyPlayer, a character object
   * @return void
   */
  public void buckets(Character enemyPlayer){
    int hit = (int) (Math.random()*100);
    if (hit<getHitChance()){
      enemyPlayer.setHealth((enemyPlayer.getHealth())-20);
    }
  }
}