package dao.impl;

import dao.InitDao;
import dao.MatchDao;
import dao.PlayerDao;
import dao.RawMatchDao;
import dao.RawPlayerDao;
import dao.RawTeamDao;
import dao.TeamDao;

/**
 * 数据库初始化的具体实现
 * 
 * created by JaneLDQ on 2015年5月17日 下午10:42:00
 */
public class InitDaoImpl implements InitDao {

	private SqlManager sqlManager = SqlManager.getSqlManager();
	
	public void initDatabase() {
		InitDaoImpl id = new InitDaoImpl();
		id.dropTable();
		id.createTable();
		id.fillTable();
	}
	
	@Override
	public void createTable() {
		
		System.out.println("Creating table...");
		
		sqlManager.getConnection();

        // 赛季信息表
        String sql = "CREATE TABLE season (" +
                "season VARCHAR(5) PRIMARY KEY NOT NULL" +
                ")";
        sqlManager.executeUpdate(sql);

        // 比赛概况表
        sql = "CREATE TABLE match_info (" +
                "game_id VARCHAR(20) PRIMARY KEY NOT NULL," +
                "season VARCHAR(5) ," +
                "is_normal VARCHAR(5) ," +
                "date VARCHAR(10) ," +
                "location VARCHAR(64) ," +
                "home_team VARCHAR(3) ," +
                "guest_team VARCHAR(3) ," +
                "home_point INT," +
                "guest_point INT ," +
                "time VARCHAR(6) " +
                ")";
        sqlManager.executeUpdate(sql);

        // 比赛比分表
        sql = "CREATE TABLE match_score (" +
                "game_id VARCHAR(20) NOT NULL," +
                "section INT DEFAULT 0 NOT NULL," +
                "home_point INT ," +
                "guest_point INT " +
                ")";
        sqlManager.executeUpdate(sql);

        // 比赛球员基本数据表
        sql = "CREATE TABLE match_player_basic (" +
                "game_id VARCHAR(20) NOT NULL," +
                "player_name VARCHAR(48) NOT NULL," +
                "team_abbr VARCHAR(3) NOT NULL," +
                "starter VARCHAR(16) NOT NULL," +
                "minute DOUBLE ," +
                "fg INT ," +
                "fga INT ," +
                "fga_pct DOUBLE ," +
                "fg3 INT ," +
                "fg3a INT ," +
                "fg3_pct DOUBLE ," +
                "ft INT ," +
                "fta INT ," +
                "ft_pct DOUBLE ," +
                "orb INT ," +
                "drb INT ," +
                "trb INT ," +
                "ast INT ," +
                "stl INT ," +
                "blk INT ," +
                "tov INT ," +
                "pf INT ," +
                "pts INT ," +
                "plus_minus DOUBLE" +
                ")";
        sqlManager.executeUpdate(sql);

        //比赛球员高阶数据表
        sql = "CREATE TABLE match_player_advanced (" +
                "game_id VARCHAR(20) NOT NULL," +
                "player_name VARCHAR(48) NOT NULL," +
                "team_abbr VARCHAR(3) NOT NULL," +
                "starter VARCHAR(16) NOT NULL," +
                "minute DOUBLE ," +
                "ts_pct DOUBLE ," +
                "efg_pct DOUBLE ," +
                "fa3a_per_fga_pct DOUBLE ," +
                "fta_per_fga_pct DOUBLE ," +
                "orb_pct DOUBLE ," +
                "drb_pct DOUBLE ," +
                "trb_pct DOUBLE ," +
                "ast_pct DOUBLE ," +
                "stl_pct DOUBLE ," +
                "tov_pct DOUBLE ," +
                "blk_pct DOUBLE ," +
                "usg_pct DOUBLE ," +
                "off_rtg DOUBLE ," +
                "def_rtg DOUBLE " +
        		")";
        sqlManager.executeUpdate(sql);

        //球员基本信息表
        sql = "CREATE TABLE player_info ("+
                "player_name VARCHAR(48) PRIMARY KEY NOT NULL," +
                "born VARCHAR(10)," +
                "hometown VARCHAR(64)," +
                "position VARCHAR(64)," +
                "height VARCHAR(5)," +
                "weight INT," +
                "shoots VARCHAR(64)," +
                "high_school VARCHAR(128)," +
                "college VARCHAR(128)," +
                "draft VARCHAR(128)," +
                "debut VARCHAR(128)," +
                "exp INT," +
                "number INT" +
        		")";
        sqlManager.executeUpdate(sql);

        //球员总数据表
        sql = "CREATE TABLE player_total (" +
        		"player_name VARCHAR(48) NOT NULL," +
        		"season VARCHAR(6) NOT NULL," +
        		"is_normal INT," +
        		"team_abbr VARCHAR(3)," +
        		"position VARCHAR(8)," +
        		"num_of_game INT ," +
        		"game_start INT ," +
        		"minute INT ," +
        		"fg INT ," +
        		"fga INT ," +
        		"fga_pct DOUBLE ," +
        		"fg3 INT ," +
        		"fg3a INT ," +
        		"fg3_pct DOUBLE ," +
        		"fg2 INT ," +
        		"fg2a INT ," +
        		"fg2_pct DOUBLE ," +
        		"efg_pct DOUBLE ," +
        		"ft INT ," +
        		"fta INT ," +
        		"ft_pct DOUBLE ," +
        		"orb INT ," +
        		"drb INT ," +
        		"trb INT ," +
        		"ast INT ," +
        		"stl INT ," +
        		"blk INT ," +
        		"tov INT ," +
        		"pf INT ," +
        		"pts INT" +
        		")";
        sqlManager.executeUpdate(sql);
        
        //球员场均数据表
        sql = "CREATE TABLE player_per_game (" +
        		"player_name VARCHAR(48) NOT NULL," +
        		"season VARCHAR(6) NOT NULL," +
        		"is_normal INT ," +
        		"team_abbr VARCHAR(3)," +
        		"position VARCHAR(8)," +
        		"num_of_game INT ," +
        		"game_start INT ," +
        		"minute DOUBLE ," +
        		"fg DOUBLE ," +
        		"fga DOUBLE ," +
        		"fga_pct DOUBLE ," +
        		"fg3 DOUBLE ," +
        		"fg3a DOUBLE ," +
        		"fg3_pct DOUBLE ," +
        		"fg2 DOUBLE ," +
        		"fg2a DOUBLE ," +
        		"fg2_pct DOUBLE ," +
        		"efg_pct DOUBLE ," +
        		"ft DOUBLE ," +
        		"fta DOUBLE ," +
        		"ft_pct DOUBLE ," +
        		"orb DOUBLE ," +
        		"drb DOUBLE ," +
        		"trb DOUBLE ," +
        		"ast DOUBLE ," +
        		"stl DOUBLE ," +
        		"blk DOUBLE ," +
        		"tov DOUBLE ," +
        		"pf DOUBLE ," +
        		"pts DOUBLE" +
        		")";
        sqlManager.executeUpdate(sql);
        
        //球员高阶数据表
        sql = "CREATE TABLE player_advanced (" +
        		"player_name VARCHAR(32) NOT NULL," +
        		"season VARCHAR(6) NOT NULL," +
        		"is_normal INT ," +
        		"team_abbr VARCHAR(3)," +
        		"position VARCHAR(8)," +
        		"num_of_game INT ," +
        		"minute INT ," +
        		"per DOUBLE ," +
        		"ts_pct DOUBLE ," +
        		"fa3a_per_fga_pct DOUBLE ," +
        		"fta_per_fga_pct DOUBLE ," +
        		"orb_pct DOUBLE ," +
        		"drb_pct DOUBLE ," +
        		"trb_pct DOUBLE ," +
        		"ast_pct DOUBLE ," +
        		"stl_pct DOUBLE ," +
        		"blk_pct DOUBLE ," +
        		"tov_pct DOUBLE ," +
        		"usg_pct DOUBLE ," +
        		"ows DOUBLE ," +
        		"dws DOUBLE ," +
        		"ws DOUBLE ," +
        		"ws_48 DOUBLE ," +
        		"obpm DOUBLE ," +
        		"dbpm DOUBLE ," +
        		"bpm DOUBLE ," +
        		"vorp DOUBLE " +
        		")";
        sqlManager.executeUpdate(sql);
        
        //球员薪水表
        sql = "CREATE TABLE player_salary (" +
        		"name VARCHAR(32) NOT NULL," +
        		"season VARCHAR(6) NOT NULL," +
        		"team VARCHAR(48) NOT NULL," +
        		"salary VARCHAR(16) NOT NULL" +
        		")";
        sqlManager.executeUpdate(sql);
        
        //球队基本信息表
        sql = "CREATE TABLE team_info (" +
        		"name VARCHAR(48) PRIMARY KEY NOT NULL," +
        		"abbr VARCHAR(3) NOT NULL," +
        		"buildup_time VARCHAR(4)," +
        		"location VARCHAR(64)," +
        		"league VARCHAR(4)," +
        		"division VARCHAR(16)," +
        		"record VARCHAR(24)," +
        		"playoff INT," +
        		"championship INT" +
        		")";
        sqlManager.executeUpdate(sql);
        
        //球队总数据表
        sql = "CREATE TABLE team_total (" +
        		"abbr VARCHAR(3) NOT NULL," +
        		"season VARCHAR(5) NOT NULL," +
        		"wins INT ," +
        		"losses INT ," +
        		"finish INT ," +
        		"age DOUBLE ," +
        		"height VARCHAR(3)," +
        		"weight DOUBLE ," +
        		"num_of_game INT ," +
        		"minute INT ," +
        		"fg INT ," +
        		"fga INT ," +
        		"fga_pct DOUBLE ," +
        		"fg3 INT ," +
        		"fg3a INT ," +
        		"fg3_pct DOUBLE ," +
        		"fg2 INT ," +
        		"fg2a INT ," +
        		"fg2_pct DOUBLE ," +
        		"ft INT ," +
        		"fta INT ," +
        		"ft_pct DOUBLE ," +
        		"orb INT ," +
        		"drb INT ," +
        		"trb INT ," +
        		"ast INT ," +
        		"stl INT ," +
        		"blk INT ," +
        		"tov INT ," +
        		"pf INT ," +
        		"pts INT" +
        		")";
        sqlManager.executeUpdate(sql);
        
        //球队场均数据表
        sql = "CREATE TABLE team_per_game (" +
        		"abbr VARCHAR(3) NOT NULL," +
        		"season VARCHAR(5) NOT NULL," +
        		"minute DOUBLE ," +
        		"fg DOUBLE ," +
        		"fga DOUBLE ," +
        		"fga_pct DOUBLE ," +
        		"fg3 DOUBLE ," +
        		"fg3a DOUBLE ," +
        		"fg3_pct DOUBLE ," +
        		"fg2 DOUBLE ," +
        		"fg2a DOUBLE ," +
        		"fg2_pct DOUBLE ," +
        		"ft DOUBLE ," +
        		"fta DOUBLE ," +
        		"ft_pct DOUBLE ," +
        		"orb DOUBLE ," +
        		"drb DOUBLE ," +
        		"trb DOUBLE ," +
        		"ast DOUBLE ," +
        		"stl DOUBLE ," +
        		"blk DOUBLE ," +
        		"tov DOUBLE ," +
        		"pf DOUBLE ," +
        		"pts DOUBLE " +
        		")";
        sqlManager.executeUpdate(sql);
        
        //球队高阶数据表
        sql = "CREATE TABLE team_advanced (" +
        		"abbr VARCHAR(3) NOT NULL," +
        		"season VARCHAR(5) NOT NULL," +
        		"pw DOUBLE ," +
        		"pl DOUBLE ," +
        		"mov DOUBLE ," +
        		"sos DOUBLE ," +
        		"srs DOUBLE ," +
        		"off_rtg DOUBLE ," +
        		"def_rtg DOUBLE ," +
        		"pace DOUBLE ," +
        		"fta_per_fga_pct DOUBLE ," +
        		"fg3a_per_fga_pct DOUBLE ," +
        		"off_efg_pct DOUBLE ," +
        		"off_tov_pct DOUBLE ," +
        		"orb_pct DOUBLE ," +
        		"off_ft_rate DOUBLE ," +
        		"opp_efg_pct DOUBLE ," +
        		"opp_tov_pct DOUBLE ," +
        		"drb_pct DOUBLE ," +
        		"opp_ft_rate DOUBLE ," +
        		"arena VARCHAR(48) NOT NULL," +
        		"attendance INT" +
        		")";
        sqlManager.executeUpdate(sql);
        //对手总数据表
        sql = "CREATE TABLE team_opp_total (" +
        		"abbr VARCHAR(3) NOT NULL," +
        		"season VARCHAR(5) NOT NULL," +
        		"num_of_game INT ," +
        		"minute INT , " +
        		"fg INT ," +
        		"fga INT ," +
        		"fga_pct DOUBLE ," +
        		"fg3 INT ," +
        		"fg3a INT ," +
        		"fg3_pct DOUBLE ," +
        		"fg2 INT ," +
        		"fg2a INT ," +
        		"fg2_pct DOUBLE ," +
        		"efg_pct DOUBLE ," +
        		"ft INT ," +
        		"fta INT ," +
        		"ft_pct DOUBLE ," +
        		"orb INT ," +
        		"drb INT ," +
        		"trb INT ," +
        		"ast INT ," +
        		"stl INT ," +
        		"blk INT ," +
        		"tov INT ," +
        		"pf INT ," +
        		"pts INT" +
        		")";
        sqlManager.executeUpdate(sql);
        //对手场均数据表
        sql = "CREATE TABLE team_opp_per_game (" +
        		"abbr VARCHAR(3) NOT NULL," +
        		"season VARCHAR(5) NOT NULL," +
        		"minute DOUBLE ," +
        		"fg DOUBLE ," +
        		"fga DOUBLE ," +
        		"fga_pct DOUBLE ," +
        		"fg3 DOUBLE ," +
        		"fg3a DOUBLE ," +
        		"fg3_pct DOUBLE ," +
        		"fg2 DOUBLE ," +
        		"fg2a DOUBLE ," +
        		"fg2_pct DOUBLE ," +
        		"ft DOUBLE ," +
        		"fta DOUBLE ," +
        		"ft_pct DOUBLE ," +
        		"orb DOUBLE ," +
        		"drb DOUBLE ," +
        		"trb DOUBLE ," +
        		"ast DOUBLE ," +
        		"stl DOUBLE ," +
        		"blk DOUBLE ," +
        		"tov DOUBLE ," +
        		"pf DOUBLE ," +
        		"pts DOUBLE " +
        		")";
        sqlManager.executeUpdate(sql);
        
        sqlManager.releaseConnection();
        
        System.out.println("Create tables done!");
	}

