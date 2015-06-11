package ui.player.index;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyLabel;
import ui.util.MyTextField;
import vo.PlayerInfoVO;

public class PlayerIndex extends JPanel {
	private PanelConfig pcfg;

	private HomeUI frame;
	// private PlayerService ps;
	private MyTextField name;
	private JScrollPane jsp;
	private JLayeredPane layerPane;
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

	private String show = "a";
	private String path = null;

	public PlayerIndex(HomeUI frame) {
		this.frame = frame;
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		/*
		 * try { ps = ServiceFactoryImpl.getInstance().getPlayerService(); }
		 * catch (RemoteException e) { // TODO 自动生成的 catch 块
		 * e.printStackTrace(); }
		 */
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		initComponent();
	}

	public void initComponent() {
		initLabels();
		initTextFields();
		try {
			initScrollPane();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initLabels() {
		indexbg = new MyLabel(pcfg.getLabels().element("indexbg"));
		search = new MyLabel(pcfg.getLabels().element("search"));
		a = new MyLabel(pcfg.getLabels().element("a"));
		String[] temp = pcfg.getLabels().element("a").attributeValue("path")
				.split("\\.");

		path = temp[0].substring(0, temp[0].length() - 1);
		// System.out.println(path);
		// a.addMouseListener(new MouseAdapter() {
		// String[] temp = pcfg.getLabels().element("a")
		// .attributeValue("path").split("\\.");
		//
		// String path = temp[0];
		//
		// String fix = temp[1];
		// List<PlayerInfoVO> infolist = new ArrayList<PlayerInfoVO>();
		//
		// @Override
		// public void mouseClicked(MouseEvent arg0) {
		// a.setIcon(new ImageIcon(path + "_click." + fix));
		//
		// /*
		// * try { infolist = ps.getPlayerInfoByNameInitial("A"); } catch
		// * (RemoteException e) { // TODO 自动生成的 catch 块
		// * e.printStackTrace(); }
		// */
		// // show(infolist);
		// // show("a");
		// }

		// @Override
		// public void mouseEntered(MouseEvent arg0) {
		// a.setIcon(new ImageIcon(path + "_click." + fix));
		//
		// }
		//
		// @Override
		// public void mouseExited(MouseEvent arg0) {
		// if (!show.equals("A"))
		// a.setIcon(new ImageIcon(path + "." + fix));
		// }
		//
		// @Override
		// public void mousePressed(MouseEvent arg0) {
		// a.setIcon(new ImageIcon(path + "_click." + fix));
		// }
		//
		// });
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

		setImage(a, "a");
		setImage(b, "b");
		setImage(c, "c");
		setImage(d, "d");
		setImage(e, "e");
		setImage(f, "f");
		setImage(g, "g");
		setImage(h, "h");
		setImage(i, "i");
		setImage(j, "j");
		setImage(k, "k");
		setImage(l, "l");
		setImage(m, "m");
		setImage(n, "n");
		setImage(o, "o");
		setImage(p, "p");
		setImage(q, "q");
		setImage(r, "r");
		setImage(s, "s");
		setImage(t, "t");
		setImage(u, "u");
		setImage(v, "v");
		setImage(w, "w");
		setImage(x, "x");
		setImage(y, "y");
		setImage(z, "z");

		// for(int i= 0;i<13;i++){
		// MyLabel left = new MyLabel(pcfg.getLabels().element("left"));
		// MyLabel middle1 = new MyLabel(pcfg.getLabels().element("middle1"));
		// MyLabel middle2 = new MyLabel(pcfg.getLabels().element("middle2"));
		// MyLabel right = new MyLabel(pcfg.getLabels().element("right"));
		//
		// add(left);
		// left.setLocation(left.getX(),left.getY()+i*35);
		// middle1.setLocation(middle1.getX(), middle1.getY()+i*35);
		// middle2.setLocation(left.getX(), middle2.getY()+i*35);
		// right.setLocation(right.getX(), right.getY()+i*35);
		// }

	}

	private void initTextFields() {
		name = new MyTextField(pcfg.getTextFields().element("name"));
		name.setBorder(new EmptyBorder(0, 0, 0, 0));
		name.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text = name.getText();
				text = text.replace(" ", "");
				if (text != null && !text.equals("") && text.length() > 1) {

				}
			}

		});
		add(name);
	}

	private void show(List<PlayerInfoVO> infolist) {
		int x = 0;
		int y = 0;

		for (int i = 0; i < infolist.size(); i++) {
			JLabel temp = new JLabel();

		}
	}

	private void setImage(MyLabel label, String word) {

		label.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// System.out.println(label.getName());
				if (label.getName() == show) {
					label.setIcon(new ImageIcon(path + word + "_point.png"));
				} else {
					label.setIcon(new ImageIcon(path + word + ".png"));

				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				label.setIcon(new ImageIcon(path + word + "_point.png"));

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				switch (show) {
				case "a": {
					a.setIcon(new ImageIcon(path + "a.png"));
					break;
				}
				case "b": {
					b.setIcon(new ImageIcon(path + "b.png"));
					break;
				}
				case "c": {
					c.setIcon(new ImageIcon(path + "c.png"));
					break;
				}
				case "d": {
					d.setIcon(new ImageIcon(path + "d.png"));
					break;
				}
				case "e": {
					e.setIcon(new ImageIcon(path + "e.png"));
					break;
				}
				case "f": {
					f.setIcon(new ImageIcon(path + "f.png"));
					break;
				}
				case "g": {
					g.setIcon(new ImageIcon(path + "g.png"));
					break;
				}
				case "h": {
					h.setIcon(new ImageIcon(path + "h.png"));
					break;
				}
				case "i": {
					i.setIcon(new ImageIcon(path + "i.png"));
					break;
				}
				case "j": {
					j.setIcon(new ImageIcon(path + "j.png"));
					break;
				}
				case "k": {
					k.setIcon(new ImageIcon(path + "k.png"));
					break;
				}
				case "l": {
					l.setIcon(new ImageIcon(path + "l.png"));
					break;
				}
				case "m": {
					m.setIcon(new ImageIcon(path + "m.png"));
					break;
				}
				case "n": {
					n.setIcon(new ImageIcon(path + "n.png"));
					break;
				}
				case "o": {
					o.setIcon(new ImageIcon(path + "o.png"));
					break;
				}
				case "p": {
					p.setIcon(new ImageIcon(path + "p.png"));
					break;
				}
				case "q": {
					q.setIcon(new ImageIcon(path + "q.png"));
					break;
				}
				case "r": {
					r.setIcon(new ImageIcon(path + "r.png"));
					break;
				}
				case "s": {
					s.setIcon(new ImageIcon(path + "s.png"));
					break;
				}
				case "t": {
					t.setIcon(new ImageIcon(path + "t.png"));
					break;
				}
				case "u": {
					u.setIcon(new ImageIcon(path + "u.png"));
					break;
				}
				case "v": {
					v.setIcon(new ImageIcon(path + "v.png"));
					break;
				}
				case "w": {
					w.setIcon(new ImageIcon(path + "w.png"));
					break;
				}
				case "x": {
					x.setIcon(new ImageIcon(path + "x.png"));
					break;
				}
				case "y": {
					y.setIcon(new ImageIcon(path + "y.png"));
					break;
				}
				case "z": {
					z.setIcon(new ImageIcon(path + "z.png"));
					break;
				}
				}
				label.setIcon(new ImageIcon(path + word + "_click.png"));

				if (show != label.getName()) {
					System.out.println("382: " + label.getName());
					try {
						setIndex(label.getName());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				show = label.getName();

			}
		});

	}

	private void initScrollPane() throws RemoteException {
		jsp = new JScrollPane();
		jsp.setSize(940, 461);
		jsp.setLocation(0, 50);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		layerPane = new JLayeredPane();
		layerPane.setPreferredSize(new Dimension(940, 1020));
		layerPane.setLayout(null);
		setIndex("A");
		jsp.setViewportView(layerPane);
		add(jsp);
	}

	public static void main(String[] args) throws RemoteException {
		// List<PlayerInfoVO> volist
		// =ServiceFactoryImpl.getInstance().getPlayerService().getAllPlayerInfo();
		// for(int i = 0;i<volist.size();i++){
		// System.out.println(volist.get(i).name);
		// }
		// for(int i = 0;i<26;i++){
		// System.out.println("");
		// }
		//
	}

	private void setIndex(String ini) throws RemoteException {
		jsp.remove(layerPane);
		layerPane = new JLayeredPane();
		layerPane.setPreferredSize(new Dimension(940, 1130));
		layerPane.setLayout(null);		
		//System.out.println("418: " + ini + "-------------");
		List<String> volist = ServiceFactoryImpl.getInstance()
				.getPlayerService()
				.getNameByNameInitial(ini.toUpperCase());
//		for (int i = 0; i < volist.size(); i++) {
//			System.out.println("i:"+i+" "+volist.get(i));
//		}

		for (int i = 0; i < volist.size(); i += 4) {
			MyLabel left;
			MyLabel middle1;
			MyLabel middle2;
			MyLabel right;
			if (i < volist.size()) {
				left = new MyLabel(volist.get(i), pcfg.getLabels()
						.element("left"));
				layerPane.add(left);
				setAction(left);
				left.setLocation(left.getX(), left.getY() + i / 4 * 35);
			}
			if (i < volist.size() - 1) {
				middle1 = new MyLabel(volist.get(i + 1), pcfg.getLabels()
						.element("middle1"));
				layerPane.add(middle1);
				setAction(middle1);
				middle1.setLocation(middle1.getX(), middle1.getY() + i / 4 * 35);
			}
			if (i < volist.size() - 2) {
				middle2 = new MyLabel(volist.get(i + 2), pcfg.getLabels()
						.element("middle2"));
				layerPane.add(middle2);
				setAction(middle2);
				middle2.setLocation(middle2.getX(), middle2.getY() + i / 4 * 35);
			}
			if (i < volist.size() - 3) {
				right = new MyLabel(volist.get(i + 3), pcfg.getLabels()
						.element("right"));
				setAction(right);
				layerPane.add(right);
				right.setLocation(right.getX(), right.getY() + i / 4 * 35);
			}
		}
		layerPane.repaint();
		jsp.setViewportView(layerPane);
		jsp.repaint();
	}
	
	private void setAction(MyLabel lb){
		String name = lb.getText();
		lb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lb.addMouseListener(new MouseListener() {
			
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
				frame.motherPanel.playerPanel.indexpanel.setVisible(false);
				frame.motherPanel.playerPanel.playerInfoPane.changeData(name);
				frame.motherPanel.playerPanel.playerInfoPane.setVisible(true);
				
			}
		});
	}

}
