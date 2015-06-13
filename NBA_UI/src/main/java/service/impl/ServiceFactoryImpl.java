package service.impl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

import service.CommonService;
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
	
	/**
	 * 单例实例
	 */
	private static ServiceFactory serviceFactory = null;
	
	/**
	 * 默认ip
	 */
	public static String address = null;
	
	/**
	 * 默认端口
	 */
	public static String port = null;

    static{
    	Properties prop = new Properties();
    	try{
    		InputStream in = new BufferedInputStream(new FileInputStream("service.properties"));
    		prop.load(in);
    		address = prop.getProperty("address");
    		port = prop.getProperty("port");
    	}catch(Exception e){
    		System.out.println(e);
    	}
    }
    
	/**
	 * 私有化构造方法
	 * @throws RemoteException
	 */
	private ServiceFactoryImpl() throws RemoteException {
		super();
	}
	
	/**
	 * 获得实例
	 * @return
	 */
	public static ServiceFactory getInstance() {
		try{
			serviceFactory = (ServiceFactory)Naming.lookup("rmi://"+address+":"+port+"/ServiceFactory");
		}catch(RemoteException | NotBoundException |MalformedURLException e){
			e.printStackTrace();
			System.out.println("\n-------- Get Service Error ------ ");
		}
		return serviceFactory;
	}

	public PlayerService getPlayerService() throws RemoteException {
		return new PlayerServiceImpl();
	}

	public TeamService getTeamService() throws RemoteException {
		return new TeamServiceImpl();
	}

	public MatchService getMatchService() throws RemoteException {
		return new MatchServiceImpl();
	}

	public CommonService getCommonService() throws RemoteException {
		return new CommonServiceImpl();
	}

	@Override
	public StatsService getStatsService() throws RemoteException {
		return null;
	}


}
