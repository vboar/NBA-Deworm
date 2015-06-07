/**
 * 自定义按钮类
 * @author JaneLDQ
 * @date 2014/11/13
 */
package ui.util;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.dom4j.Element;

@SuppressWarnings("serial")
public class MyButton extends JButton{

	public MyButton(String text, int w,int h, int x,int y){
		this.setText(text);
		this.setSize(w, h);
		this.setLocation(x, y);
		this.setFocusPainted(false);
	}
	
	public MyButton(Element ele){
		this.setText(ele.attributeValue("text"));
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));
		if(ele.attributeValue("path")!=null){
			this.setIcon(new ImageIcon(ele.attributeValue("path")));
			this.setBorderPainted(false);
			this.setContentAreaFilled(false);
		}
		if(ele.attributeValue("pathroll")!=null){
			this.setRolloverIcon(new ImageIcon(ele.attributeValue("pathroll")));
		}
		this.setFocusPainted(false);
	}
	
	public MyButton(String text, Element ele){
		this.setText(text);
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));
		this.setFocusPainted(false);
	}
	
	public MyButton(Element ele,boolean threePic){
		//System.out.println(ele.attributeValue("path"));
		String first =ele.attributeValue("path").split("\\.")[0];
		String second = ele.attributeValue("path").split("\\.")[1];
		
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));
		if(ele.attributeValue("path")!=null){
			this.setIcon(new ImageIcon(ele.attributeValue("path")));
			this.setBorderPainted(false);
			this.setContentAreaFilled(false);
			
			this.setRolloverIcon(new ImageIcon(first+"_point."+second));
			this.setPressedIcon(new ImageIcon(first+"_click."+second));
		
		}
		if(ele.attributeValue("pathroll")!=null){
			this.setRolloverIcon(new ImageIcon(ele.attributeValue("pathroll")));
		}
		this.setFocusPainted(false);
	}
}
