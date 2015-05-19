/**
 * 自定义复选框类
 * @author JaneLDQ
 * @date 2014/11/13
 */
package ui.util;

import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.dom4j.Element;

@SuppressWarnings({ "serial", "rawtypes" })
public class MyComboBox extends JComboBox {

	private int popupWidth;

	@SuppressWarnings("unchecked")
	public MyComboBox(Element ele) {
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));
		List<Element> strs = ele.elements("string");
		for (int i = 0; i < strs.size(); ++i) {
			this.addItem(strs.get(i).attributeValue("str"));
		}
		if(this.getItemCount()>0){
			this.setSelectedIndex(0);
		}
	}

	@SuppressWarnings("unchecked")
	public MyComboBox(DefaultComboBoxModel model) {
		super(model);
	}

	public Dimension getPopupSize() {
		Dimension size = getSize();
		// reset size.
		if (popupWidth < 1) {
			popupWidth = size.width;
		}
		return new Dimension(popupWidth, size.height);
	}

}
