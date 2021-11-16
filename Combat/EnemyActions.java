/* 
* EnemyActions.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: to determine what the enemy is going to do
* Methods: EnemyOptions(), EnemyAttack(), EnemyDeath(), EnemyGuard()
*/
package Combat;
import Enemies.*;
import PlayerFunc.*;
import UseFunc.*;
import java.util.*;
import GameWorldFunc.*;
import UI.*;

public class EnemyActions{
    
    Story story; //story universalizes functionality
    public boolean Eguard = false; //public variable Eguard. used to state whether or not the enemy is guarding
    //int mon_health;

    public EnemyActions(Story story_current) {
       this.story = story_current;
    }


    public void EnemyOptions(){
        //mon_health = story.combat.current_enemy.getEHealth();
        int chooseAction; //enemy choice statement
        if(story.combat.current_enemy.getEHealth() <= 0){
          System.out.println("Mon health = " + story.combat.current_enemy.getEHealth()); //debug statement
          chooseAction = 100; //sets the action to death
        }
        else{
          chooseAction = UsefulFunctions.getRandomNumber(1,6);
          //randomizes action. 1 in 6 chance to guard, 5 in 6 chance to attack
        }
        //switch statement to switch between Methods
        switch (chooseAction) { 
          case 1: 
            EnemyGuard();
            break;
          case 100: 
            EnemyDeath();
            break;
          default:
            EnemyAttack();
            break;
        }
        story.hudSetup(); //refreshes hud
    }


    void EnemyAttack(){
      Eguard = false; //removes enemy guard when they attack

        if(story.combat.Pactions.Pguard){
          story.gameText.mainTextArea.setText(    
            "The " +story.combat.current_enemy.getEName()+ " attacks! You have taken " + story.combat.current_enemy.getEDamage() + " damage!\nYour guard blocked the damage"
        ); //sets the text for when player guard is up
          
          story.combat.Pactions.Pguard = false;
          //removes damage from enemy attack and sets your guard to false
        }
        else{
          //sets player damage
          story.player.health = story.player.health - story.combat.current_enemy.getEDamage();
           story.gameText.mainTextArea.setText(    
            "The " +story.combat.current_enemy.getEName()+ " attacks! You have taken " + story.combat.current_enemy.getEDamage() + " damage!"); //sets text for when player guard is not up
        }

        story.buttonList = new ArrayList<GameButton>();
        //continue button
        story.buttonList.add(new GameButton(story, "Continue"){
            @Override
            public void buttonFunction(){
                
                story.combat.Pactions.PlayerOptions(); //goes back to player options

            }
        });
        story.refreshButtonPanel(); //refreshes buttons
    }
    
    
    void EnemyGuard(){
        Eguard = true; //sets guard to true
        story.gameText.mainTextArea.setText("The enemy has put up his guard"); //sets text
        
        story.buttonList = new ArrayList<GameButton>();
        //continue button
        story.buttonList.add(new GameButton(story, "Continue"){
            @Override
            public void buttonFunction(){
                story.combat.Pactions.PlayerOptions(); //goes to player options
            }
        });
        story.refreshButtonPanel();//refresehes buttons
    }


    void EnemyDeath(){
        
        story.gameText.mainTextArea.setText(    
            "You killed the monster. Good job"
        ); //enemy death message
        story.buttonList = new ArrayList<GameButton>();
        //continue button
        story.buttonList.add(new GameButton(story, "Continue"){
            @Override
            public void buttonFunction(){
                //disables HUD
                story.combat.enemy_hud.setVisible(false);

                story.world.rooms.get(story.playerLocation).completeRoom();
                 //sets room to complete
                 System.out.println(story.world.rooms.size());
                 if(story.world.rooms.get(8).getCompletionStatus()){
                    story.celebrate();
                 }
                 else{
                    story.player.openChest(5,10); //opens a chest
                 }
            }
        });
        story.refreshButtonPanel(); //refreshes buttons
    }

}