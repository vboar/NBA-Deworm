package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.swing.ImageIcon;

import service.TeamService;
import vo.HotTeamInfoVO;
import vo.TeamAdvancedVO;
import vo.TeamFilter;
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

	@Override
	public List<TeamTotalVO> getTeamTotalByFilter(TeamFilter filter)
			throws RemoteException {
		return null;
	}

	@Override
	public List<TeamPerGameVO> getTeamPerGameByFilter(TeamFilter filter)
			throws RemoteException {
		return null;
	}

	@Override
	public TeamInfoVO getTeamInfoByAbbr(String abbr) throws RemoteException {
		return null;
	}

	@Override
	public TeamTotalVO getTeamTotalBySeasonAbbr(String season, String abbr)
			throws RemoteException {
		return null;
	}

	@Override
	public TeamPerGameVO getTeamPerGameBySeasonAbbr(String season, String abbr)
			throws RemoteException {
		return null;
	}

	@Override
	public TeamOppTotalVO getTeamOppTotalBySeasonAbbr(String season, String abbr)
			throws RemoteException {
		return null;
	}

	@Override
	public TeamOppPerGameVO getTeamOppPerGameBySeasonAbbr(String season,
			String abbr) throws RemoteException {
		return null;
	}

	@Override
	public List<HotTeamInfoVO> getSeasonHotTeam(String season, int field, int number)
			throws RemoteException {
		return null;
	}

	@Override
	public List<TeamAdvancedVO> getTeamAdvancedByFilter(TeamFilter filter)
			throws RemoteException {
		return null;
	}

	@Override
	public TeamAdvancedVO getTeamAdvancedBySeasonAbbr(String season, String abbr)
			throws RemoteException {
		return null;
	}

	@Override
	public List<TeamAdvancedVO> getTeamAdvancedByAbbr(String abbr)
			throws RemoteException {
		return null;
	}

	@Override
	public List<TeamAdvancedVO> getTeamAdvancedBySeason(String season)
			throws RemoteException {
		return null;
	}

}
