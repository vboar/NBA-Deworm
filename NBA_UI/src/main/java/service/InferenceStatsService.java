package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import vo.MultiRegressionVO;
import vo.SimpleRegressionVO;
import vo.TeamWinAnalysisVO;

/**
 * 推断统计调用接口
 * 
 * created by JaneLDQ on 2015年6月14日 下午3:38:47
 */
public interface InferenceStatsService extends Remote{
		
	
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
	MultiRegressionVO getMultiRegression(String season) throws RemoteException;

	/**
	 * 获得某个数据与球队净胜分的一元回归分析
	 * @param typeNum
	 * @param season
	 * @return
	 * @throws RemoteException
	 */
	SimpleRegressionVO getSimpleRegression(int typeNum, String season) throws RemoteException;
	
}
