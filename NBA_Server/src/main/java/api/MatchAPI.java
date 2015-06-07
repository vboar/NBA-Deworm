package api;

import dao.InitDao;
import entity.Match;
import entity.MatchInfo;
import org.json.JSONArray;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import service.MatchService;
import service.impl.ServiceFactoryImpl;
import vo.MatchFilter;
import vo.MatchInfoVO;

import java.rmi.RemoteException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        // TODO 缺少接口


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



}
