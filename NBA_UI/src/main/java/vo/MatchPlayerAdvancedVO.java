package vo;

import java.io.Serializable;

/**
 * MatchPlayerAdvancedVO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:01:06
 */
public class MatchPlayerAdvancedVO implements Serializable{

	private static final long serialVersionUID = 1L;

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
	public Double minute;
	
	/**
	 * true shooting percentage
	 */
	public Double ts_pct;
	
	/**
	 * effective field goal percentage
	 */
	public Double efg_pct;
	
	/**
	 * 3-point attempt rate
	 */
	public Double fa3a_per_fga_pct;
	
	/**
	 * free throw attempt rate
	 */
	public Double fta_per_fga_pct;
	
	/**
	 * 进攻篮板率
	 */
	public Double orb_pct;
	
	/**
	 * 防守篮板率
	 */
	public Double drb_pct;
	
	/**
	 * 总篮板率
	 */
	public Double trb_pct;
	
	/**
	 * 助攻率
	 */
	public Double ast_pct;
	
	/**
	 * 抢断率
	 */
	public Double stl_pct;
	
	/**
	 * 失误率
	 */
	public Double tov_pct;
	
	/**
	 * 盖帽率
	 */
	public Double blk_pct;
	
	/**
	 * 使用率
	 */
	public Double usg_pct;
	
	/**
	 * 进攻效率
	 */
	public Double off_rtg;
	
	/**
	 * 防守效率
	 */
	public Double def_rtg;
	
	/**
	 * 无参构造函数
	 */
	public MatchPlayerAdvancedVO(){}
	
}
