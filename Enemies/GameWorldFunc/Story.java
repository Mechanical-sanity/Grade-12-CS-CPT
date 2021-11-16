/* 
* Story.java
* Sidharth Sreeram, Michael Kim, Luca Mancuso, Jeremy Xue
* ICS 4U1
* June 18 2021
* Purpose: To control the main direction of the game
* Methods: defaultSetup(), hudSetup(), Switch(), refreshButtonPanel(), MainMenu(), name_define(), name_confirm(), spawnLocation(), current_place(), travelling_to(), travelRoom(), getCompletionStatus1(), continueButton(), ExamineRoom(), loadGameSlot(), saveGameSlot(), Exit(), rules().
*/
package GameWorldFunc; //import required packages and libraries
import java.io.*;
import Combat.*;
import PlayerFunc.*;
import Weapon.*;
import UI.*;
import Items.*;

import javax.swing.*;
import java.util.ArrayList;

public class Story {
    //Global Variables Start
    public UI ui; 
    public HUD hud;
    public StringInput stringInput;
    public ArrayList<GameButton> buttonList;
    public GameButtonPanel game_button_panel;
    public GameScreen gameText;

    public CombatSystem combat;
    public Player player;
    public Inventory inventory;

    public GameWorld world;
    ArrayList<Integer> adjacentRooms;
    public int room_int = 0;
    public int playerLocation;
    public Save save = new Save(this);

    //Global Variables end


    //Constructor
    public Story(){
        gameText = new GameScreen(); //new game screen

        buttonList = new ArrayList<GameButton>(); //creates new buttons
        game_button_panel = new GameButtonPanel(buttonList); //new game button panel. Just instantiating

        hud = new HUD();    

        ui = new UI(); //creates user interface
        ui.createUI(this);
    }

    /*
    the original default parameters for a new game.
     */
    public void defaultSetup(){
      //sets player
        player = new Player(this);
        //sets inventory
        inventory = new Inventory(this);
        //sets combat
        combat = new CombatSystem(this);
        //sets world
        world = new GameWorld(this);
        world.initializeWorld();
    }

    //refreshes the hud with the most current info
    public void hudSetup(){
      //HUD setup and updater
        this.hud.setVisible(false);
        this.ui.window.remove(this.hud);

        this.hud = new HUD(); //new HUD object
        //sets hud display
        this.hud.playername.setText("" + this.player.player_name);
        this.hud.hpNumberLabel.setText("" + this.player.health);
        this.hud.weaponNameLabel.setText("" + this.player.current_weapon.name);

        this.ui.window.add(this.hud);
        this.hud.setVisible(true);
        System.out.println("Player Hud is updating Health:" + this.player.health); //debugger statement
    }

    public void Switch(JPanel panel1, JPanel panel2) {
      //switch method. Michael comment here
        panel1.setVisible(false);
        ui.window.remove(panel1);
        ui.window.add(panel2);
        panel2.setVisible(true);
    }
    /*
    - resets the game panel. Disables it, then enables it under the new parameters. Done to refresh the image
    - also refreshes hud. 
    - .this is used to allow it to be used in other classes
    */
    public void refreshButtonPanel(){
      //required to refresh button layout
        this.game_button_panel.setVisible(false);
        this.ui.window.remove(this.game_button_panel);

        this.game_button_panel = new GameButtonPanel(this.buttonList);
        this.ui.window.add(this.game_button_panel);
        this.game_button_panel.setVisible(true);
        //this.hudSetup();
    }









    /*
    MAIN MENU!
    */
    /*
    - the actual options thing
    - most of the menu options are on the bottom of this file
    */
    public void MainMenu(){ 
        hud.setVisible(false);
        System.out.println("Main menu working here!");//debug statement 

        //intial main menu of game
        gameText.mainTextArea.setText("Welcome to the RPG Game"); //sets text

        //gamebutton arraylist
        buttonList = new ArrayList<GameButton>();
        //creates button and sets function inside
        buttonList.add(new GameButton(this, "Start new game"){
            @Override
            public void buttonFunction(){ //button function
                defaultSetup(); //sets up the game with default values
                story.name_define(); //gets the player to set up character
            }
        });
        //creates button and sets function inside
        buttonList.add(new GameButton(this, "Continue previous game"){
            @Override
            public void buttonFunction(){//button function
              story.loadGameSlot(); //goes to load game slot method
            }
        });
        //creates button and sets function inside
        buttonList.add(new GameButton(this, "Read the rules"){
            @Override
            public void buttonFunction(){//button function
                story.rules(); //goes to rules method
            }
        });
        //creates button and sets function inside
        buttonList.add(new GameButton(this, "Exit"){
            @Override
            public void buttonFunction(){//button function
              
              System.out.println("Goodbye, please come again"); //debugger statement
              //exits program
              System.exit(0);
            }
        });
        
        ui.window.add(gameText); //sets up hud/gui
        gameText.setVisible(true);

        refreshButtonPanel(); //refreshes buttons
    }


