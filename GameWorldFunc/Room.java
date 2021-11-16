/* 
* Room.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: Room object hold room information
* Methods: addAdjacentRoom(), setDescription(), getDescription(), completedRoom(), getCompletionStatus();
*/
package GameWorldFunc;
import Enemies.BaseMonster;
//required imports
import java.util.ArrayList;
import java.util.*;
//import Enemies.BaseMonster;

public class Room{
    int roomId; //id that represents this room
    String roomName; //Name of the room
    String description; //Description of the room
    boolean completed; //states if the room is completed
    ArrayList<Integer>adjacentRooms; //an arraylist that contains all roomIds that this room can travel to
    ArrayList<BaseMonster>monsters; //an arraylist that contains all monsters within this room

    //constructor
    public Room(int roomId, String roomName){
        this.roomId = roomId; 
        this.roomName = roomName;
        this.adjacentRooms = new ArrayList<Integer>();
        this.completed = false;
    }
    public void addAdjacentRoom(int roomId){
        adjacentRooms.add(roomId);
    }
    //used to setup room layouts and which rooms one may travel between
    public void setDescription(String description){
        this.description = description;
    }
    //used to set the description of a room
    public String getDescription(){
        return this.description;
    }
    //used to get the description of a room
    public void completeRoom(){
        this.completed = true;
    }
    //used to set the completion of the room
    public boolean getCompletionStatus(){
        return this.completed;
    }
    //used to get the completion status
    public void resetRooms(){
      this.completed = false;
    }
    //used to rest room completion status
}