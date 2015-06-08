package dao;

import java.util.List;

import junit.framework.TestCase;
import util.FieldType;
import vo.PlayerFilter;
import dao.impl.DaoFactoryImpl;
import entity.HotPlayerInfo;
import entity.PlayerInfo;
import entity.PlayerSalary;
import entity.PlayerStatsAdvanced;
import entity.PlayerStatsPerGame;
import entity.PlayerStatsTotal;

public class PlayerDaoTest extends TestCase {

	PlayerDao pdao = DaoFactoryImpl.getDaoFactory().getPlayerDao();
	
	protected static void setUpBeforeClass() throws Exception {
	}

	protected static void tearDownAfterClass() throws Exception {
	}

	public void testGetNameList() {
		List<String> list = pdao.getNameList("Aaron");
		for(String s: list){
			boolean b = s.contains("Aaron");
			assertEquals(b,true);
		}
	}

	public void testGetPlayerInfoByNameInitial() {
		List<String> list = pdao.getNameByNameInitial("Z");
		for(String s: list){
			boolean b = s.startsWith("Z");
			assertEquals(b, true);
		}
	}

	public void testGetAllPlayerInfo() {
		List<PlayerInfo> list = pdao.getAllPlayerInfo();
		assertEquals(1465,list.size());
	}

	public void testGetPlayerInfoByName() {
		PlayerInfo info = pdao.getPlayerInfoByName("Aaron Brooks");
		assertEquals("Aaron Brooks", info.getName());
	}

	public void testGetPlayerTotalByName() {
		List<PlayerStatsTotal> list = pdao.getPlayerTotalByName("Kevin Durant");
		for(PlayerStatsTotal pst: list){
			assertEquals("Kevin Durant", pst.getName());
		}
	}

	public void testGetPlayerPerGameByName() {
		List<PlayerStatsPerGame> list = pdao.getPlayerPerGameByName("Kevin Durant");
		for(PlayerStatsPerGame psp: list){
			assertEquals("Kevin Durant", psp.getName());
		}
	}

	public void testGetPlayerAdvancedByName() {
		List<PlayerStatsAdvanced> list = pdao.getPlayerAdvancedByName("Kevin Durant");
		for(PlayerStatsAdvanced psp: list){
			assertEquals("Kevin Durant", psp.getName());
		}
	}

	public void testGetPlayerTotalBySeason() {
		List<PlayerStatsTotal> list = pdao.getPlayerTotalBySeason("13-14");
		for(PlayerStatsTotal pst: list){
			assertEquals("13-14", pst.getSeason());
		}
	}

	public void testGetPlayerPerGameBySeason() {
		List<PlayerStatsPerGame> list = pdao.getPlayerPerGameBySeason("13-14");
		for(PlayerStatsPerGame pst: list){
			assertEquals("13-14", pst.getSeason());
		}
	}

	public void testGetPlayerAdvancedBySeason() {
		List<PlayerStatsPerGame> list = pdao.getPlayerPerGameBySeason("13-14");
		for(PlayerStatsPerGame pst: list){
			assertEquals("13-14", pst.getSeason());
		}
	}

	public void testGetTeamPlayerBySeason() {
		List<PlayerInfo> list = pdao.getTeamPlayerBySeason("13-14", "ATL");
		for(PlayerInfo info: list){
			System.out.println(info.getName());
		}
	}

	public void testGetPlayerTotalBySeasonName() {
		List<PlayerStatsTotal> pst= pdao.getPlayerTotalBySeasonName("13-14","Kevin Durant",1);
		System.out.println(pst.size());
		assertEquals("Kevin Durant", pst.get(0).getName());
		assertEquals("13-14", pst.get(0).getSeason());
	}

	public void testGetPlayerPerGameBySeasonName() {
		List<PlayerStatsPerGame> pst= pdao.getPlayerPerGameBySeasonName("13-14","Kevin Durant",-1);
		assertEquals("Kevin Durant", pst.get(0).getName());
		assertEquals("13-14", pst.get(0).getSeason());
	}

	public void testGetPlayerAdvancedBySeasonName() {
		List<PlayerStatsAdvanced> pst= pdao.getPlayerAdvancedBySeasonName("13-14","Kevin Durant",-1);
		assertEquals("Kevin Durant", pst.get(0).getName());
		assertEquals("13-14", pst.get(0).getSeason());
	}

	public void testGetPlayerTotalByFilter() {
		PlayerFilter pf = new PlayerFilter();
		pf.position = "PF";
		pf.league = "Atlantic";
		pf.season = "13-14";
		pf.regular = 1;
		List<PlayerStatsTotal> list = pdao.getPlayerTotalByFilter(pf);
		System.out.println(list.size());
		for(PlayerStatsTotal pst: list){
			assertEquals("PF", pst.getPosition());
			assertEquals("13-14", pst.getSeason());
		}
	}

	public void testGetPlayerPerGameByFilter() {
		PlayerFilter pf = new PlayerFilter();
		pf.position = "PF";
		pf.league = "E";
		pf.season = "13-14";
		pf.team = "ATL";
		pf.regular = 1;
		pf.height = ">6-5";
		pf.weight = ">270";
		List<PlayerStatsPerGame> list = pdao.getPlayerPerGameByFilter(pf);
		System.out.println(list.size());
		for(PlayerStatsPerGame pst: list){
			assertEquals("PF", pst.getPosition());
			assertEquals("13-14", pst.getSeason());
		}
	}

	public void testGetPlayerAdvancedByFilter() {
		PlayerFilter pf = new PlayerFilter();
		pf.position = "PF";
		pf.league = "E";
		pf.season = "13-14";
		pf.team = "ATL";
		pf.regular = 1;
		pf.height = ">6-5";
		pf.weight = ">270";
		List<PlayerStatsAdvanced> list = pdao.getPlayerAdvancedByFilter(pf);
		for(PlayerStatsAdvanced pst: list){
			assertEquals("PF", pst.getPosition());
			assertEquals("13-14", pst.getSeason());
		}
	}

	public void testGetSeasonHotPlayer() {
		List<HotPlayerInfo> list = pdao.getHotPlayerBySeason("13-14", FieldType.AST);
		for(HotPlayerInfo info : list){
			assertEquals("13-14", info.getSeason());
			assertEquals(FieldType.AST, info.getField());
		}
	}
	
	public void testGetPlayerSalaryByName() {
		List<PlayerSalary> list = pdao.getPlayerSalaryByName("Kobe Bryant");
		for(PlayerSalary ps: list){
			assertEquals("Kobe Bryant", ps.getName());
		}
	}
	
	public void testGetPlayerSalaryBySeason(){
		List<PlayerSalary> list = pdao.getPlayerSalaryBySeason("13-14", "Kobe Bryant");
		System.out.println(list.size());
		for(PlayerSalary ps: list){
			assertEquals("Kobe Bryant", ps.getName());
			assertEquals("13-14", ps.getSeason());
		}
	}

	public void testGetPlayerSalaryByTeam(){
		List<PlayerSalary> list = pdao.getPlayerSalaryByTeam("13-14", "ATL");
		for(PlayerSalary ps: list){
			assertEquals("ATL", ps.getTeam());
			assertEquals("13-14", ps.getSeason());
		}
	}
	
	public void testGetTeamByPlayerName(){
		List<String> list = pdao.getTeamByPlayerNameSeason("Aaron Brooks", "13-14");
		System.out.println(list.size());
		for(String s: list){
			System.out.println(s);
		}
	}
	
}
