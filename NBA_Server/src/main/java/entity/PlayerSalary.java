package entity;

/**
 * 球员薪水信息
 * 
 * created by JaneLDQ on 2015年5月17日 下午1:55:05
 */
public class PlayerSalary {

	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 赛季
	 */
	private String season;
	
	/**
	 * 球队
	 */
	private String team;
	
	/**
	 * 薪水
	 */
	private Long salary;
	
	/**
	 * 无参构造函数
	 */
	public PlayerSalary(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

}
