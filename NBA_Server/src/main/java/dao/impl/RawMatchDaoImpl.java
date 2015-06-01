package dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import util.Utility;
import dao.RawMatchDao;
import entity.Match;
import entity.MatchPlayerAdvanced;
import entity.MatchPlayerBasic;

/**
 * 比赛原始数据处理类具体实现
 * 
 * created by JaneLDQ on 2015年5月18日 下午8:31:22
 */
public class RawMatchDaoImpl implements RawMatchDao {

	private static String MATCH_PATH = FileManager.DATA_PATH + "/matches/";

	public RawMatchDaoImpl() {
		System.out.println("Match Data：" + MATCH_PATH);
	}

	@Override
	public List<Match> getAllMatch() {
		File folder = new File(MATCH_PATH);
		String[] seasons = folder.list();
		// 存放当前有的赛季
		List<String> seasonlist = DaoFactoryImpl.getDaoFactory().getSeasonDao()
				.getAllSeason();
		List<String> newSeasonlist = new ArrayList<String>();
		List<Match> list = new ArrayList<Match>();
		for (int i = 0; i < seasons.length; ++i) {
			String path = MATCH_PATH + seasons[i] + "/";
			File fs = new File(path);
			if (!fs.isDirectory())
				continue;
			String[] names = fs.list();
			String season = seasons[i].substring(2, 5)
					+ seasons[i].substring(7);
			if (!seasonlist.contains(season))
				newSeasonlist.add(season);
			for (String name : names) {
				list.add(getEntity(season, name, path));
			}
		}
		// 插入赛季列表
		DaoFactoryImpl.getDaoFactory().getSeasonDao().insert(newSeasonlist);
		return list;
	}

	private Match getEntity(String season, String name, String path) {
		Match match = new Match();
		// 从比赛文件名中获得赛季、日期、对阵队伍名称信息
		match.setGame_id(name);
		match.setSeason(season);
		match.setDate(name.substring(0, 4) + "-" + name.substring(4, 6) + "-"
				+ name.substring(6, 8));
		match.setHome_team(name.substring(9, 12));
		match.setGuest_team(name.substring(13, 16));

		// 标志数据类型
		int playerType = -1;
		int isStarter = 0;

		List<MatchPlayerBasic> home_basic = new ArrayList<MatchPlayerBasic>();
		List<MatchPlayerBasic> guest_basic = new ArrayList<MatchPlayerBasic>();
		List<MatchPlayerAdvanced> home_advanced = new ArrayList<MatchPlayerAdvanced>();
		List<MatchPlayerAdvanced> guest_advanced = new ArrayList<MatchPlayerAdvanced>();

		// 从比赛文本文件中获得其他信息
		List<String> lines = FileManager.read(path + name);
		for (int i = 0; i < lines.size(); ++i) {
			// 从第0行获取是否为常规赛，地点，时长
			if (i == 0) {
				String[] s = lines.get(i).split(";", -1);
				// 判断是否为常规赛
				if (s[3].equals("True"))
					match.setNormal(true);
				else
					match.setNormal(false);
				match.setLocation(s[5]);
				match.setTime(s[6]);
			} else if (i == 1) {
				// 从第1行获取客队比赛得分,最后一个是总分
				String[] pts = lines.get(i).split(";");
				for (int j = 0; j < pts.length; ++j) {
					match.getGuest_pts().add(Utility.stringToInt(pts[j]));
				}
				match.setGuest_point(Utility.stringToInt(pts[pts.length - 1]));
			} else if (i == 2) {
				// 从第2行获取主队比赛得分,最后一个是总分
				String[] pts = lines.get(i).split(";");
				for (int j = 0; j < pts.length; ++j) {
					match.getHome_pts().add(Utility.stringToInt(pts[j]));
				}
				match.setHome_point(Utility.stringToInt(pts[pts.length - 1]));
			} else {
				switch (lines.get(i)) {
				case "Guest Basic":
					playerType = 0;
					isStarter = 0;
					continue;
				case "Home Basic":
					playerType = 1;
					isStarter = 0;
					continue;
				case "Guest Advanced":
					playerType = 2;
					isStarter = 0;
					continue;
				case "Home Advanced":
					playerType = 3;
					isStarter = 0;
					continue;
				default:
					break;
				}
			}
			switch (playerType) {
			case 0:
				isStarter++;
				guest_basic.add(getBasic(name, match.getGuest_team(),
						lines.get(i), isStarter));
				break;
			case 1:
				isStarter++;
				home_basic.add(getBasic(name, match.getHome_team(),
						lines.get(i), isStarter));
				break;
			case 2:
				isStarter++;
				guest_advanced.add(getAdvanced(name, match.getGuest_team(),
						lines.get(i), isStarter));
				break;
			case 3:
				isStarter++;
				home_advanced.add(getAdvanced(name, match.getHome_team(),
						lines.get(i), isStarter));
				break;
			default:
			}
		}
		match.setHome_basic_list(home_basic);
		match.setHome_advanced_list(home_advanced);
		match.setGuest_basic_list(guest_basic);
		match.setGuest_advanced_list(guest_advanced);
		return match;
	}

