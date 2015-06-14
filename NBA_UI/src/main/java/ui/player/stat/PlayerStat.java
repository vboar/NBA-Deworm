package ui.player.stat;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.util.MyTab;
import util.ChineseToOther;
import vo.PlayerAdvancedVO;
import vo.PlayerPerGameVO;
import vo.PlayerTotalVO;

public class PlayerStat extends JPanel{
	private PanelConfig pcfg;
	private HomeUI frame;
	private MyTab stat;
	
	public PlayerAllTablePane table;
	
	public List<PlayerTotalVO> volist = null;
	public List<PlayerPerGameVO> volistavg = null;
	public List<PlayerAdvancedVO> volistadv = null;
	
	public String season="14-15";
	public boolean isAdvanced = false;
	public int isRegular = 1;
	public int state=0;//0:avg 1:all 2:adv
	

	

	
	public PlayerStat(HomeUI frame){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(),pcfg.getY());
		
		initComponent();
	}
	
	private void initComponent(){
		//initTabs();
		initTable();
	}
	
	private void initTabs(){
		//stat = new MyTab(pcfg.getTab().element("stat"));
		//setTab();
		//add(stat);
	}
	
	private void initTable(){
		try {
			volist=ServiceFactoryImpl.getInstance().getPlayerService().getPlayerTotalBySeason("14-15",1);
			volistavg=ServiceFactoryImpl.getInstance().getPlayerService().getPlayerPerGameBySeason("14-15",1);
			volistadv = ServiceFactoryImpl.getInstance().getPlayerService().getPlayerAdvancedBySeason("14-15", 1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object[][] data2 = new Object[volistavg.size()][21];
		for(int i=0;i<volistavg.size();i++){			
				data2[i][0] =volistavg.get(i).name;
				data2[i][1] = volistavg.get(i).team;
				data2[i][2] = volistavg.get(i).game;
				data2[i][3] = volistavg.get(i).game_started;
				data2[i][4] = volistavg.get(i).minute;
				data2[i][5] = volistavg.get(i).fg;				
				data2[i][6] = volistavg.get(i).fga_pct;
				data2[i][7] =volistavg.get(i).fg3;
				data2[i][8] = volistavg.get(i).fg3_pct;
				data2[i][9] = volistavg.get(i).fg2;
				data2[i][10] = volistavg.get(i).fg2_pct;
				data2[i][11] = volistavg.get(i).efg_pct;
				data2[i][12] = volistavg.get(i).ft;
				data2[i][13] = volistavg.get(i).ft_pct;
				data2[i][14] = volistavg.get(i).trb;
				data2[i][15] = volistavg.get(i).ast;
				data2[i][16] = volistavg.get(i).stl;
				data2[i][17] = volistavg.get(i).blk;
				data2[i][18] = volistavg.get(i).tov;
				data2[i][19] = volistavg.get(i).pf;
				data2[i][20] = volistavg.get(i).pts;
		}
			table = new PlayerAllTablePane(new TableConfig(pcfg.getTablepane()), data2,frame);
			add(table);
	}
	
//	private void setTab(){
//		
//		stat.tab2.addMouseListener(new MouseAdapter(){
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				
//				String[] head = frame.motherPanel.playerPanel.playerstat.table.getColumnNames();
//				Object[][] data = new Object[volist.size()][8];
//				for(int i=0;i<volist.size();i++){			
//					data[i][0] = volist.get(i).name;
//					 data[i][1] = volist.get(i).team;
//					 data[i][2] = ChineseToOther.ChineseToString(head[2], volist.get(i));
//					 data[i][3] = ChineseToOther.ChineseToString(head[3], volist.get(i));
//					 data[i][4] = ChineseToOther.ChineseToString(head[4], volist.get(i));
//					 data[i][5] = ChineseToOther.ChineseToString(head[5], volist.get(i));
//					 data[i][6] = ChineseToOther.ChineseToString(head[6], volist.get(i));
//					 data[i][7] = ChineseToOther.ChineseToString(head[7], volist.get(i));
//				
//				}
//				//System.out.println("hhhhhhhh");
//				table.setData(data);
//				state = 1;
//				frame.motherPanel.playerPanel.playerfilter.changeBox(false);
//			}
//			
//		});
//		
//		stat.tab1.addMouseListener(new MouseAdapter(){
//			@Override
//			public void mouseClicked(MouseEvent e) {
//
//				String[] head = frame.motherPanel.playerPanel.playerstat.table.getColumnNames();
//				Object[][] data = new Object[volistavg.size()][8];
//				for(int i=0;i<volistavg.size();i++){			
//					 data[i][0] = volistavg.get(i).name;
//					 data[i][1] = volistavg.get(i).team;
//					 data[i][2] = ChineseToOther.ChineseToString(head[2], volistavg.get(i));
//					 data[i][3] = ChineseToOther.ChineseToString(head[3], volistavg.get(i));
//					 data[i][4] = ChineseToOther.ChineseToString(head[4], volistavg.get(i));
//					 data[i][5] = ChineseToOther.ChineseToString(head[5], volistavg.get(i));
//					 data[i][6] = ChineseToOther.ChineseToString(head[6], volistavg.get(i));
//					 data[i][7] = ChineseToOther.ChineseToString(head[7], volistavg.get(i));
//				
//				}
//				table.setData(data);
//				state = 0;
//				frame.motherPanel.playerPanel.playerfilter.changeBox(false);
//
//			}	
//		});
//	}
}
