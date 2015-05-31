package vo;

import java.util.ArrayList;
import java.util.List;

/**
 * MatchInfoVO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:00:15
 */
public class MatchInfoVO {

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
	 * 主队小节比分
	 */
	public List<Integer> home_pts = new ArrayList<Integer>();
	
	/**
	 * 客队小节比分
	 */
	public List<Integer> guest_pts = new ArrayList<Integer>();
	
	/**
	 *  无参构造函数
	 */
	public MatchInfoVO(){}
	
}
