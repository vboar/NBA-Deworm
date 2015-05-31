package vo;

import java.io.Serializable;

/**
 * PlayerPerGame数据VO类
 * 
 * created by JaneLDQ on 2015年5月31日 下午4:05:02
 */
public class PlayerPerGameVO implements Serializable{

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
	 * 常规赛/季后赛  true = normal, false = playeroff
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
	public Double minute;
	
	/**
	 * 投篮命中数
	 */
	public Double fg;
	
	/**
	 * 投篮出手数
	 */
	public Double fga;
	
	/**
	 * 投篮命中率
	 */
	public Double fga_pct;
	
	/**
	 * 三分命中数 
	 */
	public Double fg3;
	
	/**
	 * 三分出手数
	 */
	public Double fg3a;
	
	/**
	 * 三分命中率
	 */
	public Double fg3_pct;
	
	/**
	 * 两分命中数
	 */
	public Double fg2;
	
	/**
	 * 两分投篮数
	 */
	public Double fg2a;
	
	/**
	 * 两分命中率
	 */
	public Double fg2_pct;
	
	/**
	 * effectivce field goal percentage
	 */
	public Double efg_pct;
	
	/**
	 * 罚球命中数
	 */
	public Double ft;
	
	/**
	 * 罚球出手数
	 */
	public Double fta;
	
	/**
	 * 罚球命中率
	 */
	public Double ft_pct;
	
	/**
	 * 进攻篮板
	 */
	public Double orb;
	
	/**
	 * 防守篮板
	 */
	public Double drb;
	
	/**
	 * 总篮板
	 */
	public Double trb;
	
	/**
	 * 助攻
	 */
	public Double ast;
	
	/**
	 * 抢断
	 */
	public Double stl;
	
	/**
	 * 盖帽
	 */
	public Double blk;
	
	/**
	 * 失误
	 */
	public Double tov;
	
	/**
	 * 犯规
	 */
	public Double pf;
	
	/**
	 * 个人得分
	 */
	public Double pts;
	
	/**
	 * 无参构造函数
	 */
	public PlayerPerGameVO(){}
}
