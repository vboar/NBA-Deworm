package vo;

import java.io.Serializable;

/**
 * PlayerInfo数据VO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:04:07
 */
public class PlayerInfoVO implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 姓名
	 */
	public String name;
	
	/**
	 * 出生年月
	 */
	public String born;
	
	/**
	 * 出生地
	 */
	public String hometown;
	
	/**
	 * 位置
	 */
	public String position;
	
	/**
	 * 身高
	 */
	public String height;
	
	/**
	 * 体重
	 */
	public Integer weight;
	
	/**
	 * shoots
	 */
	public String shoots;
	
	/**
	 * 高中
	 */
	public String high_school;
	
	/**
	 * 大学
	 */
	public String college;
	
	/**
	 * NBA Draft
	 */
	public String draft;
	
	/**
	 * NBA Debut 初次登场
	 */
	public String debut;
	
	/**
	 * NBA球龄（经验）
	 */
	public Integer exp;
	
	/**
	 * 球衣号
	 */
	public Integer number;
	
	public PlayerInfoVO(){}
	
}
