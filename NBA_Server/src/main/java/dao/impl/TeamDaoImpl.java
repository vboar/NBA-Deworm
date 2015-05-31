package dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.TeamDao;
import entity.OpponentStatsPerGame;
import entity.OpponentStatsTotal;
import entity.TeamInfo;
import entity.TeamStatsAdvanced;
import entity.TeamStatsPerGame;
import entity.TeamStatsTotal;

/**
 * TeamDao的具体实现
 * 
 * created by JaneLDQ on 2015年5月29日 上午10:48:42
 */
public class TeamDaoImpl implements TeamDao {

	private SqlManager sqlManager = SqlManager.getSqlManager();	
	
	@Override
	public List<TeamInfo> getAllTeamInfo() {

        sqlManager.getConnection();

        List<TeamInfo> list = new ArrayList<TeamInfo>();
        String sql = "SELECT * FROM team_info ORDER BY abbr";
        List<Map<String, Object>> maplist = sqlManager.queryMulti(sql, null);
        for (Map<String, Object> map: maplist) {
            list.add(getTeamInfo(map));
        }

        sqlManager.releaseAll();
        return list;
	}

	@Override
	public List<TeamStatsTotal> getTeamTotalBySeason(String season) {
		sqlManager.getConnection();
		
		List<TeamStatsTotal> list = new ArrayList<TeamStatsTotal>();
		String sql = "SELECT * FROM team_total WHERE season=? ORDER BY abbr";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{season});
		for(Map<String,Object> map: maplist){
			list.add(getTeamTotal(map));
		}
		sqlManager.releaseAll();
		
