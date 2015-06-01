package service.impl;

import service.LiveService;
import vo.LiveMatchInfoVO;
import vo.LiveMsgVO;

import java.io.IOException;
import java.util.List;

/**
 * Created by Vboar on 2015/6/1.
 */
public class LiveServiceImpl implements LiveService {

    private Process process;

    public void startLiveService() {
        try {
            process = Runtime.getRuntime().exec("python live.py");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopLiveService() {
        if (process.isAlive()) {
            process.destroy();
        }
    }

    public List<LiveMatchInfoVO> getAllLiveList() {
        return null;
    }

    public LiveMatchInfoVO checkMatchStart() {
        List<LiveMatchInfoVO> list = getAllLiveList();
        for (LiveMatchInfoVO vo: list) {
            if (!vo.state.equals("等待更新")) {
                return vo;
            }
        }
        return null;
    }

    public List<LiveMsgVO> getAllMsg(String matchId) {
        return null;
    }

    public static void main(String[] args) {
        LiveService l = new LiveServiceImpl();
        l.startLiveService();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        l.stopLiveService();
    }

}
