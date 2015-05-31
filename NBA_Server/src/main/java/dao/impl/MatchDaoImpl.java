package dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.MatchDao;
import entity.Match;
import entity.MatchInfo;
import entity.MatchPlayerAdvanced;
import entity.MatchPlayerBasic;

/**
 * MatchDao的具体实现
 * 
 * created by JaneLDQ on 2015年5月17日 下午7:06:00
 */
public class MatchDaoImpl implements MatchDao {

	private SqlManager sqlManager = SqlManager.getSqlManager();	

	@Override
	public List<MatchInfo> getRegularMatchInfoBySeason(String season) {
		sqlManager.getConnection();
		List<MatchInfo> list = new ArrayList<MatchInfo>();
		String sql = "SELECT * FROM match_info WHERE season=? and is_normal=1 ORDER BY date";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{season});
		for(Map<String,Object> map: maplist){
			list.add(getMatchInfo(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<MatchInfo> getPlayOffMatchInfoBySeason(String season) {
		sqlManager.getConnection();
		List<MatchInfo> list = new ArrayList<MatchInfo>();
		String sql = "SELECT * FROM match_info WHERE season=? and is_normal=0 ORDER BY date";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{season});
		for(Map<String,Object> map: maplist){
			list.add(getMatchInfo(map));
		}
		sqlManager.releaseAll();
		return list;
	}
	
	@Override
	public List<MatchInfo> getMatchInfoByDate(String begin, String end) {
		sqlManager.getConnection();
		List<MatchInfo> list = new ArrayList<MatchInfo>();
		String sql = "SELECT * FROM match_info WHERE date >= ? and date <= ? ORDER BY date";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{begin,end});
		for(Map<String,Object> map: maplist){
			list.add(getMatchInfo(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<MatchPlayerAdvanced> getMatchPlayerAdvancedByGameIdTeam(
			String gameid, String abbr) {
		sqlManager.getConnection();
		List<MatchPlayerAdvanced> list = new ArrayList<MatchPlayerAdvanced>();
		String sql = "SELECT * FROM match_player_advanced WHERE game_id=? and team_abbr=? ";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{gameid,abbr});
		for(Map<String,Object> map: maplist){
			list.add(getMatchPlayerAdvanced(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public List<MatchPlayerBasic> getMatchPlayerBasicByGameIdTeam(
			String gameid, String abbr) {
		sqlManager.getConnection();
		List<MatchPlayerBasic> list = new ArrayList<MatchPlayerBasic>();
		String sql = "SELECT * FROM match_player_basic WHERE game_id=? and team_abbr=? ";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{gameid,abbr});
		for(Map<String,Object> map: maplist){
			list.add(getMatchPlayerBasic(map));
		}
		sqlManager.releaseAll();
		return list;
	}

	@Override
	public void insertMatch(List<Match> list) {
		if(list.size()==0)	return;
		
		sqlManager.getConnection();
		
		for(Match match: list){
						
			List<Object> infoObjects = new ArrayList<Object>();
			List<Object> scoreObjects = new ArrayList<Object>();
			List<Object> basicObjects = new ArrayList<Object>();
			List<Object> advancedObjects = new ArrayList<Object>();
			
			String sqlInfo = "INSERT INTO match_info (" +
	                "game_id," 		+ "season," +
	                "is_normal," 	+ "date," +
	                "location," 	+ "home_team," +
	                "guest_team," 	+ "home_point," +
	                "guest_point," 	+ "time" + 
	                ") VALUES ";
			
			String sqlScore = "INSERT INTO match_score (" +
					"game_id," 		+"section," +
	                "home_point," 	+"guest_point" +
					") VALUES ";
			
			String sqlBasic = "INSERT INTO match_player_basic (" +
					"game_id," 		+ "player_name," +
	                "team_abbr," 	+ "starter," +
	                "minute," 		+ "fg," +
	                "fga," 			+ "fga_pct," +
	                "fg3," 			+ "fg3a," +
	                "fg3_pct," 		+ "ft," +
	                "fta," 			+ "ft_pct," +
	                "orb," 			+ "drb," +
	                "trb," 			+ "ast," +
	                "stl," 			+ "blk," +
	                "tov,"			+ "pf," +
	                "pts," 			+ "plus_minus" +
					") VALUES";
			
			String sqlAdvanced = "INSERT INTO match_player_advanced (" +
	                "game_id," 		+ "player_name," +
	                "team_abbr," 	+ "starter," +
	                "minute," 		+ "ts_pct," +
	                "efg_pct,"		+ "fa3a_per_fga_pct," +
	                "fta_per_fga_pct," + 
	                "orb_pct," 		+ "drb_pct," + 
	                "trb_pct," 		+ "ast_pct," +
	                "stl_pct," 		+ "tov_pct," + 
	                "blk_pct," 		+ "usg_pct," + 
	                "off_rtg," 		+ "def_rtg" +
					") VALUES";
			
			//处理MatchInfo
			infoObjects.add(match.getGame_id());
			infoObjects.add(match.getSeason());
			infoObjects.add(match.isNormal());
			infoObjects.add(match.getDate());
			infoObjects.add(match.getLocation());
			infoObjects.add(match.getHome_team());
			infoObjects.add(match.getGuest_team());
			infoObjects.add(match.getHome_point());
			infoObjects.add(match.getGuest_point());
			infoObjects.add(match.getTime());
			
			sqlInfo = sqlManager.appendSQL(sqlInfo, 10);
			
			//处理MatchScore
            for (int i = 0; i < match.getHome_pts().size(); i++) {
                scoreObjects.add(match.getGame_id());
                scoreObjects.add(i);
                scoreObjects.add(match.getHome_pts().get(i));
                scoreObjects.add(match.getGuest_pts().get(i));
                
                sqlScore = sqlManager.appendSQL(sqlScore, 4);
            }
            
            //处理MatchPlayerBasic
            for(MatchPlayerBasic b: match.getHome_basic_list()){
            	basicObjects.add(b.getGame_id());
            	basicObjects.add(b.getPlayer_name());
            	basicObjects.add(b.getTeam_abbr());
            	basicObjects.add(b.getStarter());
            	basicObjects.add(b.getMinute());
            	basicObjects.add(b.getFg());
            	basicObjects.add(b.getFga());
            	basicObjects.add(b.getFga_pct());
            	basicObjects.add(b.getFg3());
            	basicObjects.add(b.getFg3a());
            	basicObjects.add(b.getFg3_pct());
            	basicObjects.add(b.getFt());
            	basicObjects.add(b.getFta());
            	basicObjects.add(b.getFt_pct());
            	basicObjects.add(b.getOrb());
            	basicObjects.add(b.getDrb());
            	basicObjects.add(b.getTrb());
            	basicObjects.add(b.getAst());
            	basicObjects.add(b.getStl());
            	basicObjects.add(b.getBlk());
            	basicObjects.add(b.getTov());
            	basicObjects.add(b.getPf());
            	basicObjects.add(b.getPts());
            	basicObjects.add(b.getPlus_minus());
            	
            	sqlBasic = sqlManager.appendSQL(sqlBasic, 24);
            }
            for(MatchPlayerBasic b: match.getGuest_basic_list()){
            	basicObjects.add(b.getGame_id());
            	basicObjects.add(b.getPlayer_name());
            	basicObjects.add(b.getTeam_abbr());
            	basicObjects.add(b.getStarter());
            	basicObjects.add(b.getMinute());
            	basicObjects.add(b.getFg());
            	basicObjects.add(b.getFga());
            	basicObjects.add(b.getFga_pct());
            	basicObjects.add(b.getFg3());
            	basicObjects.add(b.getFg3a());
            	basicObjects.add(b.getFg3_pct());
            	basicObjects.add(b.getFt());
            	basicObjects.add(b.getFta());
            	basicObjects.add(b.getFt_pct());
            	basicObjects.add(b.getOrb());
            	basicObjects.add(b.getDrb());
            	basicObjects.add(b.getTrb());
            	basicObjects.add(b.getAst());
            	basicObjects.add(b.getStl());
            	basicObjects.add(b.getBlk());
            	basicObjects.add(b.getTov());
            	basicObjects.add(b.getPf());
            	basicObjects.add(b.getPts());
            	basicObjects.add(b.getPlus_minus());
            	
            	sqlBasic = sqlManager.appendSQL(sqlBasic, 24);
            }
            //处理MatchPlayerAdvanced
            for(MatchPlayerAdvanced a: match.getHome_advanced_list()){
            	advancedObjects.add(a.getGame_id());
            	advancedObjects.add(a.getPlayer_name());
            	advancedObjects.add(a.getTeam_abbr());
            	advancedObjects.add(a.getStarter());
            	advancedObjects.add(a.getMinute());
            	advancedObjects.add(a.getTs_pct());
            	advancedObjects.add(a.getEfg_pct());
            	advancedObjects.add(a.getFa3a_per_fga_pct());
            	advancedObjects.add(a.getFta_per_fga_pct());
            	advancedObjects.add(a.getOrb_pct());
            	advancedObjects.add(a.getDrb_pct());
            	advancedObjects.add(a.getTrb_pct());
            	advancedObjects.add(a.getAst_pct());
            	advancedObjects.add(a.getStl_pct());
            	advancedObjects.add(a.getTov_pct());
            	advancedObjects.add(a.getBlk_pct());
            	advancedObjects.add(a.getUsg_pct());
            	advancedObjects.add(a.getOff_rtg());
            	advancedObjects.add(a.getDef_rtg());
            	
            	sqlAdvanced = sqlManager.appendSQL(sqlAdvanced, 19);
            }
            for(MatchPlayerAdvanced a: match.getGuest_advanced_list()){
            	advancedObjects.add(a.getGame_id());
            	advancedObjects.add(a.getPlayer_name());
            	advancedObjects.add(a.getTeam_abbr());
            	advancedObjects.add(a.getStarter());
            	advancedObjects.add(a.getMinute());
            	advancedObjects.add(a.getTs_pct());
            	advancedObjects.add(a.getEfg_pct());
            	advancedObjects.add(a.getFa3a_per_fga_pct());
            	advancedObjects.add(a.getFta_per_fga_pct());
            	advancedObjects.add(a.getOrb_pct());
            	advancedObjects.add(a.getDrb_pct());
            	advancedObjects.add(a.getTrb_pct());
            	advancedObjects.add(a.getAst_pct());
            	advancedObjects.add(a.getStl_pct());
            	advancedObjects.add(a.getTov_pct());
            	advancedObjects.add(a.getBlk_pct());
            	advancedObjects.add(a.getUsg_pct());
            	advancedObjects.add(a.getOff_rtg());
            	advancedObjects.add(a.getDef_rtg());
            	
            	sqlAdvanced = sqlManager.appendSQL(sqlAdvanced, 19);
            }
            
            sqlInfo = sqlManager.fillSQL(sqlInfo);
            sqlScore = sqlManager.fillSQL(sqlScore);
            sqlBasic = sqlManager.fillSQL(sqlBasic);
            sqlAdvanced = sqlManager.fillSQL(sqlAdvanced);

            sqlManager.executeUpdateByList(sqlInfo, infoObjects);
            sqlManager.executeUpdateByList(sqlScore, scoreObjects);
            sqlManager.executeUpdateByList(sqlBasic, basicObjects); 
            sqlManager.executeUpdateByList(sqlAdvanced, advancedObjects);
		}
		
		sqlManager.releaseConnection();
	}
    

	private MatchInfo getMatchInfo(Map<String, Object> map) {
		MatchInfo info = new MatchInfo();
		if(map.get("game_id")==null){
			return null;
		}
		List<Integer> homePoints = new ArrayList<Integer>();
		List<Integer> guestPoints = new ArrayList<Integer>();
		String sql = "SELECT * FROM match_score WHERE game_id=?";
		List<Map<String,Object>> maplist = sqlManager.queryMulti(sql, new Object[]{map.get("game_id")});
		for(Map<String, Object> sectionmap: maplist){
			try{
				homePoints.add(Integer.parseInt(sectionmap.get("home_point").toString()));
				guestPoints.add(Integer.parseInt(sectionmap.get("guest_point").toString()));
			}catch(Exception e){
				System.out.println("Section Score Null-pointer!");
				continue;
			}
		}
		info.setGame_id(map.get("game_id").toString());
		info.setSeason(map.get("season").toString());
		info.setIs_normal(Integer.parseInt(map.get("is_normal").toString()));
		info.setDate(map.get("date").toString());
		info.setLocation(map.get("location").toString());
		info.setHome_team(map.get("home_team").toString());
		info.setGuest_team(map.get("guest_team").toString());
		info.setHome_point(Integer.parseInt(map.get("home_point").toString()));
		info.setGuest_point(Integer.parseInt(map.get("guest_point").toString()));
		info.setTime(map.get("time").toString());
		info.setHome_pts(guestPoints);
		info.setGuest_pts(guestPoints);
		return info;
	}

	private MatchPlayerAdvanced getMatchPlayerAdvanced(Map<String, Object> map) {
		MatchPlayerAdvanced mpa = new MatchPlayerAdvanced();
		if(map.get("game_id")==null){
			return null;
		}
		mpa.setGame_id(map.get("game_id").toString());
		mpa.setPlayer_name(map.get("player_name").toString());
		mpa.setTeam_abbr(map.get("team_abbr").toString());
		mpa.setStarter(map.get("starter").toString());
		mpa.setMinute(Double.parseDouble(map.get("minute").toString()));
		mpa.setTs_pct(Double.parseDouble(map.get("ts_pct").toString()));
		mpa.setEfg_pct(Double.parseDouble(map.get("efg_pct").toString()));
		mpa.setFa3a_per_fga_pct(Double.parseDouble(map.get("fa3a_per_fga_pct").toString()));
		mpa.setFta_per_fga_pct(Double.parseDouble(map.get("fta_per_fga_pct").toString()));
		mpa.setOrb_pct(Double.parseDouble(map.get("orb_pct").toString()));
		mpa.setDrb_pct(Double.parseDouble(map.get("drb_pct").toString()));
		mpa.setTrb_pct(Double.parseDouble(map.get("drb_pct").toString()));
		mpa.setAst_pct(Double.parseDouble(map.get("ast_pct").toString()));
		mpa.setStl_pct(Double.parseDouble(map.get("stl_pct").toString()));
		mpa.setTov_pct(Double.parseDouble(map.get("tov_pct").toString()));
		mpa.setBlk_pct(Double.parseDouble(map.get("blk_pct").toString()));
		mpa.setUsg_pct(Double.parseDouble(map.get("usg_pct").toString()));
		mpa.setOff_rtg(Double.parseDouble(map.get("off_rtg").toString()));
		mpa.setDef_rtg(Double.parseDouble(map.get("def_rtg").toString()));
		return mpa;
	}

	private MatchPlayerBasic getMatchPlayerBasic(Map<String, Object> map) {
		MatchPlayerBasic mpb = new MatchPlayerBasic();
		if(map.get("game_id")==null){
			return null;
		}
		mpb.setGame_id(map.get("game_id").toString());
		mpb.setPlayer_name(map.get("player_name").toString());
		mpb.setTeam_abbr(map.get("team_abbr").toString());
		mpb.setStarter(map.get("starter").toString());
		mpb.setMinute(Double.parseDouble(map.get("minute").toString()));
		mpb.setFg(Integer.parseInt(map.get("fg").toString()));
		mpb.setFga(Integer.parseInt(map.get("fga").toString()));
		mpb.setFga_pct(Double.parseDouble(map.get("fga_pct").toString()));
		mpb.setFg3(Integer.parseInt(map.get("fg3").toString()));
		mpb.setFg3a(Integer.parseInt(map.get("fg3a").toString()));
		mpb.setFg3_pct(Double.parseDouble(map.get("fg3_pct").toString()));
		mpb.setFt(Integer.parseInt(map.get("ft").toString()));
		mpb.setFta(Integer.parseInt(map.get("fta").toString()));
		mpb.setFt_pct(Double.parseDouble(map.get("ft_pct").toString()));
		mpb.setOrb(Integer.parseInt(map.get("orb").toString()));
		mpb.setDrb(Integer.parseInt(map.get("drb").toString()));
		mpb.setTrb(Integer.parseInt(map.get("trb").toString()));
		mpb.setAst(Integer.parseInt(map.get("ast").toString()));
		mpb.setBlk(Integer.parseInt(map.get("blk").toString()));
		mpb.setTov(Integer.parseInt(map.get("tov").toString()));
		mpb.setPf(Integer.parseInt(map.get("pf").toString()));
		mpb.setPts(Integer.parseInt(map.get("pts").toString()));
		mpb.setPlus_minus(Double.parseDouble(map.get("plus_minus").toString()));
		return mpb;
	}
	
}
