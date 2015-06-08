package entity;


/**
 * 比赛基本信息
 * 
 * created by JaneLDQ on 2015年5月31日 下午2:02:15
 */
public class MatchInfo {

	/**
	 * 比赛编号
	 */
	private String game_id;
	
	/**
	 * 赛季
	 */
	private String season;
	
	/**
	 * 日期
	 */
	private String date;
	
	/**
	 * 常规赛/季后赛
	 */
	private Integer is_normal;
	
	/**
	 * 地点
	 */
	private String location;
	
	/**
	 * 主队
	 */
	private String home_team;
	
	/**
	 * 主队得分
	 */
	private Integer home_point;
	
	/**
	 * 客队
	 */
	private String guest_team;
	
	/**
	 * 客队得分
	 */
	private Integer guest_point;
	
	/**
	 * 比赛时长
	 */
	private String time="";
	
	/**
	 *  无参构造函数
	 */
	public MatchInfo(){}

	public String getGame_id() {
		return game_id;
	}

	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHome_team() {
		return home_team;
	}

	public void setHome_team(String home_team) {
		this.home_team = home_team;
	}

	public Integer getHome_point() {
		return home_point;
	}

	public void setHome_point(Integer home_point) {
		this.home_point = home_point;
	}

	public String getGuest_team() {
		return guest_team;
	}

	public void setGuest_team(String guest_team) {
		this.guest_team = guest_team;
	}

	public Integer getGuest_point() {
		return guest_point;
	}

	public void setGuest_point(Integer guest_point) {
		this.guest_point = guest_point;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer isIs_normal() {
		return is_normal;
	}

	public void setIs_normal(Integer is_normal) {
		this.is_normal = is_normal;
	}
	
}
