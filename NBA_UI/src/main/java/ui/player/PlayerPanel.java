package ui.player;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyTab;

public class PlayerPanel extends JPanel {

	private PanelConfig pcfg ;
	private HomeUI frame;
	
	private PlayerAllTablePane table;
	private PlayerIndex indexpanel;
	public PlayerFilter playerfilter;
	
	private JFrame coverFrame;
	
	private MyTab stat;
	
	private MyLabel settingbg;
	
	private MyButton setting;
	private MyButton menu;
	
	public PlayerPanel(HomeUI frame){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		// 设置布局管理器为自由布局
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		// 初始化组件
		this.initComponent();
		this.repaint();
		
	}
	
	private void initComponent(){
		initPanel();
		//initTable();
		//initButtons();
		initLabels();
		initTab();
		coverFrame = new JFrame();
		coverFrame.setBounds(457,221,690, 260);
		coverFrame.setUndecorated(true);
		coverFrame.add(playerfilter);
			
	}
	
	private void initPanel(){
		playerfilter = new PlayerFilter(frame);
		playerfilter.setVisible(false);
		add(playerfilter);	
		
		indexpanel = new PlayerIndex(frame);
		add(indexpanel);
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
	
	private void initLabels(){
		settingbg = new MyLabel(pcfg.getLabels().element("settingbg"));
		add(settingbg);
	}
	
	private void initTab(){
		stat = new MyTab(pcfg.getTab().element("stat"));
		add(stat);
	}
	
	private void initButtons(){
		menu = new MyButton(pcfg.getButtons().element("menu"),true);
		add(menu);
		
		setting = new MyButton(pcfg.getButtons().element("setting"));
		setting.addMouseListener(new MouseAdapter(){
			String[] temp = pcfg.getButtons().element("setting").attributeValue("path").split("\\.");
			String path = temp[0];
			String fix = temp[1];
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(playerfilter.isVisible() == false){
					playerfilter.setFocusable(true);
					coverFrame.setVisible(true);
					
					playerfilter.setVisible(true);
					coverFrame.setVisible(true);
					}else{
						playerfilter.setVisible(false);
						coverFrame.setVisible(false);
						
					}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(playerfilter.isVisible()==false)
				setting.setIcon(new ImageIcon(path+"_point."+fix));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(playerfilter.isVisible() == false)
					setting.setIcon(new ImageIcon(path+"."+fix));
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				setting.setIcon(new ImageIcon(path+"_click."+fix));
				
			}

		});
		add(setting);
	}

	public void filter(){
		setting.setIcon(new ImageIcon(pcfg.getButtons().element("setting").attributeValue("path")));
		playerfilter.setVisible(false);
		coverFrame.setVisible(false);
	}
}
