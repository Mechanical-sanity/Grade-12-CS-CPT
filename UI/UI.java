/* 
* .java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: to start the main user interface
* Methods: createUI(), titlescreen()
*/
package UI;

import GameWorldFunc.Story;

import javax.swing.*;
import java.awt.*;

public class UI {

    public JFrame window;
    public JPanel titleNamePanel, startButtonPanel;
    public JLabel titleNameLabel;
    public JButton startButton;

    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);


    public void createUI(Story story) {
        //window parameters
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        titlescreen(story);
        window.setVisible(true);
    }

    public void titlescreen(Story story) {
        //title parameters
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);

        titleNameLabel = new JLabel("ADVENTURE");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);

        //start button parameters. Created it without the gamebuttons and game button panel for testing purposes. 
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.setFocusPainted(false);
        startButtonPanel.add(startButton);

        startButton.addActionListener(e -> {
            titleNamePanel.setVisible(false);
            startButtonPanel.setVisible(false);

            window.remove(titleNamePanel);
            window.remove(startButtonPanel);
            
            story.MainMenu();
        });

        //the starter stuff for the actual window
        window.add(titleNamePanel);
        window.add(startButtonPanel);
    }


}