    public void name_define(){ 
      //used to set up name of character
        gameText.mainTextArea.setText("What is your name, traveller?");

        stringInput = (new StringInput(this) {
            @Override
            public void continue_next(){
                story.name_confirm(); //name confirm method
            }
        });

        ui.window.add(stringInput); //adds to window
        Switch(game_button_panel, stringInput); //switch method
    }


    public void name_confirm(){
        //if player writes nothing
        if( (stringInput.input_string.getText()).equals("") ){
            gameText.mainTextArea.setText(//sets text
                    "Hey! You can't write nothing!"
            );

            buttonList = new ArrayList<GameButton>();
            buttonList.add(new GameButton(this, "Continue"){
                @Override
                //continue button
                public void buttonFunction(){
                    story.name_define();
                }
            });
        }
        else{
            player.player_name = stringInput.input_string.getText();
            gameText.mainTextArea.setText( //sets text
                    "So your name is " + player.player_name + ", is it? Strange... I could swear I've heard it before."
            );

            //game button arraylist
            buttonList = new ArrayList<GameButton>();
            buttonList.add(new GameButton(this, "Continue"){
                @Override
                public void buttonFunction(){ //continue button
                    story.spawnLocation();
                }
            });
        }
        refreshButtonPanel(); //refreshes buttons
        Switch(stringInput, game_button_panel); //switch method
    }






   

    public void spawnLocation(){
        gameText.mainTextArea.setText(
                "Hello " + player.player_name + " and welcome to The Banished Terrain. "
                +
                "\n\nTip: Enemies sometimes do not always appear in rooms. Because you need to kill an enemy in that room, you should come back later. They might be there."
        );

        //gamebutton arraylist
        buttonList = new ArrayList<GameButton>();
        buttonList.add(new GameButton(this, "Continue"){//continue button
            @Override
            public void buttonFunction(){
                story.current_place();
            }
        });

        //sets up room layout
        adjacentRooms = world.getadjacentroomList(playerLocation);
        
        hudSetup(); //sets up hud
        refreshButtonPanel(); //refreshes buttons
    }








    //only 3 adjacent rooms will be available at any given time
    public void current_place(){
      //main during game interface, allows player to travel, or find monster
        hudSetup();
        playerLocation = player.getLocation(); //gets player location
        gameText.mainTextArea.setText( //sets text
                "You are currently in " + world.roomIdToName.get(playerLocation)
        );
        refreshButtonPanel(); //refreshes buttons
        //game button arraylist
        buttonList = new ArrayList<GameButton>(); //creates new buttons
        buttonList.add(new GameButton(this, "Examine current room"){
            @Override
            //calls examine room method when pressed
            public void buttonFunction(){
                story.ExamineRoom();
                
            }
        });

        for(int i = 0; i < adjacentRooms.size(); i++){
            buttonList.add(new GameButton(this, ("Go to " + world.roomIdToName.get(adjacentRooms.get(i) )), i){
                @Override
                //calls travelling _to method when called
                public void buttonFunction(){
                    story.room_int = roomInt;
                    story.travelling_to();
                }
            });
        }
        

        buttonList.add(new GameButton(this, "Get room Completion Status"){
            @Override
            public void buttonFunction(){
              //displays completion status of each room
              story.getCompletionStatus1();
            }
        });

        buttonList.add(new GameButton(this, "Exit"){
            @Override
            //goes to exit method
            public void buttonFunction(){
              story.Exit();
            }
        });

        refreshButtonPanel(); //refreshes buttons
    }





