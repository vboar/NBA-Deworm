package ui.live;

import service.impl.LiveServiceImpl;
import ui.config.TableConfig;
import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.MyTableModel;
import ui.util.TablePanel;
import vo.LiveMatchInfoVO;
import vo.LiveMatchVO;

import java.awt.*;
import java.util.List;

/**
 * Created by Vboar on 2015/6/12.
 */
public class ScoreTablePane extends TablePanel {

    private static int COLUMN_NUM = 5;

    private List<String> score1;
    private List<String> score2;
    private LiveMatchVO vo;
    private LiveMatchInfoVO info;

    public ScoreTablePane(TableConfig cfg, LiveMatchVO vo, LiveMatchInfoVO info) {
        super(cfg);

        this.vo = vo;
        this.info = info;
        score1 = vo.scoresA;
        score2 = vo.scoresB;

        this.initTable();
        this.initComponent();

        this.table.getTableHeader().setForeground(Color.BLACK);
        this.table.getTableHeader().setFont(new Font("微软雅黑", 0, 12));
        this.table.setFont(new Font("微软雅黑", 0, 12));
        this.table.setRowSorter(null);
        this.table.setShowGrid(false);
    }

    public void refresh(LiveMatchVO vo) {
        score1 = vo.scoresA;
        score2 = vo.scoresB;
        this.initData();
        this.dtm.setDataVector(data, columnNames);
        this.updateWidth();
    }

    @Override
    protected void initTable() {
        this.columnNames = cfg.getColumnName();
        this.initData();

        if (score1.size() == 6) {
            String[] temp = new String[7];
            for (int i = 0; i < temp.length; i++) {
                if (i <= 4) {
                    temp[i] = columnNames[i];
                }
                if (i == 5) {
                    temp[i] = "加时一";
                }
                if (i == 6) {
                    temp[i] = "总分";
                }
            }
            this.columnNames = temp;
        }

        if (score1.size() == 7) {
            String[] temp = new String[8];
            for (int i = 0; i < temp.length; i++) {
                if (i <= 4) {
                    temp[i] = columnNames[i];
                }
                if (i == 5) {
                    temp[i] = "加时一";
                }
                if (i == 6) {
                    temp[i] = "加时二";
                }
                if (i == 7) {
                    temp[i] = "总分";
                }
            }
            this.columnNames = temp;
        }

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
        createRow(data[0], score1, info.homeTeam);
        createRow(data[1], score2, info.guestTeam);
    }

    private void createRow(Object[] row, List<String> scores, String team) {
        row[0] = team;
        for (int i = 1; i <= scores.size(); i++) {
            row[i] = scores.get(i-1);
        }
    }

}
