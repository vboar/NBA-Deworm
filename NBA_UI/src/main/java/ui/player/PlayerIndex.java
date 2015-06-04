package ui.player;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyLabel;
import ui.util.MyTextField;

public class PlayerIndex extends JPanel{
	private PanelConfig pcfg;
	
	private MyTextField name;
	private MyLabel indexbg;
	private MyLabel search;
	private MyLabel a;
	private MyLabel b;
	private MyLabel c;
	private MyLabel d;
	private MyLabel e;
	private MyLabel f;
	private MyLabel g;
	private MyLabel h;
	private MyLabel i;
	private MyLabel j;
	private MyLabel k;
	private MyLabel l;
	private MyLabel m; 
	private MyLabel n;
	private MyLabel o;
	private MyLabel p;
	private MyLabel q;
	private MyLabel r;
	private MyLabel s;
	private MyLabel t;
	private MyLabel u;
	private MyLabel v;
	private MyLabel w;
	private MyLabel x;
	private MyLabel y;
	private MyLabel z;
	
	public PlayerIndex(HomeUI frame){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		initComponent();
	}
	
	public void initComponent(){
		initLabels();
		initTextFields();
	}
	
	private void initLabels(){
		indexbg = new MyLabel(pcfg.getLabels().element("indexbg"));
		search = new MyLabel(pcfg.getLabels().element("search"));
		a = new MyLabel(pcfg.getLabels().element("a"));
		b = new MyLabel(pcfg.getLabels().element("b"));
		c = new MyLabel(pcfg.getLabels().element("c"));
		d = new MyLabel(pcfg.getLabels().element("d"));
		e = new MyLabel(pcfg.getLabels().element("e"));
		f = new MyLabel(pcfg.getLabels().element("f"));
		g = new MyLabel(pcfg.getLabels().element("g"));
		h = new MyLabel(pcfg.getLabels().element("h"));
		i = new MyLabel(pcfg.getLabels().element("i"));
		j = new MyLabel(pcfg.getLabels().element("j"));
		k = new MyLabel(pcfg.getLabels().element("k"));
		l = new MyLabel(pcfg.getLabels().element("l"));
		m = new MyLabel(pcfg.getLabels().element("m"));
		n = new MyLabel(pcfg.getLabels().element("n"));
		o = new MyLabel(pcfg.getLabels().element("o"));
		p = new MyLabel(pcfg.getLabels().element("p"));
		q = new MyLabel(pcfg.getLabels().element("q"));
		r = new MyLabel(pcfg.getLabels().element("r"));
		s = new MyLabel(pcfg.getLabels().element("s"));
		t = new MyLabel(pcfg.getLabels().element("t"));
		u = new MyLabel(pcfg.getLabels().element("u"));
		v = new MyLabel(pcfg.getLabels().element("v"));
		w = new MyLabel(pcfg.getLabels().element("w"));
		x = new MyLabel(pcfg.getLabels().element("x"));
		y = new MyLabel(pcfg.getLabels().element("y"));
		z = new MyLabel(pcfg.getLabels().element("z"));
		
		
		add(a);
		add(b);
		add(c);
		add(d);
		add(e);
		add(f);
		add(g);
		add(h);
		add(i);
		add(j);
		add(k);
		add(l);
		add(m);
		add(n);
		add(o);
		add(p);
		add(q);
		add(r);
		add(s);
		add(t);
		add(u);
		add(v);
		add(w);
		add(x);
		add(y);
		add(z);
		add(search);
		add(indexbg);
		
	}
	
	private void initTextFields(){
		name = new MyTextField(pcfg.getTextFields().element("name"));
		name.setBorder(new EmptyBorder(0,0,0,0));
		add(name);
	}
}
