package ui.team.advance;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.player.PlayerNav;
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
	private HomeUI frame;
	
	public SeasonPlayerTablePane(TableConfig cfg,Object[][] list,HomeUI frame){
		super(cfg);
		this.list = list;
		this.frame = frame;
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
        this.table.addMouseListener(showDataInfo());
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

	
	 private MouseAdapter showDataInfo(){
	    	MouseAdapter adapter = new MouseAdapter() {
	    		 public void mouseReleased (MouseEvent e) {  
	            	 int column = table.columnAtPoint(e.getPoint());
	            	 int row = table.rowAtPoint(e.getPoint());
	            	 if(column == 0){
	            		 if(table.getValueAt(row, 0).toString().contains(" ")){
	            		frame.motherPanel.teamnav.setVisible(false);
	            		frame.motherPanel.teamPanel.setVisible(false);
	            		frame.motherPanel.playerPanel.playerInfoPane.changeData(table.getValueAt(row, 0).toString());
	            		frame.motherPanel.playernav.setColor(0);
	            		frame.motherPanel.playernav .hintAll();
	            		frame.motherPanel.playernav.setVisible(true);
	            		frame.motherPanel.playerPanel.setVisible(true);
	            		frame.motherPanel.playerPanel.playerInfoPane.setVisible(true);
	            		 }
	            	 }
	             }  	    		
			};
			return adapter;
	    	
	    }
	 
	private void createRow(Object[] row, Object[] vo) {
		for(int i=0;i<row.length;i++){
			row[i] = vo[i];
		}	
	}
}
