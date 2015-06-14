package api;

import java.rmi.RemoteException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import service.MatchService;
import service.impl.ServiceFactoryImpl;
import vo.MatchFilter;
import vo.MatchInfoVO;
import vo.MatchPlayerAdvancedVO;
import vo.MatchPlayerBasicVO;

/**
 * Created by Vboar on 2015/6/3.
 */
public class MatchAPI {

    private MatchService ms;

    public MatchAPI() {
        try {
            ms = ServiceFactoryImpl.getInstance().getMatchService();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getStr(String str) {
        if (str.startsWith("?begin")) {
            return beginEnd(str);
        }
        if (str.startsWith("?season")) {
            return season(str);
        }
        if (str.startsWith("?id=")) {
            return id(str);
        }
        if (str.length() == 0 || str.equals("/")) {
            return "Match API.";
        }
        return APIServer.NOTSUPPORT;
    }

    private String id(String str) {
        String id = null;
        boolean basic = false;
        boolean advanced = false;

        Pattern p = Pattern.compile("id=.{16}");
        Matcher m = p.matcher(str);
        if (m.find()) {
            id = m.group().substring(3);
        }
        p = Pattern.compile("basic=[0-1]");
        m = p.matcher(str);
        if (m.find()) {
            String temp = m.group().substring(6);
            if (temp.equals("1")) {
                basic = true;
            }
        }
        p = Pattern.compile("advanced=[0-1]");
        m = p.matcher(str);
        if (m.find()) {
            String temp = m.group().substring(9);
            if (temp.equals("1")) {
                advanced = true;
            }
        }

        try {
            MatchInfoVO vo = ms.getMatchInfoByGameId(id);
            if (vo == null) return APIServer.NOTFOUND;
            JSONObject jo = new JSONObject();
//            JSONObject jo1 = new JSONObject();
            jo.put("info", getMatchInfoJO(vo));
            jo.put("game_id", vo.game_id);

            List<List<Integer>> list = ms.getSectionScoreByGameId(vo.game_id);
            JSONObject jo2 = new JSONObject();
            JSONArray array = new JSONArray();
            JSONArray array1 = new JSONArray();
            for (List<Integer> l: list) {
                array.put(l.get(0));
                array1.put(l.get(1));
            }
            jo2.put(vo.home_team, array);
            array = new JSONArray();
            jo2.put(vo.guest_team, array1);
            jo.put("scores", jo2);

            if (basic) {
                List<MatchPlayerBasicVO> bList = ms.
                        getMatchPlayerBasicByGameIdTeam(vo.game_id, vo.home_team);
                array = new JSONArray();
                jo2 = new JSONObject();
                for (MatchPlayerBasicVO bvo: bList) {
                    array.put(getBasicJO(bvo));
                }
                jo2.put(vo.home_team, array);
                array = new JSONArray();
                bList = ms.
                        getMatchPlayerBasicByGameIdTeam(vo.game_id, vo.guest_team);
                for (MatchPlayerBasicVO bvo: bList) {
                    array.put(getBasicJO(bvo));
                }
                jo2.put(vo.guest_team, array);
                jo.put("basic", jo2);
            }

            if (advanced) {
                List<MatchPlayerAdvancedVO> aList = ms.
                        getMatchPlayerAdvancedByGameIdTeam(vo.game_id, vo.home_team);
                array = new JSONArray();
                jo2 = new JSONObject();
                for (MatchPlayerAdvancedVO avo: aList) {
                    array.put(getAdvancedJO(avo));
                }
                jo2.put(vo.home_team, array);
                array = new JSONArray();
                aList = ms.
                        getMatchPlayerAdvancedByGameIdTeam(vo.game_id, vo.guest_team);
                for (MatchPlayerAdvancedVO avo: aList) {
                    array.put(getAdvancedJO(avo));
                }
                jo2.put(vo.guest_team, array);
                jo.put("advanced", jo2);
            }

            return jo.toString();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return APIServer.NOTSUPPORT;
    }

    private String beginEnd(String str) {
        String begin = null;
        String end = null;

        Pattern p = Pattern.compile("begin=.{10}");
        Matcher m = p.matcher(str);
        if (m.find()) {
            begin = m.group().substring(6);
        }
        p = Pattern.compile("end=.{10}");
        m = p.matcher(str);
        if (m.find()) {
            end = m.group().substring(4);
        }

        if (begin != null && end != null) {
            JSONArray array = new JSONArray();
            try {
                List<MatchInfoVO> list = ms.getMatchInfoByDate(begin, end);
                for (MatchInfoVO vo: list) {
                    array.put(vo.game_id);
                }
                return array.toString();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return APIServer.NOTSUPPORT;
    }

    private String season(String str) {
        str = str.replace("%20", " ");
        String season = null;
        String team = null;
        String player = null;
        int regular = 2;
        int home = 2;
        String limit = null;
        boolean desc = false;

        Pattern p = Pattern.compile("season=.{5}");
        Matcher m = p.matcher(str);
        if (m.find()) {
            season = m.group().substring(7);
        }
        p = Pattern.compile("team=.{3}");
        m = p.matcher(str);
        if (m.find()) {
            team = m.group().substring(5);
        }
        p = Pattern.compile("player=(.*?)&");
        m = p.matcher(str);
        if (m.find()) {
            player = m.group().substring(7);
            player = player.substring(0, player.length()-1);
        } else {
            p = Pattern.compile("player=(.*)");
            m = p.matcher(str);
            if (m.find()) {
                player = m.group().substring(7);
            }
        }
        p = Pattern.compile("regular=[0-1]");
        m = p.matcher(str);
        if (m.find()) {
            String temp = m.group().substring(8);
            if (temp.equals("0")) {
                regular = 0;
            } else {
                regular = 1;
            }
        }
        p = Pattern.compile("home=[0-1]");
        m = p.matcher(str);
        if (m.find()) {
            String temp = m.group().substring(5);
            if (temp.equals("0")) {
                home = 0;
            } else {
                home = 1;
            }
        }
        p = Pattern.compile("limit=[0-9]*");
        m = p.matcher(str);
        if (m.find()) {
            limit = m.group().substring(6);
        }
        p = Pattern.compile("desc=[0-1]");
        m = p.matcher(str);
        if (m.find()) {
            String temp = m.group().substring(5);
            if (temp.equals("0")) {
                desc = false;
            } else {
                desc = true;
            }
        }

        MatchFilter mf = new MatchFilter();
        if (home == 0) mf.home = false;
        else if (home == 1) mf.home = true;
        else mf.home = null;
        if (regular == 2) mf.regular = null;
        else mf.regular = regular;
        mf.team = team;
        mf.player = player;
        mf.season = season;
        if (desc) mf.order = "DESC";
        else mf.order = "ASC";
        if (limit != null) mf.limit = Integer.parseInt(limit);
        try {
            List<MatchInfoVO> list = ms.getMatchInfoByFilter(mf);
            JSONArray array = new JSONArray();
            for (MatchInfoVO vo: list) {
                array.put(vo.game_id);
            }
            return array.toString();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return APIServer.NOTSUPPORT;
    }

    private JSONObject getMatchInfoJO(MatchInfoVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("season", vo.season);
        jo.put("is_normal", vo.is_normal);
        jo.put("date", vo.date);
        jo.put("location", vo.location);
        jo.put("home_team", vo.home_team);
        jo.put("guest_team", vo.guest_team);
        jo.put("home_point", vo.home_point);
        jo.put("guest_point", vo.guest_point);
        jo.put("time", vo.time);
        return jo;
    }

    private JSONObject getBasicJO(MatchPlayerBasicVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("player_name", vo.player_name);
        jo.put("starter", vo.starter);
        jo.put("minute", vo.minute);
        jo.put("fg", vo.fg);
        jo.put("fga", vo.fga);
        jo.put("fga_pct", vo.fga_pct);
        jo.put("fg3", vo.fg3);
        jo.put("fg3a", vo.fg3a);
        jo.put("fg3_pct", vo.fg3_pct);
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
        jo.put("plus_minus", vo.plus_minus);
        return jo;
    }

    private JSONObject getAdvancedJO(MatchPlayerAdvancedVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("player_name", vo.player_name);
        jo.put("starter", vo.starter);
        jo.put("minute", vo.minute);
        jo.put("ts_pct", vo.ts_pct);
        jo.put("efg_pct", vo.efg_pct);
        jo.put("fa3a_per_fga_pct", vo.fa3a_per_fga_pct);
        jo.put("fta_per_fga_pct", vo.fta_per_fga_pct);
        jo.put("orb_pct", vo.orb_pct);
        jo.put("drb_pct", vo.drb_pct);
        jo.put("trb_pct", vo.trb_pct);
        jo.put("ast_pct", vo.ast_pct);
        jo.put("stl_pct", vo.stl_pct);
        jo.put("tov_pct", vo.tov_pct);
        jo.put("blk_pct", vo.blk_pct);
        jo.put("usg_pct", vo.usg_pct);
        jo.put("off_rtg", vo.off_rtg);
        jo.put("def_rtg", vo.def_rtg);

        return jo;
    }


}
