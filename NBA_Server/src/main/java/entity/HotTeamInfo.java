package entity;

import util.FieldType;

/**
 * 赛季热点球队类
 * 
 * created by JaneLDQ on 2015年6月5日 下午7:47:02
 */
public class HotTeamInfo {
	
	/**
	 * 队名
	 */
	private String name;
	
	/**
	 * 缩写
	 */
	private String abbr;
	
	/**
	 * 所在联盟
	 */
	private String league;
	
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
	
	public HotTeamInfo(){}

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

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}
	
}
