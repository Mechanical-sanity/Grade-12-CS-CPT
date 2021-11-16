/* 
* Inventory.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: To hold all the item objects and use them when needed
* Methods: addItem(), getHealthPotions(), getDamagePotions(), getSwords()
*/
package PlayerFunc;
import UI.*;
import UseFunc.*;
import Items.*;
import java.util.*;
import Combat.*;
import GameWorldFunc.*;
//required package and imports


public class Inventory{

    //class variables
    UI ui;
    GameScreen gameText;
    StringInput stringInput;
    Story story; //story object to universalize functionality
    //Item item_now;

    public ArrayList<Item> HP_list; //health potion
    public ArrayList<Item> DP_list;//damage Potions
    public ArrayList<Item> W_list;//weapons


    //inventory constructor
    public Inventory(Story current_story){
        HP_list = new ArrayList<Item>();
        DP_list = new ArrayList<Item>();
        W_list = new ArrayList<Item>();
        story = current_story;
    }

    //method to add item to inventory
    public void addItem(Item itm){

            //if item type fits name then add it 
            if(itm.getType().equals("Health")){
              this.HP_list.add(itm);
            } 
            else if(itm.getType().equals("Damage")){
              this.DP_list.add(itm);
            }
            else if(itm.getType().equals("Sword")){
              this.W_list.add(itm);
            }
            else{
              System.out.println("Error in inventory.additem");
              //debugging statement
            }
        
        
    }


    //public counters
    public int h;
    public int d;
    public int w;
    

    public void getHealthPotions(){
        story.gameText.mainTextArea.setText(
            "You have the following Health Potions:"
        );
        //sets text
        story.buttonList = new ArrayList<GameButton>();
        for(h = 0; h < HP_list.size(); h++){
            //for loop creates many buttons to choose potion
            story.buttonList.add(new GameButton(story, ( (HP_list.get(h)).getIncrease() + " Health Increase"), h ) {
                @Override
                public void buttonFunction() {
                  //roomInt variable becuase it was necassary due to gui
                  story.combat.Pactions.increase_ac = story.inventory.HP_list.get(roomInt).getIncrease();
                  //sets increase_ac variable to the increase amount
                  story.inventory.HP_list.remove(roomInt);
                  //removes the item from the inventory
                  story.combat.Pactions.PlayerHealthPotion(story.combat.Pactions.increase_ac);
                  //uses the item

                  story.combat.Pactions.PlayerOptions();
                  //goes back to player options
                  
                }
            });
        }
        //cancel button
        story.buttonList.add(new GameButton(story, "cancel" ) {
            @Override
            public void buttonFunction() {
              story.combat.Pactions.PlayerOptions();
            }
        });
        story.refreshButtonPanel(); //refreshes buttons
    }

    public void getDamagePotions(){
        story.gameText.mainTextArea.setText(
            "You have the following Damage Potions:"
        );
          //sets text
        story.buttonList = new ArrayList<GameButton>();
        for(d = 0; d < DP_list.size(); d++){
          //for loop creates many buttons to choose potion
            story.buttonList.add(new GameButton(story, ( (DP_list.get(story.inventory.d)).getIncrease() + " Temp Damage Increase"), story.inventory.d ) {
                @Override
                public void buttonFunction() {
                  //roomInt variable becuase it was necassary due to gui
                  story.combat.Pactions.increase_ac =  story.inventory.DP_list.get(roomInt).getIncrease();
                   //sets increase_ac variable to the increase amount
                  story.inventory.DP_list.remove(roomInt);
                   //removes the item from the inventory
                  story.combat.Pactions.PlayerDamagePotion(story.combat.Pactions.increase_ac);
                   //uses the item
                  story.combat.Pactions.PlayerOptions();
                   //goes back to player options
                }
            });
        }
        //cancel button
        story.buttonList.add(new GameButton(story, "cancel" ) {
            @Override
            public void buttonFunction() {
              //cancel button goes back to player options
              story.combat.Pactions.PlayerOptions();
            }
        });
        story.refreshButtonPanel(); //refreshes buttons
    }


    public void getSwords(){
        story.gameText.mainTextArea.setText(
            "You have the following Swords:"
        );
        //sets text
      story.buttonList = new ArrayList<GameButton>();
        for(w = 0; w < W_list.size(); w++){
            story.buttonList.add(new GameButton(story, ( (W_list.get(story.inventory.w)).getIncrease() + " Damage Increase"), story.inventory.w ) {
                @Override
                public void buttonFunction() {
                  //room int was used because ui was setup that way
                  story.combat.Pactions.increase_ac = story.inventory.W_list.get(roomInt).getIncrease();
                    //sets increase_ac variable to the increase amount
                  story.inventory.W_list.remove(roomInt);
                    //removes the item from the inventory
                  story.combat.Pactions.PlayerEquipSword(story.combat.Pactions.increase_ac);
                    //uses the item
                  story.combat.Pactions.PlayerOptions();
                    //goes back to player options
                }
            });
        }
        story.buttonList.add(new GameButton(story, "cancel" ) {
                @Override
                //cancel button
                public void buttonFunction() {
                  story.combat.Pactions.PlayerOptions();
                }
            });
        story.refreshButtonPanel();//refrsehes buttons
        
      
    }

}
