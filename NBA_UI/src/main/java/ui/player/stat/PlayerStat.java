package ui.player.stat;

import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.util.MyTab;
import vo.PlayerInfoVO;
import vo.PlayerTotalVO;

public class PlayerStat extends JPanel{
	private PanelConfig pcfg;
	private HomeUI frame;
	private MyTab stat;
	
	public PlayerAllTablePane table;
	
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
		initTabs();
		initTable();
	}
	
	private void initTabs(){
		stat = new MyTab(pcfg.getTab().element("stat"));
		add(stat);
	}
	
	private void initTable(){
		List<PlayerTotalVO> volist = null;
		try {
			volist=ServiceFactoryImpl.getInstance().getPlayerService().getPlayerTotalBySeason("14-15");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object[][] data2 = new Object[volist.size()][8];
		for(int i=0;i<volist.size();i++){			
				data2[i][0] =volist.get(i).name;
				data2[i][1] = volist.get(i).team;
				data2[i][2] = volist.get(i).pts;
				data2[i][3] = volist.get(i).blk;
				data2[i][4] = volist.get(i).trb;
				data2[i][5] = volist.get(i).blk;
				data2[i][6] = volist.get(i).stl;
				data2[i][7] = volist.get(i).game_started;
		}
			table = new PlayerAllTablePane(new TableConfig(pcfg.getTablepane()), data2);
			add(table);
	}
}
