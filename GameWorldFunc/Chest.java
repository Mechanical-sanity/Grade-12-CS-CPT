/* 
* Chest.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: To dispence Items after monster is killed
* Methods: getItemType(), getItem()
*/
package GameWorldFunc;
import Items.*;
import UseFunc.*;


public class Chest {
    //chest object exists to be opened inside game and dispence random item
    //class variables
    public String itemType;

    //array holds all item names possible
    public String[] item_name = {"Health", "Damage", "Sword"};

    ///////////////////////////////////////////////////////
    //constructor
    public Chest() {
        //////////////////////////////////////
        //inputs number of item type to get item name
        int item = UsefulFunctions.getRandomNumber(1,3);
        if (item == 1) {
            this.itemType = item_name[0];
        }
        else if(item == 2){
            this.itemType = item_name[1];
        }
        else if(item == 3){
            this.itemType = item_name[2];
        }
        /////////////////////////////////////
        else {
            System.out.println("error in chest.java!");//debug statement
        }
    }
    //////////////////////////////////////////////////////



    ///////////////////////////////////////////////////////
    //method gets item inside chest
    public String getItemType(){

        return this.itemType;
        //gets item type string

    }

    //////////////////////////////////////////////////////



    ///////////////////////////////////////////////////////
    public Item getItem(int increaseMin, int increaseMax) {
        //method returns an item object
        /////////////////////////////////////////////////////
        //if the itemtype is known
        if ((this.itemType).equals("Health")) {

            return new Item("Health", increaseMin+20, increaseMax+20);

        }
        else if((this.itemType).equals("Damage")){

            return new Item("Damage", increaseMin+10, increaseMax+10);
        }

        else if((this.itemType).equals("Sword")){

            return new Item("Sword", increaseMin, increaseMax);

        }

        /////////////////////////////////////////////////////
        else {

            //debugging statement
            System.out.println("Error in chest.java");
            return new Item("debug" ,0, 0);

        }
        /////////////////////////////////////////////////////
    }

}