package entity;

/**
 * 比赛小节记分实体类
 * 
 * created by JaneLDQ on 2015年5月17日 下午12:50:09
 */
public class MatchScore {

	/**
	 * 比赛编号
	 */
	private String game_id;
	
	/**
	 * 小节
	 */
	private Integer section;
	
	/**
	 * 主队得分
	 */
	private Integer home_point;
	
	/**
	 * 客队得分
	 */
	private Integer guest_point;
	
	/**
	 * 无参构造函数
	 */
	public MatchScore(){}

	public String getGame_id() {
		return game_id;
	}

	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	public Integer getHome_point() {
		return home_point;
	}

	public void setHome_point(Integer home_point) {
		this.home_point = home_point;
	}

	public Integer getGuest_point() {
		return guest_point;
	}

	public void setGuest_point(Integer guest_point) {
		this.guest_point = guest_point;
	}
	
	
}
