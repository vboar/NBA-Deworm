package api;

import dao.DaoFactory;
import dao.TeamDao;
import dao.impl.DaoFactoryImpl;
import entity.TeamInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import service.ServiceFactory;
import service.TeamService;
import service.impl.ServiceFactoryImpl;
import vo.TeamInfoVO;

import java.rmi.RemoteException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                TeamInfoVO vo = ts.getTeamInfoByAbbr(abbr);
                if (vo == null) return APIServer.NOTFOUND;
                return getTeamInfoJO(vo).toString();
            } catch (RemoteException e) {
                e.printStackTrace();
                return APIServer.ERROR;
            }
        }
        Pattern p = Pattern.compile("[0-9]{8}");
        Matcher m = p.matcher(str);
        if (m.find()) System.out.println(m.group());

        return APIServer.NOTSUPPORT;
    }

    /**
     * 由VO获得球队简要信息的JO
     * @param vo
     * @return
     */
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

    /**
     * 由VO获得球队信息的JO
     * @param vo
     * @return
     */
    private JSONObject getTeamInfoJO(TeamInfoVO vo) {
        JSONObject jo = new JSONObject();
        jo.put("name", vo.name);
        jo.put("abbr", vo.abbr);
        jo.put("buildup_time", vo.buildup_time);
        jo.put("location", vo.location);
        jo.put("division", vo.division);
        jo.put("league", vo.league);
        jo.put("record", vo.record);
        jo.put("playeroff_appearance", vo.playeroff_appearance);
        jo.put("championships", vo.championships);
        return jo;
    }
}
