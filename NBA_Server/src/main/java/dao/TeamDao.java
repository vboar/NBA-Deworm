package dao;

import java.util.List;

import javax.swing.ImageIcon;

import util.FieldType;
import vo.TeamFilter;
import entity.HotTeamInfo;
import entity.OpponentStatsPerGame;
import entity.OpponentStatsTotal;
import entity.TeamInfo;
import entity.TeamStatsAdvanced;
import entity.TeamStatsPerGame;
import entity.TeamStatsTotal;

public interface TeamDao {
	
	/**
	 * 获得全部球队队徽
	 */
	public List<ImageIcon> getAllTeamLogo();
	
	/*
	 * 根据缩写获得队徽 
	 */
	public ImageIcon getTeamLogoByAbbr(String abbr);
	
	/**
	 * 获得球队基本信息
	 */
	public List<TeamInfo> getAllTeamInfo();
	
	/**
	 * 根据缩写获取球队信息
	 * @param abbr 缩写
	 */
	public TeamInfo getTeamInfoByAbbr(String abbr);
	
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
	 * 获得球队某赛季总数据
	 * @param season
	 * @param abbr
	 * @return
	 */
	public TeamStatsTotal getTeamTotalBySeasonAbbr(String season, String abbr);	
	
	/**
	 * 获得球队某赛季场均数据
	 * @param season
	 * @param abbr
	 * @return
	 */
	public TeamStatsPerGame getTeamPerGameBySeasonAbbr(String season, String abbr);
	
	/**
	 * 获得球队某赛季对手总数据
	 * @param season
	 * @param abbr
	 * @return
	 */
	public OpponentStatsTotal getTeamOppTotalBySeasonAbbr(String season,String abbr);
	
	/**
	 * 获得球队对手某赛季场均数据
	 * @param season
	 * @param abbr
	 * @return
	 */
	public OpponentStatsPerGame getTeamOppPerGameBySeasonAbbr(String season, String abbr);
	
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
	 * 赛季热点球队
	 * @param field
	 * @return
	 */
	public List<HotTeamInfo> getSeasonHotTeam(String season, FieldType field);
	
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
