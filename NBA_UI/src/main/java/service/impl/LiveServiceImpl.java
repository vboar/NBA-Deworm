package service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import service.LiveService;
import vo.LiveMatchInfoVO;
import vo.LiveMsgVO;

/**
 * Live 实现
 * Created by Vboar on 2015/6/1.
 */
public class LiveServiceImpl implements LiveService {

    private static LiveService liveService;

    private LiveServiceImpl() {}

    public static void main(String[] args) {
        LiveServiceImpl.getInstance().startLiveService();
    }

    public static LiveService getInstance() {
        if (liveService != null) {
            return liveService;
        }
        return new LiveServiceImpl();
    }

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
        List<LiveMatchInfoVO> list = new ArrayList<LiveMatchInfoVO>();
        List<String> strs = read("live/menu.txt");
        for (String s: strs) {
            String[] t = s.split(";");
            LiveMatchInfoVO vo = new LiveMatchInfoVO();
            vo.id = t[0];
            vo.date = t[1];
            vo.day = t[2];
            vo.time = t[3];
            String[] temp = t[4].split(" ");
            vo.matchType = temp[0];
            temp = temp[1].split("-");
            vo.homeTeam = temp[0];
            vo.guestTeam = temp[1];
            vo.state = t[5];
            list.add(vo);
        }
        return list;
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

    public List<LiveMsgVO> getMsg(String matchId) {
        List<LiveMsgVO> list = new ArrayList<LiveMsgVO>();
        List<String> strs = read("live/" + matchId + ".txt");
        for (String s: strs) {
            String[] t = s.split(";");
            LiveMsgVO vo = new LiveMsgVO();
            vo.sid = t[0];
            vo.residualTime = t[1];
            vo.scores = t[2];
            vo.team = t[3];
            vo.content = t[4];
            if (vo.team.equals("")) {
                vo.type = 0;
            } else if (t[5].equals("1")) {
                vo.type = 2;
            } else {
                vo.type = 1;
            }
            list.add(vo);
        }
        return list;
    }

    private List<String> read(String path) {
        List<String> lists = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(new File(path)), "UTF-8")
            );
            String temp = null;
            while((temp = br.readLine()) != null) {
                lists.add(temp);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }

}
