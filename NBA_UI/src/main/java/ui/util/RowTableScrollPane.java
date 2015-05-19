/**
 * 行号表格滚动面板
 * @author JaneLDQ
 * @date 2014/11/25
 */
package ui.util;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

@SuppressWarnings("serial")
public class RowTableScrollPane extends JScrollPane implements PropertyChangeListener, TableModelListener{

	protected RowTable rowHeader = null;
	
	protected JTable table = null;
	
	public RowTableScrollPane(JTable table){
		this.table = table;
		this.table.addPropertyChangeListener(this);
		this.table.getModel().addTableModelListener(this);
		this.rowHeader = new RowTable(this.table);
		this.setViewportView(this.table);
		this.setRowHeaderView(this.rowHeader);
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		if(this.rowHeader!=null){
			this.rowHeader.setTableModel(this.table);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(this.rowHeader!=null){
			this.rowHeader.setTableModel(this.table);
		}
	}

	public void setRowHeaderWidth(int width) {
		this.rowHeader.setPreferredScrollableViewportSize(new Dimension(width,0));
	}

}
