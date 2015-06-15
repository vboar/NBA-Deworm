package ui.stats;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import service.InferStatsService;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.config.TableConfig;
import ui.home.HomeUI;
import ui.util.LoadFont;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import vo.TeamWinAnalysisVO;

public class Stat1 extends JPanel{

    private PanelConfig pcfg ;
    private HomeUI frame;
    List<InferPanel> pList;
    String season;
    InferPanel ip;
    InferStatsService ss;
    int show = 0;
    JLabel next;

    public Stat1(HomeUI frame){
        this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
                .get(this.getClass().getName());
        this.frame = frame;
        // 设置布局管理器为自由布局
        this.setOpaque(false);
        this.setLayout(null);
        this.setSize(pcfg.getW(), pcfg.getH());
        this.setLocation(pcfg.getX(), pcfg.getY());
        // 初始化组件
        this.initComponent();
        this.repaint();
	}

    private void initComponent() {
        initPanels();

//        try {
//            ss = ServiceFactoryImpl.getInstance().getInferenceService();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
    }

    private void initPanels() {
        pList = new ArrayList<>();

        // STEP1
        ip = new InferPanel(frame);
        MyComboBox seasons = new MyComboBox(pcfg.getComboboxes().element("season"));
        seasons.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 12));
        ip.add(seasons);
        JLabel start = new JLabel("开始");
        start.setBounds(870, 137, 50, 25);
        start.setFont(new Font("微软雅黑", 0, 15));
        start.setForeground(new Color(72, 207, 173));
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                season = (String)seasons.getSelectedItem();
                pList = new ArrayList<>();
                pList.add(ip);
                initNormal();
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                start.setText("<html><u>开始</u></html>");

            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                start.setText("开始");
            }
        });
        ip.add(start);
        JLabel bg = new JLabel(new ImageIcon("img/stat/stats1/step1.png"));
        bg.setBounds(0, 0, 940, 511);
        ip.add(bg);
        this.add(ip);
        pList.add(ip);

    }

    private void initNormal() {

//        TeamWinAnalysisVO vo = new TeamWinAnalysisVO();
//        try {
//            vo = ss.getTeamTestingResultBySeason(season);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }

        ip.setVisible(false);

        // PP
        show = 1;
        ip = new InferPanel(frame);
        MyLabel home_pp = new MyLabel(pcfg.getLabels().element("a"), "img/stat/temp/1.png", 0);
        MyLabel guest_pp = new MyLabel(pcfg.getLabels().element("b"), "img/stat/temp/2.png", 0);
        ip.add(home_pp);
        ip.add(guest_pp);
        JLabel text = new JLabel("P-P图检验");
        text.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 20));
        text.setBounds(226, 140, 150, 30);
        ip.add(text);
        addNext(ip);
        JLabel bg = new JLabel(new ImageIcon("img/stat/stats1/step1.png"));
        bg.setBounds(0, 0, 940, 511);
        ip.add(bg);
        add(ip);
        pList.add(ip);

        // 单总体KS
        ip = new InferPanel(frame);
        ip.setVisible(false);
        text = new JLabel("单总体KS-检验");
        text.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 20));
        text.setBounds(226, 140, 150, 30);
        ip.add(text);
        addNext(ip);
        Object[][] data = new Object[3][3];
        data[0][0] = "";
        data[0][1] = "D值";
        data[0][2] = "p值";
        data[1][0] = "主场";
        data[2][0] = "客场";
        data[1][1] = "0.060";
        data[2][1] = "0.221";
        data[1][2] = "0.070";
        data[2][2] = "0.107";
        KSTable ksTable = new KSTable(new TableConfig(pcfg.getTables().element("ks")), data);
        ip.add(ksTable);
        bg = new JLabel(new ImageIcon("img/stat/stats1/step1.png"));
        bg.setBounds(0, 0, 940, 511);
        ip.add(bg);
        add(ip);
        pList.add(ip);

        // 偏度峰度检验
        ip = new InferPanel(frame);
        ip.setVisible(false);
        text = new JLabel("偏度峰度检验");
        text.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 20));
        text.setBounds(226, 140, 150, 30);
        ip.add(text);
        addNext(ip);
        data = new Object[3][5];
        data[0][0] = "";
        data[0][1] = "偏度";
        data[0][2] = "峰度";
        data[0][3] = "s^2+k^2";
        data[0][4] = "p值";
        data[1][0] = "主场";
        data[1][1] = "";
        data[1][2] = "";
        data[1][3] = "";
        data[1][4] = "";
        data[2][0] = "客场";
        data[2][1] = "";
        data[2][2] = "";
        data[2][3] = "";
        data[2][4] = "";
        PFTable pfTable = new PFTable(new TableConfig(pcfg.getTables().element("pf")), data);
        ip.add(pfTable);
        bg = new JLabel(new ImageIcon("img/stat/stats1/step1.png"));
        bg.setBounds(0, 0, 940, 511);
        ip.add(bg);
        add(ip);
        pList.add(ip);

        // 配对样本t-检验
        ip = new InferPanel(frame);
        ip.setVisible(false);
        text = new JLabel("配对样本t-检验");
        text.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 20));
        text.setBounds(226, 140, 150, 30);
        ip.add(text);
        addNext(ip);
        JLabel t = new JLabel();
        t.setText("t值： " + "");
        t.setFont(new Font(("微软雅黑"), 0, 12));
        t.setBounds(278, 218, 200, 30);
        ip.add(t);
        t = new JLabel();
        t.setText("p值： " + "");
        t.setFont(new Font(("微软雅黑"), 0, 12));
        t.setBounds(278, 258, 200, 30);
        ip.add(t);
        t = new JLabel();
        t.setText("主场胜场数均值： " + "");
        t.setFont(new Font(("微软雅黑"), 0, 12));
        t.setBounds(278, 298, 200, 30);
        ip.add(t);
        t = new JLabel();
        t.setText("主场胜场数标准差： " + "");
        t.setFont(new Font(("微软雅黑"), 0, 12));
        t.setBounds(278, 338, 200, 30);
        ip.add(t);
        t = new JLabel();
        t.setText("客场胜场数均值： " + "");
        t.setFont(new Font(("微软雅黑"), 0, 12));
        t.setBounds(278, 378, 200, 30);
        ip.add(t);
        t = new JLabel();
        t.setText("客场胜场数标准差： " + "");
        t.setFont(new Font(("微软雅黑"), 0, 12));
        t.setBounds(278, 418, 200, 30);
        ip.add(t);
        bg = new JLabel(new ImageIcon("img/stat/stats1/step2.png"));
        bg.setBounds(0, 0, 940, 511);
        ip.add(bg);
        add(ip);
        pList.add(ip);


        // Wilconxon符号秩和检验
        ip = new InferPanel(frame);
        ip.setVisible(false);
        text = new JLabel("Wilconxon符号秩和检验");
        text.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 20));
        text.setBounds(226, 140, 250, 30);
        ip.add(text);
        addNext(ip);
        t = new JLabel();
        t.setText("t值： " + "");
        t.setFont(new Font(("微软雅黑"), 0, 12));
        t.setBounds(278, 218, 200, 30);
        ip.add(t);
        t = new JLabel();
        t.setText("p值： " + "");
        t.setFont(new Font(("微软雅黑"), 0, 12));
        t.setBounds(278, 258, 200, 30);
        ip.add(t);
        bg = new JLabel(new ImageIcon("img/stat/stats1/step2.png"));
        bg.setBounds(0, 0, 940, 511);
        ip.add(bg);
        add(ip);
        pList.add(ip);

        // Mann-Whitneyu检验
        ip = new InferPanel(frame);
        ip.setVisible(false);
        text = new JLabel("Mann-Whitneyu检验");
        text.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 20));
        text.setBounds(226, 140, 250, 30);
        ip.add(text);
        addNext(ip);
        t = new JLabel();
        t.setText("u值： " + "");
        t.setFont(new Font(("微软雅黑"), 0, 12));
        t.setBounds(278, 218, 200, 30);
        ip.add(t);
        t = new JLabel();
        t.setText("p值： " + "");
        t.setFont(new Font(("微软雅黑"), 0, 12));
        t.setBounds(278, 258, 200, 30);
        ip.add(t);
        bg = new JLabel(new ImageIcon("img/stat/stats1/step2.png"));
        bg.setBounds(0, 0, 940, 511);
        ip.add(bg);
        add(ip);
        pList.add(ip);

        // 结论
        ip = new InferPanel(frame);
        ip.setVisible(false);
        bg = new JLabel(new ImageIcon("img/stat/stats1/step2.png"));
        bg.setBounds(0, 0, 940, 511);
        ip.add(bg);
        add(ip);
        pList.add(ip);
    }

    public void addNext(InferPanel ip) {

        next = new JLabel("Next");
        next.setBounds(844, 455, 50, 25);
        next.setFont(new Font("微软雅黑", 0, 15));
        next.setForeground(new Color(72, 207, 173));
        ip.add(next);
        next.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                pList.get(show).setVisible(false);
                show++;
                pList.get(show).setVisible(true);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                next.setText("<html><u>Next</u></html>");

            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                next.setText("Next");
            }
        });
    }

    public void backFirst() {
        pList.get(show).setVisible(false);
        show = 0;
        ip = pList.get(0);
        pList.get(show).setVisible(true);
    }
}
