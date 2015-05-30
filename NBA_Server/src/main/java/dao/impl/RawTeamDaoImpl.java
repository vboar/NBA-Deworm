package dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Utility;
import dao.RawTeamDao;
import entity.OpponentStatsPerGame;
import entity.OpponentStatsTotal;
import entity.TeamInfo;
import entity.TeamStatsAdvanced;
import entity.TeamStatsPerGame;
import entity.TeamStatsTotal;

/**
 * RawTeamDao的具体实现类
 * 
 * created by JaneLDQ on 2015年5月28日 下午8:31:21
 */
public class RawTeamDaoImpl implements RawTeamDao {

	private static String TEAM_PATH = FileManager.DATA_PATH + "/teams/text/";
	
	//存放各类数据
	private List<TeamInfo> infolist;
	private List<TeamStatsTotal> totallist;
	private List<TeamStatsPerGame> pergamelist;
	private List<TeamStatsAdvanced> advancedlist;
	private List<OpponentStatsPerGame> opp_pergamelist;
	private List<OpponentStatsTotal> opp_totallist;
	//辅助哈希表
	private Map<String, TeamStatsTotal> totalmap;
	
	public RawTeamDaoImpl(){
		System.out.println("Team Data：" + TEAM_PATH);
		
		infolist = new ArrayList<TeamInfo>();
		totallist = new ArrayList<TeamStatsTotal>();
		pergamelist = new ArrayList<TeamStatsPerGame>();
		advancedlist = new ArrayList<TeamStatsAdvanced>();
		opp_pergamelist = new ArrayList<OpponentStatsPerGame>();
		opp_totallist = new ArrayList<OpponentStatsTotal>();
		totalmap = new HashMap<String, TeamStatsTotal>();
		
		this.getAllData();
	}
	
	public void getAllData(){
		File folder = new File(TEAM_PATH);
		String[] teams = folder.list();
		for(String team: teams){
			TeamInfo info = new TeamInfo();
			List<String> lines = FileManager.read(TEAM_PATH + team);
			// 1 = team_t; 2 = team_p_g; 3 = opp_t; 4 = opp_p_g; 5 = team_adv
			int datatype = 0;
			String season = "";
			for(int i=0; i<lines.size();++i){		
				//从第0行获得球队的基本信息	
				if(i == 0){
					String[] data = lines.get(i).split(";",-1);
					info.setName(data[0]);
					info.setAbbr(data[1]);
					info.setBuildup_time(data[2]);
					info.setLocation(data[3]);
					info.setRecord(data[4]);
					info.setPlayeroff_appearance(Utility.stringToInt(data[5]));
					info.setChampionships(Utility.stringToInt(data[6]));
					info.setLeague(data[7]);
					info.setDivision(data[8]);
					infolist.add(info);
				}else{
					if(lines.get(i).equals("Totals")){
						continue;
					}
					String[] data = lines.get(i).split(";");
					if(data.length==7){
						TeamStatsTotal tst = new TeamStatsTotal();
						tst.setAbbr(team);
						tst.setSeason(data[0]);
						tst.setWins(Utility.stringToInt(data[1]));
						tst.setLosses(Utility.stringToInt(data[2]));
						tst.setFinish(Utility.stringToInt(data[3]));
						tst.setAge(Utility.stringToDouble(data[4]));
						tst.setHeight(data[5]);
						tst.setWeight(Utility.stringToDouble(data[6]));
						totalmap.put(team+data[0], tst);
					}else if(data.length==1){
						datatype = 1;
						season = data[0];
						continue;
					}else{
						switch(datatype){
						case 1:
							totallist.add(getTeamTotal(team,season,lines.get(i)));
							datatype++;
							break;
						case 2:
							pergamelist.add(getTeamPerGame(team,season,lines.get(i)));
							datatype++;
							break;
						case 3:
							opp_totallist.add(getTeamOppTotal(team,season,lines.get(i)));
							datatype++;
							break;
						case 4:
							opp_pergamelist.add(getTeamOppPerGame(team,season,lines.get(i)));
							datatype++;
							break;
						case 5:
							advancedlist.add(getTeamAdvanced(team,season,lines.get(i)));
							datatype++;
							break;
						default:
							break;
						}
					}
				}
			}// end of lines
		}// end of each team.txt
	}
	