    //handles all of the actual transfer of the integers
    public void travelling_to(){
        //gamebutton arraylist
      
      if(playerLocation == 5 && room_int == 3){ //if going to boss room
            
      boolean allowBossRoom = true; //boolean that allows travel into boss room
      for(int i = 0; i < world.rooms.size()-1; i++){
        if(!((world.rooms.get(i)).getCompletionStatus())){
          allowBossRoom = false; //for loop traverses rooms and checks if complete
        }
      }

      if(allowBossRoom){
          gameText.mainTextArea.setText("Welcome to your worst nightmare. Are you truly willing to continue? "); //sets text
          
          buttonList = new ArrayList<GameButton>(); //new buttons
          buttonList.add(new GameButton(this, "Yes"){
              @Override
              //yes i want to battle the demon lord
              public void buttonFunction(){
                  gameText.mainTextArea.setText("HAHAHAHAHA. I like your guts. Come and we shall battle till the end" + "\nYou are now travelling to " + world.roomIdToName.get(adjacentRooms.get(room_int)));
                  //states which room you are traveling to and then travels to it
                  story.travelRoom(); //travels to boss room
                  story.combat.BossRoom(); //starts boss battle
              }
          });
          buttonList.add(new GameButton(this, "No"){
              @Override
              //no i don't want to battle the boss
              public void buttonFunction(){
                  gameText.mainTextArea.setText("HAHAHA. Weak, even after all that effort. Come back when you have the guts");
                  //goes back to previous room (mountains)
                  current_place();
                  
              }
          });
                   
          refreshButtonPanel();
      }
      else{
        //if not completed all rooms
        gameText.mainTextArea.setText("HAHAHAHAHA. Does such a tiny insect truly wish to battle against the demon lord of the banished terrain. Come back after completing all rooms. HAHAHAHAHAHA");
        continueButton(); //continue button
      }

    }
    ///////////////////////

      else{
        gameText.mainTextArea.setText(
                  "You are now travelling to " + world.roomIdToName.get(adjacentRooms.get(room_int))
          );
          //if not travelling to boss room
          travelRoom();
        
      }
    }

    //travel to room

    public void travelRoom(){
      buttonList = new ArrayList<GameButton>();
      buttonList.add(new GameButton(this, "Continue"){ 
        //continue button
          @Override
          public void buttonFunction(){
              //room int is the i stored above
              //sets new player location
              player.setLocation(adjacentRooms.get(room_int));

              //updates playerLocation
              playerLocation = player.getLocation();
              //finds new adjacent rooms to display
              adjacentRooms = world.getadjacentroomList(playerLocation);

              current_place(); //since current place is set to the new place, we will then travel to the new locations
          }
      });
      refreshButtonPanel(); //refreshes buttons
    }

    public void getCompletionStatus1(){
      //gets room completion status in list format
      refreshButtonPanel();
      buttonList = new ArrayList<GameButton>();

        gameText.mainTextArea.setText("Here are the following room completion statuses\nClick on one to continue. ");
        //sets up the statuses in button format
        for(int i = 0; i < world.rooms.size(); i++){
          buttonList.add(new GameButton(this, (world.roomIdToName.get(i) +": "+ (world.rooms.get(i)).getCompletionStatus() + "\n"), i){
                @Override
                  public void buttonFunction(){
                      current_place();
                      //when pressed move back to current location
                  }
          
            });
          
        }

        game_button_panel.setVisible(false);
        ui.window.remove(game_button_panel);
        //used to create custom display box
        //x, y, width, height
        game_button_panel = new GameButtonPanel(buttonList, 250, 300, 350, 225);
        ui.window.add(game_button_panel);
        game_button_panel.setVisible(true);
    }

    
    

    public void continueButton(){ //continue button method
      buttonList = new ArrayList<GameButton>();
      buttonList.add(new GameButton(this, "Continue"){
          @Override
          public void buttonFunction(){
              story.current_place();
          }
      });
      refreshButtonPanel();
    }

