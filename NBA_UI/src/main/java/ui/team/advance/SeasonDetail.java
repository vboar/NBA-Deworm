package ui.team.advance;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.util.LoadFont;
import ui.util.MyLabel;
import vo.PlayerFilter;
import vo.PlayerInfoVO;
import vo.PlayerTotalVO;
import vo.TeamOppPerGameVO;
import vo.TeamOppTotalVO;
import vo.TeamPerGameVO;
import vo.TeamTotalVO;

public class SeasonDetail extends JPanel{

	private HomeUI frame;
	private PanelConfig pcfg;
	private Image bg;
	
	private MyLabel hint1;
	private MyLabel hint2;
	
	private Object[][] data1;
	private Object[][] data2;
	
	private TeamTotalVO total1;
	private TeamPerGameVO pergame1;
	private TeamOppTotalVO total2;
	private TeamOppPerGameVO pergame2;
	
	private List<PlayerTotalVO> playertotal;
	
	private OppStatTablePane table1;
	private SeasonPlayerTablePane table2;
	
	private String abbr;
	private String season;
	
	public SeasonDetail(HomeUI frame,String season,String abbr){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(getClass().getName());
		this.frame = frame;
		this.season = season;
		this.abbr = abbr;
		this.bg = pcfg.getBg();
		
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		
		initComponent();
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	private void initComponent(){
		initData();
		initLable();
		initTable();
	}
	
	private void initData(){
		try {
			total1= ServiceFactoryImpl.getInstance().getTeamService()
					.getTeamTotalBySeasonAbbr(season, abbr);
			pergame1 = ServiceFactoryImpl.getInstance().getTeamService()
					.getTeamPerGameBySeasonAbbr(season, abbr);
			total2 = ServiceFactoryImpl.getInstance().getTeamService()
					.getTeamOppTotalBySeasonAbbr(season, abbr);
			pergame2 = ServiceFactoryImpl.getInstance().getTeamService()
					.getTeamOppPerGameBySeasonAbbr(season, abbr);
			
			PlayerFilter f = new PlayerFilter();
			f.season = season;
			f.team = abbr;
			f.regular = 1;
			playertotal = ServiceFactoryImpl.getInstance().getPlayerService()
					.getPlayerTotalByFilter(f);
			int max = playertotal.size();
			for(int i=0;i<max;i++){
				if((!playertotal.get(i).team.equals(abbr))||(!playertotal.get(i).season.equals(season))){
					playertotal.remove(i);
					i--;
					max--;
				}
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		data1 = new Object[4][24];
		data1[0][0] = "Team";
		data1[1][0] = "Team/G";
		data1[2][0] = "Opp";
		data1[3][0] = "Opp/G";
		
		data1[0][1] = total1.num_of_game;
		data1[1][1] = "";
		data1[2][1] = total2.num_of_game;
		data1[3][1] = "";
		
		data1[0][2] = total1.minute;
		data1[1][2] = pergame1.minute;
		data1[2][2] = total2.minute;
		data1[3][2] = pergame2.minute;
				
		data1[0][3] = total1.fg;
		data1[1][3] = pergame1.fg;
		data1[2][3] = total2.fg;
		data1[3][3] = pergame2.fg;
		
		data1[0][4] = total1.fga;
		data1[1][4] = pergame1.fga;
		data1[2][4] = total2.fga;
		data1[3][4] = pergame2.fga;
		
		data1[0][5] = total1.fga_pct;
		data1[1][5] = pergame1.fga_pct;
		data1[2][5] = total2.fga_pct;
		data1[3][5] = pergame2.fga_pct;
		
		data1[0][6] = total1.fg3;
		data1[1][6] = pergame1.fg3;
		data1[2][6] = total2.fg3;
		data1[3][6] = pergame2.fg3;
		
		data1[0][7] = total1.fg3a;
		data1[1][7] = pergame1.fg3a;
		data1[2][7] = total2.fg3a;
		data1[3][7] = pergame2.fg3a;
		
		data1[0][8] = total1.fg3_pct;
		data1[1][8] = pergame1.fg3_pct;
		data1[2][8] = total2.fg3_pct;
		data1[3][8] = pergame2.fg3_pct;
		
		data1[0][9] = total1.fg2;
		data1[1][9] = pergame1.fg2;
		data1[2][9] = total2.fg2;
		data1[3][9] = pergame2.fg2;
		
		data1[0][10] = total1.fg2a;
		data1[1][10] = pergame1.fg2a;
		data1[2][10] = total2.fg2a;
		data1[3][10] = pergame2.fg2a;
		
		data1[0][11] = total1.fg2_pct;
		data1[1][11] = pergame1.fg2_pct;
		data1[2][11] = total2.fg2_pct;
		data1[3][11] = pergame2.fg2_pct;
		
		data1[0][12] = total1.ft;
		data1[1][12] = pergame1.ft;
		data1[2][12] = total2.ft;
		data1[3][12] = pergame2.ft;
		
		data1[0][13] = total1.fta;
		data1[1][13] = pergame1.fta;
		data1[2][13] = total2.fta;
		data1[3][13] = pergame2.fta;
		
		data1[0][14] = total1.ft_pct;
		data1[1][14] = pergame1.ft_pct;
		data1[2][14] = total2.ft_pct;
		data1[3][14] = pergame2.ft_pct;
		
		data1[0][15] = total1.orb;
		data1[1][15] = pergame1.orb;
		data1[2][15] = total2.orb;
		data1[3][15] = pergame2.orb;
		
		data1[0][16] = total1.drb;
		data1[1][16] = pergame1.drb;
		data1[2][16] = total2.drb;
		data1[3][16] = pergame2.drb;
		
		data1[0][17] = total1.trb;
		data1[1][17] = pergame1.trb;
		data1[2][17] = total2.trb;
		data1[3][17] = pergame2.trb;
		
		data1[0][18] = total1.ast;
		data1[1][18] = pergame1.ast;
		data1[2][18] = total2.ast;
		data1[3][18] = pergame2.ast;
		
		data1[0][19] = total1.stl;
		data1[1][19] = pergame1.stl;
		data1[2][19] = total2.stl;
		data1[3][19] = pergame2.stl;
		
		data1[0][20] = total1.blk;
		data1[1][20] = pergame1.blk;
		data1[2][20] = total2.blk;
		data1[3][20] = pergame2.blk;
		
		data1[0][21] = total1.tov;
		data1[1][21] = pergame1.tov;
		data1[2][21] = total2.tov;
		data1[3][21] = pergame2.tov;
		
		data1[0][22] = total1.pf;
		data1[1][22] = pergame1.pf;
		data1[2][22] = total2.pf;
		data1[3][22] = pergame2.pf;
		
		data1[0][23] = total1.pts;
		data1[1][23] = pergame1.pts;
		data1[2][23] = total2.pts;
		data1[3][23] = pergame2.pts;
		
		data2 = new Object[playertotal.size()+1][22];
		for(int i=0;i<playertotal.size();i++){
			
			data2[i][0] = playertotal.get(i).name;
			data2[i][1] = playertotal.get(i).game;
			data2[i][2] = playertotal.get(i).game_started;
			data2[i][3] = playertotal.get(i).minute;
			data2[i][4] = playertotal.get(i).fg;
			data2[i][5] = playertotal.get(i).fga;
			data2[i][6] = playertotal.get(i).fga_pct;
			data2[i][7] = playertotal.get(i).fg3;
			data2[i][8] = playertotal.get(i).fg3a;
			data2[i][9] = playertotal.get(i).fg3_pct;
			data2[i][10] = playertotal.get(i).ft;
			data2[i][11] = playertotal.get(i).fta;
			data2[i][12] = playertotal.get(i).ft_pct;
			data2[i][13] = playertotal.get(i).orb;
			data2[i][14] = playertotal.get(i).drb;
			data2[i][15] = playertotal.get(i).trb;
			data2[i][16] = playertotal.get(i).ast;
			data2[i][17] = playertotal.get(i).stl;
			data2[i][18] = playertotal.get(i).blk;
			data2[i][19] = playertotal.get(i).tov;
			data2[i][20] = playertotal.get(i).pf;
			data2[i][21] = playertotal.get(i).pts;
		}
			data2[playertotal.size()][0] = "TeamTotal";
			data2[playertotal.size()][1] = total1.num_of_game;
			data2[playertotal.size()][3] = total1.minute;
			data2[playertotal.size()][4] = total1.fg;
			data2[playertotal.size()][5] = total1.fga;
			data2[playertotal.size()][6] = total1.fga_pct;
			data2[playertotal.size()][7] = total1.fg3;
			data2[playertotal.size()][8] = total1.fg3a;
			data2[playertotal.size()][9] = total1.fg3_pct;
			data2[playertotal.size()][10] = total1.ft;
			data2[playertotal.size()][11] = total1.fta;
			data2[playertotal.size()][12] = total1.ft_pct;
			data2[playertotal.size()][13] = total1.orb;
			data2[playertotal.size()][14] = total1.drb;
			data2[playertotal.size()][15] = total1.trb;
			data2[playertotal.size()][16] = total1.ast;
			data2[playertotal.size()][17] = total1.stl;
			data2[playertotal.size()][18] = total1.blk;
			data2[playertotal.size()][19] = total1.tov;
			data2[playertotal.size()][20] = total1.pf;
			data2[playertotal.size()][21] = total1.pts;
	}
	
	private void initLable(){
		hint1 = new MyLabel(pcfg.getLabels().element("hint1"));
		hint1.setText(season+" "+abbr+"&OPP Data Table");
		hint1.setForeground(Color.WHITE);
		hint1.setFont(new Font("HELVETICA", 1, 16));
		add(hint1);
		
		hint2 = new MyLabel(pcfg.getLabels().element("hint2"));
		hint2.setText(season+" "+abbr+" Player Data Table");
		hint2.setForeground(Color.WHITE);
		hint2.setFont(new Font("HELVETICA", 1, 16));
		add(hint2);
	}
	
	private void initTable(){
		table1 = new OppStatTablePane(new TableConfig(pcfg.getTablepane().element("table1")),data1);
		add(table1);
		
		table2 = new SeasonPlayerTablePane(new TableConfig(pcfg.getTablepane().element("table2")),data2,frame);
		add(table2);
		updateUI();
	}
}
