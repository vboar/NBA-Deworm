package service;

import javax.swing.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 统计服务接口
 * Created by Vboar on 2015/6/11.
 */
public interface StatsService extends Remote  {

    /**
     * 获得指定球员的某个赛季的常规赛/季后赛的雷达图
     * @param name 球员名字
     * @param season 赛季 生涯为Career
     * @param regular 常规赛/季后赛
     * @return 雷达图
     */
    public ImageIcon getPlayerRadar(String name, String season, int regular) throws RemoteException;

}
