package ui.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTable;

public class FrameUtil {
private static Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
	
	private static Dimension SCREEN_SIZE = TOOLKIT.getScreenSize();

	public static void setFrameCenter(JFrame frame,int windowUp){

		Dimension windowSize = frame.getSize();
		frame.setLocation((SCREEN_SIZE.width-windowSize.width)>>1, ((SCREEN_SIZE.height-windowSize.height)>>1)-windowUp);
	}
	
	public static void setTableColumnWidth(JTable table, int containerW, int padding){
		//计算表格总体宽度 getTable().
        int allwidth = table.getIntercellSpacing().width;
        for (int j = 0; j < table.getColumnCount(); j++) {
            //计算该列中最长的宽度
            int max = 0;
            //计算表头的宽度
            int headerwidth = table.getTableHeader().
              getDefaultRenderer().getTableCellRendererComponent(
                      table, table.getColumnModel().
              getColumn(j).getIdentifier(), false, false,
              -1, j).getPreferredSize().width;
            //列宽至少应为列头宽度
            max += headerwidth+2*padding;
            //设置列宽
            table.getColumnModel().
              getColumn(j).setPreferredWidth(max);
            //给表格的整体宽度赋值，记得要加上单元格之间的线条宽度1个像素
            allwidth += max + table.getIntercellSpacing().width;
        }
        if (allwidth > containerW) {
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
	}
}
