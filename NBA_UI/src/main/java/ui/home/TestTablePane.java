package ui.home;

import java.awt.Dimension;

import ui.config.TableConfig;
import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.MyTableModel;
import ui.util.TablePanel;

@SuppressWarnings("serial")
public class TestTablePane extends TablePanel{
	 private static int COLUMN_NUM = 3;
		
	 private Object[][] list;
	
	public TestTablePane(TableConfig cfg,Object[][] list) {
		
        super(cfg);
        this.list = list;
        this.initTable();
        this.initComponent();
    }

	@Override
	protected void initTable() {
		 this.columnNames = cfg.getColumnName();
	        this.initData(list);
	        this.dtm = new MyTableModel(data, columnNames);	       
	        this.table = new MyTable(this.dtm, this.getWidth());
	        
	        //this.table.addMouseListener(showDataInfo());
	        this.updateWidth();	        
	}
	
	 private void updateWidth() {
			FrameUtil.setTableColumnWidth(table, this.getWidth(), 10);
//			this.table.getColumnModel().getColumn(0).setPreferredWidth(160);
//			this.table.getColumnModel().getColumn(1).setPreferredWidth(170);
//			this.table.getColumnModel().getColumn(2).setPreferredWidth(420);
			this.table.getTableHeader().setPreferredSize(new Dimension(0, 50));
			this.table.setRowHeight(50);
			this.updateUI();
		}	  
	 
	 private void initData(Object[][] list) {
	        int size;
	        if(list == null) size = 0;
	        else size = list.length;
	        
	        this.data = new Object[size][COLUMN_NUM];
	        for(int i=size-1; i>=0; --i){
	        	
	            this.createRow(data[i], list[i]);
	        }
	    }
	 
	 private void createRow(Object[] row, Object[] vo) {
	    	row[0] = vo[0];
	    	row[1] = vo[1];

	    }
	 
//	 public void updateData(Object[][] data,String name){
//		    this.setcolumnName(name);
//	    	for(int row=0;row<table.getRowCount();row++){
//	    		for(int column = 0;column<table.getColumnCount();column++){
//	    			table.setValueAt(data[row][column], row, column);
//	    		}
//	    	}
//	    	this.repaint();
//	    }
//	 
//	


}
