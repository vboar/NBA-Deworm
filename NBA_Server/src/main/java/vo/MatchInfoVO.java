package vo;

import java.io.Serializable;

/**
 * MatchInfoVO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:00:15
 */
public class MatchInfoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 比赛编号
	 */
	public String game_id;
	
	/**
	 * 赛季
	 */
	public String season;
	
	/**
	 * 日期
	 */
	public String date;
	
	/**
	 * 常规赛/季后赛
	 */
	public Boolean is_normal;
	
	/**
	 * 地点
	 */
	public String location;
	
	/**
	 * 主队
	 */
	public String home_team;
	
	/**
	 * 主队得分
	 */
	public int home_point;
	
	/**
	 * 客队
	 */
	public String guest_team;
	
	/**
	 * 客队得分
	 */
	public int guest_point;
	
	/**
	 * 比赛时长
	 */
	public String time;
	
	/**
	 *  无参构造函数
	 */
	public MatchInfoVO(){}
	
}
