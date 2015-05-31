package entity;

/**
 * 球队信息类
 * 
 * created by JaneLDQ on 2015年5月17日 下午2:05:49
 */
public class TeamInfo {

	/**
	 * 球队名
	 */
	private String name;
	
	/**
	 * 球队缩写
	 */
	private String abbr;
	
	/**
	 * 建立时间
	 */
	private String buildup_time;
	
	/**
	 * 地点
	 */
	private String location;
	
	/**
	 * 分区
	 */
	private String division;
	
	/**
	 * 联盟
	 */
	private String league;
	
	/**
	 * record
	 */
	private String record;
	
	/**
	 * 参加季后赛次数
	 */
	private Integer playeroff_appearance;
	
	/**
	 * 夺冠次数
	 */
	private Integer championships;
	
	/**
	 * 无参构造函数2
	 */
	public TeamInfo(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public Integer getPlayeroff_appearance() {
		return playeroff_appearance;
	}

	public void setPlayeroff_appearance(Integer playeroff_appearance) {
		this.playeroff_appearance = playeroff_appearance;
	}

	public Integer getChampionships() {
		return championships;
	}

	public void setChampionships(Integer championships) {
		this.championships = championships;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getBuildup_time() {
		return buildup_time;
	}

	public void setBuildup_time(String buildup_time) {
		this.buildup_time = buildup_time;
	}
	
}
