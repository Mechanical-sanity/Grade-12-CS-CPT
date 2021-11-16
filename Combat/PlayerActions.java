/* 
* PlayerActions.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: to allow user to choose what to do during battle 
* Methods: PlayerOptions(), PlayerAttack(), PlayerGuard(), PlayerFlee(), PlayerDeath(), PlayerUseItem(), PlayerHealthPotion(), PlayerDamagePotion(), PlayerEquipSword()
*/
package Combat;
import Enemies.*;
import Items.Item;
import PlayerFunc.*;
import UI.*;
import UseFunc.*;
import java.util.*;
import GameWorldFunc.*;


public class PlayerActions{

    UI ui; //ui and hud objects
    HUD hud;

    
    Story story; //story object to universalize variables

    public int damageAddOn = 0; //damage add on for potions
    public boolean Pguard = false; //player guard boolean
    public int increase_ac = 0; //variable for increase in damage or health

    //constructor
    public PlayerActions(Story story_current) {
        this.story = story_current;
    }

  
    //player options method
    public void PlayerOptions() {
        System.out.println("Mon : " + story.combat.current_enemy.getEHealth());
        System.out.println("player: " + story.player.health); //debug statements

        //if player has less then or 0 health they are death
        if(story.player.health <= 0){
            System.out.println("dided");
            PlayerDeath(); //calls player death method
        }
        else{
            story.gameText.mainTextArea.append(
                "\n\nIt's your turn! What would you like to do?"
            ); //sets text

            story.buttonList = new ArrayList<GameButton>();
            story.buttonList.add(new GameButton(story, "Attack") { //button setup
                @Override
                
                public void buttonFunction() {
                    PlayerAttack();
                    //calls player attack method
                }
            });
            story.buttonList.add(new GameButton(story, "Guard") {
                @Override
                public void buttonFunction() {
                    PlayerGuard();
                    //calls player guard method
                }
            });
            story.buttonList.add(new GameButton(story, "Use Inventory") {
                @Override
                public void buttonFunction() {
                    PlayerUseItem();
                    //calls player use item method
                }
            });
            story.buttonList.add(new GameButton(story, "Retreat") {
                @Override
                public void buttonFunction() {
                    PlayerFlee();
                    //calls player flee method
                }
            });
        }

        story.refreshButtonPanel(); //refreshes buttons
        story.hudSetup(); //refreshes player hud
    }







    public void PlayerAttack(){
        Pguard = false; //sets player guard to false when attacking

        if(story.combat.Eactions.Eguard){
        story.gameText.mainTextArea.setText(
                "You swung your " + story.player.current_weapon.name +"! You did " + (story.player.damage + damageAddOn) + " damage on the monster.\nThe monster guarded for a reduced 15 damage."
        ); //sets text when enemy guard is up
        story.combat.current_enemy.setEHealth( story.combat.current_enemy.getEHealth() - (story.player.damage + damageAddOn-15)); //reduces player attack by 15 damage
        System.out.println("Eguard : " + story.combat.current_enemy.getEHealth()); //debug statment
        story.combat.Eactions.Eguard = false; //sets enemy guard to false
        }
        else{
          story.gameText.mainTextArea.setText(
                "You swung your " + story.player.current_weapon.name +"! You did " + (story.player.damage + damageAddOn) + " damage on the monster."
        ); //sets text
        story.combat.current_enemy.setEHealth( story.combat.current_enemy.getEHealth() - (story.player.damage + damageAddOn)); //sets enemy health
        System.out.println("No Guard: " + story.combat.current_enemy.getEHealth());//debug statement
        }
        
        damageAddOn = 0; //resets damage addon after 1 attack
        //game button arraylist
        story.buttonList = new ArrayList<GameButton>();
        //continue button
        story.buttonList.add(new GameButton(story, "Continue"){
            @Override
            public void buttonFunction(){
                story.combat.Eactions.EnemyOptions();
                //goes to enemy options
            }
        });
        story.refreshButtonPanel(); //refreshes buttons
        story.combat.EnemyHudSetup(); //refresehes enemy hud
    }





    
    public void PlayerGuard(){
        //sets player guard to true when guarding
        Pguard = true;
        story.gameText.mainTextArea.setText(
                "You have put up your guard! All damage will be blocked from the enemies next attack."
        ); //sets text

        //game button arraylist
        story.buttonList = new ArrayList<GameButton>();
        //continue button
        story.buttonList.add(new GameButton(story, "Continue"){
            @Override
            public void buttonFunction(){
                story.combat.Eactions.EnemyOptions();
                //goes to enemy options
            }
        });
        story.refreshButtonPanel(); //refreshes buttons
    }







    
    public void PlayerUseItem(){
        story.gameText.mainTextArea.setText(
                "Which item would you like to use/equip?"
        ); //sets text

        story.buttonList = new ArrayList<GameButton>();
        //creates buttons
        story.buttonList.add(new GameButton(story, "Get Damage Potions"){
            @Override
            public void buttonFunction(){
              story.inventory.getDamagePotions();
              //gets list of damage potions
            }
        });
        story.buttonList.add(new GameButton(story, "Get Health Potions"){
            @Override
            public void buttonFunction(){
                story.inventory.getHealthPotions();
                //gets list of health potions
            }
        });
        story.buttonList.add(new GameButton(story, "Get Weapons"){
            @Override
            public void buttonFunction(){
              story.inventory.getSwords();
              //gets list of swords
            }
        });
        story.buttonList.add(new GameButton(story, "cancel" ) {
            @Override
            public void buttonFunction() {
              story.combat.Pactions.PlayerOptions();
              //cancel button goes back to player options
            }
        });
        story.refreshButtonPanel(); //refreshes buttons
    }




