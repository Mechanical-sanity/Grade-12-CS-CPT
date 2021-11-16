/* 
* GameWorld.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: to set up the world layout
* Methods: makeAdjacent(), initializeWorld(), getRooms() 
*/
package GameWorldFunc;
import java.util.*;
import Combat.*;
import Enemies.*;
import UseFunc.*;
import Items.*;


public class GameWorld{
    Story story;

    public ArrayList<Chest> chest = new ArrayList<Chest>();
    //spawns chests within the game world

    public ArrayList<String>roomIdToName; //maps room id to their respective room name. The index is the room id and the String is the room name.

    public ArrayList<Room>rooms; //arraylist of all rooms

    //method that makes room1 and room2 adjacent using the method from Room class. room1 and room2 are the index of the room in the arraylist.
    //constructor
    public GameWorld(Story story_current){
        this.roomIdToName = new ArrayList<String>();
        this.rooms = new ArrayList<Room>();
        story = story_current;
    }

    //used to setup adjacent rooms and create room layout
    public void makeAdjacent(int room1, int room2){
        rooms.get(room1).addAdjacentRoom(room2);
        rooms.get(room2).addAdjacentRoom(room1);
        //sets the room object to have these specific adjacent rooms
    }

    //method that returns a list of rooms the user can go to from the current room
    public ArrayList<Integer> getadjacentroomList(int currentroomId){
        return rooms.get(currentroomId).adjacentRooms;
    }
    //returns entire room array
    public ArrayList<Room> getRooms(){
        return this.rooms;
    }
    //used to initialize the entire world layout
    public void initializeWorld(){
        //The id of the room also corresponds to its index in the arraylist.
        roomIdToName.add("Spawn");  //0
        roomIdToName.add("Smooth Pathway"); //1
        roomIdToName.add("Forest"); //2
        roomIdToName.add("River"); //3
        roomIdToName.add("Town"); //4
        roomIdToName.add("Mountains"); //5. can go to world boss. 
        roomIdToName.add("Cave"); //6
        roomIdToName.add("Ocean"); //7
        roomIdToName.add("World Boss"); //8
        //Iterate through the hashmap roomToIdName using entryset
        for(int id = 0; id < roomIdToName.size(); id++){
            String roomName = roomIdToName.get(id);
            Room newRoom = new Room(id,roomName);
            rooms.add(newRoom);
        }
        //sets description of each room
        rooms.get(0).setDescription("This is the beginning area of the map... nothing special here");
        rooms.get(1).setDescription("The first step to your adventure");
        rooms.get(2).setDescription("You see several treehouse-worthy trees");
        rooms.get(3).setDescription("Filled with cool looking rocks!");
        rooms.get(4).setDescription("A small town with simple villagers");
        rooms.get(5).setDescription("You hear something loud and scary nearby...");
        rooms.get(6).setDescription("Cold and dank, but not too bad.");
        rooms.get(7).setDescription("Fishies.");
        rooms.get(8).setDescription("The final battle. Good luck.");

        //sets up adjacent rooms
        makeAdjacent(0,1); // Spawn <-> Smooth Pathway
        makeAdjacent(1,2); // Smooth Pathway <-> Forest
        makeAdjacent(2,3); // Forest <-> River
        makeAdjacent(3,4); // River <-> Town
        makeAdjacent(3,6); // River <-> Cave
        makeAdjacent(4,5); // Town <-> Mountains
        makeAdjacent(4,6); // Town <-> Cave
        makeAdjacent(5,6); // Mountains <-> Cave
        makeAdjacent(5,7); // Mountains <-> Ocean
        makeAdjacent(6,7); // Cave <-> Ocean
        makeAdjacent(5,8); // Mountains <-> World Boss

        
    }



}