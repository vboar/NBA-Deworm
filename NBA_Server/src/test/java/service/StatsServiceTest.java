package service;

import junit.framework.TestCase;
import service.impl.StatsServiceImpl;

import javax.swing.*;
import java.rmi.RemoteException;

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
        ImageIcon img = ss.getPlayerRadar("Aaron Gray", "Career", 1);
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 500, 500);
        frame.setVisible(true);
        JPanel panel = new JPanel();
        JLabel j = new JLabel(img);
        panel.add(j);
        frame.setContentPane(panel);
        frame.revalidate();
    }
}