	private TeamStatsAdvanced getTeamAdvanced(String team, String season, String string) {
		TeamStatsAdvanced tsadv = new TeamStatsAdvanced();
		tsadv.setAbbr(team);
		tsadv.setSeason(season);
		String[] data = string.split(";",-1);
		tsadv.setPw(Utility.stringToDouble(data[0]));
		tsadv.setPl(Utility.stringToDouble(data[1]));
		tsadv.setMov(Utility.stringToDouble(data[2]));
		tsadv.setSos(Utility.stringToDouble(data[3]));
		tsadv.setSrs(Utility.stringToDouble(data[4]));
		tsadv.setOff_rtg(Utility.stringToDouble(data[5]));
		tsadv.setDef_rtg(Utility.stringToDouble(data[6]));
		tsadv.setPace(Utility.stringToDouble(data[7]));
		tsadv.setFta_per_fga_pct(Utility.stringToDouble(data[8]));
		tsadv.setFg3a_per_fga_pct(Utility.stringToDouble(data[9]));
		tsadv.setOff_efg_pct(Utility.stringToDouble(data[10]));
		tsadv.setOff_tov_pct(Utility.stringToDouble(data[11]));
		tsadv.setOrb_pct(Utility.stringToDouble(data[12]));
		tsadv.setOff_ft_rate(Utility.stringToDouble(data[13]));
		tsadv.setOpp_efg_pct(Utility.stringToDouble(data[14]));
		tsadv.setOpp_tov_pct(Utility.stringToDouble(data[15]));
		tsadv.setDrb_pct(Utility.stringToDouble(data[16]));
		tsadv.setOpp_ft_rate(Utility.stringToDouble(data[17]));
		tsadv.setArena(data[18]);
		tsadv.setAttendance(Utility.stringToInt(data[19]));
		return tsadv;
	}

	private OpponentStatsPerGame getTeamOppPerGame(String team, String season, String string) {
		OpponentStatsPerGame osp = new OpponentStatsPerGame();
		osp.setAbbr(team);
		osp.setSeason(season);
		String[] data = string.split(";",-1);
		osp.setMinute(Utility.stringToDouble(data[1]));
		osp.setFg(Utility.stringToDouble(data[2]));
		osp.setFga(Utility.stringToDouble(data[3]));
		osp.setFga_pct(Utility.stringToDouble(data[4]));
		osp.setFg3(Utility.stringToDouble(data[5]));
		osp.setFg3a(Utility.stringToDouble(data[6]));
		osp.setFg3_pct(Utility.stringToDouble(data[7]));
		osp.setFg2(Utility.stringToDouble(data[8]));
		osp.setFg2a(Utility.stringToDouble(data[9]));
		osp.setFg2_pct(Utility.stringToDouble(data[10]));
		osp.setFt(Utility.stringToDouble(data[11]));
		osp.setFta(Utility.stringToDouble(data[12]));
		osp.setFt_pct(Utility.stringToDouble(data[13]));
		osp.setOrb(Utility.stringToDouble(data[14]));
		osp.setDrb(Utility.stringToDouble(data[15]));
		osp.setTrb(Utility.stringToDouble(data[16]));
		osp.setAst(Utility.stringToDouble(data[17]));
		osp.setStl(Utility.stringToDouble(data[18]));
		osp.setBlk(Utility.stringToDouble(data[19]));
		osp.setTov(Utility.stringToDouble(data[20]));
		osp.setPf(Utility.stringToDouble(data[21]));
		osp.setPts(Utility.stringToDouble(data[22]));
		return osp;
	}

	private OpponentStatsTotal getTeamOppTotal(String team, String season, String string) {
		OpponentStatsTotal ost = new OpponentStatsTotal();
		ost.setAbbr(team);
		ost.setSeason(season);
		String[] data = string.split(";",-1);
		ost.setNum_of_game(Utility.stringToInt(data[0]));
		ost.setMinute(Utility.stringToInt(data[1]));
		ost.setFg(Utility.stringToInt(data[2]));
		ost.setFga(Utility.stringToInt(data[3]));
		ost.setFga_pct(Utility.stringToDouble(data[4]));
		ost.setFg3(Utility.stringToInt(data[5]));
		ost.setFg3a(Utility.stringToInt(data[6]));
		ost.setFg3_pct(Utility.stringToDouble(data[7]));
		ost.setFg2(Utility.stringToInt(data[8]));
		ost.setFg2a(Utility.stringToInt(data[9]));
		ost.setFg2_pct(Utility.stringToDouble(data[10]));
		ost.setFt(Utility.stringToInt(data[11]));
		ost.setFta(Utility.stringToInt(data[12]));
		ost.setFt_pct(Utility.stringToDouble(data[13]));
		ost.setOrb(Utility.stringToInt(data[14]));
		ost.setDrb(Utility.stringToInt(data[15]));
		ost.setTrb(Utility.stringToInt(data[16]));
		ost.setAst(Utility.stringToInt(data[17]));
		ost.setStl(Utility.stringToInt(data[18]));
		ost.setBlk(Utility.stringToInt(data[19]));
		ost.setTov(Utility.stringToInt(data[20]));
		ost.setPf(Utility.stringToInt(data[21]));
		ost.setPts(Utility.stringToInt(data[22]));
		return ost;
	}

