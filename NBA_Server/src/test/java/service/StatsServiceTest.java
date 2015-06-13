package service;

import junit.framework.TestCase;
import service.impl.StatsServiceImpl;
import util.FieldType;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vboar on 2015/6/11.
 */
public class StatsServiceTest extends TestCase {

    private StatsService ss;

    public StatsServiceTest() throws RemoteException {
        ss = new StatsServiceImpl();
    }

    public void testGetPlayerRadar() throws Exception {

    }

    public static void main(String[] args) throws RemoteException {
        StatsService ss = new StatsServiceImpl();
        // ImageIcon img = ss.getPlayerCompareRadar("Kobe Bryant", "Yao Ming", "Career", 1);
        // ImageIcon img = ss.getPlayerRadar("Kobe Bryant", "Career", 1);
        List<FieldType> list = new ArrayList<>();
        list.add(FieldType.AST);
        list.add(FieldType.BLK);
        list.add(FieldType.TOV);
        ImageIcon img = ss.getPlayerBasicCompareBarChart("Kobe Bryant", "Yao Ming", "Career", list, 1);
        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 1366, 700);
        frame.setVisible(true);
        JPanel panel = new JPanel();
        JLabel j = new JLabel(img);
        panel.add(j);
        frame.setContentPane(panel);
        frame.revalidate();
    }

}