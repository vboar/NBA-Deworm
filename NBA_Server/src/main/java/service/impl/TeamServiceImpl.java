package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import service.TeamService;
import util.FieldType;
import vo.HotTeamInfoVO;
import vo.TeamAdvancedVO;
import vo.TeamFilter;
import vo.TeamInfoVO;
import vo.TeamOppPerGameVO;
import vo.TeamOppTotalVO;
import vo.TeamPerGameVO;
import vo.TeamTotalVO;
import dao.TeamDao;
import dao.impl.DaoFactoryImpl;
import entity.HotTeamInfo;
import entity.OpponentStatsPerGame;
import entity.OpponentStatsTotal;
import entity.TeamInfo;
import entity.TeamStatsAdvanced;
import entity.TeamStatsPerGame;
import entity.TeamStatsTotal;

/**
 * TeamService的具体实现
 * 
 * created by JaneLDQ on 2015年5月31日 下午8:27:03
 */
public class TeamServiceImpl extends UnicastRemoteObject implements TeamService {

	private static final long serialVersionUID = 1L;
	
	private TeamDao tdao;
	
	public TeamServiceImpl() throws RemoteException {
		super();
		tdao = DaoFactoryImpl.getDaoFactory().getTeamDao();
	}

	@Override
	public List<ImageIcon> getAllTeamLogo() throws RemoteException {
		return tdao.getAllTeamLogo();
	}

	@Override
	public ImageIcon getTeamLogoByAbbr(String abbr) throws RemoteException {
		return tdao.getTeamLogoByAbbr(abbr);
	}	

	@Override
	public TeamInfoVO getTeamInfoByAbbr(String abbr) throws RemoteException {
		return getInfoToVO(tdao.getTeamInfoByAbbr(abbr));
	}
	
