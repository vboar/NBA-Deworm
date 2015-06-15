package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.CommonService;
import service.InferStatsService;
import service.MatchService;
import service.PlayerService;
import service.ServiceFactory;
import service.StatsService;
import service.TeamService;

/**
 * RMI service 服务工厂
 * 
 * created by JaneLDQ on 2015年5月31日 下午8:26:18
 */
public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory{

	private static final long serialVersionUID = 1L;
	
	private static ServiceFactory serviceFactory = null;
	
	private ServiceFactoryImpl() throws RemoteException {
		super();
	}
	
	public static ServiceFactory getInstance() {
		if(serviceFactory==null){
			try {
				serviceFactory = new ServiceFactoryImpl();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return serviceFactory;
	}

	@Override
	public PlayerService getPlayerService() throws RemoteException {
		return new PlayerServiceImpl();
	}

	@Override
	public TeamService getTeamService() throws RemoteException {
		return new TeamServiceImpl();
	}

	@Override
	public MatchService getMatchService() throws RemoteException {
		return new MatchServiceImpl();
	}

	@Override
	public CommonService getCommonService() throws RemoteException {
		return new CommonServiceImpl();
	}

	@Override
	public StatsService getStatsService() throws RemoteException {
		return new StatsServiceImpl();
	}

	@Override
	public InferStatsService getInferStatsService() throws RemoteException {
		return new InferStatsServiceImpl();
	}
	
}
