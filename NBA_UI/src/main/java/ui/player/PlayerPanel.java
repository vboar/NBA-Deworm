package ui.player;

import java.awt.Image;

import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.util.MyButton;
import ui.util.MyLabel;

public class PlayerPanel extends JPanel {

	private PanelConfig pcfg ;
	private HomeUI frame;
	private Image bg;
	
	private PlayerAllTablePane table;
	private MyButton screen;
	
	private MyButton avg;
	private MyButton all;
	
	
	public PlayerPanel(HomeUI frame){
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
		initTable();
		initButtons();
	}
	
	//table的内容
	private void initTable(){
		Object[][] data2 = new Object[5][5];
		for(int i=0;i<4;i++){
			for(int j= 0 ;j<4;j++){
				data2[i][j] =1;
			}
		}
			table = new PlayerAllTablePane(new TableConfig(pcfg.getTablepane()), data2);
			add(table);
	}
	
	
	private void initButtons(){
		
		screen = new MyButton(pcfg.getButtons().element("screen"), true);
		all = new MyButton(pcfg.getButtons().element("all"), true);
		avg = new MyButton(pcfg.getButtons().element("avg"), true);
		
		add(screen);
		add(all);
		add(avg);
	}
	
	
	
}
