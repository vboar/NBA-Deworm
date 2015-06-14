package ui.live;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

import javafx.scene.control.OverrunStyle;
import service.impl.LiveServiceImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.team.TeamDetail;
import ui.util.LoadFont;
import ui.util.MyButton;
import ui.util.MyLabel;
import vo.LiveMatchInfoVO;
import vo.LiveMatchVO;
import vo.LiveMsgVO;

public class LivePanel extends JPanel {

	private PanelConfig pcfg;
	private HomeUI frame;
	private Image bg;
	
	private MyLabel score1;
	private MyLabel score2;
	private MyLabel team1;
	private MyLabel team2;
	private MyLabel img1;
	private MyLabel img2;
    private MyLabel start;
    private MyLabel totaltime;
    private MyLabel gym;
    private MyLabel att;
    private MyLabel rtime;
    private ScoreTablePane scoreTablePane;
    private MessageTablePane messageTablePane;
    private MyLabel data;

	private String team1Str;
	private String team2Str;
	private String path1;
	private String path2;

	public boolean islive = false;
	public String matchId;
	public LiveMatchInfoVO info;
    public LiveMatchVO vo;
    private List<LiveMsgVO> mList;
    private Thread t;
    private boolean stop = false;

	public LivePanel(HomeUI frame, LiveMatchInfoVO info) {
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		matchId = info.id;
		this.info = info;
		islive = true;
        vo = LiveServiceImpl.getInstance().getMatchVO(matchId);
        mList = LiveServiceImpl.getInstance().getMsg(matchId);

		// 设置布局管理器为自由布局
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.bg = pcfg.getBg();
		// 初始化组件
		this.initComponent();
        refresh();
		this.repaint();

        if (!info.state.equals("比赛结束")) {
            t = new Thread(new MyThread());
            t.start();
        }

        if (mList.size() == 0) {
            new Thread(new TipsThread()).start();
        }

	}

	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	private void initComponent() {
		initLabels();
        initTables();
        initButtons();
	}
    private void initButtons() {
        if (info.state.equals("比赛结束")) {
            data = new MyLabel(pcfg.getLabels().element("data"));
            data.addMouseListener(new MouseAdapter(){

                @Override
                public void mouseClicked(MouseEvent arg0) {
                    // TODO
                }

                @Override
                public void mouseEntered(MouseEvent arg0) {
                    data.setText("<html><u>点击查看详细数据</u></html>");

                }

                @Override
                public void mouseExited(MouseEvent arg0) {
                    data.setText("点击查看详细数据");
                }

            });
            data.setFont(new Font("微软雅黑", 0, 13));
            data.setForeground(Color.BLUE);
            add(data);
        }
    }

    private void initTables() {
        scoreTablePane = new ScoreTablePane(new TableConfig(pcfg.getTables().element("scores")), vo, info);
        add(scoreTablePane);
        messageTablePane = new MessageTablePane(new TableConfig(pcfg.getTables().element("msg")), mList, info);
        add(messageTablePane);
    }

	private void initLabels() {
		team1Str = info.homeTeam;
		team2Str = info.guestTeam;
		path1 = "img/team/final/" + ChiToEng(team1Str) + ".png";
		path2 = "img/team/final/" + ChiToEng(team2Str) + ".png";
		score1 = new MyLabel("0", pcfg.getLabels().element("score1"));
		score1.setFont(new Font("Arial", 0, 38));
		add(score1);

		score2 = new MyLabel("0", pcfg.getLabels().element("score2"));
		score2.setFont(new Font("Arial", 0, 38));
		add(score2);

		team1 = new MyLabel(team1Str, pcfg.getLabels().element("team1"));
		team1.setFont(new Font("微软雅黑", 0, 22));
        team1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        team1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.motherPanel.fillComponents(2);
                frame.motherPanel.teamPanel.createTeamDetail(ChiToEng(team1Str));
            }
        });
		add(team1);
		team2 = new MyLabel(team2Str, pcfg.getLabels().element("team2"));
		team2.setFont(new Font("微软雅黑", 0, 22));
        team2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        team2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.motherPanel.fillComponents(2);
                frame.motherPanel.teamPanel.createTeamDetail(ChiToEng(team2Str));
            }
        });
		add(team2);

		img1 = new MyLabel(pcfg.getLabels().element("img1"), path1, 0);
        img1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        img1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.motherPanel.fillComponents(2);
                frame.motherPanel.teamPanel.createTeamDetail(ChiToEng(team1Str));
            }
        });
		add(img1);
		img2 = new MyLabel(pcfg.getLabels().element("img2"), path2, 0);
        img2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        img2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.motherPanel.fillComponents(2);
                frame.motherPanel.teamPanel.createTeamDetail(ChiToEng(team2Str));
            }
        });
		add(img2);

        start = new MyLabel("开赛：  " + info.date + " " + info.time, pcfg.getLabels().element("start"));
        start.setFont(new Font("微软雅黑", 0, 14));
        add(start);

        totaltime = new MyLabel("耗时：  " + vo.time, pcfg.getLabels().element("totaltime"));
        totaltime.setFont(new Font("微软雅黑", 0, 14));
        add(totaltime);

        gym = new MyLabel("球馆：  " + vo.gym, pcfg.getLabels().element("gym"));
        gym.setFont(new Font("微软雅黑", 0, 14));
        add(gym);

        att = new MyLabel("观众：  " + vo.attendance, pcfg.getLabels().element("att"));
        att.setFont(new Font("微软雅黑", 0, 14));
        add(att);

        rtime = new MyLabel(vo.residualTime, pcfg.getLabels().element("rtime"));
        rtime.setFont(new Font("微软雅黑", 1, 14));
        rtime.setForeground(Color.red);
        add(rtime);
	}

	public String ChiToEng(String Chi) {
		switch (Chi) {
		case "勇士": {
			return "GSW";
		}
		case "骑士": {
			return "CLE";
		}
		default:
			return null;
		}
	}

    public void refresh() {
        vo = LiveServiceImpl.getInstance().getMatchVO(matchId);
        if (vo == null) return;
        mList = LiveServiceImpl.getInstance().getMsg(matchId);
        if (vo.scoresA.size() == 0) {
            score1.setText("0");
            score2.setText("0");
        } else {
            score1.setText(vo.scoresA.get(vo.scoresA.size()-1));
            score2.setText(vo.scoresB.get(vo.scoresB.size()-1));
        }
        rtime.setText(vo.residualTime);

        messageTablePane.refresh(mList);
        scoreTablePane.refresh(vo);
    }

    private class MyThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    if (stop) break;
                    refresh();
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class TipsThread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "比赛尚未开始！");
        }
    }

    public void stopThread() {
        stop = true;
    }

}
