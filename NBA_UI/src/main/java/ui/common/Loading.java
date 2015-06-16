package ui.common;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vboar on 2015/6/16.
 */
public class Loading extends JFrame {

    private static Loading loading = null;

    private Loading() {
        super();
        setUndecorated(true);
//        AWTUtilities.setWindowOpaque(this, false);
        setVisible(true);
        setLayout(null);
        setBounds(171, 24, 1024, 680);

        JLabel j = new JLabel(new ImageIcon("img/common/loading.gif"));
        j.setSize(66, 66);
        j.setBackground(new Color(100,100 ,100));
        j.setLocation(479, 350);
        add(j);
        j = new JLabel(new ImageIcon("img/common/alpha.png"));
        j.setSize(1024, 680);
        j.setLocation(0, 0);
        add(j);
        setBackground(new Color(0, 0, 0, 0));
        setIconImage(new ImageIcon("img/common/icon.png").getImage());
    }

    public static Loading getLoading() {
        if (loading == null) {
            loading = new Loading();
            return loading;
        }
        return loading;
    }

}