	/**
	 * 获取单个球员基本数据
	 */
	private MatchPlayerBasic getBasic(String game, String team, String str,
			int isStarter) {
		MatchPlayerBasic mpb = new MatchPlayerBasic();
		String[] data = str.split(";", -1);
		mpb.setGame_id(game);
		mpb.setTeam_abbr(team);
		mpb.setPlayer_name(data[0]);
		// Team Total球队总数据，Starter首发球员，Reserve是非首发上场，DidNotPlayer冷板凳
		if (data[0].equals("Team Totals")) {
			mpb.setStarter("Team Totals");
		} else {
			mpb.setStarter(isStarter <= 5 ? "Starter" : "Reserve");
		}
		if (data.length <= 2) {
			mpb.setStarter("DidNotPlay");
			return mpb;
		}
		mpb.setMinute(Utility.minStringToDouble(data[1]));
		mpb.setFg(Utility.stringToInt(data[2]));
		mpb.setFga(Utility.stringToInt(data[3]));
		mpb.setFga_pct(Utility.stringToDouble(data[4]));
		mpb.setFg3(Utility.stringToInt(data[5]));
		mpb.setFg3a(Utility.stringToInt(data[6]));
		mpb.setFg3_pct(Utility.stringToDouble(data[7]));
		mpb.setFt(Utility.stringToInt(data[8]));
		mpb.setFta(Utility.stringToInt(data[9]));
		mpb.setFt_pct(Utility.stringToDouble(data[10]));
		mpb.setOrb(Utility.stringToInt(data[11]));
		mpb.setDrb(Utility.stringToInt(data[12]));
		mpb.setTrb(Utility.stringToInt(data[13]));
		mpb.setAst(Utility.stringToInt(data[14]));
		mpb.setStl(Utility.stringToInt(data[15]));
		mpb.setBlk(Utility.stringToInt(data[16]));
		mpb.setTov(Utility.stringToInt(data[17]));
		mpb.setPf(Utility.stringToInt(data[18]));
		mpb.setPts(Utility.stringToInt(data[19]));
		mpb.setPlus_minus(Utility.stringToDouble(data[20]));
		return mpb;
	}

	/**
	 * 获取单个球员高阶数据
	 */
	private MatchPlayerAdvanced getAdvanced(String game, String team,
			String str, int isStarter) {

		MatchPlayerAdvanced mpa = new MatchPlayerAdvanced();
		String[] data = str.split(";", -1);
		mpa.setGame_id(game);
		mpa.setTeam_abbr(team);
		mpa.setPlayer_name(data[0]);
		if (data[0].equals("Team Totals")) {
			mpa.setStarter("Team Totals");
		} else {
			mpa.setStarter(isStarter <= 5 ? "Starter" : "Reserve");
		}
		if (data.length <= 2) {
			mpa.setStarter("DidNotPlay");
			return mpa;
		}
		mpa.setMinute(Utility.minStringToDouble(data[1]));
		mpa.setTs_pct(Utility.stringToDouble(data[2]));
		mpa.setEfg_pct(Utility.stringToDouble(data[3]));
		mpa.setFa3a_per_fga_pct(Utility.stringToDouble(data[4]));
		mpa.setFta_per_fga_pct(Utility.stringToDouble(data[5]));
		mpa.setOrb_pct(Utility.stringToDouble(data[6]));
		mpa.setDrb_pct(Utility.stringToDouble(data[7]));
		mpa.setTrb_pct(Utility.stringToDouble(data[8]));
		mpa.setAst_pct(Utility.stringToDouble(data[9]));
		mpa.setStl_pct(Utility.stringToDouble(data[10]));
		mpa.setBlk_pct(Utility.stringToDouble(data[11]));
		mpa.setTov_pct(Utility.stringToDouble(data[12]));
		mpa.setUsg_pct(Utility.stringToDouble(data[13]));
		mpa.setOff_rtg(Utility.stringToDouble(data[14]));
		mpa.setDef_rtg(Utility.stringToDouble(data[15]));
		return mpa;

	}

}
