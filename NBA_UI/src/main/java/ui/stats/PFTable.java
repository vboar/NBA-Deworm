package ui.stats;

import ui.config.TableConfig;
import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.MyTableModel;
import ui.util.TablePanel;

import java.awt.*;

/**
 * Created by Vboar on 2015/6/15.
 */
public class PFTable extends TablePanel {

    private int COLUMN_NUM = 5;
    private Object[][] list;

    public PFTable(TableConfig cfg,Object[][] list){
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
        table.getTableHeader().setFont(new Font("微软雅黑", 0, 12));
        table.setFont(new Font("微软雅黑", 0, 12));
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
        row[3] = vo[3];
        row[4] = vo[4];
    }
}
