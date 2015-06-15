package ui.team;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import ui.util.LoadFont;
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MyTab;
import vo.TeamInfoVO;
import vo.TeamPerGameVO;
import vo.TeamTotalVO;

public class TeamStat extends JPanel{

	private PanelConfig pcfg;
	private HomeUI frame;
	
	public TeamFilter teamfilter;
	
	private String item;
	
	private MyTab stat;
	private MyLabel settingbg;
	private MyLabel hint;
	private MyButton setting;
	private MyButton menu;
	
	private Object[][] data1;
	private Object[][] data2;
	private TeamStatTablePane table1;
	private TeamStatTablePane table2;
	private List<TeamPerGameVO> list1;
	private List<TeamTotalVO> list2;
	
	private MyComboBox year;
	
	private TeamInfoVO teaminfo;
	
	public TeamStat(HomeUI frame){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap().
				get(this.getClass().getName());
		this.frame = frame;
		
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		
		initComponent();
		updateUI();
	}
	
	private void initComponent(){
		initData();
		initTable();
		initButtons();
		initLabels();
		initPanels();
		initTabs();
		initBox();
	}
	
	private void initData(){
		try {
			list1 = ServiceFactoryImpl.getInstance().getTeamService().getTeamPerGameBySeason("14-15");
			list2 = ServiceFactoryImpl.getInstance().getTeamService().getTeamTotalBySeason("14-15");
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		data1 = new Object[list1.size()][24];
		for(int i=0;i<list1.size();i++){
			data1[i][0] = list1.get(i).abbr;
			data1[i][1] = list1.get(i).season;
			data1[i][2] = list2.get(i).num_of_game;
			data1[i][3] = list2.get(i).wins;
			data1[i][4] = list2.get(i).losses;
			data1[i][5] = list1.get(i).minute;
			data1[i][6] = list1.get(i).fg;
			data1[i][7] = list1.get(i).fga;
			data1[i][8] = list1.get(i).fga_pct;
			data1[i][9] = list1.get(i).fg3;
			data1[i][10] = list1.get(i).fg3a;
			data1[i][11] = list1.get(i).fg3_pct;
			data1[i][12] = list1.get(i).ft;
			data1[i][13] = list1.get(i).fta;
			data1[i][14] = list1.get(i).ft_pct;
			data1[i][15] = list1.get(i).orb;
			data1[i][16] = list1.get(i).drb;
			data1[i][17] = list1.get(i).trb;
			data1[i][18] = list1.get(i).ast;
			data1[i][19] = list1.get(i).stl;
			data1[i][20] = list1.get(i).blk;
			data1[i][21] = list1.get(i).tov;
			data1[i][22] = list1.get(i).pf;
			data1[i][23] = list1.get(i).pts;
		}
		
		data2 = new Object[list2.size()][24];
		for(int i=0;i<list2.size();i++){
			data2[i][0] = list2.get(i).abbr;
			data2[i][1] = list2.get(i).season;
			data2[i][2] = list2.get(i).num_of_game;
			data2[i][3] = list2.get(i).wins;
			data2[i][4] = list2.get(i).losses;
			data2[i][5] = list2.get(i).minute;
			data2[i][6] = list2.get(i).fg;
			data2[i][7] = list2.get(i).fga;
			data2[i][8] = list2.get(i).fga_pct;
			data2[i][9] = list2.get(i).fg3;
			data2[i][10] = list2.get(i).fg3a;
			data2[i][11] = list2.get(i).fg3_pct;
			data2[i][12] = list2.get(i).ft;
			data2[i][13] = list2.get(i).fta;
			data2[i][14] = list2.get(i).ft_pct;
			data2[i][15] = list2.get(i).orb;
			data2[i][16] = list2.get(i).drb;
			data2[i][17] = list2.get(i).trb;
			data2[i][18] = list2.get(i).ast;
			data2[i][19] = list2.get(i).stl;
			data2[i][20] = list2.get(i).blk;
			data2[i][21] = list2.get(i).tov;
			data2[i][22] = list2.get(i).pf;
			data2[i][23] = list2.get(i).pts;
		}
	}
	
	private void initLabels(){
		hint = new MyLabel(pcfg.getLabels().element("hint"));
		hint.setForeground(new Color(83,83,83));
		hint.setFont(LoadFont.loadFont("HELVETICA.TTF", 0, 26));
		add(hint);
		
		settingbg = new MyLabel(pcfg.getLabels().element("settingbg"));
		add(settingbg);
	}
	
	private void initButtons(){
		menu = new MyButton(pcfg.getButtons().element("menu"),true);
		//add(menu);
		
		setting = new MyButton(pcfg.getButtons().element("setting"));
		setting.addMouseListener(new MouseAdapter(){
			String[] temp = pcfg.getButtons().element("setting").attributeValue("path").split("\\.");
			String path = temp[0];
			String fix = temp[1];
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(teamfilter.isVisible() == false){
					teamfilter.setVisible(true);
					}else{
						teamfilter.setVisible(false);
					}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(teamfilter.isVisible()==false)
				setting.setIcon(new ImageIcon(path+"_point."+fix));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(teamfilter.isVisible() == false)
					setting.setIcon(new ImageIcon(path+"."+fix));
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				setting.setIcon(new ImageIcon(path+"_click."+fix));
				
			}

		});
		//add(setting);	
	}
	
	private void initTabs(){
		stat = new MyTab(pcfg.getTab().element("stat"));
		stat.tab1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				table1.setVisible(true);
				table2.setVisible(false);
			}	
		});
		stat.tab2.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				table1.setVisible(false);
				table2.setVisible(true);
			}	
		});
		add(stat);
	}
	
	private void initPanels(){
		teamfilter = new TeamFilter(frame);
		teamfilter.setVisible(false);
		
		add(teamfilter,0);
	}
	
	
	private void initTable(){
		table1 = new TeamStatTablePane(new TableConfig(pcfg.getTablepane().element("table1")),data1,this,frame);
		add(table1);

		table2 = new TeamStatTablePane(new TableConfig(pcfg.getTablepane().element("table2")),data2,this,frame);
		table2.setVisible(false);
		add(table2);
	}
	
	private void initBox(){
		year = new MyComboBox(pcfg.getComboboxes().element("year"));
		//add(year);
	}
	
	
	
	public void filter(){
		setting.setIcon(new ImageIcon(pcfg.getButtons().element("setting").attributeValue("path")));
		teamfilter.setVisible(false);
	}
}
