package vo;

import java.io.Serializable;

/**
 * PlayerSalary数据VO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:06:12
 */
public class PlayerSalaryVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 姓名
	 */
	public String name;
	
	/**
	 * 赛季
	 */
	public String season;
	
	/**
	 * 球队
	 */
	public String team;
	
	/**
	 * 薪水
	 */
	public Long salary;
	
	/**
	 * 无参构造函数
	 */
	public PlayerSalaryVO(){}
	
}
