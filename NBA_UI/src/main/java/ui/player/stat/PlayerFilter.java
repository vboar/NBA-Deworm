/**
 * 选择的界面
 */
package ui.player.stat;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyButton;
import ui.util.MyComboBox;
import util.ChineseToOther;
import vo.PlayerAdvancedVO;
import vo.PlayerPerGameVO;
import vo.PlayerTotalVO;

public class PlayerFilter extends JPanel {
	private HomeUI frame;
	private PanelConfig pcfg;

	private MyButton search;

	private Image bg;

	private MyComboBox season;
	private MyComboBox position;
	private MyComboBox division;
	private MyComboBox regular;
	private MyComboBox league;
	private MyComboBox type;

	// private MyComboBox box1;
	// private MyComboBox box2;
	// private MyComboBox box3;
	// private MyComboBox box4;
	// private MyComboBox box5;
	// private MyComboBox box6;

	public PlayerFilter(HomeUI frame) {
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		this.bg = pcfg.getBg();

		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());

		this.initComponent();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}

	public void initComponent() {
		initLabels();
		initButtons();
		initComboBox();
	}

	private void initLabels() {

	}

	// private void initButtons(){
	// search = new MyButton(pcfg.getButtons().element("search"),true);
	// search.addMouseListener(new MouseAdapter(){
	//
	// @Override
	// public void mouseClicked(MouseEvent arg0) {
	// frame.motherPanel.playerPanel.filter();
	//
	// int nowState = frame.motherPanel.playerPanel.playerstat.state;
	// boolean nowAdv = frame.motherPanel.playerPanel.playerstat.isAdvanced;
	//
	// String seasonStr = season.getSelectedItem().toString();
	// String positionStr = position.getSelectedItem().toString();
	// String divisionStr = division.getSelectedItem().toString();
	// int regularInt = regular.getSelectedIndex();
	//
	// vo.PlayerFilter filter = new vo.PlayerFilter();
	// filter.division =divisionStr.equals("All")?null:divisionStr;
	// filter.position = positionStr.equals("All")?null:positionStr;
	// filter.season = seasonStr;
	// filter.regular = regularInt;
	//
	//
	// if(nowState==0){
	// List<PlayerPerGameVO> list = null;
	// try {
	//
	// list =
	// ServiceFactoryImpl.getInstance().getPlayerService().getPlayerPerGameByFilter(filter);
	// frame.motherPanel.playerPanel.playerstat.volistavg=list;
	//
	// } catch (RemoteException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// Object[][] data = new Object[list.size()][8];
	// for(int i=0;i<list.size();i++){
	// data[i][0] = list.get(i).name;
	// data[i][1] = list.get(i).team;
	// data[i][2] =
	// ChineseToOther.ChineseToString(box1.getSelectedItem().toString(),list.get(i));
	// data[i][3] =
	// ChineseToOther.ChineseToString(box2.getSelectedItem().toString(),list.get(i));
	// data[i][4] =
	// ChineseToOther.ChineseToString(box3.getSelectedItem().toString(),list.get(i));
	// data[i][5] =
	// ChineseToOther.ChineseToString(box4.getSelectedItem().toString(),list.get(i));
	// data[i][6] =
	// ChineseToOther.ChineseToString(box5.getSelectedItem().toString(),list.get(i));
	// data[i][7] =
	// ChineseToOther.ChineseToString(box6.getSelectedItem().toString(),list.get(i));
	// }
	// String ss[] = new String[8];
	// ss[0]= "球员名称";
	// ss[1] = "所属球队";
	// ss[2] = box1.getSelectedItem().toString();
	// ss[3] = box2.getSelectedItem().toString();
	// ss[4] = box3.getSelectedItem().toString();
	// ss[5] = box4.getSelectedItem().toString();
	// ss[6] = box5.getSelectedItem().toString();
	// ss[7] = box6.getSelectedItem().toString();
	//
	//
	// frame.motherPanel.playerPanel.playerstat.table.setData(data);
	// frame.motherPanel.playerPanel.playerstat.table.setcolumnName(ss);
	// }else if(nowState ==1){
	// List<PlayerTotalVO> list = null;
	// try {
	//
	// list =
	// ServiceFactoryImpl.getInstance().getPlayerService().getPlayerTotalByFilter(filter);
	// frame.motherPanel.playerPanel.playerstat.volist=list;
	//
	// } catch (RemoteException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// Object[][] data = new Object[list.size()][8];
	// for(int i=0;i<list.size();i++){
	// data[i][0] = list.get(i).name;
	// data[i][1] = list.get(i).team;
	// data[i][2] =
	// ChineseToOther.ChineseToString(box1.getSelectedItem().toString(),list.get(i));
	// data[i][3] =
	// ChineseToOther.ChineseToString(box2.getSelectedItem().toString(),list.get(i));
	// data[i][4] =
	// ChineseToOther.ChineseToString(box3.getSelectedItem().toString(),list.get(i));
	// data[i][5] =
	// ChineseToOther.ChineseToString(box4.getSelectedItem().toString(),list.get(i));
	// data[i][6] =
	// ChineseToOther.ChineseToString(box5.getSelectedItem().toString(),list.get(i));
	// data[i][7] =
	// ChineseToOther.ChineseToString(box6.getSelectedItem().toString(),list.get(i));
	// }
	// String ss[] = new String[8];
	// ss[0]= "球员名称";
	// ss[1] = "所属球队";
	// ss[2] = box1.getSelectedItem().toString();
	// ss[3] = box2.getSelectedItem().toString();
	// ss[4] = box3.getSelectedItem().toString();
	// ss[5] = box4.getSelectedItem().toString();
	// ss[6] = box5.getSelectedItem().toString();
	// ss[7] = box6.getSelectedItem().toString();
	//
	//
	// frame.motherPanel.playerPanel.playerstat.table.setData(data);
	// frame.motherPanel.playerPanel.playerstat.table.setcolumnName(ss);
	// }else{
	// List<PlayerAdvancedVO> list = null;
	// try {
	//
	// list =
	// ServiceFactoryImpl.getInstance().getPlayerService().getPlayerAdvancedByFilter(filter);
	// frame.motherPanel.playerPanel.playerstat.volistadv=list;
	//
	// } catch (RemoteException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// Object[][] data = new Object[list.size()][8];
	// for(int i=0;i<list.size();i++){
	// data[i][0] = list.get(i).name;
	// data[i][1] = list.get(i).team;
	// data[i][2] =
	// ChineseToOther.ChineseToString(box1.getSelectedItem().toString(),list.get(i));
	// data[i][3] =
	// ChineseToOther.ChineseToString(box2.getSelectedItem().toString(),list.get(i));
	// data[i][4] =
	// ChineseToOther.ChineseToString(box3.getSelectedItem().toString(),list.get(i));
	// data[i][5] =
	// ChineseToOther.ChineseToString(box4.getSelectedItem().toString(),list.get(i));
	// data[i][6] =
	// ChineseToOther.ChineseToString(box5.getSelectedItem().toString(),list.get(i));
	// data[i][7] =
	// ChineseToOther.ChineseToString(box6.getSelectedItem().toString(),list.get(i));
	// }
	// String ss[] = new String[8];
	// ss[0]= "球员名称";
	// ss[1] = "所属球队";
	// ss[2] = box1.getSelectedItem().toString();
	// ss[3] = box2.getSelectedItem().toString();
	// ss[4] = box3.getSelectedItem().toString();
	// ss[5] = box4.getSelectedItem().toString();
	// ss[6] = box5.getSelectedItem().toString();
	// ss[7] = box6.getSelectedItem().toString();
	//
	//
	// frame.motherPanel.playerPanel.playerstat.table.setData(data);
	// frame.motherPanel.playerPanel.playerstat.table.setcolumnName(ss);
	// }
	//
	// }
	//
	// });
	// add(search);
	// }

	private void initButtons() {
		search = new MyButton(pcfg.getButtons().element("search"), true);
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.motherPanel.playerPanel.filter();

				String[] header0 = { "NAME", "TEAM", "G", "GS", "MIN", "FG",
						"FGA%", "FG3", "FG3%", "FG2", "FG2%", "EFG%", "FT",
						"FT%", "TRB", "AST", "STL", "BLK", "TOV", "PF", "PTS" };
				String[] header1 = { "NAME", "TEAM", "PER", "TS%", "F3AR",
						"FTAR", "ORB%", "TRB%", "AST%", "STL%", "BLK%", "TOV%",
						"USG%", "OWS", "DWS", "WS", "WS48", "OBPM", "DBPM",
						"BPM", "VORP" };
				String seasonStr = season.getSelectedItem().toString();
				//System.out.println(seasonStr);
				String positionStr = position.getSelectedItem().toString();
				String divisionStr = division.getSelectedItem().toString();
				int regularInt = regular.getSelectedIndex();
				String leagueStr = league.getSelectedItem().toString();
				int typeInt = type.getSelectedIndex();

				vo.PlayerFilter filter = new vo.PlayerFilter();
				filter.division = divisionStr.equals("All Division") ? null
						: divisionStr;
				filter.position = positionStr.equals("All Position") ? null
						: positionStr;
				filter.season = seasonStr;
				filter.league = leagueStr.equals("All League") ? null
						: leagueStr;
				filter.regular = regularInt > 1 ? null : regularInt;

				switch (typeInt) {
				case 0: {
					List<PlayerPerGameVO> list = null;
					try {

						list = ServiceFactoryImpl.getInstance()
								.getPlayerService()
								.getPlayerPerGameByFilter(filter);
						frame.motherPanel.playerPanel.playerstat.volistavg = list;
					//	System.out.println(list.size());

					} catch (RemoteException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
					}

					Object[][] data = new Object[list.size()][21];
					for (int i = 0; i < list.size(); i++) {
						data[i][0] = list.get(i).name;
						data[i][1] = list.get(i).team;
						data[i][2] = list.get(i).game;
						data[i][3] = list.get(i).game_started;
						data[i][4] = list.get(i).minute;
						data[i][5] = list.get(i).fg;
						data[i][6] = list.get(i).fga_pct;
						data[i][7] = list.get(i).fg3;
						data[i][8] = list.get(i).fg3_pct;
						data[i][9] = list.get(i).fg2;
						data[i][10] = list.get(i).fg2_pct;
						data[i][11] = list.get(i).efg_pct;
						data[i][12] = list.get(i).ft;
						data[i][13] = list.get(i).ft_pct;
						data[i][14] = list.get(i).trb;
						data[i][15] = list.get(i).ast;
						data[i][16] = list.get(i).stl;
						data[i][17] = list.get(i).blk;
						data[i][18] = list.get(i).tov;
						data[i][19] = list.get(i).pf;
						data[i][20] = list.get(i).pts;

					}
					frame.motherPanel.playerPanel.playerstat.table
							.setData(data);
					frame.motherPanel.playerPanel.playerstat.table
							.setcolumnName(header0);
					frame.motherPanel.playerPanel.playerstat.table
							.updateWidth();
					break;
				}
				case 1: {
					List<PlayerTotalVO> list = null;
					try {

						list = ServiceFactoryImpl.getInstance()
								.getPlayerService()
								.getPlayerTotalByFilter(filter);
						frame.motherPanel.playerPanel.playerstat.volist = list;

					} catch (RemoteException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
					}

					Object[][] data = new Object[list.size()][21];
					for (int i = 0; i < list.size(); i++) {
						data[i][0] = list.get(i).name;
						data[i][1] = list.get(i).team;
						data[i][2] = list.get(i).game;
						data[i][3] = list.get(i).game_started;
						data[i][4] = list.get(i).minute;
						data[i][5] = list.get(i).fg;
						data[i][6] = list.get(i).fga_pct;
						data[i][7] = list.get(i).fg3;
						data[i][8] = list.get(i).fg3_pct;
						data[i][9] = list.get(i).fg2;
						data[i][10] = list.get(i).fg2_pct;
						data[i][11] = list.get(i).efg_pct;
						data[i][12] = list.get(i).ft;
						data[i][13] = list.get(i).ft_pct;
						data[i][14] = list.get(i).trb;
						data[i][15] = list.get(i).ast;
						data[i][16] = list.get(i).stl;
						data[i][17] = list.get(i).blk;
						data[i][18] = list.get(i).tov;
						data[i][19] = list.get(i).pf;
						data[i][20] = list.get(i).pts;

					}
					frame.motherPanel.playerPanel.playerstat.table
							.setData(data);
					frame.motherPanel.playerPanel.playerstat.table
							.setcolumnName(header0);
					frame.motherPanel.playerPanel.playerstat.table
							.updateWidth();
					break;
				}
				case 2: {
					List<PlayerAdvancedVO> list = null;
					try {

						list = ServiceFactoryImpl.getInstance()
								.getPlayerService()
								.getPlayerAdvancedByFilter(filter);
						frame.motherPanel.playerPanel.playerstat.volistadv = list;

					} catch (RemoteException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
					}

					Object[][] data = new Object[list.size()][21];
					for (int i = 0; i < list.size(); i++) {
						data[i][0] = list.get(i).name;
						data[i][1] = list.get(i).team;
						data[i][2] = list.get(i).per;
						data[i][3] = list.get(i).ts_pct;
						data[i][4] = list.get(i).fa3a_per_fga_pct;
						data[i][5] = list.get(i).fta_per_fga_pct;
						data[i][6] = list.get(i).orb_pct;
						data[i][7] = list.get(i).trb_pct;
						data[i][8] = list.get(i).ast_pct;
						data[i][9] = list.get(i).stl_pct;
						data[i][10] = list.get(i).blk_pct;
						data[i][11] = list.get(i).tov_pct;
						data[i][12] = list.get(i).usg_pct;
						data[i][13] = list.get(i).ows;
						data[i][14] = list.get(i).dws;
						data[i][15] = list.get(i).ws;
						data[i][16] = list.get(i).ws_48;
						data[i][17] = list.get(i).obpm;
						data[i][18] = list.get(i).dbpm;
						data[i][19] = list.get(i).bpm;
						data[i][20] = list.get(i).vorp;
					}
					frame.motherPanel.playerPanel.playerstat.table
							.setData(data);
					frame.motherPanel.playerPanel.playerstat.table
							.setcolumnName(header1);
					frame.motherPanel.playerPanel.playerstat.table
							.updateWidth();
					break;
				}
				}

			}
		});
		add(search);
	}

	public void resetPanel() {
		String[] header0 = { "NAME", "TEAM", "G", "GS", "MIN", "FG", "FGA%",
				"FG3", "FG3%", "FG2", "FG2%", "EFG%", "FT", "FT%", "TRB",
				"AST", "STL", "BLK", "TOV", "PF", "PTS" };
		List<PlayerPerGameVO> list = null;

		try {
			list = ServiceFactoryImpl.getInstance().getPlayerService()
					.getPlayerPerGameBySeason("14-15", 1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.motherPanel.playerPanel.playerstat.volistavg = list;

		Object[][] data = new Object[list.size()][21];
		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).name;
			data[i][1] = list.get(i).team;
			data[i][2] = list.get(i).game;
			data[i][3] = list.get(i).game_started;
			data[i][4] = list.get(i).minute;
			data[i][5] = list.get(i).fg;
			data[i][6] = list.get(i).fga_pct;
			data[i][7] = list.get(i).fg3;
			data[i][8] = list.get(i).fg3_pct;
			data[i][9] = list.get(i).fg2;
			data[i][10] = list.get(i).fg2_pct;
			data[i][11] = list.get(i).efg_pct;
			data[i][12] = list.get(i).ft;
			data[i][13] = list.get(i).ft_pct;
			data[i][14] = list.get(i).trb;
			data[i][15] = list.get(i).ast;
			data[i][16] = list.get(i).stl;
			data[i][17] = list.get(i).blk;
			data[i][18] = list.get(i).tov;
			data[i][19] = list.get(i).pf;
			data[i][20] = list.get(i).pts;

		}
		frame.motherPanel.playerPanel.playerstat.table.setData(data);
		frame.motherPanel.playerPanel.playerstat.table.setcolumnName(header0);
		frame.motherPanel.playerPanel.playerstat.table.updateWidth();

	}

	private void initComboBox() {
		// int nowState = frame.motherPanel.playerPanel.playerstat.state;
		season = new MyComboBox(pcfg.getComboboxes().element("season"));
		add(season);
		position = new MyComboBox(pcfg.getComboboxes().element("position"));
		add(position);
		division = new MyComboBox(pcfg.getComboboxes().element("division"));
		add(division);
		regular = new MyComboBox(pcfg.getComboboxes().element("regular"));
		add(regular);

		league = new MyComboBox(pcfg.getComboboxes().element("league"));
		add(league);

		type = new MyComboBox(pcfg.getComboboxes().element("type"));
		add(type);

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
	//
	// public void changeBox(boolean isadv){
	// remove(box1);
	// remove(box2);
	// remove(box3);
	// remove(box4);
	// remove(box5);
	// remove(box6);
	//
	// if(isadv){
	// box1 = new MyComboBox(pcfg.getComboboxes().element("advanced1"));
	// add(box1);
	// box2 = new MyComboBox(pcfg.getComboboxes().element("advanced2"));
	// add(box2);
	// box3 = new MyComboBox(pcfg.getComboboxes().element("advanced3"));
	// add(box3);
	// box4 = new MyComboBox(pcfg.getComboboxes().element("advanced4"));
	// add(box4);
	// box5 = new MyComboBox(pcfg.getComboboxes().element("advanced5"));
	// add(box5);
	// box6 = new MyComboBox(pcfg.getComboboxes().element("advanced6"));
	// add(box6);
	//
	// }else{
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
	// }
	// }
	//
	// public void setAdvTable(){
	// List<PlayerAdvancedVO> list = null;
	// list =frame.motherPanel.playerPanel.playerstat.volistadv;
	//
	//
	//
	// Object[][] data = new Object[list.size()][8];
	// for(int i=0;i<list.size();i++){
	// data[i][0] = list.get(i).name;
	// data[i][1] = list.get(i).team;
	// data[i][2] =
	// ChineseToOther.ChineseToString(box1.getSelectedItem().toString(),list.get(i));
	// data[i][3] =
	// ChineseToOther.ChineseToString(box2.getSelectedItem().toString(),list.get(i));
	// data[i][4] =
	// ChineseToOther.ChineseToString(box3.getSelectedItem().toString(),list.get(i));
	// data[i][5] =
	// ChineseToOther.ChineseToString(box4.getSelectedItem().toString(),list.get(i));
	// data[i][6] =
	// ChineseToOther.ChineseToString(box5.getSelectedItem().toString(),list.get(i));
	// data[i][7] =
	// ChineseToOther.ChineseToString(box6.getSelectedItem().toString(),list.get(i));
	// }
	// String ss[] = new String[8];
	// ss[0]= "球员名称";
	// ss[1] = "所属球队";
	// ss[2] = box1.getSelectedItem().toString();
	// ss[3] = box2.getSelectedItem().toString();
	// ss[4] = box3.getSelectedItem().toString();
	// ss[5] = box4.getSelectedItem().toString();
	// ss[6] = box5.getSelectedItem().toString();
	// ss[7] = box6.getSelectedItem().toString();
	//
	//
	// frame.motherPanel.playerPanel.playerstat.table.setData(data);
	// frame.motherPanel.playerPanel.playerstat.table.setcolumnName(ss);
	// }
	//

}
