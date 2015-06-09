/**
 * 选择的界面
 */
package ui.player.stat;

import java.awt.Graphics;
import java.awt.Image;
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

public class PlayerFilter extends JPanel{
	private HomeUI frame;
	private PanelConfig pcfg;
	
	private MyButton search;
	
	private Image bg;
	
	private MyComboBox season;
	private MyComboBox position;
	private MyComboBox division;
	private MyComboBox regular;
	private MyComboBox box1;
	private MyComboBox box2;
	private MyComboBox box3;
	private MyComboBox box4;
	private MyComboBox box5;
	private MyComboBox box6;
	
	public PlayerFilter(HomeUI frame){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		this.bg = pcfg.getBg();
		
		this.setLayout(null);
		this.setSize(pcfg.getW(),pcfg.getH());
		this.setLocation(pcfg.getX(),pcfg.getY());
		
		this.initComponent();
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(),null);
	}
	
	public void initComponent(){
		initLabels();
		initButtons();
		initComboBox();
	}
	
	private void initLabels(){
		
	}
	
	private void initButtons(){
		search = new MyButton(pcfg.getButtons().element("search"),true);
		search.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.playerPanel.filter();
				
				int nowState = frame.motherPanel.playerPanel.playerstat.state;
				boolean nowAdv = frame.motherPanel.playerPanel.playerstat.isAdvanced;
				
				String seasonStr = season.getSelectedItem().toString();
				String positionStr = position.getSelectedItem().toString();
				String divisionStr = division.getSelectedItem().toString();
				int regularInt = regular.getSelectedIndex(); 
				
				vo.PlayerFilter filter = new vo.PlayerFilter();
				filter.division =divisionStr.equals("All")?null:divisionStr;
				filter.position = positionStr.equals("All")?null:positionStr;
				filter.season = seasonStr;
				filter.regular = regularInt;
				
				
				if(nowState==0){
					List<PlayerPerGameVO> list = null;
					try {
						
						list = ServiceFactoryImpl.getInstance().getPlayerService().getPlayerPerGameByFilter(filter);
					frame.motherPanel.playerPanel.playerstat.volistavg=list;
					
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Object[][] data = new Object[list.size()][8];
					for(int i=0;i<list.size();i++){
						data[i][0] = list.get(i).name;
						data[i][1] = list.get(i).team;
						data[i][2] = ChineseToOther.ChineseToString(box1.getSelectedItem().toString(),list.get(i));
						data[i][3] = ChineseToOther.ChineseToString(box2.getSelectedItem().toString(),list.get(i));
						data[i][4] = ChineseToOther.ChineseToString(box3.getSelectedItem().toString(),list.get(i));
						data[i][5] = ChineseToOther.ChineseToString(box4.getSelectedItem().toString(),list.get(i));
						data[i][6] = ChineseToOther.ChineseToString(box5.getSelectedItem().toString(),list.get(i));
						data[i][7] = ChineseToOther.ChineseToString(box6.getSelectedItem().toString(),list.get(i));				
					}
					String ss[] = new String[8];
					ss[0]= "球员名称";
					ss[1] = "所属球队";
					ss[2] = box1.getSelectedItem().toString();
					ss[3] = box2.getSelectedItem().toString();
					ss[4] = box3.getSelectedItem().toString();
					ss[5] = box4.getSelectedItem().toString();
					ss[6] = box5.getSelectedItem().toString();
					ss[7] = box6.getSelectedItem().toString();
					
					
					frame.motherPanel.playerPanel.playerstat.table.setData(data);
					frame.motherPanel.playerPanel.playerstat.table.setcolumnName(ss);
				}else if(nowState ==1){
					List<PlayerTotalVO> list = null;
					try {
						
						list = ServiceFactoryImpl.getInstance().getPlayerService().getPlayerTotalByFilter(filter);
					frame.motherPanel.playerPanel.playerstat.volist=list;
					
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Object[][] data = new Object[list.size()][8];
					for(int i=0;i<list.size();i++){
						data[i][0] = list.get(i).name;
						data[i][1] = list.get(i).team;
						data[i][2] = ChineseToOther.ChineseToString(box1.getSelectedItem().toString(),list.get(i));
						data[i][3] = ChineseToOther.ChineseToString(box2.getSelectedItem().toString(),list.get(i));
						data[i][4] = ChineseToOther.ChineseToString(box3.getSelectedItem().toString(),list.get(i));
						data[i][5] = ChineseToOther.ChineseToString(box4.getSelectedItem().toString(),list.get(i));
						data[i][6] = ChineseToOther.ChineseToString(box5.getSelectedItem().toString(),list.get(i));
						data[i][7] = ChineseToOther.ChineseToString(box6.getSelectedItem().toString(),list.get(i));				
					}
					String ss[] = new String[8];
					ss[0]= "球员名称";
					ss[1] = "所属球队";
					ss[2] = box1.getSelectedItem().toString();
					ss[3] = box2.getSelectedItem().toString();
					ss[4] = box3.getSelectedItem().toString();
					ss[5] = box4.getSelectedItem().toString();
					ss[6] = box5.getSelectedItem().toString();
					ss[7] = box6.getSelectedItem().toString();
					
					
					frame.motherPanel.playerPanel.playerstat.table.setData(data);
					frame.motherPanel.playerPanel.playerstat.table.setcolumnName(ss);
				}else{
					List<PlayerAdvancedVO> list = null;
					try {
						
						list = ServiceFactoryImpl.getInstance().getPlayerService().getPlayerAdvancedByFilter(filter);
					frame.motherPanel.playerPanel.playerstat.volistadv=list;
					
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Object[][] data = new Object[list.size()][8];
					for(int i=0;i<list.size();i++){
						data[i][0] = list.get(i).name;
						data[i][1] = list.get(i).team;
						data[i][2] = ChineseToOther.ChineseToString(box1.getSelectedItem().toString(),list.get(i));
						data[i][3] = ChineseToOther.ChineseToString(box2.getSelectedItem().toString(),list.get(i));
						data[i][4] = ChineseToOther.ChineseToString(box3.getSelectedItem().toString(),list.get(i));
						data[i][5] = ChineseToOther.ChineseToString(box4.getSelectedItem().toString(),list.get(i));
						data[i][6] = ChineseToOther.ChineseToString(box5.getSelectedItem().toString(),list.get(i));
						data[i][7] = ChineseToOther.ChineseToString(box6.getSelectedItem().toString(),list.get(i));				
					}
					String ss[] = new String[8];
					ss[0]= "球员名称";
					ss[1] = "所属球队";
					ss[2] = box1.getSelectedItem().toString();
					ss[3] = box2.getSelectedItem().toString();
					ss[4] = box3.getSelectedItem().toString();
					ss[5] = box4.getSelectedItem().toString();
					ss[6] = box5.getSelectedItem().toString();
					ss[7] = box6.getSelectedItem().toString();
					
					
					frame.motherPanel.playerPanel.playerstat.table.setData(data);
					frame.motherPanel.playerPanel.playerstat.table.setcolumnName(ss);
				}
				
			}
			
		});
		add(search);
	}
	
	private void initComboBox(){
		//int nowState = frame.motherPanel.playerPanel.playerstat.state;
		season = new MyComboBox(pcfg.getComboboxes().element("season"));
		add(season);
		position = new MyComboBox(pcfg.getComboboxes().element("position"));
		add(position);
		division = new MyComboBox(pcfg.getComboboxes().element("division"));
		add(division);
		regular = new MyComboBox(pcfg.getComboboxes().element("regular"));
		add(regular);
		
		box1 = new MyComboBox(pcfg.getComboboxes().element("common1"));
		add(box1);
		box2 = new MyComboBox(pcfg.getComboboxes().element("common2"));
		add(box2);
		box3 = new MyComboBox(pcfg.getComboboxes().element("common3"));
		add(box3);
		box4 = new MyComboBox(pcfg.getComboboxes().element("common4"));
		add(box4);
		box5 = new MyComboBox(pcfg.getComboboxes().element("common5"));
		add(box5);
		box6 = new MyComboBox(pcfg.getComboboxes().element("common6"));
		add(box6);
		
	}
	
	public void changeBox(boolean isadv){
		remove(box1);
		remove(box2);
		remove(box3);
		remove(box4);
		remove(box5);
		remove(box6);

		if(isadv){			
			box1 = new MyComboBox(pcfg.getComboboxes().element("advanced1"));
			add(box1);
			box2 = new MyComboBox(pcfg.getComboboxes().element("advanced2"));
			add(box2);			
			box3 = new MyComboBox(pcfg.getComboboxes().element("advanced3"));
			add(box3);
			box4 = new MyComboBox(pcfg.getComboboxes().element("advanced4"));
			add(box4);
			box5 = new MyComboBox(pcfg.getComboboxes().element("advanced5"));
			add(box5);
			box6 = new MyComboBox(pcfg.getComboboxes().element("advanced6"));
			add(box6);

		}else{
			box1 = new MyComboBox(pcfg.getComboboxes().element("common1"));
			add(box1);
			box2 = new MyComboBox(pcfg.getComboboxes().element("common2"));
			add(box2);
			box3 = new MyComboBox(pcfg.getComboboxes().element("common3"));
			add(box3);
			box4 = new MyComboBox(pcfg.getComboboxes().element("common4"));
			add(box4);
			box5 = new MyComboBox(pcfg.getComboboxes().element("common5"));
			add(box5);
			box6 = new MyComboBox(pcfg.getComboboxes().element("common6"));
			add(box6);
		}
	}
	
	public void setAdvTable(){
		List<PlayerAdvancedVO> list = null;
		list =frame.motherPanel.playerPanel.playerstat.volistadv;
		
	
		
		Object[][] data = new Object[list.size()][8];
		for(int i=0;i<list.size();i++){
			data[i][0] = list.get(i).name;
			data[i][1] = list.get(i).team;
			data[i][2] = ChineseToOther.ChineseToString(box1.getSelectedItem().toString(),list.get(i));
			data[i][3] = ChineseToOther.ChineseToString(box2.getSelectedItem().toString(),list.get(i));
			data[i][4] = ChineseToOther.ChineseToString(box3.getSelectedItem().toString(),list.get(i));
			data[i][5] = ChineseToOther.ChineseToString(box4.getSelectedItem().toString(),list.get(i));
			data[i][6] = ChineseToOther.ChineseToString(box5.getSelectedItem().toString(),list.get(i));
			data[i][7] = ChineseToOther.ChineseToString(box6.getSelectedItem().toString(),list.get(i));				
		}
		String ss[] = new String[8];
		ss[0]= "球员名称";
		ss[1] = "所属球队";
		ss[2] = box1.getSelectedItem().toString();
		ss[3] = box2.getSelectedItem().toString();
		ss[4] = box3.getSelectedItem().toString();
		ss[5] = box4.getSelectedItem().toString();
		ss[6] = box5.getSelectedItem().toString();
		ss[7] = box6.getSelectedItem().toString();
		
		
		frame.motherPanel.playerPanel.playerstat.table.setData(data);
		frame.motherPanel.playerPanel.playerstat.table.setcolumnName(ss);
	}
	


	
}