	private TeamStatsPerGame getTeamPerGame(String team, String season, String string) {
		TeamStatsPerGame tsp = new TeamStatsPerGame();
		tsp.setAbbr(team);
		tsp.setSeason(season);
		String[] data = string.split(";",-1);
		tsp.setMinute(Utility.stringToDouble(data[1]));
		tsp.setFg(Utility.stringToDouble(data[2]));
		tsp.setFga(Utility.stringToDouble(data[3]));
		tsp.setFga_pct(Utility.stringToDouble(data[4]));
		tsp.setFg3(Utility.stringToDouble(data[5]));
		tsp.setFg3a(Utility.stringToDouble(data[6]));
		tsp.setFg3_pct(Utility.stringToDouble(data[7]));
		tsp.setFg2(Utility.stringToDouble(data[8]));
		tsp.setFg2a(Utility.stringToDouble(data[9]));
		tsp.setFg2_pct(Utility.stringToDouble(data[10]));
		tsp.setFt(Utility.stringToDouble(data[11]));
		tsp.setFta(Utility.stringToDouble(data[12]));
		tsp.setFt_pct(Utility.stringToDouble(data[13]));
		tsp.setOrb(Utility.stringToDouble(data[14]));
		tsp.setDrb(Utility.stringToDouble(data[15]));
		tsp.setTrb(Utility.stringToDouble(data[16]));
		tsp.setAst(Utility.stringToDouble(data[17]));
		tsp.setStl(Utility.stringToDouble(data[18]));
		tsp.setBlk(Utility.stringToDouble(data[19]));
		tsp.setTov(Utility.stringToDouble(data[20]));
		tsp.setPf(Utility.stringToDouble(data[21]));
		tsp.setPts(Utility.stringToDouble(data[22]));
		return tsp;
	}

	private TeamStatsTotal getTeamTotal(String team, String season, String string) {
		TeamStatsTotal tst = totalmap.get(team+season);
		if(tst==null){
			tst = new TeamStatsTotal();
			tst.setAbbr(team);
			tst.setSeason(season);
			tst.setHeight("");
		}
		
		String[] data = string.split(";",-1);
		tst.setNum_of_game(Utility.stringToInt(data[0]));
		tst.setMinute(Utility.stringToInt(data[1]));
		tst.setFg(Utility.stringToInt(data[2]));
		tst.setFga(Utility.stringToInt(data[3]));
		tst.setFga_pct(Utility.stringToDouble(data[4]));
		tst.setFg3(Utility.stringToInt(data[5]));
		tst.setFg3a(Utility.stringToInt(data[6]));
		tst.setFg3_pct(Utility.stringToDouble(data[7]));
		tst.setFg2(Utility.stringToInt(data[8]));
		tst.setFg2a(Utility.stringToInt(data[9]));
		tst.setFg2_pct(Utility.stringToDouble(data[10]));
		tst.setFt(Utility.stringToInt(data[11]));
		tst.setFta(Utility.stringToInt(data[12]));
		tst.setFt_pct(Utility.stringToDouble(data[13]));
		tst.setOrb(Utility.stringToInt(data[14]));
		tst.setDrb(Utility.stringToInt(data[15]));
		tst.setTrb(Utility.stringToInt(data[16]));
		tst.setAst(Utility.stringToInt(data[17]));
		tst.setStl(Utility.stringToInt(data[18]));
		tst.setBlk(Utility.stringToInt(data[19]));
		tst.setTov(Utility.stringToInt(data[20]));
		tst.setPf(Utility.stringToInt(data[21]));
		tst.setPts(Utility.stringToInt(data[22]));
		totalmap.put(team+season, tst);
		return tst;
	}

	@Override
	public List<TeamInfo> getAllTeamInfo() {
		return infolist;
	}

	@Override
	public List<TeamStatsPerGame> getAllTeamPerGame() {
		return pergamelist;
	}

	@Override
	public List<TeamStatsTotal> getAllTeamTotal() {
		return totallist;
	}

	@Override
	public List<TeamStatsAdvanced> getAllTeamAdvanced() {
		return advancedlist;
	}

	@Override
	public List<OpponentStatsPerGame> getAllTeamOppPerGame() {
		return opp_pergamelist;
	}

	@Override
	public List<OpponentStatsTotal> getAllTeamOppTotal() {
		return opp_totallist;
	}

}
