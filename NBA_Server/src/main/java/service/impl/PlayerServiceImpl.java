package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

import service.PlayerService;
import util.FieldType;
import vo.HotPlayerInfoVO;
import vo.PlayerAdvancedVO;
import vo.PlayerFilter;
import vo.PlayerInfoVO;
import vo.PlayerPerGameVO;
import vo.PlayerSalaryVO;
import vo.PlayerTotalVO;
import dao.PlayerDao;
import dao.impl.DaoFactoryImpl;
import entity.HotPlayerInfo;
import entity.PlayerInfo;
import entity.PlayerSalary;
import entity.PlayerStatsAdvanced;
import entity.PlayerStatsPerGame;
import entity.PlayerStatsTotal;

/**
 * PlayerService的具体实现
 * 
 * created by JaneLDQ on 2015年5月31日 下午8:26:53
 */
public class PlayerServiceImpl extends UnicastRemoteObject implements PlayerService {
	
	private static final long serialVersionUID = 1L;
	
	private PlayerDao pdao;
	
	public PlayerServiceImpl() throws RemoteException {
		super();
		pdao = DaoFactoryImpl.getDaoFactory().getPlayerDao();
	}
	
	@Override
	public List<ImageIcon> getPlayerPortraitByNameList(List<String> names) throws RemoteException{
		return pdao.getPlayerPortraitByNameList(names);
	}

	@Override
	public ImageIcon getPlayerPortraitByName(String name) throws RemoteException{
		return pdao.getPlayerPortraitByName(name);
	}

	@Override
	public List<String> getNameList(String str) throws RemoteException {
		return pdao.getNameList(str);
	}

	@Override
	public List<String> getNameByNameInitial(String initial)
			throws RemoteException {
		return pdao.getNameByNameInitial(initial);
	}

    @Override
    public List<PlayerInfoVO> getAllPlayerInfo() throws RemoteException {
        List<PlayerInfoVO> volist = new ArrayList<PlayerInfoVO>();
        List<PlayerInfo> list = pdao.getAllPlayerInfo();
        for(PlayerInfo info: list){
            PlayerInfoVO vo = getInfoVO(info);
            if(vo!=null)
                volist.add(vo);
        }
        return volist;
    }

    @Override
	public PlayerInfoVO getPlayerInfoByName(String name) throws RemoteException {
		PlayerInfo info = pdao.getPlayerInfoByName(name);
		return getInfoVO(info);
	}

