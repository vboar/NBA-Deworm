package service;

import service.impl.ServiceFactoryImpl;
import util.FieldType;

import javax.swing.*;
import java.rmi.RemoteException;

/**
 * Created by Vboar on 2015/6/14.
 */
public class StatsServiceTest {

    public static void main(String[] args) throws RemoteException {

        StatsService ss = ServiceFactoryImpl.getInstance().getStatsService();
//        ImageIcon img = ss.getPlayerRadar("Kobe Bryant", "13-14", 1);
//        ImageIcon img = ss.getPlayerCareerLineChart("Kobe Bryant", FieldType.typeToInt(FieldType.BLK), 1);
//        ImageIcon img = ss.getMatchPlayerLineChart("Kobe Bryant", "09-10", FieldType.typeToInt(FieldType.PTS));
        ImageIcon img = ss.getMatchTeamLineChart("HOU", "09-10", FieldType.TRB.ordinal());
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
