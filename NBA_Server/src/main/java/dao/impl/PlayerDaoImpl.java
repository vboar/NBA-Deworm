package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.PlayerDao;
import entity.PlayerInfo;
import entity.PlayerSalary;
import entity.PlayerStatsAdvanced;
import entity.PlayerStatsPerGame;
import entity.PlayerStatsTotal;

public class PlayerDaoImpl implements PlayerDao {

	private SqlManager sqlManager = SqlManager.getSqlManager();
	
	@Override
	public void insertPlayerInfo(List<PlayerInfo> list) {	
		
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

}
