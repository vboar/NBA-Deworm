package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * 公共数据RMI服务接口
 * 
 * created by JaneLDQ on 2015年5月31日 下午6:32:23
 */
public interface CommonService extends Remote{

	public List<String> getAllSeason() throws RemoteException;
	
}
