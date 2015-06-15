package ui.team.advance;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.util.MyLabel;
import vo.TeamInfoVO;
import vo.TeamOppPerGameVO;
import vo.TeamOppTotalVO;
import vo.TeamPerGameVO;
import vo.TeamTotalVO;

/**
 * 显示team的更多详细数据
 * 
 * @author wang
 *
 */
public class TeamMore extends JPanel{
	private PanelConfig pcfg;
	private Image bg;
	public TBYTablePane table1;
	public PGBYTablePane table2;
	public OppTotalTablePane table3;
	public OppTotalTablePane table4;

	private Object[][] data1;
	private Object[][] data2;
	private Object[][] data3;
	private Object[][] data4;
	
	private TeamInfoVO vo;
	private List<TeamTotalVO> vo1;
	private List<TeamPerGameVO> vo2;
	private List<TeamOppTotalVO> vo3;
	private List<TeamOppPerGameVO> vo4;
	
	private MyLabel logo;
	private MyLabel hint;
	private MyLabel total;
	private MyLabel pergame;
	private MyLabel opptotal;
	private MyLabel opppergame;
	
	private String abbr;
	private HomeUI frame;
	
	public TeamMore(HomeUI frame,String abbr){
		this.frame = frame;
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.abbr = abbr;
		this.bg = pcfg.getBg();
		
		this.setLayout(null);
		this.setSize(pcfg.getW(),pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		initComponent();
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	private void initComponent(){
		initData();
		initLabels();
		initTables();
	}
	
	private void initLabels(){
		logo = new MyLabel(pcfg.getLabels().element("logo"));
		try {
			logo.setImage(ServiceFactoryImpl.getInstance().getTeamService().getTeamLogoByAbbr(abbr));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		add(logo);
		
		hint = new MyLabel(pcfg.getLabels().element("team"));
		hint.setText(vo.abbr+" :");
		hint.setFont(new Font("华文细黑",0,15));
		add(hint);

		total = new MyLabel(pcfg.getLabels().element("total"));
		total.setText("<html><u>Stats Total</u>&nbsp&nbsp/</html>");
		total.setFont(new Font("华文细黑",0,15));
		total.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				total.setText("<html><u>Stats Total</u>&nbsp&nbsp/</html>");
				pergame.setText("Stats Per Game  /");
				opptotal.setText("Opponent Stats Totals  /");
				opppergame.setText("Opponent Stats Per Game");
				
				table1.setVisible(true);
				table2.setVisible(false);
				table3.setVisible(false);
				table4.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				total.setText("<html><u>Stats Total</u>&nbsp&nbsp/</html>");
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(table1.isVisible() == false)
				total.setText("Stats Total  /");				
			}
			
		});
		add(total);
		
		pergame = new MyLabel(pcfg.getLabels().element("pergame"));
		pergame.setText("Stats Per Game  /");
		pergame.setFont(new Font("华文细黑",0,15));
		pergame.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				total.setText("Stats Total  /");
				pergame.setText("<html><u>Stats Per Game</u>&nbsp&nbsp/</html>");
				opptotal.setText("Opponent Stats Totals  /");
				opppergame.setText("Opponent Stats Per Game");
				
				table1.setVisible(false);
				table2.setVisible(true);
				table3.setVisible(false);
				table4.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				pergame.setText("<html><u>Stats Per Game</u>&nbsp&nbsp/</html>");
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(table2.isVisible()==false)
				pergame.setText("Stats Per Game  /");				
			}
			
		});
		add(pergame);
		
		opptotal = new MyLabel(pcfg.getLabels().element("opptotal"));
		opptotal.setText("Opponent Stats Totals  /");
		opptotal.setFont(new Font("华文细黑",0,15));
		opptotal.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				total.setText("Stats Total  /");
				pergame.setText("Stats Per Game  /");
				opptotal.setText("<html><u>Opponent Stats Totals</u>&nbsp&nbsp/</html>");
				opppergame.setText("Opponent Stats Per Game");
				
				table1.setVisible(false);
				table2.setVisible(false);
				table3.setVisible(true);
				table4.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				opptotal.setText("<html><u>Opponent Stats Totals</u>&nbsp&nbsp/</html>");
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(table3.isVisible()==false)
				opptotal.setText("Opponent Stats Totals  /");				
			}
			
		});
		add(opptotal);
		
		opppergame = new MyLabel(pcfg.getLabels().element("opppergame"));
		opppergame.setText("Opponent Stats Per Game");
		opppergame.setFont(new Font("华文细黑",0,15));
		opppergame.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				total.setText("Stats Total  /");
				pergame.setText("Stats Per Game  /");
				opptotal.setText("Opponent Stats Totals  /");
				opppergame.setText("<html><u>Opponent Stats Per Game</u></html>");
				
				table1.setVisible(false);
				table2.setVisible(false);
				table3.setVisible(false);
				table4.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				opppergame.setText("<html><u>Opponent Stats Per Game</u></html>");
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(table4.isVisible()==false)
				opppergame.setText("Opponent Stats Per Game");				
			}
			
		});
		add(opppergame);
	}
	
	private void initTables(){
		table1 = new TBYTablePane(new TableConfig(pcfg.getTablepane().element("table1")),data1,abbr,frame,this);
		add(table1);
		
		table2 = new PGBYTablePane(new TableConfig(pcfg.getTablepane().element("table2")),data2,abbr,frame,this);
		table2.setVisible(false);
		add(table2);
		
		table3 = new OppTotalTablePane(new TableConfig(pcfg.getTablepane().element("table3")),data3,abbr,frame,this);
		table3.setVisible(false);
		add(table3);
		
		table4 = new OppTotalTablePane(new TableConfig(pcfg.getTablepane().element("table4")),data4,abbr,frame,this);
		table4.setVisible(false);
		add(table4);
	}
	
	private void initData(){
		try {
			vo = ServiceFactoryImpl.getInstance().getTeamService().getTeamInfoByAbbr(abbr);
			vo1 = ServiceFactoryImpl.getInstance().getTeamService().getTeamTotalByAbbr(abbr);
			vo2 = ServiceFactoryImpl.getInstance().getTeamService().getTeamPerGameByAbbr(abbr);
			vo3 = ServiceFactoryImpl.getInstance().getTeamService().getTeamOppTotalByAbbr(abbr);
			vo4 = ServiceFactoryImpl.getInstance().getTeamService().getTeamOppPerGameByAbbr(abbr);
			
			data1 = new Object[vo1.size()][30];
			for(int i=0;i<vo1.size();i++){
				data1[i][0] = vo1.get(i).season;
				data1[i][1] = vo1.get(i).num_of_game;
				data1[i][2] = vo1.get(i).minute;
				data1[i][3] = vo1.get(i).fg;
				data1[i][4] = vo1.get(i).fga;
				data1[i][5] = vo1.get(i).fga_pct;
				data1[i][6] = vo1.get(i).fg3;
				data1[i][7] = vo1.get(i).fg3a;
				data1[i][8] = vo1.get(i).fg3_pct;
				data1[i][9] = vo1.get(i).fg2;
				data1[i][10] = vo1.get(i).fg2a;
				data1[i][11] = vo1.get(i).fg2_pct;
				data1[i][12] = vo1.get(i).ft;
				data1[i][13] = vo1.get(i).fta;
				data1[i][14] = vo1.get(i).ft_pct;
				data1[i][15] = vo1.get(i).orb;
				data1[i][16] = vo1.get(i).drb;
				data1[i][17] = vo1.get(i).trb;
				data1[i][18] = vo1.get(i).ast;
				data1[i][19] = vo1.get(i).stl;
				data1[i][20] = vo1.get(i).blk;
				data1[i][21] = vo1.get(i).tov;
				data1[i][22] = vo1.get(i).pf;
				data1[i][23] = vo1.get(i).pts;

			}
			
			data2 = new Object[vo2.size()][23];
			for(int i=0;i<vo2.size();i++){
				data2[i][0] = vo2.get(i).season;
				data2[i][1] = vo2.get(i).minute;
				data2[i][2] = vo2.get(i).fg;
				data2[i][3] = vo2.get(i).fga;
				data2[i][4] = vo2.get(i).fga_pct;
				data2[i][5] = vo2.get(i).fg3;
				data2[i][6] = vo2.get(i).fg3a;
				data2[i][7] = vo2.get(i).fga_pct;
				data2[i][8] = vo2.get(i).fg2;
				data2[i][9] = vo2.get(i).fg2a;
				data2[i][10] = vo2.get(i).fg2_pct;
				data2[i][11] = vo2.get(i).ft;
				data2[i][12] = vo2.get(i).fta;
				data2[i][13] = vo2.get(i).ft_pct;
				data2[i][14] = vo2.get(i).orb;
				data2[i][15] = vo2.get(i).drb;
				data2[i][16] = vo2.get(i).trb;
				data2[i][17] = vo2.get(i).ast;
				data2[i][18] = vo2.get(i).stl;
				data2[i][19] = vo2.get(i).blk;
				data2[i][20] = vo2.get(i).tov;
				data2[i][21] = vo2.get(i).pf;
				data2[i][22] = vo2.get(i).pts;
			}
			
			data3 = new Object[vo3.size()][23];
			for(int i=0;i<vo3.size();i++){
				data3[i][0] = vo3.get(i).season;
				data3[i][1] = vo3.get(i).minute;
				data3[i][2] = vo3.get(i).fg;
				data3[i][3] = vo3.get(i).fga;
				data3[i][4] = vo3.get(i).fga_pct;
				data3[i][5] = vo3.get(i).fg3;
				data3[i][6] = vo3.get(i).fg3a;
				data3[i][7] = vo3.get(i).fga_pct;
				data3[i][8] = vo3.get(i).fg2;
				data3[i][9] = vo3.get(i).fg2a;
				data3[i][10] = vo3.get(i).fg2_pct;
				data3[i][11] = vo3.get(i).ft;
				data3[i][12] = vo3.get(i).fta;
				data3[i][13] = vo3.get(i).ft_pct;
				data3[i][14] = vo3.get(i).orb;
				data3[i][15] = vo3.get(i).drb;
				data3[i][16] = vo3.get(i).trb;
				data3[i][17] = vo3.get(i).ast;
				data3[i][18] = vo3.get(i).stl;
				data3[i][19] = vo3.get(i).blk;
				data3[i][20] = vo3.get(i).tov;
				data3[i][21] = vo3.get(i).pf;
				data3[i][22] = vo3.get(i).pts;
			}
			
			data4 = new Object[vo4.size()][23];
			for(int i=0;i<vo4.size();i++){
				data4[i][0] = vo4.get(i).season;
				data4[i][1] = vo4.get(i).minute;
				data4[i][2] = vo4.get(i).fg;
				data4[i][3] = vo4.get(i).fga;
				data4[i][4] = vo4.get(i).fga_pct;
				data4[i][5] = vo4.get(i).fg3;
				data4[i][6] = vo4.get(i).fg3a;
				data4[i][7] = vo4.get(i).fga_pct;
				data4[i][8] = vo4.get(i).fg2;
				data4[i][9] = vo4.get(i).fg2a;
				data4[i][10] = vo4.get(i).fg2_pct;
				data4[i][11] = vo4.get(i).ft;
				data4[i][12] = vo4.get(i).fta;
				data4[i][13] = vo4.get(i).ft_pct;
				data4[i][14] = vo4.get(i).orb;
				data4[i][15] = vo4.get(i).drb;
				data4[i][16] = vo4.get(i).trb;
				data4[i][17] = vo4.get(i).ast;
				data4[i][18] = vo4.get(i).stl;
				data4[i][19] = vo4.get(i).blk;
				data4[i][20] = vo4.get(i).tov;
				data4[i][21] = vo4.get(i).pf;
				data4[i][22] = vo4.get(i).pts;
			}
			
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
