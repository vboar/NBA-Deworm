package ui.team;



import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

import ui.config.TableConfig;
import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.MyTableModel;
import ui.util.TablePanel;

/**
 * 显示球队概况的表格
 * 
 * @author wang
 *
 */
public class TeamGeneralTablePane extends TablePanel{

	private int COLUMN_NUM = 3;
	private Object[][] list;
	
	public TeamGeneralTablePane(TableConfig cfg,Object[][] list){
		super(cfg);
		this.list = list;
		this.initTable();
	}
	
	@Override
	protected void initTable() {
		this.columnNames = cfg.getColumnName();
		this.initData(list);
		
		 this.dtm = new MyTableModel(data, columnNames){
       	  
	            @Override
	            public Class<?> getColumnClass(int columnIndex) { 
	            	if(columnIndex >= 1)
	                return Double.class;
	            	else return Object.class;
	            }
	        	
	        };       
	        this.table = new MyTable(this.dtm, this.getWidth());
	     
	        //this.updateWidth();
	        table.setSize(300, 150);
	        table.setLocation(0, 0);
	        table.setShowGrid(false);
	        table.setRowHeight(30);
	        add(table);
	}
	
	private void updateWidth() {
		FrameUtil.setTableColumnWidth(table, this.getWidth(), 10);
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
    }
}
