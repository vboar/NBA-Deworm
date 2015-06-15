package ui.stats;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vboar on 2015/6/13.
 */
public class StatsPanel extends JPanel {

    private PanelConfig pcfg ;
    private HomeUI frame;

    public Stat1 stat1;
    public Stat2 stat2;
    
    public StatsPanel(HomeUI frame) {
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

    private void initComponent(){
        initPanel();
    }

    private void initPanel() {
    	stat1 = new Stat1(frame);
    	add(stat1);
    	
    	stat2 = new Stat2(frame);
    	stat2.setVisible(false);
    	add(stat2);
    }

}
