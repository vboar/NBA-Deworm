package dao;

import java.util.List;

import junit.framework.TestCase;
import util.FieldType;
import vo.TeamFilter;
import dao.impl.DaoFactoryImpl;
import entity.HotTeamInfo;
import entity.OpponentStatsPerGame;
import entity.OpponentStatsTotal;
import entity.TeamInfo;
import entity.TeamStatsAdvanced;
import entity.TeamStatsPerGame;
import entity.TeamStatsTotal;

public class TeamDaoTest extends TestCase {

	TeamDao tdao = DaoFactoryImpl.getDaoFactory().getTeamDao();
	
	protected static void setUpBeforeClass() throws Exception {
	}

	protected static void tearDownAfterClass() throws Exception {
	}

	public void testGetAllTeamInfo() {
		List<TeamInfo> list = tdao.getAllTeamInfo();
		assertEquals(30, list.size());
	}

	public void testGetTeamInfoByAbbr() {
		TeamInfo info = tdao.getTeamInfoByAbbr("ATL");
		assertEquals("ATL", info.getAbbr());
	}

	public void testGetTeamTotalBySeason() {
		List<TeamStatsTotal> list = tdao.getTeamTotalBySeason("13-14");
		for(TeamStatsTotal tst: list){
			assertEquals("13-14", tst.getSeason());
		}
	}

	public void testGetTeamPerGameBySeason() {
		List<TeamStatsPerGame> list = tdao.getTeamPerGameBySeason("13-14");
		for(TeamStatsPerGame tst: list){
			assertEquals("13-14", tst.getSeason());
		}
	}

	public void testGetTeamOppTotalBySeason() {
		List<OpponentStatsTotal> list = tdao.getTeamOppTotalBySeason("13-14");
		for(OpponentStatsTotal tst: list){
			assertEquals("13-14", tst.getSeason());
		}
	}

	public void testGetTeamOppPerGameBySeason() {
		List<OpponentStatsPerGame> list = tdao.getTeamOppPerGameBySeason("13-14");
		for(OpponentStatsPerGame tst: list){
			assertEquals("13-14", tst.getSeason());
		}
	}

	public void testGetTeamTotalByAbbr() {
		List<TeamStatsTotal> list = tdao.getTeamTotalByAbbr("ATL");
		for(TeamStatsTotal tst : list){
			assertEquals("ATL", tst.getAbbr());
		}
	}

	public void testGetTeamPerGameByAbbr() {
		List<TeamStatsPerGame> list = tdao.getTeamPerGameByAbbr("ATL");
		for(TeamStatsPerGame tsp : list){
			assertEquals("ATL", tsp.getAbbr());
		}
	}

	public void testGetTeamOppTotalByAbbr() {
		List<OpponentStatsTotal> list = tdao.getTeamOppTotalByAbbr("ATL");
		for(OpponentStatsTotal ost: list){
			assertEquals("ATL", ost.getAbbr());
		}
	}

	public void testGetTeamOppPerGameByAbbr() {
		List<OpponentStatsPerGame> list = tdao.getTeamOppPerGameByAbbr("ATL");
		for(OpponentStatsPerGame osp: list){
			assertEquals("ATL", osp.getAbbr());
		}
	}

	public void testGetTeamTotalByFilter() {
		TeamFilter tf = new TeamFilter();
		tf.season = "13-14";
		List<TeamStatsTotal> list = tdao.getTeamTotalByFilter(tf);
		for(TeamStatsTotal tst : list){
			assertEquals("13-14", tst.getSeason());
		}
	}

	public void testGetTeamPerGameByFilter() {
		TeamFilter tf = new TeamFilter();
		tf.season = "13-14";
		List<TeamStatsPerGame> list = tdao.getTeamPerGameByFilter(tf);
		for(TeamStatsPerGame tst : list){
			assertEquals("13-14", tst.getSeason());
		}
	}

	public void testGetTeamTotalBySeasonAbbr() {
		TeamStatsTotal tst = tdao.getTeamTotalBySeasonAbbr("13-14", "ATL");
		assertEquals("13-14", tst.getSeason());
		assertEquals("ATL", tst.getAbbr());
	}

	public void testGetTeamPerGameBySeasonAbbr() {
		TeamStatsPerGame tsp = tdao.getTeamPerGameBySeasonAbbr("13-14", "ATL");
		assertEquals("13-14", tsp.getSeason());
		assertEquals("ATL", tsp.getAbbr());
	}

	public void testGetTeamOppTotalBySeasonAbbr() {
		OpponentStatsTotal ost = tdao.getTeamOppTotalBySeasonAbbr("13-14", "ATL");
		assertEquals("13-14", ost.getSeason());
		assertEquals("ATL", ost.getAbbr());
	}

	public void testGetTeamOppPerGameBySeasonAbbr() {
		OpponentStatsPerGame osp = tdao.getTeamOppPerGameBySeasonAbbr("13-14", "ATL");
		assertEquals("13-14", osp.getSeason());
		assertEquals("ATL", osp.getAbbr());
	}

	public void testGetSeasonHotTeam() {
		List<HotTeamInfo> list = tdao.getSeasonHotTeam("13-14", FieldType.AST, 5);
		for(HotTeamInfo info: list){
			assertEquals("13-14", info.getSeason());
			assertEquals(FieldType.AST, info.getField());
		}
		assertEquals(5, list.size());
	}
	
	public void testGetTeamAdvancedByFilter() {
		TeamFilter tf = new TeamFilter();
		tf.season = "13-14";
		tf.league = "E";
		tf.division = "Atlantic";
		List<TeamStatsAdvanced> list = tdao.getTeamAdvancedByFilter(tf);
		assertEquals(5, list.size());
		for(TeamStatsAdvanced tst : list){
			assertEquals("13-14", tst.getSeason());
		}
	}

	public void testGetTeamAdvancedBySeasonAbbr() {
		TeamStatsAdvanced tsa = tdao.getTeamAdvancedBySeasonAbbr("13-14","ATL");
		assertEquals("13-14", tsa.getSeason());
		assertEquals("ATL", tsa.getAbbr());
	}

	public void testGetTeamAdvancedByAbbr() {
		List<TeamStatsAdvanced> list = tdao.getTeamAdvancedByAbbr("ATL");
		for(TeamStatsAdvanced tst: list){
			assertEquals("ATL", tst.getAbbr());
		}
	}

	public void testGetTeamAdvancedBySeason() {
		List<TeamStatsAdvanced> list = tdao.getTeamAdvancedBySeason("13-14");
		for(TeamStatsAdvanced tst: list){
			assertEquals("13-14", tst.getSeason());
		}
	}

}
