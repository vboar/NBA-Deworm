package service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import junit.framework.TestCase;
import service.impl.ServiceFactoryImpl;
import util.FieldType;
import vo.HotPlayerInfoVO;
import vo.PlayerAdvancedVO;
import vo.PlayerFilter;
import vo.PlayerInfoVO;
import vo.PlayerPerGameVO;
import vo.PlayerSalaryVO;
import vo.PlayerTotalVO;

public class PlayerServiceTest extends TestCase {
	
	private PlayerService ps;
	
	public PlayerServiceTest(){
		try {
			ps = ServiceFactoryImpl.getInstance().getPlayerService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	protected static void setUpBeforeClass() throws Exception {
	}

	protected static void tearDownAfterClass() throws Exception {
	}

	public void testGetPlayerProtraitByNameList(){
		try{
			List<String> names = new ArrayList<String>();
			names.add("Aaron Brooks");
			names.add("Kevin Durant");
			List<ImageIcon> list = ps.getPlayerPortraitByNameList(names);
			assertEquals(2, list.size());
			assertEquals("Aaron Brooks", list.get(0).getDescription());
			assertEquals("Kevin Durant", list.get(1).getDescription());
		}catch(RemoteException e){
			e.printStackTrace();
		}
	}
	
	public void testGetPlayerProtraitByName(){
		try{
			ImageIcon i = ps.getPlayerPortraitByName("Aaron Brooks");
			assertEquals("Aaron Brooks", i.getDescription());
		}catch(RemoteException e){
			e.printStackTrace();
		}
	}
	
	public void testGetNameList() {
		try {
			List<String> list = ps.getNameList("Aaron");
			for(String s: list){
				boolean b = s.contains("Aaron");
				assertEquals(b,true);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetPlayerInfoByNameInitial() {
		try {
			List<String> list = ps.getNameByNameInitial("Z");
			for(String s: list){
				boolean b = s.startsWith("Z");
				assertEquals(b, true);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetAllPlayerInfo() {
		try {
			List<PlayerInfoVO> list = ps.getAllPlayerInfo();
			assertEquals(1465,list.size());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetPlayerInfoByName() {
		try {
			PlayerInfoVO info = ps.getPlayerInfoByName("Aaron Brooks");
			assertEquals("Aaron Brooks", info.name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetPlayerTotalByName() {
		try {
			List<PlayerTotalVO> list = ps.getPlayerTotalByName("Aaron Brooks",1);
            System.out.println(list.size());
			for(PlayerTotalVO pst: list){
				assertEquals("Aaron Brooks", pst.name);
				System.out.println(pst.season + " " + pst.team);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetPlayerPerGameByName() {
		try {
			List<PlayerPerGameVO> list = ps.getPlayerPerGameByName("Kevin Durant",1);
			for(PlayerPerGameVO pst: list){
				assertEquals("Kevin Durant", pst.name);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetPlayerAdvancedByName() {
		try {
			List<PlayerAdvancedVO> list = ps.getPlayerAdvancedByName("Kevin Durant",1);
			for(PlayerAdvancedVO pst: list){
				assertEquals("Kevin Durant", pst.name);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetPlayerSalaryByName() {
		try {
			List<PlayerSalaryVO> list = ps.getPlayerSalaryByName("Aaron Brooks");
			assertEquals("Aaron Brooks", list.get(0).name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetPlayerTotalBySeason() {
		try {
			List<PlayerTotalVO> list = ps.getPlayerTotalBySeason("14-15",1);
			for(PlayerTotalVO pst: list){
				System.out.println(pst.efg_pct);
				assertEquals("14-15", pst.season);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetPlayerPerGameBySeason() {
		try {
			List<PlayerPerGameVO> list = ps.getPlayerPerGameBySeason("13-14",1);
			for(PlayerPerGameVO pst: list){
				assertEquals("13-14", pst.season);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetPlayerAdvancedBySeason() {
		try {
			List<PlayerAdvancedVO> list = ps.getPlayerAdvancedBySeason("13-14",1);
			for(PlayerAdvancedVO pst: list){
				assertEquals("13-14", pst.season);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetPlayerTotalBySeasonName() {
		try {
			List<PlayerTotalVO> pst= ps.getPlayerTotalBySeasonName("13-14","Kevin Durant",1);
			assertEquals("Kevin Durant", pst.get(0).name);
			assertEquals("13-14", pst.get(0).season);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetPlayerPerGameBySeasonName() {
		try {
			List<PlayerPerGameVO> pst= ps.getPlayerPerGameBySeasonName("13-14","Kevin Durant",-1);
			assertEquals("Kevin Durant", pst.get(0).name);
			assertEquals("13-14", pst.get(0).season);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetPlayerAdvancedBySeasonName() {
		try {
			List<PlayerAdvancedVO> pst= ps.getPlayerAdvancedBySeasonName("13-14","Kevin Durant",-1);
			assertEquals("Kevin Durant", pst.get(0).name);
			assertEquals("13-14", pst.get(0).season);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetPlayerPerGameByFilter() {
		try {
			PlayerFilter pf = new PlayerFilter();
//			pf.position = "PF";
//			pf.league = "Atlantic";
			pf.season = "Career";
//			pf.height = ">6-7";
//			pf.weight = "<270";
//			pf.regular = 1;
//			pf.team = "BOS";
			List<PlayerPerGameVO> list = ps.getPlayerPerGameByFilter(pf);
			System.out.println("filter per ---> " + list.size());
			for(PlayerPerGameVO pst: list){
				assertEquals("PF", pst.position);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetPlayerTotalByFilter() {
		try {
			PlayerFilter pf = new PlayerFilter();
//			pf.position = "PF";
//			pf.league = "Atlantic";
			pf.season = "13-14";
			List<PlayerTotalVO> list = ps.getPlayerTotalByFilter(pf);
			System.out.println("filter...-> "+list.size());
			for(PlayerTotalVO pst: list){
				//assertEquals("PF", pst.position);
				assertEquals("13-14", pst.season);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetPlayerAdvancedByFilter() {
		try {
			PlayerFilter pf = new PlayerFilter();
			pf.regular = 0;
			pf.season = "13-14";
			pf.position = "PF";
			List<PlayerAdvancedVO> list = ps.getPlayerAdvancedByFilter(pf);
			for(PlayerAdvancedVO pst: list){
				assertEquals("PF", pst.position);
				assertEquals("13-14", pst.season);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetTeamPlayerBySeason() {
		try {
			List<PlayerInfoVO> list = ps.getTeamPlayerBySeason("13-14", "ATL");
			System.out.println(list.size());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testGetSeasonHotPlayer() {
		try {
			List<HotPlayerInfoVO> list = ps.getSeasonHotPlayer("13-14", FieldType.typeToInt(FieldType.AST),5);
			for(HotPlayerInfoVO info : list){
				assertEquals("13-14", info.season);
				assertEquals(FieldType.AST, info.field);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
