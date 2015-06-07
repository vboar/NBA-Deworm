package ui.team;

import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyButton;

public class TeamIndex extends JPanel{

	private PanelConfig pcfg;
	private HomeUI frame;
	
	private MyButton hou;
	private MyButton mem;
	private MyButton sas;
	private MyButton dal;
	private MyButton nop;
	
	private MyButton gsw;
	
	private MyButton por;
	
	private MyButton tor;
	
	private MyButton atl;
	
	private MyButton cle;
	public TeamIndex(HomeUI frame){
	this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap().
			get(this.getClass().getName());
	this.frame = frame;
	
	this.setLayout(null);
	this.setSize(pcfg.getW(), pcfg.getH());
	this.setLocation(pcfg.getX(),pcfg.getY());
	this.setOpaque(false);
	
	initComponent();
	}
	
	private void initComponent(){
		initButtons();
	}
	
	private void initButtons(){
		hou = new MyButton(pcfg.getButtons().element("hou"),true);
		add(hou);
		mem = new MyButton(pcfg.getButtons().element("mem"),true);
		add(mem);
		sas = new MyButton(pcfg.getButtons().element("sas"),true);
		add(sas);
		dal = new MyButton(pcfg.getButtons().element("dal"),true);
		add(dal);
		nop = new MyButton(pcfg.getButtons().element("nop"),true);
		add(nop);
		
		gsw = new MyButton(pcfg.getButtons().element("gsw"),true);
		add(gsw);
		
		por = new MyButton(pcfg.getButtons().element("por"),true);
		add(por);
		
		tor = new MyButton(pcfg.getButtons().element("tor"),true);
		add(tor);
		
		atl = new MyButton(pcfg.getButtons().element("atl"),true);
		add(atl);
		
		cle = new MyButton(pcfg.getButtons().element("cle"),true);
		add(cle);
	}
	

}
