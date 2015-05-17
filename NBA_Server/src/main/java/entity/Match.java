package entity;

/**
 * 比赛实体类
 * 
 * created by JaneLDQ on 2015年5月17日 下午12:45:48
 */
public class Match {

	/**
	 * 比赛编号
	 */
	private String game_id;
	
	/**
	 * 日期
	 */
	private String date;
	
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
	private int home_point;
	
	/**
	 * 客队
	 */
	private String guest_team;
	
	/**
	 * 客队得分
	 */
	private int guest_point;
	
	/**
	 * 比赛时长
	 */
	private String time;
	
	/**
	 * 无参构造函数
	 */
	public Match(){}

	public String getGame_id() {
		return game_id;
	}

	public void setGame_id(String game_id) {
		this.game_id = game_id;
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

	public int getHome_point() {
		return home_point;
	}

	public void setHome_point(int home_point) {
		this.home_point = home_point;
	}

	public String getGuest_team() {
		return guest_team;
	}

	public void setGuest_team(String guest_team) {
		this.guest_team = guest_team;
	}

	public int getGuest_point() {
		return guest_point;
	}

	public void setGuest_point(int guest_point) {
		this.guest_point = guest_point;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
}
