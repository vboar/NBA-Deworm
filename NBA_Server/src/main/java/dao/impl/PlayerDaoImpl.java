package dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.PlayerDao;
import entity.PlayerInfo;
import entity.PlayerSalary;
import entity.PlayerStatsAdvanced;
import entity.PlayerStatsPerGame;
import entity.PlayerStatsTotal;

public class PlayerDaoImpl implements PlayerDao {

	private SqlManager sqlManager = SqlManager.getSqlManager();

	@Override
	public List<PlayerInfo> getPlayerInfoByNameInitial(String initial) {
        sqlManager.getConnection();

        List<PlayerInfo> list = new ArrayList<PlayerInfo>();
        String sql = "SELECT * FROM player_info WHERE player_name LIKE '?%' ORDER BY player_name";
        List<Map<String, Object>> maplist = sqlManager.queryMulti(sql, new Object[]{initial});
        for(Map<String,Object> map: maplist){
        	list.add(getPlayerInfo(map));
        }
        sqlManager.releaseAll();
		return list;
	}

	@Override
	public PlayerInfo getPlayerInfoByName(String name) {
        sqlManager.getConnection();

        String sql = "SELECT * FROM player_info WHERE player_name=? ";
        Map<String, Object> map = sqlManager.querySimple(sql, new Object[]{name});

        sqlManager.releaseAll();
        return getPlayerInfo(map);
	}

