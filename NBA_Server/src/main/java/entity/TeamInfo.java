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
	 * 地点
	 */
	private String location;
	
	/**
	 * 参与赛季
	 */
	private String season;
	
	/**
	 * record
	 */
	private String record;
	
	/**
	 * 参加季后赛次数
	 */
	private int playeroff_appearance;
	
	/**
	 * 夺冠次数
	 */
	private int championships;
	
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

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public int getPlayeroff_appearance() {
		return playeroff_appearance;
	}

	public void setPlayeroff_appearance(int playeroff_appearance) {
		this.playeroff_appearance = playeroff_appearance;
	}

	public int getChampionships() {
		return championships;
	}

	public void setChampionships(int championships) {
		this.championships = championships;
	}
	
}
