package service;

import java.rmi.RemoteException;
import java.util.List;

import junit.framework.TestCase;
import service.impl.ServiceFactoryImpl;
import vo.MatchFilter;
import vo.MatchInfoVO;
import vo.MatchPlayerAdvancedVO;
import vo.MatchPlayerBasicVO;

public class MatchServiceTest extends TestCase{
	
	MatchService ms ;

	public MatchServiceTest(){
		try {
			ms = ServiceFactoryImpl.getInstance().getMatchService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	protected static void setUpBeforeClass() throws Exception {
	}

	protected static void tearDownAfterClass() throws Exception {
	}

	public void testGetRegularMatchInfoBySeason() {
		try {
			List<MatchInfoVO> list;
			list = ms.getRegularMatchInfoBySeason("13-14");
			assertEquals("13-14", list.get(0).season);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetSectionScoreByGameid(){	
		try {
			List<List<Integer>> list = ms.getSectionScoreByGameId("200010310ATL-CHA");
			assertEquals(13, list.get(0).get(0).intValue());
		} catch (RemoteException e) {
				e.printStackTrace();
		}
	}
	
	public void testGetPlayOffMatchInfoBySeason() {
		try {
			List<MatchInfoVO> list;
			list = ms.getPlayOffMatchInfoBySeason("13-14");
			assertEquals("13-14", list.get(0).season);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetMatchInfoByDate() {
		try {
			List<MatchInfoVO> list = ms.getMatchInfoByDate("2014-01-01", "2014-01-10");
			boolean date = list.get(0).date.equals("2014-01-01");
			assertEquals(date, true);
			date = list.get(list.size()-1).date.equals("2014-01-10");
			assertEquals(date, true);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetMatchPlayerAdvancedByGameIdTeam() {
		try {
			List<MatchPlayerAdvancedVO> list = ms.getMatchPlayerAdvancedByGameIdTeam("200010310ATL-CHA","ATL");
			assertEquals("ATL", list.get(0).team_abbr);
			assertEquals("200010310ATL-CHA", list.get(0).game_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetMatchPlayerBasicByGameIdTeam() {
		try {
			List<MatchPlayerBasicVO> list = ms.getMatchPlayerBasicByGameIdTeam("200010310ATL-CHA","ATL");
			assertEquals("ATL", list.get(0).team_abbr);
			assertEquals("200010310ATL-CHA", list.get(0).game_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetMatchInfoByFilter() {
		try {
			MatchFilter mf = new MatchFilter();
			mf.season = "13-14";
			mf.begin_date = "2014-01-01";
			mf.end_date = "2014-01-31";
			mf.player = "Aaron Brooks";
			List<MatchInfoVO> list = ms.getMatchInfoByFilter(mf);
			assertEquals("13-14", list.get(0).season);
			boolean date = list.get(0).date.compareTo("2014-01-01") >= 0;
			assertEquals(date, true);
			date = list.get(0).date.compareTo("2014-01-31")<=0;
			assertEquals(date, true);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
