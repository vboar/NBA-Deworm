package ui.team;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.team.advance.TeamMore;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import util.FieldType;
import vo.TeamInfoVO;
import vo.TeamPerGameVO;
import vo.TeamTotalVO;

public class TeamDetail extends JPanel{
	private PanelConfig pcfg;
	private HomeUI frame;
	private Image bg;
	private MyLabel labelbg;
	//球队基本信息
	private MyLabel info;
	//球队阵容
	private MyLabel player;
	
	private MyLabel logo;
	private MyLabel team;
	private MyLabel buildtime;
	private MyLabel location;
	private MyLabel league;
	private MyLabel division;
	private MyLabel championship;
	private MyLabel current;
	
	private MyLabel more;
	
	private MyLabel chart1;
	private MyLabel chart2;
	private MyComboBox box;
	
	private MyComboBox cdt;
	
	private TeamGeneralTablePane generalTable;
	
	private String abbr;
	private TeamInfoVO vo;
	private TeamPerGameVO pergamevo;
	private TeamTotalVO totalvo;
	private ImageIcon radar;
	private ImageIcon linechart;
	
	
	public TeamMore teamMore;
	
	String boxItem= null;
	
	public TeamDetail(HomeUI frame,String abbr){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		this.abbr = abbr;
		this.bg = pcfg.getBg();
		
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		
		initData();
		initComponent();
	} 
	
	private void initData(){
		try {
            System.out.println(abbr);
			vo = ServiceFactoryImpl.getInstance().getTeamService().getTeamInfoByAbbr(abbr);
			pergamevo = ServiceFactoryImpl.getInstance().getTeamService().getTeamPerGameBySeasonAbbr("14-15", abbr);
			totalvo = ServiceFactoryImpl.getInstance().getTeamService().getTeamTotalBySeasonAbbr("14-15", abbr);
			radar = ServiceFactoryImpl.getInstance().getStatsService().getTeamRadar(abbr, "14-15");
			linechart = ServiceFactoryImpl.getInstance().getStatsService().getTeamCareerLineChart(abbr, 1);
		
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	private void initComponent(){
		initTables();
		initLabels();
		initCombox();
		initChart();
	}
	
	private void initLabels(){
		logo = new MyLabel(pcfg.getLabels().element("logo"));
		try {
			logo.setImage(ServiceFactoryImpl.getInstance().getTeamService().getTeamLogoByAbbr(abbr));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		add(logo);
		info = new MyLabel(pcfg.getLabels().element("info"));
		//add(info);
		player = new MyLabel(pcfg.getLabels().element("player"));
		//add(player);
		labelbg = new MyLabel(pcfg.getLabels().element("labelbg"));
		//add(labelbg);
		
		team = new MyLabel(pcfg.getLabels().element("team"));
		team.setText(vo.name);
		team.setFont(new Font("华文细黑", 0, 20));
		add(team);
		
		buildtime = new MyLabel(pcfg.getLabels().element("buildtime"));
		buildtime.setText("建立时间："+vo.buildup_time);
		buildtime.setFont(new Font("宋体",0,12));
		add(buildtime);
		
		location = new MyLabel(pcfg.getLabels().element("location"));
		location.setText("地理位置：" + vo.location);
		location.setFont(new Font("宋体",0,12));
		add(location);
		
		league = new MyLabel(pcfg.getLabels().element("league"));
		league.setText("联盟："+vo.league);
		league.setFont(new Font("宋体", 0, 12));
		add(league);
		
		division = new MyLabel(pcfg.getLabels().element("division"));
		division.setText("分区："+vo.division);
		division.setFont(new Font("宋体", 0, 12));
		add(division);
		
		championship = new MyLabel(pcfg.getLabels().element("championship"));
		championship.setText("夺冠次数：" + vo.championships);
		championship.setFont(new Font("宋体",0,12));
		add(championship);
		
		current = new MyLabel(pcfg.getLabels().element("current"));
		current.setText("本赛季表现：");
		current.setFont(new Font("宋体", 0, 12));
		add(current);
		
		more = new MyLabel(pcfg.getLabels().element("more"));
		more.setText("点此查看更多");
		more.setFont(new Font("华文细黑",0,15));
		more.setForeground(new Color(93,156,236));
		more.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				removeAll();
				teamMore = new TeamMore(frame,abbr);
				add(teamMore);
				updateUI();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				more.setText("<html><u>点此查看更多</u></html>");
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				more.setText("点此查看更多");
			}
			
		});
		add(more);
	}
	
	private void initTables(){
		Object[][] data = new Object[5][3];
		data[0][0] = "技术统计";
		data[0][1] = "场均";
		data[0][2] = "总数据";
		data[1][0] = "得分";
		data[2][0] = "助攻";
		data[3][0] = "篮板";
		data[4][0] = "失误";
		data[1][1] = pergamevo.pts;
		data[2][1] = pergamevo.ast;
		data[3][1] = pergamevo.trb;
		data[4][1] = pergamevo.tov;
		data[1][2] = totalvo.pts;
		data[2][2] = totalvo.ast;
		data[3][2] = totalvo.trb;
		data[4][2] = totalvo.tov;
		
		generalTable = new TeamGeneralTablePane(new TableConfig(pcfg.getTablepane()),data);
		this.add(generalTable);
	}
	
	private void initCombox(){
		cdt = new MyComboBox(pcfg.getComboboxes().element("cdt"));
		cdt.setFocusable(false);
		add(cdt);
		
		getSelectChange(cdt);
	}
	
	private void getSelectChange(MyComboBox box) {
		box.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!box.getSelectedItem().toString().equals(boxItem)) {
					boxItem = box.getSelectedItem().toString();
					ImageIcon line = null;
					try {
						line = ServiceFactoryImpl
								.getInstance()
								.getStatsService()
								.getTeamCareerLineChart(abbr, getField(box.getSelectedIndex()));
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					chart2.setImage(line);
				}

			}
		});
	}
	
	private void initChart(){
		chart1 = new MyLabel(pcfg.getLabels().element("chart1"));
		// chart1.setImage(icon);
		add(chart1);
		
		chart2 = new MyLabel(pcfg.getLabels().element("chart2"));
		// chart1.setImage(icon);
		add(chart2);
		chart1.setImage(radar);
		chart2.setImage(linechart);
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
}
