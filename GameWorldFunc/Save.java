/* 
* Save.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: To save the current progress in text files
* Methods: saveProgress(), loadProgress(), clearSlot()
*/
package GameWorldFunc; //required package and imports
import java.util.*;
import java.io.*;
import Items.*;
import UI.*;
import javax.swing.*;
import java.util.ArrayList;
import Combat.*;


public class Save{

    //global variables and objects
    public File HP; //file declaration
    public File DP;
    public File W;
    public File stats;
    public File room;
    
    public BufferedReader readHP; //buffered reader declaration
    public BufferedReader readDP;
    public BufferedReader readW;
    
    public BufferedReader readStats;
    
    public BufferedReader readRoom;

    public BufferedWriter writeHP;//buffered writer declaration
    public BufferedWriter writeDP;
    public BufferedWriter writeW;
    
    public BufferedWriter writeStats;
    
    public BufferedWriter writeRoom;

    Story story; //story controller object

    public int current_slot; //current slot integer

    //constructor
    public Save(Story story_current){
        story = story_current;
    }

    //saves progress method
    public void saveProgress(int saveSlot){
      HP = new File("GameWorldFunc/SaveFiles/PlayerHP" + saveSlot + ".txt");
      DP = new File("GameWorldFunc/SaveFiles/PlayerDP" + saveSlot + ".txt");
      W = new File("GameWorldFunc/SaveFiles/PlayerW" + saveSlot + ".txt"); 
      stats = new File("GameWorldFunc/SaveFiles/PlayerStats" + saveSlot + ".txt");
      room = new File("GameWorldFunc/SaveFiles/RoomStatus" + saveSlot + ".txt");

      //save slot integer saves the data in different files

      try{
        // try-catch for prevent program crashing
      //creates the txt file if it does not exist
        HP.createNewFile();
        DP.createNewFile();
        W.createNewFile();
        stats.createNewFile();
        room.createNewFile();

        
        //sets up buffered writers
        writeHP = new BufferedWriter(new FileWriter(HP));
        writeDP = new BufferedWriter(new FileWriter(DP));
        writeW = new BufferedWriter(new FileWriter(W));
        writeStats = new BufferedWriter(new FileWriter(stats));
        writeRoom = new BufferedWriter(new FileWriter(room));

        

        for(int i = 0; i < story.inventory.HP_list.size(); i++){
          //for loop traverses health potions and saves value in file
          writeHP.write(story.inventory.HP_list.get(i).getIncrease() + " ");

        }
          writeHP.flush(); //flushes writer
        for(int i = 0; i < story.inventory.DP_list.size(); i++){
          //for loop traverses damage potions and save value in file
          writeDP.write(story.inventory.DP_list.get(i).getIncrease() + " ");
        }
          writeDP.flush();//flushes writer
        for(int i = 0; i < story.inventory.W_list.size(); i++){
          //for loop traverses sword array and save value in file
          writeW.write(story.inventory.W_list.get(i).getIncrease() + " ");
        }
          writeW.flush();
        for(int i = 0; i < story.world.rooms.size(); i++){
          //travers the room array and saves completion status
          if(story.world.rooms.get(i).getCompletionStatus()){
            writeRoom.write("1 ");
          }
          else{
            writeRoom.write("0 ");
          }  
        }
          writeRoom.flush();//flushes writer

          //saves the data seperated by a space
        writeStats.write(story.player.player_name + " " + story.player.damage + " " + story.player.getHealth() + " " + story.player.getLocation() + " "+ story.player.current_weapon.name);
          writeStats.flush();//flushes writer
      }
      catch(Exception e){
        System.out.println("An error occurred"); //debugger statement
      }

      
      story.MainMenu(); //leaves game after
    }


