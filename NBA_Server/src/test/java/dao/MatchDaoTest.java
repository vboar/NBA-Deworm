package dao;

import java.util.List;

import junit.framework.TestCase;
import vo.MatchFilter;
import dao.impl.DaoFactoryImpl;
import entity.MatchInfo;
import entity.MatchPlayerAdvanced;
import entity.MatchPlayerBasic;

public class MatchDaoTest extends TestCase {
	
	MatchDao mdao = DaoFactoryImpl.getDaoFactory().getMatchDao();

	protected static void setUpBeforeClass() throws Exception {
	}

	protected static void tearDownAfterClass() throws Exception {
	}

	public void testGetMatchInfoByGameid(){
		MatchInfo info = mdao.getMatchInfoByGameId("200010310ATL-CHH");
		assertEquals("200010310ATL-CHH", info.getGame_id());
	}
	
	public void testGetSectionScoreByGameid(){
		List<List<Integer>> list = mdao.getSectionScoreByGameId("200010310ATL-CHH");
		assertEquals(13, list.get(0).get(0).intValue());
		list = mdao.getSectionScoreByGameId("200010310ATL-CHH");
		for(List<Integer> pts: list){
			System.out.println(pts.get(0)+" " + pts.get(1));
		}
	}
	
	public void testGetMatchInfoByFilter() {
		MatchFilter mf = new MatchFilter();
		mf.begin_date = "2014-01-01";
		mf.end_date = "2014-01-31";
		mf.order = "ASC";
		mf.team = "HOU";
		mf.regular = 1;
		List<MatchInfo> list = mdao.getMatchInfoByFilter(mf);
		assertEquals("13-14", list.get(0).getSeason());
		boolean date = list.get(0).getDate().compareTo("2014-01-01") >= 0;
		assertEquals(date, true);
		date = list.get(0).getDate().compareTo("2014-01-31")<=0;
		assertEquals(date, true);
	}

	public void testGetRegularMatchInfoBySeason() {
		List<MatchInfo> list = mdao.getRegularMatchInfoBySeason("13-14");
		assertEquals("13-14", list.get(0).getSeason());
	}

	public void testGetPlayOffMatchInfoBySeason() {
		List<MatchInfo> list = mdao.getPlayOffMatchInfoBySeason("13-14");
		assertEquals("13-14", list.get(0).getSeason());
	}

	public void testGetMatchInfoByDate() {
		List<MatchInfo> list = mdao.getMatchInfoByDate("2014-01-01", "2014-01-10");
		boolean date = list.get(0).getDate().equals("2014-01-01");
		assertEquals(date, true);
		date = list.get(list.size()-1).getDate().equals("2014-01-10");
		assertEquals(date, true);
	}

	public void testGetMatchPlayerAdvancedByGameIdTeam() {
		List<MatchPlayerAdvanced> list = mdao.getMatchPlayerAdvancedByGameIdTeam("200010310ATL-CHH","ATL");
		assertEquals("ATL", list.get(0).getTeam_abbr());
		assertEquals("200010310ATL-CHH", list.get(0).getGame_id());
	}

	public void testGetMatchPlayerBasicByGameIdTeam() {
		List<MatchPlayerBasic> list = mdao.getMatchPlayerBasicByGameIdTeam("200010310ATL-CHH","ATL");
		assertEquals("ATL", list.get(0).getTeam_abbr());
		assertEquals("200010310ATL-CHH", list.get(0).getGame_id());
	}

}
