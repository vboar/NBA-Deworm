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
import dao.impl.DaoFactoryImpl;
import entity.PlayerStatsAdvanced;
import entity.PlayerStatsPerGame;

/**
 * Created by Vboar on 2015/6/11.
 */
public class StatsServiceImpl extends UnicastRemoteObject implements
		StatsService {

	private static final long serialVersionUID = 1L;
	private PlayerDao pdao;

	public StatsServiceImpl() throws RemoteException {
		super();
		pdao = DaoFactoryImpl.getDaoFactory().getPlayerDao();
	}

	@Override
	public ImageIcon getPlayerRadar(String name, String season, int regular)
			throws RemoteException {
		PlayerStatsPerGame ps = getPlayerPerGameFromList(pdao
				.getPlayerPerGameBySeasonName(season, name, regular));
		String path = "stats/PlayerRadar";
		String s = name + ";" + season + ";" + regular + ";" + ps.getTrb()
				+ ";" + ps.getAst() + ";" + ps.getStl() + ";" + ps.getBlk()
				+ ";" + ps.getTov() + ";" + ps.getPf();
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
		String path = "stats/PlayerComparisionRadar";
		String sa = playerA + ";" + season + ";" + regular + ";" + pa.getTrb()
				+ ";" + pa.getAst() + ";" + pa.getStl() + ";" + pa.getBlk()
				+ ";" + pa.getTov() + ";" + pa.getPf();
		String sb = playerB + ";" + season + ";" + regular + ";" + pb.getTrb()
				+ ";" + pb.getAst() + ";" + pb.getStl() + ";" + pb.getBlk()
				+ ";" + pb.getTov() + ";" + pb.getPf();
		List<String> slist = new ArrayList<String>();
		slist.add(sa);
		slist.add(sb);
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
		String path = "stats/PlayerLineChart";
		List<String> strs = new ArrayList<String>();
		if (!isPlayerFieldAdvanced(field))
			strs = getPlayerBasicCareer(player, regular, field);
		else
			strs = getPlayerAdvancedCareer(player, regular, field);
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
				types += ft.toString() + ";";
				sa += pa.getPts() + ";";
				sb += pb.getPts() + ";";
				break;
			case AST:
				types += ft.toString() + ";";
				sa += pa.getAst() + ";";
				sb += pb.getAst() + ";";
				break;
			case BLK:
				types += ft.toString() + ";";
				sa += pa.getBlk() + ";";
				sb += pb.getBlk() + ";";
				break;
			case STL:
				types += ft.toString() + ";";
				sa += pa.getStl() + ";";
				sb += pb.getStl() + ";";
				break;
			case TRB:
				types += ft.toString() + ";";
				sa += pa.getTrb() + ";";
				sb += pb.getTrb() + ";";
				break;
			case DRB:
				types += ft.toString() + ";";
				sa += pa.getDrb() + ";";
				sb += pb.getDrb() + ";";
				break;
			case ORB:
				types += ft.toString() + ";";
				sa += pa.getOrb() + ";";
				sb += pb.getOrb() + ";";
				break;
			case TOV:
				types += ft.toString() + ";";
				sa += pa.getTov() + ";";
				sb += pb.getTov() + ";";
				break;
			case PF:
				types += ft.toString() + ";";
				sa += pa.getPf() + ";";
				sb += pb.getPf() + ";";
				break;
			default:
			}
		}
		strs.add(types);
		strs.add(sa);
		strs.add(sb);
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
				types += ft.toString() + ";";
				sa += pa.getPer() + ";";
				sb += pb.getPer() + ";";
				break;
			case AST_PCT:
				types += ft.toString() + ";";
				sa += pa.getAst_pct() + ";";
				sb += pb.getAst_pct() + ";";
				break;
			case BLK_PCT:
				types += ft.toString() + ";";
				sa += pa.getBlk_pct() + ";";
				sb += pb.getBlk_pct() + ";";
				break;
			case STL_PCT:
				types += ft.toString() + ";";
				sa += pa.getStl_pct() + ";";
				sb += pb.getStl_pct() + ";";
				break;
			case TRB_PCT:
				types += ft.toString() + ";";
				sa += pa.getTrb_pct() + ";";
				sb += pb.getTrb_pct() + ";";
				break;
			case DRB_PCT:
				types += ft.toString() + ";";
				sa += pa.getDrb_pct() + ";";
				sb += pb.getDrb_pct() + ";";
				break;
			case ORB_PCT:
				types += ft.toString() + ";";
				sa += pa.getOrb_pct() + ";";
				sb += pb.getOrb_pct() + ";";
				break;
			case TOV_PCT:
				types += ft.toString() + ";";
				sa += pa.getTov_pct() + ";";
				sb += pb.getTov_pct() + ";";
				break;
			case USG_PCT:
				types += ft.toString() + ";";
				sa += pa.getUsg_pct() + ";";
				sb += pb.getUsg_pct() + ";";
				break;
			default:
			}
		}
		strs.add(types);
		strs.add(sa);
		strs.add(sb);
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
				types += ft.toString() + ";";
				sa += ppA.getFg3_pct() + ";";
				sb += ppB.getFg3_pct() + ";";
				break;
			case FGA_PCT:
				types += ft.toString() + ";";
				sa += ppA.getFga_pct() + ";";
				sb += ppB.getFga_pct() + ";";
				break;
			case FT_PCT:
				types += ft.toString() + ";";
				sa += ppA.getFt_pct() + ";";
				sb += ppB.getFt_pct() + ";";
				break;
			case TS_PCT:
				types += ft.toString() + ";";
				sa += paA.getTs_pct() + ";";
				sb += paB.getTs_pct() + ";";
				break;
			default:
			}
		}
		strs.add(types);
		strs.add(sa);
		strs.add(sb);
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
					s += p.getSeason() + ";";
					value += p.getPts() + ";";
					break;
				case AST:
					s += p.getSeason() + ";";
					value += p.getAst() + ";";
					break;
				case BLK:
					s += p.getSeason() + ";";
					value += p.getBlk() + ";";
					break;
				case STL:
					s += p.getSeason() + ";";
					value += p.getStl() + ";";
					break;
				case TRB:
					s += p.getSeason() + ";";
					value += p.getTrb() + ";";
					break;
				case DRB:
					s += p.getSeason() + ";";
					value += p.getDrb() + ";";
					break;
				case ORB:
					s += p.getSeason() + ";";
					value += p.getOrb() + ";";
					break;
				case TOV:
					s += p.getSeason() + ";";
					value += p.getTov() + ";";
					break;
				case PF:
					s += p.getSeason() + ";";
					value += p.getPf() + ";";
					break;
				case FG3_PCT:
					s += p.getSeason() + ";";
					value += p.getFg3_pct() + ";";
					break;
				case FGA_PCT:
					s += p.getSeason() + ";";
					value += p.getFga_pct() + ";";
					break;
				case FT_PCT:
					s += p.getSeason() + ";";
					value += p.getFt_pct() + ";";
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
					s += p.getSeason() + ";";
					value += p.getPer() + ";";
					break;
				case AST_PCT:
					s += p.getSeason() + ";";
					value += p.getAst_pct() + ";";
					break;
				case BLK_PCT:
					s += p.getSeason() + ";";
					value += p.getBlk_pct() + ";";
					break;
				case STL_PCT:
					s += p.getSeason() + ";";
					value += p.getStl_pct() + ";";
					break;
				case TRB_PCT:
					s += p.getSeason() + ";";
					value += p.getTrb_pct() + ";";
					break;
				case DRB_PCT:
					s += p.getSeason() + ";";
					value += p.getDrb_pct() + ";";
					break;
				case ORB_PCT:
					s += p.getSeason() + ";";
					value += p.getOrb_pct() + ";";
					break;
				case TOV_PCT:
					s += p.getSeason() + ";";
					value += p.getTov_pct() + ";";
					break;
				case TS_PCT:
					s += p.getSeason() + ";";
					value += p.getTs_pct() + ";";
					break;
				case USG_PCT:
					s += p.getSeason() + ";";
					value += p.getUsg_pct() + ";";
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
	private boolean isPlayerFieldAdvanced(FieldType field) {
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
