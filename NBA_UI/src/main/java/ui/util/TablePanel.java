/**
 * 表格面板
 * @author wang
 * @date 2015/4/25
 */
package ui.util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ui.config.TableConfig;

import java.awt.*;

@SuppressWarnings("serial")
public abstract class TablePanel extends JPanel{

	protected String[] columnNames;
	
	protected Object[][] data;

	protected DefaultTableModel dtm;
	
	protected MyTable table;
	
	protected JScrollPane rollpane;
	
	protected TableConfig cfg;
	
	public TablePanel(TableConfig cfg){		
		this.cfg = cfg;
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setOpaque(false);
	}
	
	public TablePanel(){}
	
	protected abstract void initTable();
	
	protected void initComponent() {
		//创建滚动条面板
		this.rollpane = new JScrollPane(this.table);
		this.rollpane.setPreferredSize(new Dimension(cfg.getW(),cfg.getH()-5));
		this.rollpane.setOpaque(false);
		this.add(this.rollpane);
	}

	public boolean isSelected(){
		if(this.table.getSelectedRow()!=-1)		
			return true;
		else	
			return false;
	}

	public MyTable getTable() {
		return table;
	}

	public DefaultTableModel getDtm() {
		return dtm;
	}

	public void setRowHeaderWidth(int width){
		//this.rollpane.setRowHeaderWidth(width);
	}
	
	public void deleteRow(){
		if(this.isSelected()){
			this.dtm.removeRow(this.table.getSelectedRow());
		}
	}
	
	public void removeRow(int num){
		this.dtm.removeRow(num);
	}
	
	public void setcolumnName(String ss[]){
		
		dtm.setColumnIdentifiers(ss);
		columnNames = ss;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	//public RowTableScrollPane getRollpane() {
		//return rollpane;
	//}
	
	
}
