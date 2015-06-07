package ui.team;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyButton;

public class TeamIndex extends JPanel{

	private PanelConfig pcfg;
	private HomeUI frame;
	private Image bg;
	
	private MyButton hou;
	private MyButton mem;
	private MyButton sas;
	private MyButton dal;
	private MyButton nop;
	
	private MyButton gsw;
	private MyButton lac;
	private MyButton phx;
	private MyButton sac;
	private MyButton lal;
	
	private MyButton por;
	private MyButton okc;
	private MyButton uta;
	private MyButton den;
	private MyButton min;
	
	private MyButton tor;
	private MyButton bos;
	private MyButton bkn;
	private MyButton phi;
	private MyButton nyk;
	
	private MyButton atl;
	private MyButton was;
	private MyButton mia;
	private MyButton cha;
	private MyButton orl;
	
	private MyButton cle;
	private MyButton chi;
	private MyButton mil;
	private MyButton ind;
	private MyButton det;
	
	public TeamIndex(HomeUI frame){
	this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap().
			get(this.getClass().getName());
	this.frame = frame;
	this.bg = pcfg.getBg();
	
	this.setLayout(null);
	this.setSize(pcfg.getW(), pcfg.getH());
	this.setLocation(pcfg.getX(),pcfg.getY());
	this.setOpaque(false);
	
	initComponent();
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
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
		lac = new MyButton(pcfg.getButtons().element("lac"),true);
		add(lac);
		phx = new MyButton(pcfg.getButtons().element("phx"),true);
		add(phx);
		sac = new MyButton(pcfg.getButtons().element("sac"),true);
		add(sac);
		lal = new MyButton(pcfg.getButtons().element("lal"),true);
		add(lal);
		
		por = new MyButton(pcfg.getButtons().element("por"),true);
		add(por);
		okc = new MyButton(pcfg.getButtons().element("okc"),true);
		add(okc);
		uta = new MyButton(pcfg.getButtons().element("uta"),true);
		add(uta);
		den = new MyButton(pcfg.getButtons().element("den"),true);
		add(den);
		min = new MyButton(pcfg.getButtons().element("min"),true);
		add(min);
		
		tor = new MyButton(pcfg.getButtons().element("tor"),true);
		add(tor);
		bos = new MyButton(pcfg.getButtons().element("bos"),true);
		add(bos);
		bkn = new MyButton(pcfg.getButtons().element("bkn"),true);
		add(bkn);
		phi = new MyButton(pcfg.getButtons().element("phi"),true);
		add(phi);
		nyk = new MyButton(pcfg.getButtons().element("nyk"),true);
		add(nyk);
		
		atl = new MyButton(pcfg.getButtons().element("atl"),true);
		add(atl);
		was = new MyButton(pcfg.getButtons().element("was"),true);
		add(was);
		mia = new MyButton(pcfg.getButtons().element("mia"),true);
		add(mia);
		cha = new MyButton(pcfg.getButtons().element("cha"),true);
		add(cha);
		orl = new MyButton(pcfg.getButtons().element("orl"),true);
		add(orl);
		
		
		cle = new MyButton(pcfg.getButtons().element("cle"),true);
		add(cle);
		chi = new MyButton(pcfg.getButtons().element("chi"),true);
		add(chi);
		mil = new MyButton(pcfg.getButtons().element("mil"),true);
		add(mil);
		ind = new MyButton(pcfg.getButtons().element("ind"),true);
		add(ind);
		det = new MyButton(pcfg.getButtons().element("det"),true);
		add(det);
	}
	

}
