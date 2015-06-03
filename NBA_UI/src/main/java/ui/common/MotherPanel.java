package ui.common;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.live.LivePanel;
import ui.match.MatchNav;
import ui.player.PlayerNav;
import ui.player.PlayerPanel;
import ui.team.TeamNav;
import ui.team.TeamPanel;
import ui.util.MyButton;

public class MotherPanel extends JPanel {

	private PanelConfig pcfg ;
	private HomeUI frame;
	private Image bg;
	
	private MyButton back;
	private MyButton menu;
	private MyButton exit;
	private MyButton min;
	private MyButton max;
	
	public Dropdownmenu dropdown;
	
	public PlayerNav playernav; 
	public TeamNav teamnav;
	public MatchNav matchnav;
	public PlayerPanel playerPanel;
	public TeamPanel teamPanel;
	
	public LivePanel livePanel;
	
	int show = 0;
	
	int panel;
	public MotherPanel(HomeUI frame,int panel){
		this.panel = panel;
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		// 设置布局管理器为自由布局
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.bg = pcfg.getBg();
		// 初始化组件
		this.initComponent();
		this.repaint();
		
	}

	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	private void initComponent(){
		initPanels();
		initButtons();
	}
	
	private void initPanels(){
		dropdown = new Dropdownmenu(frame);
		dropdown.setVisible(false);
		
		playernav = new PlayerNav();
		playerPanel = new PlayerPanel(frame);
		if(panel!=1){
			playernav.setVisible(false);
			playerPanel.setVisible(false);
		}
		
		teamnav = new TeamNav();
		teamPanel = new TeamPanel(frame);
		if(panel!=2){
			teamnav.setVisible(false);
			teamPanel.setVisible(false);
		}
		
		matchnav =new MatchNav();
		if(panel!=3){
			matchnav.setVisible(false);
		}
		
		livePanel = new LivePanel(frame);
		if(panel!=4){
			livePanel.setVisible(false);
		}

		
		add(dropdown);
		add(playernav);
		add(teamnav);
		add(matchnav);
		add(playerPanel);
		add(teamPanel);
		add(livePanel);

	}
	
	private void initButtons(){
		back = new MyButton(pcfg.getButtons().element("back"),true);
		add(back);
		
		menu = new MyButton(pcfg.getButtons().element("menu"));
		menu.addMouseListener(new MouseAdapter(){
			String[] temp = pcfg.getButtons().element("menu").attributeValue("path").split("\\.");
			String path = temp[0];
			String fix = temp[1];
			boolean isshow = false;
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(isshow == false){
				isshow = true;
				dropdown.setVisible(true);
				}else{
					isshow = false;
					dropdown.setVisible(false);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				menu.setIcon(new ImageIcon(path+"_point."+fix));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(isshow == false)
					menu.setIcon(new ImageIcon(path+"."+fix));
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				menu.setIcon(new ImageIcon(path+"_point."+fix));
				
			}
			
		});
		add(menu);
		
		exit = new MyButton(pcfg.getButtons().element("exit"),true);
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
		});
		add(exit);
		
		min = new MyButton(pcfg.getButtons().element("min"),true);
		min.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setExtendedState(frame.ICONIFIED);
				
			}
		});
		add(min);
		
		max = new MyButton(pcfg.getButtons().element("max"));
		add(max);
	}

}
