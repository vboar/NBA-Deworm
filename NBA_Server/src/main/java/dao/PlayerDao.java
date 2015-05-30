package dao;

import java.util.List;

import entity.PlayerInfo;
import entity.PlayerSalary;
import entity.PlayerStatsAdvanced;
import entity.PlayerStatsPerGame;
import entity.PlayerStatsTotal;

/**
 * 球员数据Dao抽象接口
 * 
 * created by JaneLDQ on 2015年5月28日 下午6:59:16
 */
public interface PlayerDao {
	
	/**
	 * 插入球员基本信息
	 */
	public void insertPlayerInfo(List<PlayerInfo> list);
	
	/**
	 * 插入球员薪水列表
	 */
	public void insertPlayerSalary(List<PlayerSalary> list);
	
	/**
	 * 插入球员总数据
	 */
	public void insertPlayerTotal(List<PlayerStatsTotal> list);
	
	/**
	 * 插入球员场均数据
	 */
	public void insertPlayerPerGame(List<PlayerStatsPerGame> list);
	
	/**
	 * 插入球员高阶数据
	 */
	public void insertPlayerAdvanced(List<PlayerStatsAdvanced> list);
	
}
