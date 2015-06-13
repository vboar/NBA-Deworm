package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;

import vo.HotTeamInfoVO;
import vo.TeamAdvancedVO;
import vo.TeamFilter;
import vo.TeamInfoVO;
import vo.TeamOppPerGameVO;
import vo.TeamOppTotalVO;
import vo.TeamPerGameVO;
import vo.TeamTotalVO;

/**
 * 球队RMI数据服务接口
 * 
 * created by JaneLDQ on 2015年5月31日 下午6:31:24
 */
public interface TeamService extends Remote{
	
	/**
	 * 获得全部球队队徽
	 * @return
	 * @throws RemoteException
	 */
	public List<ImageIcon> getAllTeamLogo() throws RemoteException;
	
	/**
	 * 根据球队缩写获得队徽
	 * @param abbr
	 * @return
	 * @throws RemoteException
	 */
	public ImageIcon getTeamLogoByAbbr(String abbr) throws RemoteException;

	/**
	 * 根据缩写获取球队信息
	 * @param abbr 缩写
	 */
	public TeamInfoVO getTeamInfoByAbbr(String abbr) throws RemoteException;
	
	/**
	 * 获得全部球队基本信息
	 * @return
	 * @throws RemoteException 
	 */
	public List<TeamInfoVO> getAllTeamInfo() throws RemoteException;
	
	/**
	 * 获得全部球队某赛季总数据
	 * @param season 赛季
	 */
	public List<TeamTotalVO> getTeamTotalBySeason(String season) throws RemoteException;
	
	/**
	 * 获得全部球队某赛季场均数据
	 * @param season 赛季
	 */
	public List<TeamPerGameVO> getTeamPerGameBySeason(String season) throws RemoteException;
	
	/**
	 * 获得全部球队某赛季对手总数据
	 * @param season 赛季
	 */
	public List<TeamOppTotalVO> getTeamOppTotalBySeason(String season) throws RemoteException;
	
	/**
	 * 获得全部球队某赛季对手场均数据
	 * @param season 赛季
	 */
	public List<TeamOppPerGameVO> getTeamOppPerGameBySeason(String season) throws RemoteException;

	/**
	 * 获得单个球队全部赛季总数据
	 * @param abbr 队名缩写
	 */
	public List<TeamTotalVO> getTeamTotalByAbbr(String abbr) throws RemoteException;
	
	/**
	 * 获得单个球队全部赛季场均数据
	 * @param abbr 队名缩写
	 */
	public List<TeamPerGameVO> getTeamPerGameByAbbr(String abbr) throws RemoteException;
	
	/**
	 * 获得单个球队全部赛季对手总数据
	 * @param abbr 队名缩写
	 */
	public List<TeamOppTotalVO> getTeamOppTotalByAbbr(String abbr) throws RemoteException;
	
	/**
	 * 获得单个球队全部赛季对手场均数据
	 * @param abbr 队名缩写
	 */
	public List<TeamOppPerGameVO> getTeamOppPerGameByAbbr(String abbr) throws RemoteException;
	
	/**
	 * 多项条件筛选球队总数据
	 * @param filter
	 * @return
	 */
	public List<TeamTotalVO> getTeamTotalByFilter(TeamFilter filter) throws RemoteException;
	
	/**
	 * 多项条件筛选球队场均数据
	 * @param filter
	 * @return
	 */
	public List<TeamPerGameVO> getTeamPerGameByFilter(TeamFilter filter) throws RemoteException;	
	
	/**
	 * 获得球队某赛季总数据
	 * @param season
	 * @param abbr
	 * @return
	 */
	public TeamTotalVO getTeamTotalBySeasonAbbr(String season, String abbr) throws RemoteException;	
	
	/**
	 * 获得球队某赛季场均数据
	 * @param season
	 * @param abbr
	 * @return
	 */
	public TeamPerGameVO getTeamPerGameBySeasonAbbr(String season, String abbr) throws RemoteException;
	
	/**
	 * 获得球队某赛季对手总数据
	 * @param season
	 * @param abbr
	 * @return
	 */
	public TeamOppTotalVO getTeamOppTotalBySeasonAbbr(String season,String abbr) throws RemoteException;
	
	/**
	 * 获得球队对手某赛季场均数据
	 * @param season
	 * @param abbr
	 * @return
	 */
	public TeamOppPerGameVO getTeamOppPerGameBySeasonAbbr(String season, String abbr) throws RemoteException;
	
	/**
	 * 赛季热点球队
	 * @param field
	 * @return
	 */
	public List<HotTeamInfoVO> getSeasonHotTeam(String season, int fieldNum, int number) throws RemoteException;

	/**
	 * 多项条件筛选球队高阶数据
	 * @param filter
	 * @return
	 */
	public List<TeamAdvancedVO> getTeamAdvancedByFilter(TeamFilter filter) throws RemoteException;

	/**
	 * 获得球队某赛季高阶 数据
	 * @param season
	 * @param abbr
	 * @return
	 */
	public TeamAdvancedVO getTeamAdvancedBySeasonAbbr(String season, String abbr) throws RemoteException;
	
	/**
	 * 获得单个球队全部赛季高阶数据
	 * @param abbr 队名缩写
	 */
	public List<TeamAdvancedVO> getTeamAdvancedByAbbr(String abbr) throws RemoteException;
	
	/**
	 * 获得全部球队某赛季高阶数据
	 * @param season 赛季
	 */
	public List<TeamAdvancedVO> getTeamAdvancedBySeason(String season) throws RemoteException;

}
