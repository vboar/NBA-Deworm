package ui.player.stat;

import java.awt.Dimension;

import javax.swing.JPanel;

import ui.config.TableConfig;
import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.MyTableModel;
import ui.util.TablePanel;

public class PlayerAllTablePane extends TablePanel {

	 private static int COLUMN_NUM = 8;
		
	 private Object[][] list;
	
	public PlayerAllTablePane(TableConfig cfg,Object[][] list) {
		
       super(cfg);
       this.list = list;
       this.initTable();
       this.initComponent();
   }

	@Override
	protected void initTable() {
		 this.columnNames = cfg.getColumnName();
	        this.initData(list);
	        this.dtm = new MyTableModel(data, columnNames){
	        	  
	            @Override
	            public Class<?> getColumnClass(int columnIndex) { 
	            	if(columnIndex >= 2)
	                return Double.class;
	            	else return Object.class;
	            }
	        	
	        };       
	        this.table = new MyTable(this.dtm, this.getWidth());
	        
	        //this.table.addMouseListener(showDataInfo());
	        this.updateWidth();	        
	}
	
	 private void updateWidth() {
			FrameUtil.setTableColumnWidth(table, this.getWidth(), 10);
//			this.table.getColumnModel().getColumn(0).setPreferredWidth(160);
//			this.table.getColumnModel().getColumn(1).setPreferredWidth(170);
//			this.table.getColumnModel().getColumn(2).setPreferredWidth(420);
			//this.table.getTableHeader().setPreferredSize(new Dimension(0, 50));
			//this.table.setRowHeight(50);
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
	    	row[2] = vo[2];
	    	row[3] = vo[3];
	    	row[4] = vo[4];
	    	row[5] = vo[5];
	    	row[6] = vo[6];
	    	row[7] = vo[7];
	    	

	    }
	 
	  public void setData(Object[][] list){
		   int size;
	        if(list == null) size = 0;
	        else size = list.length;
	        while(dtm.getRowCount()>size){
	        	//System.out.println(x++);
	        	this.dtm.removeRow(size-1);
	        }
	       
	        while(this.dtm.getRowCount()<size){
	        	Object[] rowData = {0,0,0,0};
	        	this.dtm.addRow(rowData);
	        	
	        }
	        for(int i=size-1; i>=0; --i){
	        	Object[] vo = list[i];
	        	
	            this.setRow( i, vo);
	        }
	       
	        this.repaint();
	        this.revalidate();
	   }
	   
	   private void setRow(int i, Object[] vo) {
	    	this.getTable().setValueAt(vo[0], i, 0); 
	    	this.getTable().setValueAt(vo[1], i, 1); 
	    	this.getTable().setValueAt(vo[2], i, 2); 
	    	this.getTable().setValueAt(vo[3], i, 3); 
	    	this.getTable().setValueAt(vo[4], i, 4); 	 
	    	this.getTable().setValueAt(vo[5], i, 5); 	 
	    	this.getTable().setValueAt(vo[6], i, 6); 	 
	    	this.getTable().setValueAt(vo[7], i, 7); 	 
	    }
	


}
