/* 
* HUD.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: to display the main details of the player
* Methods: none
*/
package UI;

import javax.swing.*;
import java.awt.*;

public class HUD extends JPanel{

    public JLabel playername, hpLabel, hpNumberLabel, weaponLabel, weaponNameLabel;
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 25);

    public HUD(){
        //player HUD bar
        //x, y, width, height
        this.setBounds(100, 15, 600, 50);
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.setLayout(new GridLayout(1, 5) );

        playername = new JLabel();
        playername.setFont(normalFont);
        playername.setForeground(Color.white);
        this.add(playername);

        hpLabel = new JLabel("         HP:");
        hpLabel.setFont(normalFont);
        hpLabel.setForeground(Color.white);
        this.add(hpLabel);

        hpNumberLabel = new JLabel();
        hpNumberLabel.setFont(normalFont);
        hpNumberLabel.setForeground(Color.white);
        this.add(hpNumberLabel);

        weaponLabel = new JLabel("Weapon:");
        weaponLabel.setFont(normalFont);
        weaponLabel.setForeground(Color.white);
        this.add(weaponLabel);

        weaponNameLabel = new JLabel();
        weaponNameLabel.setFont(normalFont);
        weaponNameLabel.setForeground(Color.white);
        this.add(weaponNameLabel);

    }
}
