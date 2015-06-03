package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.swing.ImageIcon;

import service.TeamService;
import vo.TeamInfoVO;
import vo.TeamOppPerGameVO;
import vo.TeamOppTotalVO;
import vo.TeamPerGameVO;
import vo.TeamTotalVO;

/**
 * TeamService的具体实现
 * 
 * created by JaneLDQ on 2015年5月31日 下午8:27:03
 */
public class TeamServiceImpl extends UnicastRemoteObject implements TeamService {

	private static final long serialVersionUID = 1L;
	
	public TeamServiceImpl() throws RemoteException {
		super();
	}

	public List<TeamInfoVO> getAllTeamInfo() throws RemoteException {
		return null;
	}

	public List<TeamTotalVO> getTeamTotalBySeason(String season)
			throws RemoteException {
		return null;
	}

	public List<TeamPerGameVO> getTeamPerGameBySeason(String season)
			throws RemoteException {
		return null;
	}

	public List<TeamOppTotalVO> getTeamOppTotalBySeason(String season)
			throws RemoteException {
		return null;
	}

	public List<TeamOppPerGameVO> getTeamOppPerGameBySeason(String season)
			throws RemoteException {
		return null;
	}

	public List<TeamTotalVO> getTeamTotalByAbbr(String abbr)
			throws RemoteException {
		return null;
	}

	public List<TeamPerGameVO> getTeamPerGameByAbbr(String abbr)
			throws RemoteException {
		return null;
	}

	public List<TeamOppTotalVO> getTeamOppTotalByAbbr(String abbr)
			throws RemoteException {
		return null;
	}

	public List<TeamOppPerGameVO> getTeamOppPerGameByAbbr(String abbr)
			throws RemoteException {
		return null;
	}

	@Override
	public List<ImageIcon> getAllTeamLogo() throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getTeamLogoByAbbr(String abbr) throws RemoteException {
		return null;
	}

}
