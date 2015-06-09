package dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import util.FieldType;
import util.Utility;
import vo.PlayerFilter;
import dao.PlayerDao;
import entity.HotPlayerInfo;
import entity.PlayerInfo;
import entity.PlayerSalary;
import entity.PlayerStatsAdvanced;
import entity.PlayerStatsPerGame;
import entity.PlayerStatsTotal;

/**
 * PlayerDao的具体实现
 * 
 * created by JaneLDQ on 2015年5月25日 下午9:16:38
 */
public class PlayerDaoImpl implements PlayerDao {

	private SqlManager sqlManager = SqlManager.getSqlManager();

	public PlayerDaoImpl() {
	}

	@Override
	public List<ImageIcon> getPlayerPortraitByNameList(List<String> names) {
		List<ImageIcon> list = new ArrayList<ImageIcon>();
		String now_pic = FileManager.DATA_PATH + "/players/now_pic/";
		String pic = FileManager.DATA_PATH + "/players/pic/";
		for (String p : names) {
			ImageIcon lg = new ImageIcon(now_pic + p + ".png");
			if (lg.getImage() == null) {
				lg = new ImageIcon(pic + p + ".png");
			}
			lg.setDescription(p.split(",")[0]);
			list.add(lg);
		}
		return list;
	}

	@Override
	public ImageIcon getPlayerPortraitByName(String name) {
		String now_pic = FileManager.DATA_PATH + "/players/now_pic/" + name
				+ ".png";
		String pic = FileManager.DATA_PATH + "/players/pic/" + name + ".png";
		ImageIcon icon = new ImageIcon(now_pic);
		if (icon.getImage() == null)
			icon = new ImageIcon(pic);
		icon.setDescription(name);
		return icon;
	}

	@Override
	public List<String> getNameList(String str) {
		List<String> names = new ArrayList<String>();
		sqlManager.getConnection();
		String sql = "SELECT player_name FROM player_info "
				+ "WHERE player_name LIKE '%" + str + "%' ORDER BY player_name";
		List<Map<String, Object>> maplist = sqlManager.queryMulti(sql, null);
		for (Map<String, Object> map : maplist) {
			String name = map.get("player_name").toString();
			if (name != null)
				names.add(name);
		}
		sqlManager.releaseAll();
		return names;
	}

