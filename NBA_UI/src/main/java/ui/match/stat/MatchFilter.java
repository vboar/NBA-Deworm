package ui.match.stat;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyButton;
import ui.util.MyComboBox;
import vo.MatchInfoVO;

public class MatchFilter extends JPanel {
	private HomeUI frame;
	private PanelConfig pcfg;

	private MyButton search;

	private Image bg;
	
	private MyComboBox regular;
	private MyComboBox season;
	private MyComboBox team;
	private MyComboBox home;
	private MyComboBox begin_date;
	private MyComboBox end_date;
	
	public MatchFilter(HomeUI frame) {
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		this.bg = pcfg.getBg();

		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());

		this.initComponent();
	}
	
	public void initComponent() {
		//initLabels();
		initButtons();
		initComboBox();
	}
	
	private void initButtons() {
		search = new MyButton(pcfg.getButtons().element("search"), true);
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.motherPanel.matchPanel.matchStat.filter();
				String seasonStr = season.getSelectedItem().toString();
				//System.out.println(seasonStr);
				String teamStr = team.getSelectedItem().toString();
				int homeStr = home.getSelectedIndex();
				String beginStr = begin_date.getSelectedItem().toString();
				String endStr = end_date.getSelectedItem().toString();
				int regularInt  = regular.getSelectedIndex();

				vo.MatchFilter filter = new vo.MatchFilter();
				filter.season = seasonStr;
				filter.regular = regularInt;
				if(team.getSelectedIndex() ==0){
					filter.team = null;
				}else{
					filter.team = teamStr;
				}
				if(begin_date.getSelectedIndex() == 0){
					filter.begin_date = null;
				}else{
				filter.begin_date = beginStr;
				}
				if(end_date.getSelectedIndex() == 0){
					filter.end_date = null;
				}else{
				filter.end_date = endStr;
				}
				
				if(homeStr==0){
				filter.home =null;
				}else if(homeStr ==1){
					filter.home = true;
				}else{
					filter.home = false;
				}
					List<MatchInfoVO> list = null;
					try {

						list = ServiceFactoryImpl.getInstance()
								.getMatchService().getMatchInfoByFilter(filter);
						frame.motherPanel.matchPanel.matchStat.volist = list;
					//	System.out.println(list.size());

					} catch (RemoteException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
					}

					Object[][] data = new Object[list.size()][8];
					for (int i = 0; i < list.size(); i++) {
						data[i][0] = list.get(i).game_id;
						data[i][1] = list.get(i).date;
						data[i][2] = list.get(i).location;
						data[i][3] = list.get(i).home_team;
						data[i][4] = list.get(i).home_point;
						data[i][5] = list.get(i).guest_team;
						data[i][6] = list.get(i).guest_point;
						data[i][7] = list.get(i).time;
					

					}
					frame.motherPanel.matchPanel.matchStat.table.setData(data);
					frame.motherPanel.matchPanel.matchStat.table.updateWidth();
					
					

			}
		});
		add(search);
	}

	
	private void initComboBox() {
		// int nowState = frame.motherPanel.playerPanel.playerstat.state;
		regular = new MyComboBox(pcfg.getComboboxes().element("regular"));
		regular.setFont(new Font("华文细黑",0,12));
		add(regular);
		season = new MyComboBox(pcfg.getComboboxes().element("season"));
        season.setFont(new Font("华文细黑",0,12));
		add(season);
		team = new MyComboBox(pcfg.getComboboxes().element("team"));
		team.setFont(new Font("华文细黑",0,12));
		add(team);
		home = new MyComboBox(pcfg.getComboboxes().element("home"));
		home.setFont(new Font("华文细黑",0,12));
		add(home);
		begin_date = new MyComboBox(pcfg.getComboboxes().element("begin"));
		begin_date.setFont(new Font("华文细黑",0,12));
		add(begin_date);

		end_date = new MyComboBox(pcfg.getComboboxes().element("end"));
		end_date.setFont(new Font("华文细黑",0,12));
		add(end_date);

	

		// box1 = new MyComboBox(pcfg.getComboboxes().element("common1"));
		// add(box1);
		// box2 = new MyComboBox(pcfg.getComboboxes().element("common2"));
		// add(box2);
		// box3 = new MyComboBox(pcfg.getComboboxes().element("common3"));
		// add(box3);
		// box4 = new MyComboBox(pcfg.getComboboxes().element("common4"));
		// add(box4);
		// box5 = new MyComboBox(pcfg.getComboboxes().element("common5"));
		// add(box5);
		// box6 = new MyComboBox(pcfg.getComboboxes().element("common6"));
		// add(box6);
		//
	}
	
	public void resetPanel() {
		List<MatchInfoVO> list = null;

		try {
			vo.MatchFilter filter = new vo.MatchFilter();
			filter.season = "14-15";
			list = ServiceFactoryImpl.getInstance().getMatchService().getMatchInfoByFilter(filter);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.motherPanel.matchPanel.matchStat.volist = list;

		Object[][] data2 = new Object[list.size()][8];
		for(int i=0;i<list.size();i++){			
				data2[i][0] =list.get(i).game_id;
				data2[i][1] = list.get(i).date;
				data2[i][2] = list.get(i).location;
				data2[i][3] = list.get(i).home_team;
				data2[i][4] = list.get(i).home_point;
				data2[i][5] = list.get(i).guest_team;				
				data2[i][6] = list.get(i).guest_point;
				data2[i][7] =list.get(i).time;
		}
		frame.motherPanel.matchPanel.matchStat.table.setData(data2);
		frame.motherPanel.matchPanel.matchStat.table.updateWidth();

	}


}
