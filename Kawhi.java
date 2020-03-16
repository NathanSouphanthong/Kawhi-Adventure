/* [Kawhi.java]
 * Kawhi object that inherits the character class
 * June 12th, 2019
 * @author: Jerry Jiao/Nathan Souphanthong
 */

class Kawhi extends Character{
  //does not need collision box, kawhi represents the sprite 
  Kawhi(int health, int healthMax,int hitChance, int x, int y){
    super(health, healthMax,hitChance,x ,y);
  }
  public Character[] getArr(){
    return characterArray;
  }
  public boolean score(Character enemyPlayer){
    int hit = (int) (Math.random()*100);
    if (hit<getHitChance()){
      
      enemyPlayer.setHealth(enemyPlayer.getHealth()-15);
      return true;
    }else{
      return false;
    }
  }
  
  
    /**
   * sacrifice 
   * This method accepts a character array and attacks it based on the hit chance
   * if hit chance is less than the hit value, the enemy player loses health
   * @param arr, the character array
   * @return boolean  
   */
  public boolean sacrifice(Character [] arr){
    boolean breakb = false;
    for (int i=0;i<3 && arr[i]!=null && breakb==false;i++){
      breakb = true;
    if (arr[i] instanceof StartPlayer || arr[i] instanceof Coach){
      setHealth(getHealth()+10);
      setHitChance(getHitChance()+10);
    }else if (arr[i] instanceof BenchPlayer){
      setHealth(getHealth()+5);
      setHitChance(getHitChance()+5);
    }else if (arr[i] instanceof LebronJames || arr[i] instanceof StephenCurry ||arr[i] instanceof KevinDurant){
      setHealth(getHealth()+20);
      setHitChance(getHitChance()+20);
    }
    
    }
    return breakb;
  }
  
  
      /**
   * funGuy 
   * This method causes the character to gain health determined by hit chance
   * @param void
   * @return boolean  
   */
  public boolean funGuy(){
    int hit = (int) (Math.random()*100);
    if (hit<getHitChance()){
      
      setHealth((getHealth())+15);
      return true;
    }else{
      return false;
    }
  }
  
  
      /**
   * carryTheNorth 
   * attacks or puts enemy character to sleep based on hit chance
   * @param enemyPlayer, a character object
   * @return boolean value 
   */
  public boolean carryTheNorth(Character enemyPlayer){
    int hit = (int) (Math.random()*200);
    if (hit<getHitChance()){
      enemyPlayer.setSleepTrue();
    if (getHealth()>50){
      enemyPlayer.setHealth((enemyPlayer.getHealth())-20);
      setHealth(getHealth()-10);
    }else{
      enemyPlayer.setHealth((enemyPlayer.getHealth())-40);
      setHealth(getHealth()-15);
    }
    return true;
    }else{
      return false;
    }
  }
}