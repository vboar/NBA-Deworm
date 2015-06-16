package ui.player.info;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.common.Loading;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.util.LoadFont;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MyPressedLabel;
import ui.util.MyTab;
import util.FieldType;
import vo.MatchFilter;
import vo.MatchInfoVO;
import vo.PlayerAdvancedVO;
import vo.PlayerInfoVO;
import vo.PlayerPerGameVO;

public class PlayerInfoPane extends JPanel {
	private PanelConfig pcfg;
	private HomeUI frame;
	private Image bg;
	
	private MyLabel img;

	private MyLabel nameHint;
	private MyLabel positionHint;
	private MyLabel bornHint;
	private MyLabel hometownHint;
	private MyLabel heightHint;
	private MyLabel weightHint;
	private MyLabel highschoolHint;
	private MyLabel collegeHint;
	private MyLabel debutHint;
	private MyLabel expHint;
	private MyLabel numHint;
	private MyLabel teamHint;

	private MyLabel name;
	private MyLabel position;
	private MyLabel born;
	private MyLabel hometown;
	private MyLabel height;
	private MyLabel weight;
	private MyLabel highschool;
	private MyLabel college;
	private MyLabel debut;
	private MyLabel exp;
	private MyLabel num;
	// private MyLabel team;

	private MyPressedLabel findMore;

	private MyLabel chart1;
	private MyLabel chart1Name;

	private MyLabel chart2;
	private MyLabel chart2Name;

	private MyComboBox box;

	private FiveMatchTabelPane fiveTablePane;
	private MyTab myTab;

	private String boxItem = "PTS";
	private String latestSeason = "14-15";
	private String nameStr = "Kobe Bryant";

	public Object[][] matchData;

	public PlayerInfoPane(HomeUI frame) {
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.bg = pcfg.getBg();
		this.frame = frame;
		
		// 设置布局管理器为自由布局
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setBackground(Color.WHITE);
		// this.setBackground(Color.blue);
		// 初始化组件
		this.initComponent();
		this.repaint();

	}

	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	private void initComponent() {
		initLabels();
		initTables();
		// initTabs();
		initChart();

	}