	@Override
	public void dropTable() {
        sqlManager.getConnection();
        sqlManager.executeUpdate("DROP TABLE season");
        sqlManager.executeUpdate("DROP TABLE match_info");
        sqlManager.executeUpdate("DROP TABLE match_score");
        sqlManager.executeUpdate("DROP TABLE match_player_basic");
        sqlManager.executeUpdate("DROP TABLE match_player_advanced");
        sqlManager.executeUpdate("DROP TABLE player_info");
        sqlManager.executeUpdate("DROP TABLE player_total");
        sqlManager.executeUpdate("DROP TABLE player_per_game");
        sqlManager.executeUpdate("DROP TABLE player_advanced");
        sqlManager.executeUpdate("DROP TABLE player_salary");
        sqlManager.executeUpdate("DROP TABLE team_info");
        sqlManager.executeUpdate("DROP TABLE team_total");
        sqlManager.executeUpdate("DROP TABLE team_per_game");
        sqlManager.executeUpdate("DROP TABLE team_advanced");
        sqlManager.executeUpdate("DROP TABLE team_opp_total");
        sqlManager.executeUpdate("DROP TABLE team_opp_per_game");
        sqlManager.releaseConnection();
	}

	@Override
	public void truncateTable() {
        sqlManager.getConnection();
        sqlManager.executeUpdate("DELETE FROM season");
        sqlManager.executeUpdate("DELETE FROM match_info");
        sqlManager.executeUpdate("DELETE FROM match_score");
        sqlManager.executeUpdate("DELETE FROM match_player_basic");
        sqlManager.executeUpdate("DELETE FROM match_player_advanced");
        sqlManager.executeUpdate("DELETE FROM player_info");
        sqlManager.executeUpdate("DELETE FROM player_total");
        sqlManager.executeUpdate("DELETE FROM player_per_game");
        sqlManager.executeUpdate("DELETE FROM player_advanced");
        sqlManager.executeUpdate("DELETE FROM player_salary");
        sqlManager.executeUpdate("DELETE FROM team_info");
        sqlManager.executeUpdate("DELETE FROM team_total");
        sqlManager.executeUpdate("DELETE FROM team_per_game");
        sqlManager.executeUpdate("DELETE FROM team_advanced");
        sqlManager.executeUpdate("DELETE FROM team_opp_total");
        sqlManager.executeUpdate("DELETE FROM team_opp_per_game");
        sqlManager.releaseConnection();
	}

