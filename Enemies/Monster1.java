/* 
* Monster1.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: to hold Monster details
* Methods: getEName(), getEHealth(), getEDamage(), setEHealth()
*/
package Enemies;


public class Monster1 implements BaseMonster{
  //monster class, uses BaseMonster interface

  //public variables
    public String name;
    public int mon_health;
    public int mon_damage;

    //monster constructor
    public Monster1(int health, int damage, String mon){
        //super("Monster",health, damage);
        this.name = mon;
        this.mon_health = health;
        this.mon_damage = damage;
    }
    
    //gets the name
    public String getEName(){
        return this.name;
    }
    //gets the health
    public int getEHealth(){
        return this.mon_health;
    }
    //gets the damage
    public int getEDamage(){
        return this.mon_damage;
    }
    //sets the health 
    public void setEHealth(int quantity){
      this.mon_health = quantity;
    }
} // class Monsters