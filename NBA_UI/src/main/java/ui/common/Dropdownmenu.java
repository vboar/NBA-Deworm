package ui.common;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyButton;
import ui.util.MyLabel;

public class Dropdownmenu extends JPanel{
	private HomeUI frame;
	private PanelConfig pcfg;
	private Image bg;
	
	private MyLabel background;
	
	private MyButton player;
	private MyButton team;
	private MyButton match;
	private MyButton stats;
	private MyButton home;
	
	public Dropdownmenu(HomeUI frame){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(),pcfg.getY());
		this.setVisible(false);
		initComponent();
	}
	
	public void initComponent(){
		initButtons();
		initLabels();
	}
	
	private void initLabels(){
		background = new MyLabel(pcfg.getLabels().element("bg"));
		add(background);
	}
	private void initButtons(){
		player = new MyButton(pcfg.getButtons().element("player"),true);
		team = new MyButton(pcfg.getButtons().element("team"),true);
		match = new MyButton(pcfg.getButtons().element("match"),true);
		stats = new MyButton(pcfg.getButtons().element("stats"),true);
		home = new MyButton(pcfg.getButtons().element("home"),true);
		home.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
                frame.motherPanel.fillMenu();
                setVisible(false);
                frame.motherPanel.setVisible(false);
				frame.home.setVisible(true);;
				frame.repaint();
			}
			
		});
		add(player);
		add(team);
		add(match);
		add(stats);
		add(home);

        player.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.motherPanel.fillComponents(1);
                frame.motherPanel.fillMenu();
                setVisible(false);
            }
        });

        team.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.motherPanel.fillComponents(2);
                frame.motherPanel.fillMenu();
                setVisible(false);
            }
        });

        match.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.motherPanel.fillComponents(3);
                frame.motherPanel.fillMenu();
                setVisible(false);
            }
        });

        stats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.motherPanel.fillComponents(4);
                frame.motherPanel.fillMenu();
                setVisible(false);
            }
        });
	}
}
