/* [LebronJames.java]
 *Lebron James object that inherits the character class
 * June 12th, 2019
 * @author: Jerry Jiao/Nathan Souphanthong
 */


import java.awt.*;

class LebronJames extends Character{
  CollisionBox lebronBox;
  Image lebronImage = Toolkit.getDefaultToolkit().getImage("lebron.gif");
  LebronJames(int health, int healthMax,int hitChance, int x, int y){
    super(health, healthMax,hitChance,x ,y);
    lebronBox = new CollisionBox(x,y,60,60);

  }
  
     /**
   * theDecision 
   * This method accepts a character object puts the character to sleep based on a random number
   * @param enemyPlayer
   * @return void
   */
  public void theDecision(Character enemyPlayer){
    int hit = (int) (Math.random()*150);
    if (hit<getHitChance()){
      enemyPlayer.setSleepTrue();
    }
  }
  
     /**
   * burnerAccount 
   * This method changes the player's health based on a random number
   * @param void
   * @return void
   */
  public void jamToTrashMusic(){
    int hit = (int) (Math.random()*100);
    if (hit<getHitChance()){
      setHealth(getHealth()+20);
    }
  }
  
     /**
   * buzzerBeater 
   * This method accepts an enemy character obejct and either decreases the enemy's health by 2 different numbers
   * or increases its own health determined by a random number 
   * @param enemyPlayer
   * @return void
   */
  public void buzzerBeater(Character enemyPlayer){
    int hit = (int) (Math.random()*200);
    if (hit<getHitChance()){
      setHealth(getHealth()+10);
      if (enemyPlayer.getHealth()<50)
      enemyPlayer.setHealth((enemyPlayer.getHealth())-30);
    }else{
      enemyPlayer.setHealth((enemyPlayer.getHealth())-10);
    }
    }
  
     /**
   * posterize 
   * This method accepts a character object and decreases the enemy player's health based on a random number
   * @param enemyPlayer
   * @return void
   */
  public void posterize(Character enemyPlayer){
    int hit = (int) (Math.random()*120);
    if (hit<getHitChance()){
      enemyPlayer.setHealth((enemyPlayer.getHealth())-20);
    }
  }
}