    public void loadProgress( int saveSlot){
      
      HP = new File("GameWorldFunc/SaveFiles/PlayerHP" + saveSlot + ".txt");
      DP = new File("GameWorldFunc/SaveFiles/PlayerDP" + saveSlot + ".txt");
      W = new File("GameWorldFunc/SaveFiles/PlayerW" + saveSlot + ".txt"); 
      stats = new File("GameWorldFunc/SaveFiles/PlayerStats" + saveSlot + ".txt");
      room = new File("GameWorldFunc/SaveFiles/RoomStatus" + saveSlot + ".txt");

      try{
        story.defaultSetup(); //sets up everything
        System.out.println("d");  
        
        System.out.println("a");
        story.hudSetup(); //sets up hud
        System.out.println("h");
        

        if(stats.length() == 0){
          System.out.println("stats error");
                throw new IllegalArgumentException();
            }
        else{
          current_slot = saveSlot; //sets current load slot to 
            }

        //sets up buffered reader
        readHP = new BufferedReader(new FileReader(HP));
        readDP = new BufferedReader(new FileReader(DP));
        readW = new BufferedReader(new FileReader(W));
        readStats = new BufferedReader(new FileReader(stats));
        readRoom = new BufferedReader(new FileReader(room));
        String word2;
        //creates a string for every line in the file
        while((word2 = readHP.readLine())!=null){
          String[] str = word2.split(" ");
          //seperates the string into an array of strings based on spaces
            for(int i = 0; i < str.length; i++){
              //traverses the string array to read every value
            story.inventory.addItem(new Item("Health", 5, 10));
          
            story.inventory.HP_list.get(i).setIncrease(Integer.parseInt(str[i]));
            //creates health potion and sets the increase value based off file
          
            }
        
        
          }
        System.out.println("hp");
          //creates a string for every line in the file             
         while((word2 = readDP.readLine())!=null){
          String[] str = word2.split(" ");
            //seperates the string into an array of strings based on spaces 
            for(int i = 0; i < str.length; i++){
              //traverses the string array to read every value            
            story.inventory.addItem(new Item("Damage", 5, 10));
          
            story.inventory.DP_list.get(i).setIncrease(Integer.parseInt(str[i]));
              //creates damage potion and sets the increase value based off file            
            }
        
        
           }
           System.out.println("dp");
           //creates a string for every line in the file   
         while((word2 = readW.readLine())!=null){
          String[] str = word2.split(" ");
            //seperates the string into an array of strings based on spaces 
            for(int i = 0; i < str.length; i++){
                //traverses the string array to read every value     
            story.inventory.addItem(new Item("Sword", 5, 10));
          
            story.inventory.W_list.get(i).setIncrease(Integer.parseInt(str[i]));
              //creates swords and sets the increase value based off file  
            }
        
        
          }
          System.out.println("w");
         //creates a string for every line in the file   
        while((word2 = readRoom.readLine())!=null){
          //seperates the string into an array of strings based on spaces 
          String[] str = word2.split(" ");
          
          for(int i = 0; i < str.length; i++){  
           //traverses the string array to read every value  
           int n = Integer.parseInt(str[i]);
           //System.out.println(n);
           if(n == 1){
            story.world.rooms.get(i).completeRoom();
            //sets the room to complete if the file value is one
           }
           
           
        }
        }
          System.out.println("room");//debug point
        while((word2 = readStats.readLine())!=null){
          //creates a string for every line in the file
          String[] str = word2.split(" ");

          story.player.player_name = str[0];
          //seperates string into array
          story.player.setDamageAbs(Integer.parseInt(str[1]));
          story.player.setHealthAbs(Integer.parseInt(str[2]));
          story.player.setLocation(Integer.parseInt(str[3]));
          story.playerLocation = story.player.getLocation();
          story.player.setWeapon(str[4]);
          //reades and sets values accordingly
        }
        System.out.println("stats");
        
        story.adjacentRooms = story.world.getadjacentroomList(story.playerLocation);
        story.refreshButtonPanel(); //refreshes buttons
        story.current_place();
        //goes to current_place in story.java
        
    }
    
    catch(Exception e){
      story.gameText.mainTextArea.setText("This save slot has no data. Please select another one");
      
      return;
    }
    }



    public void clearSlot(){
      HP = new File("GameWorldFunc/SaveFiles/PlayerHP" + current_slot + ".txt");
      DP = new File("GameWorldFunc/SaveFiles/PlayerDP" + current_slot + ".txt");
      W = new File("GameWorldFunc/SaveFiles/PlayerW" + current_slot + ".txt"); 
      stats = new File("GameWorldFunc/SaveFiles/PlayerStats" + current_slot + ".txt");
      room = new File("GameWorldFunc/SaveFiles/RoomStatus" + current_slot + ".txt");
      //sellects the current file slot
      try{
        // try-catch for prevent program crashing
      //creates the txt file if it does not exist
        HP.createNewFile();
        DP.createNewFile();
        W.createNewFile();
        stats.createNewFile();
        room.createNewFile();

        

        writeHP = new BufferedWriter(new FileWriter(HP));
        writeDP = new BufferedWriter(new FileWriter(DP));
        writeW = new BufferedWriter(new FileWriter(W));
        writeStats = new BufferedWriter(new FileWriter(stats));
        writeRoom = new BufferedWriter(new FileWriter(room));
        //makes buffered writers for every file
        writeHP.write("");
        writeHP.flush();
        writeDP.write("");
        writeDP.flush();
        writeW.write("");
        writeW.flush();
        writeRoom.write("");
        writeRoom.flush();
        writeStats.write("");
        writeStats.flush();
        //writes a blank in each file to clear them.
      }
      catch(Exception e){
        System.out.println("There was an error in clearSlot"); //debug statement
      }
    }


    
}