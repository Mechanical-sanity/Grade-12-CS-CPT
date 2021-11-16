/* 
* GameButton.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: to create usable buttons within the game screen
* Methods: buttonFunction();
*/
package UI;

import GameWorldFunc.Story;

import javax.swing.*;
import java.awt.*;

public class GameButton extends JButton{

    public Story story;
    public JButton choice;
    public int roomInt;
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 20);

    //used java Varargs for optional parameters
    public GameButton(Story story_button, String choice_name, Integer... roomNum){
        story = story_button;
        roomInt = roomNum.length > 0 ? roomNum[0] : 0; 

        choice = new JButton(choice_name); //sets the button name to what the code needs
        choice.setBackground(Color.black);
        choice.setForeground(Color.white);
        choice.setFont(normalFont); //sets font for the button (despite replit having no fonts)

        //created an lambda operation that can be overrided. When user presses button, it will do whatever the function does (which can be overridded)
        choice.addActionListener(e -> {
            buttonFunction();
        });
    }

    //specifically made to be overridded
    public void buttonFunction(){
        System.out.println("ERROR!"); //debug function
    }
}
