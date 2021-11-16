/* 
* EnemyHUD.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: to display enemy hud
* Methods: constructor only
*/
package UI;

import javax.swing.*;
import java.awt.*;



public class EnemyHUD extends JPanel{

    public JLabel EnemyName, enemy_hpLabel, enemy_hpNumberLabel, AttackLabel, AttackNumberLabel;
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 25); //repl.it does not display font, but add just in case something changes

    public EnemyHUD(){
        //player HUD setBackground
        this.setBounds(100, 55, 600, 50); //sets size as such (x, y, width, height)
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.setLayout(new GridLayout(1, 5) ); //one row, 5 columns

        EnemyName = new JLabel(); //empty so that it can be edited later
        EnemyName.setFont(normalFont);
        EnemyName.setForeground(Color.white);
        this.add(EnemyName); //adding it to the panel. Do not need to delcare panel because this is a panel

        enemy_hpLabel = new JLabel("   Foe HP:");
        enemy_hpLabel.setFont(normalFont);
        enemy_hpLabel.setForeground(Color.white);
        this.add(enemy_hpLabel); 

        enemy_hpNumberLabel = new JLabel();
        enemy_hpNumberLabel.setFont(normalFont);
        enemy_hpNumberLabel.setForeground(Color.white);
        this.add(enemy_hpNumberLabel); 

        AttackLabel = new JLabel("Attack:");
        AttackLabel.setFont(normalFont);
        AttackLabel.setForeground(Color.white);
        this.add(AttackLabel); 

        AttackNumberLabel = new JLabel();
        AttackNumberLabel.setFont(normalFont);
        AttackNumberLabel.setForeground(Color.white);
        this.add(AttackNumberLabel); 
    }
}
