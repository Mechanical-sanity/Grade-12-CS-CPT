/* 
* Player.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: to hold player details and actions
* Methods: setWeapon(), setDamage(), setHealth(), setHealthAbs(), setDamageAbs(), getHealth(), getName(), getLocation(), setLocation(), openChest()
*/
package PlayerFunc;
import java.util.*;
import GameWorldFunc.*;
import UseFunc.UsefulFunctions;
import Weapon.*;
import UI.*;

public class Player {
    public String player_name;//current player name
    public SuperWeapon current_weapon; //current weapon name

    public Story story; //story object

    public int chestItemCounter = 0;
    public int health,damage;
    public int location; //roomId of room that player is currently in


    //Constructor
    public Player(Story story_current) {
        story = story_current;
        this.health = 100;
        this.damage = 20;
        this.current_weapon = new Weapon_Fists();
        this.location = 0;
    }

    //used to set the current weapon
    public void setWeapon(String choice){
      if(choice.equals("Sword")){
      this.current_weapon = new Weapon_Sword();
      }
      else if(choice.equals("Fists")){
        this.current_weapon = new Weapon_Fists();
      }
    }

    //used to get the current weapon name
    public String getWeapon(){
        return this.current_weapon.name;
    }
    
    //used to add on damage to the current damage
    public void setDamage(int quantity){
        this.damage = this.damage + quantity;
    }

    //used to add on health
    public void setHealth(int quantity){
        this.health = this.health + quantity;
    }

    //used to set the health 
    public void setHealthAbs(int quantity){
        this.health = quantity;
    }

    //used to set the damage
    public void setDamageAbs(int quantity){
      this.damage = quantity;
    }

    //used to get the current health
    public int getHealth(){
        return this.health;
    }

    //used to get the name
    public String getName(){
        return this.player_name;
    }

    //used to get the location
    public int getLocation(){
        return this.location;
    }
    public void setLocation(int loc){
        this.location = loc;
    }

    public void openChest(int increaseMin, int increaseMax){
        
        story.world.chest.add(new Chest());
        story.inventory.addItem( (story.world.chest.get(chestItemCounter)).getItem(increaseMin, increaseMax) );
        if(story.world.chest.get(chestItemCounter).getItemType().equals("Sword") ){
        story.gameText.mainTextArea.setText( "You opened a chest. You have obtained a " + story.world.chest.get(chestItemCounter).getItemType()        );
        }
        else{
          story.gameText.mainTextArea.setText( "You opened a chest. You have obtained a " + story.world.chest.get(chestItemCounter).getItemType() + " potion");
        }
        chestItemCounter++;
        story.buttonList = new ArrayList<GameButton>(); //michael fix this
        story.buttonList.add(new GameButton(story, "Continue") {
            @Override
            public void buttonFunction() {
                story.current_place();
            }
        });
        story.refreshButtonPanel();
    }

}
