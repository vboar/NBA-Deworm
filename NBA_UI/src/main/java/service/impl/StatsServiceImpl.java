package service.impl;

import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;

import service.StatsService;

public class StatsServiceImpl implements StatsService {

	@Override
	public ImageIcon getPlayerRadar(String name, String season, int regular)
			throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getPlayerCompareRadar(String playerA, String playerB,
			String season, int regular) throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getPlayerCareerLineChart(String player, int fieldNum,
			int regular) throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getPlayerBasicCompareBarChart(String playerA,
			String playerB, String season, List<Integer> fields, int regular)
			throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getPlayerAdvancedCompareBarChart(String playerA,
			String playerB, String season, List<Integer> fields, int regular)
			throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getPlayerPctCompareBarChart(String playerA,
			String playerB, String season, List<Integer> fields, int regular)
			throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getTeamRadar(String team, String season)
			throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getTeamCompareRadar(String teamA, String teamB,
			String season) throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getTeamCompareRadarByGameId(String gameid)
			throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getTeamCareerLineChart(String team, int fieldNum)
			throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getTeamBasicCompareBarChart(String teamA, String teamB,
			String season, List<Integer> field) throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getTeamAdvancedCompareBarChart(String teamA, String teamB,
			String season, List<Integer> field) throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getTeamPctCompareBarChart(String teamA, String teamB,
			String season, List<Integer> field) throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getMatchPlayerLineChart(String name, String season,
			int fieldNum) throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getMatchTeamLineChart(String abbr, String season, int field)
			throws RemoteException {
		return null;
	}

	@Override
	public ImageIcon getPlayerContribution(String abbr, String season)
			throws RemoteException {
		return null;
	}

}
