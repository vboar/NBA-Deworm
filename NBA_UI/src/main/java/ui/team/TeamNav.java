package ui.team;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.team.compare.TeamCompare;
import ui.team.compare.TeamCompareChoosePanel;
import ui.util.MyLabel;

public class TeamNav extends JPanel{
	private PanelConfig pcfg;
	private HomeUI frame;
	private Image bg;
	
	private MyLabel index;
	private MyLabel teamstat;
	private MyLabel hotteam;
	private MyLabel compare;
	
	int show =0;
	
	public TeamNav(HomeUI frame){
	this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
			.get(this.getClass().getName());
	this.bg = pcfg.getBg();
	this.frame = frame;
	this.setLayout(null);
	this.setSize(pcfg.getW(),pcfg.getH());
	this.setLocation(pcfg.getX(), pcfg.getY());
	
	initComponent();
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	public void initComponent(){
		initLabels();
	}
	
	private void initLabels(){
		index = new MyLabel(pcfg.getLabels().element("index"));
		index.setIcon(new ImageIcon("img/team/nav/index_click.png"));
		index.addMouseListener(new MouseAdapter(){
			String[] temp = pcfg.getLabels().element("index").attributeValue("path").split("\\.");
			String path = temp[0];
			String fix = temp[1];
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				index.setIcon(new ImageIcon(path+"_click."+fix));
				show = 0;
				teamstat.setIcon(new ImageIcon(pcfg.getLabels().element("teamstat").attributeValue("path")));
				hotteam.setIcon(new ImageIcon(pcfg.getLabels().element("hotteam").attributeValue("path")));
				compare.setIcon(new ImageIcon(pcfg.getLabels().element("compare").attributeValue("path")));
				
				frame.motherPanel.teamPanel.removeAll();
				frame.motherPanel.teamPanel.teamindex = new TeamIndex(frame);
				frame.motherPanel.teamPanel.add(frame.motherPanel.teamPanel.teamindex);
				frame.motherPanel.teamPanel.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				index.setIcon(new ImageIcon(path+"_click."+fix));	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(show!=0)
				index.setIcon(new ImageIcon(path+"."+fix));
		
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				index.setIcon(new ImageIcon(path+"_click."+fix));
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				index.setIcon(new ImageIcon(path+"_click."+fix));
				
			}
			
		});
		teamstat = new MyLabel(pcfg.getLabels().element("teamstat"));
		teamstat.addMouseListener(new MouseAdapter(){
			String[] temp = pcfg.getLabels().element("teamstat").attributeValue("path").split("\\.");
			String path = temp[0];
			String fix = temp[1];
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				teamstat.setIcon(new ImageIcon(path+"_click."+fix));
				show =1;
				index.setIcon(new ImageIcon(pcfg.getLabels().element("index").attributeValue("path")));
				hotteam.setIcon(new ImageIcon(pcfg.getLabels().element("hotteam").attributeValue("path")));
				compare.setIcon(new ImageIcon(pcfg.getLabels().element("compare").attributeValue("path")));

				//更改teampanel内容
				frame.motherPanel.teamPanel.removeAll();
				frame.motherPanel.teamPanel.teamstat = new TeamStat(frame);
				frame.motherPanel.teamPanel.add(frame.motherPanel.teamPanel.teamstat);
				frame.motherPanel.teamPanel.updateUI();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				teamstat.setIcon(new ImageIcon(path+"_click."+fix));	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(show!=1)
					teamstat.setIcon(new ImageIcon(path+"."+fix));
		
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				teamstat.setIcon(new ImageIcon(path+"_click."+fix));
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				teamstat.setIcon(new ImageIcon(path+"_click."+fix));
				
			}
			
		});
		hotteam = new MyLabel(pcfg.getLabels().element("hotteam"));
		hotteam.addMouseListener(new MouseAdapter(){
			String[] temp = pcfg.getLabels().element("hotteam").attributeValue("path").split("\\.");
			String path = temp[0];
			String fix = temp[1];
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hotteam.setIcon(new ImageIcon(path+"_click."+fix));
				show = 2;
				index.setIcon(new ImageIcon(pcfg.getLabels().element("index").attributeValue("path")));
				teamstat.setIcon(new ImageIcon(pcfg.getLabels().element("teamstat").attributeValue("path")));
				compare.setIcon(new ImageIcon(pcfg.getLabels().element("hotteam").attributeValue("path")));

				frame.motherPanel.teamPanel.removeAll();
				frame.motherPanel.teamPanel.teamhot = new TeamHot(frame);
				frame.motherPanel.teamPanel.add(frame.motherPanel.teamPanel.teamhot);
				frame.motherPanel.teamPanel.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				hotteam.setIcon(new ImageIcon(path+"_click."+fix));	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(show!=2)
					hotteam.setIcon(new ImageIcon(path+"."+fix));
		
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				hotteam.setIcon(new ImageIcon(path+"_click."+fix));
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				hotteam.setIcon(new ImageIcon(path+"_click."+fix));
				
			}
			
		});
		compare = new MyLabel(pcfg.getLabels().element("compare"));
		compare.addMouseListener(new MouseAdapter(){
			String[] temp = pcfg.getLabels().element("compare").attributeValue("path").split("\\.");
			String path = temp[0];
			String fix = temp[1];
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				compare.setIcon(new ImageIcon(path+"_click."+fix));
				show = 3;
				index.setIcon(new ImageIcon(pcfg.getLabels().element("index").attributeValue("path")));
				teamstat.setIcon(new ImageIcon(pcfg.getLabels().element("teamstat").attributeValue("path")));
				hotteam.setIcon(new ImageIcon(pcfg.getLabels().element("hotteam").attributeValue("path")));
				
				frame.motherPanel.teamPanel.removeAll();
				frame.motherPanel.teamPanel.comparechoose = new TeamCompareChoosePanel(frame);
				frame.motherPanel.teamPanel.add(frame.motherPanel.teamPanel.comparechoose);
				frame.motherPanel.teamPanel.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				compare.setIcon(new ImageIcon(path+"_click."+fix));	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(show!=3)
					compare.setIcon(new ImageIcon(path+"."+fix));
		
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				compare.setIcon(new ImageIcon(path+"_click."+fix));
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				compare.setIcon(new ImageIcon(path+"_click."+fix));
				
			}
			
		});
		add(index);
		add(teamstat);
		add(hotteam);
		add(compare);
	}
}
