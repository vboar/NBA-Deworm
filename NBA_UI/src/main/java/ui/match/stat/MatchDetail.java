package ui.match.stat;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.player.info.PlayerDetailPane;
import ui.util.MyLabel;
import ui.util.MyPressedLabel;
import vo.MatchInfoVO;
import vo.MatchPlayerAdvancedVO;
import vo.MatchPlayerBasicVO;

public class MatchDetail extends JPanel{
	private HomeUI frame;
	private PanelConfig pcfg;
	

	private MyLabel info;
	
	private MyPressedLabel basicChoose;
	private MyPressedLabel advancedChoose;
	private MyPressedLabel basicChoose2;
	private MyPressedLabel advancedChoose2;
	
	
	public String id = " ";

	public String hometeam;
	public String guestteam;
	public String homepoint;
	public String guestpoint;
	public String date;
	
	private MatchPlayerTabel table;
	
	
	public MatchDetail(HomeUI frame) {
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;

		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());

		this.initComponent();
	}
	
	private void initComponent() {
		initLabels();
		initTable();
	}
	
	
	private void initLabels() {
		
		info = new MyLabel(pcfg.getLabels().element("info"));
		basicChoose = new MyPressedLabel(pcfg.getLabels().element("basic"));
		advancedChoose = new MyPressedLabel(pcfg.getLabels()
				.element("advanced"));
		basicChoose2 = new MyPressedLabel(pcfg.getLabels().element("basic2"));
		advancedChoose2 = new MyPressedLabel(pcfg.getLabels()
				.element("advanced2"));
		addLabelAction(basicChoose, 0);
		addLabelAction(advancedChoose, 1);
		addLabelAction(basicChoose2, 2);
		addLabelAction(advancedChoose2, 3);
		
		add(info);
		
		add(basicChoose);
		add(advancedChoose);
		add(basicChoose2);
		add(advancedChoose2);
		
	}
	
	public void initTable() {

		Object[][] data = new Object[10][22];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 22; j++) {
				data[i][j] = 0;
			}
		}
		table = new MatchPlayerTabel(new TableConfig(
				pcfg.getTablepane()), data, frame);
		add(table);
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
		basicChoose2.setText(basicChoose2.getName());
		advancedChoose2.setText(advancedChoose2.getName());
		basicChoose.setText(basicChoose.getName());
		advancedChoose.setText(advancedChoose.getName());
	}
	
	public void changeName(String id){
		this.id = id;
		
		basicChoose.setText("<HTML><U>" + basicChoose.getName() + "</U></HTML>");
		MatchInfoVO vo = null;;
		try {
			vo = ServiceFactoryImpl.getInstance().getMatchService().getMatchInfoByGameId(id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.hometeam = vo.home_team;
		this.homepoint = vo.home_point+"";
		this.guestpoint = vo.guest_point+"";
		this.guestteam = vo.guest_team;
		this.date = vo.date;
		this.info.setText(vo.date+" "+vo.home_team+"("+vo.home_point+")-"+vo.guest_team+"("+vo.guest_point+")");
		setTabelData(0);
		table.updateWidth();
		table.repaint();
		
	}

	private void setTabelData(int num) {

		String[] header0 = { "NAME", "MIN", "FG", "FGA%", "FG3", "FG3%", "FT",
				"FT%", "ORB", "DRB", "TRB", "AST", "STL","BLK","TOV", "PF", "PTS" };
		String[] header1 = { "NAME", "START","MIN", "TS%", "EFG%", "F3AR", "FTAR",
				"ORB%", "DRB%","TRB%", "AST%", "STL%","TOV%", "BLK%", "USG%", "OFF",
				"DEF"};
		switch (num) {
		case 0: {

			List<MatchPlayerBasicVO> list = null;
			try {
				list = ServiceFactoryImpl.getInstance().getMatchService().getMatchPlayerBasicByGameIdTeam(id, hometeam);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object[][] data = new Object[list.size()][18];
			for (int i = 0;i<list.size();i++) {
				data[i][0] = list.get(i).player_name;				
				data[i][1] = list.get(i).minute;
				data[i][2] = list.get(i).fg;
				data[i][3] = list.get(i).fga_pct;
				data[i][4] = list.get(i).fg3;
				data[i][5] = list.get(i).fg3_pct;
				data[i][6] = list.get(i).ft;
				data[i][7] = list.get(i).ft_pct;
				data[i][8] = list.get(i).orb;
				data[i][9] = list.get(i).drb;
				data[i][10] = list.get(i).trb;
				data[i][11] = list.get(i).ast;
				data[i][12] = list.get(i).stl;
				data[i][13] = list.get(i).blk;
				data[i][14] = list.get(i).tov;
				data[i][15] = list.get(i).pf;
				data[i][16] = list.get(i).pts;
				

			}

			table.setcolumnName(header0);
			table.updateWidth();
			table.renewTable(data);
			break;
		}
		case 1: {
			List<MatchPlayerAdvancedVO> list = null;
			try {
				list = ServiceFactoryImpl.getInstance().getMatchService()
						.getMatchPlayerAdvancedByGameIdTeam(id,hometeam);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object[][] data = new Object[list.size()][18];
			for (int i = list.size()-1; i >=0; i--) {
				data[i][0] = list.get(i).player_name;
				data[i][1] = list.get(i).starter;
				data[i][2] = list.get(i).minute;
				data[i][3] = list.get(i).ts_pct;
				data[i][4] = list.get(i).efg_pct;
				data[i][5] = list.get(i).fa3a_per_fga_pct;
				data[i][6] = list.get(i).fta_per_fga_pct;
				data[i][7] = list.get(i).orb_pct;
				data[i][8] = list.get(i).drb_pct;
				data[i][9] = list.get(i).trb_pct;
				data[i][10] = list.get(i).ast_pct;
				data[i][11] = list.get(i).stl_pct;
				data[i][12] = list.get(i).tov_pct;
				data[i][13] = list.get(i).blk_pct;
				data[i][14] = list.get(i).usg_pct;
				data[i][15] = list.get(i).off_rtg;
				data[i][16] = list.get(i).def_rtg;
				

			}
			table.setcolumnName(header1);
			table.updateWidth();
			table.renewTable(data);
			break;
		}

		case 2: {

			List<MatchPlayerBasicVO> list = null;
			try {
				list = ServiceFactoryImpl.getInstance().getMatchService().getMatchPlayerBasicByGameIdTeam(id, guestteam);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object[][] data = new Object[list.size()][18];
			for (int i = 0;i<list.size();i++) {
				data[i][0] = list.get(i).player_name;				
				data[i][1] = list.get(i).minute;
				data[i][2] = list.get(i).fg;
				data[i][3] = list.get(i).fga_pct;
				data[i][4] = list.get(i).fg3;
				data[i][5] = list.get(i).fg3_pct;
				data[i][6] = list.get(i).ft;
				data[i][7] = list.get(i).ft_pct;
				data[i][8] = list.get(i).orb;
				data[i][9] = list.get(i).drb;
				data[i][10] = list.get(i).trb;
				data[i][11] = list.get(i).ast;
				data[i][12] = list.get(i).stl;
				data[i][13] = list.get(i).blk;
				data[i][14] = list.get(i).tov;
				data[i][15] = list.get(i).pf;
				data[i][16] = list.get(i).pts;
				

			}

			table.setcolumnName(header0);
			table.updateWidth();
			table.renewTable(data);
			break;
		}
		case 3: {
			List<MatchPlayerAdvancedVO> list = null;
			try {
				list = ServiceFactoryImpl.getInstance().getMatchService()
						.getMatchPlayerAdvancedByGameIdTeam(id,guestteam);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object[][] data = new Object[list.size()][18];
			for (int i = list.size()-1; i >=0; i--) {
				data[i][0] = list.get(i).player_name;
				data[i][1] = list.get(i).starter;
				data[i][2] = list.get(i).minute;
				data[i][3] = list.get(i).ts_pct;
				data[i][4] = list.get(i).efg_pct;
				data[i][5] = list.get(i).fa3a_per_fga_pct;
				data[i][6] = list.get(i).fta_per_fga_pct;
				data[i][7] = list.get(i).orb_pct;
				data[i][8] = list.get(i).drb_pct;
				data[i][9] = list.get(i).trb_pct;
				data[i][10] = list.get(i).ast_pct;
				data[i][11] = list.get(i).stl_pct;
				data[i][12] = list.get(i).tov_pct;
				data[i][13] = list.get(i).blk_pct;
				data[i][14] = list.get(i).usg_pct;
				data[i][15] = list.get(i).off_rtg;
				data[i][16] = list.get(i).def_rtg;
				

			}
			table.setcolumnName(header1);
			table.updateWidth();
			table.renewTable(data);
			break;
		}
		
		}
	}
}
