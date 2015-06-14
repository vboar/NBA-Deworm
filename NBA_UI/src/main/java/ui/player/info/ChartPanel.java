package ui.player.info;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyLabel;
import util.FieldType;

public class ChartPanel extends JPanel {
	private PanelConfig pcfg;
	private HomeUI frame;

	private MyLabel nameLb;
	private MyLabel seasonLb;
	
	private MyLabel chart1;
	private MyLabel chart2;
	private MyLabel chart3;

	public ChartPanel(HomeUI frame) {
		this.frame = frame;
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;

		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());

		this.initComponent();
	}
	
	private void initComponent(){
		initLabels();
		initChart();
	}
	
	private void initLabels(){
		nameLb = new MyLabel(pcfg.getLabels().element("name"));		
		add(nameLb);
		
		seasonLb = new MyLabel(pcfg.getLabels().element("season"));
		add(seasonLb);
		
		
	}
	
	private void initChart(){
		chart1 = new MyLabel(pcfg.getLabels().element("chart1"));
		chart2 = new MyLabel(pcfg.getLabels().element("chart2"));
		chart3 = new MyLabel(pcfg.getLabels().element("chart3"));
		
		add(chart1);
		add(chart2);
		add(chart3);
		
		
	}
public void changeData(String name,String season){
		
		ArrayList<Integer> field0 =new ArrayList<>();
		field0.add(FieldType.PER.ordinal());
		System.out.println(field0.get(0));
//		field0.add(FieldType.ORB_PCT.ordinal());
//		field0.add(FieldType.DRB_PCT.ordinal());
//		field0.add(FieldType.TRB_PCT.ordinal());
//		field0.add(FieldType.AST_PCT.ordinal());
//		field0.add(FieldType.STL_PCT.ordinal());
//		field0.add(FieldType.BLK_PCT.ordinal());
//		field0.add(FieldType.TOV_PCT.ordinal());
//		field0.add(FieldType.USG_PCT.ordinal());
		
		
		
		ArrayList<Integer> field =new ArrayList<>();
		field.add(FieldType.PTS.ordinal());
//		field.add(FieldType.AST.ordinal());
//		field.add(FieldType.BLK.ordinal());
//		field.add(FieldType.STL.ordinal());
//		field.add(FieldType.TRB.ordinal());		
//		field.add(FieldType.ORB.ordinal());
//		field.add(FieldType.DRB.ordinal());
//		field.add(FieldType.TOV.ordinal());
//		field.add(FieldType.PF.ordinal());		
		nameLb.setText(name);
		seasonLb.setText(season);
		
		ImageIcon radar = null;
		ImageIcon basic = null;
		ImageIcon advance = null;
		try {
		radar = ServiceFactoryImpl.getInstance().getStatsService().getPlayerRadar(name, season, 1);
		
//		basic = ServiceFactoryImpl.getInstance().getStatsService().
//		advance = ServiceFactoryImpl.getInstance().getStatsService().getPlayerAdvancedCompareBarChart(name1, name2, "Career", field0, 1);
//		
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		chart1.setImage(radar);
		chart2.setImage(basic);
		chart3.setImage(advance);

		
	}

}
