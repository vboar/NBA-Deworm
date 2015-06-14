package service;

import java.awt.MediaTracker;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import util.Utility;
import vo.TeamWinAnalysisVO;
import dao.MatchDao;
import dao.impl.DaoFactoryImpl;
import entity.MatchInfo;
import entity.MatchPlayerBasic;

/**
 * 推断统计
 * 
 * created by JaneLDQ on 2015年6月14日 下午3:42:30
 */
public class InferenceStatsServiceImpl implements InferenceStatsService {

	private MatchDao mdao;

	private String[] teams = { "ATL", "BOS", "NJN", "CHA", "CHI", "CLE", "DAL",
			"DEN", "DET", "GSW", "HOU", "IND", "LAC", "LAL", "MEM", "MIA",
			"MIL", "MIN", "NOH", "NYK", "OKC", "ORL", "PHI", "PHO", "POR",
			"SAC", "SAS", "TOR", "UTA", "WAS" };

	private String[] fields = { "pts", "ast", "blk", "stl", "trb", "tov", "pf" };

	private String[] seasons = { "14-15", "13-14", "12-13", "10-11", "09-10",
			"08-09", "07-08", "06-07", "05-06", "04-05" };

	private String testing_path = "stats/team_testing.txt";

	public InferenceStatsServiceImpl() {
		mdao = DaoFactoryImpl.getDaoFactory().getMatchDao();
	}

	// TODO ---------main----------------
	public static void main(String[] args) {
		InferenceStatsServiceImpl is = new InferenceStatsServiceImpl();
		is.getTeamStepwiseMatchToTxt("14-15");
	}

	@Override
	public void getTeamStepwiseToTxt(String season) {
		List<List<String>> strs = getStepwistString(season);
		for (int i = 0; i < strs.size(); ++i) {
			Utility.writeMulti(strs.get(i), "stats/" + fields[i] + ".txt");
		}
	}

	@Override
	public void getTeamStepwiseToTxt_10() {
		List<List<String>> list = new ArrayList<List<String>>();
		for (int i = 0; i < 7; ++i) {
			String home = "";
			String guest = "";
			List<String> s = new ArrayList<String>();
			s.add(home);
			s.add(guest);
			list.add(s);
		}
		for (int i = 0; i < seasons.length; ++i) {
			List<List<String>> single = getStepwistString(seasons[i]);
			for (int j = 0; j < 7; ++j) {
				String home = list.get(j).get(0);
				String guest = list.get(j).get(1);
				home += single.get(j).get(0) + ";";
				guest += single.get(j).get(1) + ";";
				list.get(j).set(0, home);
				list.get(j).set(1, guest);
			}
		}
		for (int i = 0; i < 7; ++i) {
			String h = checkString(list.get(i).get(0));
			String g = checkString(list.get(i).get(1));
			List<String> strs = new ArrayList<String>();
			strs.add(h);
			strs.add(g);
			Utility.writeMulti(strs, "stats/" + fields[i] + ".txt");
		}
	}

	@Override
	public void getTeamStepwiseMatchToTxt(String season) {
		List<MatchPlayerBasic> homelist = mdao.getGuestHomeTeamTotalBySeason(
				season, true);
		List<MatchPlayerBasic> guestlist = mdao.getGuestHomeTeamTotalBySeason(
				season, false);
		String[] pts = { "", "" };
		String[] ast = { "", "" };
		String[] blk = { "", "" };
		String[] stl = { "", "" };
		String[] trb = { "", "" };
		String[] tov = { "", "" };
		String[] pf = { "", "" };
		for (int i = 0; i < homelist.size(); ++i) {
			MatchPlayerBasic h = homelist.get(i);
			MatchPlayerBasic g = guestlist.get(i);
			pts[0] += h.getPts() + ";";
			ast[0] += h.getAst() + ";";
			blk[0] += h.getBlk() + ";";
			stl[0] += h.getStl() + ";";
			trb[0] += h.getTrb() + ";";
			tov[0] += h.getTov() + ";";
			pf[0]  += h.getPf()  + ";";
			pts[1] += g.getPts() + ";";
			ast[1] += g.getAst() + ";";
			blk[1] += g.getBlk() + ";";
			stl[1] += g.getStl() + ";";
			trb[1] += g.getTrb() + ";";
			tov[1] += g.getTov() + ";";
			pf[1]  += g.getPf()  + ";";
		}
		String[][] a = {pts,ast,blk,stl,trb,tov,pf};
		for(int i=0; i<7; ++i){
			List<String> l = new ArrayList<String>();
			l.add(checkString(a[i][0]));
			l.add(checkString(a[i][1]));
			Utility.writeMulti(l, "stats/"+fields[i]+".txt");
		}
	}

