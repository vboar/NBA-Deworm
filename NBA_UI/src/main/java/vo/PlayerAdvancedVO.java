package vo;

import java.io.Serializable;

/**
 * PlayerAdvanced数据VO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:03:14
 */
public class PlayerAdvancedVO implements Serializable{

	private static final long serialVersionUID = 1L;

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
	public Boolean is_normal;
	
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
	public Integer game;
	
	/**
	 * 在场时间
	 */
	public Integer minute;
	
	/**
	 * player efficiency rating
	 */
	public Double per;
	/**
	 * true shooting percentage
	 */
	public Double ts_pct;
	
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
	 * 盖帽率
	 */
	public Double blk_pct;
	
	/**
	 * 失误率
	 */
	public Double tov_pct;
	
	/**
	 * 使用率
	 */
	public Double usg_pct;
	
	/**
	 * offensive win shares
	 */
	public Double ows;
	
	/**
	 * defensive win shares
	 */
	public Double dws;
	
	/**
	 * win shares
	 */
	public Double ws;
	
	/**
	 * win shares per 48 mins;
	 */
	public Double ws_48;
	
	/**
	 * offensive box plus/minus
	 */
	public Double obpm;
	
	/**
	 * defensive box plus/minus
	 */
	public Double dbpm;
	
	/**
	 * box plus/minus
	 */
	public Double bpm;
	
	/**
	 * value over replacement player
	 */
	public Double vorp;
	
	public PlayerAdvancedVO(){}
	
}