		return list;
	}

	@Override
	public List<TeamStatsPerGame> getTeamPerGameBySeason(String season) {
		sqlManager.getConnection();
		
		List<TeamStatsPerGame> list = new ArrayList<TeamStatsPerGame>();
		String sql = "SELECT * FROM team_per_game WHERE season=? ORDER BY abbr";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{season});
		for(Map<String,Object> map: maplist){
			list.add(getTeamPerGame(map));
		}
		sqlManager.releaseAll();
		
		return list;
	}

	@Override
	public List<OpponentStatsTotal> getTeamOppTotalBySeason(String season) {
		sqlManager.getConnection();
		
		List<OpponentStatsTotal> list = new ArrayList<OpponentStatsTotal>();
		String sql = "SELECT * FROM team_opp_total WHERE season=? ORDER BY abbr";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{season});
		for(Map<String,Object> map: maplist){
			list.add(getOppTotal(map));
		}
		sqlManager.releaseAll();
		
		return list;
	}

	@Override
	public List<OpponentStatsPerGame> getTeamOppPerGameBySeason(String season) {
		sqlManager.getConnection();
		
		List<OpponentStatsPerGame> list = new ArrayList<OpponentStatsPerGame>();
		String sql = "SELECT * FROM team_opp_per_game WHERE season=? ORDER BY abbr";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{season});
		for(Map<String,Object> map: maplist){
			list.add(getOppPerGame(map));
		}
		sqlManager.releaseAll();
		
		return list;
	}

	@Override
	public List<TeamStatsTotal> getTeamTotalByAbbr(String abbr) {
		sqlManager.getConnection();
		
		List<TeamStatsTotal> list = new ArrayList<TeamStatsTotal>();
		String sql = "SELECT * FROM team_total WHERE abbr=? ORDER BY season DESC";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{abbr});
		for(Map<String,Object> map: maplist){
			list.add(getTeamTotal(map));
		}
		sqlManager.releaseAll();
		
		return list;
	}

	@Override
	public List<TeamStatsPerGame> getTeamPerGameByAbbr(String abbr) {
		sqlManager.getConnection();
		
		List<TeamStatsPerGame> list = new ArrayList<TeamStatsPerGame>();
		String sql = "SELECT * FROM team_per_game WHERE abbr=? ORDER BY season DESC";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{abbr});
		for(Map<String,Object> map: maplist){
			list.add(getTeamPerGame(map));
		}
		sqlManager.releaseAll();
		
		return list;
	}

	@Override
	public List<OpponentStatsTotal> getTeamOppTotalByAbbr(String abbr) {
		sqlManager.getConnection();
		
		List<OpponentStatsTotal> list = new ArrayList<OpponentStatsTotal>();
		String sql = "SELECT * FROM team_opp_total WHERE abbr=? ORDER BY season DESC";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{abbr});
		for(Map<String,Object> map: maplist){
			list.add(getOppTotal(map));
		}
		sqlManager.releaseAll();
		
		return list;
	}

	@Override
	public List<OpponentStatsPerGame> getTeamOppPerGameByAbbr(String abbr) {
		sqlManager.getConnection();
		
		List<OpponentStatsPerGame> list = new ArrayList<OpponentStatsPerGame>();
		String sql = "SELECT * FROM team_opp_per_game WHERE abbr=? ORDER BY season DESC";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{abbr});
		for(Map<String,Object> map: maplist){
			list.add(getOppPerGame(map));
		}
		sqlManager.releaseAll();
		
		return list;
	}
	
	@Override
	public void insertTeamInfo(List<TeamInfo> list) {
		if(list.size()==0)
			return;
		
		sqlManager.getConnection();
		
		List<Object> infoObjects = new ArrayList<Object>();	
		String sqlInfo = "INSERT INTO team_info (" + 
				"name, " 			+ 	"abbr, " +
				"buildup_time, "	+ 	"location, " +
				"league, "			+ 	"division, " +
				"record, "			+ 	"playoff, " +
				"championship"			+ 
				") VALUES";
		
		for(TeamInfo info: list){
			
			infoObjects.add(info.getName());
			infoObjects.add(info.getAbbr());
			infoObjects.add(info.getBuildup_time());
			infoObjects.add(info.getLocation());
			infoObjects.add(info.getLeague());
			infoObjects.add(info.getDivision());
			infoObjects.add(info.getRecord());
			infoObjects.add(info.getPlayeroff_appearance());
			infoObjects.add(info.getChampionships());
			
			sqlInfo = sqlManager.appendSQL(sqlInfo, 9);
		}
		sqlInfo = sqlManager.fillSQL(sqlInfo);
		sqlManager.executeUpdateByList(sqlInfo, infoObjects);
		sqlManager.releaseConnection();
	}

	@Override
	public void insertTeamTotal(List<TeamStatsTotal> list) {
		if(list.size()==0)
			return;
		
		sqlManager.getConnection();
		
		List<Object> totalObjects = new ArrayList<Object>();
		
		String sqlTotal = "INSERT INTO team_total (" + 
				"abbr, " 			+ 	"season, " +
				"wins, "			+ 	"losses, " +
				"finish, "			+ 	"age, " +
				"height, "			+ 	"weight, " +
				"num_of_game, "		+ 	"minute, " +
				"fg, "				+	"fga," +
				"fga_pct, "			+	"fg3," +
				"fg3a, " 			+	"fg3_pct, " +
				"fg2," 				+	"fg2a, " +
				"fg2_pct, "			+ 	"ft, "	+	
				"fta, " 			+	"ft_pct, " +
				"orb, " 			+	"drb, " +
				"trb, " 			+	"ast, " +
				"stl, " 			+	"blk, " +
				"tov, " 			+	"pf, " +
				"pts " +
				") VALUES";
		
		for(TeamStatsTotal tst: list){
			totalObjects.add(tst.getAbbr());
			totalObjects.add(tst.getSeason());
			totalObjects.add(tst.getWins());
			totalObjects.add(tst.getLosses());
			totalObjects.add(tst.getFinish());
			totalObjects.add(tst.getAge());
			totalObjects.add(tst.getHeight());
			totalObjects.add(tst.getWeight());
			totalObjects.add(tst.getNum_of_game());
			totalObjects.add(tst.getMinute());
			totalObjects.add(tst.getFg());
			totalObjects.add(tst.getFga());
			totalObjects.add(tst.getFga_pct());
			totalObjects.add(tst.getFg3());
			totalObjects.add(tst.getFg3a());
			totalObjects.add(tst.getFg3_pct());
			totalObjects.add(tst.getFg2());
			totalObjects.add(tst.getFg2a());
			totalObjects.add(tst.getFg2_pct());
			totalObjects.add(tst.getFt());
			totalObjects.add(tst.getFta());
			totalObjects.add(tst.getFt_pct());
			totalObjects.add(tst.getOrb());
			totalObjects.add(tst.getDrb());
			totalObjects.add(tst.getTrb());
			totalObjects.add(tst.getAst());
			totalObjects.add(tst.getStl());
			totalObjects.add(tst.getBlk());
			totalObjects.add(tst.getTov());
			totalObjects.add(tst.getPf());
			totalObjects.add(tst.getPts());
			sqlTotal = sqlManager.appendSQL(sqlTotal, 31);
		}
		sqlTotal = sqlManager.fillSQL(sqlTotal);
		sqlManager.executeUpdateByList(sqlTotal, totalObjects);
		sqlManager.releaseConnection();
	}

	@Override
	public void insertTeamPerGame(List<TeamStatsPerGame> list) {
		if(list.size()==0)
			return;
		
		sqlManager.getConnection();
		
		List<Object> pergameObjects = new ArrayList<Object>();
		
		String sqlPergame = "INSERT INTO team_per_game (" + 
				"abbr, " 		+ 	"season, " +
				"minute, "		+	"fg, "	+	
				"fga, " 		+	"fga_pct, "	+	
				"fg3, " 		+	"fg3a, " +	
				"fg3_pct, " 	+	"fg2, " +	
				"fg2a, " 		+	"fg2_pct, " 	+ 	
				"ft, "			+	"fta, " +
				"ft_pct, "		+	"orb, " +	
				"drb, " 		+	"trb, " +	
				"ast, " 		+	"stl, " +	
				"blk, " 		+	"tov, " +	
				"pf, " 			+	"pts " +
				") VALUES";
		
		for(TeamStatsPerGame tsp: list){
			pergameObjects.add(tsp.getAbbr());
			pergameObjects.add(tsp.getSeason());
			pergameObjects.add(tsp.getMinute());
			pergameObjects.add(tsp.getFg());
			pergameObjects.add(tsp.getFga());
			pergameObjects.add(tsp.getFga_pct());
			pergameObjects.add(tsp.getFg3());
			pergameObjects.add(tsp.getFg3a());
			pergameObjects.add(tsp.getFg3_pct());
			pergameObjects.add(tsp.getFg2());
			pergameObjects.add(tsp.getFg2a());
			pergameObjects.add(tsp.getFg2_pct());
			pergameObjects.add(tsp.getFt());
			pergameObjects.add(tsp.getFta());
			pergameObjects.add(tsp.getFt_pct());
			pergameObjects.add(tsp.getOrb());
			pergameObjects.add(tsp.getDrb());
			pergameObjects.add(tsp.getTrb());
			pergameObjects.add(tsp.getAst());
			pergameObjects.add(tsp.getStl());
			pergameObjects.add(tsp.getBlk());
			pergameObjects.add(tsp.getTov());
			pergameObjects.add(tsp.getPf());
			pergameObjects.add(tsp.getPts());
			sqlPergame = sqlManager.appendSQL(sqlPergame, 24);
		}
		sqlPergame = sqlManager.fillSQL(sqlPergame);
		sqlManager.executeUpdateByList(sqlPergame, pergameObjects);
		sqlManager.releaseConnection();
	}

	@Override
	public void insertTeamAdvanced(List<TeamStatsAdvanced> list) {
		if(list.size()==0)
			return;
		
		sqlManager.getConnection();
		
		List<Object> advObjects = new ArrayList<Object>();
		
		String sqlAdv = "INSERT INTO team_advanced (" + 
				"abbr, " 				+ 	"season, " +
				"pw, "					+	"pl, " 	+	
				"mov, "					+	"sos, " +	
				"srs, " 				+	"off_rtg, " 	+	
				"def_rtg, " 			+	"pace," +	
				"fta_per_fga_pct, " 	+ 	"fg3a_per_fga_pct, "	+	
				"off_efg_pct, " 		+	"off_tov_pct, "	+	
				"orb_pct, " 			+	"off_ft_rate, " 	+	
				"opp_efg_pct, " 		+	"opp_tov_pct, " +	
				"drb_pct, " 			+	"opp_ft_rate, " 	+	
				"arena, "				+	"attendance" +	
				") VALUES";
		
		for(TeamStatsAdvanced tadv: list){
			advObjects.add(tadv.getAbbr());
			advObjects.add(tadv.getSeason());
			advObjects.add(tadv.getPw());
			advObjects.add(tadv.getPl());
			advObjects.add(tadv.getMov());
			advObjects.add(tadv.getSos());
			advObjects.add(tadv.getSrs());
			advObjects.add(tadv.getOff_rtg());
			advObjects.add(tadv.getDef_rtg());
			advObjects.add(tadv.getPace());
			advObjects.add(tadv.getFta_per_fga_pct());
			advObjects.add(tadv.getFg3a_per_fga_pct());
			advObjects.add(tadv.getOff_efg_pct());
			advObjects.add(tadv.getOff_tov_pct());
			advObjects.add(tadv.getOrb_pct());
			advObjects.add(tadv.getOff_ft_rate());
			advObjects.add(tadv.getOpp_efg_pct());
			advObjects.add(tadv.getOpp_tov_pct());
			advObjects.add(tadv.getDrb_pct());
			advObjects.add(tadv.getOff_ft_rate());
			advObjects.add(tadv.getArena());
			advObjects.add(tadv.getAttendance());
			sqlAdv = sqlManager.appendSQL(sqlAdv, 22);
		}
		sqlAdv = sqlManager.fillSQL(sqlAdv);
		sqlManager.executeUpdateByList(sqlAdv, advObjects);
		sqlManager.releaseConnection();
	}

	@Override
	public void insertTeamOppTotal(List<OpponentStatsTotal> list) {
		if(list.size()==0)
			return;
		
		sqlManager.getConnection();
		
		List<Object> optObjects = new ArrayList<Object>();
		
		String sqlOpt = "INSERT INTO team_opp_total (" + 
				"abbr, " 			+ 	"season, " +
				"num_of_game, " 	+
				"minute, "			+	"fg, "	+	
				"fga, " 			+	"fga_pct, "	+	
				"fg3, " 			+	"fg3a, " +	
				"fg3_pct, " 		+	"fg2, " +	
				"fg2a, " 			+	"fg2_pct, " 	+ 	
				"ft, "				+	"fta, " +
				"ft_pct, "			+	"orb, " +	
				"drb, " 			+	"trb, " +	
				"ast, " 			+	"stl, " +	
				"blk, " 			+	"tov, " +	
				"pf, " 				+	"pts " +
				") VALUES";
		
		for(OpponentStatsTotal opt: list){
			optObjects.add(opt.getAbbr());
			optObjects.add(opt.getSeason());
			optObjects.add(opt.getNum_of_game());
			optObjects.add(opt.getMinute());
			optObjects.add(opt.getFg());
			optObjects.add(opt.getFga());
			optObjects.add(opt.getFga_pct());
			optObjects.add(opt.getFg3());
			optObjects.add(opt.getFg3a());
			optObjects.add(opt.getFg3_pct());
			optObjects.add(opt.getFg2());
			optObjects.add(opt.getFg2a());
			optObjects.add(opt.getFg2_pct());
			optObjects.add(opt.getFt());
			optObjects.add(opt.getFta());
			optObjects.add(opt.getFt_pct());
			optObjects.add(opt.getOrb());
			optObjects.add(opt.getDrb());
			optObjects.add(opt.getTrb());
			optObjects.add(opt.getAst());
			optObjects.add(opt.getStl());
			optObjects.add(opt.getBlk());
			optObjects.add(opt.getTov());
			optObjects.add(opt.getPf());
			optObjects.add(opt.getPts());
			sqlOpt = sqlManager.appendSQL(sqlOpt, 25);
		}
		sqlOpt = sqlManager.fillSQL(sqlOpt);
		sqlManager.executeUpdateByList(sqlOpt, optObjects);
		sqlManager.releaseConnection();
	}

	@Override
	public void insertTeamOppPerGame(List<OpponentStatsPerGame> list) {
		if(list.size()==0)
			return;
		
		sqlManager.getConnection();
		
		List<Object> opgObjects = new ArrayList<Object>();
		
		String sqlOpg = "INSERT INTO team_opp_per_game (" + 
				"abbr, " 		+ 	"season, " +
				"minute, "		+	"fg, "	+	
				"fga, " 			+	"fga_pct, "	+	
				"fg3, " 			+	"fg3a, " +	
				"fg3_pct, " 		+	"fg2, " +	
				"fg2a, " 		+	"fg2_pct, " 	+ 	
				"ft, "			+	"fta, " +
				"ft_pct, "		+	"orb, " +	
				"drb, " 		+	"trb, " +	
				"ast, " 		+	"stl, " +	
				"blk, " 		+	"tov, " +	
				"pf, " 			+	"pts " +
				") VALUES";
		
		for(OpponentStatsPerGame tst: list){
			opgObjects.add(tst.getAbbr());
			opgObjects.add(tst.getSeason());
			opgObjects.add(tst.getMinute());
			opgObjects.add(tst.getFg());
			opgObjects.add(tst.getFga());
			opgObjects.add(tst.getFga_pct());
			opgObjects.add(tst.getFg3());
			opgObjects.add(tst.getFg3a());
			opgObjects.add(tst.getFg3_pct());
			opgObjects.add(tst.getFg2());
			opgObjects.add(tst.getFg2a());
			opgObjects.add(tst.getFg2_pct());
			opgObjects.add(tst.getFt());
			opgObjects.add(tst.getFta());
			opgObjects.add(tst.getFt_pct());
			opgObjects.add(tst.getOrb());
			opgObjects.add(tst.getDrb());
			opgObjects.add(tst.getTrb());
			opgObjects.add(tst.getAst());
			opgObjects.add(tst.getStl());
			opgObjects.add(tst.getBlk());
			opgObjects.add(tst.getTov());
			opgObjects.add(tst.getPf());
			opgObjects.add(tst.getPts());
			sqlOpg = sqlManager.appendSQL(sqlOpg, 24);
		}
		sqlOpg = sqlManager.fillSQL(sqlOpg);
		sqlManager.executeUpdateByList(sqlOpg, opgObjects);
		sqlManager.releaseConnection();
	}

	private TeamInfo getTeamInfo(Map<String, Object> map) {
		TeamInfo info = new TeamInfo();
        if (map.get("name") == null) {
            return null;
        }
        info.setName(map.get("name").toString());
        info.setAbbr(map.get("abbr").toString());
        info.setBuildup_time(map.get("buildup_time").toString());
        info.setLocation(map.get("location").toString());
        info.setLeague(map.get("league").toString());
        info.setDivision(map.get("division").toString());
        info.setRecord(map.get("record").toString());
        info.setPlayeroff_appearance(Integer.parseInt(map.get("playoff").toString()));
        info.setChampionships(Integer.parseInt(map.get("championship").toString()));
		return info;
	}
	
	private TeamStatsTotal getTeamTotal(Map<String, Object> map) {
		TeamStatsTotal tst = new TeamStatsTotal();
		if(map.get("abbr")==null){
			return null;
		}
		tst.setAbbr(map.get("abbr").toString());
		tst.setSeason(map.get("season").toString());
		tst.setWins(Integer.parseInt(map.get("wins").toString()));
		tst.setLosses(Integer.parseInt(map.get("losses").toString()));
		tst.setFinish(Integer.parseInt(map.get("finish").toString()));
		tst.setAge(Double.parseDouble(map.get("age").toString()));
		tst.setHeight(map.get("height").toString());
		tst.setWeight(Double.parseDouble(map.get("weight").toString()));
		tst.setNum_of_game(Integer.parseInt(map.get("num_of_game").toString()));
		tst.setMinute(Integer.parseInt(map.get("minute").toString()));
		tst.setFg(Integer.parseInt(map.get("fg").toString()));
		tst.setFga(Integer.parseInt(map.get("fga").toString()));
		tst.setFga_pct(Double.parseDouble(map.get("fga_pct").toString()));
		tst.setFg3(Integer.parseInt(map.get("fg3").toString()));
		tst.setFg3a(Integer.parseInt(map.get("fg3a").toString()));
		tst.setFg3_pct(Double.parseDouble(map.get("fg3_pct").toString()));
		tst.setFg2(Integer.parseInt(map.get("fg2").toString()));
		tst.setFg2a(Integer.parseInt(map.get("fg2a").toString()));
		tst.setFg2_pct(Double.parseDouble(map.get("fg2_pct").toString()));
		tst.setFt(Integer.parseInt(map.get("ft").toString()));
		tst.setFta(Integer.parseInt(map.get("fta").toString()));
		tst.setFt_pct(Double.parseDouble(map.get("ft_pct").toString()));
		tst.setOrb(Integer.parseInt(map.get("orb").toString()));
		tst.setDrb(Integer.parseInt(map.get("drb").toString()));
		tst.setTrb(Integer.parseInt(map.get("trb").toString()));
		tst.setAst(Integer.parseInt(map.get("ast").toString()));
		tst.setStl(Integer.parseInt(map.get("stl").toString()));
		tst.setBlk(Integer.parseInt(map.get("blk").toString()));
		tst.setTov(Integer.parseInt(map.get("tov").toString()));
		tst.setPf(Integer.parseInt(map.get("pf").toString()));
		tst.setPts(Integer.parseInt(map.get("pts").toString()));
		return tst;
	}

	private TeamStatsPerGame getTeamPerGame(Map<String, Object> map) {
		TeamStatsPerGame tsp = new TeamStatsPerGame();
		if(map.get("abbr")==null){
			return null;
		}
		tsp.setAbbr(map.get("abbr").toString());
		tsp.setSeason(map.get("season").toString());
		tsp.setMinute(Double.parseDouble(map.get("minute").toString()));
		tsp.setFg(Double.parseDouble(map.get("fg").toString()));
		tsp.setFga(Double.parseDouble(map.get("fga").toString()));
		tsp.setFga_pct(Double.parseDouble(map.get("fga_pct").toString()));
		tsp.setFg3(Double.parseDouble(map.get("fg3").toString()));
		tsp.setFg3a(Double.parseDouble(map.get("fg3a").toString()));
		tsp.setFg3_pct(Double.parseDouble(map.get("fg3_pct").toString()));
		tsp.setFg2(Double.parseDouble(map.get("fg2").toString()));
		tsp.setFg2a(Double.parseDouble(map.get("fg2a").toString()));
		tsp.setFg2_pct(Double.parseDouble(map.get("fg2_pct").toString()));
		tsp.setFt(Double.parseDouble(map.get("ft").toString()));
		tsp.setFta(Double.parseDouble(map.get("fta").toString()));
		tsp.setFt_pct(Double.parseDouble(map.get("ft_pct").toString()));
		tsp.setOrb(Double.parseDouble(map.get("orb").toString()));
		tsp.setDrb(Double.parseDouble(map.get("drb").toString()));
		tsp.setTrb(Double.parseDouble(map.get("trb").toString()));
		tsp.setAst(Double.parseDouble(map.get("ast").toString()));
		tsp.setStl(Double.parseDouble(map.get("stl").toString()));
		tsp.setBlk(Double.parseDouble(map.get("blk").toString()));
		tsp.setTov(Double.parseDouble(map.get("tov").toString()));
		tsp.setPf(Double.parseDouble(map.get("pf").toString()));
		tsp.setPts(Double.parseDouble(map.get("pts").toString()));
		return tsp;
	}
	
	private OpponentStatsTotal getOppTotal(Map<String, Object> map) {
		OpponentStatsTotal ost = new OpponentStatsTotal();
		if(map.get("abbr")==null){
			return null;
		}
		ost.setAbbr(map.get("abbr").toString());
		ost.setSeason(map.get("season").toString());
		ost.setMinute(Integer.parseInt(map.get("minute").toString()));
		ost.setFg(Integer.parseInt(map.get("fg").toString()));
		ost.setFga(Integer.parseInt(map.get("fga").toString()));
		ost.setFga_pct(Double.parseDouble(map.get("fga_pct").toString()));
		ost.setFg3(Integer.parseInt(map.get("fg3").toString()));
		ost.setFg3a(Integer.parseInt(map.get("fg3a").toString()));
		ost.setFg3_pct(Double.parseDouble(map.get("fg3_pct").toString()));
		ost.setFg2(Integer.parseInt(map.get("fg2").toString()));
		ost.setFg2a(Integer.parseInt(map.get("fg2a").toString()));
		ost.setFg2_pct(Double.parseDouble(map.get("fg2_pct").toString()));
		ost.setFt(Integer.parseInt(map.get("ft").toString()));
		ost.setFta(Integer.parseInt(map.get("fta").toString()));
		ost.setFt_pct(Double.parseDouble(map.get("ft_pct").toString()));
		ost.setOrb(Integer.parseInt(map.get("orb").toString()));
		ost.setDrb(Integer.parseInt(map.get("drb").toString()));
		ost.setTrb(Integer.parseInt(map.get("trb").toString()));
		ost.setAst(Integer.parseInt(map.get("ast").toString()));
		ost.setStl(Integer.parseInt(map.get("stl").toString()));
		ost.setBlk(Integer.parseInt(map.get("blk").toString()));
		ost.setTov(Integer.parseInt(map.get("tov").toString()));
		ost.setPf(Integer.parseInt(map.get("pf").toString()));
		ost.setPts(Integer.parseInt(map.get("pts").toString()));
		return ost;
	}
	
	private OpponentStatsPerGame getOppPerGame(Map<String, Object> map) {
		OpponentStatsPerGame osp = new OpponentStatsPerGame();
		if(map.get("abbr")==null){
			return null;
		}
		osp.setAbbr(map.get("abbr").toString());
		osp.setSeason(map.get("season").toString());
		osp.setMinute(Double.parseDouble(map.get("minute").toString()));
		osp.setFg(Double.parseDouble(map.get("fg").toString()));
		osp.setFga(Double.parseDouble(map.get("fga").toString()));
		osp.setFga_pct(Double.parseDouble(map.get("fga_pct").toString()));
		osp.setFg3(Double.parseDouble(map.get("fg3").toString()));
		osp.setFg3a(Double.parseDouble(map.get("fg3a").toString()));
		osp.setFg3_pct(Double.parseDouble(map.get("fg3_pct").toString()));
		osp.setFg2(Double.parseDouble(map.get("fg2").toString()));
		osp.setFg2a(Double.parseDouble(map.get("fg2a").toString()));
		osp.setFg2_pct(Double.parseDouble(map.get("fg2_pct").toString()));
		osp.setFt(Double.parseDouble(map.get("ft").toString()));
		osp.setFta(Double.parseDouble(map.get("fta").toString()));
		osp.setFt_pct(Double.parseDouble(map.get("ft_pct").toString()));
		osp.setOrb(Double.parseDouble(map.get("orb").toString()));
		osp.setDrb(Double.parseDouble(map.get("drb").toString()));
		osp.setTrb(Double.parseDouble(map.get("trb").toString()));
		osp.setAst(Double.parseDouble(map.get("ast").toString()));
		osp.setStl(Double.parseDouble(map.get("stl").toString()));
		osp.setBlk(Double.parseDouble(map.get("blk").toString()));
		osp.setTov(Double.parseDouble(map.get("tov").toString()));
		osp.setPf(Double.parseDouble(map.get("pf").toString()));
		osp.setPts(Double.parseDouble(map.get("pts").toString()));		
		return osp;
	}
	
}
