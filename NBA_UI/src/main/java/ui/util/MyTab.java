package ui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.dom4j.Element;

public class MyTab extends JPanel{

	public Element ele;
	JLabel tab1;
	JLabel tab2;
	String path1;
	String path2;
	String fix;
	
	public MyTab(Element ele){
		this.ele = ele;
		tab1 = new JLabel();
		tab2 = new JLabel();
		
		//tab1.setText(ele.attributeValue("text1"));
		//tab2.setText(ele.attributeValue("text2"));
		
		tab1.setSize(Integer.parseInt(ele.attributeValue("w1")), Integer.parseInt(ele.attributeValue("h1")));
		tab2.setSize(Integer.parseInt(ele.attributeValue("w2")), Integer.parseInt(ele.attributeValue("h2")));

		tab1.setLocation(0,0);
		tab2.setLocation(66,0);

		if(ele.attributeValue("path1")!=null){	
			path1 = ele.attributeValue("path1").split("\\.")[0];
			fix = ele.attributeValue("path1").split("\\.")[1];
			tab1.setIcon(new ImageIcon(path1+"_click."+fix));
		}
		
		if(ele.attributeValue("path2")!=null){
			tab2.setIcon(new ImageIcon(ele.attributeValue("path2")));
			path2 = ele.attributeValue("path2").split("\\.")[0];
		}
		
		tab1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				tab1.setIcon(new ImageIcon(path1+"_click."+fix));
				tab2.setIcon(new ImageIcon(path2+"."+fix));
			}
			
		});
		
		tab2.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				tab2.setIcon(new ImageIcon(path2+"_click."+fix));
				tab1.setIcon(new ImageIcon(path1+"."+fix));
			}	
		});
		
		this.setLayout(null);
		this.setSize(134, 30);
		this.setLocation(Integer.parseInt(ele.attributeValue("x")), Integer.parseInt(ele.attributeValue("y")));
		add(tab1);
		add(tab2);
		this.repaint();
	}
}
