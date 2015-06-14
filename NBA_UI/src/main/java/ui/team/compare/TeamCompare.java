package ui.team.compare;

import java.awt.Image;

import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;

public class TeamCompare extends JPanel{
	private PanelConfig pcfg;
	private HomeUI frame;
	
	private String name1;
	private String name2;
	
	public TeamCompare(HomeUI frame,String name1,String name2){
		this.name1 = name1;
		this.name2 = name2;
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		// 设置布局管理器为自由布局
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		// 初始化组件
		//this.initComponent();
		this.repaint();
	} 
}
