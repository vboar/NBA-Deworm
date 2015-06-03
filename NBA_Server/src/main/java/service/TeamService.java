package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;

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
}
