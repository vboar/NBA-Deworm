package api;

import java.rmi.RemoteException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import service.TeamService;
import service.impl.ServiceFactoryImpl;
import vo.TeamAdvancedVO;
import vo.TeamInfoVO;
import vo.TeamOppPerGameVO;
import vo.TeamOppTotalVO;
import vo.TeamPerGameVO;
import vo.TeamTotalVO;

/**
 * Created by Vboar on 2015/6/3.
 */
public class TeamAPI {

    private TeamService ts;

    public TeamAPI() {
        try {
            ts = ServiceFactoryImpl.getInstance().getTeamService();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getStr(String str) {
        if (str.equals("/all") || str.equals("/all/")) {
            // command is /team/all or /team/all/
            return all();
        }
        if (str.startsWith("?abbr=")) {
            // command is /team?abbr={}
            return abbr(str);
        }
        if (str.length() == 0 || str.equals("/")) {
            return "Team API.";
        }
        return APIServer.NOTSUPPORT;
    }

    private String all() {
        try {
            List<TeamInfoVO> list = ts.getAllTeamInfo();
            JSONArray teams = new JSONArray();
            for (TeamInfoVO vo: list) {
                teams.put(getSimpleTeamInfoJO(vo));
            }
            return teams.toString();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return APIServer.ERROR;
    }

    private String abbr(String str) {
        if (str.length() == 9) {
            // command is /team?abbr={}
            String abbr = str.substring(6);
            try {
                JSONObject jo = new JSONObject();
                jo.put("abbr", abbr);
                TeamInfoVO vo = ts.getTeamInfoByAbbr(abbr);
                if (vo == null) return APIServer.NOTFOUND;
                jo.put("info", getTeamInfoJO(vo));
                return jo.toString();
            } catch (RemoteException e) {
                e.printStackTrace();
                return APIServer.ERROR;
            }
        }
        String abbr = "";
        String season = null;
        boolean total = false;
        boolean pergame = false;
        boolean advanced = false;
        boolean opptotal = false;
        boolean opppergame = false;

        Pattern p = Pattern.compile("season=.{5}");
        Matcher m = p.matcher(str);
        if (m.find()) {
            season = m.group().substring(7);
        }
        p = Pattern.compile("abbr=.{3}");
        m = p.matcher(str);
        if (m.find()) {
            abbr = m.group().substring(5);
        }
        p = Pattern.compile("total=[0-1]");
        m = p.matcher(str);
        if (m.find()) {
            String temp = m.group().substring(6);
            if (temp.equals("1")) total = true;
        }
        p = Pattern.compile("pergame=[0-1]");
        m = p.matcher(str);
        if (m.find()) {
            String temp = m.group().substring(8);
            if (temp.equals("1")) pergame = true;
        }
        p = Pattern.compile("advanced=[0-1]");
        m = p.matcher(str);
        if (m.find()) {
            String temp = m.group().substring(9);
            if (temp.equals("1")) advanced = true;
        }
        p = Pattern.compile("opptotal=[0-1]");
        m = p.matcher(str);
        if (m.find()) {
            String temp = m.group().substring(9);
            if (temp.equals("1")) opptotal = true;
        }
        p = Pattern.compile("opppergame=[0-1]");
        m = p.matcher(str);
        if (m.find()) {
            String temp = m.group().substring(11);
            if (temp.equals("1")) opppergame = true;
        }

        if (season == null) {
            JSONObject jo = new JSONObject();
            jo.put("abbr", abbr);
            try {
                TeamInfoVO vo = ts.getTeamInfoByAbbr(abbr);
                if (vo == null) return APIServer.NOTFOUND;
                jo.put("info", getTeamInfoJO(vo));
                if (total) {
                    List<TeamTotalVO> list1 = ts.getTeamTotalByAbbr(abbr);
                    JSONObject jo2 = new JSONObject();
                    for (TeamTotalVO temp: list1) {
                        jo2.put(temp.season, getTeamTotalJO(temp));
                    }
                    jo.put("total", jo2);
                }
                if (pergame) {
                    List<TeamPerGameVO> list1 = ts.getTeamPerGameByAbbr(abbr);
                    JSONObject jo2 = new JSONObject();
                    for (TeamPerGameVO temp: list1) {
                        jo2.put(temp.season, getTeamPerGameJO(temp));
                    }
                    jo.put("pergame", jo2);
                }
                if (advanced) {
                    List<TeamAdvancedVO> list = ts.getTeamAdvancedByAbbr(abbr);
                    JSONObject jo2 = new JSONObject();
                    for (TeamAdvancedVO temp: list) {
                        jo2.put(temp.season, getTeamAdvancedJO(temp));
                    }
                    jo.put("advanced", jo2);
                }
                if (opptotal) {
                    List<TeamOppTotalVO> list = ts.getTeamOppTotalByAbbr(abbr);
                    JSONObject jo2 = new JSONObject();
                    for (TeamOppTotalVO temp: list) {
                        jo2.put(temp.season, getTeamOppTotalJO(temp));
                    }
                    jo.put("opptotal", jo2);
                }
                if (opppergame) {
                    List<TeamOppPerGameVO> list = ts.getTeamOppPerGameByAbbr(abbr);
                    JSONObject jo2 = new JSONObject();
                    for (TeamOppPerGameVO temp: list) {
                        jo2.put(temp.season, getTeamOppPergameJO(temp));
                    }
                    jo.put("opppergame", jo2);
                }
                return jo.toString();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }


        try {
            JSONObject jo = new JSONObject();
            jo.put("abbr", abbr);
            TeamInfoVO vo = ts.getTeamInfoByAbbr(abbr);
            if (vo == null) return APIServer.NOTFOUND;

            jo.put("info", getTeamInfoJO(vo));
            if (total) {
                JSONObject jo2 = new JSONObject();
                TeamTotalVO ttvo = ts.getTeamTotalBySeasonAbbr(season, abbr);
                if (ttvo == null) return APIServer.NOTFOUND;
                jo2.put(season, getTeamTotalJO(ttvo));
                jo.put("total", jo2);
            }
            if (pergame) {
                JSONObject jo2 = new JSONObject();
                TeamPerGameVO tpvo = ts.getTeamPerGameBySeasonAbbr(season, abbr);
                if (tpvo == null) return APIServer.NOTFOUND;
                jo2.put(season, getTeamPerGameJO(tpvo));
                jo.put("pergame", jo2);
            }
            if (advanced) {
                JSONObject jo2 = new JSONObject();
                TeamAdvancedVO tavo = ts.getTeamAdvancedBySeasonAbbr(season, abbr);
                if (tavo == null) return APIServer.NOTFOUND;
                jo2.put(season, getTeamAdvancedJO(tavo));
                jo.put("advanced", jo2);
            }
            if (opptotal) {
                JSONObject jo2 = new JSONObject();
                TeamOppTotalVO topvo = ts.getTeamOppTotalBySeasonAbbr(season, abbr);
                if (topvo == null) return APIServer.NOTFOUND;
                jo2.put(season, getTeamOppTotalJO(topvo));
                jo.put("opptotal", jo2);
            }
            if (opppergame) {
                JSONObject jo2 = new JSONObject();
                TeamOppPerGameVO topgvo = ts.getTeamOppPerGameBySeasonAbbr(season, abbr);
                if (topgvo == null) return APIServer.NOTFOUND;
                jo2.put(season, getTeamOppPergameJO(topgvo));
                jo.put("opppergame", jo2);
            }
            return jo.toString();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return APIServer.NOTSUPPORT;
    }

    private JSONObject getSimpleTeamInfoJO(TeamInfoVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("name", vo.name);
        jo.put("abbr", vo.abbr);
        jo.put("buildup_time", vo.buildup_time);
        jo.put("location", vo.location);
        jo.put("division", vo.division);
        jo.put("league", vo.league);
        return jo;
    }

    private JSONObject getTeamInfoJO(TeamInfoVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("name", vo.name);
        jo.put("buildup_time", vo.buildup_time);
        jo.put("location", vo.location);
        jo.put("division", vo.division);
        jo.put("league", vo.league);
        jo.put("record", vo.record);
        jo.put("playeroff_appearance", vo.playeroff_appearance);
        jo.put("championships", vo.championships);
        return jo;
    }

    private JSONObject getTeamTotalJO(TeamTotalVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("wins", vo.wins);
        jo.put("losses", vo.losses);
        jo.put("finish", vo.finish);
        jo.put("age", vo.age);
        jo.put("height", vo.height);
        jo.put("weight", vo.weight);
        jo.put("num_of_game", vo.num_of_game);
        jo.put("minute", vo.minute);
        jo.put("fg", vo.fg);
        jo.put("fga", vo.fga);
        jo.put("fga_pct", vo.fga_pct);
        jo.put("fg3", vo.fg3);
        jo.put("fg3a", vo.fg3a);
        jo.put("fg3_pct", vo.fg3_pct);
        jo.put("fg2", vo.fg2);
        jo.put("fg2a", vo.fg2a);
        jo.put("fg2_pct", vo.fg2_pct);
        jo.put("ft", vo.ft);
        jo.put("fta", vo.fta);
        jo.put("ft_pct", vo.ft_pct);
        jo.put("orb", vo.orb);
        jo.put("drb", vo.drb);
        jo.put("trb", vo.trb);
        jo.put("ast", vo.ast);
        jo.put("stl", vo.stl);
        jo.put("blk", vo.blk);
        jo.put("tov", vo.tov);
        jo.put("pf", vo.pf);
        jo.put("pts", vo.pts);
        return jo;
    }

    private JSONObject getTeamPerGameJO(TeamPerGameVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("minute", vo.minute);
        jo.put("fg", vo.fg);
        jo.put("fga", vo.fga);
        jo.put("fga_pct", vo.fga_pct);
        jo.put("fg3", vo.fg3);
        jo.put("fg3a", vo.fg3a);
        jo.put("fg3_pct", vo.fg3_pct);
        jo.put("fg2", vo.fg2);
        jo.put("fg2a", vo.fg2a);
        jo.put("fg2_pct", vo.fg2_pct);
        jo.put("ft", vo.ft);
        jo.put("fta", vo.fta);
        jo.put("ft_pct", vo.ft_pct);
        jo.put("orb", vo.orb);
        jo.put("drb", vo.drb);
        jo.put("trb", vo.trb);
        jo.put("ast", vo.ast);
        jo.put("stl", vo.stl);
        jo.put("blk", vo.blk);
        jo.put("tov", vo.tov);
        jo.put("pf", vo.pf);
        jo.put("pts", vo.pts);
        return jo;
    }

    private JSONObject getTeamAdvancedJO(TeamAdvancedVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("pw", vo.pw);
        jo.put("pl", vo.pl);
        jo.put("mov", vo.mov);
        jo.put("sos", vo.sos);
        jo.put("srs", vo.srs);
        jo.put("off_rtg", vo.off_rtg);
        jo.put("def_rtg", vo.def_rtg);
        jo.put("pace", vo.pace);
        jo.put("fta_per_fga_pct", vo.fta_per_fga_pct);
        jo.put("fg3a_per_fga_pct", vo.fg3a_per_fga_pct);
        jo.put("off_efg_pct", vo.off_efg_pct);
        jo.put("off_tov_pct", vo.off_tov_pct);
        jo.put("orb_pct", vo.orb_pct);
        jo.put("off_ft_rate", vo.off_ft_rate);
        jo.put("opp_efg_pct", vo.opp_efg_pct);
        jo.put("opp_tov_pct", vo.opp_tov_pct);
        jo.put("drb_pct", vo.drb_pct);
        jo.put("opp_ft_rate", vo.opp_ft_rate);
        jo.put("arena", vo.arena);
        jo.put("attendance", vo.attendance);
        return jo;
    }

    private JSONObject getTeamOppTotalJO(TeamOppTotalVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("num_of_game", vo.num_of_game);
        jo.put("minute", vo.minute);
        jo.put("fg", vo.fg);
        jo.put("fga", vo.fga);
        jo.put("fga_pct", vo.fga_pct);
        jo.put("fg3", vo.fg3);
        jo.put("fg3a", vo.fg3a);
        jo.put("fg3_pct", vo.fg3_pct);
        jo.put("fg2", vo.fg2);
        jo.put("fg2a", vo.fg2a);
        jo.put("fg2_pct", vo.fg2_pct);
        jo.put("ft", vo.ft);
        jo.put("fta", vo.fta);
        jo.put("ft_pct", vo.ft_pct);
        jo.put("orb", vo.orb);
        jo.put("drb", vo.drb);
        jo.put("trb", vo.trb);
        jo.put("ast", vo.ast);
        jo.put("stl", vo.stl);
        jo.put("blk", vo.blk);
        jo.put("tov", vo.tov);
        jo.put("pf", vo.pf);
        jo.put("pts", vo.pts);
        return jo;
    }

    private JSONObject getTeamOppPergameJO(TeamOppPerGameVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("minute", vo.minute);
        jo.put("fg", vo.fg);
        jo.put("fga", vo.fga);
        jo.put("fga_pct", vo.fga_pct);
        jo.put("fg3", vo.fg3);
        jo.put("fg3a", vo.fg3a);
        jo.put("fg3_pct", vo.fg3_pct);
        jo.put("fg2", vo.fg2);
        jo.put("fg2a", vo.fg2a);
        jo.put("fg2_pct", vo.fg2_pct);
        jo.put("ft", vo.ft);
        jo.put("fta", vo.fta);
        jo.put("ft_pct", vo.ft_pct);
        jo.put("orb", vo.orb);
        jo.put("drb", vo.drb);
        jo.put("trb", vo.trb);
        jo.put("ast", vo.ast);
        jo.put("stl", vo.stl);
        jo.put("blk", vo.blk);
        jo.put("tov", vo.tov);
        jo.put("pf", vo.pf);
        jo.put("pts", vo.pts);
        return jo;
    }


}
