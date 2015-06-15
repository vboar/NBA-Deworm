package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.InferenceStatsService;
import vo.MultiRegressionVO;
import vo.SimpleRegressionVO;
import vo.TeamWinAnalysisVO;

public class InferenceStatsServiceImpl extends UnicastRemoteObject implements InferenceStatsService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected InferenceStatsServiceImpl() throws RemoteException {
		super();
	}

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

}
