package ui.home;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import ui.common.MotherPanel;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.util.MyButton;

public class HomePanel extends JPanel {

	private PanelConfig pcfg ;
	private HomeUI frame;
	private Image bg;
	
	private TestTablePane table;
	
	private MyButton exit;
	private MyButton min;
	private MyButton max;
	
	private MyButton player;
	private MyButton team;
	private MyButton match;
	private MyButton stats;
	
	private MotherPanel motherPanel;
	
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
		initButtons();
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

		max = new MyButton(pcfg.getButtons().element("max"));
		add(max);
		
		player = new MyButton(pcfg.getButtons().element("player"), true);
		player.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.home.setVisible(false);
				
				motherPanel = new MotherPanel(frame,1);
				frame.motherPanel = motherPanel;
				frame.getContentPane().add(motherPanel);
				
				frame.repaint();
			}

			});
		add(player);
		
		team = new MyButton(pcfg.getButtons().element("team"), true);
		team.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.home.setVisible(false);
				
				motherPanel = new MotherPanel(frame,2);
				frame.motherPanel = motherPanel;
				frame.getContentPane().add(motherPanel);
				
				frame.repaint();
			}

			});
		add(team);
		
		match = new MyButton(pcfg.getButtons().element("match"), true);
		match.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.home.setVisible(false);
				
				motherPanel = new MotherPanel(frame,3);
				frame.motherPanel = motherPanel;
				frame.getContentPane().add(motherPanel);
				
				frame.repaint();
			}

			});
		add(match);
		
		stats = new MyButton(pcfg.getButtons().element("stats"), true);
		stats.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.home.setVisible(false);
				
				motherPanel = new MotherPanel(frame,4);
				frame.motherPanel = motherPanel;
				frame.getContentPane().add(motherPanel);
				
				frame.repaint();
			}

			});
		add(stats);
			
	}
}
