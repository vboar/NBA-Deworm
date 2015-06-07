package api;

import entity.Match;
import service.MatchService;
import service.impl.ServiceFactoryImpl;

import java.rmi.RemoteException;

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

        return APIServer.NOTSUPPORT;
    }



}
