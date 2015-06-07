package dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import util.Utility;
import dao.RawPlayerDao;
import entity.PlayerInfo;
import entity.PlayerSalary;
import entity.PlayerStatsAdvanced;
import entity.PlayerStatsPerGame;
import entity.PlayerStatsTotal;

public class RawPlayerDaoImpl implements RawPlayerDao {

	private static String PLAYER_PATH = FileManager.DATA_PATH
			+ "/players/text/";

	// 存放各类数据
	private List<PlayerInfo> infolist;
	private List<PlayerSalary> salarylist;
	private List<PlayerStatsTotal> totallist;
	private List<PlayerStatsPerGame> pergamelist;
	private List<PlayerStatsAdvanced> advancedlist;
	
	public RawPlayerDaoImpl() {
		System.out.println("Player Data：" + PLAYER_PATH);
		infolist = new ArrayList<PlayerInfo>();
		salarylist = new ArrayList<PlayerSalary>();
		totallist = new ArrayList<PlayerStatsTotal>();
		pergamelist = new ArrayList<PlayerStatsPerGame>();
		advancedlist = new ArrayList<PlayerStatsAdvanced>();
		this.getAllData();
	}

	/**
	 * 解析文件为五个List
	 */
	private void getAllData() {
		File folder = new File(PLAYER_PATH);
		String[] players = folder.list();
		for (String player : players) {
			PlayerInfo info = new PlayerInfo();
			info.setName(player);
			List<String> lines = FileManager.read(PLAYER_PATH + player);
			// Totals = 0, Per Game = 1, Advanced = 2, salary = 3
			int dataType = -1;
			// normal = 0, playoff = 1
			int normal_playeroff = 0;
			for (int i = 0; i < lines.size(); ++i) {
				// 从第0行获得姓名、位置、shoots、出生年月、出生地、身高、体重、高中、大学、
				// Draft、debut、exp、number
				if (i == 0) {
					String[] data = lines.get(i).split(";", -1);
					info.setPosition(data[1]);
					info.setShoots(data[2]);
					info.setBorn(data[3]);
					info.setHometown(data[4]);
					info.setHeight(data[5]);
					info.setWeight(Utility.stringToInt(data[6]));
					info.setHigh_school(data[7]);
					info.setCollege(data[8]);
					info.setDraft(data[9]);
					info.setDebut(data[10]);
					info.setExperience(Utility.stringToInt(data[11]));
					info.setNumber(Utility.stringToInt(data[12]));
					this.infolist.add(info);
				} else {
					switch (lines.get(i)) {
					case "Totals":
						dataType = 0;
						normal_playeroff = 1;
						continue;
					case "Per Game":
						dataType = 1;
						normal_playeroff = 1;
						continue;
					case "Advanced":
						dataType = 2;
						normal_playeroff = 1;
						continue;
					case "Playoffs Totals":
						dataType = 0;
						normal_playeroff = 0;
						continue;
					case "Playoffs Per Game":
						dataType = 1;
						normal_playeroff = 0;
						continue;
					case "Playoffs Advanced":
						dataType = 2;
						normal_playeroff = 0;
						continue;
					case "Salaries":
						dataType = 3;
						continue;
					default:
						break;
					}
				}// end of else

				// 依据dataType解析各类数据
				switch (dataType) {
				case 0:
					this.totallist.add(getTotals(player, lines.get(i),
							normal_playeroff));
					break;
				case 1:
					this.pergamelist.add(getPerGame(player, lines.get(i),
							normal_playeroff));
					break;
				case 2:
					this.advancedlist.add(getAdvanced(player, lines.get(i),
							normal_playeroff));
					break;
				case 3:
					this.salarylist.add(getSalary(player, lines.get(i)));
					break;
				default:
				}
			}// end of lines circle
		}// end of players circle
	}

