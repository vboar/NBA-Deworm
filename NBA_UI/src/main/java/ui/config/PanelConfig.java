package ui.config;

import java.awt.Image;

import javax.swing.ImageIcon;

import org.dom4j.Element;

public class PanelConfig extends ComponentConfig{

	/**
	 * 类名
	 */
	private String className;
	
	/**
	 * 按钮配置
	 */
	private Element buttons;
	
	/**
	 * 标签配置
	 */
	private Element labels;
	
	/**
	 * 输入框配置
	 */
	private Element textFields;
	
	/**
	 * 复选框配置
	 */
	private Element comboboxes;
	
	private Element tablepane;
	
	private Element subpane;
	
	private Element tables;
	
	private Element tree;
	
	private Element datepicker;
	
	private Element textarea;
	
	private Element checkbox;
		
	private Element tabbedpanes;
	
	private Element tab;
	
	private Image bg;
	
	private Image bg1;
	
	
	public PanelConfig(Element panel) {
		this.className = panel.attributeValue("className");
		// 获取按钮属性
		this.buttons = panel.element("buttons");
		// 获取标签属性
		this.labels = panel.element("labels");
		// 获取输入框属性
		this.textFields = panel.element("textfields");
		// 获取复选框属性
		this.comboboxes = panel.element("comboboxes");
		this.w = Integer.parseInt(panel.attributeValue("width"));
		this.h = Integer.parseInt(panel.attributeValue("height"));
		this.x = Integer.parseInt(panel.attributeValue("x"));
		this.y = Integer.parseInt(panel.attributeValue("y"));
		this.bg = new ImageIcon(panel.attributeValue("bg")).getImage();
		if(panel.attributeValue("bg1")!=null)
			this.bg1 = new ImageIcon(panel.attributeValue("bg1")).getImage();
		this.tablepane = panel.element("tablepane");
		this.subpane = panel.element("subpane");
		this.tree = panel.element("tree");
		this.datepicker = panel.element("datepicker");
		this.tables = panel.element("tables");
		this.textarea = panel.element("textarea");
		this.checkbox = panel.element("checkbox");
		this.tabbedpanes = panel.element("tabbedpane");
		this.tab = panel.element("tab");
	}	


	public Element getButtons() {
		return buttons;
	}

	public Element getLabels() {
		return labels;
	}

	public Element getTextFields() {
		return textFields;
	}
	
	public Element getComboboxes() {
		return comboboxes;
	}

	public String getClassName() {
		return className;
	}

	public Image getBg() {
		return bg;
	}

	public Element getTablepane() {
		return tablepane;
	}

	public Element getTree() {
		return tree;
	}

	public Element getDatepicker() {
		return datepicker;
	}

	public Element getTables() {
		return tables;
	}

	public Element getTextarea() {
		return textarea;
	}

	public Element getCheckbox() {
		return checkbox;
	}

	public Image getBg1() {
		return bg1;
	}

	public Element getTabbedpanes() {
		return tabbedpanes;
	}


	public Element getSubpane() {
		return subpane;
	}
	
	public Element getTab(){
		return tab;
	}
	

}
