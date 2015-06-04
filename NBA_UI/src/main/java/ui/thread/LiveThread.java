package ui.thread;

import java.util.List;

import service.impl.LiveServiceImpl;
import ui.live.LivePanel;
import vo.LiveMsgVO;

public class LiveThread implements Runnable {

	public static List<LiveMsgVO> msgList;

	@Override
	public void run() {

		LiveServiceImpl.getInstance().startLiveService();
		while (true) {
			while (LivePanel.islive) {
				String id = LivePanel.matchId;
				msgList = LiveServiceImpl.getInstance().getMsg(id);
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
