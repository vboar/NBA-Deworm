package ui.team;

import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.team.compare.TeamCompare;
import ui.team.compare.TeamCompareChoosePanel;

public class TeamPanel extends JPanel{

	private PanelConfig pcfg ;
	private HomeUI frame;

	public TeamIndex teamindex;
	public TeamStat teamstat; 
	public TeamHot teamhot;
	public TeamCompareChoosePanel comparechoose;
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
		
		comparechoose = new TeamCompareChoosePanel(frame);
		comparechoose.setVisible(false);
		add(comparechoose);
	}

    public void removeTeamDetail() {
        if (teamDetail != null) {
            teamDetail.setVisible(false);
            remove(teamDetail);
        }
    }

    public void createTeamDetail(String team) {
        removeTeamDetail();
        teamindex.setVisible(false);
        teamDetail = new TeamDetail(frame, team);
        add(teamDetail);
    }

}