	@Override
	public void getTeamWinsToTxt(String season) {
		List<String> s = getWinStringList(season);
		Utility.writeMulti(s, testing_path);
	}

	@Override
	public void getTeamWinsToTxt_10() {
		String home = "";
		String guest = "";
		for (int i = 0; i < seasons.length; ++i) {
			home += getWinStringList(seasons[i]).get(0) + ";";
			guest += getWinStringList(seasons[i]).get(1) + ";";
		}
		home = home.substring(0, home.length() - 1);
		guest = guest.substring(0, guest.length() - 1);
		List<String> strs = new ArrayList<String>();
		strs.add(home);
		strs.add(guest);
		Utility.writeMulti(strs, testing_path);
	}

	@Override
	public TeamWinAnalysisVO getTeamTestingResultBySeason(String season) {
		this.getTeamWinsToTxt(season);
		this.getTeamStepwiseToTxt(season);
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/team_testing.py");
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getWinVO();
	}

	@Override
	public TeamWinAnalysisVO getTeamTestingResult_10() {
		this.getTeamWinsToTxt_10();
		;
		this.getTeamStepwiseToTxt_10();
		try {
			Process p = Runtime.getRuntime().exec(
					"python stats/team_testing.py");
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getWinVO();
	}

	private String checkString(String str) {
		if (str.length() == 0) {
			return str;
		}
		return str.substring(0, str.length() - 1);
	}

	private TeamWinAnalysisVO getWinVO() {
		List<String> strs = Utility.read("stats/team_result.txt");
		TeamWinAnalysisVO vo = new TeamWinAnalysisVO();
		// 分析第0行数据
		String line = strs.get(0);
		String[] data = line.split(";", -1);
		vo.home_gradient = Utility.stringToDouble(data[0]);
		vo.home_intercept = Utility.stringToDouble(data[1]);
		vo.home_correlation = Utility.stringToDouble(data[2]);
		// 分析第1行数据
		line = strs.get(1);
		data = line.split(";", -1);
		vo.guest_gradient = Utility.stringToDouble(data[0]);
		vo.guest_intercept = Utility.stringToDouble(data[1]);
		vo.guest_correlation = Utility.stringToDouble(data[2]);
		// 分析第2行数据
		line = strs.get(2);
		data = line.split(";", -1);
		vo.home_D = Utility.stringToDouble(data[0]);
		vo.home_p = Utility.stringToDouble(data[1]);
		// 分析第3行数据
		line = strs.get(3);
		data = line.split(";", -1);
		vo.guest_D = Utility.stringToDouble(data[0]);
		vo.guest_p = Utility.stringToDouble(data[1]);
		// 分析第4行数据
		line = strs.get(4);
		data = line.split(";", -1);
		vo.standardKS_D = Utility.stringToDouble(data[0]);
		vo.standardKS_p = Utility.stringToDouble(data[1]);
		// 分析第5行数据
		line = strs.get(5);
		data = line.split(";", -1);
		vo.KS_D = Utility.stringToDouble(data[0]);
		vo.KS_p = Utility.stringToDouble(data[1]);
		// 分析第6行数据
		line = strs.get(6);
		data = line.split(";", -1);
		vo.home_skewness = Utility.stringToDouble(data[0]);
		vo.home_kurtosis = Utility.stringToDouble(data[1]);
		// 分析第7行数据
		line = strs.get(7);
		data = line.split(";", -1);
		vo.guest_skewness = Utility.stringToDouble(data[0]);
		vo.guest_kurtosis = Utility.stringToDouble(data[1]);
		// 分析第8行数据
		line = strs.get(8);
		data = line.split(";", -1);
		vo.t = Utility.stringToDouble(data[0]);
		vo.t_p = Utility.stringToDouble(data[1]);
		// 分析第9行数据
		line = strs.get(9);
		data = line.split(";", -1);
		vo.home_mean = Utility.stringToDouble(data[0]);
		vo.home_std = Utility.stringToDouble(data[1]);
		vo.guest_mean = Utility.stringToDouble(data[2]);
		vo.guest_std = Utility.stringToDouble(data[3]);
		// 主场胜场P-P图
		ImageIcon img = new ImageIcon("stats/home_probplot.png");
		if (img.getImageLoadStatus() == MediaTracker.ERRORED) {
			img = null;
		}
		vo.home_P_P = img;
		// 客场胜场P-P图
		img = new ImageIcon("stats/guest_probplot.png");
		if (img.getImageLoadStatus() == MediaTracker.ERRORED) {
			img = null;
		}
		vo.guest_P_P = img;
		return vo;
	}

	private List<String> getWinStringList(String season) {
		String home_Str = "";
		String guest_Str = "";
		List<MatchInfo> list = mdao.getRegularMatchInfoBySeason(season);
		int[] home_wins = new int[30];
		int[] guest_wins = new int[30];
		for (MatchInfo m : list) {
			String home = m.getHome_team();
			String guest = m.getGuest_team();
			if (m.getHome_point() > m.getGuest_point()) {
				for (int i = 0; i < teams.length; ++i) {
					if (teams[i].equals(home)) {
						home_wins[i]++;
					}
				}
			} else {
				for (int j = 0; j < teams.length; ++j) {
					if (teams[j].equals(guest)) {
						guest_wins[j]++;
					}
				}
			}
		}
		for (int i = 0; i < 30; ++i) {
			home_Str += home_wins[i] + ";";
			guest_Str += guest_wins[i] + ";";
		}

		home_Str = home_Str.substring(0, home_Str.length() - 1);
		guest_Str = guest_Str.substring(0, guest_Str.length() - 1);
		List<String> s = new ArrayList<String>();
		s.add(home_Str);
		s.add(guest_Str);
		return s;
	}

	private List<List<String>> getStepwistString(String season) {
		int[][] homes = new int[30][7];
		int[][] guests = new int[30][7];
		String[] home_str = new String[7];
		String[] guest_str = new String[7];
		for (int i = 0; i < 7; ++i) {
			home_str[i] = "";
			guest_str[i] = "";
		}
		List<MatchPlayerBasic> homelist = mdao.getGuestHomeTeamTotalBySeason(
				season, true);
		List<MatchPlayerBasic> guestlist = mdao.getGuestHomeTeamTotalBySeason(
				season, false);
		for (int k = 0; k < homelist.size(); ++k) {
			MatchPlayerBasic h = homelist.get(k);
			MatchPlayerBasic g = guestlist.get(k);
			for (int i = 0; i < teams.length; ++i) {
				if (teams[i].equals(h.getTeam_abbr())) {
					homes[i][0] += h.getPts();
					homes[i][1] += h.getAst();
					homes[i][2] += h.getBlk();
					homes[i][3] += h.getStl();
					homes[i][4] += h.getTrb();
					homes[i][5] += h.getTov();
					homes[i][6] += h.getPf();
					guests[i][0] += g.getPts();
					guests[i][1] += g.getAst();
					guests[i][2] += g.getBlk();
					guests[i][3] += g.getStl();
					guests[i][4] += g.getTrb();
					guests[i][5] += g.getTov();
					guests[i][6] += g.getPf();
				}
			}

		}
		for (int i = 0; i < 30; ++i) {
			home_str[0] += homes[i][0] + ";";
			home_str[1] += homes[i][1] + ";";
			home_str[2] += homes[i][2] + ";";
			home_str[3] += homes[i][3] + ";";
			home_str[4] += homes[i][4] + ";";
			home_str[5] += homes[i][5] + ";";
			home_str[6] += homes[i][6] + ";";
			guest_str[0] += guests[i][0] + ";";
			guest_str[1] += guests[i][1] + ";";
			guest_str[2] += guests[i][2] + ";";
			guest_str[3] += guests[i][3] + ";";
			guest_str[4] += guests[i][4] + ";";
			guest_str[5] += guests[i][5] + ";";
			guest_str[6] += guests[i][6] + ";";
		}
		List<List<String>> fieldStr = new ArrayList<List<String>>();
		for (int i = 0; i < 7; ++i) {
			List<String> strs = new ArrayList<String>();
			strs.add(checkString(home_str[i]));
			strs.add(checkString(guest_str[i]));
			fieldStr.add(strs);
		}
		return fieldStr;
	}

}