	@Override
	public List<String> getNameByNameInitial(String initial) {
		sqlManager.getConnection();

		List<String> list = new ArrayList<String>();
		String sql = "SELECT player_name FROM player_info WHERE player_name LIKE '"
				+ initial + "%' ORDER BY player_name";
		List<Map<String, Object>> maplist = sqlManager.queryMulti(sql, null);
		for (Map<String, Object> map : maplist) {
			list.add(map.get("player_name").toString());
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerInfo> getAllPlayerInfo() {
		sqlManager.getConnection();

		List<PlayerInfo> list = new ArrayList<PlayerInfo>();
		String sql = "SELECT * FROM player_info ORDER BY player_name";
		List<Map<String, Object>> maplist = sqlManager.queryMulti(sql, null);
		for (Map<String, Object> map : maplist) {
			list.add(getPlayerInfo(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public PlayerInfo getPlayerInfoByName(String name) {
		sqlManager.getConnection();

		String sql = "SELECT * FROM player_info WHERE player_name=? ";
		Map<String, Object> map = sqlManager.querySimple(sql,
				new Object[] { name });

		sqlManager.releaseAll();
		return getPlayerInfo(map);
	}

	@Override
	public List<PlayerStatsTotal> getPlayerTotalByName(String name, int regular) {
		sqlManager.getConnection();

		List<PlayerStatsTotal> list = new ArrayList<PlayerStatsTotal>();
		String sql = "SELECT * FROM player_total WHERE player_name=? ";
		if(regular == 0 || regular == 1)
			sql += " AND is_normal=" + regular;
		sql += " ORDER BY season DESC";
		List<Map<String, Object>> maplist = sqlManager.queryMulti(sql,
				new Object[] { name });
		for (Map<String, Object> map : maplist) {
			list.add(getPlayerTotal(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsTotal> getPlayerTotalBySeason(String season, int regular) {
		sqlManager.getConnection();

		List<PlayerStatsTotal> list = new ArrayList<PlayerStatsTotal>();
		String sql = "SELECT * FROM player_total WHERE season=? ";
		if(regular == 0 || regular == 1)
			sql += " AND is_normal=" + regular;
		sql += "ORDER BY player_name";
		List<Map<String, Object>> maplist = sqlManager.queryMulti(sql,
				new Object[] { season });
		for (Map<String, Object> map : maplist) {
			list.add(getPlayerTotal(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsTotal> getPlayerTotalBySeasonName(String season,
			String name, int regular) {
		sqlManager.getConnection();
		List<PlayerStatsTotal> list = new ArrayList<PlayerStatsTotal>();
		String sql = "SELECT * FROM player_total WHERE player_name=?";
		List<Object> objects = new ArrayList<Object>();
		objects.add(name);
		if(season != null){
			sql += " AND season=? ";
			objects.add(season);
		}
		if (regular == 0 || regular == 1) {
			sql += " AND is_normal=" + regular;
		}
		List<Map<String, Object>> maplist = sqlManager.queryMultiByList(sql, objects);
		for (Map<String, Object> map : maplist) {
			list.add(getPlayerTotal(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsTotal> getPlayerTotalByFilter(PlayerFilter filter) {
		List<PlayerStatsTotal> list = new ArrayList<PlayerStatsTotal>();
		sqlManager.getConnection();
		String sql = "SELECT DISTINCT pt.player_name, "
				+ "season, "
				+ "is_normal, "
				+ "team_abbr, "
				+ "pt.position, "
				+ "num_of_game, "
				+ "game_start, "
				+ "minute, "
				+ "fg, "
				+ "fga, "
				+ "fga_pct, "
				+ "fg3, "
				+ "fg3a, "
				+ "fg3_pct, "
				+ "fg2, "
				+ "fg2a, "
				+ "fg2_pct, "
				+ "efg_pct, "
				+ "ft, "
				+ "fta, "
				+ "ft_pct, "
				+ "orb, "
				+ "drb, "
				+ "trb, "
				+ "ast, "
				+ "stl, "
				+ "blk, "
				+ "tov, "
				+ "pf, "
				+ "pts "
				+ "FROM player_total as pt, player_info as pi, team_info as ti "
				+ "WHERE pt.player_name = pi.player_name "
				+ "AND pt.team_abbr = ti.abbr ";
		List<Object> objects = new ArrayList<Object>();
		if (filter.season != null) {
			sql += " AND pt.season=? ";
			objects.add(filter.season);
		}
		if (filter.regular != null) {
			sql += " AND is_normal=? ";
			objects.add(filter.regular);
		}
		if (filter.position != null && !filter.position.equalsIgnoreCase("All")) {
			sql += " AND pt.position like '%" + filter.position + "%' ";
		}
		if (filter.league != null) {
			if (filter.league.equals("W")) {
				sql += " AND ti.league='W' ";
			} 
			else if(filter.league.equals("E")){
				sql += " AND ti.league='E' ";
			}
		}
		if (filter.division != null) {
			sql += " AND ti.division=? ";
			objects.add(filter.division);
		}
		if (filter.height != null) {
			sql += " AND pi.height " + filter.height.substring(0, 1) + "'"
					+ filter.height.substring(1) + "'";
		}
		if (filter.weight != null) {
			sql += " AND pi.weight " + filter.weight;
		}
		sql += " ORDER BY pt.player_name";
		List<Map<String, Object>> mapList = sqlManager.queryMultiByList(sql,
				objects);
		for (Map<String, Object> map : mapList) {
			list.add(getPlayerTotal(map));
		}

		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsPerGame> getPlayerPerGameByName(String name, int regular) {
		sqlManager.getConnection();

		List<PlayerStatsPerGame> list = new ArrayList<PlayerStatsPerGame>();
		String sql = "SELECT * FROM player_per_game WHERE player_name=? ";
		if(regular == 0 || regular == 1)
			sql += " AND is_normal=" + regular;
		sql += "ORDER BY season DESC";
		List<Map<String, Object>> maplist = sqlManager.queryMulti(sql,
				new Object[] { name });
		for (Map<String, Object> map : maplist) {
			list.add(getPlayerPerGame(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsPerGame> getPlayerPerGameBySeason(String season, int regular) {
		sqlManager.getConnection();

		List<PlayerStatsPerGame> list = new ArrayList<PlayerStatsPerGame>();
		String sql = "SELECT * FROM player_per_game WHERE season=? ";
		if(regular == 0 || regular == 1)
			sql += " AND is_normal=" + regular;
		sql += "ORDER BY player_name";
		List<Map<String, Object>> maplist = sqlManager.queryMulti(sql,
				new Object[] { season });
		for (Map<String, Object> map : maplist) {
			list.add(getPlayerPerGame(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsPerGame> getPlayerPerGameBySeasonName(String season,
			String name, int regular) {
		sqlManager.getConnection();
		List<PlayerStatsPerGame> list = new ArrayList<PlayerStatsPerGame>();
		String sql = "SELECT * FROM player_per_game WHERE player_name=? ";
		List<Object> objects = new ArrayList<Object>();
		objects.add(name);
		if(season != null){
			sql += " AND season=? ";
			objects.add(season);
		}
		if (regular == 0 || regular == 1) {
			sql += " AND is_normal=" + regular;
		}
		List<Map<String, Object>> maplist = sqlManager.queryMultiByList(sql, objects);

		for (Map<String, Object> map : maplist) {
			list.add(getPlayerPerGame(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsPerGame> getPlayerPerGameByFilter(PlayerFilter filter) {
		List<PlayerStatsPerGame> list = new ArrayList<PlayerStatsPerGame>();
		sqlManager.getConnection();
		String sql = "SELECT DISTINCT pg.player_name, "
				+ "season, "
				+ "is_normal, "
				+ "team_abbr, "
				+ "pg.position, "
				+ "num_of_game, "
				+ "game_start, "
				+ "minute, "
				+ "fg, "
				+ "fga, "
				+ "fga_pct, "
				+ "fg3, "
				+ "fg3a, "
				+ "fg3_pct, "
				+ "fg2, "
				+ "fg2a, "
				+ "fg2_pct, "
				+ "efg_pct, "
				+ "ft, "
				+ "fta, "
				+ "ft_pct, "
				+ "orb, "
				+ "drb, "
				+ "trb, "
				+ "ast, "
				+ "stl, "
				+ "blk, "
				+ "tov, "
				+ "pf, "
				+ "pts "
				+ "FROM player_per_game as pg, player_info as pi, team_info as ti "
				+ "WHERE pg.player_name = pi.player_name "
				+ "AND pg.team_abbr = ti.abbr ";
		List<Object> objects = new ArrayList<Object>();
		if (filter.season != null) {
			sql += " AND pg.season=? ";
			objects.add(filter.season);
		}
		if (filter.regular != null) {
			sql += " AND is_normal=? ";
			objects.add(filter.regular);
		}
		if (filter.position != null && !filter.position.equalsIgnoreCase("All")) {
			sql += " AND pg.position like '%" + filter.position + "%' ";
		}
		if (filter.league != null) {
			if (filter.league.equals("W")) {
				sql += " AND ti.league='W' ";
			}
			else if(filter.league.equals("E")){
				sql += " AND ti.league='E' ";
			}
		}
		if (filter.division != null) {
			sql += " AND ti.division=? ";
			objects.add(filter.division);
		}
		if (filter.height != null) {
			sql += " AND pi.height " + filter.height.substring(0, 1) + "'"
					+ filter.height.substring(1) + "'";
		}
		if (filter.weight != null) {
			sql += " AND pi.weight " + filter.weight;
		}
		if(filter.team != null){
			sql += " AND pg.team_abbr=? ";
			objects.add(filter.team);
		}
		sql += " ORDER BY pg.player_name";
		List<Map<String, Object>> mapList = sqlManager.queryMultiByList(sql,
				objects);
		for (Map<String, Object> map : mapList) {
			list.add(getPlayerPerGame(map));
		}

		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsAdvanced> getPlayerAdvancedByName(String name, int regular) {
		sqlManager.getConnection();

		List<PlayerStatsAdvanced> list = new ArrayList<PlayerStatsAdvanced>();
		String sql = "SELECT * FROM player_advanced WHERE player_name=? ";
		if(regular == 0 || regular == 1)
			sql += " AND is_normal=" + regular;
		sql += "ORDER BY season DESC";
		List<Map<String, Object>> maplist = sqlManager.queryMulti(sql,
				new Object[] { name });
		for (Map<String, Object> map : maplist) {
			list.add(getPlayerAdvanced(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsAdvanced> getPlayerAdvancedBySeason(String season, int regular) {
		sqlManager.getConnection();

		List<PlayerStatsAdvanced> list = new ArrayList<PlayerStatsAdvanced>();
		String sql = "SELECT * FROM player_advanced WHERE season=? ";
		if(regular == 0 || regular == 1)
			sql += " AND is_normal=" + regular;
		sql += "ORDER BY player_name";
		List<Map<String, Object>> maplist = sqlManager.queryMulti(sql,
				new Object[] { season });
		for (Map<String, Object> map : maplist) {
			list.add(getPlayerAdvanced(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsAdvanced> getPlayerAdvancedBySeasonName(
			String season, String name, int regular) {
		sqlManager.getConnection();
		List<PlayerStatsAdvanced> list = new ArrayList<PlayerStatsAdvanced>();
		String sql = "SELECT * FROM player_advanced WHERE player_name=?";
		List<Object> objects = new ArrayList<Object>();
		objects.add(name);
		if(season != null){
			sql += " AND season=? ";
			objects.add(season);
		}
		if (regular == 0 || regular == 1) {
			sql += " AND is_normal=" + regular;
		}
		List<Map<String, Object>> maplist = sqlManager.queryMultiByList(sql, objects);

		for (Map<String, Object> map : maplist) {
			list.add(getPlayerAdvanced(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerStatsAdvanced> getPlayerAdvancedByFilter(
			PlayerFilter filter) {
		List<PlayerStatsAdvanced> list = new ArrayList<PlayerStatsAdvanced>();
		sqlManager.getConnection();
		String sql = "SELECT DISTINCT pa.player_name,"
				+ "season,"
				+ "is_normal, "
				+ "team_abbr, "
				+ "pa.position, "
				+ "num_of_game, "
				+ "minute, "
				+ "per, "
				+ "ts_pct,"
				+ "fa3a_per_fga_pct, "
				+ "fta_per_fga_pct, "
				+ "orb_pct, "
				+ "drb_pct, "
				+ "trb_pct, "
				+ "ast_pct, "
				+ "stl_pct, "
				+ "blk_pct, "
				+ "tov_pct, "
				+ "usg_pct, "
				+ "ows, "
				+ "dws, "
				+ "ws, "
				+ "ws_48, "
				+ "obpm, "
				+ "dbpm, "
				+ "bpm, "
				+ "vorp "
				+ " FROM player_advanced as pa, player_info as pi, team_info as ti "
				+ "WHERE pa.player_name = pi.player_name "
				+ "AND pa.team_abbr = ti.abbr ";
		List<Object> objects = new ArrayList<Object>();
		if (filter.season != null) {
			sql += " AND pa.season=? ";
			objects.add(filter.season);
		}
		if (filter.regular != null) {
			sql += " AND is_normal=? ";
			objects.add(filter.regular);
		}
		if (filter.position != null && !filter.position.equalsIgnoreCase("All")) {
			sql += " AND pa.position like '%" + filter.position + "%' ";
		}
		if (filter.league != null) {
			if (filter.league.equals("W")) {
				sql += " AND ti.league='W' ";
			}
			else if(filter.league.equals("E")){
				sql += " AND ti.league='E' ";
			}
		}
		if (filter.division != null) {
			sql += " AND ti.division=? ";
			objects.add(filter.division);
		}
		if (filter.height != null) {
			sql += " AND pi.height " + filter.height.substring(0, 1) + "'"
					+ filter.height.substring(1) + "'";
		}
		if (filter.weight != null) {
			sql += " AND pi.weight " + filter.weight;
		}
		if(filter.team != null){
			sql += " AND pa.team_abbr=? ";
			objects.add(filter.team);
		}
		sql += " ORDER BY pa.player_name";
		List<Map<String, Object>> mapList = sqlManager.queryMultiByList(sql,
				objects);
		for (Map<String, Object> map : mapList) {
			list.add(getPlayerAdvanced(map));
		}

		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerSalary> getPlayerSalaryByName(String name) {
		sqlManager.getConnection();

		List<PlayerSalary> list = new ArrayList<PlayerSalary>();
		String sql = "SELECT * FROM player_salary WHERE name=? ORDER BY season DESC";
		List<Map<String, Object>> maplist = sqlManager.queryMulti(sql,
				new Object[] { name });
		for (Map<String, Object> map : maplist) {
			list.add(getPlayerSalary(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerSalary> getPlayerSalaryBySeason(String season, String name) {
		List<PlayerSalary> list = new ArrayList<PlayerSalary>();
		sqlManager.getConnection();
		String sql = "SELECT * FROM player_salary WHERE name=?";
		List<Object> objects = new ArrayList<Object>();
		objects.add(name);
		if(season != null){
			sql += " AND season=? ";
			objects.add(season);
		}
		List<Map<String,Object>> maplist = sqlManager.queryMultiByList(sql, objects);
		for(Map<String,Object> map: maplist){
			list.add(getPlayerSalary(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerSalary> getPlayerSalaryByTeam(String season, String team) {
		List<PlayerSalary> list = new ArrayList<PlayerSalary>();
		sqlManager.getConnection();
		String sql = "SELECT * FROM player_salary WHERE team=? ";
		List<Object> objects = new ArrayList<Object>();
		objects.add(team);
		if(season != null){
			sql += " AND season=?";
			objects.add(season);
		}
		List<Map<String,Object>> maplist = sqlManager.queryMultiByList(sql, objects);
		for(Map<String,Object> map: maplist){
			list.add(getPlayerSalary(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<PlayerInfo> getTeamPlayerBySeason(String season, String abbr) {
		List<PlayerInfo> list = new ArrayList<PlayerInfo>();
		sqlManager.getConnection();

		String sql = "SELECT DISTINCT a.player_name, "
				+ "born, "
				+ "hometown, "
				+ "a.position, "
				+ "height, "
				+ "weight, "
				+ "shoots, "
				+ "high_school, "
				+ "college, "
				+ "draft, "
				+ "debut, "
				+ "exp, "
				+ "number "
				+ "FROM player_info as a, match_player_basic as b, match_info as c "
				+ "WHERE a.player_name=b.player_name "
				+ "AND b.game_id = c.game_id " + "AND b.team_abbr=? ";
		List<Object> objects = new ArrayList<Object>();
		objects.add(abbr);
		if (season != null) {
			sql += "AND c.season=? ";
			objects.add(season);
		}
		sql += "ORDER BY a.player_name";
		List<Map<String, Object>> maplist = sqlManager.queryMultiByList(sql,
				objects);
		for (Map<String, Object> map : maplist) {
			list.add(getPlayerInfo(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<HotPlayerInfo> getHotPlayerBySeason(String season, FieldType field) {
		List<HotPlayerInfo> list = new ArrayList<HotPlayerInfo>();
		if (!isFieldValid(field))
			return list;
		sqlManager.getConnection();
		String sql = "SELECT player_name, team_abbr, season, position,"
				+ field.toString()
				+ " FROM player_per_game WHERE season=? AND is_normal=1 "
				+ " ORDER BY " + field.toString() + " DESC LIMIT 0,5";
		List<Object> objects = new ArrayList<Object>();
		objects.add(season);
		List<Map<String, Object>> maplist = sqlManager.queryMultiByList(sql,
				objects);
		for (Map<String, Object> map : maplist) {
			list.add(getHotPlayerInfo(map, field));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<String> getTeamByPlayerNameSeason(String name, String season) {
		sqlManager.getConnection();
		String sql = "SELECT DISTINCT a.team_abbr, "
				+ "min(b.date) as begin_date, "
				+ "max(b.date) as end_date "
				+ "FROM match_player_basic as a, match_info as b "
				+ " WHERE player_name = ? "
				+ " AND a.game_id = b.game_id"
				+ " AND b.season = ? "
				+ " GROUP BY a.team_abbr ";
		List<Map<String, Object>> maplist = sqlManager.queryMulti(sql, new Object[] {
				name, season });
		List<String> teams = new ArrayList<String>();
		for(Map<String, Object> map: maplist){
			if (map.get("team_abbr") == null) {
				continue;
			}
			teams.add(map.get("team_abbr").toString()+";"+map.get("begin_date").toString() 
					+ ";" + map.get("end_date").toString());
		}
		sqlManager.releaseAll();
		return teams;
	}
	@Override
	public void insertPlayerInfo(List<PlayerInfo> list) {
		System.out.println("Insert Player Info : " + list.size());

		if (list.size() == 0)
			return;

		sqlManager.getConnection();

		for (PlayerInfo info : list) {
			List<Object> infoObjects = new ArrayList<Object>();
			String sqlInfo = "INSERT INTO player_info (" + "player_name, "
					+ "born, " + "hometown, " + "position, " + "height, "
					+ "weight, " + "shoots, " + "high_school, " + "college, "
					+ "draft, " + "debut, " + "exp, " + "number " + ") VALUES";
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
			sqlInfo = sqlManager.fillSQL(sqlInfo);
			sqlManager.executeUpdateByList(sqlInfo, infoObjects);
		}

		sqlManager.releaseConnection();
	}

	@Override
	public void insertPlayerSalary(List<PlayerSalary> list) {
		System.out.println("Insert Player Salary : " + list.size());
		if (list.size() == 0)
			return;

		sqlManager.getConnection();
		for (PlayerSalary slr : list) {
			List<Object> salaryObjects = new ArrayList<Object>();
			String sqlSalary = "INSERT INTO player_salary (" + "name, "
					+ "season, " + "team, " + "salary " + ") VALUES ";
			salaryObjects.add(slr.getName());
			salaryObjects.add(slr.getSeason());
			salaryObjects.add(slr.getTeam());
			salaryObjects.add(slr.getSalary());

			sqlSalary = sqlManager.appendSQL(sqlSalary, 4);
			sqlSalary = sqlManager.fillSQL(sqlSalary);
			sqlManager.executeUpdateByList(sqlSalary, salaryObjects);

		}
		sqlManager.releaseConnection();
	}

	@Override
	public void insertPlayerTotal(List<PlayerStatsTotal> list) {
		System.out.println("Insert Player Total : " + list.size());

		if (list.size() == 0)
			return;

		sqlManager.getConnection();

		for (PlayerStatsTotal pt : list) {
			List<Object> ptObjects = new ArrayList<Object>();
			String sqlTotal = "INSERT INTO player_total (" + "player_name, "
					+ "season, " + "is_normal, " + "team_abbr, " + "position, "
					+ "num_of_game, " + "game_start, " + "minute, " + "fg, "
					+ "fga, " + "fga_pct, " + "fg3, " + "fg3a, " + "fg3_pct, "
					+ "fg2, " + "fg2a, " + "fg2_pct, " + "efg_pct, " + "ft, "
					+ "fta, " + "ft_pct, " + "orb, " + "drb, " + "trb, "
					+ "ast, " + "stl, " + "blk, " + "tov, " + "pf, " + "pts "
					+ ") VALUES ";
			ptObjects.add(pt.getName());
			ptObjects.add(pt.getSeason());
			ptObjects.add(pt.getIs_normal());
			ptObjects.add(pt.getTeam());
			ptObjects.add(pt.getPosition());
			ptObjects.add(pt.getNum_Of_Game());
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
			sqlTotal = sqlManager.fillSQL(sqlTotal);
			sqlManager.executeUpdateByList(sqlTotal, ptObjects);
		}
		sqlManager.releaseConnection();
	}

	@Override
	public void insertPlayerPerGame(List<PlayerStatsPerGame> list) {
		System.out.println("Insert Player_PerGame : " + list.size());

		if (list.size() == 0)
			return;

		sqlManager.getConnection();

		for (PlayerStatsPerGame ppg : list) {
			List<Object> ppgObjects = new ArrayList<Object>();
			String sqlPerGame = "INSERT INTO player_per_game ("
					+ "player_name, " + "season, " + "is_normal, "
					+ "team_abbr, " + "position, " + "num_of_game, "
					+ "game_start, " + "minute, " + "fg, " + "fga, "
					+ "fga_pct, " + "fg3, " + "fg3a, " + "fg3_pct, " + "fg2, "
					+ "fg2a, " + "fg2_pct, " + "efg_pct, " + "ft, " + "fta, "
					+ "ft_pct, " + "orb, " + "drb, " + "trb, " + "ast, "
					+ "stl, " + "blk, " + "tov, " + "pf, " + "pts"
					+ ") VALUES ";
			ppgObjects.add(ppg.getName());
			ppgObjects.add(ppg.getSeason());
			ppgObjects.add(ppg.getIs_normal());
			ppgObjects.add(ppg.getTeam());
			ppgObjects.add(ppg.getPosition());
			ppgObjects.add(ppg.getNum_Of_Game());
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
			sqlPerGame = sqlManager.fillSQL(sqlPerGame);
			sqlManager.executeUpdateByList(sqlPerGame, ppgObjects);
		}
		sqlManager.releaseConnection();
	}

	@Override
	public void insertPlayerAdvanced(List<PlayerStatsAdvanced> list) {
		System.out.println("Insert Player Advanced : " + list.size());

		if (list.size() == 0)
			return;

		sqlManager.getConnection();

		for (PlayerStatsAdvanced padv : list) {
			List<Object> padvObjects = new ArrayList<Object>();
			String sqlAdvanced = "INSERT INTO player_advanced ("
					+ "player_name, " + "season, " + "is_normal, "
					+ "team_abbr, " + "position, " + "num_of_game, "
					+ "minute, " + "per, " + "ts_pct, " + "fa3a_per_fga_pct, "
					+ "fta_per_fga_pct, " + "orb_pct, " + "drb_pct, "
					+ "trb_pct, " + "ast_pct, " + "stl_pct, " + "blk_pct, "
					+ "tov_pct, " + "usg_pct, " + "ows, " + "dws, " + "ws, "
					+ "ws_48, " + "obpm, " + "dbpm, " + "bpm, " + "vorp"
					+ ") VALUES ";
			padvObjects.add(padv.getName());
			padvObjects.add(padv.getSeason());
			padvObjects.add(padv.getIs_normal());
			padvObjects.add(padv.getTeam());
			padvObjects.add(padv.getPosition());
			padvObjects.add(padv.getNum_Of_Game());
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
			sqlAdvanced = sqlManager.fillSQL(sqlAdvanced);
			sqlManager.executeUpdateByList(sqlAdvanced, padvObjects);
		}
		sqlManager.releaseConnection();
	}

	private PlayerInfo getPlayerInfo(Map<String, Object> map) {
		PlayerInfo info = new PlayerInfo();
		if (map.get("player_name") == null) {
			return null;
		}
		info.setName(map.get("player_name").toString());
		info.setBorn(map.get("born").toString());
		info.setHometown(map.get("hometown").toString());
		info.setPosition(map.get("position").toString());
		info.setHeight(map.get("height").toString());
		info.setWeight(Utility.objectToInt(map.get("weight")));
		info.setShoots(map.get("shoots").toString());
		info.setHigh_school(map.get("high_school").toString());
		info.setCollege(map.get("college").toString());
		info.setDraft(map.get("draft").toString());
		info.setDebut(map.get("debut").toString());
		info.setExperience(Utility.objectToInt(map.get("exp")));
		info.setNumber(Utility.objectToInt(map.get("number")));
		return info;
	}

	private PlayerStatsTotal getPlayerTotal(Map<String, Object> map) {
		PlayerStatsTotal pst = new PlayerStatsTotal();
		if (map.get("player_name") == null) {
			return null;
		}
		pst.setName(map.get("player_name").toString());
		pst.setSeason(map.get("season").toString());
		pst.setIs_normal(Utility.objectToInt(map.get("is_normal")));
		pst.setTeam(map.get("team_abbr").toString());
		pst.setPosition(map.get("position").toString());
		pst.setNum_Of_Game(Utility.objectToInt(map.get("num_of_game")));
		pst.setGame_started(Utility.objectToInt(map.get("game_start")));
		pst.setMinute(Utility.objectToInt(map.get("minute")));
		pst.setFg(Utility.objectToInt(map.get("fg")));
		pst.setFga(Utility.objectToInt(map.get("fga")));
		pst.setFga_pct(Utility.objectToDouble(map.get("fga_pct")));
		pst.setFg3(Utility.objectToInt(map.get("fg3")));
		pst.setFg3a(Utility.objectToInt(map.get("fg3a")));
		pst.setFg3_pct(Utility.objectToDouble(map.get("fg3_pct")));
		pst.setFg2(Utility.objectToInt(map.get("fg2")));
		pst.setFg2a(Utility.objectToInt(map.get("fg2a")));
		pst.setFg2_pct(Utility.objectToDouble(map.get("fg2_pct")));
		pst.setEfg_pct(Utility.objectToDouble(map.get("efg_pct")));
		pst.setFt(Utility.objectToInt(map.get("ft")));
		pst.setFta(Utility.objectToInt(map.get("fta")));
		pst.setFt_pct(Utility.objectToDouble(map.get("ft_pct")));
		pst.setOrb(Utility.objectToInt(map.get("orb")));
		pst.setDrb(Utility.objectToInt(map.get("drb")));
		pst.setTrb(Utility.objectToInt(map.get("trb")));
		pst.setAst(Utility.objectToInt(map.get("ast")));
		pst.setStl(Utility.objectToInt(map.get("stl")));
		pst.setBlk(Utility.objectToInt(map.get("blk")));
		pst.setTov(Utility.objectToInt(map.get("tov")));
		pst.setPf(Utility.objectToInt(map.get("pf")));
		pst.setPts(Utility.objectToInt(map.get("pts")));
		return pst;
	}

	private PlayerStatsPerGame getPlayerPerGame(Map<String, Object> map) {
		PlayerStatsPerGame psp = new PlayerStatsPerGame();
		if (map.get("player_name") == null) {
			return null;
		}
		psp.setName(map.get("player_name").toString());
		psp.setSeason(map.get("season").toString());
		psp.setIs_normal(Utility.objectToInt(map.get("is_normal")));
		psp.setTeam(map.get("team_abbr").toString());
		psp.setPosition(map.get("position").toString());
		psp.setNum_Of_Game(Utility.objectToInt(map.get("num_of_game")));
		psp.setGame_started(Utility.objectToInt(map.get("game_start")));
		psp.setMinute(Utility.objectToDouble(map.get("minute")));
		psp.setFg(Utility.objectToDouble(map.get("fg")));
		psp.setFga(Utility.objectToDouble(map.get("fga")));
		psp.setFga_pct(Utility.objectToDouble(map.get("fga_pct")));
		psp.setFg3(Utility.objectToDouble(map.get("fg3")));
		psp.setFg3a(Utility.objectToDouble(map.get("fg3a")));
		psp.setFg3_pct(Utility.objectToDouble(map.get("fg3_pct")));
		psp.setFg2(Utility.objectToDouble(map.get("fg2")));
		psp.setFg2a(Utility.objectToDouble(map.get("fg2a")));
		psp.setFg2_pct(Utility.objectToDouble(map.get("fg2_pct")));
		psp.setEfg_pct(Utility.objectToDouble(map.get("efg_pct")));
		psp.setFt(Utility.objectToDouble(map.get("ft")));
		psp.setFta(Utility.objectToDouble(map.get("fta")));
		psp.setFt_pct(Utility.objectToDouble(map.get("ft_pct")));
		psp.setOrb(Utility.objectToDouble(map.get("orb")));
		psp.setDrb(Utility.objectToDouble(map.get("drb")));
		psp.setTrb(Utility.objectToDouble(map.get("trb")));
		psp.setAst(Utility.objectToDouble(map.get("ast")));
		psp.setStl(Utility.objectToDouble(map.get("stl")));
		psp.setBlk(Utility.objectToDouble(map.get("blk")));
		psp.setTov(Utility.objectToDouble(map.get("tov")));
		psp.setPf(Utility.objectToDouble(map.get("pf")));
		psp.setPts(Utility.objectToDouble(map.get("pts")));
		return psp;
	}

	private PlayerStatsAdvanced getPlayerAdvanced(Map<String, Object> map) {
		PlayerStatsAdvanced psa = new PlayerStatsAdvanced();
		if (map.get("player_name") == null) {
			return null;
		}
		psa.setName(map.get("player_name").toString());
		psa.setSeason(map.get("season").toString());
		psa.setIs_normal(Utility.objectToInt(map.get("is_normal")));
		psa.setTeam(map.get("team_abbr").toString());
		psa.setPosition(map.get("position").toString());
		psa.setNum_Of_Game(Utility.objectToInt(map.get("num_of_game")));
		psa.setMinute(Utility.objectToInt(map.get("minute")));
		psa.setPer(Utility.objectToDouble(map.get("per")));
		psa.setTs_pct(Utility.objectToDouble(map.get("ts_pct")));
		psa.setFa3a_per_fga_pct(Utility.objectToDouble(map
				.get("fa3a_per_fga_pct")));
		psa.setFta_per_fga_pct(Utility.objectToDouble(map
				.get("fta_per_fga_pct")));
		psa.setOrb_pct(Utility.objectToDouble(map.get("orb_pct")));
		psa.setDrb_pct(Utility.objectToDouble(map.get("drb_pct")));
		psa.setTrb_pct(Utility.objectToDouble(map.get("trb_pct")));
		psa.setAst_pct(Utility.objectToDouble(map.get("ast_pct")));
		psa.setStl_pct(Utility.objectToDouble(map.get("ast_pct")));
		psa.setBlk_pct(Utility.objectToDouble(map.get("blk_pct")));
		psa.setTov_pct(Utility.objectToDouble(map.get("tov_pct")));
		psa.setUsg_pct(Utility.objectToDouble(map.get("usg_pct")));
		psa.setOws(Utility.objectToDouble(map.get("ows")));
		psa.setDws(Utility.objectToDouble(map.get("dws")));
		psa.setWs(Utility.objectToDouble(map.get("ws")));
		psa.setWs_48(Utility.objectToDouble(map.get("ws_48")));
		psa.setObpm(Utility.objectToDouble(map.get("obpm")));
		psa.setDbpm(Utility.objectToDouble(map.get("dbpm")));
		psa.setBpm(Utility.objectToDouble(map.get("bpm")));
		psa.setVorp(Utility.objectToDouble(map.get("vorp")));
		return psa;
	}

	private PlayerSalary getPlayerSalary(Map<String, Object> map) {
		PlayerSalary ps = new PlayerSalary();
		if (map.get("name") == null) {
			return null;
		}
		ps.setName(map.get("name").toString());
		ps.setSeason(map.get("season").toString());
		ps.setTeam(map.get("team").toString());
		ps.setSalary(Utility.salaryObjectToLong(map.get("salary")));
		return ps;
	}

	private HotPlayerInfo getHotPlayerInfo(Map<String, Object> map,
			FieldType field) {
		if (map.get("player_name") == null)
			return null;
		HotPlayerInfo info = new HotPlayerInfo();
		info.setName(map.get("player_name").toString());
		info.setTeam(map.get("team_abbr").toString());
		info.setSeason(map.get("season").toString());
		info.setPosition(map.get("position").toString());
		info.setField(field);
		info.setValue(map.get(field.toString()).toString());
		return info;
	}

	private boolean isFieldValid(FieldType field) {
		switch (field) {
		case DEF_RTG:
		case OFF_RTG:
		case DRB_PCT:
		case ORB_PCT:
			return false;
		default:
			return true;
		}
	}

}
