package ui.player.hot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import util.FieldType;
import vo.HotPlayerInfoVO;

public class PlayerHotPane extends JPanel {
	private PanelConfig pcfg ;
	private HomeUI frame;
	
//	private MyLabel name1;
//	private MyLabel position1;
//	private MyLabel num1;
//	private MyLabel team1;
//	private MyLabel data1;
//	private MyLabel playerImg1;
//	private MyLabel teamImg1;
//	
	
	private MyLabel[] names;
	private MyLabel[] positions;
	private MyLabel[] teams;
	private MyLabel[] datas;
	private MyLabel[] playerImgs;
	private MyLabel[] teamImgs;
	
	private MyComboBox type;
	private MyComboBox season;
	private MyButton button;
	
	List<HotPlayerInfoVO> volist =null;
	List<String> teamlist = null;
	List<ImageIcon> playerimglist = null;
	List<ImageIcon> teamimglist = null;
	
	
	
	public PlayerHotPane(HomeUI frame){
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
		initLabels();
		initBoxes();
		initButtons();
	}
	
	private void initLabels(){
		for(int i = 0;i<5;i++){
			names[i] = new MyLabel(pcfg.getLabels().element("name"+i));
			add(names[i]);
			
			positions[i] = new MyLabel(pcfg.getLabels().element("position"+i));
			add(positions[i]);
			
			
			teams[i] = new MyLabel(pcfg.getLabels().element("team"+i));
			add(teams[i]);
			
			datas[i] = new MyLabel(pcfg.getLabels().element("data"+i));
			add(datas[i]);
			
			playerImgs[i] = new MyLabel(pcfg.getLabels().element("playerImg"+i));
			add(playerImgs[i]);
			
			teamImgs[i] = new MyLabel(pcfg.getLabels().element("teamImg"+i));
			add(teamImgs[i]);
		}
	}
	
	private void initBoxes(){
		type = new MyComboBox(pcfg.getComboboxes().element("type"));
		add(type);
		
		season = new MyComboBox(pcfg.getComboboxes().element("season"));
		add(season);
		
	
	}
	
	private void initButtons(){
		button = new MyButton(pcfg.getButtons().element("button"));
		add(button);
		
	}
	
	
	private void addButtonAction(MyButton btn){
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int item = type.getSelectedIndex();
				String seasonStr = season.getSelectedItem().toString();
			
				try {
					volist = ServiceFactoryImpl.getInstance().getPlayerService().getSeasonHotPlayer(seasonStr, FieldType.values()[item],5);
					for(int i= 0;i<5;i++){
					ImageIcon icon = ServiceFactoryImpl.getInstance().getPlayerService().getPlayerPortraitByName(volist.get(i).name);					
					String teamStr = ServiceFactoryImpl.getInstance().getPlayerService().getTeamByPlayerNameSeason(volist.get(i).name, seasonStr).get(0);
					teamStr = teamStr.split(";")[0];
					ImageIcon teamIcon  = ServiceFactoryImpl.getInstance().getTeamService().getTeamLogoByAbbr(teamStr);
					teamimglist.set(i,teamIcon);
					teamlist.set(i, teamStr);
					playerimglist.set(i, icon);
					}
					
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				changeData();
			}
		});
		
	}
	
	private void changeData(){
		for(int i = 0;i<5;i++){
			names[i].setText(volist.get(i).name);
			
			positions[i].setText(volist.get(i).position);
			
			
			teams[i].setText(teamlist.get(i));
			
			datas[i].setText(volist.get(i).value);
			
			playerImgs[i].setImage(playerimglist.get(i));
			
			teamImgs[i].setImage(teamimglist.get(i));
		}
		repaint();
		
	}
}
