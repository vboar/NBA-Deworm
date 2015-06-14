package service;

import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;

import junit.framework.TestCase;
import service.impl.ServiceFactoryImpl;
import util.FieldType;
import vo.HotTeamInfoVO;
import vo.TeamAdvancedVO;
import vo.TeamFilter;
import vo.TeamInfoVO;
import vo.TeamOppPerGameVO;
import vo.TeamOppTotalVO;
import vo.TeamPerGameVO;
import vo.TeamTotalVO;

public class TeamServiceTest extends TestCase {
	
	private TeamService ts;
	
	public TeamServiceTest(){
		try {
			ts = ServiceFactoryImpl.getInstance().getTeamService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}	

	protected static void setUpBeforeClass() throws Exception {
	}

	protected static void tearDownAfterClass() throws Exception {
	}

	public void testGetTeamLogoByAbbr(){
		try {
			ImageIcon i = ts.getTeamLogoByAbbr("ATL");
			assertEquals("ATL", i.getDescription());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void testGetAllTeamLogo(){
		try {
			List<ImageIcon> list = ts.getAllTeamLogo();
			assertEquals(30, list.size());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void testGetTeamInfoByAbbr() {
		try {
			TeamInfoVO info = ts.getTeamInfoByAbbr("ATL");
			assertEquals("ATL", info.abbr);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamTotalBySeason() {
		try {
			List<TeamTotalVO> list = ts.getTeamTotalBySeason("13-14");
			for(TeamTotalVO tst: list){
				assertEquals("13-14", tst.season);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamPerGameBySeason() {
		try {
			List<TeamPerGameVO> list = ts.getTeamPerGameBySeason("13-14");
			for(TeamPerGameVO tst: list){
				assertEquals("13-14", tst.season);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamOppTotalBySeason() {
		try {
			List<TeamOppTotalVO> list = ts.getTeamOppTotalBySeason("13-14");
			for(TeamOppTotalVO tst: list){
				assertEquals("13-14", tst.season);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamOppPerGameBySeason() {
		try {
			List<TeamOppPerGameVO> list = ts.getTeamOppPerGameBySeason("13-14");
			for(TeamOppPerGameVO tst: list){
				assertEquals("13-14", tst.season);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamTotalByAbbr() {
		try {
			List<TeamTotalVO> list = ts.getTeamTotalByAbbr("ATL");
			for(TeamTotalVO tst : list){
				assertEquals("ATL", tst.abbr);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamPerGameByAbbr() {
		try {
			List<TeamPerGameVO> list = ts.getTeamPerGameByAbbr("ATL");
			for(TeamPerGameVO tst : list){
				assertEquals("ATL", tst.abbr);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamOppTotalByAbbr() {
		try {
			List<TeamOppTotalVO> list = ts.getTeamOppTotalByAbbr("ATL");
			for(TeamOppTotalVO ost: list){
				assertEquals("ATL", ost.abbr);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamOppPerGameByAbbr() {
		try {
			List<TeamOppPerGameVO> list = ts.getTeamOppPerGameByAbbr("ATL");
			for(TeamOppPerGameVO ost: list){
				assertEquals("ATL", ost.abbr);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamTotalBySeasonAbbr() {
		try {
			TeamTotalVO tst = ts.getTeamTotalBySeasonAbbr("13-14", "ATL");
			assertEquals("13-14", tst.season);
			assertEquals("ATL", tst.abbr);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamPerGameBySeasonAbbr() {
		try {
			TeamPerGameVO tst = ts.getTeamPerGameBySeasonAbbr("13-14", "ATL");
			assertEquals("13-14", tst.season);
			assertEquals("ATL", tst.abbr);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamOppTotalBySeasonAbbr() {
		try {
			TeamOppTotalVO tst = ts.getTeamOppTotalBySeasonAbbr("13-14", "ATL");
			assertEquals("13-14", tst.season);
			assertEquals("ATL", tst.abbr);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamOppPerGameBySeasonAbbr() {
		try {
			TeamOppPerGameVO tst = ts.getTeamOppPerGameBySeasonAbbr("13-14", "ATL");
			assertEquals("13-14", tst.season);
			assertEquals("ATL", tst.abbr);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetAllTeamInfo() {
		try {
			List<TeamInfoVO> list = ts.getAllTeamInfo();
			assertEquals(30, list.size());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamTotalByFilter() {
		try {
			TeamFilter tf = new TeamFilter();
			tf.season = "13-14";
			List<TeamTotalVO> list = ts.getTeamTotalByFilter(tf);
			for(TeamTotalVO tst : list){
				assertEquals("13-14", tst.season);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamPerGameByFilter() {
		try {
			TeamFilter tf = new TeamFilter();
			tf.season = "13-14";
			List<TeamPerGameVO> list = ts.getTeamPerGameByFilter(tf);
			for(TeamPerGameVO tst : list){
				assertEquals("13-14", tst.season);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetSeasonHotTeam() {
		try {
			List<HotTeamInfoVO> list = ts.getSeasonHotTeam("13-14", FieldType.AST.ordinal(), 5);
			for(HotTeamInfoVO info: list){
				assertEquals("13-14", info.season);
				assertEquals(FieldType.AST, info.field);
			}
			assertEquals(5, list.size());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamAdvancedByFilter() {
		try {
			TeamFilter tf = new TeamFilter();
			tf.season = "13-14";
			List<TeamAdvancedVO> list = ts.getTeamAdvancedByFilter(tf);
			for(TeamAdvancedVO tst : list){
				assertEquals("13-14", tst.season);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamAdvancedBySeasonAbbr() {
		try {
			TeamAdvancedVO tsa = ts.getTeamAdvancedBySeasonAbbr("13-14","ATL");
			assertEquals("13-14", tsa.season);
			assertEquals("ATL", tsa.abbr);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamAdvancedByAbbr() {
		try {
			List<TeamAdvancedVO> list = ts.getTeamAdvancedByAbbr("ATL");
			for(TeamAdvancedVO tst: list){
				assertEquals("ATL", tst.abbr);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamAdvancedBySeason() {
		try {
			List<TeamAdvancedVO> list = ts.getTeamAdvancedBySeason("12-13");
			for(TeamAdvancedVO tst: list){
				assertEquals("12-13", tst.season);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
