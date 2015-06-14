package ui.player.info;

import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;

public class ChartPanel extends JPanel {
	private PanelConfig pcfg;
	private HomeUI frame;

	
	public ChartPanel(HomeUI frame) {
		this.frame = frame;
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;

		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());

		this.initComponent();
	}
	
	private void initComponent(){
		
	}

}
