package model;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class PersonalizadeJbutton extends JButton {
	
	public  PersonalizadeJbutton(String IconRoad) {
		this.setIcon(new ImageIcon(IconRoad));
		this.setBackground(Color.white);
		setBorder(new LineBorder(Color.white));
		
	}
}
