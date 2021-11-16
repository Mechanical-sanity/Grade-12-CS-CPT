/* 
* BaseMonster.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: Monster interface for BossMonster and Monster1
* Methods: getEName(), getEHealth(), getEDamage(), setEHealth()
*/
package Enemies;
import java.util.*;

// this is the interface for monsters
public interface BaseMonster {
    //interface methods
    public String getEName();
    public int getEHealth();
    public int getEDamage();
    public void setEHealth(int quantity);
} // class basemonster
