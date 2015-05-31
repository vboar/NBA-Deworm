package vo;

/**
 * MatchPlayerAdvancedVO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:01:06
 */
public class MatchPlayerAdvancedVO {

	/**
	 * 比赛编号
	 */
	public String game_id;
	
	/**
	 * 球员名
	 */
	public String player_name;
	
	/**
	 * 球队缩写
	 */
	public  String team_abbr;
	
	/**
	 * 首发(Team Total球队总数据，Starter首发球员，Reserve是非首发上场，DidNotPlayer冷板凳)
	 */
	public String starter;
	
	/**
	 * 上场时间
	 */
	public double minute;
	
	/**
	 * true shooting percentage
	 */
	public double ts_pct;
	
	/**
	 * effective field goal percentage
	 */
	public double efg_pct;
	
	/**
	 * 3-point attempt rate
	 */
	public double fa3a_per_fga_pct;
	
	/**
	 * free throw attempt rate
	 */
	public double fta_per_fga_pct;
	
	/**
	 * 进攻篮板率
	 */
	public double orb_pct;
	
	/**
	 * 防守篮板率
	 */
	public double drb_pct;
	
	/**
	 * 总篮板率
	 */
	public double trb_pct;
	
	/**
	 * 助攻率
	 */
	public double ast_pct;
	
	/**
	 * 抢断率
	 */
	public double stl_pct;
	
	/**
	 * 失误率
	 */
	public double tov_pct;
	
	/**
	 * 盖帽率
	 */
	public double blk_pct;
	
	/**
	 * 使用率
	 */
	public double usg_pct;
	
	/**
	 * 进攻效率
	 */
	public double off_rtg;
	
	/**
	 * 防守效率
	 */
	public double def_rtg;
	
	/**
	 * 无参构造函数
	 */
	public MatchPlayerAdvancedVO(){}
	
}
