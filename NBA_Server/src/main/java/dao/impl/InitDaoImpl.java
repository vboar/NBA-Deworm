package dao.impl;

import dao.InitDao;

/**
 * 数据库初始化的具体实现
 * 
 * created by JaneLDQ on 2015年5月17日 下午10:42:00
 */
public class InitDaoImpl implements InitDao {

	private SqlManager sqlManager = SqlManager.getSqlManager();
	
	public static void main(String[] args) {
		InitDaoImpl id = new InitDaoImpl();
		id.createTable();
	}
	
	@Override
	public void createTable() {
		
		sqlManager.getConnection();
		
        // 赛季信息表
        String sql = "CREATE TABLE season (" +
                "season VARCHAR(5) PRIMARY KEY NOT NULL" +
                ")";
        sqlManager.executeUpdate(sql);
        
        // 比赛概况表
        sql = "CREATE TABLE match_info (" +
                "game_id VARCHAR(20) PRIMARY KEY NOT NULL," +
                "season VARCHAR(5) NOT NULL," +
                "is_normal VARCHAR(5) NOT NULL," +
                "date VARCHAR(10) NOT NULL," +
                "location VARCHAR(64) NOT NULL," +
                "home_team VARCHAR(3) NOT NULL," +
                "guest_team VARCHAR(3) NOT NULL," +
                "home_point INT DEFAULT 0 NOT NULL," +
                "guest_point INT DEFAULT 0 NOT NULL," +
                "time VARCHAR(6) NOT NULL" + 
                ")";
        sqlManager.executeUpdate(sql);

        // 比赛比分表
        sql = "CREATE TABLE match_score (" +
                "game_id VARCHAR(20) NOT NULL," +
                "section INT DEFAULT 0 NOT NULL," +
                "home_point INT DEFAULT 0 NOT NULL," +
                "guest_point INT DEFAULT 0 NOT NULL" +
                ")";
        sqlManager.executeUpdate(sql);

        // 比赛球员基本数据表
        sql = "CREATE TABLE match_player_basic (" +
                "game_id VARCHAR(20) NOT NULL," +
                "player_name VARCHAR(48) NOT NULL," +
                "team_abbr VARCHAR(3) NOT NULL," +
                "starter VARCHAR(16) NOT NULL," +
                "minute DOUBLE DEFAULT 0 NOT NULL," +
                "fg INT DEFAULT 0 NOT NULL," +
                "fga INT DEFAULT 0 NOT NULL," +
                "fga_pct DOUBLE DEFAULT 0 NOT NULL," +
                "fg3 INT DEFAULT 0 NOT NULL," +
                "fg3a INT DEFAULT 0 NOT NULL," +
                "fg3_pct DOUBLE DEFAULT 0 NOT NULL," +
                "ft INT DEFAULT 0 NOT NULL," +
                "fta INT DEFAULT 0 NOT NULL," +
                "ft_pct DOUBLE DEFAULT 0 NOT NULL," +
                "orb INT DEFAULT 0 NOT NULL," +
                "drb INT DEFAULT 0 NOT NULL," +
                "trb INT DEFAULT 0 NOT NULL," +
                "ast INT DEFAULT 0 NOT NULL," +
                "stl INT DEFAULT 0 NOT NULL," +
                "blk INT DEFAULT 0 NOT NULL," +
                "tov INT DEFAULT 0 NOT NULL," +
                "pf INT DEFAULT 0 NOT NULL," +
                "pts INT DEFAULT 0 NOT NULL," +
                "plus_minus DOUBLE DEFAULT 0 NOT NULL" +
                ")";
        sqlManager.executeUpdate(sql);
        
        //比赛球员高阶数据表
        sql = "CREATE TABLE match_player_advanced (" +
                "game_id VARCHAR(20) NOT NULL," +
                "player_name VARCHAR(48) NOT NULL," +
                "team_abbr VARCHAR(3) NOT NULL," +
                "starter VARCHAR(16) NOT NULL," +
                "minute DOUBLE DEFAULT 0 NOT NULL," +
                "ts_pct DOUBLE DEFAULT 0 NOT NULL," +
                "efg_pct DOUBLE DEFAULT 0 NOT NULL," +
                "fa3a_per_fga_pct DOUBLE DEFAULT 0 NOT NULL," +
                "fta_per_fga_pct DOUBLE DEFAULT 0 NOT NULL," +
                "orb_pct DOUBLE DEFAULT 0 NOT NULL," +
                "drb_pct DOUBLE DEFAULT 0 NOT NULL," +
                "trb_pct DOUBLE DEFAULT 0 NOT NULL," +
                "ast_pct DOUBLE DEFAULT 0 NOT NULL," +
                "stl_pct DOUBLE DEFAULT 0 NOT NULL," +
                "tov_pct DOUBLE DEFAULT 0 NOT NULL," +
                "blk_pct DOUBLE DEFAULT 0 NOT NULL," +
                "usg_pct DOUBLE DEFAULT 0 NOT NULL," +
                "off_rtg DOUBLE DEFAULT 0 NOT NULL," +
                "def_rtg DOUBLE DEFAULT 0 NOT NULL" +
        		")";
        sqlManager.executeUpdate(sql);
        
        //球员基本信息表
        sql = "CREATE TABLE player_info ("+
                "player_name VARCHAR(48) PRIMARY KEY NOT NULL," +
                "born VARCHAR(10) NOT NULL," +
                "hometown VARCHAR(64) NOT NULL," +
                "position VARCHAR(3) NOT NULL," +
                "height VARCHAR(5) NOT NULL," +
                "weight INT DEFAULT 0 NOT NULL," +
                "shoots VARCHAR(5) NOT NULL," +
                "high_school VARCHAR(64) NOT NULL," +
                "college VARCHAR(32) NOT NULL," +
                "draft VARCHAR(128) DEFAULT 0 NOT NULL," +
                "debut VARCHAR(32) NOT NULL," +
                "exp INT DEFAULT 0 NOT NULL," +
                "number INT DEFAULT -1 NOT NULL" +
        		")";
        
        //球员总数据表
        sql = "CREATE TABLE player_total (" +
        		"player_name VARCHAR(48) NOT NULL," +
        		"season VARCHAR(5) NOT NULL," +
        		"is_normal INT DEFUALT 0 NOT NULL," +
        		"team VARCHAR(3) NOT NULL," +
        		"position VARCHAR(3) NOT NULL," +
        		"game INT DEFAULT 0 NOT NULL," +
        		"game_start INT DEFAULT 0 NOT NULL," +
        		"minute INT DEFAULT 0 NOT NULL," +
        		"fg INT DEFAULT 0 NOT NULL," +
        		"fga INT DEFAULT 0 NOT NULL," +
        		"fga_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"fg3 INT DEFAULT 0 NOT NULL," +
        		"fg3a INT DEFAULT 0 NOT NULL," +
        		"fg3_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"fg2 INT DEFAULT 0 NOT NULL," +
        		"fg2a INT DEFAULT 0 NOT NULL," +
        		"fg2_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"efg_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"ft INT DEFAULT 0 NOT NULL," +
        		"fta INT DEFAULT 0 NOT NULL," +
        		"ft_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"orb INT DEFAULT 0 NOT NULL," +
        		"drb INT DEFAULT 0 NOT NULL," +
        		"trb INT DEFAULT 0 NOT NULL," +
        		"ast INT DEFAULT 0 NOT NULL," +
        		"stl INT DEFAULT 0 NOT NULL," +
        		"blk INT DEFAULT 0 NOT NULL," +
        		"tov INT DEFAULT 0 NOT NULL," +
        		"pf INT DEFAULT 0 NOT NULL," +
        		"pts INT DEFAULT 0 NOT NULL" +
        		")";
        sqlManager.executeUpdate(sql);
        
        //球员场均数据表
        sql = "CREATE TABLE player_per_game (" +
        		"player_name VARCHAR(48) NOT NULL," +
        		"season VARCHAR(5) NOT NULL," +
        		"is_normal INT DEFUALT 0 NOT NULL," +
        		"team VARCHAR(3) NOT NULL," +
        		"position VARCHAR(3) NOT NULL," +
        		"game INT DEFAULT 0 NOT NULL," +
        		"game_start INT DEFAULT 0 NOT NULL," +
        		"minute DOUBLE DEFAULT 0 NOT NULL," +
        		"fg DOUBLE DEFAULT 0 NOT NULL," +
        		"fga DOUBLE DEFAULT 0 NOT NULL," +
        		"fga_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"fg3 DOUBLE DEFAULT 0 NOT NULL," +
        		"fg3a DOUBLE DEFAULT 0 NOT NULL," +
        		"fg3_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"fg2 DOUBLE DEFAULT 0 NOT NULL," +
        		"fg2a DOUBLE DEFAULT 0 NOT NULL," +
        		"fg2_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"efg_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"ft DOUBLE DEFAULT 0 NOT NULL," +
        		"fta DOUBLE DEFAULT 0 NOT NULL," +
        		"ft_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"orb DOUBLE DEFAULT 0 NOT NULL," +
        		"drb DOUBLE DEFAULT 0 NOT NULL," +
        		"trb DOUBLE DEFAULT 0 NOT NULL," +
        		"ast DOUBLE DEFAULT 0 NOT NULL," +
        		"stl DOUBLE DEFAULT 0 NOT NULL," +
        		"blk DOUBLE DEFAULT 0 NOT NULL," +
        		"tov DOUBLE DEFAULT 0 NOT NULL," +
        		"pf DOUBLE DEFAULT 0 NOT NULL," +
        		"pts DOUBLE DEFAULT 0 NOT NULL" +
        		")";
        sqlManager.executeUpdate(sql);
        
        //球员高阶数据表
        sql = "CREATE TABLE player_advanced (" +
        		"player_name VARCHAR(32) NOT NULL," +
        		"season VARCHAR(5) NOT NULL," +
        		"is_normal INT DEFUALT 0 NOT NULL," +
        		"team VARCHAR(3) NOT NULL," +
        		"position VARCHAR(3) NOT NULL," +
        		"game INT DEFAULT 0 NOT NULL," +
        		"minute INT DEFAULT 0 NOT NULL," +
        		"per DOUBLE DEFAULT 0 NOT NULL," +
        		"ts_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"fa3a_per_fga_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"fta_per_fga_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"orb_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"drb_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"trb_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"ast_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"stl_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"blk_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"tov_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"usg_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"ows DOUBLE DEFAULT 0 NOT NULL," +
        		"dws DOUBLE DEFAULT 0 NOT NULL," +
        		"ws DOUBLE DEFAULT 0 NOT NULL," +
        		"ws_48 DOUBLE DEFAULT 0 NOT NULL," +
        		"obpm DOUBLE DEFAULT 0 NOT NULL," +
        		"dbpm DOUBLE DEFAULT 0 NOT NULL," +
        		"bpm DOUBLE DEFAULT 0 NOT NULL," +
        		"vorp DOUBLE DEFAULT 0 NOT NULL" +
        		")";
        sqlManager.executeUpdate(sql);
        
        //球员薪水表
        sql = "CREATE TABLE player_salary (" +
        		"name VARCHAR(32) NOT NULL," +
        		"season VARCHAR(5) NOT NULL," +
        		"team VARCHAR(48) NOT NULL," +
        		"salary VARCHAR(16) NOT NULL" +
        		")";
        sqlManager.executeUpdate(sql);
        
        //球队基本信息表
        sql = "CREATE TABLE team_info (" +
        		"name VARCHAR(48) PRIMERY KEY NOT NULL," +
        		"abbr VARCHAR(3) NOT NULL," +
        		"location VARCHAR(64) NOT NULL," +
        		"season VARCHAR(5) NOT NULL," +
        		"record VARCHAR(24) NOT NULL," +
        		")";
        sqlManager.executeUpdate(sql);
        
        //球队总数据表
        sql = "CREATE TABLE team_total (" +
        		"abbr VARCHAR(3) NOT NULL," +
        		"season VARCHAR(5) NOT NULL," +
        		"wins INT DEFAULT 0 NOT NULL," +
        		"losses INT DEFAULT 0 NOT NULL," +
        		"finish INT DEFAULT 0 NOT NULL," +
        		"age DOUBLE DEFAULT 0 NOT NULL," +
        		"height VARCHAR(3) 0 NOT NULL," +
        		"weight DOUBLE DEFAULT 0 NOT NULL," +
        		"num_of_game INT DEFAULT 0 NOT NULL," +
        		"minute INT DEFAULT 0 NOT NULL," +
        		"fg INT DEFAULT 0 NOT NULL," +
        		"fga INT DEFAULT 0 NOT NULL," +
        		"fga_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"fg3 INT DEFAULT 0 NOT NULL," +
        		"fg3a INT DEFAULT 0 NOT NULL," +
        		"fg3_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"fg2 INT DEFAULT 0 NOT NULL," +
        		"fg2a INT DEFAULT 0 NOT NULL," +
        		"fg2_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"efg_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"ft INT DEFAULT 0 NOT NULL," +
        		"fta INT DEFAULT 0 NOT NULL," +
        		"ft_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"orb INT DEFAULT 0 NOT NULL," +
        		"drb INT DEFAULT 0 NOT NULL," +
        		"trb INT DEFAULT 0 NOT NULL," +
        		"ast INT DEFAULT 0 NOT NULL," +
        		"stl INT DEFAULT 0 NOT NULL," +
        		"blk INT DEFAULT 0 NOT NULL," +
        		"tov INT DEFAULT 0 NOT NULL," +
        		"pf INT DEFAULT 0 NOT NULL," +
        		"pts INT DEFAULT 0 NOT NULL" +
        		")";
        sqlManager.executeUpdate(sql);
        
        //球队场均数据表
        sql = "CREATE TABLE team_per_game (" +
        		"abbr VARCHAR(3) NOT NULL," +
        		"season VARCHAR(5) NOT NULL," +
        		"num_of_game INT DEFAULT 0 NOT NULL," +
        		"minute DOUBLE DEFAULT 0 NOT NULL," +
        		"fg DOUBLE DEFAULT 0 NOT NULL," +
        		"fga DOUBLE DEFAULT 0 NOT NULL," +
        		"fga_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"fg3 DOUBLE DEFAULT 0 NOT NULL," +
        		"fg3a DOUBLE DEFAULT 0 NOT NULL," +
        		"fg3_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"fg2 DOUBLE DEFAULT 0 NOT NULL," +
        		"fg2a DOUBLE DEFAULT 0 NOT NULL," +
        		"fg2_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"ft DOUBLE DEFAULT 0 NOT NULL," +
        		"fta DOUBLE DEFAULT 0 NOT NULL," +
        		"ft_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"orb DOUBLE DEFAULT 0 NOT NULL," +
        		"drb DOUBLE DEFAULT 0 NOT NULL," +
        		"trb DOUBLE DEFAULT 0 NOT NULL," +
        		"ast DOUBLE DEFAULT 0 NOT NULL," +
        		"stl DOUBLE DEFAULT 0 NOT NULL," +
        		"blk DOUBLE DEFAULT 0 NOT NULL," +
        		"tov DOUBLE DEFAULT 0 NOT NULL," +
        		"pf DOUBLE DEFAULT 0 NOT NULL," +
        		"pts DOUBLE DEFAULT 0 NOT NULL" +
        		")";
        sqlManager.executeUpdate(sql);
        
        //球队高阶数据表
        sql = "CREATE TABLE team_advanced (" +
        		"abbr VARCHAR(3) NOT NULL," +
        		"season VARCHAR(5) NOT NULL," +
        		"pw DOUBLE DEFAULT 0 NOT NULL," +
        		"pl DOUBLE DEFAULT 0 NOT NULL," +
        		"mov DOUBLE DEFAULT 0 NOT NULL," +
        		"sos DOUBLE DEFAULT 0 NOT NULL," +
        		"srs DOUBLE DEFAULT 0 NOT NULL," +
        		"off_rtg DOUBLE DEFAULT 0 NOT NULL," +
        		"def_rtg DOUBLE DEFAULT 0 NOT NULL," +
        		"pace DOUBLE DEFAULT 0 NOT NULL," +
        		"fta_per_fga_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"fg3a_per_fga_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"off_efg_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"off_tov_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"orb_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"off_ft_rate DOUBLE DEFAULT 0 NOT NULL," +
        		"opp_efg_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"opp_tov_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"drb_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"opp_ft_rate DOUBLE DEFAULT 0 NOT NULL," +
        		"arena VARCHAR(16) 0 NOT NULL," +
        		"attendance INT DEFAULT 0 NOT NULL" +
        		")";
        sqlManager.executeUpdate(sql);
        
        //对手总数据表
        sql = "CREATE TABLE opp_total (" +
        		"abbr VARCHAR(3) NOT NULL," +
        		"season VARCHAR(5) NOT NULL," +
        		"fg INT DEFAULT 0 NOT NULL," +
        		"fga INT DEFAULT 0 NOT NULL," +
        		"fga_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"fg3 INT DEFAULT 0 NOT NULL," +
        		"fg3a INT DEFAULT 0 NOT NULL," +
        		"fg3_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"fg2 INT DEFAULT 0 NOT NULL," +
        		"fg2a INT DEFAULT 0 NOT NULL," +
        		"fg2_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"efg_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"ft INT DEFAULT 0 NOT NULL," +
        		"fta INT DEFAULT 0 NOT NULL," +
        		"ft_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"orb INT DEFAULT 0 NOT NULL," +
        		"drb INT DEFAULT 0 NOT NULL," +
        		"trb INT DEFAULT 0 NOT NULL," +
        		"ast INT DEFAULT 0 NOT NULL," +
        		"stl INT DEFAULT 0 NOT NULL," +
        		"blk INT DEFAULT 0 NOT NULL," +
        		"tov INT DEFAULT 0 NOT NULL," +
        		"pf INT DEFAULT 0 NOT NULL," +
        		"pts INT DEFAULT 0 NOT NULL" +
        		")";
        sqlManager.executeUpdate(sql);
        
        //对手场均数据表
        sql = "CREATE TABLE opp_per_game (" +
        		"abbr VARCHAR(3) NOT NULL," +
        		"season VARCHAR(5) NOT NULL," +
        		"fg DOUBLE DEFAULT 0 NOT NULL," +
        		"fga DOUBLE DEFAULT 0 NOT NULL," +
        		"fga_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"fg3 DOUBLE DEFAULT 0 NOT NULL," +
        		"fg3a DOUBLE DEFAULT 0 NOT NULL," +
        		"fg3_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"fg2 DOUBLE DEFAULT 0 NOT NULL," +
        		"fg2a DOUBLE DEFAULT 0 NOT NULL," +
        		"fg2_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"ft DOUBLE DEFAULT 0 NOT NULL," +
        		"fta DOUBLE DEFAULT 0 NOT NULL," +
        		"ft_pct DOUBLE DEFAULT 0 NOT NULL," +
        		"orb DOUBLE DEFAULT 0 NOT NULL," +
        		"drb DOUBLE DEFAULT 0 NOT NULL," +
        		"trb DOUBLE DEFAULT 0 NOT NULL," +
        		"ast DOUBLE DEFAULT 0 NOT NULL," +
        		"stl DOUBLE DEFAULT 0 NOT NULL," +
        		"blk DOUBLE DEFAULT 0 NOT NULL," +
        		"tov DOUBLE DEFAULT 0 NOT NULL," +
        		"pf DOUBLE DEFAULT 0 NOT NULL," +
        		"pts DOUBLE DEFAULT 0 NOT NULL" +
        		")";
        sqlManager.executeUpdate(sql);
        
        sqlManager.releaseConnection();
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
        sqlManager.executeUpdate("DROP TABLE opp_total");
        sqlManager.executeUpdate("DROP TABLE opp_per_game");
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
        sqlManager.executeUpdate("DELETE FROM opp_total");
        sqlManager.executeUpdate("DELETE FROM opp_per_game");
        sqlManager.releaseConnection();
	}

}
