package dao;

import java.util.List;

import entity.PlayerInfo;
import entity.PlayerSalary;
import entity.PlayerStatsAdvanced;
import entity.PlayerStatsPerGame;
import entity.PlayerStatsTotal;

/**
 * 原始Player数据解析抽象接口
 * 
 * created by JaneLDQ on 2015年5月18日 下午9:19:28
 */
public interface RawPlayerDao {

	public List<PlayerInfo> getAllPlayerInfo();
	
	public List<PlayerSalary> getAllPlayerSalary();
	
	public List<PlayerStatsPerGame> getAllPlayerPerGame();
	
	public List<PlayerStatsTotal> getAllPlayerTotal();
	
	public List<PlayerStatsAdvanced> getAllPlayerAdvanced();
	
}
