package vo;

import java.io.Serializable;

/**
 * PlayerTotal数据VO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:07:09
 */
public class PlayerTotalVO implements Serializable{

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
	 * 常规赛/季后赛  true = normal, false = playoff
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
	 * 首发场数
	 */
	public Integer game_started;
	
	/**
	 * 在场时间
	 */
	public Integer minute;
	
	/**
	 * 投篮命中数
	 */
	public Integer fg;
	
	/**
	 * 投篮出手数
	 */
	public Integer fga;
	
	/**
	 * 投篮命中率
	 */
	public double fga_pct;
	
	/**
	 * 三分命中数 
	 */
	public Integer fg3;
	
	/**
	 * 三分出手数
	 */
	public Integer fg3a;
	
	/**
	 * 三分命中率
	 */
	public double fg3_pct;
	
	/**
	 * 两分命中数
	 */
	public Integer fg2;
	
	/**
	 * 两分投篮数
	 */
	public Integer fg2a;
	
	/**
	 * 两分命中率
	 */
	public double fg2_pct;
	
	/**
	 * effectivce field goal percentage
	 */
	public double efg_pct;
	
	/**
	 * 罚球命中数
	 */
	public Integer ft;
	
	/**
	 * 罚球出手数
	 */
	public Integer fta;
	
	/**
	 * 罚球命中率
	 */
	public double ft_pct;
	
	/**
	 * 进攻篮板
	 */
	public Integer orb;
	
	/**
	 * 防守篮板
	 */
	public Integer drb;
	
	/**
	 * 总篮板
	 */
	public Integer trb;
	
	/**
	 * 助攻
	 */
	public Integer ast;
	
	/**
	 * 抢断
	 */
	public Integer stl;
	
	/**
	 * 盖帽
	 */
	public Integer blk;
	
	/**
	 * 失误
	 */
	public Integer tov;
	
	/**
	 * 犯规
	 */
	public Integer pf;
	
	/**
	 * 个人得分
	 */
	public Integer pts;
	
	public PlayerTotalVO(){}
}
