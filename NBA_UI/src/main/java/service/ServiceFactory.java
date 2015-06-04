package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Service抽象工厂
 * 
 * created by JaneLDQ on 2015年5月31日 下午8:13:55
 */
public interface ServiceFactory extends Remote{
	
	public PlayerService getPlayerService() throws RemoteException;
	
	public TeamService getTeamService() throws RemoteException;
	
	public MatchService getMatchService() throws RemoteException;
	
	public CommonService getCommonService() throws RemoteException;
	
	public LiveService getLiveService() throws RemoteException;

}
