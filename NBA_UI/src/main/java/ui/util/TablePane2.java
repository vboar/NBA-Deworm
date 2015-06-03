package ui.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TablePane2 extends JPanel {

	private JTable fileTable;

	private Color color1 = new Color(225,237,233);
	private Color color2 = new Color(76,124,206);
	
	
	
	public TablePane2(){
		this.setBounds(0, 0, 1000, 1000);
		this.setBackground(Color.BLUE);
		this.setLayout(null);
		
		initTable();
		initScrollPane();
		initPanel();
		
	}
	
	private void initPanel(){
		JPanel jp = new JPanel()
		{
			
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics   g)
			{ 
				//使用Graphics2D绘制渐变色彩
				Graphics2D g2d = (Graphics2D) g;
				g2d.setPaint(new GradientPaint(0, 0, color1, this.getWidth(), this.getHeight(), color2));
				g2d.fill(new Rectangle(0, 0,this.getWidth(), this.getHeight()));
			} 
		};
		jp.setBounds(0, 0, 1000, 1000);
		this.add(jp);
	}
	
	private void initScrollPane() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1000, 1000);
		scrollPane.getViewport().setOpaque(false);// 将JScrollPane设置为透明
		scrollPane.setOpaque(false);// 将中间的viewport设置为透明
		scrollPane.setViewportView(fileTable);// 装载表格
		scrollPane.setColumnHeaderView(fileTable.getTableHeader());// 设置头部（HeaderView部分）
		scrollPane.getColumnHeader().setOpaque(false);// 再取出头部，并设置为透
		this.add(scrollPane);
	}
	
	private void initTable()
	{
		//初始化table
		String[] columnName = new String[]{
				"文件名","大小","用户","上传时间"
		};
		Object[][] columnValues = new Object[][]{
				{"楚留香传奇","232134 KB","fykhlp","2012-07-28 19:36:21"},
				{"楚留香传奇","232134 KB","fykhlp","2012-07-28 19:36:21"},
				{"楚留香传奇","232134 KB","fykhlp","2012-07-28 19:36:21"},
				{"楚留香传奇","232134 KB","fykhlp","2012-07-28 19:36:21"},
				{"楚留香传奇","232134 KB","fykhlp","2012-07-28 19:36:21"}
		};
		 TableModel model = new DefaultTableModel(columnValues, columnName);  
		fileTable = new JTable(model);
		fileTable.setRowHeight(25); 
		fileTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN); 
		fileTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		fileTable.setIntercellSpacing(new Dimension(0, 0)); 
		
		/*
		 * 将表格设置为透明，表格同样包括表格本身和其中的内容项
		 * 仅仅将表格本身设置为透明也没有用，应该将其中的内容项也设置为透明
		 * 内容项的设置是通过设置渲染器的透明来实现
		 */
		fileTable.setOpaque(false);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer(); 
		render.setOpaque(false); //将渲染器设置为透明
		
		//将这个渲染器设置到fileTable里。
		//这个设置在没有另外专门对column设置的情况下有效
		//若你对某个column特殊指定了渲染器，则对于这个column，它将不调用render渲染器
		//因此为了保证透明，如果你对column额外指定了渲染器，那么在额外的渲染器里也应该设置透明
		fileTable.setDefaultRenderer(Object.class,render);
		
		//设置显示范围
		Dimension viewSize = new Dimension(); 
        viewSize.width = fileTable.getColumnModel().getTotalColumnWidth(); ; 
        viewSize.height = 10 * fileTable.getRowHeight(); 
        fileTable.setPreferredScrollableViewportSize(viewSize); 
        
        //设置头部透明
        //头部实际上也是一个JTABLE，只有一行而已。
        JTableHeader header = fileTable.getTableHeader();//获取头部 
        header.setPreferredSize(new Dimension(30, 26)); 
        header.setOpaque(false);//设置头部为透明
        header.getTable().setOpaque(false);//设置头部里面的表格透明
        
        /*
         * 头部的表格也像前面的表格设置一样，还需要将里面的单元项设置为透明
         * 因此同样需要对头部单元项进行透明度设置，这里还是用渲染器。
         */
        header.setDefaultRenderer(render);
        TableCellRenderer headerRenderer = header.getDefaultRenderer(); 
        if (headerRenderer instanceof JLabel) 
        {
            ((JLabel) headerRenderer).setHorizontalAlignment(JLabel.CENTER); 
            ((JLabel) headerRenderer).setOpaque(false);	
        }
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);       
        fileTable.setRowSorter(sorter); 
	}
	

	public static void main(String[] args) {
			JFrame test  = new JFrame();
			test.setBounds(0, 0, 1000, 1000);
			test.getContentPane().add(new TablePane2());
			test.setVisible(true);
	}

}
