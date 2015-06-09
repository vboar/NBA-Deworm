package ui.match;

import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;

public class MatchStat extends JPanel{

	private PanelConfig pcfg;
	private HomeUI frame;
	
	public MatchStat(HomeUI frame){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(),pcfg.getY());
		
		initComponent();
	}
	
	private void initComponent(){
		initLabels();
	}
	
	private void initLabels(){
		
	}
	
}
