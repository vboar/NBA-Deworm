package ui.stats;

import javax.swing.*;

import service.InferStatsService;
import service.impl.ServiceFactoryImpl;
import ui.common.Loading;
import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.LoadFont;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import vo.MultiRegressionVO;
import vo.SimpleRegressionVO;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Stat2 extends JPanel{

    private PanelConfig pcfg ;
    private HomeUI frame;
    List<InferPanel> pList;
    String season;
    int whatNum;
    int dataSource;
    InferPanel ip;
    InferStatsService ss;
    int show = 0;
    JLabel next;
    MyLabel img;

    JLabel bl;
    JLabel al;
    JLabel ql;
    JLabel sl;
    JLabel pl;
    JLabel umaxl;
    JLabel uminl;
    JLabel ul;
    JLabel rl;
    JLabel pvl;
    JLabel sel;

    JLabel qll;
    JLabel sll;
    JLabel rll;
    JLabel ull;
    JLabel fll;

    JLabel vll;
    JLabel v1;
    JLabel v2;
    JLabel v3;
    JLabel v4;
    JLabel v5;
    JLabel v6;


    public Stat2(HomeUI frame){
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

        try {
            ss = ServiceFactoryImpl.getInstance().getInferStatsService();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void initPanels() {
        pList = new ArrayList<>();

        ip = new InferPanel(frame);
        final MyComboBox seasons = new MyComboBox(pcfg.getComboboxes().element("season"));
        seasons.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 12));
        ip.add(seasons);
        final MyComboBox what = new MyComboBox(pcfg.getComboboxes().element("what"));
        what.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 12));
        ip.add(what);
        final MyComboBox dataS = new MyComboBox(pcfg.getComboboxes().element("ds"));
        dataS.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 12));
        ip.add(dataS);
        JLabel start = new JLabel("开始");
        start.setBounds(870, 137, 50, 25);
        start.setFont(new Font("微软雅黑", 0, 15));
        start.setForeground(new Color(72, 207, 173));
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                season = (String)seasons.getSelectedItem();
                int num = what.getSelectedIndex();
                switch (num) {
                    case 0:
                        whatNum = 4;
                        break;
                    case 1:
                        whatNum = 5;
                        break;
                    case 2:
                        whatNum = 6;
                        break;
                    case 3:
                        whatNum = 1;
                        break;
                    case 4:
                        whatNum = 7;
                        break;
                    case 5:
                        whatNum = 8;
                        break;
                }
                dataSource = dataS.getSelectedIndex();
                pList = new ArrayList<>();
                pList.add(ip);
                new Thread(new MyThread()).start();
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

        img = new MyLabel(pcfg.getLabels().element("img"));
        ip.add(img);

        bl = new JLabel();
        bl.setText("回归系数b：     ");
        bl.setBounds(612, 180, 200, 30);
        ip.add(bl);
        al = new JLabel();
        al.setText("回归系数a：     ");
        al.setBounds(612, 200, 200, 30);
        ip.add(al);
        ql = new JLabel();
        ql.setText("偏差平方和q：   ");
        ql.setBounds(612, 220, 200, 30);
        ip.add(ql);
        sl = new JLabel();
        sl.setText("平均标准偏差s：  ");
        sl.setBounds(612, 240, 200, 30);
        ip.add(sl);
        pl = new JLabel();
        pl.setText("回归平方和p：    ");
        pl.setBounds(612, 260, 200, 30);
        ip.add(pl);
        umaxl = new JLabel();
        umaxl.setText("最大偏差umax：");
        umaxl.setBounds(612, 280, 200, 30);
        ip.add(umaxl);
        uminl = new JLabel();
        uminl.setText("最小偏差umin：");
        uminl.setBounds(612, 300, 200, 30);
        ip.add(uminl);
        ul = new JLabel();
        ul.setText("偏差平均值u：    ");
        ul.setBounds(612, 320, 200, 30);
        ip.add(ul);
        rl = new JLabel();
        rl.setText("相关系数：       ");
        rl.setBounds(612, 340, 200, 30);
        ip.add(rl);
        pvl = new JLabel();
        pvl.setText("p值：          ");
        pvl.setBounds(612, 360, 200, 30);
        ip.add(pvl);
        sel = new JLabel();
        sel.setText("str_err：      ");
        sel.setBounds(612, 380, 200, 30);
        ip.add(sel);


        addNext(ip);


        JLabel bg = new JLabel(new ImageIcon("img/stat/stats2/multi_step1.png"));
        bg.setBounds(0, 0, 940, 511);
        ip.add(bg);
        this.add(ip);
        pList.add(ip);



    }

    public void init2() {
        ip = new InferPanel(frame);
        final MyComboBox seasons = new MyComboBox(pcfg.getComboboxes().element("season"));
        seasons.setFont(LoadFont.loadFont("XIHEI.TTF", 0, 12));
        seasons.setLocation(730, 140);
        ip.add(seasons);
        JLabel start = new JLabel("开始");
        start.setBounds(870, 137, 50, 25);
        start.setFont(new Font("微软雅黑", 0, 15));
        start.setForeground(new Color(72, 207, 173));
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                season = (String)seasons.getSelectedItem();
                new Thread(new MyThread2()).start();
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

        qll = new JLabel();
        qll.setText("偏差平方和：     ");
        qll.setBounds(200, 180, 200, 30);
        ip.add(qll);
        sll = new JLabel();
        sll.setText("平均标准差：     ");
        sll.setBounds(200, 210, 200, 30);
        ip.add(sll);
        rll = new JLabel();
        rll.setText("复相关系数：     ");
        rll.setBounds(200, 240, 200, 30);
        ip.add(rll);
        ull = new JLabel();
        ull.setText("回归平方和：     ");
        ull.setBounds(200, 270, 200, 30);
        ip.add(ull);
        fll = new JLabel();
        fll.setText("F检验值：       ");
        fll.setBounds(200, 300, 200, 30);
        ip.add(fll);

        vll = new JLabel();
        vll.setText("m个偏相关系数：     ");
        vll.setBounds(600, 180, 200, 30);
        ip.add(vll);
        v1 = new JLabel();
        v1.setBounds(600, 210, 200, 30);
        ip.add(v1);
        v2 = new JLabel();
        v2.setBounds(600, 240, 200, 30);
        ip.add(v2);
        v3 = new JLabel();
        v3.setBounds(600, 270, 200, 30);
        ip.add(v3);
        v4 = new JLabel();
        v4.setBounds(600, 300, 200, 30);
        ip.add(v4);
        v5 = new JLabel();
        v5.setBounds(600, 330, 200, 30);
        ip.add(v5);
        v6 = new JLabel();
        v6.setBounds(600, 360, 200, 30);
        ip.add(v6);



        JLabel bg = new JLabel(new ImageIcon("img/stat/stats2/multi_step2.png"));
        bg.setBounds(0, 0, 940, 511);
        ip.add(bg);
        this.add(ip);
        pList.add(ip);

    }

    private void refresh() {
        SimpleRegressionVO vo = new SimpleRegressionVO();
        try {
            if (dataSource == 1) {
                vo = ss.getSimpleRegression(whatNum, season);
            } else {
                vo = ss.getSimpleRegressionMatch(whatNum, season);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        img.setImage(vo.img);

        bl.setText("回归系数b：     " + String.format("%.4f", vo.b));
        al.setText("回归系数a：     " + String.format("%.4f", vo.a));
        ql.setText("偏差平方和q：   " + String.format("%.4f", vo.q));
        sl.setText("平均标准偏差s：  " + String.format("%.4f", vo.s));
        pl.setText("回归平方和p：    " + String.format("%.4f", vo.p));
        umaxl.setText("最大偏差umax：" + String.format("%.4f", vo.umax));
        uminl.setText("最小偏差umin：" + String.format("%.4f", vo.umin));
        ul.setText("偏差平均值u：    " + String.format("%.4f", vo.u));
        rl.setText("相关系数：       " +String.format("%.4f",  vo.r));
        pvl.setText("p值：          " + String.format("%.4f", vo.p_value));
        sel.setText("str_err：      " + String.format("%.4f", vo.str_err));
    }

    private void refresh2() {
        MultiRegressionVO vo = new MultiRegressionVO();

        try {
            vo = ss.getMultiRegression(season);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        qll.setText("偏差平方和：     " + String.format("%.4f", vo.q));
        sll.setText("平均标准差：     " + String.format("%.4f", vo.s));
        rll.setText("复相关系数：     " + String.format("%.4f", vo.r));
        ull.setText("回归平方和：     " + String.format("%.4f", vo.u));
        fll.setText("F检验值：        " + String.format("%.4f", vo.f));

        v1.setText(String.format("%.4f", vo.v[0]));
        v2.setText(String.format("%.4f", vo.v[1]));
        v3.setText(String.format("%.4f", vo.v[2]));
        v4.setText(String.format("%.4f", vo.v[3]));
        v5.setText(String.format("%.4f", vo.v[4]));
        v6.setText(String.format("%.4f", vo.v[5]));
    }

    private class MyThread implements Runnable {

        @Override
        public void run() {
            Loading.getLoading().setVisible(true);
            refresh();
            Loading.getLoading().setVisible(false);
            repaint();
        }
    }

    private class MyThread2 implements Runnable {

        @Override
        public void run() {
            Loading.getLoading().setVisible(true);
            refresh2();
            Loading.getLoading().setVisible(false);
            repaint();
        }
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
                init2();
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
