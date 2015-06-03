package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.swing.ImageIcon;

import service.PlayerService;
import vo.PlayerAdvancedVO;
import vo.PlayerInfoVO;
import vo.PlayerPerGameVO;
import vo.PlayerSalaryVO;
import vo.PlayerTotalVO;

/**
 * PlayerService的具体实现
 * 
 * created by JaneLDQ on 2015年5月31日 下午8:26:53
 */
public class PlayerServiceImpl extends UnicastRemoteObject implements PlayerService {
	
	private static final long serialVersionUID = 1L;
	
	public PlayerServiceImpl() throws RemoteException {
		super();
	}

	public List<PlayerInfoVO> getPlayerInfoByNameInitial(String initial)
			throws RemoteException {
		return null;
	}

	public PlayerInfoVO getPlayerInfoByName(String name) throws RemoteException {
		return null;
	}

	public List<PlayerTotalVO> getPlayerTotalByName(String name)
			throws RemoteException {
		return null;
	}

	public List<PlayerPerGameVO> getPlayerPerGameByName(String name)
			throws RemoteException {
		return null;
	}

	public List<PlayerAdvancedVO> getPlayerAdvancedByName(String name)
			throws RemoteException {
		return null;
	}

	public List<PlayerSalaryVO> getPlayerSalaryByName(String name)
			throws RemoteException {
		return null;
	}

	public List<PlayerTotalVO> getPlayerTotalBySeason(String season)
			throws RemoteException {
		return null;
	}

	public List<PlayerPerGameVO> getPlayerPerGameBySeason(String season)
			throws RemoteException {
		return null;
	}

	public List<PlayerAdvancedVO> getPlayerAdvancedBySeason(String season)
			throws RemoteException {
		return null;
	}

	@Override
	public List<ImageIcon> getPlayerPortraitByNameList(List<String> names)
			throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getPlayerPortraitByName(String name)
			throws RemoteException {
		return null;
	}

}
