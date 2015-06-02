package dao;

import java.util.List;

import vo.TeamFilter;
import entity.OpponentStatsPerGame;
import entity.OpponentStatsTotal;
import entity.TeamInfo;
import entity.TeamStatsAdvanced;
import entity.TeamStatsPerGame;
import entity.TeamStatsTotal;

public interface TeamDao {
	
	/**
	 * 获得球队基本信息
	 */
	public List<TeamInfo> getAllTeamInfo();
	
	/**
	 * 获得全部球队某赛季总数据
	 * @param season 赛季
	 */
	public List<TeamStatsTotal> getTeamTotalBySeason(String season);
	
	/**
	 * 获得全部球队某赛季场均数据
	 * @param season 赛季
	 */
	public List<TeamStatsPerGame> getTeamPerGameBySeason(String season);
	
	/**
	 * 获得全部球队某赛季对手总数据
	 * @param season 赛季
	 */
	public List<OpponentStatsTotal> getTeamOppTotalBySeason(String season);
	
	/**
	 * 获得全部球队某赛季对手场均数据
	 * @param season 赛季
	 */
	public List<OpponentStatsPerGame> getTeamOppPerGameBySeason(String season);

	/**
	 * 获得单个球队全部赛季总数据
	 * @param abbr 队名缩写
	 */
	public List<TeamStatsTotal> getTeamTotalByAbbr(String abbr);
	
	/**
	 * 获得单个球队全部赛季场均数据
	 * @param abbr 队名缩写
	 */
	public List<TeamStatsPerGame> getTeamPerGameByAbbr(String abbr);
	
	/**
	 * 获得单个球队全部赛季对手总数据
	 * @param abbr 队名缩写
	 */
	public List<OpponentStatsTotal> getTeamOppTotalByAbbr(String abbr);
	
	/**
	 * 获得单个球队全部赛季对手场均数据
	 * @param abbr 队名缩写
	 */
	public List<OpponentStatsPerGame> getTeamOppPerGameByAbbr(String abbr);
	
	/**
	 * 多项条件筛选球队总数据
	 * @param filter
	 * @return
	 */
	public List<TeamStatsTotal> getTeamTotalByFilter(TeamFilter filter);
	
	/**
	 * 多项条件筛选球队场均数据
	 * @param filter
	 * @return
	 */
	public List<TeamStatsPerGame> getTeamPerGameByFilter(TeamFilter filter);
	
	/**
	 * 插入球队基本信息
	 */
	public void insertTeamInfo(List<TeamInfo> list);
	
	/**
	 * 插入球队总数据
	 */
	public void insertTeamTotal(List<TeamStatsTotal> list);
	
	/**
	 * 插入球队场均数据
	 */
	public void insertTeamPerGame(List<TeamStatsPerGame> list);
	
	/**
	 * 插入球队高阶数据
	 */
	public void insertTeamAdvanced(List<TeamStatsAdvanced> list);
	
	/**
	 * 插入球队对手总数据
	 */
	public void insertTeamOppTotal(List<OpponentStatsTotal> list);
	
	/**
	 * 插入球队对手场均数据
	 */
	public void insertTeamOppPerGame(List<OpponentStatsPerGame> list);
	
}
