/* [BenchPlayer.java]
 * bench player object methods, poition, collison box and image
 * June 12th, 2019
 * @author: Jerry Jiao
 */

import java.awt.*;

//bench player object that extends character abstract class
class BenchPlayer extends Character{
  CollisionBox benchPlayerBox;
  Image benchImage = Toolkit.getDefaultToolkit().getImage("bench.gif");
  BenchPlayer(int health, int healthMax, int hitChance, int x, int y){
    super(health, healthMax,hitChance,x ,y);
    benchPlayerBox = new CollisionBox(x,y,60,60);
  }
  

  /** 
   * pourWater
   * changes enemy player health based on hit chance 
   * @param enemyPlayer, object that represents another enemy player
   * @return void 
   * */ 
  public void pourWater(Character enemyPlayer){
    int hit = (int) (Math.random()*100);
    if (hit<getHitChance()){
      enemyPlayer.setHealth((enemyPlayer.getHealth())-5);
    }
  }
  
    /** 
   * sit
   * changes sprite health based on hit chance 
   * @return void, but change health 
   * */ 
  public void sit(){
    int hit = (int) (Math.random()*100);
    if (hit<getHitChance()){
      setHealth(getHealth()+2);
    }
  }
  
}
  