	/**
	 * 将字符串解析为PlayerSalary
	 * 
	 * @param player
	 * @param string
	 * @return
	 */
	private PlayerSalary getSalary(String player, String str) {
		PlayerSalary slr = new PlayerSalary();
		slr.setName(player);
		String[] data = str.split(";", -1);
		slr.setSeason(data[0]);
		slr.setTeam(data[1]);
		slr.setSalary(Utility.salaryToLong(data[2]));
		return slr;
	}

	/**
	 * 将字符串解析为PlayerStatsAdvanced
	 * 
	 * @param player
	 * @param string
	 * @param normal_playeroff
	 * @return
	 */
	private PlayerStatsAdvanced getAdvanced(String player, String str,
			int normal_playeroff) {
		PlayerStatsAdvanced advanced = new PlayerStatsAdvanced();
		advanced.setName(player);
		advanced.setIs_normal(normal_playeroff);
		String[] data = str.split(";", -1);
		try{
		advanced.setSeason(data[0]);
		advanced.setTeam(data[1]);
		advanced.setPosition(data[2]);
		advanced.setNum_Of_Game(Utility.stringToInt(data[3]));
		advanced.setMinute(Utility.stringToInt(data[4]));
		advanced.setPer(Utility.stringToDouble(data[5]));
		advanced.setTs_pct(Utility.stringToDouble(data[6]));
		advanced.setFa3a_per_fga_pct(Utility.stringToDouble(data[7]));
		advanced.setFta_per_fga_pct(Utility.stringToDouble(data[8]));
		advanced.setOrb_pct(Utility.stringToDouble(data[9]));
		advanced.setDrb_pct(Utility.stringToDouble(data[10]));
		advanced.setTrb_pct(Utility.stringToDouble(data[11]));
		advanced.setAst_pct(Utility.stringToDouble(data[12]));
		advanced.setStl_pct(Utility.stringToDouble(data[13]));
		advanced.setBlk_pct(Utility.stringToDouble(data[14]));
		advanced.setTov_pct(Utility.stringToDouble(data[15]));
		advanced.setUsg_pct(Utility.stringToDouble(data[16]));
		advanced.setOws(Utility.stringToDouble(data[17]));
		advanced.setDws(Utility.stringToDouble(data[18]));
		advanced.setWs(Utility.stringToDouble(data[19]));
		advanced.setWs_48(Utility.stringToDouble(data[20]));
		advanced.setObpm(Utility.stringToDouble(data[21]));
		advanced.setDbpm(Utility.stringToDouble(data[22]));
		advanced.setBpm(Utility.stringToDouble(data[23]));
		advanced.setVorp(Utility.stringToDouble(data[24]));
		}catch(Exception e){
			System.out.println("Advanced--> "+player+" "+str);
		}
		return advanced;
	}

	/**
	 * 将字符串解析为PlayerStatsPerGame
	 * 
	 * @param player
	 * @param str
	 * @param normal_playeroff
	 * @return
	 */
	private PlayerStatsPerGame getPerGame(String player, String str,
			int normal_playeroff) {
		PlayerStatsPerGame pergame = new PlayerStatsPerGame();
		pergame.setName(player);
		pergame.setIs_normal(normal_playeroff);
		String[] data = str.split(";", -1);
		try{
		pergame.setSeason(data[0]);
		pergame.setTeam(data[1]);
		pergame.setPosition(data[2]);
		pergame.setNum_Of_Game(Utility.stringToInt(data[3]));
		pergame.setGame_started(Utility.stringToInt(data[4]));
		pergame.setMinute(Utility.stringToDouble(data[5]));
		pergame.setFg(Utility.stringToDouble(data[6]));
		pergame.setFga(Utility.stringToDouble(data[7]));
		pergame.setFga_pct(Utility.stringToDouble(data[8]));
		pergame.setFg3(Utility.stringToDouble(data[9]));
		pergame.setFg3a(Utility.stringToDouble(data[10]));
		pergame.setFg3_pct(Utility.stringToDouble(data[11]));
		pergame.setFg2(Utility.stringToDouble(data[12]));
		pergame.setFg2a(Utility.stringToDouble(data[13]));
		pergame.setFg2_pct(Utility.stringToDouble(data[14]));
		pergame.setEfg_pct(Utility.stringToDouble(data[15]));
		pergame.setFt(Utility.stringToDouble(data[16]));
		pergame.setFta(Utility.stringToDouble(data[17]));
		pergame.setFt_pct(Utility.stringToDouble(data[18]));
		pergame.setOrb(Utility.stringToDouble(data[19]));
		pergame.setDrb(Utility.stringToDouble(data[20]));
		pergame.setTrb(Utility.stringToDouble(data[21]));
		pergame.setAst(Utility.stringToDouble(data[22]));
		pergame.setStl(Utility.stringToDouble(data[23]));
		pergame.setBlk(Utility.stringToDouble(data[24]));
		pergame.setTov(Utility.stringToDouble(data[25]));
		pergame.setPf(Utility.stringToDouble(data[26]));
		pergame.setPts(Utility.stringToDouble(data[27]));
		}catch(Exception e){
			System.out.println("Pergame--> "+player+" "+str);
		}
		return pergame;
	}

