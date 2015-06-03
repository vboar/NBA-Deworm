package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;

import vo.PlayerAdvancedVO;
import vo.PlayerInfoVO;
import vo.PlayerPerGameVO;
import vo.PlayerSalaryVO;
import vo.PlayerTotalVO;

/**
 * 球员RMI数据服务接口
 * 
 * created by JaneLDQ on 2015年5月31日 下午6:31:07
 */
public interface PlayerService extends Remote{

	/**
	 * 获得一列球员头像
	 * @param names 球员名字列表
	 * @return
	 */
	public List<ImageIcon> getPlayerPortraitByNameList(List<String> names) throws RemoteException;
	
	/**
	 * 获得单个球员头像
	 * @param name 球员姓名
	 * @return
	 */
	public ImageIcon getPlayerPortraitByName(String name) throws RemoteException;
	
	/**
	 * 根据名字首字母获得球员信息
	 * @param initial 首字母A-Z
	 */
	public List<PlayerInfoVO> getPlayerInfoByNameInitial(String initial) throws RemoteException;
	
	/**
	 * 获取单个球员基本信息
	 * @param name 姓名
	 * @return
	 */
	public PlayerInfoVO getPlayerInfoByName(String name) throws RemoteException;
	
	/**
	 * 获取单个球员所有赛季的总数据
	 * @param name 球员姓名
	 * @return
	 */
	public List<PlayerTotalVO> getPlayerTotalByName(String name) throws RemoteException;
	
	/**
	 * 获取单个球员所有赛季的场均数据
	 * @param name 球员姓名
	 * @return
	 */
	public List<PlayerPerGameVO> getPlayerPerGameByName(String name) throws RemoteException;
	
	/**
	 * 获取单个球员所有赛季的高阶数据
	 * @param name 球员姓名
	 * @return
	 */
	public List<PlayerAdvancedVO> getPlayerAdvancedByName(String name) throws RemoteException;
	
	/**
	 * 获得单个球员每赛季的薪水列表
	 * @param name
	 * @return
	 */
	public List<PlayerSalaryVO> getPlayerSalaryByName(String name) throws RemoteException;
	
	/**
	 * 获取单赛季的所有球员总数据
	 * @param season 赛季
	 * @return
	 */
	public List<PlayerTotalVO> getPlayerTotalBySeason(String season) throws RemoteException;
	
	/**
	 * 获取单个赛季的所有球员场均数据
	 * @param season 赛季
	 * @return
	 */
	public List<PlayerPerGameVO> getPlayerPerGameBySeason(String season) throws RemoteException;
	
	/**
	 * 获取单个赛季所有球员的高阶数据
	 * @param season 赛季
	 * @return
	 */
	public List<PlayerAdvancedVO> getPlayerAdvancedBySeason(String season) throws RemoteException;
	
}
