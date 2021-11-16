/* 
* GameScreen.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: to create the master game text screen
* Methods: none
*/
package UI;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class GameScreen extends JPanel{

    public JTextArea mainTextArea;
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 20);


    //if user has custom dimensions, they can do it, but if not, it will do the defaults
    public GameScreen() {

        //main mainTextPanel
        //this.setBounds(100, 100, 600, 200);
        this.setBounds(100, 115, 600, 200);
        this.setBackground(Color.black);
        //window.add(mainTextPanel);

        //actual text stuff
        mainTextArea = new JTextArea("");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);
        this.add(mainTextArea);
    }
}
