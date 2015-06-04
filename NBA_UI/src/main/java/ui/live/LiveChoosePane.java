package ui.live;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import service.impl.LiveServiceImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyButton;
import ui.util.MyLabel;
import vo.LiveMatchInfoVO;



public class LiveChoosePane extends JPanel {
	
	private PanelConfig pcfg ;
	private HomeUI frame;
	private Image bg;
	
	public LiveChoosePane(HomeUI frame) {
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		// 设置布局管理器为自由布局
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.bg = pcfg.getBg();
		// 初始化组件
		this.initComponent();
		this.repaint();
	}

	private void initComponent(){
		LiveServiceImpl.getInstance().startLiveService();
		List<LiveMatchInfoVO>  list=LiveServiceImpl.getInstance().getAllLiveList();
		for(int i=0;i<list.size();i++){
			String text = list.get(i).date+ " "+list.get(i).time+" "+list.get(i).matchType+" "+list.get(i).homeTeam+" "+list.get(i).guestTeam;
			MyLabel lb = new MyLabel(text, pcfg.getLabels().element("news"));
			lb.setLocation(lb.getX(), lb.getY()+100*i);
			add(lb);
			
		}
		
		MyButton btn = new MyButton(pcfg.getButtons().element("btn"));
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				LiveMatchInfoVO info = LiveServiceImpl.getInstance().checkMatchStart();
				if(info!=null){
					LivePanel livePanel = new LivePanel(frame, info);
				}
				
			}
		});
		add(btn);
	}
	
}
