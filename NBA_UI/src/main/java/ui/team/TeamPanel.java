package ui.team;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyTab;

public class TeamPanel extends JPanel{

	private PanelConfig pcfg ;
	private HomeUI frame;

	public TeamIndex teamindex;
	public TeamStat teamstat; 
	public TeamHot teamhot;
    public TeamDetail teamDetail;
	
	public TeamPanel(HomeUI frame){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		// 设置布局管理器为自由布局
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		// 初始化组件
		this.initComponent();
		this.repaint();
		
	}
	
	private void initComponent(){
		initPanel();
	}
	
	private void initPanel(){
		teamindex = new TeamIndex(frame);
		add(teamindex);
		
		teamstat = new TeamStat(frame);
		teamstat.setVisible(false);
		add(teamstat);
		
		teamhot = new TeamHot(frame);
		teamhot.setVisible(false);
		add(teamhot);
	}

    public void removeTeamDetail() {
        if (teamDetail != null) {
            teamDetail.setVisible(false);
            remove(teamDetail);
        }
    }

    public void createTeamDetail(String team) {
        removeTeamDetail();
        teamDetail = new TeamDetail(frame, team);
        add(teamDetail);
    }

}
