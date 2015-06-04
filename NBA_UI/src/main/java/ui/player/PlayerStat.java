package ui.player;

import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyTab;

public class PlayerStat extends JPanel{
	private PanelConfig pcfg;
	private HomeUI frame;
	private MyTab stat;
	
	public PlayerStat(HomeUI frame){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(),pcfg.getY());
		
		initComponent();
	}
	
	private void initComponent(){
		initTabs();
	}
	
	private void initTabs(){
		stat = new MyTab(pcfg.getTab().element("stat"));
		add(stat);
	}
}
