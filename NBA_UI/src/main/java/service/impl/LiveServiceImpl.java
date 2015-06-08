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
import vo.LiveMatchVO;
import vo.LiveMsgVO;

/**
 * Live 实现
 * Created by Vboar on 2015/6/1.
 */
public class LiveServiceImpl implements LiveService {

    private static LiveService liveService;

    private LiveServiceImpl() {}

    public static void main(String[] args) {
//        LiveServiceImpl.getInstance().startLiveService();
//        while (true) {
//            try {
//                Thread.sleep(5000);
//                List<LiveMsgVO> list = LiveServiceImpl.getInstance().getMsg("150120");
//                for (LiveMsgVO vo: list) {
//                    System.out.println(vo.content);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        LiveMatchVO vo = LiveServiceImpl.getInstance().getMatchVO("150120");
        System.out.println(vo.residualTime);
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
            List<String> strs = read("live/" + vo.id + ".txt");
            if (strs.size() > 0) return vo;
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

    @Override
    public List<LiveMatchInfoVO> getHistoryList() {
        List<LiveMatchInfoVO> list = new ArrayList<LiveMatchInfoVO>();
        List<String> strs = read("live/history.txt");
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

    @Override
    public LiveMatchVO getMatchVO(String matchId) {
        List<String> strs = read("live/" + matchId + "_info.txt");
        LiveMatchVO vo = new LiveMatchVO();
        String[] temp = strs.get(0).split(";", -1);
        vo.id = matchId;
        vo.time = temp[0];
        vo.gym = temp[1];
        vo.attendance = temp[2];
        vo.residualTime = temp[3];
        temp = strs.get(1).split(";");
        for (String t: temp) {
            vo.scoresA.add(t);
        }
        temp = strs.get(2).split(";");
        for (String t: temp) {
            vo.scoresB.add(t);
        }
        return vo;
    }

    @Override
    public String hupuIdToGameId(String hupuId) {
        // TODO 临时解决方案
        switch (hupuId) {
            case "150119":
                return "201506040GSW-CLE";
            case "150120":
                return "201506070GSW-CLE";
            case "150121":
                return "201506090CLE-GSW";
            case "150122":
                return "201506110CLE-GSW";
        }
        return "201506040GSW-CLE";
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
