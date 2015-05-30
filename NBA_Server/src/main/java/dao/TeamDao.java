package dao;

import java.util.List;

import entity.OpponentStatsPerGame;
import entity.OpponentStatsTotal;
import entity.TeamInfo;
import entity.TeamStatsAdvanced;
import entity.TeamStatsPerGame;
import entity.TeamStatsTotal;

public interface TeamDao {

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
