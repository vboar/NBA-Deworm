/**
 * 选择的界面
 */
package ui.player.stat;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyButton;
import ui.util.MyComboBox;

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
			}
			
		});
		add(search);
	}
	
	private void initComboBox(){
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
}
