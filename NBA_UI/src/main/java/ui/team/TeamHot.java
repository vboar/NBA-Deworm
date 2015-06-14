package ui.team;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;

public class TeamHot extends JPanel{

	private PanelConfig pcfg;
	private Image bg;

	
	public TeamHot(HomeUI frame){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.bg = pcfg.getBg();
		
		this.setLayout(null);
		this.setSize(pcfg.getW(),pcfg.getH());
		this.setLocation(pcfg.getX(),pcfg.getY());
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
}
