package api;

import org.json.JSONArray;
import service.PlayerService;
import service.impl.ServiceFactoryImpl;
import vo.PlayerInfoVO;

import java.rmi.RemoteException;
import java.util.List;

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
        if (str.equals("/all")) {
            return all();
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
        return null;
    }

}