	@Override
	public List<PlayerStatsTotal> getPlayerTotalByName(String name) {
		sqlManager.getConnection();
		
		List<PlayerStatsTotal> list = new ArrayList<PlayerStatsTotal>();
		String sql = "SELECT * FROM player_total WHERE player_name=? ORDER BY season DESC";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{name});
		for(Map<String,Object> map: maplist){
			list.add(getPlayerTotal(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsPerGame> getPlayerPerGameByName(String name) {
		sqlManager.getConnection();
		
		List<PlayerStatsPerGame> list = new ArrayList<PlayerStatsPerGame>();
		String sql = "SELECT * FROM player_per_game WHERE player_name=? ORDER BY season DESC";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{name});
		for(Map<String,Object> map: maplist){
			list.add(getPlayerPerGame(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsAdvanced> getPlayerAdvancedByName(String name) {
		sqlManager.getConnection();
		
		List<PlayerStatsAdvanced> list = new ArrayList<PlayerStatsAdvanced>();
		String sql = "SELECT * FROM player_advanced WHERE player_name=? ORDER BY season DESC";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{name});
		for(Map<String,Object> map: maplist){
			list.add(getPlayerAdvanced(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerSalary> getPlayerSalaryByName(String name) {
		sqlManager.getConnection();
		
		List<PlayerSalary> list = new ArrayList<PlayerSalary>();
		String sql = "SELECT * FROM player_salary WHERE player_name=? ORDER BY season DESC";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{name});
		for(Map<String,Object> map: maplist){
			list.add(getPlayerSalary(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsTotal> getPlayerTotalBySeason(String season) {
		sqlManager.getConnection();
		
		List<PlayerStatsTotal> list = new ArrayList<PlayerStatsTotal>();
		String sql = "SELECT * FROM player_total WHERE season=? ORDER BY player_name";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{season});
		for(Map<String,Object> map: maplist){
			list.add(getPlayerTotal(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsPerGame> getPlayerPerGameBySeason(String season) {
		sqlManager.getConnection();
		
		List<PlayerStatsPerGame> list = new ArrayList<PlayerStatsPerGame>();
		String sql = "SELECT * FROM player_per_game WHERE season=? ORDER BY player_name";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{season});
		for(Map<String,Object> map: maplist){
			list.add(getPlayerPerGame(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsAdvanced> getPlayerAdvancedBySeason(String season) {
		sqlManager.getConnection();
		
		List<PlayerStatsAdvanced> list = new ArrayList<PlayerStatsAdvanced>();
		String sql = "SELECT * FROM player_advanced WHERE season=? ORDER BY player_name";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{season});
		for(Map<String,Object> map: maplist){
			list.add(getPlayerAdvanced(map));
		}
		sqlManager.releaseAll();
		return list;
	}
	
	@Override
	public void insertPlayerInfo(List<PlayerInfo> list) {	
		
		if(list.size()==0)	return;
		
		sqlManager.getConnection();
		
		List<Object> infoObjects = new ArrayList<Object>();		
		String sqlInfo = "INSERT INTO player_info (" + 
				"player_name, " + 	"born, " +
				"hometown, "	+ 	"position, " +
				"height, "		+ 	"weight, " +
				"shoots, "		+ 	"high_school, " +
				"college, "		+ 	"draft, "	+
				"debut, "		+ 	"exp, " +
				"number " 		+
				") VALUES";
		
		for(PlayerInfo info: list){		
			infoObjects.add(info.getName());
			infoObjects.add(info.getBorn());
			infoObjects.add(info.getHometown());
			infoObjects.add(info.getPosition());
			infoObjects.add(info.getHeight());
			infoObjects.add(info.getWeight());
			infoObjects.add(info.getShoots());
			infoObjects.add(info.getHigh_school());
			infoObjects.add(info.getCollege());
			infoObjects.add(info.getDraft());
			infoObjects.add(info.getDebut());
			infoObjects.add(info.getExperience());
			infoObjects.add(info.getNumber());
			
			sqlInfo = sqlManager.appendSQL(sqlInfo, 13);
		}
		
		sqlInfo = sqlManager.fillSQL(sqlInfo);
		sqlManager.executeUpdateByList(sqlInfo, infoObjects);
		sqlManager.releaseConnection();
	}

	@Override
	public void insertPlayerSalary(List<PlayerSalary> list) {
		
		if(list.size()==0)	return;
		
		sqlManager.getConnection();	
		
		List<Object> salaryObjects = new ArrayList<Object>();		
		String sqlSalary = "INSERT INTO player_salary (" +
				"name, " +
				"season, " +
				"team, " +
				"salary " +
				") VALUES ";	
		
		for(PlayerSalary slr : list){		
			salaryObjects.add(slr.getName());
			salaryObjects.add(slr.getSeason());
			salaryObjects.add(slr.getTeam());
			salaryObjects.add(slr.getSalary());
			
			sqlSalary = sqlManager.appendSQL(sqlSalary, 4);
		}
		
		sqlSalary = sqlManager.fillSQL(sqlSalary);
		sqlManager.executeUpdateByList(sqlSalary, salaryObjects);
		sqlManager.releaseConnection();
	}

	@Override
	public void insertPlayerTotal(List<PlayerStatsTotal> list) {
		
		if(list.size()==0)	return;
		
		sqlManager.getConnection();
		
		List<Object> ptObjects = new ArrayList<Object>();
		String sqlTotal = "INSERT INTO player_total (" + 
				"player_name, "		+ "season, " +
				"is_normal, "		+ "team_abbr, " +
				"position, "		+ "num_of_game, " +
				"game_start, "		+ "minute, " +
				"fg, "				+ "fga, " +
				"fga_pct, "			+ "fg3, " +
				"fg3a, "			+ "fg3_pct, " +
				"fg2, "				+ "fg2a, " +
				"fg2_pct, "			+ "efg_pct, " +
				"ft, "				+ "fta, " +
				"ft_pct, "			+ "orb, " +
				"drb, "				+ "trb, " +
				"ast, "				+ "stl, " +
				"blk, "				+ "tov, " +
				"pf, "				+ "pts " +
				") VALUES ";
		
		for(PlayerStatsTotal pt : list){
			ptObjects.add(pt.getName());
			ptObjects.add(pt.getSeason());
			ptObjects.add(pt.getIs_normal());
			ptObjects.add(pt.getTeam());
			ptObjects.add(pt.getPosition());
			ptObjects.add(pt.getGame());
			ptObjects.add(pt.getGame_started());
			ptObjects.add(pt.getMinute());
			ptObjects.add(pt.getFg());
			ptObjects.add(pt.getFga());
			ptObjects.add(pt.getFga_pct());
			ptObjects.add(pt.getFg3());
			ptObjects.add(pt.getFg3a());
			ptObjects.add(pt.getFg3_pct());
			ptObjects.add(pt.getFg2());
			ptObjects.add(pt.getFg2a());
			ptObjects.add(pt.getFg2_pct());
			ptObjects.add(pt.getEfg_pct());
			ptObjects.add(pt.getFt());
			ptObjects.add(pt.getFta());
			ptObjects.add(pt.getFt_pct());
			ptObjects.add(pt.getOrb());
			ptObjects.add(pt.getDrb());
			ptObjects.add(pt.getTrb());
			ptObjects.add(pt.getAst());
			ptObjects.add(pt.getStl());
			ptObjects.add(pt.getBlk());
			ptObjects.add(pt.getTov());
			ptObjects.add(pt.getPf());
			ptObjects.add(pt.getPts());
			
			sqlTotal = sqlManager.appendSQL(sqlTotal, 30);
		}
		
		sqlTotal = sqlManager.fillSQL(sqlTotal);
		sqlManager.executeUpdateByList(sqlTotal, ptObjects);
		sqlManager.releaseConnection();
	}

	@Override
	public void insertPlayerPerGame(List<PlayerStatsPerGame> list) {
		
		if(list.size()==0)	return;
		
		sqlManager.getConnection();
		
		List<Object> ppgObjects = new ArrayList<Object>();
		String sqlPerGame = "INSERT INTO player_per_game (" + 
				"player_name, "		+ "season, " +
				"is_normal, "		+ "team_abbr, " +
				"position, "		+ "num_of_game, " +
				"game_start, "		+ "minute, " +
				"fg, "				+ "fga, " +
				"fga_pct, "			+ "fg3, " +
				"fg3a, "			+ "fg3_pct, " +
				"fg2, "				+ "fg2a, " +
				"fg2_pct, "			+ "efg_pct, " +
				"ft, "				+ "fta, " +
				"ft_pct, "			+ "orb, " +
				"drb, "				+ "trb, " +
				"ast, "				+ "stl, " +
				"blk, "				+ "tov, " +
				"pf, "				+ "pts" +
				") VALUES ";
		
		for(PlayerStatsPerGame ppg : list){
			ppgObjects.add(ppg.getName());
			ppgObjects.add(ppg.getSeason());
			ppgObjects.add(ppg.getIs_normal());
			ppgObjects.add(ppg.getTeam());
			ppgObjects.add(ppg.getPosition());
			ppgObjects.add(ppg.getGame());
			ppgObjects.add(ppg.getGame_started());
			ppgObjects.add(ppg.getMinute());
			ppgObjects.add(ppg.getFg());
			ppgObjects.add(ppg.getFga());
			ppgObjects.add(ppg.getFga_pct());
			ppgObjects.add(ppg.getFg3());
			ppgObjects.add(ppg.getFg3a());
			ppgObjects.add(ppg.getFg3_pct());
			ppgObjects.add(ppg.getFg2());
			ppgObjects.add(ppg.getFg2a());
			ppgObjects.add(ppg.getFg2_pct());
			ppgObjects.add(ppg.getEfg_pct());
			ppgObjects.add(ppg.getFt());
			ppgObjects.add(ppg.getFta());
			ppgObjects.add(ppg.getFt_pct());
			ppgObjects.add(ppg.getOrb());
			ppgObjects.add(ppg.getDrb());
			ppgObjects.add(ppg.getTrb());
			ppgObjects.add(ppg.getAst());
			ppgObjects.add(ppg.getStl());
			ppgObjects.add(ppg.getBlk());
			ppgObjects.add(ppg.getTov());
			ppgObjects.add(ppg.getPf());
			ppgObjects.add(ppg.getPts());
			
			sqlPerGame = sqlManager.appendSQL(sqlPerGame, 30);
		}
		
		sqlPerGame = sqlManager.fillSQL(sqlPerGame);
		sqlManager.executeUpdateByList(sqlPerGame, ppgObjects);
		sqlManager.releaseConnection();
	}

	@Override
	public void insertPlayerAdvanced(List<PlayerStatsAdvanced> list) {
		
		if(list.size()==0)	return;
		
		sqlManager.getConnection();
		
		List<Object> padvObjects = new ArrayList<Object>();
		String sqlAdvanced = "INSERT INTO player_advanced (" + 
				"player_name, "		+ "season, " +
				"is_normal, "		+ "team_abbr, " +
				"position, "		+ "num_of_game, " +
				"minute, " +
				"per, "				+ "ts_pct, " +
				"fa3a_per_fga_pct, "+ "fta_per_fga_pct, " +
				"orb_pct, "			+ "drb_pct, " +
				"trb_pct, "			+ "ast_pct, " +
				"stl_pct, "			+ "blk_pct, " +
				"tov_pct, "			+ "usg_pct, " +
				"ows, "				+ "dws, " +
				"ws, "				+ "ws_48, " +
				"obpm, "			+ "dbpm, " +
				"bpm, "				+ "vorp" +
				") VALUES ";
		
		for(PlayerStatsAdvanced padv : list){
			padvObjects.add(padv.getName());
			padvObjects.add(padv.getSeason());
			padvObjects.add(padv.getIs_normal());
			padvObjects.add(padv.getTeam());
			padvObjects.add(padv.getPosition());
			padvObjects.add(padv.getGame());
			padvObjects.add(padv.getMinute());
			padvObjects.add(padv.getPer());
			padvObjects.add(padv.getTs_pct());
			padvObjects.add(padv.getFa3a_per_fga_pct());
			padvObjects.add(padv.getFta_per_fga_pct());
			padvObjects.add(padv.getOrb_pct());
			padvObjects.add(padv.getDrb_pct());
			padvObjects.add(padv.getTrb_pct());
			padvObjects.add(padv.getAst_pct());
			padvObjects.add(padv.getStl_pct());
			padvObjects.add(padv.getBlk_pct());
			padvObjects.add(padv.getTov_pct());
			padvObjects.add(padv.getUsg_pct());
			padvObjects.add(padv.getOws());
			padvObjects.add(padv.getDws());
			padvObjects.add(padv.getWs());
			padvObjects.add(padv.getWs_48());
			padvObjects.add(padv.getObpm());
			padvObjects.add(padv.getDbpm());
			padvObjects.add(padv.getBpm());
			padvObjects.add(padv.getVorp());
			
			sqlAdvanced = sqlManager.appendSQL(sqlAdvanced, 27);
		}
		
		sqlAdvanced = sqlManager.fillSQL(sqlAdvanced);
		sqlManager.executeUpdateByList(sqlAdvanced, padvObjects);
		sqlManager.releaseConnection();
	}

	private PlayerInfo getPlayerInfo(Map<String, Object> map) {
		PlayerInfo info = new PlayerInfo();
		if(map.get("player_name")==null){
			return null;
		}
		info.setName(map.get("player_name").toString());
		info.setBorn(map.get("born").toString());
		info.setHometown(map.get("hometown").toString());
		info.setPosition(map.get("position").toString());
		info.setHeight(map.get("height").toString());
		info.setWeight(Integer.parseInt(map.get("weight").toString()));
		info.setShoots(map.get("shoots").toString());
		info.setHigh_school(map.get("high_school").toString());
		info.setCollege(map.get("college").toString());
		info.setDraft(map.get("draft").toString());
		info.setDebut(map.get("debut").toString());
		info.setExperience(Integer.parseInt(map.get("exp").toString()));
		info.setNumber(Integer.parseInt(map.get("number").toString()));
		return info;
	}
	
	private PlayerStatsTotal getPlayerTotal(Map<String, Object> map) {
		PlayerStatsTotal pst = new PlayerStatsTotal();
		if(map.get("player_name")==null){
			return null;
		}
		pst.setName(map.get("player_name").toString());
		pst.setSeason(map.get("season").toString());
		pst.setIs_normal(Integer.parseInt(map.get("is_normal").toString()));
		pst.setTeam(map.get("team_abbr").toString());
		pst.setPosition(map.get("position").toString());
		pst.setGame(Integer.parseInt(map.get("num_of_game").toString()));
		pst.setGame_started(Integer.parseInt(map.get("game_start").toString()));
		pst.setMinute(Integer.parseInt(map.get("minute").toString()));
		pst.setFg(Integer.parseInt(map.get("fg").toString()));
		pst.setFga(Integer.parseInt(map.get("fga").toString()));
		pst.setFga_pct(Double.parseDouble(map.get("fga_pct").toString()));
		pst.setFg3(Integer.parseInt(map.get("fg3").toString()));
		pst.setFg3a(Integer.parseInt(map.get("fg3a").toString()));
		pst.setFg3_pct(Double.parseDouble(map.get("fg3_pct").toString()));
		pst.setFg2(Integer.parseInt(map.get("fg2").toString()));
		pst.setFg2a(Integer.parseInt(map.get("fg2a").toString()));
		pst.setFg2_pct(Double.parseDouble(map.get("fg2_pct").toString()));
		pst.setEfg_pct(Double.parseDouble(map.get("efg_pct").toString()));
		pst.setFt(Integer.parseInt(map.get("ft").toString()));
		pst.setFta(Integer.parseInt(map.get("fta").toString()));
		pst.setFt_pct(Double.parseDouble(map.get("ft_pct").toString()));
		pst.setOrb(Integer.parseInt(map.get("orb").toString()));
		pst.setDrb(Integer.parseInt(map.get("drb").toString()));
		pst.setTrb(Integer.parseInt(map.get("trb").toString()));
		pst.setAst(Integer.parseInt(map.get("ast").toString()));
		pst.setStl(Integer.parseInt(map.get("stl").toString()));
		pst.setBlk(Integer.parseInt(map.get("blk").toString()));
		pst.setTov(Integer.parseInt(map.get("tov").toString()));
		pst.setPf(Integer.parseInt(map.get("pf").toString()));		
		pst.setPts(Integer.parseInt(map.get("pts").toString()));		
		return pst;
	}

	private PlayerStatsPerGame getPlayerPerGame(Map<String, Object> map) {
		PlayerStatsPerGame psp = new PlayerStatsPerGame();
		if(map.get("player_name")==null){
			return null;
		}
		psp.setName(map.get("player_name").toString());
		psp.setSeason(map.get("season").toString());
		psp.setIs_normal(Integer.parseInt(map.get("is_normal").toString()));
		psp.setTeam(map.get("team_abbr").toString());
		psp.setPosition(map.get("position").toString());
		psp.setGame(Integer.parseInt(map.get("num_of_game").toString()));
		psp.setGame_started(Integer.parseInt(map.get("game_start").toString()));
		psp.setMinute(Double.parseDouble(map.get("minute").toString()));
		psp.setFg(Double.parseDouble(map.get("fg").toString()));
		psp.setFga(Double.parseDouble(map.get("fga").toString()));
		psp.setFga_pct(Double.parseDouble(map.get("fga_pct").toString()));
		psp.setFg3(Double.parseDouble(map.get("fg3").toString()));
		psp.setFg3a(Double.parseDouble(map.get("fg3a").toString()));
		psp.setFg3_pct(Double.parseDouble(map.get("fg3_pct").toString()));
		psp.setFg2(Double.parseDouble(map.get("fg2").toString()));
		psp.setFg2a(Double.parseDouble(map.get("fg2a").toString()));
		psp.setFg2_pct(Double.parseDouble(map.get("fg2_pct").toString()));
		psp.setEfg_pct(Double.parseDouble(map.get("efg_pct").toString()));
		psp.setFt(Double.parseDouble(map.get("ft").toString()));
		psp.setFta(Double.parseDouble(map.get("fta").toString()));
		psp.setFt_pct(Double.parseDouble(map.get("ft_pct").toString()));
		psp.setOrb(Double.parseDouble(map.get("orb").toString()));
		psp.setDrb(Double.parseDouble(map.get("drb").toString()));
		psp.setTrb(Double.parseDouble(map.get("trb").toString()));
		psp.setAst(Double.parseDouble(map.get("ast").toString()));
		psp.setStl(Double.parseDouble(map.get("stl").toString()));
		psp.setBlk(Double.parseDouble(map.get("blk").toString()));
		psp.setTov(Double.parseDouble(map.get("tov").toString()));
		psp.setPf(Double.parseDouble(map.get("pf").toString()));		
		psp.setPts(Double.parseDouble(map.get("pts").toString()));	
		return psp;
	}
	
	private PlayerStatsAdvanced getPlayerAdvanced(Map<String, Object> map) {
		PlayerStatsAdvanced psa = new PlayerStatsAdvanced();
		if(map.get("player_name")==null){
			return null;
		}
		psa.setName(map.get("player_name").toString());
		psa.setSeason(map.get("season").toString());
		psa.setIs_normal(Integer.parseInt(map.get("is_normal").toString()));
		psa.setTeam(map.get("team_abbr").toString());
		psa.setGame(Integer.parseInt(map.get("num_of_game").toString()));
		psa.setMinute(Integer.parseInt(map.get("minute").toString()));
		psa.setPer(Double.parseDouble(map.get("per").toString()));
		psa.setTs_pct(Double.parseDouble(map.get("ts_pct").toString()));
		psa.setFa3a_per_fga_pct(Double.parseDouble(map.get("fa3a_per_fga_pct").toString()));
		psa.setFta_per_fga_pct(Double.parseDouble(map.get("fta_per_fga_pct").toString()));
		psa.setOrb_pct(Double.parseDouble(map.get("orb_pct").toString()));
		psa.setDrb_pct(Double.parseDouble(map.get("drb_pct").toString()));
		psa.setTrb_pct(Double.parseDouble(map.get("trb_pct").toString()));
		psa.setAst_pct(Double.parseDouble(map.get("ast_pct").toString()));
		psa.setStl_pct(Double.parseDouble(map.get("ast_pct").toString()));
		psa.setBlk_pct(Double.parseDouble(map.get("blk_pct").toString()));
		psa.setTov_pct(Double.parseDouble(map.get("tov_pct").toString()));
		psa.setUsg_pct(Double.parseDouble(map.get("usg_pct").toString()));
		psa.setOws(Double.parseDouble(map.get("ows").toString()));
		psa.setDws(Double.parseDouble(map.get("dws").toString()));
		psa.setWs(Double.parseDouble(map.get("ws").toString()));
		psa.setWs_48(Double.parseDouble(map.get("ws_48").toString()));
		psa.setObpm(Double.parseDouble(map.get("obpm").toString()));
		psa.setDbpm(Double.parseDouble(map.get("dbpm").toString()));
		psa.setBpm(Double.parseDouble(map.get("bpm").toString()));
		psa.setVorp(Double.parseDouble(map.get("vorp").toString()));
		return psa;
	}
	
	private PlayerSalary getPlayerSalary(Map<String, Object> map) {
		PlayerSalary ps = new PlayerSalary();
		if(map.get("name")==null){
			return null;
		}
		ps.setName(map.get("name").toString());
		ps.setSeason(map.get("season").toString());
		ps.setTeam(map.get("team").toString());
		ps.setSalary(map.get("salary").toString());
		return ps;
	}
	
}
