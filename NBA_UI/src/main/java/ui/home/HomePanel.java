package ui.home;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.util.MyButton;

public class HomePanel extends JPanel {

	private PanelConfig pcfg ;
	private HomeUI frame;
	private Image bg;
	
	private TestTablePane table;
	
	private MyButton exit;
	private MyButton min;
	
	private MyButton player;
	private MyButton team;
	private MyButton match;
	private MyButton stats;
	
	public HomePanel(HomeUI frame){
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
	
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	private void initComponent(){
		initTable();
		initButtons();
	}
	
	private void initTable(){
		Object[][] data2 = new Object[7][7];
		for(int i=0;i<7;i++){
			for(int j= 0 ;j<7;j++){
				data2[i][j] =1;
			}
		}
	
		table = new TestTablePane(new TableConfig(pcfg.getTablepane()), data2);
		add(table);
	}
	private void initButtons(){
		exit = new MyButton(pcfg.getButtons().element("exit"),true);
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
		});
		add(exit);
		
		min = new MyButton(pcfg.getButtons().element("min"),true);
		min.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setExtendedState(frame.ICONIFIED);
				
			}
		});
		add(min);
		
		player = new MyButton(pcfg.getButtons().element("player"), true);
		add(player);
		
		team = new MyButton(pcfg.getButtons().element("team"), true);
		add(team);
		
		match = new MyButton(pcfg.getButtons().element("match"), true);
		add(match);
		
		stats = new MyButton(pcfg.getButtons().element("stats"), true);
		add(stats);
		
		
		
	}
}
