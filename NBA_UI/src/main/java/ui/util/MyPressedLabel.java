package ui.util;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.dom4j.Element;

public class MyPressedLabel extends JLabel {

	
	
public MyPressedLabel(Element ele) {
		this.setName(ele.attributeValue("text"));
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

		this.setCursor(new Cursor(Cursor.HAND_CURSOR));

//		ImageIcon icon = new ImageIcon(path);
//		icon.setImage(icon.getImage().getScaledInstance(Integer.parseInt(ele.attributeValue("w")),
//				Integer.parseInt(ele.attributeValue("h")), Image.SCALE_DEFAULT));
//		
//		this.setIcon(icon);
		
}

public MyPressedLabel(Element ele,String path) {
	
	//this.setText(ele.attributeValue("text"));
	this.setSize(Integer.parseInt(ele.attributeValue("w")),
			Integer.parseInt(ele.attributeValue("h")));
	this.setLocation(Integer.parseInt(ele.attributeValue("x")),
			Integer.parseInt(ele.attributeValue("y")));
	ImageIcon icon = new ImageIcon(path);
	icon.setImage(icon.getImage().getScaledInstance(Integer.parseInt(ele.attributeValue("w")),
			Integer.parseInt(ele.attributeValue("h")), Image.SCALE_DEFAULT));
	
	this.setIcon(icon);
	this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	
}
}
