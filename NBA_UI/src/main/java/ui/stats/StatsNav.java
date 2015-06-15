package ui.stats;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyLabel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Vboar on 2015/6/13.
 */
public class StatsNav extends JPanel {

    private HomeUI frame;
    private PanelConfig pcfg;
    private Image bg;

    private MyLabel stat1;
	private MyLabel stat2;
	
	public int show =0;
	
    public StatsNav(HomeUI frame){
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

    private void initLabels() {
    	stat1 = new MyLabel(pcfg.getLabels().element("stat1"));
    	stat1.setIcon(new ImageIcon("img/stat/nav/stat1_click.png"));
    	stat1.addMouseListener(new MouseAdapter(){
			String[] temp = pcfg.getLabels().element("stat1").attributeValue("path").split("\\.");
			String path = temp[0];
			String fix = temp[1];
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				stat1.setIcon(new ImageIcon(path+"_click."+fix));
				stat2.setIcon(new ImageIcon(pcfg.getLabels().element("stat2").attributeValue("path")));
				show = 0;

				frame.motherPanel.statsPanel.stat1.setVisible(true);
                frame.motherPanel.statsPanel.stat1.backFirst();
				frame.motherPanel.statsPanel.stat2.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				stat1.setIcon(new ImageIcon(path+"_click."+fix));	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(show!=0)
					stat1.setIcon(new ImageIcon(path+"."+fix));
		
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				stat1.setIcon(new ImageIcon(path+"_click."+fix));
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				stat1.setIcon(new ImageIcon(path+"_click."+fix));
                frame.motherPanel.matchPanel.switchPanel(0);
			}
			
		});
    	stat2 = new MyLabel(pcfg.getLabels().element("stat2"));
    	stat2.addMouseListener(new MouseAdapter(){
			String[] temp = pcfg.getLabels().element("stat2").attributeValue("path").split("\\.");
			String path = temp[0];
			String fix = temp[1];
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				stat2.setIcon(new ImageIcon(path+"_click."+fix));
				show =1;
				stat1.setIcon(new ImageIcon(pcfg.getLabels().element("stat1").attributeValue("path")));

				frame.motherPanel.statsPanel.stat1.setVisible(false);
				frame.motherPanel.statsPanel.stat2.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				stat2.setIcon(new ImageIcon(path+"_click."+fix));	
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(show!=1)
					stat2.setIcon(new ImageIcon(path+"."+fix));
		
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				stat2.setIcon(new ImageIcon(path+"_click."+fix));
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				stat2.setIcon(new ImageIcon(path+"_click."+fix));

            }

        });
		
		add(stat1);
		add(stat2);
		}
}
