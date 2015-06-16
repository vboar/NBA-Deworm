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
    InferPanel ip;
    InferStatsService ss;
    int show = 0;
    JLabel next;


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
//                initNormal();
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
        JLabel bg = new JLabel(new ImageIcon("img/stat/stats1/step1.png"));
        bg.setBounds(0, 0, 940, 511);
        ip.add(bg);
        this.add(ip);
        pList.add(ip);
    }

    private void initNormal() {

    }

    private class MyThread implements Runnable {

        @Override
        public void run() {
            Loading.getLoading().setVisible(true);
            initNormal();
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
