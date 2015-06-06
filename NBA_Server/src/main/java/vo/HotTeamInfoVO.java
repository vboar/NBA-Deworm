package vo;

import util.FieldType;

/**
 * 热点球队信息类
 * 
 * created by JaneLDQ on 2015年6月5日 下午7:53:32
 */
public class HotTeamInfoVO {
	
	/**
	 * 队名
	 */
	public String name;
	
	/**
	 * 缩写
	 */
	public String abbr;
	
	/**
	 * 所在联盟
	 */
	public String league;
	
	/**
	 * 赛季
	 */
	public String season;
	
	/**
	 * 热门属性
	 */
	public FieldType field;
	
	/**
	 * 热门属性值
	 */
	public String value;
}
