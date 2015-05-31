package entity;

import java.util.ArrayList;
import java.util.List;

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
	private Boolean is_normal;
	
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
	 * 主队基本球员数据
	 */
	private List<MatchPlayerBasic> home_basic_list = new ArrayList<MatchPlayerBasic>();
	
	/**
	 * 主队高阶球员数据
	 */
	private List<MatchPlayerAdvanced> home_advanced_list = new ArrayList<MatchPlayerAdvanced>();
	
	/**
	 * 客队基本球员数据
	 */
	private List<MatchPlayerBasic> guest_basic_list = new ArrayList<MatchPlayerBasic>();
	
	/**
	 * 客队高阶球员数据
	 */
	private List<MatchPlayerAdvanced> guest_advanced_list = new ArrayList<MatchPlayerAdvanced>();
	
	/**
	 * 主队小节比分
	 */
	private List<Integer> home_pts = new ArrayList<Integer>();
	
	/**
	 * 客队小节比分
	 */
	private List<Integer> guest_pts = new ArrayList<Integer>();
	
	/**
	 * 无参构造函数
	 */
	public Match(){}

	public String getGame_id() {
		return game_id;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
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

	public List<Integer> getHome_pts() {
		return home_pts;
	}

	public void setHome_pts(List<Integer> home_pts) {
		this.home_pts = home_pts;
	}

	public List<Integer> getGuest_pts() {
		return guest_pts;
	}

	public void setGuest_pts(List<Integer> guest_pts) {
		this.guest_pts = guest_pts;
	}

	public boolean isNormal() {
		return is_normal;
	}

	public void setNormal(boolean isNormal) {
		this.is_normal = isNormal;
	}

	public List<MatchPlayerBasic> getHome_basic_list() {
		return home_basic_list;
	}

	public void setHome_basic_list(List<MatchPlayerBasic> home_basic_list) {
		this.home_basic_list = home_basic_list;
	}

	public List<MatchPlayerAdvanced> getHome_advanced_list() {
		return home_advanced_list;
	}

	public void setHome_advanced_list(List<MatchPlayerAdvanced> home_advanced_list) {
		this.home_advanced_list = home_advanced_list;
	}

	public List<MatchPlayerBasic> getGuest_basic_list() {
		return guest_basic_list;
	}

	public void setGuest_basic_list(List<MatchPlayerBasic> guest_basic_list) {
		this.guest_basic_list = guest_basic_list;
	}

	public List<MatchPlayerAdvanced> getGuest_advanced_list() {
		return guest_advanced_list;
	}

	public void setGuest_advanced_list(List<MatchPlayerAdvanced> guest_advanced_list) {
		this.guest_advanced_list = guest_advanced_list;
	}
	
}
