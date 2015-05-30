package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.MatchDao;
import entity.Match;
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
	public void insertMatch(List<Match> list) {
		
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
    
}
