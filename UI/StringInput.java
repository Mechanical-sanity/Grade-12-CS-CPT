/* 
* StringInput.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: to take in user keyboard input
* Methods: continue_next(), clear()
*/
package UI;

import GameWorldFunc.Story;

import javax.swing.*;
import java.awt.*;

public class StringInput extends JPanel{

    public JButton submit;
    public JTextField input_string;
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    public Story story;

    public StringInput(Story story){
        this.story = story;

        this.setBounds(250, 350, 350, 150);
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.setLayout(new GridLayout(2, 1));
        //window.add(this);

        //user String input
        input_string = new JTextField(""); // this is so that users can input whatever string they want
        input_string.setBackground(Color.black);
        input_string.setForeground(Color.white);
        input_string.setFont(normalFont);
        this.add(input_string);

        submit = new JButton("Continue");
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.setFont(normalFont);
        this.add(submit);

        submit.addActionListener(e -> {
            continue_next();
        });

    }

    public void continue_next(){
        System.out.println("ERROR!");
    }

    public void clear(){
        input_string.setText("");
    }
}
