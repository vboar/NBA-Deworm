package ui.match.stat;

import java.awt.Color;

import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;

public class CoverPanel extends JPanel {
	private PanelConfig pcfg ;
	private HomeUI frame;
	
	public CoverPanel(HomeUI frame){
	this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
			.get(this.getClass().getName());
	this.frame = frame;
	// 设置布局管理器为自由布局
	this.setBackground(Color.BLUE);
	this.setLayout(null);
	this.setSize(pcfg.getW(), pcfg.getH());
	this.setLocation(pcfg.getX(), pcfg.getY());
	// 初始化组件
	this.setVisible(true);
	this.repaint();
	}
}
