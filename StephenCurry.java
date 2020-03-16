/* [StephenCurry.java]
 *Stephen curry object that inherits the character class
 * June 12th, 2019
 * @author: Jerry Jiao/Nathan Souphanthong
 */

//imports 
import java.awt.*;

class StephenCurry extends Character{
  CollisionBox stephenCurryBox; 
  Image curryImage = Toolkit.getDefaultToolkit().getImage("curry.gif");
  
  //constructor
  StephenCurry(int health, int healthMax,int hitChance, int x, int y){
    super(health, healthMax,hitChance,x ,y);
   stephenCurryBox = new CollisionBox(x,y,60,60);
  }
  
  /**
   * floater 
   * decreases the enemy players health based on a random number and hit chance
   * @param enemyPlayer, character object
   * @return void
   */
  public void floater(Character enemyPlayer){
    int hit = (int) (Math.random()*80);
    if (hit<getHitChance()){
      enemyPlayer.setHealth(enemyPlayer.getHealth()-15);
    }
  }
  
    /**
   * playoffMode  
   * decreases the own player's health and increases the hitchance based on a random number
   * @param enemyPlayer, character object
   * @return void
   */
  public void playoffMode(){
    int hit = (int) (Math.random()*100);
    if (hit<getHitChance()){
      setHealth(getHealth()-20);
      setHitChance(getHitChance()+40);
    }
  }
  
    /**
   * clutch
   * increases hit chance of the own player if the enemy players health is below 50 
   * @param enemyPlayer, character object
   * @return void
   */
  public void clutch(Character enemyPlayer){
    int hit = (int) (Math.random()*200);
    if (hit<getHitChance()){
      if (enemyPlayer.getHealth()<50)
      setHealth(getHealth()+20);
      setHitChance(getHitChance()+20);
    
    }else{
      enemyPlayer.setHealth((enemyPlayer.getHealth())+5);
    }
    }
  
    /**
   * deepThree
   * decreases the enemy players health based on a random number and hit chance
   * @param enemyPlayer, character object
   * @return void
   */
  public void deepThree(Character enemyPlayer){
    int hit = (int) (Math.random()*180);
    if (hit<getHitChance()){
      enemyPlayer.setHealth((enemyPlayer.getHealth())-25);
    }
  }
}