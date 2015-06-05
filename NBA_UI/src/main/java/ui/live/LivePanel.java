package ui.live;

import java.awt.Image;
import java.util.List;

import javax.swing.JPanel;

import service.impl.LiveServiceImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.thread.LiveThread;
import ui.util.MyLabel;
import vo.LiveMatchInfoVO;
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

	private MessageTablePane table;

	private String team1Str;
	private String team2Str;
	private String path1;
	private String path2;

	public boolean islive = false;
	public String matchId;
	public LiveMatchInfoVO info;

	public LivePanel(HomeUI frame, LiveMatchInfoVO info) {
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		matchId = info.id;
		this.info = info;
		islive = true;

		// 设置布局管理器为自由布局
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.bg = pcfg.getBg();
		// 初始化组件
		this.initComponent();
		this.repaint();
		LiveThread thread = new LiveThread(this);
		Thread th2 = new Thread(thread);
		th2.start();
	}

	private void initComponent() {
		initLabels();
		initTable();
	}

	private void initLabels() {
		team1Str = info.homeTeam;
		team2Str = info.guestTeam;
		path1 = "img/team/final/" + ChiToEng(team1Str) + ".png";
		path2 = "img/team/final/" + ChiToEng(team2Str) + ".png";
		score1 = new MyLabel("0", pcfg.getLabels().element("score1"));
		add(score1);

		score2 = new MyLabel("0", pcfg.getLabels().element("score2"));
		add(score2);

		team1 = new MyLabel(team1Str, pcfg.getLabels().element("team1"));
		add(team1);
		team2 = new MyLabel(team2Str, pcfg.getLabels().element("team2"));
		add(team2);

		img1 = new MyLabel(pcfg.getLabels().element("img1"), path1, 0);
		add(img1);
		img2 = new MyLabel(pcfg.getLabels().element("img2"), path2, 0);
		add(img2);

	}

	private void initTable() {
		Object[][] data2 = new Object[9][5];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 4; j++) {
				data2[i][0] = "12:00";
				data2[i][1] = "勇士";
				data2[i][2] = "博古特和霍华德跳球，德雷蒙德-格林得到篮球";
				data2[i][3] = "0-0";
			}
		}
		table = new MessageTablePane(new TableConfig(pcfg.getTablepane()),
				data2);
		add(table);
	}

	public void autoRefresh(List<LiveMsgVO> list) {
		if (list.size() > 0) {
			Object[][] data = chageData(list);
			// for(int i=0;i<data.length;i++){
			// System.out.println(data[i][2]);
			// }
			table.setData(data);
			if (data.length > 0) {
				System.out.print(data.length);
				System.out.println(data[0][3]);

				String scores = data[0][3].toString();
				if (scores.contains("-")) {
					score1.setText(scores.split("-")[0]);
					score2.setText(scores.split("-")[1]);

				}
			}
		}

	}

	public Object[][] chageData(List<LiveMsgVO> list) {
		Object[][] data = new Object[list.size()][4];
		for (int i = data.length - 1; i >= 0; i--) {
			if (list.get(i).type == 0) {
				data[i][0] = "";
				data[i][1] = "";
				data[i][2] = list.get(i).content;
				data[i][3] = " ";
			} else {
				data[i][0] = list.get(i).residualTime;
				data[i][1] = list.get(i).team;
				data[i][2] = list.get(i).content;
				data[i][3] = list.get(i).scores;
			}
		}
		return data;

	}

	public MessageTablePane getTable() {
		return table;
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

}
