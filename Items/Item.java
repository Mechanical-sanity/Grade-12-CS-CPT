/* 
* Item.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: to create and host health potions, damage potions, and swords
* Methods: getIncrease(), getType(), reset(), setIncrease()
*/
package Items;
import UseFunc.*;
import java.util.*;
//requried imports

public class Item {
    public String itemType;
    public int increase;
    //global values

    //constructor
    public Item(String type, int increaseMin, int increaseMax){
        this.itemType = type;
        this.increase = UsefulFunctions.getRandomNumber(increaseMin, increaseMax) ; //random increase value
    }

    //////////////////////////////////////////////////////
    public int getIncrease(){
        return increase;
        //returns increase value
    }
    //////////////////////////////////////////////////////

    //////////////////////////////////////////////////////
    public String getType(){
        return this.itemType;
        //returns item type string
    }
    //////////////////////////////////////////////////////

    //////////////////////////////////////////////////////
    public void reset(){ 
        this.itemType = "";
        this.increase = 0;
        //resets an item
    }
    /////////////////////////////////////////////////
    public void setIncrease(int quantity){
      this.increase = quantity;
      //sets the increase. used when loading save file
    }

}