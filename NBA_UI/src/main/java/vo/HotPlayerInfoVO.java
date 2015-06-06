package vo;

import util.FieldType;

/**
 * 热点球员信息VO类
 * 
 * created by JaneLDQ on 2015年6月6日 上午9:48:38
 */
public class HotPlayerInfoVO {

	/**
	 * 球员姓名
	 */
	public String name;

	/**
	 * 球队缩写
	 */
	public String team;
	
	/**
	 * 位置
	 */
	public String position;
	
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
	
	public HotPlayerInfoVO(){}
}
