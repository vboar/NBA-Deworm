package vo;

import java.io.Serializable;

/**
 * 球员数据查看筛选器
 * 
 * created by JaneLDQ on 2015年6月2日 下午7:18:33
 */
public class PlayerFilter implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 赛季
	 */
	public String season;
	
	/**
	 * 常规赛/季后赛  = 1/0, null为全部
	 */
	public Integer regular;
	
	/**
	 * 分区
	 */
	public String division;
	
	/**
	 * 联盟
	 */
	public String league;
	
	/**
	 * 位置
	 */
	public String position;
	
	/**
	 * 身高(e.g. "<6-6" 符号为"<>="，默认只占一位，后面为身高的值)
	 */
	public String height;
	
	/**
	 * 体重(e.g. "<275" )
	 */
	public String weight;
	
	/**
	 * 球队
	 */
	public String team;
	
}
