package ui.player;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyButton;

public class PlayerFilter extends JPanel{
	private HomeUI frame;
	private PanelConfig pcfg;
	
	private MyButton search;
	
	private Image bg;
	
	public PlayerFilter(HomeUI frame){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		this.bg = pcfg.getBg();
		
		this.setLayout(null);
		this.setSize(pcfg.getW(),pcfg.getH());
		this.setLocation(pcfg.getX(),pcfg.getY());
		
		this.initComponent();
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(),null);
	}
	
	public void initComponent(){
		initLabels();
		initButtons();
	}
	
	private void initLabels(){
		
	}
	
	private void initButtons(){
		search = new MyButton(pcfg.getButtons().element("search"),true);
		search.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.playerPanel.filter();
			}
			
		});
		add(search);
	}
}
