package dao;

import java.util.List;

import vo.MatchFilter;
import entity.Match;
import entity.MatchInfo;
import entity.MatchPlayerAdvanced;
import entity.MatchPlayerBasic;

/**
 * 比赛Dao抽象接口
 * 
 * created by JaneLDQ on 2015年5月18日 下午5:06:44
 */
public interface MatchDao {
	
	/**
	 * 根据game_id获得某场比赛信息
	 */
	public MatchInfo getMatchInfoByGameId(String gameid);
	
	/**
	 * 获取单场比赛小分
	 * @param gameid
	 * @param home 主队/客队得分 = true/false
	 * @return List<List<Integer>> 内嵌每一个List<Integer>，第一个为home，第二个为guest
	 */
	public List<List<Integer>> getSectionScoreByGameId(String gameid);
	
	/**
	 * 获得某赛季的常规赛比赛基本信息
	 * @param season
	 * @return
	 */
	public List<MatchInfo> getRegularMatchInfoBySeason(String season);
	
	/**
	 * 获得某赛季的季后赛比赛基本信息
	 * @param season
	 * @return
	 */
	public List<MatchInfo> getPlayOffMatchInfoBySeason(String season);
	
	/**
	 * 获得某一时间段的比赛基本信息
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<MatchInfo> getMatchInfoByDate(String begin, String end);
	
	/**
	 * 多项条件筛选比赛信息
	 * @param filter
	 * @return
	 */
	public List<MatchInfo> getMatchInfoByFilter(MatchFilter filter);
	
	/**
	 * 获得单场比赛中一支球队的球员高阶数据
	 * @param gameid 比赛编号
	 * @param abbr 球队缩写
	 * @return
	 */
	public List<MatchPlayerAdvanced> getMatchPlayerAdvancedByGameIdTeam(String gameid, String abbr);
	
	/**
	 * 获得单场比赛一支球队的球员基本数据
	 * @param gameid 比赛编号
	 * @param abbr 球队缩写
	 * @return
	 */
	public List<MatchPlayerBasic> getMatchPlayerBasicByGameIdTeam(String gameid, String abbr);
	
	public List<MatchPlayerBasic> getMatchPlayerBasicByPlayerName(String name, String season, String abbr, int regular);
	
	public List<MatchPlayerAdvanced> getMatchPlayerAdvancedByPlayerName(String name, String season, String abbr,  int regular);
	
	public MatchPlayerBasic getMatchPlayerByGameIdNameAbbr(String gameid, String name, String abbr);
		
	public List<MatchPlayerBasic> getGuestHomeTeamTotalBySeason(String season, boolean home);
	
    /**
     * 插入比赛的完整信息和数据
     * @param list Match的List
     */
    public void insertMatch(List<Match> list);
}
