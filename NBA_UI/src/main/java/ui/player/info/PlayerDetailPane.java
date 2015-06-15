package ui.player.info;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.team.TeamDetail;
import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.MyTableModel;
import ui.util.TablePanel;

public class PlayerDetailPane extends TablePanel {

	
private static int COLUMN_NUM = 22;
	
	
	Object[][] list = null;
	
	
	private HomeUI frame;
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
        table.setShowGrid(false);
        this.table.addMouseListener(showDataInfo());
        table.getTableHeader().setFont(new Font("华文细黑",0,12));
        table.getTableHeader().setForeground(Color.BLACK);
        table.setFont(new Font("华文细黑",0,12));

        this.updateWidth();
	}
	public PlayerDetailPane(TableConfig cfg,Object[][] list,HomeUI frame) {
		
        super(cfg);
        this.frame = frame;
        this.list = list;
        //list = controller.show("13-14");
        
        //System.out.println(list.size());       
        this.initTable();
        this.initComponent();
        
        DefaultTableCellRenderer render = new DefaultTableCellRenderer(); 
		render.setOpaque(false);
		table.getTableHeader().setDefaultRenderer(render);
	    TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer(); 
	        if (headerRenderer instanceof JLabel) 
	        {
	            ((JLabel) headerRenderer).setHorizontalAlignment(JLabel.CENTER); 
	            ((JLabel) headerRenderer).setOpaque(false);	
	        }
    }
	
	 public void updateWidth() {
			FrameUtil.setTableColumnWidth(table, this.getWidth(), 8);
			this.table.getColumnModel().getColumn(0).setPreferredWidth(50);
//			this.table.getColumnModel().getColumn(1).setPreferredWidth(50);
//			this.table.getColumnModel().getColumn(2).setPreferredWidth(85);
//			this.table.getColumnModel().getColumn(3).setPreferredWidth(95);
			int columncount = this.table.getColumnCount();
			int i =0;
	        for ( i = 1; i < columncount-1; i++) {
	            this.table.getColumnModel().getColumn(i).setPreferredWidth(40);
	        }
	        this.table.getColumnModel().getColumn(i).setPreferredWidth(85);
         this.table.setRowHeight(25);
			this.updateUI();
		}	
	 
	 private MouseAdapter showDataInfo(){
	    	MouseAdapter adapter = new MouseAdapter() {
	    		 public void mouseReleased (MouseEvent e) {  
	            	 int column = table.columnAtPoint(e.getPoint());
	            	 int row = table.rowAtPoint(e.getPoint());
	            	 if(column == 1){	            		 
	            		String name = table.getValueAt(row, 1).toString();
	            		if(name.length()==3){
	            			//System.out.println("ddd"+name);
	            			frame.motherPanel.playerPanel.setVisible(false);
	            			frame.motherPanel.teamPanel.add(frame.motherPanel.teamPanel.
	            					teamDetail = new TeamDetail(frame,name));
	            			frame.motherPanel.teamPanel.teamindex.setVisible(false);
	            			frame.motherPanel.playernav.setVisible(false);
	            			frame.motherPanel.teamnav.setVisible(true);
	            			frame.motherPanel.teamPanel.setVisible(true);
	            		}
	            		 
	            	 }
	             }  	    		
			};
			return adapter;
	    	
	    }
	 
	  private void initData(Object[][] list) {
			int size;
			if (list == null)
				size = 0;
			else
				size = list.length;

			this.data = new Object[size][COLUMN_NUM];
			for (int i = 0; i <size; i++) {

				this.createRow(data[size-i-1], list[i]);
			}
		}

	  
	   
	   private void createRow(Object[] row, Object[] vo) {
	    	row[0] =vo[0];
	    	row[1] =vo[1];
	    	row[2] = vo[2];
	    	row[3] = vo[3];
	    	row[4] = vo[4];
	    	row[5] = vo[5];
	    	row[6] = vo[6];
	    	row[7] = vo[7];
	    	row[8] = vo[8];
	    	row[9] = vo[9];
	    	
	    	row[10] = vo[10];
	    	row[11] = vo[11];
	    	row[12] = vo[12];
	    	row[13] = vo[13];
	    	row[14] = vo[14];
	    	row[15] = vo[15];
	    	row[16] = vo[16];
	    	row[17] = vo[17];
	    	row[18] = vo[18];
	    	row[19] = vo[19];
	    	
	    	row[20] = vo[20];
	    	row[21] = vo[21];
	    	
	    }
	   
	   public void renewTable(Object[][] list){
		   
			  
		   
			  
		   setData(list);
		  // setcolumnName(head);
		   this.updateWidth();
		   this.repaint();
		   this.revalidate();
	   }
	   
	   private void setData(Object[][] list){
		   int size;
	        if(list == null) size = 0;
	        else size = list.length;
	        while(dtm.getRowCount()>size){
	        	//System.out.println(x++);
	        	this.dtm.removeRow(size);
	        }
	       
	        while(this.dtm.getRowCount()<size){
	        	Object[] rowData = {0,0,0,0,0,0,0,0,0,0};
	        	this.dtm.addRow(rowData);
	        }
	        for(int i=0; i<size; i++){
	        	Object[] vo = list[size-i-1];
	        	
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
	    	
	   
	    }
	
}
