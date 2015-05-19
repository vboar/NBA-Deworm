/**
 * 行号表格
 * @author JaneLDQ
 * @date 2014/11/24
 */
package ui.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class RowTable extends JTable {

	public RowTable(JTable table) {
		setModel(new RowHeaderModel(table.getModel()));
		setRowHeight(table.getRowHeight());
		setEnabled(false);
		setPreferredScrollableViewportSize(new Dimension(40,0));
		this.setDefaultRenderer(Object.class, new RowHeaderRenderer(table,this));
	}

	public void setTableModel(JTable table) {
		this.setModel(new RowHeaderModel(table.getModel()));
	}

	/**
	 * 行号表格渲染器
	 * @author JanelDQ
	 * @date 2014/11/25
	 */
	private class RowHeaderRenderer extends JLabel implements TableCellRenderer,ListSelectionListener{
		
		private JTable table;
		
		private RowTable rowtable;
		
		public RowHeaderRenderer(JTable table, RowTable rowtable){
			this.table = table;  
	        this.rowtable = rowtable;  
	        ListSelectionModel listModel=table.getSelectionModel();  
	        listModel.addListSelectionListener(this);
		}
		
			@Override
			public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
				JTableHeader header = table.getTableHeader(); 
				setHorizontalAlignment(CENTER);//让text居中显示 
				this.setOpaque(true);
				this.setBackground(header.getBackground());
	        	if (isSelect(row)){
	        		setForeground(new Color(230,230,238));  
	        		setBackground(Color.DARK_GRAY);  
	        	}else{  
	        		setForeground(header.getForeground()); 
	        	}
	        	this.setText("" + (row + 1));
	        	return this;
			}
		
			private boolean isSelect(int row){  
	        	int[] sel = table.getSelectedRows();  
	       	 	for ( int i=0; i<sel.length; i++ )  
	           		if (sel[i] == row )   
	                	return true;  
	        	return false;  
	   		}

			@Override
			public void valueChanged(ListSelectionEvent e) {
				this.rowtable.repaint(); 
			}
	}

	/**
	 * 行号表格模型
	 * @author JanelDQ
	 * @date 2014/11/25
	 */
	private class RowHeaderModel extends AbstractTableModel {

		private TableModel model;

		public RowHeaderModel(TableModel model) {
			this.model = model;
		}
		
		public int getRowCount() {
			return model.getRowCount();
		}

		public int getColumnCount() {
			return 1;
		}

		public Object getValueAt(int row, int column) {
			return row;
		}
	}
}
