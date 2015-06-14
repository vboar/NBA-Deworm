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
import vo.PlayerFilter;
import dao.MatchDao;
import dao.PlayerDao;
import dao.TeamDao;
import dao.impl.DaoFactoryImpl;
import entity.MatchPlayerAdvanced;
import entity.MatchPlayerBasic;
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
	private MatchDao mdao;

	public StatsServiceImpl() throws RemoteException {
		super();
		pdao = DaoFactoryImpl.getDaoFactory().getPlayerDao();
		tdao = DaoFactoryImpl.getDaoFactory().getTeamDao();
		mdao = DaoFactoryImpl.getDaoFactory().getMatchDao();
	}

	@Override
	public ImageIcon getPlayerRadar(String name, String season, int regular)
			throws RemoteException {
		PlayerStatsPerGame ps = getPlayerPerGameFromList(pdao
				.getPlayerPerGameBySeasonName(season, name, regular));
		if(ps==null)
			return null;
		String s = name + ";" + season + ";" + regular + ";" + ps.getTrb()
				+ ";" + ps.getAst() + ";" + ps.getStl() + ";" + ps.getBlk()
				+ ";" + ps.getTov() + ";" + ps.getPf();

		// 将数据写入文件
		String path = "stats/Radar";
		write(s, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/radar.py");
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
		if(pa==null||pb==null)
			return null;
		String sa = playerA + ";" + season + ";" + regular + ";" + pa.getTrb()
				+ ";" + pa.getAst() + ";" + pa.getStl() + ";" + pa.getBlk()
				+ ";" + pa.getTov() + ";" + pa.getPf();
		String sb = playerB + ";" + season + ";" + regular + ";" + pb.getTrb()
				+ ";" + pb.getAst() + ";" + pb.getStl() + ";" + pb.getBlk()
				+ ";" + pb.getTov() + ";" + pb.getPf();
		List<String> slist = new ArrayList<String>();
		slist.add(sa);
		slist.add(sb);

		// 将数据写入文件
		String path = "stats/RadarCompare";
		writeMulti(slist, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/radar_compare.py");
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
	public ImageIcon getPlayerCareerLineChart(String player, int fieldNum,
			int regular) throws RemoteException {
		FieldType field = FieldType.intToType(fieldNum);
		List<String> strs = new ArrayList<String>();
		if (!isFieldAdvanced(field))
			strs = getPlayerBasicCareer(player, regular, field);
		else
			strs = getPlayerAdvancedCareer(player, regular, field);

		if(strs==null)
			return null;
		// 将数据写入文件
		String path = "stats/LineChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/linechart.py");
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
			String playerB, String season, List<Integer> fieldNums, int regular)
			throws RemoteException {
		List<FieldType> fields = getTypeList(fieldNums);
		PlayerStatsPerGame pa = getPlayerPerGameFromList(pdao
				.getPlayerPerGameBySeasonName(season, playerA, regular));
		PlayerStatsPerGame pb = getPlayerPerGameFromList(pdao
				.getPlayerPerGameBySeasonName(season, playerB, regular));
		if(pa==null||pb==null)
			return null;
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
		if(types.length()>0){
		types = types.substring(0,types.length()-1);
		sa = sa.substring(0,sa.length()-1);
		sb = sb.substring(0,sb.length()-1);
		strs.add(types);
		strs.add(sa);
		strs.add(sb);
		}

		// 将数据写入文件
		String path = "stats/BarChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/barchart.py");
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
			String playerB, String season, List<Integer> fieldNums, int regular)
			throws RemoteException {
		List<FieldType> fields = getTypeList(fieldNums);
		PlayerStatsAdvanced pa = getPlayerAdvancedFromList(pdao
				.getPlayerAdvancedBySeasonName(season, playerA, regular));
		PlayerStatsAdvanced pb = getPlayerAdvancedFromList(pdao
				.getPlayerAdvancedBySeasonName(season, playerB, regular));
		if(pa==null||pb==null)
			return null;
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
		if(types.length()>0){
		types = types.substring(0,types.length()-1);
		sa = sa.substring(0,sa.length()-1);
		sb = sb.substring(0,sb.length()-1);
		strs.add(types);
		strs.add(sa);
		strs.add(sb);
		}

		// 将数据写入文件
		String path = "stats/BarChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/barchart.py");
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
			String playerB, String season, List<Integer> fieldNums, int regular)
			throws RemoteException {
		List<FieldType> fields = getTypeList(fieldNums);
		PlayerStatsAdvanced paA = getPlayerAdvancedFromList(pdao
				.getPlayerAdvancedBySeasonName(season, playerA, regular));
		PlayerStatsAdvanced paB = getPlayerAdvancedFromList(pdao
				.getPlayerAdvancedBySeasonName(season, playerB, regular));
		PlayerStatsPerGame ppA = getPlayerPerGameFromList(pdao
				.getPlayerPerGameBySeasonName(season, playerA, regular));
		PlayerStatsPerGame ppB = getPlayerPerGameFromList(pdao
				.getPlayerPerGameBySeasonName(season, playerB, regular));
		if(paA==null||paB==null||ppA==null||ppB==null)
			return null;
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
		if(types.length()>0){
		types = types.substring(0,types.length()-1);
		sa = sa.substring(0,sa.length()-1);
		sb = sb.substring(0,sb.length()-1);
		strs.add(types);
		strs.add(sa);
		strs.add(sb);
		}
		// 将数据写入文件
		String path = "stats/BarChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/barchart.py");
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
		if(tsp==null)
			return null;
		String s = team + ";" + season + ";"  + "0" + ";" + tsp.getTrb() + ";"
				+ tsp.getAst() + ";" + tsp.getStl() + ";" + tsp.getBlk() + ";"
				+ tsp.getTov() + ";" + tsp.getPf();

		// 将数据写入文件
		String path = "stats/Radar";
		write(s, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec("python stats/radar.py");
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
		if(ta==null||tb==null)
			return null;
		String sa = teamA + ";" + season + ";" + "0" + ";" + ta.getTrb() + ";"
				+ ta.getAst() + ";" + ta.getStl() + ";" + ta.getBlk() + ";"
				+ ta.getTov() + ";" + ta.getPf();
		String sb = teamB + ";" + season + ";" + "0" + ";" + tb.getTrb() + ";"
				+ tb.getAst() + ";" + tb.getStl() + ";" + tb.getBlk() + ";"
				+ tb.getTov() + ";" + tb.getPf();
		List<String> slist = new ArrayList<String>();
		slist.add(sa);
		slist.add(sb);

		// 将数据写入文件
		String path = "stats/RadarCompare";
		writeMulti(slist, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/radar_compare.py");
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
	public ImageIcon getTeamCareerLineChart(String team, int fieldNum)
			throws RemoteException {
		FieldType field = FieldType.intToType(fieldNum);
		List<String> strs = new ArrayList<String>();
		if (!isFieldAdvanced(field))
			strs = getTeamBasicCareer(team, field);
		else
			strs = getTeamAdvancedCareer(team, field);

		if(strs==null)
			return null;
		// 将数据写入文件
		String path = "stats/LineChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/linechart.py");
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
	public ImageIcon getTeamBasicCompareBarChart(String teamA, String teamB,
			String season, List<Integer> fieldNums) throws RemoteException {
		List<FieldType> field = getTypeList(fieldNums);
		TeamStatsPerGame ta = tdao.getTeamPerGameBySeasonAbbr(season, teamA);
		TeamStatsPerGame tb = tdao.getTeamPerGameBySeasonAbbr(season, teamB);
		if(ta==null||tb==null)
			return null;
		List<String> strs = new ArrayList<String>();
		strs.add(teamA + ";" + teamB + ";" + season + ";" + 1);
		String types = "";
		String sa = "";
		String sb = "";
		for (FieldType ft : field) {
			switch (ft) {
			case PTS:
				types += ft.toString() + ";";
				sa += ta.getPts() + ";";
				sb += tb.getPts() + ";";
				break;
			case AST:
				types += ft.toString() + ";";
				sa += ta.getAst() + ";";
				sb += tb.getAst() + ";";
				break;
			case BLK:
				types += ft.toString() + ";";
				sa += ta.getBlk() + ";";
				sb += tb.getBlk() + ";";
				break;
			case STL:
				types += ft.toString() + ";";
				sa += ta.getStl() + ";";
				sb += tb.getStl() + ";";
				break;
			case TRB:
				types += ft.toString() + ";";
				sa += ta.getTrb() + ";";
				sb += tb.getTrb() + ";";
				break;
			case DRB:
				types += ft.toString() + ";";
				sa += ta.getDrb() + ";";
				sb += tb.getDrb() + ";";
				break;
			case ORB:
				types += ft.toString() + ";";
				sa += ta.getOrb() + ";";
				sb += tb.getOrb() + ";";
				break;
			case TOV:
				types += ft.toString() + ";";
				sa += ta.getTov() + ";";
				sb += tb.getTov() + ";";
				break;
			case PF:
				types += ft.toString() + ";";
				sa += ta.getPf() + ";";
				sb += tb.getPf() + ";";
				break;
			default:
			}
		}
		types = types.substring(0,types.length()-1);
		sa = sa.substring(0,sa.length()-1);
		sb = sb.substring(0,sb.length()-1);
		strs.add(types);
		strs.add(sa);
		strs.add(sb);

		// 将数据写入文件
		String path = "stats/BarChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/barchart.py");
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
	public ImageIcon getTeamAdvancedCompareBarChart(String teamA, String teamB,
			String season, List<Integer> fieldNum) throws RemoteException {
		List<FieldType> field = getTypeList(fieldNum);
		TeamStatsAdvanced ta = tdao.getTeamAdvancedBySeasonAbbr(season, teamA);
		TeamStatsAdvanced tb = tdao.getTeamAdvancedBySeasonAbbr(season, teamB);
		if(ta==null||tb==null)
			return null;
		List<String> strs = new ArrayList<String>();
		strs.add(teamA + ";" + teamB + ";" + season +";"+1);
		String types = "";
		String sa = "";
		String sb = "";
		for (FieldType ft : field) {
			switch (ft) {
			case ORB_PCT:
				types += ft.toString() + ";";
				sa += ta.getOrb_pct() + ";";
				sb += tb.getOrb_pct() + ";";
				break;
			case DRB_PCT:
				types += ft.toString() + ";";
				sa += ta.getDrb_pct() + ";";
				sb += tb.getDrb_pct() + ";";
				break;
			case OFF_RTG:
				types += ft.toString() + ";";
				sa += ta.getOff_rtg() + ";";
				sb += tb.getOff_rtg() + ";";
				break;
			case DEF_RTG:
				types += ft.toString() + ";";
				sa += ta.getDef_rtg() + ";";
				sb += tb.getDef_rtg() + ";";
				break;
			default:
			}
		}
		if(types.length()>0){
		types = types.substring(0,types.length()-1);
		sa = sa.substring(0,sa.length()-1);
		sb = sb.substring(0,sb.length()-1);
		strs.add(types);
		strs.add(sa);
		strs.add(sb);
		}
		// 将数据写入文件
		String path = "stats/BarChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/barchart.py");
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
			String season, List<Integer> fieldNum) throws RemoteException {
		List<FieldType> field = getTypeList(fieldNum);
		TeamStatsPerGame ta = tdao.getTeamPerGameBySeasonAbbr(season, teamA);
		TeamStatsPerGame tb = tdao.getTeamPerGameBySeasonAbbr(season, teamB);
		if(ta==null||tb==null)
			return null;
		List<String> strs = new ArrayList<String>();
		strs.add(teamA + ";" + teamB + ";" + season + ";" + 1);
		String types = "";
		String sa = "";
		String sb = "";
		for (FieldType ft : field) {
			switch (ft) {
			case FGA_PCT:
				types += ft.toString() + ";";
				sa += ta.getFga_pct() + ";";
				sb += tb.getFga_pct() + ";";
				break;
			case FG3_PCT:
				types += ft.toString() + ";";
				sa += ta.getFg3_pct() + ";";
				sb += tb.getFg3_pct() + ";";
				break;
			case FT_PCT:
				types += ft.toString() + ";";
				sa += ta.getFt_pct() + ";";
				sb += tb.getFt_pct() + ";";
				break;
			default:
			}
		}
		types = types.substring(0,types.length()-1);
		sa = sa.substring(0,sa.length()-1);
		sb = sb.substring(0,sb.length()-1);
		strs.add(types);
		strs.add(sa);
		strs.add(sb);

		// 将数据写入文件
		String path = "stats/BarChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/barchart.py");
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
	public ImageIcon getMatchPlayerLineChart(String name, String season,
			int fieldNum) throws RemoteException {
		FieldType field = FieldType.intToType(fieldNum);
		List<String> strs = new ArrayList<String>();
		if (!isFieldAdvanced(field))
			strs = getPlayerBasicMatch(season, name, null, field);
		else
			strs = getPlayerAdvancedMatch(season, name, null, field);

		if(strs==null)
			return null;
		// 将数据写入文件
		String path = "stats/LineChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/linechart.py");
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
	public ImageIcon getMatchTeamLineChart(String abbr, String season,
			int fieldNum) throws RemoteException {
		FieldType field = FieldType.intToType(fieldNum);
		List<String> strs = new ArrayList<String>();
		if (!isFieldAdvanced(field))
			strs = getPlayerBasicMatch(season, "Team Totals", abbr, field);
		else
			strs = getPlayerAdvancedMatch(season, "Team Totals", abbr, field);

		if(strs == null)
			return null;
		// 将数据写入文件
		String path = "stats/LineChart";
		writeMulti(strs, path + ".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/linechart.py");
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
	public ImageIcon getPlayerContribution(String abbr, String season)
			throws RemoteException {
		PlayerFilter pf = new PlayerFilter();
		pf.team = abbr;
		pf.season = season;
        pf.regular = 1;
		List<PlayerStatsAdvanced> list = pdao.getPlayerAdvancedByFilter(pf);
		String pl="";
		String per="";
		for(PlayerStatsAdvanced psa:list){
			pl += psa.getName() + ";";
			per += psa.getPer() + ";";
		}
        pl = pl.substring(0, pl.length()-1);
        per = per.substring(0, per.length()-1);
		List<String> strs = new ArrayList<String>();
		strs.add(abbr+";"+season);
		strs.add(pl);
		strs.add(per);
		String path = "stats/Per";
		writeMulti(strs, path+".txt");
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/per.py");
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
		for (int i=list.size()-1 ; i>=0; --i) {
			TeamStatsPerGame tsp = list.get(i);
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
		strs.add(team + ";" + 1 +";" + field.toString());
		strs.add(s);
		strs.add(value);
		return strs;
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
		if(list.size()==0)
			return list;
		String season = list.get(0).getSeason();
		for (int i = 1; i < list.size(); ++i) {
			PlayerStatsPerGame p = list.get(i);
			if (p.getSeason().equals(season)) {
				if (!p.getTeam().equals("TOT")) {
					list.remove(i);
					i = i - 1;
				}
			}
			season = p.getSeason();
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
		if(list.size()==0)
			return list;
		String season = list.get(0).getSeason();
		for (int i = 1; i < list.size(); ++i) {
			PlayerStatsAdvanced p = list.get(i);
			if (p.getSeason().equals(season)) {
				if (!p.getTeam().equals("TOT")) {
					list.remove(i);
					i = i - 1;
				}
			}
			season = p.getSeason();
		}
		System.out.println("---------------------");
		for(PlayerStatsAdvanced p:list){
			System.out.println(p.getSeason()+" "+p.getTeam());
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
		if(list.size()==0)
			return null;
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
		if(list.size()==0)
			return null;
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

	private List<String> getPlayerAdvancedMatch(String season, String name,String abbr,FieldType field) {
		List<MatchPlayerAdvanced> list = mdao
				.getMatchPlayerAdvancedByPlayerName(name, season, abbr, -1);
		String s = "";
		String value = "";
		if(list.size()==0)
			return null;
		for (MatchPlayerAdvanced p : list) {
			switch (field) {
			case AST_PCT:
				if (p.getAst_pct() != null) {
					s += p.getDate()+ ";";
					value += p.getAst_pct() + ";";
				}
				break;
			case BLK_PCT:
				if (p.getBlk_pct() != null) {
					s += p.getDate() + ";";
					value += p.getBlk_pct() + ";";
				}
				break;
			case STL_PCT:
				if (p.getStl_pct() != null) {
					s += p.getDate() + ";";
					value += p.getStl_pct() + ";";
				}
				break;
			case TRB_PCT:
				if (p.getTrb_pct() != null) {
					s += p.getDate() + ";";
					value += p.getTrb_pct() + ";";
				}
				break;
			case DRB_PCT:
				if (p.getDrb_pct() != null) {
					s += p.getDate() + ";";
					value += p.getDrb_pct() + ";";
				}
				break;
			case ORB_PCT:
				if (p.getOrb_pct() != null) {
					s += p.getDate() + ";";
					value += p.getOrb_pct() + ";";
				}
				break;
			case TOV_PCT:
				if (p.getTov_pct() != null) {
					s += p.getDate() + ";";
					value += p.getTov_pct() + ";";
				}
				break;
			case TS_PCT:
				if (p.getTs_pct() != null) {
					s += p.getDate() + ";";
					value += p.getTs_pct() + ";";
				}
				break;
			case USG_PCT:
				if (p.getUsg_pct() != null) {
					s += p.getDate() + ";";
					value += p.getUsg_pct() + ";";
				}
				break;
			default:

			}
		}
		s = s.substring(0, s.length() - 1);
		value = value.substring(0, value.length() - 1);
		List<String> strs = new ArrayList<String>();
		strs.add(name + ";" + -1 + ";" + field.toString());
		strs.add(s);
		strs.add(value);
		return strs;
	}

	private List<String> getPlayerBasicMatch(String season, String name, 
			String abbr, FieldType field) {
		List<MatchPlayerBasic> list = mdao.getMatchPlayerBasicByPlayerName(
				name, season, abbr, -1);
		if(list.size()==0)
			return null;
		String s = "";
		String value = "";
		for (MatchPlayerBasic p : list) {
			switch (field) {
			case PTS:
				if (p.getPts() != null) {
					s += p.getDate() + ";";
					value += p.getPts() + ";";
				}
				break;
			case AST:
				if (p.getAst() != null) {
					s += p.getDate() + ";";
					value += p.getAst() + ";";
				}
				break;
			case BLK:
				if (p.getBlk() != null) {
					s += p.getDate() + ";";
					value += p.getBlk() + ";";
				}
				break;
			case STL:
				if (p.getStl() != null) {
					s += p.getDate() + ";";
					value += p.getStl() + ";";
				}
				break;
			case TRB:
				if (p.getTrb() != null) {
					s += p.getDate() + ";";
					value += p.getTrb() + ";";
				}
				break;
			case DRB:
				if (p.getDrb() != null) {
					s += p.getDate() + ";";
					value += p.getDrb() + ";";
				}
				break;
			case ORB:
				if (p.getOrb() != null) {
					s += p.getDate() + ";";
					value += p.getOrb() + ";";
				}
				break;
			case TOV:
				if (p.getTov() != null) {
					s += p.getDate() + ";";
					value += p.getTov() + ";";
				}
				break;
			case PF:
				if (p.getPf() != null) {
					s += p.getDate() + ";";
					value += p.getPf() + ";";
				}
				break;
			case FG3_PCT:
				if (p.getFg3_pct() != null) {
					s += p.getDate() + ";";
					value += p.getFg3_pct() + ";";
				}
				break;
			case FGA_PCT:
				if (p.getFga_pct() != null) {
					s += p.getDate() + ";";
					value += p.getFga_pct() + ";";
				}
				break;
			case FT_PCT:
				if (p.getFt_pct() != null) {
					s += p.getDate() + ";";
					value += p.getFt_pct() + ";";
				}
				break;
			default:
			}
		}
		s = s.substring(0, s.length() - 1);
		value = value.substring(0, value.length() - 1);
		List<String> strs = new ArrayList<String>();
		strs.add(name + ";" + -1 + ";" +field.toString());
		strs.add(s);
		strs.add(value);
		return strs;
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

	private List<FieldType> getTypeList(List<Integer> list){
		List<FieldType> fields = new ArrayList<FieldType>();
		for(Integer i: list){
			fields.add(FieldType.intToType(i));
		}
		return fields;
	}

}
