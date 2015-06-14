package ui.match.stat;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.player.stat.PlayerAllTablePane;
import ui.util.MyButton;
import vo.MatchFilter;
import vo.MatchInfoVO;

public class MatchStat extends JPanel{

	private PanelConfig pcfg;
	private HomeUI frame;
	
	public MatchAllTablePane table;
	public List<MatchInfoVO> volist = null;
	
	public MyButton setting;
	public MyButton menu;
	
	public ui.match.stat.MatchFilter matchFilter;
	
	private JFrame coverFrame;
	
	public MatchStat(HomeUI frame){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(),pcfg.getY());
		
		initComponent();
	}
	
	private void initComponent(){
		initPanels();
		initLabels();
		initTable();
		initButtons();
	}
	
	private void initPanels(){
		matchFilter = new ui.match.stat.MatchFilter(frame);
		matchFilter.setVisible(false);
		initCover();
	}
	private void initCover(){
		coverFrame = new JFrame();
		coverFrame.setBounds(458,218,690, 260);
		coverFrame.setUndecorated(true);
		coverFrame.add(matchFilter);
		coverFrame.setAlwaysOnTop(true);
	}
	
	private void initTable(){
		MatchFilter filter = new MatchFilter();
		filter.season = "14-15";
		try {
			volist=ServiceFactoryImpl.getInstance().getMatchService().getMatchInfoByFilter(filter);
			} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object[][] data2 = new Object[volist.size()][8];
		for(int i=0;i<volist.size();i++){			
				data2[i][0] =volist.get(i).game_id;
				data2[i][1] = volist.get(i).date;
				data2[i][2] = volist.get(i).location;
				data2[i][3] = volist.get(i).home_team;
				data2[i][4] = volist.get(i).home_point;
				data2[i][5] = volist.get(i).guest_team;				
				data2[i][6] = volist.get(i).guest_point;
				data2[i][7] =volist.get(i).time;
		}
			table = new MatchAllTablePane(new TableConfig(pcfg.getTablepane()), data2,frame);
			add(table);
	}
	
	private void initLabels(){
		
	}
	
	
	private void initButtons(){
		menu = new MyButton(pcfg.getButtons().element("menu"),true);
		menu.addMouseListener(new MouseListener() {
			
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
				matchFilter.resetPanel();			
			}
		});
		
		add(menu);
		
		setting = new MyButton(pcfg.getButtons().element("setting"));
		setting.addMouseListener(new MouseAdapter(){
			String[] temp = pcfg.getButtons().element("setting").attributeValue("path").split("\\.");
			String path = temp[0];
			String fix = temp[1];
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(matchFilter.isVisible() == false){
					
					coverFrame.setVisible(true);
					
					matchFilter.setVisible(true);
					coverFrame.setVisible(true);
					}else{
						matchFilter.setVisible(false);
						coverFrame.setVisible(false);
						
					}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(matchFilter.isVisible()==false)
				setting.setIcon(new ImageIcon(path+"_point."+fix));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(matchFilter.isVisible() == false)
					setting.setIcon(new ImageIcon(path+"."+fix));
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				setting.setIcon(new ImageIcon(path+"_click."+fix));
				
			}

		});
		
		add(setting);
	}
	
	public void filter(){
		setting.setIcon(new ImageIcon(pcfg.getButtons().element("setting").attributeValue("path")));
		matchFilter.setVisible(false);
		coverFrame.setVisible(false);
		
	}
	
}
