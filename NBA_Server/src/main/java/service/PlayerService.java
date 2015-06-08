package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;

import util.FieldType;
import vo.HotPlayerInfoVO;
import vo.PlayerAdvancedVO;
import vo.PlayerFilter;
import vo.PlayerInfoVO;
import vo.PlayerPerGameVO;
import vo.PlayerSalaryVO;
import vo.PlayerTotalVO;

/**
 * 球员RMI数据服务接口
 * 
 * created by JaneLDQ on 2015年5月31日 下午6:31:07
 */
public interface PlayerService extends Remote{

	
	/**
	 * 模糊查找匹配的球员名字
	 * @param str 查找输入字符（球员姓名）
	 * @return
	 */
	public List<String> getNameList(String str) throws RemoteException;
	
	/**
	 * 获得一列球员头像
	 * @param names 球员名字列表
	 * @return
	 */
	public List<ImageIcon> getPlayerPortraitByNameList(List<String> names) throws RemoteException;
	
	/**
	 * 获得单个球员头像
	 * @param name 球员姓名
	 * @return
	 */
	public ImageIcon getPlayerPortraitByName(String name) throws RemoteException;
	
	/**
	 * 根据名字首字母获得球员名字
	 * @param initial 首字母A-Z
	 */
	public List<String> getNameByNameInitial(String initial) throws RemoteException;

    /**
     * 获得所有球员信息
     * @return
     */
    public List<PlayerInfoVO> getAllPlayerInfo() throws RemoteException;
	
	/**
	 * 获取单个球员基本信息
	 * @param name 姓名
	 * @return
	 */
	public PlayerInfoVO getPlayerInfoByName(String name) throws RemoteException;
	
	/**
	 * 获取单个球员所有赛季的总数据
	 * @param name 球员姓名
	 * @return
	 */
	public List<PlayerTotalVO> getPlayerTotalByName(String name) throws RemoteException;
	
	/**
	 * 获取单赛季的所有球员总数据
	 * @param season 赛季
	 * @return
	 */
	public List<PlayerTotalVO> getPlayerTotalBySeason(String season) throws RemoteException;
	
	/**
	 * 根据姓名查找球员某赛季总数据
	 * @param season
	 * @param name
	 * @return
	 */
	public List<PlayerTotalVO> getPlayerTotalBySeasonName(String season, String name, int regular) throws RemoteException;
	
	/**
	 * 多项条件筛选球员总数据
	 * @param filter
	 * @return
	 */
	public List<PlayerTotalVO> getPlayerTotalByFilter(PlayerFilter filter) throws RemoteException;
	
	/**
	 * 获取单个球员所有赛季的场均数据
	 * @param name 球员姓名
	 * @return
	 */
	public List<PlayerPerGameVO> getPlayerPerGameByName(String name) throws RemoteException;
	
	/**
	 * 获取单个赛季的所有球员场均数据
	 * @param season 赛季
	 * @return
	 */
	public List<PlayerPerGameVO> getPlayerPerGameBySeason(String season) throws RemoteException;
	
	/**
	 * 根据姓名查找球员某赛季场均数据
	 * @param season
	 * @param name
	 * @param regular/off 1/0
	 * @return
	 */
	public List<PlayerPerGameVO> getPlayerPerGameBySeasonName(String season, String name, int regular) throws RemoteException;
	
	/**
	 * 多项条件筛选球员场均数据
	 * @param filter
	 * @return
	 */
	public List<PlayerPerGameVO> getPlayerPerGameByFilter(PlayerFilter filter) throws RemoteException;

	
	/**
	 * 获取单个球员所有赛季的高阶数据
	 * @param name 球员姓名
	 * @return
	 */
	public List<PlayerAdvancedVO> getPlayerAdvancedByName(String name) throws RemoteException;
	
	/**
	 * 获取单个赛季所有球员的高阶数据
	 * @param season 赛季
	 * @return
	 */
	public List<PlayerAdvancedVO> getPlayerAdvancedBySeason(String season) throws RemoteException;
	
	/**
	 * 根据姓名查找球员某赛季高阶数据
	 * @param season
	 * @param name
	 * @param regular
	 * @return
	 */
	public List<PlayerAdvancedVO> getPlayerAdvancedBySeasonName(String season, String name, int regular) throws RemoteException;
			
	/**
	 * 多项条件筛选球员高阶数据
	 * @param filter
	 * @return
	 */
	public List<PlayerAdvancedVO> getPlayerAdvancedByFilter(PlayerFilter filter) throws RemoteException;
	
	/**
	 * 获取某个球队某赛季的签约球员
	 * @param season 赛季，如果为null则为所有赛季
	 * @param abbr 球队缩写
	 */
	public List<PlayerInfoVO> getTeamPlayerBySeason(String season, String abbr) throws RemoteException;
	
	/**
	 * 获取某赛季热门属性的热点球队
	 * @param season
	 * @param field
	 * @return
	 */
	public List<HotPlayerInfoVO> getSeasonHotPlayer(String season, FieldType field) throws RemoteException;
	
	/**
	 * 获得单个球员每赛季的薪水列表
	 * @param name
	 * @return
	 */
	public List<PlayerSalaryVO> getPlayerSalaryByName(String name) throws RemoteException;
	
	/**
	 * 获得某赛季全部球员的薪水列表
	 * @param season
	 * @return
	 */
	public List<PlayerSalaryVO> getPlayerSalaryBySeason(String season, String name) throws RemoteException; 
	
	/** 
	 * 拿到一个球员某赛季所属球队（返回按日期倒序，第一个最新所属球队）
	 * @param name 球员姓名
	 * @param season 赛季
	 * @return
	 */
	public List<String> getTeamByPlayerNameSeason(String name, String season) throws RemoteException;

}