    //examines the room
    public void ExamineRoom(){ //examines the room
        String currentroomDescription = world.getRooms().get(player.getLocation()).getDescription();
        gameText.mainTextArea.setText(currentroomDescription);
        //displays the current room description
        

        //game button arraylist
        buttonList = new ArrayList<GameButton>();
        //continue button
        buttonList.add(new GameButton(this, "Continue"){
            @Override
            public void buttonFunction(){
                story.combat.initiateFight();
                //when searching you encounter a monster
            }
        });
        refreshButtonPanel(); //refreshes buttons
    }











   
  public void loadGameSlot(){ //loads saved game slot
    gameText.mainTextArea.setText("Please select a save slot");
      //refreshButtonPanel();
        //gamebutton arraylist
        buttonList = new ArrayList<GameButton>();
        //new buttons for which save slot
        buttonList.add(new GameButton(this, "Save Slot 1"){
            @Override
            public void buttonFunction(){
                story.save.loadProgress(1);
                //slot 1
            }
        });
        buttonList.add(new GameButton(this, "Save Slot 2"){
            @Override
            public void buttonFunction(){
                story.save.loadProgress(2);
                //slot 2
            }
        });
        buttonList.add(new GameButton(this, "Save Slot 3"){
            @Override
            public void buttonFunction(){
                story.save.loadProgress(3);
                //slot 3
            }
        });
        buttonList.add(new GameButton(this, "Save Slot 4"){
            @Override
            public void buttonFunction(){
                story.save.loadProgress(4);
                //slot 4
            }
        });
        buttonList.add(new GameButton(this, "Go back"){
            @Override
            public void buttonFunction(){
                story.MainMenu();
                //goes back to main menu
            }
        });
        refreshButtonPanel(); //refreshes buttons
  }

  public void saveGameSlot(){ //saves games in slot
    gameText.mainTextArea.setText("Please select a save slot");
      //refreshButtonPanel();
        //gamebutton arraylist
        buttonList = new ArrayList<GameButton>();
        //buttons for choosing slot
        buttonList.add(new GameButton(this, "Save Slot 1"){
            @Override
            public void buttonFunction(){
                story.save.saveProgress(1); 

                //slot 1
            }
        });
        buttonList.add(new GameButton(this, "Save Slot 2"){
            @Override
            public void buttonFunction(){
                story.save.saveProgress(2);
                //slot 2
            }
        });
        buttonList.add(new GameButton(this, "Save Slot 3"){
            @Override
            public void buttonFunction(){
                story.save.saveProgress(3);
                //slot 3
            }
        });
        buttonList.add(new GameButton(this, "Save Slot 4"){
            @Override
            public void buttonFunction(){
                story.save.saveProgress(4);
                //slot 4
            }
        });
        buttonList.add(new GameButton(this, "Go back"){
            @Override
            public void buttonFunction(){
                story.MainMenu();
                //goes back to main menu
            }
        });
        refreshButtonPanel(); //refreshes buttons
    }

    public void Exit(){
      gameText.mainTextArea.setText("Would you like to save progress?"); //exit method gives options to save or not
      //refreshButtonPanel();
        //gamebutton arraylist
        buttonList = new ArrayList<GameButton>();
        //buttons to choose yes or no
        buttonList.add(new GameButton(this, "Yes"){
            @Override
            public void buttonFunction(){
                story.saveGameSlot(); //yes then go to saveGameslot in save.java
            }
        });
        buttonList.add(new GameButton(this, "No"){
            @Override
            public void buttonFunction(){
                gameText.mainTextArea.setText("Thank you for playing");
                story.MainMenu();
                //no then exits program
            }
        });
      
        refreshButtonPanel();
    }

  public void rules(){
    gameText.mainTextArea.setText("OBJECTIVE : Explore the world, gather weapons and potions until you are able to defeat the demon lord. Items will be inside chests after defeating room monsters. At least one kill is required per room before attempting to fight the demon lord. Spend some time gathering items to increase your health and total damage in order to kill the dungeon lord easily. \nNOTE: Monsters will not appear unless you search the room for them. They will respawn, so farming is an option.");
    //sets the text to display the rules
    
    buttonList = new ArrayList<GameButton>();
      buttonList.add(new GameButton(this, "go back"){
          @Override
          public void buttonFunction(){
              story.MainMenu();
              //button takes you back to main menu
          }
      });
      refreshButtonPanel(); //refreshes buttons

  }
  
  public void celebrate(){
    System.out.println("boich celebrate is working");
    gameText.mainTextArea.setText("Great job. The Demon lord has been sealed away from another million years. Thank you hero for saving this world, come back whenever you want to and claim your glory once again");
    //congratulations message

    buttonList = new ArrayList<GameButton>();
      buttonList.add(new GameButton(this, "go back"){
          @Override
          public void buttonFunction(){
              story.MainMenu();
              //button takes you back to main menu
          }
      });
      refreshButtonPanel(); //refreshes buttons
  }
}
