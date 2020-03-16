/* [Coach.java]
 * Coach object that extends the character class and has seperate methods
 * June 12th, 2019
 * @author: Jerry Jiao/Nathan Souphanthong
 */

//imports 
import java.awt.*;

class Coach extends Character{
  CollisionBox coachBox;
  Image coachImage = Toolkit.getDefaultToolkit().getImage("coach.gif");
  
  Coach(int health, int healthMax,int hitChance, int x, int y){
    super(health, healthMax,hitChance,x ,y);
    coachBox = new CollisionBox(x,y,60,60);
  }
  
  /**
   * sendToDLeague 
   * This method accepts a character object and attacks it based on the hit chance
   * if hit chance is less than the hit value, the enemy player loses health
   * @param enemyPlayer, the opposing player during battle screen
   * @return void
   */

  public void sendToDLeague(Character enemyPlayer){
    int hit = (int) (Math.random()*2000);
    if (hit<getHitChance()){
      enemyPlayer.setHealth(0);
    }
  }
  
  /**
   * whine 
   * This method accepts a character object and increases the hit chance based on the previous hit chance
   * @param void
   * @return void
   */
  public void whine(){
    int hit = (int) (Math.random()*100);
    if (hit<getHitChance()){
      setHitChance(getHitChance()+10);
    }
  }
  
    /**
   * bench 
   * This method accepts a character object and increases the health based on hit chance
   * @param void
   * @return void
   */
  public void bench(){
    int hit = (int) (Math.random()*100);
    if (hit<getHitChance()){
      setHealth(getHealth()+10);
    }
  }
  
  
  /**
   * scream 
   * This method accepts a character object and decreases the hit chance 
   * of the enemy player  based on hit chance
   * @param enemyPlayer, opposing player during battle screen
   * @return void
   */
  public void scream(Character enemyPlayer){
    int hit = (int) (Math.random()*100);
    if (hit<getHitChance()){
      enemyPlayer.setHitChance((enemyPlayer.getHitChance())-10);
    }
  }
}