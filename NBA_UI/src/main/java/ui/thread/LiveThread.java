package ui.thread;

import java.util.List;

import service.impl.LiveServiceImpl;
import ui.live.LivePanel;
import vo.LiveMsgVO;

public class LiveThread implements Runnable {

	public static List<LiveMsgVO> msgList;
	public LivePanel livePanel;
	
	public LiveThread(LivePanel livePanel){
		this.livePanel = livePanel;
	}

	@Override
	public void run() {

		//LiveServiceImpl.getInstance().startLiveService();
		while (true) {
			while (livePanel.islive) {
				String id = livePanel.matchId;
				msgList = LiveServiceImpl.getInstance().getMsg(id);
				livePanel.autoRefresh(msgList);
				livePanel.repaint();
				livePanel.revalidate();
				System.out.println("-----------------refresh done-----------");
				try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
