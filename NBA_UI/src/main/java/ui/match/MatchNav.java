package ui.match;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.live.LiveChoosePane;
import ui.util.MyLabel;

public class MatchNav extends JPanel{
	
	private HomeUI frame;
	private PanelConfig pcfg;
	private Image bg;
	
	private MyLabel matchstat;
	private MyLabel live;
	
	
    // live 为 1
	public int show =0;
	
	public MatchNav(HomeUI frame){
		this.frame = frame;
	this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
			.get(this.getClass().getName());
	this.bg = pcfg.getBg();
	
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
		matchstat = new MyLabel(pcfg.getLabels().element("matchstat"));
        matchstat.setIcon(new ImageIcon("img/match/nav/stat_click.png"));
		matchstat.addMouseListener(new MouseAdapter(){
			String[] temp = pcfg.getLabels().element("matchstat").attributeValue("path").split("\\.");
			String path = temp[0];
			String fix = temp[1];
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				matchstat.setIcon(new ImageIcon(path+"_click."+fix));
                live.setIcon(new ImageIcon(pcfg.getLabels().element("live").attributeValue("path")));
				show = 0;

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				matchstat.setIcon(new ImageIcon(path+"_click."+fix));	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(show!=0)
					matchstat.setIcon(new ImageIcon(path+"."+fix));
		
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				matchstat.setIcon(new ImageIcon(path+"_click."+fix));
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				matchstat.setIcon(new ImageIcon(path+"_click."+fix));
                frame.motherPanel.matchPanel.switchPanel(0);
			}
			
		});
		live = new MyLabel(pcfg.getLabels().element("live"));
		live.addMouseListener(new MouseAdapter(){
			String[] temp = pcfg.getLabels().element("live").attributeValue("path").split("\\.");
			String path = temp[0];
			String fix = temp[1];
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				live.setIcon(new ImageIcon(path+"_click."+fix));
				show =1;
				matchstat.setIcon(new ImageIcon(pcfg.getLabels().element("matchstat").attributeValue("path")));
				frame.motherPanel.matchPanel.switchPanel(1);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				live.setIcon(new ImageIcon(path+"_click."+fix));	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(show!=1)
					live.setIcon(new ImageIcon(path+"."+fix));
		
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				live.setIcon(new ImageIcon(path+"_click."+fix));
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				live.setIcon(new ImageIcon(path+"_click."+fix));
				
			}
			
		});
		
		add(matchstat);
		add(live);
	}

    public void recoverFirst() {
        matchstat.setIcon(new ImageIcon("img/match/nav/stat_click.png"));
        live.setIcon(new ImageIcon("img/match/nav/live.png"));
        revalidate();
        repaint();
    }
	
	
}
