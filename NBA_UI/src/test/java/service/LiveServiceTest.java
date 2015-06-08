package service;

import junit.framework.TestCase;
import service.impl.LiveServiceImpl;
import service.impl.ServiceFactoryImpl;
import vo.LiveMatchInfoVO;
import vo.LiveMatchVO;
import vo.LiveMsgVO;

import java.util.List;

/**
 * Created by Vboar on 2015/6/8.
 */
public class LiveServiceTest extends TestCase {

    private LiveService ls;

    public LiveServiceTest() {
        ls = LiveServiceImpl.getInstance();
    }

    public void testGetAllLiveList() throws Exception {
        System.out.println("testGetAllLiveList");
        List<LiveMatchInfoVO> list = ls.getAllLiveList();
        for (LiveMatchInfoVO vo: list) {
            System.out.println(vo.id + " " + vo.date + " " + vo.day + " " + vo.time + " " + vo.matchType
            + " " + vo.homeTeam + " " + vo.guestTeam + " " + vo.state);
        }
    }

    public void testCheckMatchStart() throws Exception {
        System.out.println("testCheckMatchStart");
        LiveMatchInfoVO vo = ls.checkMatchStart();
        System.out.println(vo);
    }

    public void testGetMsg() throws Exception {
        System.out.println("testGetMsg");
        List<LiveMsgVO> list = ls.getMsg("150120");
        for (LiveMsgVO vo: list) {
            System.out.println(vo.sid + " " + vo.residualTime + " " + vo.team + " " + vo.content + " " + vo.scores
            + " " + vo.type);
        }
    }

    public void testGetHistoryList() throws Exception {
        System.out.println("testGetHistoryList");
        List<LiveMatchInfoVO> list = ls.getHistoryList();
        for (LiveMatchInfoVO vo: list) {
            System.out.println(vo.id + " " + vo.date + " " + vo.day + " " + vo.time + " " + vo.matchType
                    + " " + vo.homeTeam + " " + vo.guestTeam + " " + vo.state);
        }
    }

    public void testGetMatchVO() throws Exception {
        System.out.println("testGetMatchVO");
        LiveMatchVO vo = ls.getMatchVO("150120");
        System.out.println(vo.id + " " + vo.time + " " + vo.gym + " " + vo.attendance + " " + vo.residualTime);
        System.out.print("Home team: ");
        for (String s: vo.scoresA) {
            System.out.print(s + " ");
        }
        System.out.print("\nGuest team: ");
        for (String s: vo.scoresB) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}