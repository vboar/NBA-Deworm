package ui.match.info;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import ui.config.TableConfig;
import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.MyTableModel;
import ui.util.TablePanel;
import vo.LiveMatchVO;

public class ScoreTablePane extends TablePanel {
	
	 private static int COLUMN_NUM = 5;
	 
	 String home = null;
	 String guest = null;
	   private List<Integer> score1;
	    private List<Integer> score2;
	 
	 public ScoreTablePane(TableConfig cfg,List<List<Integer>> scores,String home,String guest) {
	        super(cfg);
	        score1 = scores.get(0);
	        score2 = scores.get(1);
	        this.home = home;
	        this.guest = guest;

	        this.initTable();
	        this.initComponent();

	        this.table.getTableHeader().setForeground(Color.BLACK);
	        this.table.getTableHeader().setFont(new Font("微软雅黑", 0, 12));
	        this.table.setFont(new Font("微软雅黑", 0, 12));
	        this.table.setRowSorter(null);
	        this.table.setShowGrid(false);
	    }


	@Override
	protected void initTable() {
		this.columnNames = cfg.getColumnName();
        this.initData();

        this.setSize(getWidth() + (columnNames.length - 6) * 50, getHeight());
        this.setLocation(getX() - (columnNames.length - 6) * 50, getY());

        this.dtm = new MyTableModel(data, columnNames);
        this.table = new MyTable(this.dtm, this.getWidth());
        this.updateWidth();

	}
	
	
	private void updateWidth() {
        FrameUtil.setTableColumnWidth(table, this.getWidth() + (columnNames.length-6)*50, 10);
        for (int i = 0; i < columnNames.length; i++) {
            this.table.getColumnModel().getColumn(i).setMaxWidth(50);
            this.table.getColumnModel().getColumn(i).setMinWidth(50);
        }
        this.table.setRowHeight(25);
        this.updateUI();
    }

    private void initData() {
        this.data = new Object[2][score1.size()+1];
        createRow(data[0], score1, home);
        createRow(data[1], score2, guest);
    }

    public void refresh(List<List<Integer>> scores,String home,String guest ) {
        score1 = scores.get(0);
        score2 = scores.get(1);
        this.home = home;
        this.guest = guest;
        this.initData();
        this.dtm.setDataVector(data, columnNames);
        this.updateWidth();
    }
    private void createRow(Object[] row, List<Integer> scores, String team) {
        row[0] = team;
        for (int i = 1; i <= scores.size(); i++) {
            row[i] = scores.get(i-1);
        }
    }

}
