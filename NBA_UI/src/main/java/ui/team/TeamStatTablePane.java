package ui.team;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.util.MyTable;
import ui.util.MyTableModel;
import ui.util.TablePanel;

public class TeamStatTablePane extends TablePanel{
	
	private int COLUMN_NUM = 24;
	private Object[][] list;
	private HomeUI frame;
	private TeamStat teamstat;
	
	public TeamStatTablePane(TableConfig cfg,Object[][] list,TeamStat teamstat,HomeUI frame){
		super(cfg);
		this.list = list;
		this.frame = frame;
		this.teamstat = teamstat;
		
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
        table.setShowGrid(false);
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("华文细黑",0,12));
        table.getTableHeader().setForeground(Color.BLACK);
        table.setFont(new Font("华文细黑", 0, 12));
        table.FitTableColumns(table);
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
	
	
	public void setData(Object[][] list) {
		int size;
		if (list == null)
			size = 0;
		else
			size = list.length;
		while (dtm.getRowCount() > size) {
			// System.out.println(x++);
			this.dtm.removeRow(size);
		}

		while (this.dtm.getRowCount() < size) {
			Object[] rowData = {0,0,0,0};
			this.dtm.addRow(rowData);

		}
		for (int i = 0; i<size; i++) {
			Object[] vo = list[i];

			this.setRow(i, vo);
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
		this.getTable().setValueAt(vo[8], i, 8);
		this.getTable().setValueAt(vo[9], i, 9);
		this.getTable().setValueAt(vo[10], i, 10);
		this.getTable().setValueAt(vo[11], i, 11);
		this.getTable().setValueAt(vo[12], i, 12);
		this.getTable().setValueAt(vo[13], i, 13);
		this.getTable().setValueAt(vo[14], i, 14);
		this.getTable().setValueAt(vo[15], i, 15);
		this.getTable().setValueAt(vo[16], i, 16);
		this.getTable().setValueAt(vo[17], i, 17);
		this.getTable().setValueAt(vo[18], i, 18);
		this.getTable().setValueAt(vo[19], i, 19);
		this.getTable().setValueAt(vo[20], i, 20);
		this.getTable().setValueAt(vo[21], i, 21);
		this.getTable().setValueAt(vo[22], i, 22);
		this.getTable().setValueAt(vo[23], i, 23);
		
		
	}

	
	
}
