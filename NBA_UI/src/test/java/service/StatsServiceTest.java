package service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import service.impl.ServiceFactoryImpl;
import util.FieldType;

/**
 * Created by Vboar on 2015/6/14.
 */
public class StatsServiceTest {

    public static void main(String[] args) throws RemoteException {

        StatsService ss = ServiceFactoryImpl.getInstance().getStatsService();
        List<Integer> list = new ArrayList<Integer>();
		list.add(FieldType.PTS.ordinal());
		list.add(FieldType.AST.ordinal());
		list.add(FieldType.BLK.ordinal());
		list.add(FieldType.STL.ordinal());
		list.add(FieldType.TRB.ordinal());		
		list.add(FieldType.ORB.ordinal());
		list.add(FieldType.DRB.ordinal());
		list.add(FieldType.TOV.ordinal());
		list.add(FieldType.PF.ordinal());	
//        ImageIcon img = ss.getPlayerRadar("Kobe Bryant", "13-14", 1);
//        ImageIcon img = ss.getPlayerCareerLineChart("Kobe Bryant", FieldType.AST.ordinal(), 1);
//        ImageIcon img = ss.getMatchPlayerLineChart("Kobe Bryant", "09-10", FieldType.PTS.ordinal());
//        ImageIcon img = ss.getMatchTeamLineChart("HOU", "09-10", FieldType.TRB.ordinal());
        ImageIcon img = ss.getPlayerBasicCompareBarChart("Kobe Bryant", "Yao Ming", "04-05",
        		list, 1);
        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 666, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JLabel j = new JLabel(img);
        panel.add(j);
        frame.setContentPane(panel);
        frame.revalidate();
    }

}
