package ui.live;

import java.awt.Image;

import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.player.PlayerAllTablePane;
import ui.util.MyLabel;

public class LivePanel extends JPanel {
	
	private PanelConfig pcfg ;
	private HomeUI frame;
	private Image bg;
	
	private MyLabel score1;
	private MessageTablePane table;

	public static boolean islive = true;
	public LivePanel(HomeUI frame){
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
	
	private void initComponent(){
		initLabels();
		initTable();
	}
	
	private void initLabels(){
		score1 = new MyLabel(pcfg.getLabels().element("score1"));
		add(score1);
	}
	
	private void initTable(){
		Object[][] data2 = new Object[9][5];
		for(int i=0;i<9;i++){
			for(int j= 0 ;j<4;j++){
				data2[i][0] ="12:00";
				data2[i][1] ="勇士";
				data2[i][2] ="博古特和霍华德跳球，德雷蒙德-格林得到篮球";
				data2[i][3] ="0-0";
			}
		}
			table = new MessageTablePane(new TableConfig(pcfg.getTablepane()), data2);
			add(table);
	}
	
	
	
}
