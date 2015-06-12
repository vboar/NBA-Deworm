package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.swing.ImageIcon;

import service.PlayerService;
import util.FieldType;
import vo.HotPlayerInfoVO;
import vo.PlayerAdvancedVO;
import vo.PlayerFilter;
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

	@Override
	public List<PlayerPerGameVO> getPlayerPerGameByFilter(PlayerFilter filter)
			throws RemoteException {
		return null;
	}

	@Override
	public List<PlayerTotalVO> getPlayerTotalByFilter(PlayerFilter filter)
			throws RemoteException {
		return null;
	}

	@Override
	public List<PlayerAdvancedVO> getPlayerAdvancedByFilter(PlayerFilter filter)
			throws RemoteException {
		return null;
	}

	@Override
	public List<PlayerInfoVO> getTeamPlayerBySeason(String season, String abbr)
			throws RemoteException {
		return null;
	}

	@Override
	public List<String> getNameList(String str) throws RemoteException {
		return null;
	}

	@Override
	public List<HotPlayerInfoVO> getSeasonHotPlayer(String season,
			FieldType field, int num) throws RemoteException {
		return null;
	}

	@Override
	public List<PlayerInfoVO> getAllPlayerInfo() throws RemoteException {
		return null;
	}

	@Override
	public List<String> getNameByNameInitial(String initial)
			throws RemoteException {
		return null;
	}

	@Override
	public List<PlayerTotalVO> getPlayerTotalBySeasonName(String season,
			String name, int regular) throws RemoteException {
		return null;
	}

	@Override
	public List<PlayerPerGameVO> getPlayerPerGameBySeasonName(String season,
			String name, int regular) throws RemoteException {
		return null;
	}

	@Override
	public List<PlayerAdvancedVO> getPlayerAdvancedBySeasonName(String season,
			String name, int regular) throws RemoteException {
		return null;
	}

	@Override
	public List<PlayerSalaryVO> getPlayerSalaryBySeason(String season, String name)
			throws RemoteException {
		return null;
	}

	@Override
	public List<String> getTeamByPlayerNameSeason(String name, String season)
			throws RemoteException {
		return null;
	}

	@Override
	public PlayerInfoVO getPlayerInfoByName(String name) throws RemoteException {
		return null;
	}

	@Override
	public List<PlayerTotalVO> getPlayerTotalByName(String name, int regular)
			throws RemoteException {
		return null;
	}

	@Override
	public List<PlayerTotalVO> getPlayerTotalBySeason(String season, int regular)
			throws RemoteException {
		return null;
	}

	@Override
	public List<PlayerPerGameVO> getPlayerPerGameByName(String name, int regular)
			throws RemoteException {
		return null;
	}

	@Override
	public List<PlayerPerGameVO> getPlayerPerGameBySeason(String season,
			int regular) throws RemoteException {
		return null;
	}

	@Override
	public List<PlayerAdvancedVO> getPlayerAdvancedByName(String name,
			int regular) throws RemoteException {
		return null;
	}

	@Override
	public List<PlayerAdvancedVO> getPlayerAdvancedBySeason(String season,
			int regular) throws RemoteException {
		return null;
	}

	@Override
	public List<PlayerSalaryVO> getPlayerSalaryByName(String name)
			throws RemoteException {
		return null;
	}

}