    public void PlayerFlee(){

        story.gameText.mainTextArea.setText("You were overwhelmed by the monster. You decided on a tactical retreat");//sets text

        story.buttonList = new ArrayList<GameButton>();
        //continue button
        story.buttonList.add(new GameButton(story, "Continue"){
            @Override
            public void buttonFunction(){
                story.combat.enemy_hud.setVisible(false);
                //sends you to spawn location
                story.player.setLocation(0);
                story.current_place();
            }
        });
        story.refreshButtonPanel(); //refreshes button
        
    }





    void PlayerDeath(){
      //player death method
        story.buttonList = new ArrayList<GameButton>();
        story.gameText.mainTextArea.setText(    
            "You died. The banished terrain has prevailed once again."
        );//sets text, player death message
        story.buttonList.add(new GameButton(story, "Continue"){
            @Override
            //continue button
            public void buttonFunction(){
                story.combat.enemy_hud.setVisible(false);
                //clears the current save slot
                story.save.clearSlot();
                System.out.println("This is working");//debug statement 

                story.MainMenu(); //goes to the main menu after
            }
        });
        story.refreshButtonPanel(); //refreshes buttons
    }





    public void PlayerHealthPotion(int HealthIncrease){
      //increase the health by the amount the potion provides
        story.player.setHealth(HealthIncrease); 
        
        story.gameText.mainTextArea.setText("You drank a instant health potion. your health has increased to "+ story.player.health);
        //sets text

        story.buttonList = new ArrayList<GameButton>();
        //continue button
        story.buttonList.add(new GameButton(story, "Continue"){
            @Override
            public void buttonFunction(){
                story.combat.Pactions.PlayerOptions(); //goes back to player options
            }
        });
        story.refreshButtonPanel(); //refreshes buttons
        return;
    }




    public void PlayerDamagePotion(int DamageIncrease){
      //temporarily increases damage by amount the potion provides
        damageAddOn = damageAddOn + DamageIncrease;
        story.gameText.mainTextArea.setText("You drank a damage potion. Your damage has increased to " + (story.player.damage + damageAddOn) +" attack points for one attack"); //sets text

        story.buttonList = new ArrayList<GameButton>();
        story.buttonList.add(new GameButton(story, "Continue"){
          //continue button
            @Override
            public void buttonFunction(){
                story.combat.Pactions.PlayerOptions();
                //goes to player options
            }
        });
        story.refreshButtonPanel(); //refreshes buttons
        return;
    }





    public void PlayerEquipSword(int DamageIncrease){
      //permently increases damage
      story.player.setDamage(DamageIncrease);
      story.gameText.mainTextArea.setText("You equiped a sword. Your damage will permently increase to "+ story.player.damage + " attack points"); //sets text
      story.player.setWeapon("Sword");
      //sets player weapon to sword so it shows in hud

      story.buttonList = new ArrayList<GameButton>();
      //continue button
      story.buttonList.add(new GameButton(story, "Continue"){
          @Override
          public void buttonFunction(){
            //goes back to player options
              story.combat.Pactions.PlayerOptions();
          }
      });
      story.refreshButtonPanel(); //refreshes buttons
      return;
    }


}