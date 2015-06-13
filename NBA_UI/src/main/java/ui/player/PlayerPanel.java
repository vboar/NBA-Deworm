package ui.player;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.player.hot.PlayerHotPane;
import ui.player.index.PlayerIndex;
import ui.player.info.PlayerInfoPane;
import ui.player.stat.PlayerFilter;
import ui.player.stat.PlayerStat;
import ui.util.MyButton;
import ui.util.MyLabel;

public class PlayerPanel extends JPanel {

	private PanelConfig pcfg ;
	private HomeUI frame;
	
	
	public PlayerIndex indexpanel;
	public PlayerFilter playerfilter;
	public PlayerStat playerstat;
	public PlayerInfoPane playerInfoPane;
	public PlayerHotPane playerHotPane;
	private JFrame coverFrame;
	
	private MyLabel settingbg;
	
	public MyButton setting;
	public MyButton menu;
	
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
		initCover();
		
		initButtons();
		initLabels();
	}
	
	
	private void initPanel() {
		playerfilter = new PlayerFilter(frame);
		playerfilter.setVisible(false);
		add(playerfilter);	
		
		indexpanel = new PlayerIndex(frame);
		//indexpanel.setVisible(false);
		add(indexpanel);
		
		playerstat = new PlayerStat(frame);
		playerstat.setVisible(false);
		add(playerstat);
		
		playerInfoPane =new PlayerInfoPane(frame);
		playerInfoPane.setVisible(false);
		add(playerInfoPane);
		
		playerHotPane = new PlayerHotPane(frame);
		playerHotPane.setVisible(false);
		add(playerHotPane);
	}
	
	private void initCover(){
		coverFrame = new JFrame();
		coverFrame.setBounds(458,218,690, 260);
		coverFrame.setUndecorated(true);
		coverFrame.add(playerfilter);
		coverFrame.setAlwaysOnTop(true);	
	}
	
	//table的内容
	
	
	private void initLabels(){
		settingbg = new MyLabel(pcfg.getLabels().element("settingbg"));
		add(settingbg);
	}
	
	
	private void initButtons(){
		menu = new MyButton(pcfg.getButtons().element("menu"),true);
		menu.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
//				playerfilter.changeBox(true);
//				playerstat.state = 2;
//				playerfilter.setAdvTable();				
			}
		});
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
