package ui.stats;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;

import javax.swing.*;

/**
 * Created by Vboar on 2015/6/15.
 */
public class InferPanel extends JPanel {

    private PanelConfig pcfg ;
    private HomeUI frame;

    public InferPanel(HomeUI frame) {
        this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
                .get(this.getClass().getName());
        this.frame = frame;
        // 设置布局管理器为自由布局
        this.setOpaque(false);
        this.setLayout(null);
        this.setSize(pcfg.getW(), pcfg.getH());
        this.setLocation(pcfg.getX(), pcfg.getY());
        // 初始化组件
        this.init();
    }

    public void init() {
        this.repaint();
    }

}
