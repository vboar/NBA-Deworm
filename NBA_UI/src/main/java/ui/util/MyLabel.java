/**
 * 自定义标签类
 * @author JaneLDQ
 * @date 2014/11/13
 */
package ui.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.dom4j.Element;

@SuppressWarnings("serial")
public class MyLabel extends JLabel {

	public Element ele;
	ImageIcon icon;

	public MyLabel(String text, int w, int h, int x, int y) {
		this.setText(text);
		this.setSize(w, h);
		this.setLocation(x, y);
	}

	public MyLabel(Element ele) {
		this.ele = ele;
		this.setText(ele.attributeValue("text"));
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));
		if (ele.attributeValue("path") != null) {
			this.setIcon(new ImageIcon(ele.attributeValue("path")));
		}
		if (ele.attributeValue("font") != null) {
			this.setFont(new Font(ele.attributeValue("font"), Font.PLAIN,
					Integer.parseInt(ele.attributeValue("fontsize"))));
		}
		if (ele.attributeValue("color") != null) {
			int r = Integer.parseInt(ele.attributeValue("r"));
			int g = Integer.parseInt(ele.attributeValue("g"));
			int b = Integer.parseInt(ele.attributeValue("b"));
			this.setForeground(new Color(r, g, b));
		}
	}

	public MyLabel(String text, Element ele) {
		this.ele = ele;
		this.setText(text);
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));
		if (ele.attributeValue("path") != null) {
			this.setIcon(new ImageIcon(ele.attributeValue("path")));
		}
		if (ele.attributeValue("font") != null) {
			this.setFont(new Font(ele.attributeValue("font"), Font.PLAIN,
					Integer.parseInt(ele.attributeValue("fontsize"))));
		}
		if (ele.attributeValue("color") != null) {
			int r = Integer.parseInt(ele.attributeValue("r"));
			int g = Integer.parseInt(ele.attributeValue("g"));
			int b = Integer.parseInt(ele.attributeValue("b"));
			this.setForeground(new Color(r, g, b));
		}
	}

	public MyLabel(Element ele, String path, int x) {
		this.ele = ele;
		File file = new File(path);
		if (!file.exists()) {
			if (path.contains("action")) {
				path = "img/player/actionunknown.png";
			} else if (path.contains("portrait")) {
				path = "img/player/portraitunknown.png";
			}
		}
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));
		icon = new ImageIcon(path);

		icon.setImage(icon.getImage().getScaledInstance(
				Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")), Image.SCALE_DEFAULT));

		this.setIcon(icon);

	}
	
	public MyLabel(Element ele,int width) {
		this.ele = ele;
		this.setOpaque(true);
		//this.setText(ele.attributeValue("text"));
		this.setSize(width,
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));		
		if (ele.attributeValue("color") != null) {
			int r = Integer.parseInt(ele.attributeValue("r"));
			int g = Integer.parseInt(ele.attributeValue("g"));
			int b = Integer.parseInt(ele.attributeValue("b"));
			this.setBackground(new Color(r, g, b));
		}
	}

	public void setImageSize(int w, int h) {
		setSize(w, h);
		icon.setImage(icon.getImage().getScaledInstance(w, h,
				Image.SCALE_DEFAULT));
	}
}