	@Override
	public void fillTable() {
		// 比赛数据入库
		System.out.println("Insert Match Begin...");	
		RawMatchDao rawMatch = DaoFactoryImpl.getDaoFactory().getRawMatchDao();
		MatchDao match = DaoFactoryImpl.getDaoFactory().getMatchDao();
		match.insertMatch(rawMatch.getAllMatch());
		System.out.println("Insert Match Done!\n");
		
		// 球员数据入库
		System.out.println("Insert Player Begin...");
		RawPlayerDao rawPlayer = DaoFactoryImpl.getDaoFactory().getRawPlayerDao();
		PlayerDao player = DaoFactoryImpl.getDaoFactory().getPlayerDao();
		player.insertPlayerInfo(rawPlayer.getAllPlayerInfo());
		player.insertPlayerSalary(rawPlayer.getAllPlayerSalary());
		player.insertPlayerTotal(rawPlayer.getAllPlayerTotal());
		player.insertPlayerPerGame(rawPlayer.getAllPlayerPerGame());
		player.insertPlayerAdvanced(rawPlayer.getAllPlayerAdvanced());
		System.out.println("Insert Player Done!\n");
		
		// 球队数据入库
		System.out.println("Insert Team Begin...");
		RawTeamDao rawTeam = DaoFactoryImpl.getDaoFactory().getRawTeamDao();
		TeamDao team = DaoFactoryImpl.getDaoFactory().getTeamDao();
		team.insertTeamInfo(rawTeam.getAllTeamInfo());
		team.insertTeamTotal(rawTeam.getAllTeamTotal());
		team.insertTeamPerGame(rawTeam.getAllTeamPerGame());
		team.insertTeamAdvanced(rawTeam.getAllTeamAdvanced());
		team.insertTeamOppTotal(rawTeam.getAllTeamOppTotal());
		team.insertTeamOppPerGame(rawTeam.getAllTeamOppPerGame());
		System.out.println("Insert Team Done!\n");
	}

}