	private void initLabels() {
		ImageIcon icon = null;
		// try {
		// icon = ServiceFactoryImpl.getInstance().getPlayerService()
		// .getPlayerPortraitByName("LeBron James");
		// } catch (RemoteException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// icon.setImage(icon.getImage().getScaledInstance(169, 130,
		// Image.SCALE_DEFAULT));
		img = new MyLabel(pcfg.getLabels().element("img"));
		img.setIcon(icon);
		add(img);

		positionHint = new MyLabel(pcfg.getLabels().element("positionhint"));
        positionHint.setFont(new Font("微软雅黑", 0, 13));
		add(positionHint);

		bornHint = new MyLabel(pcfg.getLabels().element("bornhint"));
        bornHint.setFont(new Font("微软雅黑", 0, 13));
		add(bornHint);

		hometownHint = new MyLabel(pcfg.getLabels().element("hometownhint"));
        hometownHint.setFont(new Font("微软雅黑", 0, 13));
		add(hometownHint);

		heightHint = new MyLabel(pcfg.getLabels().element("heighthint"));
        heightHint.setFont(new Font("微软雅黑", 0, 13));
		add(heightHint);

		weightHint = new MyLabel(pcfg.getLabels().element("weighthint"));
        weightHint.setFont(new Font("微软雅黑", 0, 13));
		add(weightHint);

		highschoolHint = new MyLabel(pcfg.getLabels().element("highschoolhint"));
        highschoolHint.setFont(new Font("微软雅黑", 0, 13));
		add(highschoolHint);

		collegeHint = new MyLabel(pcfg.getLabels().element("collegehint"));
        collegeHint.setFont(new Font("微软雅黑", 0, 13));
		add(collegeHint);

		debutHint = new MyLabel(pcfg.getLabels().element("debuthint"));
        debutHint.setFont(new Font("微软雅黑", 0, 13));
		add(debutHint);

		expHint = new MyLabel(pcfg.getLabels().element("exphint"));
        expHint.setFont(new Font("微软雅黑", 0, 13));
		add(expHint);

		numHint = new MyLabel(pcfg.getLabels().element("numhint"));
        numHint.setFont(new Font("微软雅黑", 0, 13));
		add(numHint);

		//

		name = new MyLabel(pcfg.getLabels().element("name"));
        name.setFont(LoadFont.loadFont("HELVETICA.TTF", 0, 26));
		name.setForeground(Color.WHITE);
		add(name);

		position = new MyLabel(pcfg.getLabels().element("position"));
        position.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 13));
		add(position);

		born = new MyLabel(pcfg.getLabels().element("born"));
        born.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 13));
		add(born);

		hometown = new MyLabel(pcfg.getLabels().element("hometown"));
        hometown.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 13));
		add(hometown);

		height = new MyLabel(pcfg.getLabels().element("height"));
        height.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 13));
		add(height);

		weight = new MyLabel(pcfg.getLabels().element("weight"));
        weight.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 13));
		add(weight);

		highschool = new MyLabel(pcfg.getLabels().element("highschool"));
        highschool.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 13));
		add(highschool);

		college = new MyLabel(pcfg.getLabels().element("college"));
        college.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 13));
		add(college);

		debut = new MyLabel(pcfg.getLabels().element("debut"));
        debut.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 13));
		add(debut);

		exp = new MyLabel(pcfg.getLabels().element("exp"));
        exp.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 13));
		add(exp);

		num = new MyLabel(pcfg.getLabels().element("num"));
        num.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 13));
		add(num);

		findMore = new MyPressedLabel(pcfg.getLabels().element("findmore"));
		findMore.setForeground(new Color(96,156,236));
		findMore.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 17));
		add(findMore);
		findMore.addMouseListener(new MouseListener() {

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
				frame.motherPanel.playerPanel.playerInfoPane.setVisible(false);
				frame.motherPanel.playerPanel.fourTablePane.changeName(name
						.getText());
				frame.motherPanel.playerPanel.fourTablePane.setVisible(true);

			}
		});
		add(findMore);

	}

	private void initTabs() {
		myTab = new MyTab(pcfg.getTab().element("stat"));
		setTab();
		add(myTab);
	}

	private void initTables() {
		Object[][] data = new Object[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				data[i][j] = 100;
			}
		}

		fiveTablePane = new FiveMatchTabelPane(new TableConfig(
				pcfg.getTablepane()), data, frame);
		add(fiveTablePane);
		fiveTablePane.setVisible(false);
	}

	private void initChart() {

		// System.out.println(name.getText());
		// ImageIcon icon =
		// ServiceFactoryImpl.getInstance().getStatsService().getPlayerRadar(name.getText(),
		// "14-15", 1);
		chart1Name = new MyLabel(pcfg.getLabels().element("chart1name"));
		chart1Name.setForeground(Color.WHITE);
		chart1Name.setFont(LoadFont.loadFont("HELVETICA.TTF", 0, 26));
		add(chart1Name);
		chart1 = new MyLabel(pcfg.getLabels().element("chart1"));
		// chart1.setImage(icon);
		add(chart1);

		chart2Name = new MyLabel(pcfg.getLabels().element("chart2name"));
		chart2Name.setFont(LoadFont.loadFont("HELVETICA.TTF", 0, 26));
		chart2Name.setForeground(Color.WHITE);
		add(chart2Name);
		box = new MyComboBox(pcfg.getComboboxes().element("box"));
        box.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 12));
		add(box);
		getSelectChange(box);
		chart2 = new MyLabel(pcfg.getLabels().element("chart2"));
		add(chart2);

	}

	private void setTab() {

		myTab.tab2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fiveTablePane.renewTable(matchData);

			}

		});

		myTab.tab1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[][] matchData2 = new Object[5][10];
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 10; j++) {
						matchData2[i][j] = matchData[i][j];
					}
				}
				fiveTablePane.renewTable(matchData2);
			}
		});
	}

	public void changeData(String name) {
		nameStr = name;
		latestSeason = "14-15";
		try {
			List<PlayerAdvancedVO> vo = ServiceFactoryImpl.getInstance().getPlayerService()
					.getPlayerAdvancedByName(name, 1);
			if(vo.size()>2){
			latestSeason= vo.get(vo.size()-2).season;
			}else{
				latestSeason = "14-15";
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MatchFilter filter = new MatchFilter();
		filter.player = name;
		List<PlayerPerGameVO> listAll = null;
		PlayerInfoVO info = null;
		List<MatchInfoVO> matchlist = null;
		String teamStr = null;
		ImageIcon radar = null;
		ImageIcon line = null;
		try {
			info = ServiceFactoryImpl.getInstance().getPlayerService()
					.getPlayerInfoByName(name);
			listAll = ServiceFactoryImpl.getInstance().getPlayerService()
					.getPlayerPerGameByName(name, 2);
			matchlist = ServiceFactoryImpl.getInstance().getMatchService()
					.getMatchInfoByFilter(filter);
			radar = ServiceFactoryImpl.getInstance().getStatsService()
					.getPlayerRadar(name, latestSeason, 1);
			System.out.println(latestSeason);
			line = ServiceFactoryImpl
					.getInstance()
					.getStatsService()
					.getMatchPlayerLineChart(name, latestSeason,
							getField(box.getSelectedIndex()));
			List<String> teamList = ServiceFactoryImpl.getInstance()
					.getPlayerService()
					.getTeamByPlayerNameSeason(name, latestSeason);
			if (teamList.size() > 0) {
				teamStr = teamList.get(0).split(";")[0];
			} else {
				teamStr = "";
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.name.setText(name);

		if (info.position.length() < 1) {
			position.setText("-");
		} else {
			position.setText(info.position);
		}
		born.setText(info.born);
		hometown.setText(info.hometown);
		height.setText(info.height);
		weight.setText(info.weight.toString());
		if (info.high_school.length() < 2) {
			highschool.setText("-");
		} else {
            highschool.setText(info.high_school);
        }

		if (info.college.length() < 2) {
			college.setText("-");
		} else {
            college.setText(info.high_school);
        }
		debut.setText(info.debut);
		if (info.exp != null) {
			if (info.exp.toString().contains("-1")) {
				exp.setText("Retired");
			} else {
				exp.setText(info.exp.toString());
			}
		} else {
			exp.setText("-");
		}
		num.setText(info.number.toString());

		chart1.setImage(radar);
		chart1Name.setText(latestSeason + " Radar Chart");
		chart2.setImage(line);
		chart2Name.setText(latestSeason + " "
				+ box.getSelectedItem().toString() + " Line Chart");

		ImageIcon icon = null;
		try {
			icon = ServiceFactoryImpl.getInstance().getPlayerService()
					.getPlayerPortraitByName(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (icon != null) {
			icon.setImage(icon.getImage().getScaledInstance(200, 150,
					Image.SCALE_DEFAULT));
			img.setIcon(icon);
		} else {
			icon = new ImageIcon("img/player/unknown.png");
			icon.setImage(icon.getImage().getScaledInstance(169, 130,
					Image.SCALE_DEFAULT));
			img.setIcon(icon);
		}

		matchData = new Object[matchlist.size()][10];
		for (int i = 0; i < matchlist.size(); i++) {
			matchData[i][0] = matchlist.get(i).game_id;
			matchData[i][1] = matchlist.get(i).season;
			matchData[i][2] = matchlist.get(i).date;
			matchData[i][3] = matchlist.get(i).is_normal == true ? "regular season"
					: "post season";
			matchData[i][4] = matchlist.get(i).location;
			matchData[i][5] = matchlist.get(i).home_team;
			matchData[i][6] = matchlist.get(i).home_point;
			matchData[i][7] = matchlist.get(i).guest_team;
			matchData[i][8] = matchlist.get(i).guest_point;
			matchData[i][9] = matchlist.get(i).time;
		}
		Object[][] matchData2 = new Object[5][10];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				matchData2[i][j] = matchData[i][j];
			}
		}
		fiveTablePane.renewTable(matchData2);
	}

	public int getField(int num) {
		switch (num) {
		case 0:
			return FieldType.PTS.ordinal();
		case 1:
			return FieldType.AST.ordinal();
		case 2:
			return FieldType.BLK.ordinal();
		case 3:
			return FieldType.STL.ordinal();
		case 4:
			return FieldType.TRB.ordinal();
		case 5:
			return FieldType.DRB.ordinal();
		case 6:
			return FieldType.ORB.ordinal();
		case 7:
			return FieldType.TOV.ordinal();
		case 8:
			return FieldType.PF.ordinal();
		case 9:
			return FieldType.FG3_PCT.ordinal();
		case 10:
			return FieldType.FGA_PCT.ordinal();
		case 11:
			return FieldType.FT_PCT.ordinal();
		default:
			return 0;
		}
	}

	private void getSelectChange(MyComboBox box) {
		box.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!box.getSelectedItem().toString().equals(boxItem)) {
					boxItem = box.getSelectedItem().toString();
                    new Thread(new PlayerThread()).start();
				}

			}
		});
	}


    private class PlayerThread implements Runnable {

        @Override
        public void run() {
            Loading.getLoading().setVisible(true);
            ImageIcon line = null;
            try {
                line = ServiceFactoryImpl
                        .getInstance()
                        .getStatsService()
                        .getMatchPlayerLineChart(nameStr, latestSeason,
                                getField(box.getSelectedIndex()));
            } catch (RemoteException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Loading.getLoading().setVisible(false);
            chart2Name.setText(latestSeason + " "
                    + box.getSelectedItem().toString() + " Line Chart");
            chart2.setImage(line);
        }
    }
}
