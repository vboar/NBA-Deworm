package entity;

import util.FieldType;

/**
 * 热点球员信息类
 * 
 * created by JaneLDQ on 2015年6月6日 上午9:42:35
 */
public class HotPlayerInfo {

	/**
	 * 球员姓名
	 */
	private String name;

	/**
	 * 球队缩写
	 */
	private String team;
	
	/**
	 * 位置
	 */
	private String position;
	
	/**
	 * 赛季
	 */
	private String season;
	
	/**
	 * 热门属性
	 */
	private FieldType field;
	
	/**
	 * 热门属性值
	 */
	private String value;
	
	public HotPlayerInfo(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String potisition) {
		this.position = potisition;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public FieldType getField() {
		return field;
	}

	public void setField(FieldType field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
