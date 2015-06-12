package ui.team;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.home.HomeUI;

public class TeamHot extends JPanel{
	
	public TeamHot(HomeUI frame){
		this.setSize(500, 300);
		this.setLocation(50, 150);
		this.add(new JButton("test"));
	}
}
