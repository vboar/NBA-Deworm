package ui.util;

import java.awt.Cursor;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.dom4j.Element;

public class MyPressedLabel extends JLabel {

	
	
public MyPressedLabel(Element ele) {
		
		this.setText(ele.attributeValue("text"));
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));
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
