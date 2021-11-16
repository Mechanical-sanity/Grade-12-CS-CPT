/* 
* Main.java
* Sidharth Sreeram, Michael Kim, Luca Mancuso, Jeremy Xue
* ICS 4U1
* June 18 2021
* Purpose: to activate the game in story.java
* Methods: none
*/
import GameWorldFunc.Story;
import UI.UI;

public class Main {

    /*
    - Title screen is in UI. Everything else is in GameWorldFunc
    */
    public static void main(String [] args){
        new Story(); //activates game
    }

}
