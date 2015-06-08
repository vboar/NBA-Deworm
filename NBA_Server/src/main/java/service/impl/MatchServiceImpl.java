package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import service.MatchService;
import vo.MatchFilter;
import vo.MatchInfoVO;
import vo.MatchPlayerAdvancedVO;
import vo.MatchPlayerBasicVO;
import dao.MatchDao;
import dao.impl.DaoFactoryImpl;
import entity.MatchInfo;
import entity.MatchPlayerAdvanced;
import entity.MatchPlayerBasic;

/**
 * MatchService 的具体实现
 * 
 * created by JaneLDQ on 2015年5月31日 下午8:26:38
 */
public class MatchServiceImpl extends UnicastRemoteObject implements MatchService {
	
	private static final long serialVersionUID = 1L;

	private MatchDao mdao;
	
	public MatchServiceImpl() throws RemoteException {
		super();
		mdao = DaoFactoryImpl.getDaoFactory().getMatchDao();
	}

	@Override
	public MatchInfoVO getMatchInfoByGameId(String gameid)
			throws RemoteException {
		return getInfoVO(mdao.getMatchInfoByGameId(gameid));
	}

	@Override
	public List<MatchInfoVO> getRegularMatchInfoBySeason(String season) throws RemoteException {
		List<MatchInfoVO> volist = new ArrayList<MatchInfoVO>();
		List<MatchInfo> list = mdao.getRegularMatchInfoBySeason(season);
		for(MatchInfo info: list){
			MatchInfoVO vo = getInfoVO(info);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<MatchInfoVO> getPlayOffMatchInfoBySeason(String season) throws RemoteException {
		List<MatchInfoVO> volist = new ArrayList<MatchInfoVO>();
		List<MatchInfo> list = mdao.getPlayOffMatchInfoBySeason(season);
		for(MatchInfo info: list){
			MatchInfoVO vo = getInfoVO(info);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<MatchInfoVO> getMatchInfoByDate(String begin, String end) throws RemoteException{
		List<MatchInfoVO> volist = new ArrayList<MatchInfoVO>();
		List<MatchInfo> list = mdao.getMatchInfoByDate(begin, end);
		for(MatchInfo info: list){
			MatchInfoVO vo = getInfoVO(info);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<MatchPlayerAdvancedVO> getMatchPlayerAdvancedByGameIdTeam(
			String gameid, String abbr) throws RemoteException {
		List<MatchPlayerAdvancedVO> volist = new ArrayList<MatchPlayerAdvancedVO>();
		List<MatchPlayerAdvanced> list = mdao.getMatchPlayerAdvancedByGameIdTeam(gameid, abbr);
		for(MatchPlayerAdvanced mpa: list){
			MatchPlayerAdvancedVO vo = getAdvancedVO(mpa);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<MatchPlayerBasicVO> getMatchPlayerBasicByGameIdTeam(
			String gameid, String abbr) throws RemoteException {
		List<MatchPlayerBasicVO> volist = new ArrayList<MatchPlayerBasicVO>();
		List<MatchPlayerBasic> list = mdao.getMatchPlayerBasicByGameIdTeam(gameid, abbr);
		for(MatchPlayerBasic mpb:list){
			MatchPlayerBasicVO vo = getBasicVO(mpb);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<MatchInfoVO> getMatchInfoByFilter(MatchFilter filter)
			throws RemoteException {
		List<MatchInfo> list = mdao.getMatchInfoByFilter(filter);
		List<MatchInfoVO> volist = new ArrayList<MatchInfoVO>();
		for(MatchInfo info:list){
			MatchInfoVO vo = getInfoVO(info);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}
	
	private MatchInfoVO getInfoVO(MatchInfo info) {
		if(info==null)
			return null;
		MatchInfoVO vo = new MatchInfoVO();
		vo.game_id = info.getGame_id();
		vo.season = info.getSeason();
		vo.date = info.getDate();
		vo.is_normal = info.isIs_normal()==0?false:true;
		vo.location = info.getLocation();
		vo.home_team = info.getHome_team();
		vo.home_point = info.getHome_point();
		vo.guest_team = info.getGuest_team();
		vo.guest_point = info.getGuest_point();
		vo.time = info.getTime();
		vo.home_pts = info.getHome_pts();
		vo.guest_pts = info.getGuest_pts();
		return vo;
	}
	
	private MatchPlayerAdvancedVO getAdvancedVO(MatchPlayerAdvanced mpa) {
		if(mpa==null)
			return null;
		MatchPlayerAdvancedVO vo = new MatchPlayerAdvancedVO();
		vo.game_id = mpa.getGame_id();
		vo.player_name = mpa.getPlayer_name();
		vo.team_abbr = mpa.getTeam_abbr();
		vo.starter = mpa.getStarter();
		vo.minute = mpa.getMinute();
		vo.ts_pct = mpa.getTs_pct();
		vo.efg_pct = mpa.getEfg_pct();
		vo.fa3a_per_fga_pct = mpa.getFa3a_per_fga_pct();
		vo.fta_per_fga_pct = mpa.getFta_per_fga_pct();
		vo.orb_pct = mpa.getOrb_pct();
		vo.drb_pct = mpa.getDrb_pct();
		vo.trb_pct = mpa.getTrb_pct();
		vo.ast_pct = mpa.getAst_pct();
		vo.stl_pct = mpa.getStl_pct();
		vo.tov_pct = mpa.getTov_pct();
		vo.blk_pct = mpa.getBlk_pct();
		vo.usg_pct = mpa.getUsg_pct();
		vo.off_rtg = mpa.getOff_rtg();
		vo.def_rtg = mpa.getDef_rtg();
		return vo;
	}
	
	private MatchPlayerBasicVO getBasicVO(MatchPlayerBasic mpb) {
		if(mpb==null)
			return null;
		MatchPlayerBasicVO vo = new MatchPlayerBasicVO();
		vo.game_id = mpb.getGame_id();
		vo.player_name = mpb.getPlayer_name();
		vo.team_abbr = mpb.getTeam_abbr();
		vo.starter = mpb.getStarter();
		vo.minute = mpb.getMinute();
		vo.fg = mpb.getFg();
		vo.fga = mpb.getFga();
		vo.fga_pct = mpb.getFga_pct();
		vo.fg3 = mpb.getFg3();
		vo.fg3a = mpb.getFg3a();
		vo.fg3_pct = mpb.getFg3_pct();
		vo.ft = mpb.getFt();
		vo.fta = mpb.getFta();
		vo.ft_pct = mpb.getFt_pct();
		vo.orb = mpb.getOrb();
		vo.drb = mpb.getDrb();
		vo.trb = mpb.getTrb();
		vo.ast = mpb.getAst();
		vo.stl = mpb.getStl();
		vo.blk = mpb.getBlk();
		vo.tov = mpb.getTov();
		vo.pf = mpb.getPf();
		vo.pts = mpb.getPts();	
		vo.plus_minus = mpb.getPlus_minus();
		return vo;
	}

}
