package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.InferStatsService;
import vo.MultiRegressionVO;
import vo.SimpleRegressionVO;
import vo.TeamWinAnalysisVO;

public class InferStatsServiceImpl extends UnicastRemoteObject implements InferStatsService {
	
	public InferStatsServiceImpl() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public TeamWinAnalysisVO getTeamTestingResultBySeason(String season)
			throws RemoteException {
		return null;
	}

	@Override
	public TeamWinAnalysisVO getTeamTestingResult_10() throws RemoteException {
		return null;
	}

	@Override
	public MultiRegressionVO getMultiRegression(String season)
			throws RemoteException {
		return null;
	}

	@Override
	public SimpleRegressionVO getSimpleRegression(int typeNum, String season)
			throws RemoteException {
		return null;
	}

	@Override
	public SimpleRegressionVO getSimpleRegressionMatch(int typeNum,
			String season) throws RemoteException {
		return null;
	}

}
