package ui.config;

import java.util.List;

import org.dom4j.Element;

public class TableConfig extends ComponentConfig{

	private String[] columnName;
	
	public TableConfig(Element tablepane){
		this.w = Integer.parseInt(tablepane.attributeValue("width"));
		this.h = Integer.parseInt(tablepane.attributeValue("height"));
		this.x = Integer.parseInt(tablepane.attributeValue("x"));
		this.y = Integer.parseInt(tablepane.attributeValue("y"));
		this.initColumnName(tablepane.element("columnnames"));
	}
	
	private void initColumnName(Element column){
		@SuppressWarnings("unchecked")
		List<Element> names = column.elements("columnname");
		this.columnName = new String[names.size()];
		for(int i=0; i<names.size(); ++i){
			this.columnName[i] = names.get(i).attributeValue("name");
		}
	}
	
	public String[] getColumnName() {
		return this.columnName;
	}

}
