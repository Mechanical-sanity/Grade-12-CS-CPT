/* 
* CombatSystem.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: to initiate battles between player and monster
* Methods: EnemyHudSetup(), initiateFight(), PlayerVsMon(), MonVsPlayer(), BossRoom()
*/
package Combat;
import UI.*;
import GameWorldFunc.*;
import Enemies.*;
import UseFunc.*;
import Items.*;
import java.util.*;
import Combat.*;
import PlayerFunc.*;
//required packages and imports

public class CombatSystem{
    Story story; //story object to universalize function
    EnemyHUD enemy_hud;

    //defining a object of type basemonster
    BaseMonster current_enemy; 

    //for calling player actions
    public PlayerActions Pactions;
    
    //for calling enemy actions
    public EnemyActions Eactions;

    
    //constructor
    public CombatSystem(Story story_current){
        this.story = story_current;
        Pactions = new PlayerActions(story_current);
        Eactions = new EnemyActions(story_current);
        enemy_hud = new EnemyHUD();
    }
    
    

    //refreshes the hud with the most current info
    public void EnemyHudSetup(){
        enemy_hud.setVisible(false);
        story.ui.window.remove(enemy_hud);

        enemy_hud = new EnemyHUD();
        enemy_hud.EnemyName.setText("" + current_enemy.getEName());
        enemy_hud.enemy_hpNumberLabel.setText("" + current_enemy.getEHealth());
        enemy_hud.AttackNumberLabel.setText("" + current_enemy.getEDamage());

        story.ui.window.add(enemy_hud);
        enemy_hud.setVisible(true);
        System.out.println("Enemy HUD");
    }
    
    /*
    - previous system did not work, which is why part of it still exists, and this takes it's place.
    */
    public void initiateFight(){
      int chance = UsefulFunctions.getRandomNumber(0, 2);
      //randomizes monster encounter
      int which_enemy = UsefulFunctions.getRandomNumber(0, 5);
      //sets random monster of different strenghts and weaknesses
      switch(which_enemy){
        case 0: current_enemy = new Monster1(40, 5, "Imp");
        break;
        case 1: current_enemy = new Monster1(50, 10, "Goblin");
        break;
        case 2: current_enemy = new Monster1(20, 30, "Orc");
        break;
        case 3: current_enemy = new Monster1(20, 25, "Demon" );
        break;
        case 4: current_enemy = new Monster1(30, 10, "Knight");
        break;
        case 5: current_enemy = new Monster1(60, 5, "Kobold");
        break;
        default: current_enemy = new Monster1(40, 20, "BRO");
        break; 
      }
      
      
      if(chance == 0){ //you find monster and attack first
        PlayerVsMon();
      }
      else if(chance == 1){//you get attacked by monster first
          MonVsPlayer();
      }
      else{
          story.current_place(); //you don't encounter monster
      }
      
    }








    public void PlayerVsMon(){ 
        
        story.gameText.mainTextArea.setText(    
            "An " + story.combat.current_enemy.getEName() + " appeared! Fight it to clear this room"
        );//sets text
        story.buttonList = new ArrayList<GameButton>();
        //continue button
        story.buttonList.add(new GameButton(story, "Continue"){
          @Override
          public void buttonFunction(){
              System.out.println("PlayerActions"); //debug statement
              story.combat.Pactions.PlayerOptions(); //starts player options
          }
        });
        story.refreshButtonPanel(); //refreshes buttons
        story.hudSetup(); //refreshes hud
        EnemyHudSetup(); //refresehes enemy hud
    }

    public void MonVsPlayer(){
        story.gameText.mainTextArea.setText(    
            "An " + story.combat.current_enemy.getEName() + " attacked you from behind. Fight back to clear this room"
        ); //sets text
        story.buttonList = new ArrayList<GameButton>();
        //continue button
        story.buttonList.add(new GameButton(story, "Continue"){
          @Override
          public void buttonFunction(){ 
              System.out.println("EnemyActions");//debug statement 
              story.combat.Eactions.EnemyOptions(); //enemy options
          }
        });
        story.refreshButtonPanel(); //refreshes buttons
        story.hudSetup(); //refreshes player hud
        EnemyHudSetup();
    }





    public void BossRoom(){
        story.gameText.mainTextArea.setText(    
            "You have encountered the Demon lord of the Banished Terrain. Defeat him to save all of humanity."
        ); //sets text
        current_enemy = new BossMonster(); //creates new boss monster

        story.buttonList = new ArrayList<GameButton>();
        //continue button
        story.buttonList.add(new GameButton(story, "Continue"){
          @Override
          public void buttonFunction(){
              System.out.println("bossmonster");
              story.combat.Eactions.EnemyOptions(); //gives enemy first option
          }
        });
        story.refreshButtonPanel(); //refreshes buttons
        story.hudSetup();//refreshes player hud
        EnemyHudSetup(); //refreshes enemy hud
    }

}