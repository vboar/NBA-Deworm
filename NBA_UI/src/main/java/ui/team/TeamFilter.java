package ui.team;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyButton;
import ui.util.MyComboBox;
import vo.MatchInfoVO;
import vo.TeamInfoVO;
import vo.TeamPerGameVO;
import vo.TeamTotalVO;

public class TeamFilter extends JPanel{
	
	private HomeUI frame;
	private PanelConfig pcfg;

	private MyButton search;

	private Image bg;
	
	
	private MyComboBox season;
	private MyComboBox division;
	private MyComboBox league;
	
	public TeamFilter(HomeUI frame) {
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		this.bg = pcfg.getBg();

		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());

		this.initComponent();
	}
	public void initComponent() {
		//initLabels();
		initButtons();
		initComboBox();
	}
	
	
	
	private void initButtons() {
		search = new MyButton(pcfg.getButtons().element("search"), true);
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.motherPanel.teamPanel.teamstat.filter();
				String seasonStr = season.getSelectedItem().toString();
				//System.out.println(seasonStr);
				
				
				vo.TeamFilter filter = new vo.TeamFilter();
				filter.season = seasonStr;
				if(league.getSelectedIndex() ==0){
					filter.league = null;
				}else{
					filter.league = league.getSelectedItem().toString();
				}
				if(division.getSelectedIndex() == 0){
					filter.division = null;
				}else{
				filter.division = division.getSelectedItem().toString();
				}
				
				
				
					List<TeamPerGameVO> list1 = null;
					List<TeamTotalVO> list2 = null;
					try {

						list1
						= ServiceFactoryImpl.getInstance()
								.getTeamService().getTeamPerGameByFilter(filter);
						
						list2 = ServiceFactoryImpl.getInstance().getTeamService().getTeamTotalByFilter(filter);
						
							//	System.out.println(list.size());

					} catch (RemoteException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
					}
					Object[][]data1;
					data1 = new Object[list1.size()][24];
					for(int i=0;i<list1.size();i++){
						data1[i][0] = list1.get(i).abbr;
						data1[i][1] = list1.get(i).season;
						data1[i][2] = list2.get(i).num_of_game;
						data1[i][3] = list2.get(i).wins;
						data1[i][4] = list2.get(i).losses;
						data1[i][5] = list1.get(i).minute;
						data1[i][6] = list1.get(i).fg;
						data1[i][7] = list1.get(i).fga;
						data1[i][8] = list1.get(i).fga_pct;
						data1[i][9] = list1.get(i).fg3;
						data1[i][10] = list1.get(i).fg3a;
						data1[i][11] = list1.get(i).fg3_pct;
						data1[i][12] = list1.get(i).ft;
						data1[i][13] = list1.get(i).fta;
						data1[i][14] = list1.get(i).ft_pct;
						data1[i][15] = list1.get(i).orb;
						data1[i][16] = list1.get(i).drb;
						data1[i][17] = list1.get(i).trb;
						data1[i][18] = list1.get(i).ast;
						data1[i][19] = list1.get(i).stl;
						data1[i][20] = list1.get(i).blk;
						data1[i][21] = list1.get(i).tov;
						data1[i][22] = list1.get(i).pf;
						data1[i][23] = list1.get(i).pts;
					}
					
					Object[][]data2;
					data2 = new Object[list2.size()][24];
					for(int i=0;i<list2.size();i++){
						data2[i][0] = list2.get(i).abbr;
						data2[i][1] = list2.get(i).season;
						data2[i][2] = list2.get(i).num_of_game;
						data2[i][3] = list2.get(i).wins;
						data2[i][4] = list2.get(i).losses;
						data2[i][5] = list2.get(i).minute;
						data2[i][6] = list2.get(i).fg;
						data2[i][7] = list2.get(i).fga;
						data2[i][8] = list2.get(i).fga_pct;
						data2[i][9] = list2.get(i).fg3;
						data2[i][10] = list2.get(i).fg3a;
						data2[i][11] = list2.get(i).fg3_pct;
						data2[i][12] = list2.get(i).ft;
						data2[i][13] = list2.get(i).fta;
						data2[i][14] = list2.get(i).ft_pct;
						data2[i][15] = list2.get(i).orb;
						data2[i][16] = list2.get(i).drb;
						data2[i][17] = list2.get(i).trb;
						data2[i][18] = list2.get(i).ast;
						data2[i][19] = list2.get(i).stl;
						data2[i][20] = list2.get(i).blk;
						data2[i][21] = list2.get(i).tov;
						data2[i][22] = list2.get(i).pf;
						data2[i][23] = list2.get(i).pts;
					}
					
					
					frame.motherPanel.teamPanel.teamstat.data1 = data1;
					frame.motherPanel.teamPanel.teamstat.data2 = data2;
					
					frame.motherPanel.teamPanel.teamstat.table1.setData(data1);
					frame.motherPanel.teamPanel.teamstat.table2.setData(data2);
					
					
					
					

			}
		});
		add(search);
	}

	
	private void initComboBox() {
		// int nowState = frame.motherPanel.playerPanel.playerstat.state;
	
		season = new MyComboBox(pcfg.getComboboxes().element("season"));
        season.setFont(new Font("华文细黑",0,12));
		add(season);
		division = new MyComboBox(pcfg.getComboboxes().element("division"));
		division.setFont(new Font("华文细黑",0,12));
		add(division);
		league = new MyComboBox(pcfg.getComboboxes().element("league"));
		league.setFont(new Font("华文细黑",0,12));
		add(league);
	

	}
	
	public void resetPanel() {		
		List<TeamPerGameVO> list1 = null;
		List<TeamTotalVO> list2 = null;
		try {
			vo.TeamFilter filter = new vo.TeamFilter();
			filter.season = "14-15";
			list1
			= ServiceFactoryImpl.getInstance()
					.getTeamService().getTeamPerGameByFilter(filter);
			
			list2 = ServiceFactoryImpl.getInstance().getTeamService().getTeamTotalByFilter(filter);
			
				//	System.out.println(list.size());

		} catch (RemoteException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		}
		Object[][]data1;
		data1 = new Object[list1.size()][24];
		for(int i=0;i<list1.size();i++){
			data1[i][0] = list1.get(i).abbr;
			data1[i][1] = list1.get(i).season;
			data1[i][2] = list2.get(i).num_of_game;
			data1[i][3] = list2.get(i).wins;
			data1[i][4] = list2.get(i).losses;
			data1[i][5] = list1.get(i).minute;
			data1[i][6] = list1.get(i).fg;
			data1[i][7] = list1.get(i).fga;
			data1[i][8] = list1.get(i).fga_pct;
			data1[i][9] = list1.get(i).fg3;
			data1[i][10] = list1.get(i).fg3a;
			data1[i][11] = list1.get(i).fg3_pct;
			data1[i][12] = list1.get(i).ft;
			data1[i][13] = list1.get(i).fta;
			data1[i][14] = list1.get(i).ft_pct;
			data1[i][15] = list1.get(i).orb;
			data1[i][16] = list1.get(i).drb;
			data1[i][17] = list1.get(i).trb;
			data1[i][18] = list1.get(i).ast;
			data1[i][19] = list1.get(i).stl;
			data1[i][20] = list1.get(i).blk;
			data1[i][21] = list1.get(i).tov;
			data1[i][22] = list1.get(i).pf;
			data1[i][23] = list1.get(i).pts;
		}
		
		Object[][]data2;
		data2 = new Object[list2.size()][24];
		for(int i=0;i<list2.size();i++){
			data2[i][0] = list2.get(i).abbr;
			data2[i][1] = list2.get(i).season;
			data2[i][2] = list2.get(i).num_of_game;
			data2[i][3] = list2.get(i).wins;
			data2[i][4] = list2.get(i).losses;
			data2[i][5] = list2.get(i).minute;
			data2[i][6] = list2.get(i).fg;
			data2[i][7] = list2.get(i).fga;
			data2[i][8] = list2.get(i).fga_pct;
			data2[i][9] = list2.get(i).fg3;
			data2[i][10] = list2.get(i).fg3a;
			data2[i][11] = list2.get(i).fg3_pct;
			data2[i][12] = list2.get(i).ft;
			data2[i][13] = list2.get(i).fta;
			data2[i][14] = list2.get(i).ft_pct;
			data2[i][15] = list2.get(i).orb;
			data2[i][16] = list2.get(i).drb;
			data2[i][17] = list2.get(i).trb;
			data2[i][18] = list2.get(i).ast;
			data2[i][19] = list2.get(i).stl;
			data2[i][20] = list2.get(i).blk;
			data2[i][21] = list2.get(i).tov;
			data2[i][22] = list2.get(i).pf;
			data2[i][23] = list2.get(i).pts;
		}
		
		
		frame.motherPanel.teamPanel.teamstat.data1 = data1;
		frame.motherPanel.teamPanel.teamstat.data2 = data2;
		
		frame.motherPanel.teamPanel.teamstat.table1.setData(data1);
		frame.motherPanel.teamPanel.teamstat.table2.setData(data2);
		
		
		
		

	}

	
}
