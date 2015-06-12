package ui.live;

import ui.config.TableConfig;
import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.MyTableModel;
import ui.util.TablePanel;
import vo.*;

import java.awt.*;
import java.util.List;
import java.util.Objects;

public class MessageTablePane extends TablePanel {

    private static int COLUMN_NUM = 4;
    private List<LiveMsgVO> list;
    private LiveMatchInfoVO info;

    public MessageTablePane(TableConfig cfg, List<LiveMsgVO> list, LiveMatchInfoVO info) {
        super(cfg);

        this.list = list;
        this.info = info;

        this.initTable();
        this.initComponent();

        this.table.getTableHeader().setForeground(Color.BLACK);
        this.table.getTableHeader().setFont(new Font("微软雅黑", 0, 12));
        this.table.setFont(new Font("微软雅黑", 0, 12));

        this.getRollpane().setRowHeaderWidth(0);
    }

    @Override
    protected void initTable() {
        this.columnNames = cfg.getColumnName();
        this.columnNames[3] = "比分(" + info.homeTeam + "-" + info.guestTeam + ")";
        this.initData();
        this.dtm = new MyTableModel(data, columnNames);
        this.table = new MyTable(this.dtm, this.getWidth());
        this.updateWidth();
    }

    private void updateWidth() {
        FrameUtil.setTableColumnWidth(table, this.getWidth(), 10);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(80);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(80);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(400);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(120);
        this.table.setRowHeight(25);
        this.updateUI();
    }

    private void initData() {
        this.data = new Object[list.size()][4];
        for (int i = 0; i < data.length; i++) {
            createRow(data[i], list.get(i));
        }
    }

    private void createRow(Object[] row, LiveMsgVO vo) {
        row[0] = vo.residualTime;
        row[1] = vo.team;
        row[2] = vo.content;
        row[3] = vo.scores;
    }

    public void refresh(List<LiveMsgVO> list) {
        this.list = list;
        this.initData();
        this.dtm.setDataVector(data, columnNames);
        this.updateWidth();
    }

}
