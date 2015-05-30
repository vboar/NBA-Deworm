package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.TeamDao;
import entity.OpponentStatsPerGame;
import entity.OpponentStatsTotal;
import entity.TeamInfo;
import entity.TeamStatsAdvanced;
import entity.TeamStatsPerGame;
import entity.TeamStatsTotal;

public class TeamDaoImpl implements TeamDao {

	private SqlManager sqlManager = SqlManager.getSqlManager();
	
	@Override
	public void insertTeamInfo(List<TeamInfo> list) {
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

}
