package vo;

import java.io.Serializable;

/**
 * 球队数据查看
 * 
 * created by JaneLDQ on 2015年6月2日 下午9:17:46
 */
public class TeamFilter implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 赛季
	 */
	public String season;
	
	/**
	 * 分区
	 */
	public String division;
	
	/**
	 * 联盟
	 */
	public String league;

}
