package ui.player.stat;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.util.FrameUtil;
import ui.util.MyTable;
import ui.util.MyTableModel;
import ui.util.TablePanel;

public class PlayerAllTablePane extends TablePanel {

	private static int COLUMN_NUM = 21;

	private Object[][] list;
	private HomeUI frame;

	public PlayerAllTablePane(TableConfig cfg, Object[][] list,HomeUI frame) {

		
		super(cfg);
		this.frame = frame;
		this.list = list;
		this.initTable();
		this.initComponent();
	}

	@Override
	protected void initTable() {
		this.columnNames = cfg.getColumnName();
		this.initData(list);
		this.dtm = new MyTableModel(data, columnNames) {

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex >= 2)
					return Double.class;
				else
					return Object.class;
			}

		};
		this.table = new MyTable(this.dtm, this.getWidth());
		table.getTableHeader().setFont(new Font("华文细黑",0,12));
        table.getTableHeader().setForeground(Color.BLACK);
        table.setFont(new Font("华文细黑",0,12));
		this.table.addMouseListener(showDataInfo());
		table.setShowGrid(false);
//        table.getTableHeader().setResizingAllowed(false);
		this.updateWidth();
	}

	public void updateWidth() {
		FrameUtil.setTableColumnWidth(table, this.getWidth(), 10);
		for(int i = 1;i<this.table.getColumnModel().getColumnCount();i++){
			this.table.getColumnModel().getColumn(i).setPreferredWidth(39);
		}
		this.table.getColumnModel().getColumn(0).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(53);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(28);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(28);
        this.table.getColumnModel().getColumn(6).setPreferredWidth(44);
        this.table.getColumnModel().getColumn(8).setPreferredWidth(44);
        this.table.getColumnModel().getColumn(10).setPreferredWidth(44);
        this.table.getColumnModel().getColumn(11).setPreferredWidth(44);
        this.table.getColumnModel().getColumn(12).setPreferredWidth(28);
		// this.table.getColumnModel().getColumn(1).setPreferredWidth(170);
		// this.table.getColumnModel().getColumn(2).setPreferredWidth(420);
		// this.table.getTableHeader().setPreferredSize(new Dimension(0, 50));
//		 this.table.setRowHeight(18);
		this.updateUI();
	}

    public void updateWidthWithAdvanced() {
        FrameUtil.setTableColumnWidth(table, this.getWidth(), 10);
        for(int i = 1;i<this.table.getColumnModel().getColumnCount();i++){
            this.table.getColumnModel().getColumn(i).setPreferredWidth(39);
        }
        this.table.getColumnModel().getColumn(0).setPreferredWidth(80);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(42);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(38);
        this.table.getColumnModel().getColumn(6).setPreferredWidth(43);
        this.table.getColumnModel().getColumn(11).setPreferredWidth(43);
        this.table.getColumnModel().getColumn(12).setPreferredWidth(43);
        this.table.getColumnModel().getColumn(13).setPreferredWidth(38);
        this.table.getColumnModel().getColumn(14).setPreferredWidth(38);
        this.table.getColumnModel().getColumn(15).setPreferredWidth(28);
        this.table.getColumnModel().getColumn(16).setPreferredWidth(43);
        this.table.getColumnModel().getColumn(17).setPreferredWidth(45);
        this.table.getColumnModel().getColumn(18).setPreferredWidth(45);
        this.table.getColumnModel().getColumn(19).setPreferredWidth(38);
        this.table.getColumnModel().getColumn(20).setPreferredWidth(44);
        this.updateUI();
    }

	private void initData(Object[][] list) {
		int size;
		if (list == null)
			size = 0;
		else
			size = list.length;

		this.data = new Object[size][COLUMN_NUM];
		for (int i = size - 1; i >= 0; --i) {

			this.createRow(data[i], list[i]);
		}
	}

	private MouseAdapter showDataInfo() {
		MouseAdapter adapter = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int column = table.columnAtPoint(e.getPoint());
					int row = table.rowAtPoint(e.getPoint());
					if (column == 0) {
						frame.motherPanel.playerPanel.playerstat.setVisible(false);
						frame.motherPanel.playerPanel.playerInfoPane.changeData(table.getValueAt(row, 0).toString());
						frame.motherPanel.playerPanel.playerInfoPane.setVisible(true);
					}
				}
			}

			public void mouseEntered(MouseEvent e) {
				int column = table.columnAtPoint(e.getPoint());
				int row = table.rowAtPoint(e.getPoint());
				if (column == 0) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}
		};
		return adapter;

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
//		row[21] = vo[21];
//		row[22] = vo[22];
//		row[23] = vo[23];
//		row[24] = vo[24];
//		row[25] = vo[25];
//		row[26] = vo[26];
	


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
			Object[] rowData = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
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
		
//		this.getTable().setValueAt(vo[21], i, 21);
//		this.getTable().setValueAt(vo[22], i, 22);
//		this.getTable().setValueAt(vo[23], i, 23);
//		this.getTable().setValueAt(vo[24], i, 24);
//		this.getTable().setValueAt(vo[25], i, 25);
//		this.getTable().setValueAt(vo[26], i, 26);
	}

}