	@Override
	public List<TeamTotalVO> getTeamTotalBySeason(String season)
			throws RemoteException {
		List<TeamTotalVO> volist = new ArrayList<TeamTotalVO>();
		List<TeamStatsTotal> list = tdao.getTeamTotalBySeason(season);
		for(TeamStatsTotal tst: list){
			TeamTotalVO vo = getTeamTotalToVO(tst);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<TeamPerGameVO> getTeamPerGameBySeason(String season)
			throws RemoteException {
		List<TeamPerGameVO> volist = new ArrayList<TeamPerGameVO>();
		List<TeamStatsPerGame> list = tdao.getTeamPerGameBySeason(season);
		for(TeamStatsPerGame tsp: list){
			TeamPerGameVO vo = getTeamPerGameVO(tsp);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<TeamOppTotalVO> getTeamOppTotalBySeason(String season)
			throws RemoteException {
		List<TeamOppTotalVO> volist = new ArrayList<TeamOppTotalVO>();
		List<OpponentStatsTotal> list = tdao.getTeamOppTotalBySeason(season);
		for(OpponentStatsTotal ost: list){
			TeamOppTotalVO vo = getOppTotalVO(ost);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<TeamOppPerGameVO> getTeamOppPerGameBySeason(String season)
			throws RemoteException {
		List<TeamOppPerGameVO> volist = new ArrayList<TeamOppPerGameVO>();
		List<OpponentStatsPerGame> list = tdao.getTeamOppPerGameBySeason(season);
		for(OpponentStatsPerGame osp: list){
			TeamOppPerGameVO vo = getOppPerGameVO(osp);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<TeamTotalVO> getTeamTotalByAbbr(String abbr)
			throws RemoteException {
		List<TeamTotalVO> volist = new ArrayList<TeamTotalVO>();
		List<TeamStatsTotal> list = tdao.getTeamTotalByAbbr(abbr);
		for(TeamStatsTotal tst: list){
			TeamTotalVO vo = getTeamTotalToVO(tst);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<TeamPerGameVO> getTeamPerGameByAbbr(String abbr)
			throws RemoteException {
		List<TeamPerGameVO> volist = new ArrayList<TeamPerGameVO>();
		List<TeamStatsPerGame> list = tdao.getTeamPerGameByAbbr(abbr);
		for(TeamStatsPerGame tsp: list){
			TeamPerGameVO vo = getTeamPerGameVO(tsp);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<TeamOppTotalVO> getTeamOppTotalByAbbr(String abbr)
			throws RemoteException {
		List<TeamOppTotalVO> volist = new ArrayList<TeamOppTotalVO>();
		List<OpponentStatsTotal> list = tdao.getTeamOppTotalByAbbr(abbr);
		for(OpponentStatsTotal ost: list){
			TeamOppTotalVO vo = getOppTotalVO(ost);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<TeamOppPerGameVO> getTeamOppPerGameByAbbr(String abbr)
			throws RemoteException {
		List<TeamOppPerGameVO> volist = new ArrayList<TeamOppPerGameVO>();
		List<OpponentStatsPerGame> list = tdao.getTeamOppPerGameByAbbr(abbr);
		for(OpponentStatsPerGame osp: list){
			TeamOppPerGameVO vo = getOppPerGameVO(osp);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}
	
	@Override
	public TeamTotalVO getTeamTotalBySeasonAbbr(String season, String abbr)
			throws RemoteException {
		return getTeamTotalToVO(tdao.getTeamTotalBySeasonAbbr(season, abbr));
	}

	@Override
	public TeamPerGameVO getTeamPerGameBySeasonAbbr(String season, String abbr)
			throws RemoteException {
		return getTeamPerGameVO(tdao.getTeamPerGameBySeasonAbbr(season, abbr));
	}

	@Override
	public TeamOppTotalVO getTeamOppTotalBySeasonAbbr(String season, String abbr)
			throws RemoteException {
		return getOppTotalVO(tdao.getTeamOppTotalBySeasonAbbr(season, abbr));
	}

	@Override
	public TeamOppPerGameVO getTeamOppPerGameBySeasonAbbr(String season,
			String abbr) throws RemoteException {
		return getOppPerGameVO(tdao.getTeamOppPerGameBySeasonAbbr(season, abbr));
	}

	@Override
	public List<TeamInfoVO> getAllTeamInfo() throws RemoteException{
		List<TeamInfo> list = tdao.getAllTeamInfo();
		List<TeamInfoVO> volist = new ArrayList<TeamInfoVO>();
		for(TeamInfo info: list){
			TeamInfoVO vo = getInfoToVO(info);
			if(vo!=null)
				volist.add(getInfoToVO(info));
		}
		return volist;
	}

	@Override
	public List<TeamTotalVO> getTeamTotalByFilter(TeamFilter filter) throws RemoteException{
		List<TeamStatsTotal> list = tdao.getTeamTotalByFilter(filter);
		List<TeamTotalVO> volist = new ArrayList<TeamTotalVO>();
		for(TeamStatsTotal tst: list){
			TeamTotalVO vo = getTeamTotalToVO(tst);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<TeamPerGameVO> getTeamPerGameByFilter(TeamFilter filter) throws RemoteException{
		List<TeamStatsPerGame> list = tdao.getTeamPerGameByFilter(filter);
		List<TeamPerGameVO> volist = new ArrayList<TeamPerGameVO>();
		for(TeamStatsPerGame tsp: list){
			TeamPerGameVO vo = getTeamPerGameVO(tsp);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}
	
	@Override
	public List<HotTeamInfoVO> getSeasonHotTeam(String season, int fieldNum, int number)
			throws RemoteException {
		FieldType field = FieldType.intToType(fieldNum);
		List<HotTeamInfo> list = tdao.getSeasonHotTeam(season, field, number);
		List<HotTeamInfoVO> volist = new ArrayList<HotTeamInfoVO>();
		for(HotTeamInfo info : list){
			HotTeamInfoVO vo = getHotTeamToVO(info);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<TeamAdvancedVO> getTeamAdvancedByFilter(TeamFilter filter)
			throws RemoteException {
		List<TeamAdvancedVO> volist = new ArrayList<TeamAdvancedVO>();
		List<TeamStatsAdvanced> list = tdao.getTeamAdvancedByFilter(filter);
		for(TeamStatsAdvanced tsa: list){
			TeamAdvancedVO vo = getAdvancedToVO(tsa);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public TeamAdvancedVO getTeamAdvancedBySeasonAbbr(String season, String abbr)
			throws RemoteException {
		return getAdvancedToVO(tdao.getTeamAdvancedBySeasonAbbr(season, abbr));
	}

	@Override
	public List<TeamAdvancedVO> getTeamAdvancedByAbbr(String abbr)
			throws RemoteException {
		List<TeamAdvancedVO> volist = new ArrayList<TeamAdvancedVO>();
		List<TeamStatsAdvanced> list = tdao.getTeamAdvancedByAbbr(abbr);
		for(TeamStatsAdvanced tsa: list){
			TeamAdvancedVO vo = getAdvancedToVO(tsa);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<TeamAdvancedVO> getTeamAdvancedBySeason(String season)
			throws RemoteException {
		List<TeamAdvancedVO> volist = new ArrayList<TeamAdvancedVO>();
		List<TeamStatsAdvanced> list = tdao.getTeamAdvancedBySeason(season);
		for(TeamStatsAdvanced tsa: list){
			TeamAdvancedVO vo = getAdvancedToVO(tsa);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}
	
	private TeamInfoVO getInfoToVO(TeamInfo info) {
		if(info==null)
			return null;
		TeamInfoVO vo = new TeamInfoVO();
		vo.name = info.getName();
		vo.abbr = info.getAbbr();
		vo.buildup_time = info.getBuildup_time();
		vo.location = info.getLocation();
		vo.division = info.getDivision();
		vo.league = info.getLeague();
		vo.record = info.getRecord();
		vo.playeroff_appearance = info.getPlayeroff_appearance();
        vo.championships = info.getChampionships();
		return vo;
	}

	private TeamTotalVO getTeamTotalToVO(TeamStatsTotal tst) {
		if(tst==null)
			return null;
		TeamTotalVO vo = new TeamTotalVO();
		vo.abbr = tst.getAbbr();
		vo.season = tst.getSeason();
		vo.wins = tst.getWins();
		vo.losses = tst.getLosses();
		vo.finish = tst.getFinish();
		vo.age = tst.getAge();
		vo.height = tst.getHeight();
		vo.weight = tst.getWeight();
		vo.num_of_game = tst.getNum_of_game();
		vo.minute = tst.getMinute();
		vo.fg = tst.getFg();
		vo.fga = tst.getFga();
		vo.fga_pct = tst.getFga_pct();
		vo.fg3 = tst.getFg3();
		vo.fg3a = tst.getFg3a();
		vo.fg3_pct = tst.getFg3_pct();
		vo.fg2 = tst.getFg2();
		vo.fg2a = tst.getFg2a();
		vo.fg2_pct = tst.getFg2_pct();
		vo.ft = tst.getFt();
		vo.fta = tst.getFta();
		vo.ft_pct = tst.getFt_pct();
		vo.orb = tst.getOrb();
		vo.drb = tst.getDrb();
		vo.trb = tst.getTrb();
		vo.ast = tst.getAst();
		vo.stl = tst.getStl();
		vo.blk = tst.getBlk();
		vo.tov = tst.getTov();
		vo.pf = tst.getPf();
		vo.pts = tst.getPts();	
		return vo;
	}
	
	private TeamPerGameVO getTeamPerGameVO(TeamStatsPerGame tsp) {
		if(tsp==null)
			return null;
		TeamPerGameVO vo = new TeamPerGameVO();
		vo.abbr = tsp.getAbbr();
		vo.season = tsp.getSeason();
		vo.minute = tsp.getMinute();
		vo.fg = tsp.getFg();
		vo.fga = tsp.getFga();
		vo.fga_pct = tsp.getFga_pct();
		vo.fg3 = tsp.getFg3();
		vo.fg3a = tsp.getFg3a();
		vo.fg3_pct = tsp.getFg3_pct();
		vo.fg2 = tsp.getFg2();
		vo.fg2a = tsp.getFg2a();
		vo.fg2_pct = tsp.getFg2_pct();
		vo.ft = tsp.getFt();
		vo.fta = tsp.getFta();
		vo.ft_pct = tsp.getFt_pct();
		vo.orb = tsp.getOrb();
		vo.drb = tsp.getDrb();
		vo.trb = tsp.getTrb();
		vo.ast = tsp.getAst();
		vo.stl = tsp.getStl();
		vo.blk = tsp.getBlk();
		vo.tov = tsp.getTov();
		vo.pf = tsp.getPf();
		vo.pts = tsp.getPts();	
		return vo;
	}

	private TeamOppTotalVO getOppTotalVO(OpponentStatsTotal ost) {
		if(ost==null)
			return null;
		TeamOppTotalVO vo = new TeamOppTotalVO();
		vo.abbr = ost.getAbbr();
		vo.season = ost.getSeason();
		vo.num_of_game = ost.getNum_of_game();
		vo.minute = ost.getMinute();
		vo.fg = ost.getFg();
		vo.fga = ost.getFga();
		vo.fga_pct = ost.getFga_pct();
		vo.fg3 = ost.getFg3();
		vo.fg3a = ost.getFg3a();
		vo.fg3_pct = ost.getFg3_pct();
		vo.fg2 = ost.getFg2();
		vo.fg2a = ost.getFg2a();
		vo.fg2_pct = ost.getFg2_pct();
		vo.ft = ost.getFt();
		vo.fta = ost.getFta();
		vo.ft_pct = ost.getFt_pct();
		vo.orb = ost.getOrb();
		vo.drb = ost.getDrb();
		vo.trb = ost.getTrb();
		vo.ast = ost.getAst();
		vo.stl = ost.getStl();
		vo.blk = ost.getBlk();
		vo.tov = ost.getTov();
		vo.pf = ost.getPf();
		vo.pts = ost.getPts();	
		return vo;		
	}

	private TeamOppPerGameVO getOppPerGameVO(OpponentStatsPerGame osp) {
		if(osp==null)
			return null;
		TeamOppPerGameVO vo = new TeamOppPerGameVO();
		vo.abbr = osp.getAbbr();
		vo.season = osp.getSeason();
		vo.minute = osp.getMinute();
		vo.fg = osp.getFg();
		vo.fga = osp.getFga();
		vo.fga_pct = osp.getFga_pct();
		vo.fg3 = osp.getFg3();
		vo.fg3a = osp.getFg3a();
		vo.fg3_pct = osp.getFg3_pct();
		vo.fg2 = osp.getFg2();
		vo.fg2a = osp.getFg2a();
		vo.fg2_pct = osp.getFg2_pct();
		vo.ft = osp.getFt();
		vo.fta = osp.getFta();
		vo.ft_pct = osp.getFt_pct();
		vo.orb = osp.getOrb();
		vo.drb = osp.getDrb();
		vo.trb = osp.getTrb();
		vo.ast = osp.getAst();
		vo.stl = osp.getStl();
		vo.blk = osp.getBlk();
		vo.tov = osp.getTov();
		vo.pf = osp.getPf();
		vo.pts = osp.getPts();	
		return vo;
	}

	private HotTeamInfoVO getHotTeamToVO(HotTeamInfo info) {
		if(info==null)
			return null;
		HotTeamInfoVO vo = new HotTeamInfoVO();
		vo.name = info.getName();
		vo.abbr = info.getAbbr();
		vo.field = info.getField();
		vo.value = info.getValue();
		vo.season = info.getSeason();
		vo.league = info.getLeague();
		return vo;
	}
	
	private TeamAdvancedVO getAdvancedToVO(
			TeamStatsAdvanced tsa) {
		if(tsa == null){
			return null;
		}
		TeamAdvancedVO vo = new TeamAdvancedVO();
		vo.abbr = tsa.getAbbr();
		vo.season = tsa.getSeason();
		vo.pw = tsa.getPw();
		vo.pl = tsa.getPl();
		vo.mov = tsa.getMov();
		vo.sos = tsa.getSos();
		vo.srs = tsa.getSrs();
		vo.off_rtg = tsa.getOff_rtg();
		vo.def_rtg = tsa.getDef_rtg();
		vo.pace = tsa.getPace();
		vo.fta_per_fga_pct = tsa.getFta_per_fga_pct();
		vo.fg3a_per_fga_pct = tsa.getFg3a_per_fga_pct();
		vo.off_efg_pct = tsa.getOff_efg_pct();
		vo.off_tov_pct = tsa.getOff_tov_pct();
		vo.orb_pct = tsa.getOrb_pct();
		vo.off_ft_rate = tsa.getOff_ft_rate();
		vo.opp_efg_pct = tsa.getOff_efg_pct();
		vo.opp_tov_pct = tsa.getOpp_tov_pct();
		vo.drb_pct = tsa.getDrb_pct();
		vo.opp_ft_rate = tsa.getOpp_ft_rate();
		vo.arena = tsa.getArena();
		vo.attendance = tsa.getAttendance();
		return vo;
	}
	
}
