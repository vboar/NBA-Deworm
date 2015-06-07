package ui.team;

import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyLabel;

public class TeamDetail extends JPanel{
	private PanelConfig pcfg;
	private HomeUI frame;
	
	private MyLabel basicinfo;
	private MyLabel cseason;
	
	public TeamDetail(HomeUI frame){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		
		initComponent();
	} 
	
	private void initComponent(){
		
	}
}
