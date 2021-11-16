/* 
* BossMonster.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: To hold boss monster details
* Methods: getEName(), getEHealth(), getEDamage(), setEHealth()
*/
package Enemies;
import java.util.*;

public class BossMonster implements BaseMonster{
  //boss monster class, uses BaseMonster interface

  //public variables
    public String name;
    public int mon_health;
    public int mon_damage;

    //constructor
    public BossMonster(){
        name = "Demon Lord of the banished terrain";
        mon_health = 200;
        mon_damage = 30;
    }
    
    //gets monster name
    public String getEName(){
        return name;
    }
    //gets monster health
    public int getEHealth(){
        return mon_health;
    }
    //gets monster damage
    public int getEDamage(){
        return mon_damage;
    }
    //sets monster health
    public void setEHealth(int quantity){
      mon_health = quantity;
    }

    
}
