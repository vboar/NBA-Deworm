package ui.home;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import service.impl.LiveServiceImpl;
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
        new Thread(new NewThread()).start();

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
                LiveServiceImpl.getInstance().stopLiveService();
				frame.dispose();
				System.exit(3);
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
		add(player);
		
		team = new MyButton(pcfg.getButtons().element("team"), true);
		add(team);
		
		match = new MyButton(pcfg.getButtons().element("match"), true);
		add(match);
		
		stats = new MyButton(pcfg.getButtons().element("stats"), true);
		add(stats);
			
	}

    public void addListener() {


        player.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                frame.home.setVisible(false);
                motherPanel.setVisible(true);
                motherPanel.fillComponents(1);
                frame.repaint();
            }

        });


        team.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                frame.home.setVisible(false);
                motherPanel.setVisible(true);
                motherPanel.fillComponents(2);
                frame.repaint();
            }

        });

        match.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                frame.home.setVisible(false);
                motherPanel.setVisible(true);
                motherPanel.fillComponents(3);
                frame.repaint();
            }

        });

        stats.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                frame.home.setVisible(false);
                motherPanel.setVisible(true);
                motherPanel.fillComponents(4);
                frame.repaint();
            }

        });
    }

    private class NewThread implements Runnable {

        @Override
        public void run() {
            motherPanel = new MotherPanel(frame);
            frame.motherPanel = motherPanel;
            motherPanel.setVisible(false);
            frame.getContentPane().add(motherPanel);
            addListener();
            motherPanel.checkLive();
            revalidate();
            repaint();
        }

    }
}