	@Override
	public List<PlayerTotalVO> getPlayerTotalByName(String name, int regular)
			throws RemoteException {
		List<PlayerTotalVO> volist = new ArrayList<PlayerTotalVO>();
		List<PlayerStatsTotal> list = pdao.getPlayerTotalByName(name, regular);
		list = getSumTotalList(list);
		for(PlayerStatsTotal pst: list){
			PlayerTotalVO vo = getTotalVO(pst);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<PlayerPerGameVO> getPlayerPerGameByName(String name, int regular)
			throws RemoteException {
		List<PlayerPerGameVO> volist = new ArrayList<PlayerPerGameVO>();
		List<PlayerStatsPerGame> list = pdao.getPlayerPerGameByName(name,regular);
		list = getSumPerGameList(list);
		for(PlayerStatsPerGame psp:list){
			PlayerPerGameVO vo = getPerGameVO(psp);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<PlayerAdvancedVO> getPlayerAdvancedByName(String name, int regular)
			throws RemoteException {
		List<PlayerAdvancedVO> volist = new ArrayList<PlayerAdvancedVO>();
		List<PlayerStatsAdvanced> list = pdao.getPlayerAdvancedByName(name, regular);
		list = getSumAdvancedList(list);
		for(PlayerStatsAdvanced psa: list){
			PlayerAdvancedVO vo = getAdvancedVO(psa);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<PlayerSalaryVO> getPlayerSalaryByName(String name)
			throws RemoteException {
		List<PlayerSalaryVO> volist = new ArrayList<PlayerSalaryVO>();
		List<PlayerSalary> list = pdao.getPlayerSalaryByName(name);
		for(PlayerSalary ps: list){
			PlayerSalaryVO vo = getSalaryVO(ps);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<PlayerTotalVO> getPlayerTotalBySeason(String season, int regular)
			throws RemoteException {
		List<PlayerTotalVO> volist = new ArrayList<PlayerTotalVO>();
		List<PlayerStatsTotal> list = pdao.getPlayerTotalBySeason(season,regular);
		for(PlayerStatsTotal pst: list){
			PlayerTotalVO vo = getTotalVO(pst);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<PlayerPerGameVO> getPlayerPerGameBySeason(String season, int regular)
			throws RemoteException {
		List<PlayerPerGameVO> volist = new ArrayList<PlayerPerGameVO>();
		List<PlayerStatsPerGame> list = pdao.getPlayerPerGameBySeason(season, regular);
		for(PlayerStatsPerGame psp:list){
			PlayerPerGameVO vo = getPerGameVO(psp);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<PlayerAdvancedVO> getPlayerAdvancedBySeason(String season, int regular)
			throws RemoteException {
		List<PlayerAdvancedVO> volist = new ArrayList<PlayerAdvancedVO>();
		List<PlayerStatsAdvanced> list = pdao.getPlayerAdvancedBySeason(season,regular);
		for(PlayerStatsAdvanced psa: list){
			PlayerAdvancedVO vo = getAdvancedVO(psa);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<PlayerTotalVO> getPlayerTotalBySeasonName(String season, String name, int regular)
			throws RemoteException {
		List<PlayerTotalVO> volist = new ArrayList<PlayerTotalVO>();
		List<PlayerStatsTotal> list = pdao.getPlayerTotalBySeasonName(season, name, regular);
		for(PlayerStatsTotal pst: list){
			PlayerTotalVO vo = getTotalVO(pst);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<PlayerPerGameVO> getPlayerPerGameBySeasonName(String season,
			String name, int regular) throws RemoteException {
		List<PlayerPerGameVO> volist = new ArrayList<PlayerPerGameVO>();
		List<PlayerStatsPerGame> list = pdao.getPlayerPerGameBySeasonName(season, name, regular);
		for(PlayerStatsPerGame pst: list){
			PlayerPerGameVO vo = getPerGameVO(pst);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<PlayerAdvancedVO> getPlayerAdvancedBySeasonName(String season,
			String name, int regular) throws RemoteException {
		List<PlayerAdvancedVO> volist = new ArrayList<PlayerAdvancedVO>();
		List<PlayerStatsAdvanced> list = pdao.getPlayerAdvancedBySeasonName(season, name, regular);
		for(PlayerStatsAdvanced pst: list){
			PlayerAdvancedVO vo = getAdvancedVO(pst);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}
	
	@Override
	public List<PlayerPerGameVO> getPlayerPerGameByFilter(PlayerFilter filter)
			throws RemoteException {
		List<PlayerStatsPerGame> list = pdao.getPlayerPerGameByFilter(filter);
		List<PlayerPerGameVO> volist = new ArrayList<PlayerPerGameVO>();
		for(PlayerStatsPerGame psp: list){
			PlayerPerGameVO vo = getPerGameVO(psp);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<PlayerTotalVO> getPlayerTotalByFilter(PlayerFilter filter)
			throws RemoteException {
		List<PlayerStatsTotal> list = pdao.getPlayerTotalByFilter(filter);
		List<PlayerTotalVO> volist = new ArrayList<PlayerTotalVO>();
		for(PlayerStatsTotal pst: list){
			PlayerTotalVO vo = getTotalVO(pst);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<PlayerAdvancedVO> getPlayerAdvancedByFilter(PlayerFilter filter)
			throws RemoteException {
		List<PlayerStatsAdvanced> list = pdao.getPlayerAdvancedByFilter(filter);
		List<PlayerAdvancedVO> volist = new ArrayList<PlayerAdvancedVO>();
		for(PlayerStatsAdvanced psp: list){
			PlayerAdvancedVO vo = getAdvancedVO(psp);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}
	
	@Override
	public List<PlayerInfoVO> getTeamPlayerBySeason(String season, String abbr)
			throws RemoteException {
		List<PlayerInfoVO> volist = new ArrayList<PlayerInfoVO>();
		List<PlayerInfo> list = pdao.getTeamPlayerBySeason(season, abbr);
		for(PlayerInfo info: list){
			PlayerInfoVO vo = getInfoVO(info);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<PlayerSalaryVO> getPlayerSalaryBySeason(String season, String name)
			throws RemoteException {
		List<PlayerSalary> list = pdao.getPlayerSalaryBySeason(season,name);
		List<PlayerSalaryVO> volist = new ArrayList<PlayerSalaryVO>();
		for(PlayerSalary ps: list){
			PlayerSalaryVO vo = getSalaryVO(ps);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}
	@Override
	public List<HotPlayerInfoVO> getSeasonHotPlayer(String season,
			FieldType field, int num) throws RemoteException {
		List<HotPlayerInfoVO> volist = new ArrayList<HotPlayerInfoVO>();
		List<HotPlayerInfo> list = pdao.getHotPlayerBySeason(season, field,num);
		for(HotPlayerInfo info : list){
			HotPlayerInfoVO vo = getHotPlayerToVO(info);
			if(vo!=null)
				volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<String> getTeamByPlayerNameSeason(String name, String season)
			throws RemoteException {
		return pdao.getTeamByPlayerNameSeason(name, season);
	}
	
	private PlayerSalaryVO getSalaryVO(PlayerSalary ps) {
		if(ps==null)
			return null;
		PlayerSalaryVO vo = new PlayerSalaryVO();
		vo.name = ps.getName();
		vo.season = ps.getSeason();
		vo.team = ps.getTeam();
		vo.salary = ps.getSalary();
		return vo;
	}
	
	private PlayerInfoVO getInfoVO(PlayerInfo info) {
		if(info==null)
			return null;
		PlayerInfoVO vo = new PlayerInfoVO();
		vo.name = info.getName();
		vo.born = info.getBorn();
		vo.hometown = info.getHometown();
		vo.position = info.getPosition();
		vo.height = info.getHeight();
		vo.weight = info.getWeight();
		vo.shoots = info.getShoots();
		vo.high_school = info.getHigh_school();
		vo.college = info.getCollege();
		vo.draft = info.getDraft();
		vo.debut = info.getDebut();
		vo.exp = info.getExperience();
		vo.number = info.getNumber();
		return vo;
	}
	
	private PlayerPerGameVO getPerGameVO(PlayerStatsPerGame psp) {
		if(psp==null)
			return null;
		PlayerPerGameVO vo = new PlayerPerGameVO();
		vo.name = psp.getName();
		vo.season = psp.getSeason();
		vo.is_normal = psp.getIs_normal()==0?false:true;
		if(psp.getTeam().endsWith("/"))
			vo.team = psp.getTeam().substring(0,psp.getTeam().length()-1);
		else
			vo.team = psp.getTeam();
		vo.position = psp.getPosition();
		vo.game = psp.getNum_Of_Game();
		vo.game_started = psp.getGame_started();
		vo.minute = psp.getMinute();
		vo.fg = psp.getFg();
		vo.fga = psp.getFga();
		vo.fga_pct = psp.getFga_pct();
		vo.fg3 = psp.getFg3();
		vo.fg3a = psp.getFg3a();
		vo.fg3_pct = psp.getFg3_pct();
		vo.fg2 = psp.getFg2();
		vo.fg2a = psp.getFg2a();
		vo.fg2_pct = psp.getFg2_pct();
		vo.efg_pct = psp.getEfg_pct();
		vo.ft = psp.getFt();
		vo.fta = psp.getFta();
		vo.ft_pct = psp.getFt_pct();
		vo.orb = psp.getOrb();
		vo.drb = psp.getDrb();
		vo.trb = psp.getTrb();
		vo.ast = psp.getAst();
		vo.stl = psp.getStl();
		vo.blk = psp.getBlk();
		vo.tov = psp.getTov();
		vo.pf = psp.getPf();
		vo.pts = psp.getPts();	
		return vo;
	}
	
	private PlayerAdvancedVO getAdvancedVO(PlayerStatsAdvanced psa) {
		if(psa==null)	return null;
		PlayerAdvancedVO vo = new PlayerAdvancedVO();
		vo.name = psa.getName();
		vo.season = psa.getSeason();
		vo.is_normal = psa.getIs_normal()==0?false:true;
		if(psa.getTeam().endsWith("/"))
			vo.team = psa.getTeam().substring(0,psa.getTeam().length()-1);
		else
			vo.team = psa.getTeam();
		vo.position = psa.getPosition();
		vo.game = psa.getNum_Of_Game();
		vo.minute = psa.getMinute();
		vo.per = psa.getPer();
		vo.ts_pct = psa.getTs_pct();
		vo.fa3a_per_fga_pct = psa.getFa3a_per_fga_pct();
		vo.fta_per_fga_pct = psa.getFta_per_fga_pct();
		vo.orb_pct = psa.getOrb_pct();
		vo.drb_pct = psa.getDrb_pct();
		vo.trb_pct = psa.getTrb_pct();
		vo.ast_pct = psa.getAst_pct();
		vo.stl_pct = psa.getStl_pct();
		vo.tov_pct = psa.getTov_pct();
		vo.blk_pct = psa.getBlk_pct();
		vo.usg_pct = psa.getUsg_pct();
		vo.ows = psa.getOws();
		vo.dws = psa.getDws();
		vo.ws = psa.getWs();
		vo.ws_48 = psa.getWs_48();
		vo.obpm = psa.getObpm();
		vo.dbpm = psa.getDbpm();
		vo.bpm = psa.getBpm();
		vo.vorp = psa.getVorp();
		return vo;
	}
	
	private PlayerTotalVO getTotalVO(PlayerStatsTotal pst) {
		if(pst==null)
			return null;
		PlayerTotalVO vo = new PlayerTotalVO();
		vo.name = pst.getName();
		vo.season = pst.getSeason();
		vo.is_normal = pst.getIs_normal()==0? false : true;
		if(pst.getTeam().endsWith("/"))
			vo.team = pst.getTeam().substring(0,pst.getTeam().length()-1);
		else
			vo.team = pst.getTeam();
		vo.position = pst.getPosition();
		vo.game = pst.getNum_Of_Game();
		vo.game_started = pst.getGame_started();
		vo.minute = pst.getMinute();
		vo.fg = pst.getFg();
		vo.fga = pst.getFga();
		vo.fga_pct = pst.getFga_pct();
		vo.fg3 = pst.getFg3();
		vo.fg3a = pst.getFg3a();
		vo.fg3_pct = pst.getFg3_pct();
		vo.fg2 = pst.getFg2();
		vo.fg2a = pst.getFg2a();
		vo.fg2_pct = pst.getFg2_pct();
		vo.efg_pct = pst.getEfg_pct();
		vo.ft = pst.getFt();
		vo.fta = pst.getFta();
		vo.ft_pct = pst.getFt_pct();
		vo.orb = pst.getOrb();
		vo.drb = pst.getDrb();
		vo.trb = pst.getTrb();
		vo.ast = pst.getAst();
		vo.stl = pst.getStl();
		vo.blk = pst.getBlk();
		vo.tov = pst.getTov();
		vo.pf = pst.getPf();
		vo.pts = pst.getPts();	
		return vo;
	}

	private HotPlayerInfoVO getHotPlayerToVO(HotPlayerInfo info) {
		if(info==null)
			return null;
		HotPlayerInfoVO vo = new HotPlayerInfoVO();
		vo.name = info.getName();
		vo.team = info.getTeam();
		vo.position = info.getPosition();
		vo.field = info.getField();
		vo.value = info.getValue();
		vo.season = info.getSeason();
		return vo;
	}

	private List<PlayerStatsTotal> getSumTotalList(List<PlayerStatsTotal> list){
		if(list.size()==0)
			return list;
		String season = list.get(0).getSeason();
		String temp_t = "";
		Map<String ,String> map = new HashMap<String,String>();
		for (int i = 1; i < list.size(); ++i) {
			PlayerStatsTotal p = list.get(i);
			if (p.getSeason().equals(season)) {
				if (!p.getTeam().equals("TOT")) {
					temp_t += p.getTeam() + "/";
					map.put(p.getSeason(), temp_t);
					list.remove(i);
					i = i-1;
				}
			}else{
				temp_t = "";
			}	
			season = p.getSeason();
		}
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Entry<String, String> entry = (Entry<String, String>)iter.next();
			String key = entry.getKey();
			for(PlayerStatsTotal p: list){	
				if(p.getSeason().equals(key)){
					p.setTeam(map.get(key));
				}
			}
		}
		return list;
	}
	
	private List<PlayerStatsPerGame> getSumPerGameList(List<PlayerStatsPerGame> list){
		if(list.size()==0)
			return list;
		String season = list.get(0).getSeason();
		String temp_t = "";
		Map<String ,String> map = new HashMap<String,String>();
		for (int i = 1; i < list.size(); ++i) {
			PlayerStatsPerGame p = list.get(i);
			if (p.getSeason().equals(season)) {
				if (!p.getTeam().equals("TOT")) {
					temp_t += p.getTeam() + "/";
					map.put(p.getSeason(), temp_t);
					list.remove(i);
					i = i-1;
				}
			}else{
				temp_t = "";
			}	
			season = p.getSeason();
		}
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Entry<String, String> entry = (Entry<String, String>)iter.next();
			String key = entry.getKey();
			for(PlayerStatsPerGame p: list){	
				if(p.getSeason().equals(key)){
					p.setTeam(map.get(key));
				}
			}
		}
		return list;
	}
	
	private List<PlayerStatsAdvanced> getSumAdvancedList(List<PlayerStatsAdvanced> list){
		if(list.size()==0)
			return list;
		String season = list.get(0).getSeason();
		String temp_t = "";
		Map<String ,String> map = new HashMap<String,String>();
		for (int i = 1; i < list.size(); ++i) {
			PlayerStatsAdvanced p = list.get(i);
			if (p.getSeason().equals(season)) {
				if (!p.getTeam().equals("TOT")) {
					temp_t += p.getTeam() + "/";
					map.put(p.getSeason(), temp_t);
					list.remove(i);
					i = i-1;
				}
			}else{
				temp_t = "";
			}	
			season = p.getSeason();
		}
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Entry<String, String> entry = (Entry<String, String>)iter.next();
			String key = entry.getKey();
			for(PlayerStatsAdvanced p: list){	
				if(p.getSeason().equals(key)){
					p.setTeam(map.get(key));
				}
			}
		}
		return list;
	}
	
}
