package ui.player.info;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.util.LoadFont;
import ui.util.MyLabel;
import ui.util.MyPressedLabel;
import vo.PlayerAdvancedVO;
import vo.PlayerPerGameVO;
import vo.PlayerSalaryVO;
import vo.PlayerTotalVO;

public class FourTablePane extends JPanel {

	private PanelConfig pcfg;
	private HomeUI frame;
	private Image bg;
	
	private MyLabel name;
	private MyLabel img;
	private MyPressedLabel totalChoose;
	private MyPressedLabel perGameChoose;
	private MyPressedLabel advancedChoose;
	
	private PlayerDetailPane playerDetailPane;
	public String playerName = "Kobe Bryant";

	public FourTablePane(HomeUI frame) {
		this.frame = frame;
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		this.bg = pcfg.getBg();
		
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());

		this.initComponent();
	}

	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	private void initComponent() {
		initLabels();
		initTable();
	}

	private void initLabels() {
		name = new MyLabel(pcfg.getLabels().element("name"));
		name.setFont(LoadFont.loadFont("HELVETICA.TTF", 0, 26));
		name.setForeground(new Color(237,85,101));
		img = new MyLabel(pcfg.getLabels().element("img"));
		
		totalChoose = new MyPressedLabel(pcfg.getLabels().element("total"));
		totalChoose.setFont(new Font("微软雅黑",0,17));
		totalChoose.setForeground(Color.WHITE);
		
		perGameChoose = new MyPressedLabel(pcfg.getLabels().element("pergame"));
		perGameChoose.setFont(new Font("微软雅黑",0,17));
		perGameChoose.setForeground(Color.WHITE);

		advancedChoose = new MyPressedLabel(pcfg.getLabels()
				.element("advanced"));
		advancedChoose.setFont(new Font("微软雅黑",0,17));
		advancedChoose.setForeground(Color.WHITE);

		addLabelAction(totalChoose, 0);
		addLabelAction(perGameChoose, 1);
		addLabelAction(advancedChoose, 2);
		add(name);
		//add(img);
		add(totalChoose);
		add(perGameChoose);
		add(advancedChoose);
		
	}

	public void initTable() {

		Object[][] data = new Object[10][22];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 22; j++) {
				data[i][j] = 0;
			}
		}
		playerDetailPane = new PlayerDetailPane(new TableConfig(
				pcfg.getTablepane()), data, frame);
		add(playerDetailPane);
	}

	private void addLabelAction(MyPressedLabel lb, int num) {
		lb.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				resetAllLabels();
				lb.setText("<HTML><U>" + lb.getName() + "</U></HTML>");
				// System.out.println(num);
				setTabelData(num);
			}
		});

	}

	private void resetAllLabels() {
		totalChoose.setText(totalChoose.getName());
		perGameChoose.setText(perGameChoose.getName());
		advancedChoose.setText(advancedChoose.getName());
	}

	
	public void changeName(String name){
		this.playerName = name;
		setTabelData(0);
		totalChoose.setText("<HTML><U>" + totalChoose.getName() + "</U></HTML>");
		ImageIcon icon = null;
		try {
			icon=ServiceFactoryImpl.getInstance().getPlayerService().getPlayerPortraitByName(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(icon!=null){
			img.setImage(icon);
			img.setIcon(icon);
			}else{
				icon = new ImageIcon("img/player/unknown.png");
				icon.setImage(icon.getImage().getScaledInstance(169, 130,
						Image.SCALE_SMOOTH));
				img.setIcon(icon);
			}
		
		this.name.setText(name);
		
		
	}
	private void setTabelData(int num) {

		String[] header0 = { "SEASON", "TEAM", "G", "GS", "MIN", "FG", "FGA%",
				"FG3", "FG3%", "FG2", "FG2%", "EFG%", "FT", "FT%", "TRB",
				"AST", "STL", "BLK", "TOV", "PF", "PTS", "$" };
		String[] header1 = { "SEASON", "TEAM", "PER", "TS%", "F3AR", "FTAR",
				"ORB%", "TRB%", "AST%", "STL%", "BLK%", "TOV%", "USG%", "OWS",
				"DWS", "WS", "WS48", "OBPM", "DBPM", "BPM", "VORP", "$" };
		List<PlayerSalaryVO> salarylist = null;
		try {
			salarylist = ServiceFactoryImpl.getInstance().getPlayerService()
					.getPlayerSalaryByName(playerName);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		switch (num) {
		case 0: {

			List<PlayerTotalVO> list = null;
			try {
				list = ServiceFactoryImpl.getInstance().getPlayerService()
						.getPlayerTotalByName(playerName, 2);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object[][] data = new Object[list.size()][22];
			for (int i = 0;i<list.size();i++) {
				data[i][0] = list.get(i).season;
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
				data[i][21] = "";
				jump: for (int j = 0; j < salarylist.size(); j++) {
					if (salarylist.get(j).season.equals(list.get(i).season)) {
						data[i][21] = salarylist.get(j).salary;
						break jump;
					}
				}

			}

			playerDetailPane.setcolumnName(header0);
			playerDetailPane.updateWidth();
			playerDetailPane.renewTable(data);
			break;
		}
		case 1: {
			List<PlayerPerGameVO> list = null;
			try {
				list = ServiceFactoryImpl.getInstance().getPlayerService()
						.getPlayerPerGameByName(playerName, 2);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object[][] data = new Object[list.size()][22];
			for (int i = list.size()-1; i >=0; i--) {
				data[i][0] = list.get(i).season;
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
				data[i][21] = "";
				for (int j = 0; j < salarylist.size(); j++) {
					if (salarylist.get(j).season.equals(list.get(i).season)) {
						data[i][21] = salarylist.get(j).salary;
						break;
					}
				}

			}
			playerDetailPane.setcolumnName(header0);
			playerDetailPane.updateWidth();
			playerDetailPane.renewTable(data);
			break;
		}

		case 2: {

			List<PlayerAdvancedVO> list = null;
			try {

				list = ServiceFactoryImpl.getInstance().getPlayerService()
						.getPlayerAdvancedByName(playerName, 2);
			} catch (RemoteException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}

			Object[][] data = new Object[list.size()][22];
			for (int i = list.size()-1; i >=0; i--) {
				data[i][0] = list.get(i).season;
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
				data[i][21] = "";
				for (int j = 0; j < salarylist.size(); j++) {
					if (salarylist.get(j).season.equals(list.get(i).season)) {
						data[i][21] = salarylist.get(j).salary;
						break;
					}
				}

			}
			playerDetailPane.setcolumnName(header1);
			playerDetailPane.updateWidth();
			playerDetailPane.renewTable(data);
			break;
		}
		}
	}
}
