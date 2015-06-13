package ui.stats;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vboar on 2015/6/13.
 */
public class StatsNav extends JPanel {

    private HomeUI frame;
    private PanelConfig pcfg;
    private Image bg;

    public StatsNav(HomeUI frame){
        this.frame = frame;
        this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
                .get(this.getClass().getName());
        this.bg = pcfg.getBg();

        this.setLayout(null);
        this.setSize(pcfg.getW(),pcfg.getH());
        this.setLocation(pcfg.getX(), pcfg.getY());

        initComponent();
    }

    public void paintComponent(Graphics g){
        g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
    }

    public void initComponent(){
        initLabels();
    }

    private void initLabels() {

    }
}
