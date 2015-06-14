package ui.live;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

import service.impl.LiveServiceImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.team.advance.TeamMore;
import ui.util.LoadFont;
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


    public void paintComponent(Graphics g){
        g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
    }

	private void initComponent(){
		List<LiveMatchInfoVO>  list = LiveServiceImpl.getInstance().getAllLiveList();
		for(int i = 0; i < list.size(); i++){

            LiveMatchInfoVO vo = list.get(i);
			String title = vo.date + "     " + vo.day;
			MyLabel lb = new MyLabel(title, pcfg.getLabels().element("title"));
            lb.setFont(new Font("微软雅黑", 0 , 14));
            lb.setForeground(Color.WHITE);
            lb.setLocation(lb.getX(), lb.getY()+90*i);
            add(lb);
            String content = vo.time + " " + vo.matchType + " " + vo.homeTeam + " VS " + vo.guestTeam +
                    "     " + vo.state;
            lb = new MyLabel(content, pcfg.getLabels().element("content"));
            lb.setFont(new Font("微软雅黑", 0 , 13));
			lb.setLocation(lb.getX(), lb.getY()+90*i);
			add(lb);

            MyLabel watch = new MyLabel(pcfg.getLabels().element("watch"));
            watch.setLocation(watch.getX(), watch.getY()+90*i);
            watch.setFont(new Font("微软雅黑", 0, 13));
            watch.setForeground(Color.BLUE);
            watch.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent arg0) {
                    setLivePanel(vo);
                }

                @Override
                public void mouseEntered(MouseEvent arg0) {
                    watch.setText("<html><u>立即观看</u></html>");

                }

                @Override
                public void mouseExited(MouseEvent arg0) {
                    watch.setText("立即观看");
                }

            });
            add(watch);


            MyLabel red = new MyLabel(pcfg.getLabels().element("red"));
            red.setIcon(new ImageIcon("img/match/live/red.png"));
            red.setLocation(red.getX(), red.getY()+90*i);
            add(red);
			
		}

        list = LiveServiceImpl.getInstance().getHistoryList();
        for(int i = 0; i < list.size(); i++){
            LiveMatchInfoVO vo = list.get(i);
            String title = vo.date + "     " + vo.day;
            MyLabel lb = new MyLabel(title, pcfg.getLabels().element("htitle"));
            lb.setFont(new Font("微软雅黑", 0 , 13));
            lb.setLocation(lb.getX(), lb.getY()+85*i);
            add(lb);
            String content = vo.time + " " + vo.matchType + " " + vo.homeTeam + " VS " + vo.guestTeam +
                    "     " + vo.state;
            lb = new MyLabel(content, pcfg.getLabels().element("hcontent"));
            lb.setFont(new Font("微软雅黑", 0 , 12));
            lb.setLocation(lb.getX(), lb.getY()+85*i);
            add(lb);

            MyLabel hwatch = new MyLabel(pcfg.getLabels().element("hwatch"));
            hwatch.setLocation(hwatch.getX(), hwatch.getY()+85*i);
            hwatch.setFont(new Font("微软雅黑", 0, 12));
            hwatch.setForeground(Color.BLUE);
            hwatch.addMouseListener(new MouseAdapter(){

                @Override
                public void mouseClicked(MouseEvent arg0) {
                    setLivePanel(vo);
                }

                @Override
                public void mouseEntered(MouseEvent arg0) {
                    hwatch.setText("<html><u>直播回顾</u></html>");

                }

                @Override
                public void mouseExited(MouseEvent arg0) {
                    hwatch.setText("直播回顾");
                }

            });
            add(hwatch);

            MyLabel green = new MyLabel(pcfg.getLabels().element("green"));
            green.setIcon(new ImageIcon("img/match/live/green.png"));;
            green.setLocation(green.getX(), green.getY()+85*i);
            add(green);

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
