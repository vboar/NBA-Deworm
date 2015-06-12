package ui.live;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import service.impl.LiveServiceImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyButton;
import ui.util.MyLabel;
import vo.LiveMatchInfoVO;



public class LiveChoosePane extends JPanel {
	
	private PanelConfig pcfg ;
	private HomeUI frame;
	private Image bg;
    public LivePanel livePanel;
	
	public LiveChoosePane(HomeUI frame) {
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		// 设置布局管理器为自由布局
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.bg = pcfg.getBg();
		// 初始化组件
		this.initComponent();
		this.repaint();
	}

	private void initComponent(){
		List<LiveMatchInfoVO>  list = LiveServiceImpl.getInstance().getAllLiveList();
		for(int i = 0; i < list.size(); i++){
            LiveMatchInfoVO vo = list.get(i);
			String title = vo.date + "     " + vo.day;
			MyLabel lb = new MyLabel(title, pcfg.getLabels().element("title"));
            lb.setFont(new Font("微软雅黑", 1 , 14));
            lb.setLocation(lb.getX(), lb.getY()+70*i);
            add(lb);
            String content = vo.time + " " + vo.matchType + " " + vo.homeTeam + " VS " + vo.guestTeam +
                    "     " + vo.state;
            lb = new MyLabel(content, pcfg.getLabels().element("content"));
            lb.setFont(new Font("微软雅黑", 0 , 14));
			lb.setLocation(lb.getX(), lb.getY()+70*i);
			add(lb);

            MyButton btn = new MyButton(pcfg.getButtons().element("watch"));
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    livePanel = new LivePanel(frame, vo);
                    frame.motherPanel.matchPanel.liveChoosePane.setVisible(false);
                    frame.motherPanel.matchPanel.add(livePanel);
                }
            });
            btn.setLocation(btn.getX(), btn.getY()+70*i);
            btn.setFont(new Font("微软雅黑", 0, 12));
            add(btn);
			
		}

        list = LiveServiceImpl.getInstance().getHistoryList();
        for(int i = 0; i < list.size(); i++){
            LiveMatchInfoVO vo = list.get(i);
            String title = vo.date + "     " + vo.day;
            MyLabel lb = new MyLabel(title, pcfg.getLabels().element("htitle"));
            lb.setFont(new Font("微软雅黑", 1 , 14));
            lb.setLocation(lb.getX(), lb.getY()+70*i);
            add(lb);
            String content = vo.time + " " + vo.matchType + " " + vo.homeTeam + " VS " + vo.guestTeam +
                    "     " + vo.state;
            lb = new MyLabel(content, pcfg.getLabels().element("hcontent"));
            lb.setFont(new Font("微软雅黑", 0 , 14));
            lb.setLocation(lb.getX(), lb.getY()+70*i);
            add(lb);

            MyButton btn = new MyButton(pcfg.getButtons().element("hwatch"));
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setLivePanel(vo);
                }
            });
            btn.setLocation(btn.getX(), btn.getY()+70*i);
            btn.setFont(new Font("微软雅黑", 0, 12));
            add(btn);

        }

	}

    public void removeLivePanel() {
        if (livePanel != null) {
            livePanel.stopThread();
            livePanel.setVisible(false);
            remove(livePanel);
            repaint();
        }
    }

    public void setLivePanel(LiveMatchInfoVO vo) {
        livePanel = new LivePanel(frame, vo);
        setVisible(false);
        frame.motherPanel.matchPanel.add(livePanel);
        frame.motherPanel.matchPanel.revalidate();
        frame.motherPanel.matchPanel.repaint();
    }

}
