package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;

import util.FieldType;

/**
 * 统计服务接口 Created by Vboar on 2015/6/11.
 */
public interface StatsService extends Remote {

	/**
	 * 获得指定球员的某个赛季的常规赛/季后赛的雷达图
	 * 
	 * @param name
	 *            球员名字
	 * @param season
	 *            赛季 生涯为Career
	 * @param regular
	 *            常规赛/季后赛
	 * @return 雷达图
	 */
	public ImageIcon getPlayerRadar(String name, String season, int regular)
			throws RemoteException;

	/**
	 * 获得指定两个球员某赛季的常规赛/季后赛的雷达图
	 * 
	 * @param playerA
	 *            球员A
	 * @param playerB
	 *            球员B
	 * @param season
	 *            赛季，生涯为Career
	 * @param regular
	 *            常规赛/季后赛
	 * @return
	 * @throws RemoteException
	 */
	public ImageIcon getPlayerCompareRadar(String playerA, String playerB,
			String season, int regular) throws RemoteException;

	/**
	 * 获得指定球员指定数据的常规赛/季后赛历史折线图
	 * 
	 * @param player
	 *            球员姓名
	 * @param field
	 *            指定数据
	 * @param regular
	 *            常规赛/季后赛
	 * @return
	 * @throws RemoteException
	 */
	public ImageIcon getPlayerCareerLineChart(String player, FieldType field,
			int regular) throws RemoteException;

	/**
	 * 获得指定两个球队的指定基本数据的常规赛/季后赛历史对比直方图
	 * 
	 * @param playerA
	 *            球员A姓名
	 * @param playerB
	 *            球员B姓名
	 * @param season
	 *            赛季 ，生涯为Career
	 * @param fields (范围： pts, ast, blk, stl, trb, orb, drb,tov, pf)
	 *            带比较的数据
	 * @param regular
	 *            常规赛/季后赛
	 * @return
	 * @throws RemoteException
	 */
	public ImageIcon getPlayerBasicCompareBarChart(String playerA,
			String playerB, String season, List<FieldType> fields, int regular)
			throws RemoteException;

	/**
	 * 获得指定两个球队的指定高阶数据的常规赛/季后赛历史对比直方图
	 * @param playerA 球员A姓名
	 * @param playerB
	 * @param season
	 * @param fields (范围： per, orb_pct, drb_pct, trb_pct, ast_pct, stl_pct, blk_pct, tov_pct, usg_pct)
	 * @param regular
	 * @return
	 * @throws RemoteException
	 */
	public ImageIcon getPlayerAdvancedCompareBarChart(String playerA,
			String playerB, String season, List<FieldType> fields, int regular)
			throws RemoteException;
	
	/**
	 * 获得指定两个球队指定xx率的常规赛/季后赛历史对比直方图
	 * @param playerA
	 * @param playerB
	 * @param season
	 * @param fields (范围： fg3_pct, fga_pct, ft_pct, ts_pct)
	 * @param regular
	 * @return
	 * @throws RemoteException
	 */
	public ImageIcon getPlayerPctCompareBarChart(String playerA,
			String playerB, String season, List<FieldType> fields, int regular)
			throws RemoteException;
	
	/**
	 * 球队某赛季六项雷达图
	 * @param team 球队缩写
	 * @param season 赛季
	 * @return
	 * @throws RemoteException
	 */
	public ImageIcon getTeamRadar(String team, String season) throws RemoteException;

	/**
	 * 两只球队某赛季六项雷达图
	 * @param teamA 球队A缩写
	 * @param teamB 球队B缩写
	 * @param season 赛季
	 * @return
	 * @throws RemoteException
	 */
	public ImageIcon getTeamCompareRadar(String teamA, String teamB, String season) throws RemoteException;
	
	/**
	 * 某球队某数据属性历史折线图
	 * @param team 球队缩写
	 * @param field 数据属性
	 * @return
	 * @throws RemoteException
	 */
	public ImageIcon getTeamCareerLineChar(String team, FieldType field) throws RemoteException;

	/**
	 * 获得两只球队某赛季基本属性对比直方图
	 * @param teamA 球队A缩写
	 * @param teamB 球队B缩写
	 * @param season 赛季
	 * @param field 对比属性(pts,ast,blk,stl,trb,drb,orb,tov,pf)
	 * @return
	 * @throws RemoteException
	 */
	public ImageIcon getTeamBasicCompareBarChar(String teamA, String teamB, String season, List<FieldType> field) throws RemoteException;

	/**
	 * 获得两只球队某赛季高阶属性对比直方图
	 * @param teamA 球队A缩写
	 * @param teamB 球队B缩写
	 * @param season 赛季
	 * @param field 对比高阶属性(orb_pct, drb_pct, off_rtg, def_rtg)
	 * @return
	 * @throws RemoteException
	 */
	public ImageIcon getTeamAdvancedCompareBarChar(String teamA, String teamB, String season, List<FieldType> field) throws RemoteException;

	/**
	 * 获得两只球队xx率属性对比直方图
	 * @param teamA 球队A缩写 
	 * @param teamB 球队B缩写
	 * @param season 赛季
	 * @param field 对比xx率属性(fga_pct, fg3_pct, ft_pct)
	 * @return
	 * @throws RemoteException
	 */
	public ImageIcon getTeamPctCompareBarChart(String teamA, String teamB, String season, List<FieldType> field) throws RemoteException;
	
}
