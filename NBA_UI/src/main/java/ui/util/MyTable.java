/**
 * 自定义表格类
 * @author JaneLDQ
 * @date 2014/11/24
 */
package ui.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class MyTable extends JTable {

	private RowSorter<TableModel> sorter;
	
	private int padding = 20;
	
	final JTableHeader header = getTableHeader();
	
	public MyTable(TableModel dtm, int containerW) {
		super(dtm);
		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setForeground(Color.WHITE);
		this.getTableHeader().setBackground(new Color(0xB0,0xC4,0xDE));
		//this.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(164,174,185)));
		
		this.getTableHeader().setLayout(null);

		//this.setShowGrid(false);
		this.sorter = new TableRowSorter<TableModel>(dtm);
			
			
		
		((DefaultTableCellRenderer)getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		this.setRowSorter(sorter);
		this.setDefaultRenderer(Object.class, new MyTableCellRenderer());
		this.setDefaultRenderer(Double.class, new MyTableCellRenderer());
		   
		
		this.setBackground(new Color(240,240,255));
		FrameUtil.setTableColumnWidth(this, containerW, padding);
		
		  
		//表头增加监听  www.2cto.com
	     header.addMouseListener (new MouseAdapter() {  
	             public void mouseReleased (MouseEvent e) {  
	                 if (! e.isShiftDown())  
	                     clearSelection();  
	                 //获取点击的列索引  
	                 int pick = header.columnAtPoint(e.getPoint());  
	                 //设置选择模型  
	                 //addColumnSelectionInterval (pick, pick);                  
	             }  
	         });  
	}
	
	public void setUnvisibleColumn(int column){
		TableColumn tc = this.getTableHeader().getColumnModel().getColumn(column);
        tc.setMaxWidth(0);
        tc.setPreferredWidth(0);
        tc.setWidth(0);
        tc.setMinWidth(0);
        this.updateUI();
        this.getTableHeader().getColumnModel().getColumn(column).setMaxWidth(0);
        this.getTableHeader().getColumnModel().getColumn(column).setMinWidth(0);
	}
	
	//根据内容长度自动调整列宽
	public void FitTableColumns(JTable myTable)
	{
	    JTableHeader header = myTable.getTableHeader();
	    int rowCount = myTable.getRowCount();
	    Enumeration columns = myTable.getColumnModel().getColumns();
	    while(columns.hasMoreElements())
	    {
	        TableColumn column = (TableColumn)columns.nextElement();
	        int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
	        int width = (int)myTable.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();                
	        for(int row = 0; row<rowCount; row++)
	         {
	             int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
	             myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
	             width = Math.max(width, preferedWidth);
	         }
	         header.setResizingColumn(column); 
	         column.setWidth(width+myTable.getIntercellSpacing().width+25);
	     }
	}
	
	private class MyTableCellRenderer extends DefaultTableCellRenderer{
		
		public Component getTableCellRendererComponent(JTable table,
				Object value,boolean isSelected, boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
			if ((row % 2) != 0) {
				setBackground(new Color(246,248,250));
			} else {
				setBackground(Color.white);
			}
			if (isSelected) {
				setBackground(new Color(200,210,230));
				setForeground(new Color(40,40,40));
			}
			if(table.getValueAt(row, 0)!=null&&table.getValueAt(row, 0).toString().equals("true")){
				setForeground(new Color(247,171,0));
			}else{
				setForeground(new Color(40,40,40));
			}
			setHorizontalAlignment(JLabel.CENTER);
		
			
			return this;
		}
		
	}

}
