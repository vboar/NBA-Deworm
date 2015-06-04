package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import vo.MatchFilter;
import vo.MatchInfoVO;
import vo.MatchPlayerAdvancedVO;
import vo.MatchPlayerBasicVO;

/**
 * 比赛RMI数据服务接口
 * 
 * created by JaneLDQ on 2015年5月31日 下午6:31:54
 */
public interface MatchService extends Remote{
	
	/**
	 * 多项条件筛选比赛信息
	 * @param filter
	 * @return
	 */
	public List<MatchInfoVO> getMatchInfoByFilter(MatchFilter filter) throws RemoteException;
	
	/**
	 * 获得某赛季的常规赛比赛基本信息
	 * @param season
	 * @return
	 */
	public List<MatchInfoVO> getRegularMatchInfoBySeason(String season) throws RemoteException;
	
	/**
	 * 获得某赛季的季后赛比赛基本信息
	 * @param season
	 * @return
	 */
	public List<MatchInfoVO> getPlayOffMatchInfoBySeason(String season) throws RemoteException;
	
	/**
	 * 获得某一时间段的比赛基本信息
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<MatchInfoVO> getMatchInfoByDate(String begin, String end) throws RemoteException;
	
	/**
	 * 获得单场比赛中一支球队的球员高阶数据
	 * @param gameid 比赛编号
	 * @param abbr 球队缩写
	 * @return
	 */
	public List<MatchPlayerAdvancedVO> getMatchPlayerAdvancedByGameIdTeam(String gameid, String abbr) throws RemoteException;
	
	/**
	 * 获得单场比赛一支球队的球员基本数据
	 * @param gameid 比赛编号
	 * @param abbr 球队缩写
	 * @return
	 */
	public List<MatchPlayerBasicVO> getMatchPlayerBasicByGameIdTeam(String gameid, String abbr) throws RemoteException;
	
}
