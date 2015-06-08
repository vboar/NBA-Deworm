package dao;

import java.util.List;

import javax.swing.ImageIcon;

import util.FieldType;
import vo.PlayerFilter;
import entity.HotPlayerInfo;
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
	 * 模糊查找匹配的球员名字
	 * @param str 查找输入字符（球员姓名）
	 * @return
	 */
	public List<String> getNameList(String str);
	
	/**
	 * 获得一列球员头像
	 * @param names 球员名字列表
	 * @return
	 */
	public List<ImageIcon> getPlayerPortraitByNameList(List<String> names);
	
	/**
	 * 获得单个球员头像
	 * @param name 球员姓名
	 * @return
	 */
	public ImageIcon getPlayerPortraitByName(String name);
	
	/**
	 * 根据名字首字母获得球员名字
	 * @param initial 首字母A-Z
	 */
	public List<String> getNameByNameInitial(String initial);

    /**
     * 获得所有球员信息
     * @return
     */
    public List<PlayerInfo> getAllPlayerInfo();
	
	/**
	 * 获取单个球员基本信息
	 * @param name 姓名
	 * @return
	 */
	public PlayerInfo getPlayerInfoByName(String name);
	
	/**
	 * 获取单个球员所有赛季的总数据
	 * @param name 球员姓名
	 * @return
	 */
	public List<PlayerStatsTotal> getPlayerTotalByName(String name);
	
	/**
	 * 获取单赛季的所有球员总数据
	 * @param season 赛季
	 * @return
	 */
	public List<PlayerStatsTotal> getPlayerTotalBySeason(String season);
	
	/**
	 * 根据姓名查找球员某赛季总数据
	 * @param season
	 * @param name
	 * @return
	 */
	public List<PlayerStatsTotal> getPlayerTotalBySeasonName(String season, String name, int regular);
	
	/**
	 * 多项条件筛选球员总数据
	 * @param filter
	 * @return
	 */
	public List<PlayerStatsTotal> getPlayerTotalByFilter(PlayerFilter filter);
	
	/**
	 * 获取单个球员所有赛季的场均数据
	 * @param name 球员姓名
	 * @return
	 */
	public List<PlayerStatsPerGame> getPlayerPerGameByName(String name);
	
	/**
	 * 获取单个赛季的所有球员场均数据
	 * @param season 赛季
	 * @return
	 */
	public List<PlayerStatsPerGame> getPlayerPerGameBySeason(String season);
	
	/**
	 * 根据姓名查找球员某赛季场均数据
	 * @param season
	 * @param name
	 * @param regular/off 1/0
	 * @return
	 */
	public List<PlayerStatsPerGame> getPlayerPerGameBySeasonName(String season, String name, int regular);
	
	/**
	 * 多项条件筛选球员场均数据
	 * @param filter
	 * @return
	 */
	public List<PlayerStatsPerGame> getPlayerPerGameByFilter(PlayerFilter filter);

	
	/**
	 * 获取单个球员所有赛季的高阶数据
	 * @param name 球员姓名
	 * @return
	 */
	public List<PlayerStatsAdvanced> getPlayerAdvancedByName(String name);
	
	/**
	 * 获取单个赛季所有球员的高阶数据
	 * @param season 赛季
	 * @return
	 */
	public List<PlayerStatsAdvanced> getPlayerAdvancedBySeason(String season);
	
	/**
	 * 根据姓名查找球员某赛季高阶数据
	 * @param season
	 * @param name
	 * @param regular
	 * @return
	 */
	public List<PlayerStatsAdvanced> getPlayerAdvancedBySeasonName(String season, String name, int regular);
			
	/**
	 * 多项条件筛选球员高阶数据
	 * @param filter
	 * @return
	 */
	public List<PlayerStatsAdvanced> getPlayerAdvancedByFilter(PlayerFilter filter);
	
	/**
	 * 获取某个球队某赛季的签约球员
	 * @param season 赛季，如果为null则为所有赛季
	 * @param abbr 球队缩写
	 */
	public List<PlayerInfo> getTeamPlayerBySeason(String season, String abbr);
	
	/**
	 * 获取某赛季热门属性的热点球队
	 * @param season
	 * @param field
	 * @return
	 */
	public List<HotPlayerInfo> getHotPlayerBySeason(String season, FieldType field);
	
	/** 
	 * 拿到一个球员某赛季所属球队（返回按日期倒序，第一个最新所属球队）
	 * @param name 球员姓名
	 * @param season 赛季
	 * @return
	 */
	public List<String> getTeamByPlayerNameSeason(String name, String season);
	
	/**
	 * 获得单个球员每赛季的薪水列表
	 * @param name
	 * @return
	 */
	public List<PlayerSalary> getPlayerSalaryByName(String name);
	
	/**
	 * 获得某赛季全部球员的薪水列表
	 * @param season
	 * @param name null则为该赛季全部球员
	 * @return
	 */
	public List<PlayerSalary> getPlayerSalaryBySeason(String season, String name);
	
	/**
	 * 获得某赛季某球队球员的薪水列表
	 * @param season 
	 * @param team 球队缩写
	 */
	public List<PlayerSalary> getPlayerSalaryByTeam(String season, String team);
	
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
