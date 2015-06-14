package ui.match.info;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.util.MyLabel;
import ui.util.MyPressedLabel;
import vo.MatchInfoVO;

public class MatchInfoPanel extends JPanel {
	
	public String gameId;
	
	private PanelConfig pcfg;
	private HomeUI frame;

	private MyPressedLabel findMore;
	private MyLabel season;
	private MyLabel date;
	private MyLabel type;
	
	private MyLabel home_team;
	private MyLabel home_point;
	private MyLabel guest_team;
	private MyLabel guest_point;
	private MyLabel time;
	
	private MyLabel seasonHint;
	private MyLabel dateHint;
	private MyLabel typeHint;
	
	private MyLabel home_teamHint;
	private MyLabel home_pointHint;
	private MyLabel guest_teamHint;
	private MyLabel guest_pointHint;
	private MyLabel timeHint;
	
	

	private MyLabel chart1;
	private MyLabel chart2;
	private ScoreTablePane score;

	public MatchInfoPanel(HomeUI frame) {
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
	
	private void initComponent(){
		initTable();
		initLabels();
	}
	private void initLabels(){
		
		season = new MyLabel(pcfg.getLabels().element("season"));
		add(season);
		
		date = new MyLabel(pcfg.getLabels().element("date"));
		add(date);
		
		type = new MyLabel(pcfg.getLabels().element("type"));
		add(type);
		
		
		
		home_team = new MyLabel(pcfg.getLabels().element("hometeam"));
		add(home_team);
		
		home_point = new MyLabel(pcfg.getLabels().element("homepoint"));
		add(home_point);
		
		guest_point = new MyLabel(pcfg.getLabels().element("guestpoint"));
		add(guest_point);
		
		guest_team = new MyLabel(pcfg.getLabels().element("guestteam"));
		add(guest_team);
		
		time = new MyLabel(pcfg.getLabels().element("time"));
		add(time);
		
		seasonHint = new MyLabel(pcfg.getLabels().element("seasonhint"));
		add(seasonHint);
		
		dateHint = new MyLabel(pcfg.getLabels().element("datehint"));
		add(dateHint);
		
		typeHint = new MyLabel(pcfg.getLabels().element("typehint"));
		add(typeHint);
		
		home_teamHint = new MyLabel(pcfg.getLabels().element("hometeamhint"));
		add(home_teamHint);
		
		home_pointHint = new MyLabel(pcfg.getLabels().element("homepointhint"));
		add(home_pointHint);
		
		guest_pointHint = new MyLabel(pcfg.getLabels().element("guestpointhint"));
		add(guest_pointHint);
		
		guest_teamHint = new MyLabel(pcfg.getLabels().element("guestteamhint"));
		add(guest_teamHint);
		
		timeHint = new MyLabel(pcfg.getLabels().element("timehint"));
		add(timeHint);
		
		
		chart1 = new MyLabel(pcfg.getLabels().element("chart1"));
		add(chart1);
		
		chart2 = new MyLabel(pcfg.getLabels().element("chart2"));
		add(chart2);
		
		findMore = new MyPressedLabel(pcfg.getLabels().element("findmore"));
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
				frame.motherPanel.matchPanel.matchInfoPanel.setVisible(false);
				frame.motherPanel.matchPanel.matchDetail.changeName(gameId);
				frame.motherPanel.matchPanel.matchDetail.setVisible(true);
				
			}
		});
		add(findMore);
	}
	
	private void initTable(){
		List<Integer> score1 = new ArrayList<Integer>();
		List<Integer> score2 = new ArrayList<Integer>();
		
		for(int i=0;i<4;i++){
		score1.add(0);
		score2.add(0);
		}
		List<List<Integer>> scores = new ArrayList<List<Integer>>();
		scores.add(score1);
		scores.add(score2);
		
		score = new ScoreTablePane(new TableConfig(pcfg.getTables().element("scores")), scores, "HOU", "DAL");
		add(score);
		
	}
	
	public void refreashData(String gameId){
		this.gameId = gameId;
		MatchInfoVO vo = null;
		List<List<Integer>> scores = null;
		ImageIcon icon1 = null;
		ImageIcon icon2 = null;
		try {
			vo=ServiceFactoryImpl.getInstance().getMatchService().getMatchInfoByGameId(gameId);
			icon1= ServiceFactoryImpl.getInstance().getStatsService().getTeamCompareRadarByGameId(gameId);
			
		scores = ServiceFactoryImpl.getInstance().getMatchService().getSectionScoreByGameId(gameId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		season.setText(vo.season);
		date.setText(vo.date);
		type.setText(vo.is_normal?"Regular Season":"Post Season");
		home_team.setText(vo.home_team);
		home_point.setText(vo.home_point+"");
		guest_team.setText(vo.guest_team);
		guest_point.setText(vo.guest_point+"");
		time.setText(vo.time);
		
		List<Integer> score1 = new ArrayList<Integer>(scores.size());
		List<Integer> score2 = new ArrayList<Integer>(scores.size());
		for(int i = 0;i<scores.size();i++){
			score1.add(scores.get(i).get(0));
			score2.add(scores.get(i).get(1));
		}
		List<List<Integer>> scoreData = new ArrayList<List<Integer>>(2);
		scoreData.add(score1);
		scoreData.add(score2);
		score.refresh(scoreData, vo.home_team, vo.guest_team);
		//System.out.println(scores.get(0).size());
		
		chart1.setImage(icon1);
		
	}
}
