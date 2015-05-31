package vo;

/**
 * PlayerAdvanced数据VO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:03:14
 */
public class PlayerAdvancedVO {

	/**
	 * 球员姓名
	 */
	public String name;
	
	/**
	 * 赛季
	 */
	public String season;
	
	/**
	 * 常规赛/季后赛   true = normal, false = playeroff
	 */
	public boolean is_normal;
	
	/**
	 * 所属球队（缩写）
	 */
	public String team;
	
	/**
	 * 位置
	 */
	public String position;
	
	/**
	 * 比赛场数
	 */
	public int game;
	
	/**
	 * 在场时间
	 */
	public int minute;
	
	/**
	 * player efficiency rating
	 */
	public double per;
	/**
	 * true shooting percentage
	 */
	public double ts_pct;
	
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
	 * 盖帽率
	 */
	public double blk_pct;
	
	/**
	 * 失误率
	 */
	public double tov_pct;
	
	/**
	 * 使用率
	 */
	public double usg_pct;
	
	/**
	 * offensive win shares
	 */
	public double ows;
	
	/**
	 * defensive win shares
	 */
	public double dws;
	
	/**
	 * win shares
	 */
	public double ws;
	
	/**
	 * win shares per 48 mins;
	 */
	public double ws_48;
	
	/**
	 * offensive box plus/minus
	 */
	public double obpm;
	
	/**
	 * defensive box plus/minus
	 */
	public double dbpm;
	
	/**
	 * box plus/minus
	 */
	public double bpm;
	
	/**
	 * value over replacement player
	 */
	public double vorp;
	
	public PlayerAdvancedVO(){}
	
}