	/**
	 * 将字符串解析为PlayerStatsTotal
	 * 
	 * @param player
	 *            姓名
	 * @param str
	 * @param normal_playeroff
	 *            常规赛=0，季后赛=1
	 * @return
	 */
	private PlayerStatsTotal getTotals(String player, String str,
			int normal_playeroff) {
		PlayerStatsTotal total = new PlayerStatsTotal();
		total.setName(player);
		total.setIs_normal(normal_playeroff);
		String[] data = str.split(";", -1);
		try {
			total.setSeason(data[0]);
			total.setTeam(data[1]);
			total.setPosition(data[2]);
			total.setNum_Of_Game(Utility.stringToInt(data[3]));
			total.setGame_started(Utility.stringToInt(data[4]));
			total.setMinute(Utility.stringToInt(data[5]));
			total.setFg(Utility.stringToInt(data[6]));
			total.setFga(Utility.stringToInt(data[7]));
			total.setFga_pct(Utility.stringToDouble(data[8]));
			total.setFg3(Utility.stringToInt(data[9]));
			total.setFg3a(Utility.stringToInt(data[10]));
			total.setFg3_pct(Utility.stringToDouble(data[11]));
			total.setFg2(Utility.stringToInt(data[12]));
			total.setFg2a(Utility.stringToInt(data[13]));
			total.setFg2_pct(Utility.stringToDouble(data[14]));
			total.setEfg_pct(Utility.stringToDouble(data[15]));
			total.setFt(Utility.stringToInt(data[16]));
			total.setFta(Utility.stringToInt(data[17]));
			total.setFt_pct(Utility.stringToDouble(data[18]));
			total.setOrb(Utility.stringToInt(data[19]));
			total.setDrb(Utility.stringToInt(data[20]));
			total.setTrb(Utility.stringToInt(data[21]));
			total.setAst(Utility.stringToInt(data[22]));
			total.setStl(Utility.stringToInt(data[23]));
			total.setBlk(Utility.stringToInt(data[24]));
			total.setTov(Utility.stringToInt(data[25]));
			total.setPf(Utility.stringToInt(data[26]));
			total.setPts(Utility.stringToInt(data[27]));
		} catch (Exception e) {
			System.out.println("Total-->" + player + " " + str);
		}
		return total;
	}

	@Override
	public List<PlayerInfo> getAllPlayerInfo() {
		return infolist;
	}

	@Override
	public List<PlayerSalary> getAllPlayerSalary() {
		return salarylist;
	}

	@Override
	public List<PlayerStatsPerGame> getAllPlayerPerGame() {
		return pergamelist;
	}

	@Override
	public List<PlayerStatsTotal> getAllPlayerTotal() {
		return totallist;
	}

	@Override
	public List<PlayerStatsAdvanced> getAllPlayerAdvanced() {
		return advancedlist;
	}
}
