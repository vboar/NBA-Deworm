package service.impl;

import dao.PlayerDao;
import dao.impl.DaoFactoryImpl;
import entity.PlayerStatsPerGame;
import service.StatsService;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vboar on 2015/6/11.
 */
public class StatsServiceImpl extends UnicastRemoteObject implements StatsService {

    private static final long serialVersionUID = 1L;
    private PlayerDao pdao;

    public StatsServiceImpl() throws RemoteException {
        super();
        pdao = DaoFactoryImpl.getDaoFactory().getPlayerDao();
    }

    @Override
    public ImageIcon getPlayerRadar(String name, String season, int regular) throws RemoteException {
        List<PlayerStatsPerGame> list = pdao.getPlayerPerGameBySeasonName(season, name, regular);
        PlayerStatsPerGame ps = new PlayerStatsPerGame();
        if (list.size() > 1) {
            for (PlayerStatsPerGame po: list) {
                if (po.getTeam().equals("TOT")) {
                    ps = po;
                    break;
                }
            }
        } else {
            ps = list.get(0);
        }
        String path = "stats/PlayerRadar";
        String s = name + ";" + season + ";" + regular + ";" + ps.getAst() + ";" + ps.getStl() + ";" +
                ps.getBlk() + ";" + ps.getTov() + ";" + ps.getPf();
        write(s, path + ".txt");
        try {
            Process p = Runtime.getRuntime().exec("python stats/player_radar.py");
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageIcon img = new ImageIcon(path + ".png");
        return img;
    }

    /**
     * 写入多行数据到文本文件（覆盖）
     * @param path 存储路径
     */
    private void writeMulti(List<String> list, String path) {
        try {
            FileWriter fw = new FileWriter(path, false);
            for (String s : list) {
                fw.write(s + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入一行数据到文本文件（覆盖）
     * @param s
     * @param path
     */
    private void write(String s, String path) {
        try {
            FileWriter fw = new FileWriter(path, false);
            fw.write(s + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
