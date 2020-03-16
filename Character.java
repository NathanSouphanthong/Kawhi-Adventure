/* [Character.java]
 * abstract class for the gameboard characters 
 * June 12th, 2019
 * @author: Jerry Jiao
 */


abstract class Character{
  private int health;
  private int healthMax;
  private int hitChance;
  private boolean sleep = false;
  private int x;
  private int y;
  Character [] characterArray = new Character[3];
  
 //constructor
  Character (int health, int healthMax, int hitChance,int x, int y){
    this.health = health;
    this.healthMax = healthMax;
    this.hitChance = hitChance;
    this.x=x;
    this.y=y;
  }
  
  //getters and setters 
  public int getX(){ 
    return this.x;
  }
  
  public void setX(int x){ 
    this.x = x;
  }
  
  public int getY(){ 
    return this.y;
  }
  
  public void setY(int y){ 
    this.y= y;
  }
  
  public void setHealth(int newHealth){
    if (newHealth>healthMax){
      this.health = healthMax;
    }else{
    this.health= newHealth;
    }
  }
  
  public int getHealth(){
    return this.health;
  }
  
  public int getMaxHealth(){
    return this.healthMax;
  }
  
  public void setHitChance(int newHitChance){
    this.hitChance = newHitChance;
  }
  
  public int getHitChance(){
    return this.hitChance;
  }
  
  public void setSleepTrue(){
    this.sleep = true;
  }
  
  public void setSleepFalse(){
    this.sleep = false;
  }
  
  public boolean getSleep(){
    return sleep;
  }
  
  public void moveUp(){
    this.x+=1;
  }
  
  public void moveDown(){
    this.x-=1;
  }
  
  public void moveLeft(){
    this.y+=1;
  }
  
  public void moveRight(){
    this.y-=1;
  }
  
  public Character [] getArr(){
    return characterArray;
  }
  
  public void addArr(int i, Character chara){
    characterArray[i]=chara;
  }
}
    