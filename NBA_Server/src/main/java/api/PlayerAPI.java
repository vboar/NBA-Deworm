package api;

import entity.PlayerInfo;
import entity.PlayerSalary;
import org.json.JSONArray;
import org.json.JSONObject;
import service.PlayerService;
import service.impl.ServiceFactoryImpl;
import vo.*;

import java.rmi.RemoteException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vboar on 2015/6/3.
 */
public class PlayerAPI {

    private PlayerService ps;

    public PlayerAPI() {
        try {
            ps = ServiceFactoryImpl.getInstance().getPlayerService();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getStr(String str) {
        if (str.equals("/all") || str.equals("/all/")) {
            // command is /player/all/
            return all();
        }
        if (str.startsWith("?initial=")) {
            // command is /player?initial={}
            return initial(str);
        }
        if (str.startsWith("?team=")) {
            // command is /player?team={}&season={}
            return teamSeason(str);
        }
        if (str.startsWith("?name=")) {
            return name(str);
        }
        return APIServer.NOTSUPPORT;
    }

    private String all() {
        try {
            List<PlayerInfoVO> list = ps.getAllPlayerInfo();
            JSONArray array = new JSONArray();
            for (PlayerInfoVO vo : list) {
                array.put(vo.name);
            }
            return array.toString();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return APIServer.NOTSUPPORT;
    }

    private String initial(String str) {
        String initial = str.substring(9);
        if (initial.length() > 1) return APIServer.NOTSUPPORT;
        try {
            List<String> list = ps.getNameByNameInitial(initial);
            JSONArray array = new JSONArray();
            for (String s: list) {
                array.put(s);
            }
            return array.toString();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return APIServer.NOTSUPPORT;
    }

    private String teamSeason(String str) {
        String abbr = null;
        String season = null;

        Pattern p = Pattern.compile("team=.{3}");
        Matcher m = p.matcher(str);
        if (m.find()) {
            abbr = m.group().substring(5);
        }
        p = Pattern.compile("season=.{5}");
        m = p.matcher(str);
        if (m.find()) {
            season = m.group().substring(7);
        }

        try {
            JSONArray array = new JSONArray();
            List<PlayerInfoVO> list = ps.getTeamPlayerBySeason(season, abbr);
            for (PlayerInfoVO vo: list) {
                array.put(vo.name);
            }
            return array.toString();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return APIServer.NOTSUPPORT;
    }

    private String name(String str) {
        str = str.replace("%20", " ");

        boolean only = true;
        String name = null;
        String season = null;
        boolean total = false;
        boolean pergame = false;
        boolean advanced = false;
        boolean salary = false;
        boolean regular = true;
        boolean playoff = true;

        Pattern p = Pattern.compile("name=(.*)&");
        Matcher m = p.matcher(str);
        if (m.find()) {
            only = false;
            name = m.group().substring(5);
            name = name.substring(0, name.length()-1);
        }

        if (only) {
            p = Pattern.compile("name=(.*)");
            m = p.matcher(str);
            if (m.find()) {
                name = m.group().substring(5);
                try {
                    JSONObject jo = new JSONObject();
                    JSONObject jo1 = new JSONObject();
                    PlayerInfoVO vo = ps.getPlayerInfoByName(name);
                    if (vo == null) return APIServer.NOTFOUND;
                    jo1.put("info", getPlayerInfoJO(vo));
                    jo.put(name, jo1);
                    return jo.toString();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                return APIServer.NOTSUPPORT;
            }
        }

        p = Pattern.compile("season=.{5}");
        m = p.matcher(str);
        if (m.find()) {
            season = m.group().substring(7);
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
        p = Pattern.compile("salary=[0-1]");
        m = p.matcher(str);
        if (m.find()) {
            String temp = m.group().substring(7);
            if (temp.equals("1")) salary = true;
        }
        p = Pattern.compile("regular=[0-1]");
        m = p.matcher(str);
        if (m.find()) {
            String temp = m.group().substring(8);
            if (temp.equals("1")) {
                playoff = false;
            } else {
                regular = false;
            }
        }

        if (season == null) {
            JSONObject jo = new JSONObject();
            JSONObject jo1 = new JSONObject();
            try {
                System.out.println(name);
                PlayerInfoVO vo = ps.getPlayerInfoByName(name);
                if (vo == null) return APIServer.NOTFOUND;
                jo1.put("info", getPlayerInfoJO(vo));

                if (total) {
                    if (regular) {
                        PlayerFilter pf = new PlayerFilter();
                        pf.regular = 1;
                        List<PlayerTotalVO> list = ps.getPlayerTotalByFilter(pf);
                        JSONObject jo2 = new JSONObject();
                        for (PlayerTotalVO temp: list) {
                            jo2.put(temp.season, getPlayerTotalJO(temp));
                        }
                        jo1.put("total_regular", jo2);
                    }
                    if (playoff) {
                        PlayerFilter pf = new PlayerFilter();
                        pf.regular = 0;
                        List<PlayerTotalVO> list = ps.getPlayerTotalByFilter(pf);
                        JSONObject jo2 = new JSONObject();
                        for (PlayerTotalVO temp: list) {
                            jo2.put(temp.season, getPlayerTotalJO(temp));
                        }
                        jo1.put("total_playoff", jo2);
                    }
                }
                jo.put(name, jo1);
                return jo.toString();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        JSONObject jo = new JSONObject();
        try {
            PlayerInfoVO vo = ps.getPlayerInfoByName(name);
            if (vo == null) return APIServer.NOTFOUND;
            JSONObject jo1 = new JSONObject();
            jo1.put("info", getPlayerInfoJO(vo));




            jo.put(vo.name, jo1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        return APIServer.NOTSUPPORT;
    }

    private JSONObject getPlayerInfoJO(PlayerInfoVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("born", vo.born);
        jo.put("hometown", vo.hometown);
        jo.put("position", vo.position);
        jo.put("height", vo.height);
        jo.put("weight", vo.weight);
        jo.put("shoots", vo.shoots);
        jo.put("high_school", vo.high_school);
        jo.put("college", vo.college);
        jo.put("draft", vo.draft);
        jo.put("debut", vo.debut);
        jo.put("exp", vo.exp);
        jo.put("number", vo.number);
        return jo;
    }

    private JSONObject getPlayerTotalJO(PlayerTotalVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("team_abbr", vo.team);
        jo.put("position", vo.position);
        jo.put("num_of_game", vo.game);
        jo.put("game_start", vo.game_started);
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
        jo.put("efg_pct", vo.efg_pct);
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

    private JSONObject getPlayerPergameJO(PlayerPerGameVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("team_abbr", vo.team);
        jo.put("position", vo.position);
        jo.put("num_of_game", vo.game);
        jo.put("game_start", vo.game_started);
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
        jo.put("efg_pct", vo.efg_pct);
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

    private JSONObject getPlayerAdvancedJO(PlayerAdvancedVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("team_abbr", vo.team);
        jo.put("position", vo.position);
        jo.put("num_of_game", vo.game);
        jo.put("minute", vo.minute);
        jo.put("per", vo.per);
        jo.put("ts_pct", vo.ts_pct);
        jo.put("fa3a_per_fga_pct", vo.fa3a_per_fga_pct);
        jo.put("fta_per_fga_pct", vo.fta_per_fga_pct);
        jo.put("orb_pct", vo.orb_pct);
        jo.put("drb_pct", vo.drb_pct);
        jo.put("trb_pct", vo.trb_pct);
        jo.put("ast_pct", vo.ast_pct);
        jo.put("stl_pct", vo.stl_pct);
        jo.put("blk_pct", vo.blk_pct);
        jo.put("tov_pct", vo.tov_pct);
        jo.put("usg_pct", vo.usg_pct);
        jo.put("ows", vo.ows);
        jo.put("dws", vo.dws);
        jo.put("ws", vo.ws);
        jo.put("ws_48", vo.ws_48);
        jo.put("obpm", vo.obpm);
        jo.put("dbpm", vo.dbpm);
        jo.put("bpm", vo.bpm);
        jo.put("vorp", vo.vorp);
        return jo;
    }

    private JSONObject getPlayerSalaryJO(PlayerSalaryVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("team", vo.team);
        jo.put("salary", vo.salary);
        return jo;
    }

}
