package ui.player.info;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.util.MyLabel;
import ui.util.MyTab;
import vo.MatchFilter;
import vo.MatchInfoVO;
import vo.PlayerInfoVO;
import vo.PlayerPerGameVO;

public class PlayerInfoPane extends JPanel {
	private PanelConfig pcfg;
	private HomeUI frame;

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
	private MyLabel team;
	
	private MyLabel chart1;
	

	private FiveMatchTabelPane fiveTablePane;
	private MyTab myTab;
	
	public Object[][] matchData;

	public PlayerInfoPane(HomeUI frame) {
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		// 设置布局管理器为自由布局
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setBackground(Color.WHITE);
		// this.setBackground(Color.blue);
		// 初始化组件
		this.initComponent();
		this.repaint();

	}

	private void initComponent() {
		initLabels();
		initTables();
		initTabs();
		//initChart();
		
	}

	private void initLabels() {
		ImageIcon icon = null;
		try {
			icon = ServiceFactoryImpl.getInstance().getPlayerService()
					.getPlayerPortraitByName("LeBron James");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		icon.setImage(icon.getImage().getScaledInstance(169, 130,
				Image.SCALE_DEFAULT));
		img = new MyLabel(pcfg.getLabels().element("img"));
		img.setIcon(icon);
		add(img);

		positionHint = new MyLabel(pcfg.getLabels().element("positionhint"));
		add(positionHint);

		bornHint = new MyLabel(pcfg.getLabels().element("bornhint"));
		add(bornHint);

		hometownHint = new MyLabel(pcfg.getLabels().element("hometownhint"));
		add(hometownHint);

		heightHint = new MyLabel(pcfg.getLabels().element("heighthint"));
		add(heightHint);

		weightHint = new MyLabel(pcfg.getLabels().element("weighthint"));
		add(weightHint);

		highschoolHint = new MyLabel(pcfg.getLabels().element("highschoolhint"));
		add(highschoolHint);

		collegeHint = new MyLabel(pcfg.getLabels().element("collegehint"));
		add(collegeHint);

		debutHint = new MyLabel(pcfg.getLabels().element("debuthint"));
		add(debutHint);

		expHint = new MyLabel(pcfg.getLabels().element("exphint"));
		add(expHint);

		numHint = new MyLabel(pcfg.getLabels().element("numhint"));
		add(numHint);

		//

		name = new MyLabel(pcfg.getLabels().element("name"));
		add(name);

		team = new MyLabel(pcfg.getLabels().element("team"));
		add(team);

		position = new MyLabel(pcfg.getLabels().element("position"));
		add(position);

		born = new MyLabel(pcfg.getLabels().element("born"));
		add(born);

		hometown = new MyLabel(pcfg.getLabels().element("hometown"));
		add(hometown);

		height = new MyLabel(pcfg.getLabels().element("height"));
		add(height);

		weight = new MyLabel(pcfg.getLabels().element("weight"));
		add(weight);

		highschool = new MyLabel(pcfg.getLabels().element("highschool"));
		add(highschool);

		college = new MyLabel(pcfg.getLabels().element("college"));
		add(college);

		debut = new MyLabel(pcfg.getLabels().element("debut"));
		add(debut);

		exp = new MyLabel(pcfg.getLabels().element("exp"));
		add(exp);

		num = new MyLabel(pcfg.getLabels().element("num"));
		add(num);

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
	}
	
	private void initChart(){
		List<PlayerPerGameVO> listAll = null;
		try {
			
			listAll = ServiceFactoryImpl.getInstance().getPlayerService().getPlayerPerGameByName("LeBron James", 2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String point = listAll.get(0).pts.toString();
		String bound = listAll.get(0).trb.toString();
		String assist = listAll.get(0).ast.toString();
		String block = listAll.get(0).blk.toString();
		String steal = listAll.get(0).stl.toString();
		String sys = System.getProperty("user.dir"); 
		
		String data1 = listAll.get(4).pts.toString();
		String data2 = listAll.get(3).pts.toString();
		String data3 = listAll.get(2).pts.toString();
		String data4 = listAll.get(1).pts.toString();
		String data5 = listAll.get(0).pts.toString();
		 try {
			Process p = Runtime.getRuntime().exec("python "+sys+"/leida.py "+point+" "+bound+" "+assist+" "+block+" "+steal);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 ImageIcon icon = new ImageIcon("radar.png");
			icon.setImage(icon.getImage());
			chart1 = new MyLabel(pcfg.getLabels().element("chart1"));
			chart1.setIcon(icon);
			add(chart1);
			
			
		
		
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
				 Object[][]matchData2 = new Object[5][10];
				 for(int i =0;i<5;i++){
					for(int j = 0;j<10;j++){
						matchData2[i][j] = matchData[i][j];
					}
				 }
				 fiveTablePane.renewTable(matchData2);
			}
		});
	}

	public void changeData(String name){
		MatchFilter filter = new MatchFilter();
		filter.player = name;
		List<PlayerPerGameVO> listAll = null;
		PlayerInfoVO info = null; 
		List<MatchInfoVO> matchlist= null;
		try {
			info = ServiceFactoryImpl.getInstance().getPlayerService().getPlayerInfoByName(name);
			listAll = ServiceFactoryImpl.getInstance().getPlayerService().getPlayerPerGameByName(name, 2);
			matchlist = ServiceFactoryImpl.getInstance().getMatchService().getMatchInfoByFilter(filter);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.name.setText(name);
		team.setText(info.team);
		position.setText(info.position);
		born.setText(info.born);
		hometown.setText(info.hometown);
		height.setText(info.height);
		weight.setText(info.weight.toString());
		highschool.setText(info.high_school);
		college.setText(info.college);
		debut.setText(info.debut);
		if(info.exp!=null){
			exp.setText(info.exp.toString());
		}else{
			exp.setText("无数据");
		}
		num.setText(info.number.toString());
		
		
		ImageIcon icon = null;
		try {
			icon = ServiceFactoryImpl.getInstance().getPlayerService()
					.getPlayerPortraitByName(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(icon!=null){
		icon.setImage(icon.getImage().getScaledInstance(169, 130,
				Image.SCALE_DEFAULT));
		img.setIcon(icon);
		}else{
			icon = new ImageIcon("img/player/unknown.png");
			icon.setImage(icon.getImage().getScaledInstance(169, 130,
					Image.SCALE_DEFAULT));
			img.setIcon(icon);
		}
		
		 matchData = new Object[matchlist.size()][10];
		 for(int i =0;i<matchlist.size();i++){
			 matchData[i][0] = matchlist.get(i).game_id;
			 matchData[i][1] = matchlist.get(i).season;
			 matchData[i][2] = matchlist.get(i).date;
			 matchData[i][3] = matchlist.get(i).is_normal;
			 matchData[i][4] = matchlist.get(i).location;
			 matchData[i][5] = matchlist.get(i).home_team;
			 matchData[i][6] = matchlist.get(i).home_point;
			 matchData[i][7] = matchlist.get(i).guest_team;
			 matchData[i][8] = matchlist.get(i).guest_point;
			 matchData[i][9] = matchlist.get(i).time;			
		 }
		 Object[][]matchData2 = new Object[5][10];
		 for(int i =0;i<5;i++){
			for(int j = 0;j<10;j++){
				matchData2[i][j] = matchData[i][j];
			}
		 }
		 fiveTablePane.renewTable(matchData2);
	}
}
