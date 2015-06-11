package service.impl;

import java.awt.MediaTracker;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import service.StatsService;
import util.FieldType;
import dao.PlayerDao;
import dao.TeamDao;
import dao.impl.DaoFactoryImpl;
import entity.PlayerStatsAdvanced;
import entity.PlayerStatsPerGame;
import entity.TeamStatsAdvanced;
import entity.TeamStatsPerGame;

/**
 * Created by Vboar on 2015/6/11.
 */
public class StatsServiceImpl extends UnicastRemoteObject implements
		StatsService {

	private static final long serialVersionUID = 1L;
	private PlayerDao pdao;
	private TeamDao tdao;

	public StatsServiceImpl() throws RemoteException {
		super();
		pdao = DaoFactoryImpl.getDaoFactory().getPlayerDao();
		tdao = DaoFactoryImpl.getDaoFactory().getTeamDao();
	}

	public static void main(String[] args) {
		
		try {
			StatsService ss = new StatsServiceImpl();
			ss.getTeamRadar("ATL", "13-14");
			ss.getTeamCareerLineChar("ATL", FieldType.ORB);
			ss.getTeamCompareRadar("ATL", "BOS", "13-14");
			List<FieldType> la = new ArrayList<FieldType>();
			la.add(FieldType.AST);
			la.add(FieldType.STL);
			la.add(FieldType.TRB);
			la.add(FieldType.BLK);
			ss.getTeamBasicCompareBarChar("ATL", "BOS", "13-14", la);
			la = new ArrayList<FieldType>();
			la.add(FieldType.FG3_PCT);
			la.add(FieldType.FGA_PCT);
			la.add(FieldType.FT_PCT);
			ss.getTeamPctCompareBarChart("ATL", "BOS", "78-79", la);
			la = new ArrayList<FieldType>();
			la.add(FieldType.DRB_PCT);
			la.add(FieldType.ORB_PCT);
			la.add(FieldType.OFF_RTG);
			la.add(FieldType.DEF_RTG);
			ss.getTeamAdvancedCompareBarChar("ATL", "BOS", "13-14", la);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public ImageIcon getPlayerRadar(String name, String season, int regular)
			throws RemoteException {
		PlayerStatsPerGame ps = getPlayerPerGameFromList(pdao
				.getPlayerPerGameBySeasonName(season, name, regular));
		String s = name + ";" + season + ";" + regular + ";" + ps.getTrb()
				+ ";" + ps.getAst() + ";" + ps.getStl() + ";" + ps.getBlk()
				+ ";" + ps.getTov() + ";" + ps.getPf();
		
		//将数据写入文件
		String path = "stats/PlayerRadar";
		write(s, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/player_radar.py");
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageIcon img = new ImageIcon(path + ".png");
		if (img.getImageLoadStatus() == MediaTracker.ERRORED)
			return null;
		return img;
	}

	@Override
	public ImageIcon getPlayerCompareRadar(String playerA, String playerB,
			String season, int regular) throws RemoteException {
		PlayerStatsPerGame pa = getPlayerPerGameFromList(pdao
				.getPlayerPerGameBySeasonName(season, playerA, regular));
		PlayerStatsPerGame pb = getPlayerPerGameFromList(pdao
				.getPlayerPerGameBySeasonName(season, playerB, regular));
		String sa = playerA + ";" + season + ";" + regular + ";" + pa.getTrb()
				+ ";" + pa.getAst() + ";" + pa.getStl() + ";" + pa.getBlk()
				+ ";" + pa.getTov() + ";" + pa.getPf();
		String sb = playerB + ";" + season + ";" + regular + ";" + pb.getTrb()
				+ ";" + pb.getAst() + ";" + pb.getStl() + ";" + pb.getBlk()
				+ ";" + pb.getTov() + ";" + pb.getPf();
		List<String> slist = new ArrayList<String>();
		slist.add(sa);
		slist.add(sb);
		
		//将数据写入文件
		String path = "stats/PlayerComparisionRadar";
		writeMulti(slist, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/player_radar_compare.py");
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageIcon img = new ImageIcon(path + ".png");
		if (img.getImageLoadStatus() == MediaTracker.ERRORED)
			return null;
		return img;
	}

	@Override
	public ImageIcon getPlayerCareerLineChart(String player, FieldType field,
			int regular) throws RemoteException {

		List<String> strs = new ArrayList<String>();
		if (!isFieldAdvanced(field))
			strs = getPlayerBasicCareer(player, regular, field);
		else
			strs = getPlayerAdvancedCareer(player, regular, field);
		
		//将数据写入文件
		String path = "stats/PlayerLineChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/player_linechart.py");
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageIcon img = new ImageIcon(path + ".png");
		if (img.getImageLoadStatus() == MediaTracker.ERRORED)
			return null;
		return img;
	}

	@Override
	public ImageIcon getPlayerBasicCompareBarChart(String playerA,
			String playerB, String season, List<FieldType> fields, int regular)
			throws RemoteException {
		PlayerStatsPerGame pa = getPlayerPerGameFromList(pdao
				.getPlayerPerGameBySeasonName(season, playerA, regular));
		PlayerStatsPerGame pb = getPlayerPerGameFromList(pdao
				.getPlayerPerGameBySeasonName(season, playerB, regular));
		List<String> strs = new ArrayList<String>();
		strs.add(playerA + ";" + playerB + ";" + season + ";" + regular);
		String types = "";
		String sa = "";
		String sb = "";
		for (FieldType ft : fields) {
			switch (ft) {
			case PTS:
				if (pa.getPts() != null && pb.getPts() != null) {
					types += ft.toString() + ";";
					sa += pa.getPts() + ";";
					sb += pb.getPts() + ";";
				}
				break;
			case AST:
				if (pa.getAst() != null && pb.getAst() != null) {
					types += ft.toString() + ";";
					sa += pa.getAst() + ";";
					sb += pb.getAst() + ";";
				}
				break;
			case BLK:
				if (pa.getBlk() != null && pb.getBlk() != null) {
					types += ft.toString() + ";";
					sa += pa.getBlk() + ";";
					sb += pb.getBlk() + ";";
				}
				break;
			case STL:
				if (pa.getStl() != null && pb.getStl() != null) {
					types += ft.toString() + ";";
					sa += pa.getStl() + ";";
					sb += pb.getStl() + ";";
				}
				break;
			case TRB:
				if (pa.getTrb() != null && pb.getTrb() != null) {
					types += ft.toString() + ";";
					sa += pa.getTrb() + ";";
					sb += pb.getTrb() + ";";
				}
				break;
			case DRB:
				if (pa.getDrb() != null && pb.getDrb() != null) {
					types += ft.toString() + ";";
					sa += pa.getDrb() + ";";
					sb += pb.getDrb() + ";";
				}
				break;
			case ORB:
				if (pa.getOrb() != null && pb.getOrb() != null) {
					types += ft.toString() + ";";
					sa += pa.getOrb() + ";";
					sb += pb.getOrb() + ";";
				}
				break;
			case TOV:
				if (pa.getTov() != null && pb.getTov() != null) {
					types += ft.toString() + ";";
					sa += pa.getTov() + ";";
					sb += pb.getTov() + ";";
				}
				break;
			case PF:
				if (pa.getPf() != null && pb.getPf() != null) {
					types += ft.toString() + ";";
					sa += pa.getPf() + ";";
					sb += pb.getPf() + ";";
				}
				break;
			default:
			}
		}
		strs.add(types);
		strs.add(sa);
		strs.add(sb);
		
		//将数据写入文件
		String path = "stats/PlayerCompareLineChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/player_linechart.py");
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageIcon img = new ImageIcon(path + ".png");
		if (img.getImageLoadStatus() == MediaTracker.ERRORED)
			return null;
		return img;
	}

	@Override
	public ImageIcon getPlayerAdvancedCompareBarChart(String playerA,
			String playerB, String season, List<FieldType> fields, int regular)
			throws RemoteException {
		PlayerStatsAdvanced pa = getPlayerAdvancedFromList(pdao
				.getPlayerAdvancedBySeasonName(season, playerA, regular));
		PlayerStatsAdvanced pb = getPlayerAdvancedFromList(pdao
				.getPlayerAdvancedBySeasonName(season, playerB, regular));
		List<String> strs = new ArrayList<String>();
		strs.add(playerA + ";" + playerB + ";" + season + ";" + regular);
		String types = "";
		String sa = "";
		String sb = "";
		for (FieldType ft : fields) {
			switch (ft) {
			case PER:
				if (pa.getPer() != null && pb.getPer() != null) {
					types += ft.toString() + ";";
					sa += pa.getPer() + ";";
					sb += pb.getPer() + ";";
				}
				break;
			case AST_PCT:
				if (pa.getAst_pct() != null && pb.getAst_pct() != null) {
					types += ft.toString() + ";";
					sa += pa.getAst_pct() + ";";
					sb += pb.getAst_pct() + ";";
				}
				break;
			case BLK_PCT:
				if (pa.getBlk_pct() != null && pb.getBlk_pct() != null) {
					types += ft.toString() + ";";
					sa += pa.getBlk_pct() + ";";
					sb += pb.getBlk_pct() + ";";
				}
				break;
			case STL_PCT:
				if (pa.getStl_pct() != null && pb.getStl_pct() != null) {
					types += ft.toString() + ";";
					sa += pa.getStl_pct() + ";";
					sb += pb.getStl_pct() + ";";
				}
				break;
			case TRB_PCT:
				if (pa.getTrb_pct() != null && pb.getTrb_pct() != null) {
					types += ft.toString() + ";";
					sa += pa.getTrb_pct() + ";";
					sb += pb.getTrb_pct() + ";";
				}
				break;
			case DRB_PCT:
				if (pa.getDrb_pct() != null && pb.getDrb_pct() != null) {
					types += ft.toString() + ";";
					sa += pa.getDrb_pct() + ";";
					sb += pb.getDrb_pct() + ";";
				}
				break;
			case ORB_PCT:
				if (pa.getOrb_pct() != null && pb.getOrb_pct() != null) {
					types += ft.toString() + ";";
					sa += pa.getOrb_pct() + ";";
					sb += pb.getOrb_pct() + ";";
				}
				break;
			case TOV_PCT:
				if (pa.getTov_pct() != null && pb.getTov_pct() != null) {
					types += ft.toString() + ";";
					sa += pa.getTov_pct() + ";";
					sb += pb.getTov_pct() + ";";
				}
				break;
			case USG_PCT:
				if (pa.getUsg_pct() != null && pb.getUsg_pct() != null) {
					types += ft.toString() + ";";
					sa += pa.getUsg_pct() + ";";
					sb += pb.getUsg_pct() + ";";
				}
				break;
			default:
			}
		}
		strs.add(types);
		strs.add(sa);
		strs.add(sb);
		
		//将数据写入文件
		String path = "stats/PlayerCompareLineChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/player_linechart.py");
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageIcon img = new ImageIcon(path + ".png");
		if (img.getImageLoadStatus() == MediaTracker.ERRORED)
			return null;
		return img;
	}

	@Override
	public ImageIcon getPlayerPctCompareBarChart(String playerA,
			String playerB, String season, List<FieldType> fields, int regular)
			throws RemoteException {
		PlayerStatsAdvanced paA = getPlayerAdvancedFromList(pdao
				.getPlayerAdvancedBySeasonName(season, playerA, regular));
		PlayerStatsAdvanced paB = getPlayerAdvancedFromList(pdao
				.getPlayerAdvancedBySeasonName(season, playerB, regular));
		PlayerStatsPerGame ppA = getPlayerPerGameFromList(pdao
				.getPlayerPerGameBySeasonName(season, playerA, regular));
		PlayerStatsPerGame ppB = getPlayerPerGameFromList(pdao
				.getPlayerPerGameBySeasonName(season, playerB, regular));
		List<String> strs = new ArrayList<String>();
		strs.add(playerA + ";" + playerB + ";" + season + ";" + regular);
		String types = "";
		String sa = "";
		String sb = "";
		for (FieldType ft : fields) {
			switch (ft) {
			case FG3_PCT:
				if (ppA.getFg3_pct() != null && ppB.getFg3_pct() != null) {
					types += ft.toString() + ";";
					sa += ppA.getFg3_pct() + ";";
					sb += ppB.getFg3_pct() + ";";
				}
				break;
			case FGA_PCT:
				if (ppA.getFga_pct() != null && ppB.getFga_pct() != null) {
					types += ft.toString() + ";";
					sa += ppA.getFga_pct() + ";";
					sb += ppB.getFga_pct() + ";";
				}
				break;
			case FT_PCT:
				if (ppA.getFt_pct() != null && ppB.getFt_pct() != null) {
					types += ft.toString() + ";";
					sa += ppA.getFt_pct() + ";";
					sb += ppB.getFt_pct() + ";";
				}
				break;
			case TS_PCT:
				if (paA.getTs_pct() != null && paB.getTs_pct() != null) {
					types += ft.toString() + ";";
					sa += paA.getTs_pct() + ";";
					sb += paB.getTs_pct() + ";";
				}
				break;
			default:
			}
		}
		strs.add(types);
		strs.add(sa);
		strs.add(sb);
		
		//将数据写入文件
		String path = "stats/PlayerCompareLineChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/player_linechart.py");
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageIcon img = new ImageIcon(path + ".png");
		if (img.getImageLoadStatus() == MediaTracker.ERRORED)
			return null;
		return img;
	}

	@Override
	public ImageIcon getTeamRadar(String team, String season)
			throws RemoteException {
		TeamStatsPerGame tsp = tdao.getTeamPerGameBySeasonAbbr(season, team);
		String s = team + ";" + season + ";" + tsp.getTrb() + ";"
				+ tsp.getAst() + ";" + tsp.getStl() + ";" + tsp.getBlk() + ";"
				+ tsp.getTov() + ";" + tsp.getPf();
		
		//将数据写入文件
		String path = "stats/TeamRadar";
		write(s, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec("python stats/team_radar.py");
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageIcon img = new ImageIcon(path + ".png");
		if (img.getImageLoadStatus() == MediaTracker.ERRORED)
			return null;
		return img;
	}

	@Override
	public ImageIcon getTeamCompareRadar(String teamA, String teamB,
			String season) throws RemoteException {
		TeamStatsPerGame ta = tdao.getTeamPerGameBySeasonAbbr(season, teamA);
		TeamStatsPerGame tb = tdao.getTeamPerGameBySeasonAbbr(season, teamB);
		String sa = teamA + ";" + season + ";" + ta.getTrb() + ";"
				+ ta.getAst() + ";" + ta.getStl() + ";" + ta.getBlk() + ";"
				+ ta.getTov() + ";" + ta.getPf();
		String sb = teamB + ";" + season + ";" + tb.getTrb() + ";"
				+ tb.getAst() + ";" + tb.getStl() + ";" + tb.getBlk() + ";"
				+ tb.getTov() + ";" + tb.getPf();
		List<String> slist = new ArrayList<String>();
		slist.add(sa);
		slist.add(sb);
		
		//将数据写入文件
		String path = "stats/TeamComparisionRadar";
		writeMulti(slist, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/team_radar_compare.py");
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageIcon img = new ImageIcon(path + ".png");
		if (img.getImageLoadStatus() == MediaTracker.ERRORED)
			return null;
		return img;
	}

	@Override
	public ImageIcon getTeamCareerLineChar(String team, FieldType field)
			throws RemoteException {
		List<String> strs = new ArrayList<String>();
		if (!isFieldAdvanced(field))
			strs = getTeamBasicCareer(team, field);
		else
			strs = getTeamAdvancedCareer(team, field);
		
		//将数据写入文件
		String path = "stats/TeamCareerLineChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/team_linechart.py");
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageIcon img = new ImageIcon(path + ".png");
		if (img.getImageLoadStatus() == MediaTracker.ERRORED)
			return null;
		return img;
	}
	
	@Override
	public ImageIcon getTeamBasicCompareBarChar(String teamA, String teamB,
			String season, List<FieldType> field) throws RemoteException {
		TeamStatsPerGame ta = tdao.getTeamPerGameBySeasonAbbr(season, teamA);
		TeamStatsPerGame tb = tdao.getTeamPerGameBySeasonAbbr(season, teamB);
		List<String> strs = new ArrayList<String>();
		strs.add(teamA + ";" + teamB + ";" + season);
		String types = "";
		String sa = "";
		String sb = "";
		for (FieldType ft : field) {
			switch (ft) {
			case PTS:
				if (ta.getPts() != null && tb.getPts() != null) {
					types += ft.toString() + ";";
					sa += ta.getPts() + ";";
					sb += tb.getPts() + ";";
				}
				break;
			case AST:
				if (ta.getAst() != null && tb.getAst() != null) {
					types += ft.toString() + ";";
					sa += ta.getAst() + ";";
					sb += tb.getAst() + ";";
				}
				break;
			case BLK:
				if (ta.getBlk() != null && tb.getBlk() != null) {
					types += ft.toString() + ";";
					sa += ta.getBlk() + ";";
					sb += tb.getBlk() + ";";
				}
				break;
			case STL:
				if (ta.getStl() != null && tb.getStl() != null) {
					types += ft.toString() + ";";
					sa += ta.getStl() + ";";
					sb += tb.getStl() + ";";
				}
				break;
			case TRB:
				if (ta.getTrb() != null && tb.getTrb() != null) {
					types += ft.toString() + ";";
					sa += ta.getTrb() + ";";
					sb += tb.getTrb() + ";";
				}
				break;
			case DRB:
				if (ta.getDrb() != null && tb.getDrb() != null) {
					types += ft.toString() + ";";
					sa += ta.getDrb() + ";";
					sb += tb.getDrb() + ";";
				}
				break;
			case ORB:
				if (ta.getOrb() != null && tb.getOrb() != null) {
					types += ft.toString() + ";";
					sa += ta.getOrb() + ";";
					sb += tb.getOrb() + ";";
				}
				break;
			case TOV:
				if (ta.getTov() != null && tb.getTov() != null) {
					types += ft.toString() + ";";
					sa += ta.getTov() + ";";
					sb += tb.getTov() + ";";
				}
				break;
			case PF:
				if (ta.getPf() != null && tb.getPf() != null) {
					types += ft.toString() + ";";
					sa += ta.getPf() + ";";
					sb += tb.getPf() + ";";
				}
				break;
			default:
			}
		}
		strs.add(types);
		strs.add(sa);
		strs.add(sb);
		
		//将数据写入文件
		String path = "stats/TeamBasicCompareLineChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/team_linechart.py");
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageIcon img = new ImageIcon(path + ".png");
		if (img.getImageLoadStatus() == MediaTracker.ERRORED)
			return null;
		return img;
	}

	@Override
	public ImageIcon getTeamAdvancedCompareBarChar(String teamA, String teamB,
			String season, List<FieldType> field) throws RemoteException {
		TeamStatsAdvanced ta = tdao.getTeamAdvancedBySeasonAbbr(season, teamA);
		TeamStatsAdvanced tb = tdao.getTeamAdvancedBySeasonAbbr(season, teamB);
		List<String> strs = new ArrayList<String>();
		strs.add(teamA + ";" + teamB + ";" + season);
		String types = "";
		String sa = "";
		String sb = "";
		for (FieldType ft : field) {
			switch (ft) {
			case ORB_PCT:
				if (ta.getOrb_pct() != null && tb.getOrb_pct() != null) {
					types += ft.toString() + ";";
					sa += ta.getOrb_pct() + ";";
					sb += tb.getOrb_pct() + ";";
				}
				break;
			case DRB_PCT:
				if (ta.getDrb_pct() != null && tb.getDrb_pct() != null) {
					types += ft.toString() + ";";
					sa += ta.getDrb_pct() + ";";
					sb += tb.getDrb_pct() + ";";
				}
				break;
			case OFF_RTG:
				if (ta.getOff_rtg() != null && tb.getOff_rtg() != null) {
					types += ft.toString() + ";";
					sa += ta.getOff_rtg() + ";";
					sb += tb.getOff_rtg() + ";";
				}
				break;
			case DEF_RTG:
				if (ta.getDef_rtg() != null && tb.getDef_rtg() != null) {
					types += ft.toString() + ";";
					sa += ta.getDef_rtg() + ";";
					sb += tb.getDef_rtg() + ";";
				}
				break;
			default:
			}
		}
		strs.add(types);
		strs.add(sa);
		strs.add(sb);
		
		//将数据写入文件
		String path = "stats/TeamAdvancedCompareLineChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/team_linechart.py");
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageIcon img = new ImageIcon(path + ".png");
		if (img.getImageLoadStatus() == MediaTracker.ERRORED)
			return null;
		return img;
	}

	@Override
	public ImageIcon getTeamPctCompareBarChart(String teamA, String teamB,
			String season, List<FieldType> field) throws RemoteException {
		TeamStatsPerGame ta = tdao.getTeamPerGameBySeasonAbbr(season, teamA);
		TeamStatsPerGame tb = tdao.getTeamPerGameBySeasonAbbr(season, teamB);
		List<String> strs = new ArrayList<String>();
		strs.add(teamA + ";" + teamB + ";" + season);
		String types = "";
		String sa = "";
		String sb = "";
		for (FieldType ft : field) {
			switch (ft) {
			case FGA_PCT:
				if (ta.getFga_pct() != null && tb.getFga_pct() != null) {
					types += ft.toString() + ";";
					sa += ta.getFga_pct() + ";";
					sb += tb.getFga_pct() + ";";
				}
				break;
			case FG3_PCT:
				if (ta.getFg3_pct() != null && tb.getFg3_pct() != null) {
					types += ft.toString() + ";";
					sa += ta.getFg3_pct() + ";";
					sb += tb.getFg3_pct() + ";";
				}
				break;
			case FT_PCT:
				if (ta.getFt_pct() != null && tb.getFt_pct() != null) {
					types += ft.toString() + ";";
					sa += ta.getFt_pct() + ";";
					sb += tb.getFt_pct() + ";";
				}
				break;
			default:
			}
		}
		strs.add(types);
		strs.add(sa);
		strs.add(sb);
		
		//将数据写入文件
		String path = "stats/TeamPctCompareLineChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/team_linechart.py");
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageIcon img = new ImageIcon(path + ".png");
		if (img.getImageLoadStatus() == MediaTracker.ERRORED)
			return null;
		return img;
	}
	
	private List<String> getTeamAdvancedCareer(String team, FieldType field) {
		List<TeamStatsAdvanced> list = tdao.getTeamAdvancedByAbbr(team);
		if (list.size() == 0)
			return null;
		String s = "";
		String value = "";
		for (TeamStatsAdvanced tsa : list) {
			switch (field) {
			case MOV:
				if (tsa.getMov() != null) {
					s += tsa.getSeason() + ";";
					value += tsa.getMov() + ";";
				}
				break;
			case SOS:
				if (tsa.getSos() != null) {
					s += tsa.getSeason() + ";";
					value += tsa.getSos() + ";";
				}
				break;
			case SRS:
				if (tsa.getSrs() != null) {
					s += tsa.getSeason() + ";";
					value += tsa.getSrs() + ";";
				}
				break;
			case ORB_PCT:
				if (tsa.getOrb_pct() != null) {
					s += tsa.getSeason() + ";";
					value += tsa.getOrb_pct() + ";";
				}
				break;
			case DRB_PCT:
				if (tsa.getDrb_pct() != null) {
					s += tsa.getSeason() + ";";
					value += tsa.getDrb_pct() + ";";
				}
				break;
			case OFF_RTG:
				if (tsa.getOrb_pct() != null) {
					s += tsa.getSeason() + ";";
					value += tsa.getOrb_pct() + ";";
				}
				break;
			case DEF_RTG:
				if (tsa.getDef_rtg() != null) {
					s += tsa.getSeason() + ";";
					value += tsa.getDef_rtg() + ";";
				}
				break;
			case OPP_TOV_PCT:
				if (tsa.getOpp_tov_pct() != null) {
					s += tsa.getSeason() + ";";
					value += tsa.getOpp_tov_pct() + ";";
				}
				break;
			case OFF_TOV_PCT:
				if (tsa.getOff_tov_pct() != null) {
					s += tsa.getSeason() + ";";
					value += tsa.getOff_tov_pct() + ";";
				}
				break;
			case OPP_EFG_PCT:
				if (tsa.getOpp_efg_pct() != null) {
					s += tsa.getSeason() + ";";
					value += tsa.getOff_efg_pct();
				}
				break;
			case OFF_EFG_PCT:
				if (tsa.getOff_efg_pct() != null) {
					s += tsa.getSeason() + ";";
					value += tsa.getOff_efg_pct();
				}
				break;
			default:
			}
		}
		s = s.substring(0, s.length() - 1);
		value = value.substring(0, value.length() - 1);
		List<String> strs = new ArrayList<String>();
		strs.add(team + ";" + field.toString());
		strs.add(s);
		strs.add(value);
		return strs;
	}

	private List<String> getTeamBasicCareer(String team, FieldType field) {
		List<TeamStatsPerGame> list = tdao.getTeamPerGameByAbbr(team);
		if (list.size() == 0)
			return null;
		String s = "";
		String value = "";
		for (TeamStatsPerGame tsp : list) {
			switch (field) {
			case PTS:
				if (tsp.getPts() != null) {
					s += tsp.getSeason() + ";";
					value += tsp.getPts() + ";";
				}
				break;
			case AST:
				if (tsp.getAst() != null) {
					s += tsp.getSeason() + ";";
					value += tsp.getAst() + ";";
				}
				break;
			case BLK:
				if (tsp.getBlk() != null) {
					s += tsp.getSeason() + ";";
					value += tsp.getBlk() + ";";
				}
				break;
			case STL:
				if (tsp.getStl() != null) {
					s += tsp.getSeason() + ";";
					value += tsp.getStl() + ";";
				}
				break;
			case TRB:
				if (tsp.getTrb() != null) {
					s += tsp.getSeason() + ";";
					value += tsp.getTrb() + ";";
				}
				break;
			case DRB:
				if (tsp.getDrb() != null) {
					s += tsp.getSeason() + ";";
					value += tsp.getDrb() + ";";
				}
				break;
			case ORB:
				if (tsp.getOrb() != null) {
					s += tsp.getSeason() + ";";
					value += tsp.getOrb() + ";";
				}
				break;
			case TOV:
				if (tsp.getTov() != null) {
					s += tsp.getSeason() + ";";
					value += tsp.getTov() + ";";
				}
				break;
			case PF:
				if (tsp.getPf() != null) {
					s += tsp.getSeason() + ";";
					value += tsp.getPf() + ";";
				}
				break;
			default:
			}
		}
		s = s.substring(0, s.length() - 1);
		value = value.substring(0, value.length() - 1);
		List<String> strs = new ArrayList<String>();
		strs.add(team + ";" + field.toString());
		strs.add(s);
		strs.add(value);
		return strs;
	}

	
	/**
	 * 写入多行数据到文本文件（覆盖）
	 * 
	 * @param path
	 *            存储路径
	 */
	private void writeMulti(List<String> list, String path) {
		try {
			FileWriter fw = new FileWriter(path, false);
			for (String s : list) {
				fw.write(s + "\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写入一行数据到文本文件（覆盖）
	 * 
	 * @param s
	 * @param path
	 */
	private void write(String s, String path) {
		try {
			FileWriter fw = new FileWriter(path, false);
			fw.write(s + "\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将基本数据转为字符串
	 */
	private List<String> getPlayerBasicCareer(String player, int regular,
			FieldType field) {
		List<PlayerStatsPerGame> list = pdao.getPlayerPerGameByName(player,
				regular);
		if (list.size() == 0)
			return null;
		list = getDistinctBasicList(list);
		String s = "";
		String value = "";
		for (PlayerStatsPerGame p : list) {
			if (!p.getSeason().equals("Career")) {
				switch (field) {
				case PTS:
					if (p.getPts() != null) {
						s += p.getSeason() + ";";
						value += p.getPts() + ";";
					}
					break;
				case AST:
					if (p.getAst() != null) {
						s += p.getSeason() + ";";
						value += p.getAst() + ";";
					}
					break;
				case BLK:
					if (p.getBlk() != null) {
						s += p.getSeason() + ";";
						value += p.getBlk() + ";";
					}
					break;
				case STL:
					if (p.getStl() != null) {
						s += p.getSeason() + ";";
						value += p.getStl() + ";";
					}
					break;
				case TRB:
					if (p.getTrb() != null) {
						s += p.getSeason() + ";";
						value += p.getTrb() + ";";
					}
					break;
				case DRB:
					if (p.getDrb() != null) {
						s += p.getSeason() + ";";
						value += p.getDrb() + ";";
					}
					break;
				case ORB:
					if (p.getOrb() != null) {
						s += p.getSeason() + ";";
						value += p.getOrb() + ";";
					}
					break;
				case TOV:
					if (p.getTov() != null) {
						s += p.getSeason() + ";";
						value += p.getTov() + ";";
					}
					break;
				case PF:
					if (p.getPf() != null) {
						s += p.getSeason() + ";";
						value += p.getPf() + ";";
					}
					break;
				case FG3_PCT:
					if (p.getFg3_pct() != null) {
						s += p.getSeason() + ";";
						value += p.getFg3_pct() + ";";
					}
					break;
				case FGA_PCT:
					if (p.getFga_pct() != null) {
						s += p.getSeason() + ";";
						value += p.getFga_pct() + ";";
					}
					break;
				case FT_PCT:
					if (p.getFt_pct() != null) {
						s += p.getSeason() + ";";
						value += p.getFt_pct() + ";";
					}
					break;
				default:
				}
			}
		}
		s = s.substring(0, s.length() - 1);
		value = value.substring(0, value.length() - 1);
		List<String> strs = new ArrayList<String>();
		strs.add(player + ";" + regular + ";" + field.toString());
		strs.add(s);
		strs.add(value);
		return strs;
	}

	/**
	 * 将高阶数据转为字符串
	 */
	private List<String> getPlayerAdvancedCareer(String player, int regular,
			FieldType field) {
		List<PlayerStatsAdvanced> list = pdao.getPlayerAdvancedByName(player,
				regular);
		if (list.size() == 0)
			return null;
		list = getDistinctAdvancedList(list);
		String s = "";
		String value = "";
		for (PlayerStatsAdvanced p : list) {
			if (!p.getSeason().equals("Career")) {
				switch (field) {
				case PER:
					if (p.getPer() != null) {
						s += p.getSeason() + ";";
						value += p.getPer() + ";";
					}
					break;
				case AST_PCT:
					if (p.getAst_pct() != null) {
						s += p.getSeason() + ";";
						value += p.getAst_pct() + ";";
					}
					break;
				case BLK_PCT:
					if (p.getBlk_pct() != null) {
						s += p.getSeason() + ";";
						value += p.getBlk_pct() + ";";
					}
					break;
				case STL_PCT:
					if (p.getStl_pct() != null) {
						s += p.getSeason() + ";";
						value += p.getStl_pct() + ";";
					}
					break;
				case TRB_PCT:
					if (p.getTrb_pct() != null) {
						s += p.getSeason() + ";";
						value += p.getTrb_pct() + ";";
					}
					break;
				case DRB_PCT:
					if (p.getDrb_pct() != null) {
						s += p.getSeason() + ";";
						value += p.getDrb_pct() + ";";
					}
					break;
				case ORB_PCT:
					if (p.getOrb_pct() != null) {
						s += p.getSeason() + ";";
						value += p.getOrb_pct() + ";";
					}
					break;
				case TOV_PCT:
					if (p.getTov_pct() != null) {
						s += p.getSeason() + ";";
						value += p.getTov_pct() + ";";
					}
					break;
				case TS_PCT:
					if (p.getTs_pct() != null) {
						s += p.getSeason() + ";";
						value += p.getTs_pct() + ";";
					}
					break;
				case USG_PCT:
					if (p.getUsg_pct() != null) {
						s += p.getSeason() + ";";
						value += p.getUsg_pct() + ";";
					}
					break;
				default:
				}
			}
		}
		s = s.substring(0, s.length() - 1);
		value = value.substring(0, value.length() - 1);
		List<String> strs = new ArrayList<String>();
		strs.add(player + ";" + regular + ";" + field.toString());
		strs.add(s);
		strs.add(value);
		return strs;
	}

	/**
	 * 去除球员场均数据列表里同赛季转队记录
	 * 
	 * @param list
	 * @return
	 */
	private List<PlayerStatsPerGame> getDistinctBasicList(
			List<PlayerStatsPerGame> list) {
		String season = list.get(0).getSeason();
		String team = list.get(0).getTeam();
		for (int i = 1; i < list.size(); ++i) {
			PlayerStatsPerGame p = list.get(i);
			if (p.getSeason().equals(season)) {
				if (!team.equals("TOT")) {
					list.remove(i - 1);
					i = i - 1;
				}
			}
			season = p.getSeason();
			team = p.getTeam();
		}
		return list;
	}

	/**
	 * 去除球员高阶数据列表中同赛季转队记录
	 * 
	 * @param list
	 * @return
	 */
	private List<PlayerStatsAdvanced> getDistinctAdvancedList(
			List<PlayerStatsAdvanced> list) {
		String season = list.get(0).getSeason();
		String team = list.get(0).getTeam();
		for (int i = 1; i < list.size(); ++i) {
			PlayerStatsAdvanced p = list.get(i);
			if (p.getSeason().equals(season)) {
				if (!team.equals("TOT")) {
					list.remove(i - 1);
					i = i - 1;
				}
			}
			season = p.getSeason();
			team = p.getTeam();
		}
		return list;
	}

	/**
	 * 获取单个球员场均数据
	 * 
	 * @param list
	 * @return
	 */
	private PlayerStatsPerGame getPlayerPerGameFromList(
			List<PlayerStatsPerGame> list) {
		PlayerStatsPerGame pp = new PlayerStatsPerGame();
		if (list.size() > 1) {
			for (PlayerStatsPerGame po : list) {
				if (po.getTeam().equals("TOT")) {
					pp = po;
					break;
				}
			}
		} else {
			pp = list.get(0);
		}
		return pp;
	}

	/**
	 * 获取单个球员高阶数据
	 * 
	 * @param list
	 * @return
	 */
	private PlayerStatsAdvanced getPlayerAdvancedFromList(
			List<PlayerStatsAdvanced> list) {
		PlayerStatsAdvanced pa = new PlayerStatsAdvanced();
		if (list.size() > 1) {
			for (PlayerStatsAdvanced po : list) {
				if (po.getTeam().equals("TOT")) {
					pa = po;
					break;
				}
			}
		} else {
			pa = list.get(0);
		}
		return pa;
	}

	/**
	 * 判断数据是否为球员高阶数据类型
	 */
	private boolean isFieldAdvanced(FieldType field) {
		switch (field) {
		case PTS:
		case AST:
		case STL:
		case BLK:
		case TRB:
		case DRB:
		case ORB:
		case TOV:
		case PF:
		case FG3_PCT:
		case FGA_PCT:
		case FT_PCT:
			return false;
		default:
			return true;
		}
	}

	
}
