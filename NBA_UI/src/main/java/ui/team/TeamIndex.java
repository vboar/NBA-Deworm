package ui.team;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
		hou.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"HOU"));
			}
			
		});
		add(hou);
		
		mem = new MyButton(pcfg.getButtons().element("mem"),true);
		mem.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"MEM"));
			}
			
		});
		add(mem);
		
		sas = new MyButton(pcfg.getButtons().element("sas"),true);
		sas.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"SAS"));
			}
			
		});
		add(sas);
		
		dal = new MyButton(pcfg.getButtons().element("dal"),true);
		dal.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"DAL"));
			}
			
		});
		add(dal);
		
		nop = new MyButton(pcfg.getButtons().element("nop"),true);
		nop.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"NOP"));
			}
			
		});
		add(nop);
		
		gsw = new MyButton(pcfg.getButtons().element("gsw"),true);
		gsw.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"GSW"));
			}
			
		});
		add(gsw);
		
		lac = new MyButton(pcfg.getButtons().element("lac"),true);
		lac.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"LAC"));
			}
			
		});
		add(lac);
		
		phx = new MyButton(pcfg.getButtons().element("phx"),true);
		phx.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"PHX"));
			}
			
		});
		add(phx);
		
		sac = new MyButton(pcfg.getButtons().element("sac"),true);
		sac.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"SAC"));
			}
			
		});
		add(sac);
		
		lal = new MyButton(pcfg.getButtons().element("lal"),true);
		lal.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"LAL"));
			}
			
		});
		add(lal);
		
		por = new MyButton(pcfg.getButtons().element("por"),true);
		por.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"POR"));
			}
			
		});
		add(por);
		
		okc = new MyButton(pcfg.getButtons().element("okc"),true);
		okc.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"OKC"));
			}
			
		});
		add(okc);
		
		uta = new MyButton(pcfg.getButtons().element("uta"),true);
		uta.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"UTA"));
			}
			
		});
		add(uta);
		
		den = new MyButton(pcfg.getButtons().element("den"),true);
		den.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"DEN"));
			}
			
		});
		add(den);
		
		min = new MyButton(pcfg.getButtons().element("min"),true);
		min.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"MIN"));
			}
			
		});
		add(min);
		
		tor = new MyButton(pcfg.getButtons().element("tor"),true);
		tor.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"TOR"));
			}
			
		});
		add(tor);
		
		bos = new MyButton(pcfg.getButtons().element("bos"),true);
		bos.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"BOS"));
			}
			
		});
		add(bos);
		
		bkn = new MyButton(pcfg.getButtons().element("bkn"),true);
		bkn.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"BKN"));
			}
			
		});
		add(bkn);
		
		phi = new MyButton(pcfg.getButtons().element("phi"),true);
		phi.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"PHI"));
			}
			
		});
		add(phi);
		
		nyk = new MyButton(pcfg.getButtons().element("nyk"),true);
		nyk.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"NYK"));
			}
			
		});
		add(nyk);
		
		atl = new MyButton(pcfg.getButtons().element("atl"),true);
		atl.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"ATL"));
			}
			
		});
		add(atl);
		
		was = new MyButton(pcfg.getButtons().element("was"),true);
		was.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"WAS"));
			}
			
		});
		add(was);
		
		mia = new MyButton(pcfg.getButtons().element("mia"),true);
		mia.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"MIA"));
			}
			
		});
		add(mia);
		
		cha = new MyButton(pcfg.getButtons().element("cha"),true);
		cha.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"CHA"));
			}
			
		});
		add(cha);
		
		orl = new MyButton(pcfg.getButtons().element("orl"),true);
		orl.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"ORL"));
			}
			
		});
		add(orl);
		
		cle = new MyButton(pcfg.getButtons().element("cle"),true);
		cle.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"CLE"));
			}
			
		});
		add(cle);
		
		chi = new MyButton(pcfg.getButtons().element("chi"),true);
		chi.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"CHI"));
			}
			
		});
		add(chi);
		
		mil = new MyButton(pcfg.getButtons().element("mil"),true);
		mil.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"MIL"));
			}
			
		});
		add(mil);
		
		ind = new MyButton(pcfg.getButtons().element("ind"),true);
		ind.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"IND"));
			}
			
		});
		add(ind);
		
		det = new MyButton(pcfg.getButtons().element("det"),true);
		det.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.motherPanel.teamPanel.teamindex.setVisible(false);
				frame.motherPanel.teamPanel.add(new TeamDetail(frame,"DET"));
			}
			
		});
		add(det);
	}
	

}
