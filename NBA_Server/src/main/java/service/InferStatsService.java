package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import vo.MultiRegressionVO;
import vo.SimpleRegressionVO;
import vo.TeamWinAnalysisVO;

public interface InferStatsService extends Remote{

	/**
	 * 获得单个赛季——球队主客场胜场影响差异分析结果
	 * @param season 单个赛季
	 * @return
	 */
	public TeamWinAnalysisVO getTeamTestingResultBySeason(String season) throws RemoteException;
	
	/**
	 * 获得10个赛季——球队主客场胜场影响差异统计分析结果
	 * @return
	 */
	public TeamWinAnalysisVO getTeamTestingResult_10() throws RemoteException;

	/**
	 * 获得一个赛季的球队数据的多元回归分析
	 * @param season
	 * @return
	 * @throws RemoteException
	 */
	public MultiRegressionVO getMultiRegression(String season) throws RemoteException;

	/**
	 * 获得某个数据与球队净胜分的一元回归分析（球队赛季总数据）
	 * @param typeNum
	 * @param season
	 * @return
	 * @throws RemoteException
	 */
	public SimpleRegressionVO getSimpleRegression(int typeNum, String season) throws RemoteException;
	
	/**
	 * 获得单赛季球队得分与某指标的一元回归分析（单场比赛数据）
	 * @param typeNum
	 * @param season
	 * @return
	 * @throws RemoteException
	 */
	public SimpleRegressionVO getSimpleRegressionMatch(int typeNum, String season) throws RemoteException;
	
}
