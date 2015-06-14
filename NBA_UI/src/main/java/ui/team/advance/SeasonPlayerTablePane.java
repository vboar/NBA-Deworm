package ui.team.advance;

import java.awt.Color;
import java.awt.Font;

import ui.config.TableConfig;
import ui.util.MyTable;
import ui.util.MyTableModel;
import ui.util.TablePanel;

/**
 * 展示某赛季某球队参赛球员数据的表格
 * 
 * @author wang
 *
 */
public class SeasonPlayerTablePane extends TablePanel{
	private int COLUMN_NUM = 22;
	private Object[][] list;
	
	public SeasonPlayerTablePane(TableConfig cfg,Object[][] list){
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
            	if(columnIndex >= 2)
                return Double.class;
            	else return Object.class;
            }
        	
        }; 		
		
        table = new MyTable(this.dtm,this.getWidth());
        table.setRowHeight(25);
        table.setShowGrid(false);
        table.getTableHeader().setFont(new Font("华文细黑",0,12));
        table.getTableHeader().setForeground(Color.BLACK);
        table.FitTableColumns(table);
        table.getColumnModel().getColumn(0).setMaxWidth(144);
        initComponent();
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
		for(int i=0;i<row.length;i++){
			row[i] = vo[i];
		}	
	}
}
