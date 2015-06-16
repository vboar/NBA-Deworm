package ui.team;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.LoadFont;
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import util.FieldType;
import vo.HotTeamInfoVO;

public class TeamHot extends JPanel{

	private HomeUI frame;
	private PanelConfig pcfg;
	private Image bg;

	private MyLabel[] teams= new MyLabel[5];
	private MyLabel[] datas= new MyLabel[5];
	private MyLabel[] teamImgs= new MyLabel[5];
	private MyLabel hint;
	private MyLabel location;
	private MyLabel league;
	private MyLabel division;
	
	private MyComboBox type;
	private MyComboBox season;
	private MyButton button;
	
	List<HotTeamInfoVO> hotlist;
	List<ImageIcon> teamimglist;
	
	public TeamHot(HomeUI frame){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.bg = pcfg.getBg();
		this.frame = frame;
		
		// 设置布局管理器为自由布局
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		
		// 初始化组件
		this.initComponent();
		makeChangePrep();
		this.repaint();
		this.validate();
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	private void initComponent(){
		initLabels();
		initBoxes();
		initButtons();
	}
	
	private void initLabels(){
		hint = new MyLabel(pcfg.getLabels().element("hint"));
		add(hint);
		location = new MyLabel(pcfg.getLabels().element("location"));
		location.setForeground(new Color(83,83,83));
		location.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 18));
		add(location);
		league = new MyLabel(pcfg.getLabels().element("league"));
		league.setForeground(new Color(83,83,83));
		league.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 18));
		add(league);
		division = new MyLabel(pcfg.getLabels().element("division"));
		division.setForeground(new Color(83,83,83));
		division.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 18));
		add(division);
		
		for(int i=0;i<5;i++){
			teams[i] = new MyLabel(pcfg.getLabels().element("team"+i));
			teams[i].setForeground(new Color(83,83,83));
			if(i==0){
				teams[i].setFont(LoadFont.loadFont("HELVETICA.TTF",0,20));
			}else{
				teams[i].setFont(LoadFont.loadFont("XIHEI.TTF",0,16));
			}
			//addListener(teams[i]);
			add(teams[i]);
			
			datas[i] = new MyLabel(pcfg.getLabels().element("data"+i));
			datas[i].setForeground(new Color(83,83,83));
			if(i==0)
				datas[i].setFont(LoadFont.loadFont("XIHEI.TTF",1,36));
			else
				datas[i].setFont(LoadFont.loadFont("XIHEI.TTF",1,25));
			add(datas[i]);	
			
			teamImgs[i] = new MyLabel(pcfg.getLabels().element("teamImg"+i));
			add(teamImgs[i]);
		}
	}
	
	

	private void initBoxes(){
		type = new MyComboBox(pcfg.getComboboxes().element("type"));
		add(type);
		type.updateUI();

		season = new MyComboBox(pcfg.getComboboxes().element("season"));
		add(season);
		season.updateUI();

		
	}
	
	private class ChangeThread2 implements Runnable {

        @Override
        public void run() {
            makeChangePrep();
        }
    }
	
	private void initButtons(){
		button = new MyButton(pcfg.getButtons().element("search"));
		addButtonAction(button);
		add(button);
	}

	private void addButtonAction(MyButton btn){
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new ChangeThread2()).start();
			}
		});
		
	}
	
	private void makeChangePrep(){
		int item = type.getSelectedIndex();
		String seasonStr = season.getSelectedItem().toString();
	
		try {

			teamimglist = new ArrayList<ImageIcon>(5);
			hotlist = ServiceFactoryImpl.getInstance().getTeamService().getSeasonHotTeam(seasonStr, FieldType.values()[item].ordinal(),5);
			for(int i= 0;i<5;i++){
			ImageIcon icon = ServiceFactoryImpl.getInstance().getTeamService().getTeamLogoByAbbr(hotlist.get(i).abbr);					
			
			teamimglist.add(icon);
			}
			
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		changeData();
	}
	
	private void changeData(){
		
		try {
			location.setText(ServiceFactoryImpl.getInstance().getTeamService().
					getTeamInfoByAbbr(hotlist.get(0).abbr).location);
			league.setText(ServiceFactoryImpl.getInstance().getTeamService().
					getTeamInfoByAbbr(hotlist.get(0).abbr).league);
			division.setText(ServiceFactoryImpl.getInstance().getTeamService().
					getTeamInfoByAbbr(hotlist.get(0).abbr).division);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for(int i = 0;i<5;i++){

			teams[i].setText(hotlist.get(i).name);
			
			datas[i].setText(hotlist.get(i).value);
			
			if(teamimglist.get(i) != null){
			teamImgs[i].setImage(teamimglist.get(i));
			}else{
				teamImgs[i].setIconPath("img/player/unknown.png");
			}
			
		}
		hint.setText(season.getSelectedItem().toString()+ "   "+type.getSelectedItem().toString());
		hint.setForeground(new Color(237,85,101));
		hint.setFont(new Font("HELVETICA",1,35));
		repaint();
		this.validate();
	}
}
