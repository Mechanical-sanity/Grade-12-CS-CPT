/* 
* GameButtonPanel.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: to setup the button size and look
* Methods: none
*/
package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameButtonPanel extends JPanel{

    ArrayList<JButton> choices_list = new ArrayList<JButton>();

    //4 optional parameters which will set button panel location on screen and size
    public GameButtonPanel(ArrayList<GameButton> ButtonList, Integer... dimensions) {
        int size1 = dimensions.length > 0 ? dimensions[0] : 0;
        int size2 = dimensions.length > 1 ? dimensions[1] : 0;
        int size3 = dimensions.length > 2 ? dimensions[2] : 0;
        int size4 = dimensions.length > 3 ? dimensions[3] : 0;

        //main mainTextPanel
        if (size4 == 0){
            //default stuff
            this.setBounds(250, 350, 350, 150);
        }
        else{
            //x, y, width, height
            this.setBounds(size1, size2, size3, size4);
            System.out.println(size4);
        }
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.setLayout(new GridLayout(ButtonList.size(), 1));

        for (int i = 0; i < ButtonList.size(); i++) {
            //adds a button for every button in the arry list that is in the parameters
            this.add( ButtonList.get(i).choice );
        }
    }
}
